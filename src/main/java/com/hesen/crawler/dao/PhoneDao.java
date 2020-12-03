package com.hesen.crawler.dao;

import com.hesen.crawler.entity.Phone;

import java.util.List;

public interface PhoneDao {

    int insertPhone(Phone phone);

    List<Phone> selectPhoneByCriterion(Phone phone);

    double averagePrice(Phone criterion);

    double maxPrice(Phone criterion);

    double minPrice(Phone criterion);
}
