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

        Map<String, Object> criterionTM = new HashMap<>();
        criterionTM.put("websiteId", Website.TM.getWebsiteId());
        criterionTM.put("searchStr", searchStr);

        CompareInfo compareInfoTM = new CompareInfo();
        compareInfoTM.setWebsiteName(Website.TM.getWebsiteName());

        compareInfoTM.setMaxPrice(phoneDao.maxPrice(criterionTM));
        compareInfoTM.setMinPrice(phoneDao.minPrice(criterionTM));
        compareInfoTM.setAveragePrice(phoneDao.averagePrice(criterionTM));

        Map<String, Object> criterionJD = new HashMap<>();
        criterionTM.put("websiteId", Website.JD.getWebsiteId());
        criterionTM.put("searchStr", searchStr);

        CompareInfo compareInfoJD = new CompareInfo();
        compareInfoJD.setWebsiteName(Website.JD.getWebsiteName());

        compareInfoJD.setMaxPrice(phoneDao.maxPrice(criterionJD));
        compareInfoJD.setMinPrice(phoneDao.minPrice(criterionJD));
        compareInfoJD.setAveragePrice(phoneDao.averagePrice(criterionJD));

        compareInfoList.add(compareInfoTM);
        compareInfoList.add(compareInfoJD);
        return compareInfoList;
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
