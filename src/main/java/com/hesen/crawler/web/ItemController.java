package com.hesen.crawler.web;

import com.hesen.crawler.entity.Item;
import com.hesen.crawler.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ItemController {

    @Autowired
    private ItemService itemService;

    @ResponseBody
    @RequestMapping(value = "/items", method = RequestMethod.GET)
    public Map<String, Object> getItemByBrand(int brandId) {
        Map<String, Object> retMap = new HashMap<>();
        try{
            List<Item> itemList = itemService.getItemByBrandId(brandId);
            retMap.put("itemList", itemList);
            retMap.put("success", true);
        } catch (Exception e) {
            retMap.put("success", false);
            retMap.put("errorMsg", "获取手机信息失败");
            e.printStackTrace();
        }
        return retMap;
    }
}
