package com.hesen.crawler.service;

import com.hesen.crawler.dto.CompareInfo;
import com.hesen.crawler.entity.Phone;
import com.hesen.crawler.enums.Accuracy;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PhoneService {

    int addPhone(Phone phone);

    List<Phone> searchPhone(int websiteId, String searchStr);

    List<Phone> searchPhoneWithAccuracy(int websiteId, String searchStr, Accuracy accuracy);

    List<CompareInfo> getCompareInfo(String searchStr);

    List<CompareInfo> getCompareInfoWithAccuracy(String search, Accuracy accuracy);
}
