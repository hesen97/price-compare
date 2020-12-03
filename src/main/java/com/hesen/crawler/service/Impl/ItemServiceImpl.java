package com.hesen.crawler.service.Impl;

import com.hesen.crawler.dao.ItemDao;
import com.hesen.crawler.entity.Item;
import com.hesen.crawler.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemDao itemDao;

    @Override
    public int countItemBySku(String sku) {
        Item criterion = new Item();
        criterion.setSku(sku);
        return itemDao.countItemByCriterion(criterion);
    }

    @Override
    public int addItem(Item item) {
        return itemDao.insertItem(item);
    }

    @Override
    public List<Item> getItemByBrandId(int brandId) {
        Item criterion = new Item();
        criterion.setBrandId(brandId);
        return itemDao.selectItemByCriterion(criterion);
    }
}
