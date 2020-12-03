package com.hesen.crawler.service.Impl;

import com.hesen.crawler.dao.BrandDao;
import com.hesen.crawler.entity.Brand;
import com.hesen.crawler.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {
    @Autowired
    private BrandDao brandDao;

    @Override
    public List<Brand> getBrandList() {
        return brandDao.selectAllBrand();
    }
}
