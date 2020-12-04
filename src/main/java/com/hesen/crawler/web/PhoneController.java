package com.hesen.crawler.web;

import com.hesen.crawler.dto.CompareInfo;
import com.hesen.crawler.entity.Phone;
import com.hesen.crawler.enums.Accuracy;
import com.hesen.crawler.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class PhoneController {
    @Autowired
    private PhoneService phoneService;

    @RequestMapping(value = "/compareInfo", method = RequestMethod.POST)
    public ModelAndView getCompareInfo(String searchStr, int accuracyLevel) {
        ModelAndView mav = new ModelAndView();
        List<CompareInfo> compareInfoList = null;
        Accuracy accuracy = Accuracy.getInstanceByLevel(accuracyLevel);
        if (accuracy == Accuracy.UNDEFINE) {
            compareInfoList =  phoneService.getCompareInfo(searchStr);
        } else {
            compareInfoList = phoneService.getCompareInfoWithAccuracy(searchStr, accuracy);
        }

        mav.addObject("compareInfoList", compareInfoList);
        mav.addObject("searchStr", searchStr);
        mav.addObject("resultSize", compareInfoList.size());
        mav.setViewName("main");
        return mav;
    }

    @RequestMapping(value = "/phones", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getPhones(int websiteId, String searchStr) {
        Map<String, Object> modalMap = new HashMap<>();
        try {
            List<Phone> phoneList = phoneService.searchPhone(websiteId, searchStr);
            modalMap.put("phoneList", phoneList);
            modalMap.put("success", true);
        } catch (Exception e) {
            modalMap.put("errorMsg", "获取商品信息失败");
            modalMap.put("success", false);
            e.printStackTrace();
        }
        return modalMap;
    }

}
