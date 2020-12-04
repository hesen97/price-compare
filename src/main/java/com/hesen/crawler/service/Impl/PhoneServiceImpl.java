package com.hesen.crawler.service.Impl;

import com.hesen.crawler.dao.PhoneDao;
import com.hesen.crawler.dto.CompareInfo;
import com.hesen.crawler.entity.Phone;
import com.hesen.crawler.enums.Accuracy;
import com.hesen.crawler.enums.Website;
import com.hesen.crawler.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
        Phone phone = new Phone();
        phone.setWebsiteId(websiteId);
        phone.setTitle(searchStr);
        return phoneDao.selectPhoneByCriterion(phone);
    }

    @Override
    public List<CompareInfo> getCompareInfo(String searchStr) {
        List<CompareInfo> compareInfoList = new ArrayList<>();

        Phone criterionTM = new Phone();
        criterionTM.setWebsiteId(Website.TM.getWebsiteId());
        criterionTM.setTitle(searchStr);

        CompareInfo compareInfoTM = new CompareInfo();
        compareInfoTM.setWebsiteName(Website.TM.getWebsiteName());

        compareInfoTM.setMaxPrice(phoneDao.maxPrice(criterionTM));
        compareInfoTM.setMinPrice(phoneDao.minPrice(criterionTM));
        compareInfoTM.setAveragePrice(phoneDao.averagePrice(criterionTM));

        Phone criterionJD = new Phone();
        criterionJD.setTitle(searchStr);
        criterionJD.setWebsiteId(Website.JD.getWebsiteId());
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
