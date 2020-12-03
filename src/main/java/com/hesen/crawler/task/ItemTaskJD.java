package com.hesen.crawler.task;



import com.hesen.crawler.entity.Phone;
import com.hesen.crawler.enums.Website;
import com.hesen.crawler.service.PhoneService;
import com.hesen.crawler.util.HttpUtils;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.*;

@Component
public class ItemTaskJD {
    private HttpUtils httpUtils = HttpUtils.getInstance();

    @Autowired
    private PhoneService phoneService;

    private static final int source = Website.JD.getWebsiteId();

    private static final int pages = 4;

    public void itemTask() throws Exception {
//        // 声明需要解析的初始地址
//        String url = "https://search.jd.com/Search?keyword=%E6%89%8B%E6%9C%BA&enc=utf-8" +
//                "&pvid=f112521d94c04007826aa41adcbb0658";
//
//        String html = httpUtils.doGetHtml(url);
//        Document doc = Jsoup.parse(html);
//
//
//        List<Brand> brandList = brandService.getBrandList();
//        Map<String, Integer> brandNameIdMap = new HashMap<>();
//        for (Brand brand : brandList) {
//            brandNameIdMap.put(brand.getBrandName(), brand.getBrandId());
//        }
//
//        int visitedSum = 0;
//        Elements valueListElem = doc.select("#J_selector ul.J_valueList > li");
//        for (Element elem : valueListElem) {
//            if (visitedSum == brandList.size()) {
//                break;
//            }
//
//            String brandName = elem.select("a").text().trim();
//            if (brandNameIdMap.containsKey(brandName)) {
//                String brandUrl = "https://search.jd.com/" + elem.select("a").attr("href");
//                String brandHtml = httpUtils.doGetHtml(brandUrl);
//                parse(brandHtml, brandNameIdMap.get(brandName));
//                ++visitedSum;
//            }
//        }
//
//        System.out.println("done!");

        String[] urlArray = new String[] {
                //小米
                "https://search.jd.com/Search?keyword=%E5%B0%8F%E7%B1%B3%E6%89%8B%E6%9C%BA&enc=utf-8&wq=%E5%B0%8F%E7%B1%B3%E6%89%8B%E6%9C%BA&pvid=138d3504c30647a4aca4ac7ae9173ced",
                //荣耀
                "https://search.jd.com/Search?keyword=%E8%8D%A3%E8%80%80%E6%89%8B%E6%9C%BA&enc=utf-8&wq=%E8%8D%A3%E8%80%80%E6%89%8B%E6%9C%BA&pvid=4a565209cae146f3af0f6c345d3eed51",
                //苹果
                "https://search.jd.com/Search?keyword=apple%E6%89%8B%E6%9C%BA&enc=utf-8&wq=apple%E6%89%8B%E6%9C%BA&pvid=1434a3f17ff444f797cb31adb4b7f221",
                //魅族
                "https://search.jd.com/Search?keyword=%E9%AD%85%E6%97%8F%E6%89%8B%E6%9C%BA&enc=utf-8&wq=%E9%AD%85%E6%97%8F%E6%89%8B%E6%9C%BA&pvid=97976aeb80e74312baae0ad22d3af7b8",
                //华为
                "https://search.jd.com/Search?keyword=%E5%8D%8E%E4%B8%BA%E6%89%8B%E6%9C%BA&enc=utf-8&wq=%E5%8D%8E%E4%B8%BA%E6%89%8B%E6%9C%BA&pvid=7f1fe2e4793949ab915cdbbb03478e9d",
                //三星
                "https://search.jd.com/Search?keyword=%E4%B8%89%E6%98%9F%E6%89%8B%E6%9C%BA&enc=utf-8&suggest=1.rem.0.base&wq=%E4%B8%89%E6%98%9F%E6%89%8B%E6%9C%BA&pvid=752133e012fa4537851594d27f92cc59",
                //vivo
                "https://search.jd.com/Search?keyword=vivo%E6%89%8B%E6%9C%BA&enc=utf-8&wq=vivo%E6%89%8B%E6%9C%BA&pvid=cda8864cf95445be93e1596acf467673",
                //oppo
                "https://search.jd.com/Search?keyword=oppo%E6%89%8B%E6%9C%BA&enc=utf-8&wq=oppo%E6%89%8B%E6%9C%BA&pvid=c2ff9c73c52d4434bd9d04776cd38234"
        };

        for (int i = 0; i < urlArray.length; i++) {
            for (int j = 0; j < pages; j++) {
                String buildUrl = urlArray[i] + "?page=" + (1 + j * 2);
                String html = httpUtils.doGetHtml(buildUrl);
                parse(html);
            }
        }
    }




//     解析页面, 获取商品数据并存储
    private void parse(String html) throws Exception {
        Document doc = Jsoup.parse(html);
        Elements itemLis = doc.select("div#J_goodsList > ul > li");

        for (Element itemLi : itemLis) {
            if (StringUtils.isNotEmpty(itemLi.attr("data-sku"))) {
                Phone phone = new Phone();
                phone.setWebsiteId(source);

                String sku = itemLi.attr("data-sku");
                phone.setSku(sku);

                String itemTitle = itemLi.select("div.gl-i-wrap > div.p-name > a > em").text();
                phone.setTitle(itemTitle);

                String detailUrl = itemLi.select("div.p-img > a").attr("href");
                phone.setDetailUrl("https:" + detailUrl);

                String imageUrl = itemLi.select("div.p-img > a > img").attr("data-lazy-img");
                phone.setImageUrl("https:" + imageUrl);

                double price = Double.parseDouble(itemLi.select("div.p-price i").text());
                phone.setPrice(price);

                phoneService.addPhone(phone);
            }
        }
    }
}