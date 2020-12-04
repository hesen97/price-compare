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
        Map<String, Object> criterion = new HashMap<>();
        criterion.put("websiteId", websiteId);
        criterion.put("searchStr", searchStr);

        int originalNum = phoneDao.countPhoneByCriterion(criterion);
        if (originalNum == 0) {
            return new ArrayList<>();
        }

        int sampleNum = (int) (originalNum * 0.1);
        criterion.put("limit", sampleNum);
        List<Phone> samplePhoneList = phoneDao.selectPhoneByCriterion(criterion);

        double samplePriceSum = 0;
        for (Phone phone : samplePhoneList) {
            samplePriceSum += phone.getPrice();
        }
        double standardPrice = samplePriceSum / sampleNum;
        double lowerBound = standardPrice * (1.0 - accuracy.getValue());
        double upperBound = standardPrice * (1.0 + accuracy.getValue());

        criterion.put("limit", null);
        criterion.put("lowerBound", lowerBound);
        criterion.put("upperBound", upperBound);

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
        Map<String, Object> criterion = new HashMap<>();
        criterion.put("websiteId", website.getWebsiteId());
        criterion.put("searchStr", searchStr);

        CompareInfo compareInfo = new CompareInfo();
        compareInfo.setWebsiteName(website.getWebsiteName());

        int originalNum = phoneDao.countPhoneByCriterion(criterion);
        if (originalNum == 0) {
            compareInfo.setResultSum(originalNum);
            return compareInfo;
        }

        int sampleNum = (int) (originalNum * 0.1);
        criterion.put("limit", sampleNum);
        List<Phone> samplePhoneList = phoneDao.selectPhoneByCriterion(criterion);

        double samplePriceSum = 0;
        for (Phone phone : samplePhoneList) {
            samplePriceSum += phone.getPrice();
        }
        double standardPrice = samplePriceSum / sampleNum;
        double lowerBound = standardPrice * (1.0 - accuracy.getValue());
        double upperBound = standardPrice * (1.0 + accuracy.getValue());

        criterion.put("limit", null);
        criterion.put("lowerBound", lowerBound);
        criterion.put("upperBound", upperBound);

        compareInfo.setResultSum(phoneDao.countPhoneByCriterion(criterion));
        compareInfo.setMaxPrice(phoneDao.maxPrice(criterion));
        compareInfo.setMinPrice(phoneDao.minPrice(criterion));
        compareInfo.setAveragePrice(phoneDao.averagePrice(criterion));
        return compareInfo;
    }

}
