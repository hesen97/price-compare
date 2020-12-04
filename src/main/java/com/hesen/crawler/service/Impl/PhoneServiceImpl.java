package com.hesen.crawler.service.Impl;

import com.hesen.crawler.dao.PhoneDao;
import com.hesen.crawler.dto.CompareInfo;
import com.hesen.crawler.entity.Phone;
import com.hesen.crawler.enums.Accuracy;
import com.hesen.crawler.enums.Website;
import com.hesen.crawler.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.rmi.transport.ObjectTable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PhoneServiceImpl implements PhoneService {

    @Autowired
    private PhoneDao phoneDao;

    @Override
    public int addPhone(Phone phone) {
        return phoneDao.insertPhone(phone);
    }

    @Override
    public List<Phone> searchPhone(int websiteId, String searchStr) {
        Map<String, Object> criterion = new HashMap<>();
        criterion.put("websiteId", websiteId);
        criterion.put("searchStr", searchStr);
        return phoneDao.selectPhoneByCriterion(criterion);
    }

    @Override
    public List<Phone> searchPhoneWithAccuracy(int websiteId, String searchStr, Accuracy accuracy) {
        double[] bound = getBound(websiteId, searchStr, accuracy);
        if (bound == null) {
            return new ArrayList<>();
        }

        Map<String, Object> criterion = new HashMap<>();
        criterion.put("websiteId", websiteId);
        criterion.put("searchStr", searchStr);
        criterion.put("lowerBound", bound[0]);
        criterion.put("upperBound", bound[1]);

        return phoneDao.selectPhoneByCriterion(criterion);
    }

    @Override
    public List<CompareInfo> getCompareInfo(String searchStr) {
        List<CompareInfo> compareInfoList = new ArrayList<>();
        for (Website website : Website.values()) {
            compareInfoList.add(getCompareInfoHelper(searchStr, website));
        }
        return compareInfoList;
    }

    private CompareInfo getCompareInfoHelper(String searchStr, Website website) {
        Map<String, Object> criterion = new HashMap<>();
        criterion.put("websiteId", website.getWebsiteId());
        criterion.put("searchStr", searchStr);

        CompareInfo compareInfo = new CompareInfo();
        compareInfo.setWebsiteName(website.getWebsiteName());

        compareInfo.setResultSum(phoneDao.countPhoneByCriterion(criterion));
        if (compareInfo.getResultSum() == 0) {
            return compareInfo;
        }

        compareInfo.setMaxPrice(phoneDao.maxPrice(criterion));
        compareInfo.setMinPrice(phoneDao.minPrice(criterion));
        compareInfo.setAveragePrice(phoneDao.averagePrice(criterion));
        return compareInfo;
    }

    @Override
    public List<CompareInfo> getCompareInfoWithAccuracy(String searchStr, Accuracy accuracy) {
        List<CompareInfo> compareInfoList = new ArrayList<>();
        for (Website website : Website.values()) {
            CompareInfo compareInfo =
                    getCompareInfoWithAccuracyHelper(searchStr, accuracy, website);
            compareInfoList.add(compareInfo);
        }
        return compareInfoList;
    }

    private CompareInfo getCompareInfoWithAccuracyHelper(String searchStr, Accuracy accuracy,  Website website) {
        CompareInfo compareInfo = new CompareInfo();
        compareInfo.setWebsiteName(website.getWebsiteName());

        double[] bound = getBound(website.getWebsiteId(), searchStr, accuracy);
        if (bound == null) {
            compareInfo.setResultSum(0);
            return compareInfo;
        }

        Map<String, Object> criterion = new HashMap<>();
        criterion.put("websiteId", website.getWebsiteId());
        criterion.put("searchStr", searchStr);
        criterion.put("lowerBound", bound[0]);
        criterion.put("upperBound", bound[1]);

        compareInfo.setResultSum(phoneDao.countPhoneByCriterion(criterion));
        compareInfo.setMaxPrice(phoneDao.maxPrice(criterion));
        compareInfo.setMinPrice(phoneDao.minPrice(criterion));
        compareInfo.setAveragePrice(phoneDao.averagePrice(criterion));
        return compareInfo;
    }

    private double[] getBound(int websiteId, String searchStr, Accuracy accuracy) {
        Map<String, Object> criterion = new HashMap<>();
        criterion.put("websiteId", websiteId);
        criterion.put("searchStr", searchStr);

        int originalNum = phoneDao.countPhoneByCriterion(criterion);
        if (originalNum == 0) {
            return null;
        }

        int sampleNum = (int) (originalNum * 0.1);
        criterion.put("limit", sampleNum);

        List<Phone> samplePhoneList = phoneDao.selectPhoneByCriterion(criterion);
        double samplePriceSum = 0;
        for (Phone phone : samplePhoneList) {
            samplePriceSum += phone.getPrice();
        }
        double standardPrice = samplePriceSum / sampleNum;

        double[] bound = new double[2];
        bound[0] = standardPrice * (1.0 - accuracy.getValue());
        bound[1] = standardPrice * (1.0 + accuracy.getValue());
        return bound;
    }
}
