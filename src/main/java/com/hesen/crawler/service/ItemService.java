package com.hesen.crawler.service;


import com.hesen.crawler.entity.Item;

import java.util.List;

public interface ItemService {
    int countItemBySku(String sku);

    int addItem(Item item);

    List<Item> getItemByBrandId(int brandId);
}
