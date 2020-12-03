package com.hesen.crawler.service;

import com.hesen.crawler.BaseTest;
import com.hesen.crawler.dto.CompareInfo;
import com.hesen.crawler.entity.Phone;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PhoneServiceTest extends BaseTest {

    @Autowired
    private PhoneService phoneService;

    @Test
    public void testSearchPhone() {
        List<Phone> phoneList = phoneService.searchPhone(1, "apple");
        System.out.println("done");
    }

    @Test
    public void testGetCompareInfoList() {
        List<CompareInfo> compareInfoList = phoneService.getCompareInfo("苹果");
        for (CompareInfo info : compareInfoList) {
            System.out.println(info);
        }
    }

}
