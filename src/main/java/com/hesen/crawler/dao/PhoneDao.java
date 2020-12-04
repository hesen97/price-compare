package com.hesen.crawler.dao;

import com.hesen.crawler.entity.Phone;

import java.util.List;
import java.util.Map;

public interface PhoneDao {

    int insertPhone(Phone phone);

    List<Phone> selectPhoneByCriterion(Map<String, Object> criterion);

    int countPhoneByCriterion(Map<String, Object> criterion);

    double averagePrice(Map<String, Object> criterion);

    double maxPrice(Map<String, Object> criterion);

    double minPrice(Map<String, Object> criterion);
}
