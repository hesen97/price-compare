package com.hesen.crawler.service;

import com.hesen.crawler.BaseTest;
import com.hesen.crawler.entity.Brand;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class BrandServiceTest extends BaseTest {

    @Autowired
    private BrandService brandService;

    @Test
    public void testGetBrandList() {
        List<Brand> brandList = brandService.getBrandList();
        for (Brand brand : brandList) {
            System.out.println(brand);
        }
    }
}
