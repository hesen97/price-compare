package com.hesen.crawler.dao;

import com.hesen.crawler.entity.Item;

import java.util.List;

public interface ItemDao {
    List<Item> selectItemByCriterion(Item item);

    int countItemByCriterion(Item item);

    int insertItem(Item item);
}
