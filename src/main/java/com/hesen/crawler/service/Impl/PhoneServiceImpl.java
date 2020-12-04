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

        compareInfo.setMaxPrice(phoneDao.maxPrice(criterion));
        compareInfo.setMinPrice(phoneDao.minPrice(criterion));
        compareInfo.setAveragePrice(phoneDao.averagePrice(criterion));
        compareInfo.setResultSum(phoneDao.countPhoneByCriterion(criterion));
        return compareInfo;
    }

    @Override
    public List<CompareInfo> getCompareInfoWithAccuracy(String searchStr, Accuracy accuracy) {
//        List<Phone> originalPhoneList = phoneDao.selectPhoneByCriterion()
//        int sampleSum = (int) (originalCompareInfoList.size() * 0.1);
//        double samplePriceSum = 0;
//        for (int i = 0; i < sampleSum; i++) {
//            samplePriceSum += originalCompareInfoList.get(i)
//        }
        return null;
    }

}
