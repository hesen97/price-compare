package com.hesen.crawler.service.Impl;

import com.hesen.crawler.dao.HistoryPriceDao;
import com.hesen.crawler.entity.HistoryPrice;
import com.hesen.crawler.service.HistoryPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HistoryPriceServiceImpl implements HistoryPriceService {
    @Autowired
    private HistoryPriceDao historyPriceDao;
    @Override
    public int addHistoryPrice(HistoryPrice historyPrice) {
        return historyPriceDao.insertHistoryPrice(historyPrice);
    }
}
