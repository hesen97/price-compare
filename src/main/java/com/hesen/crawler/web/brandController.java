package com.hesen.crawler.web;

import com.hesen.crawler.entity.Brand;
import com.hesen.crawler.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class brandController {
    @Autowired
    private BrandService brandService;

    @RequestMapping(value = "/brands", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getAllBrands() {
        Map<String, Object> retMap = new HashMap<>();
        try {
            List<Brand> brandList = brandService.getBrandList();
            retMap.put("brandList", brandList);
            retMap.put("success", true);
        } catch (Exception e) {
            retMap.put("success", false);
            retMap.put("errorMsg", "获取品牌列表失败");
        }
        return retMap;
    }
}
