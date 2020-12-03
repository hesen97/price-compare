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

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ItemTaskTM {

    private HttpUtils httpUtils = HttpUtils.getInstance();

    @Autowired
    private PhoneService phoneService;

    private static final int source = Website.TM.getWebsiteId();

    public void itemTask() throws Exception {
        String[] urlArray = new String[] {
                //小米
                "https://list.tmall.com/search_product.htm?q=%D0%A1%C3%D7%CA%D6%BB%FA&type=p&spm=a220m.1000858.a2227oh.d100&from=.list.pc_1_searchbutton",
//                "https://list.tmall.com/search_product.htm?s=60&q=%D0%A1%C3%D7%CA%D6%BB%FA&sort=s&style=g&from=.list.pc_1_searchbutton&spm=a220m.1000858.a2227oh.d100&type=pc#J_Filter",
                //荣耀
                "https://list.tmall.com/search_product.htm?q=%C8%D9%D2%AB%CA%D6%BB%FA&type=p&spm=a220m.1000858.a2227oh.d100&from=.list.pc_1_searchbutton",
//                "https://list.tmall.com/search_product.htm?spm=a220m.1000858.1000724.9.77374d7eYg7Cjx&s=60&q=%C8%D9%D2%AB%CA%D6%BB%FA&sort=s&style=g&from=.list.pc_1_searchbutton&type=pc#J_Filter",
                //苹果
                "https://list.tmall.com/search_product.htm?q=%C6%BB%B9%FB%CA%D6%BB%FA&type=p&spm=a220m.1000858.a2227oh.d100&from=.list.pc_1_searchbutton",
//                "https://list.tmall.com/search_product.htm?spm=a220m.1000858.1000724.9.46313e44HmVuzQ&s=60&q=apple%CA%D6%BB%FA&sort=s&style=g&from=.list.pc_1_searchbutton&type=pc#J_Filter",
                //魅族
                "https://list.tmall.com/search_product.htm?q=%F7%C8%D7%E5%CA%D6%BB%FA&type=p&spm=a220m.1000858.a2227oh.d100&from=.list.pc_1_searchbutton",
//                "https://list.tmall.com/search_product.htm?spm=a220m.1000858.1000724.9.7638153bWjHptl&brand=25143&s=60&q=%F7%C8%D7%E5%CA%D6%BB%FA&sort=s&style=g&from=.list.pc_1_searchbutton&type=pc#J_Filter",
                //华为
                "https://list.tmall.com/search_product.htm?q=%BB%AA%CE%AA%CA%D6%BB%FA&type=p&spm=a220m.1000858.a2227oh.d100&from=.list.pc_1_searchbutton",
//                "https://list.tmall.com/search_product.htm?spm=a220m.1000858.1000724.9.1b4210b2bC54fi&brand=11813&s=60&q=%BB%AA%CE%AA%CA%D6%BB%FA&sort=s&style=g&from=.list.pc_1_searchbutton&type=pc#J_Filter",
                //三星
                "https://list.tmall.com/search_product.htm?q=%C8%FD%D0%C7%CA%D6%BB%FA&type=p&spm=a220m.1000858.a2227oh.d100&from=.list.pc_1_searchbutton",
//                "https://list.tmall.com/search_product.htm?spm=a220m.1000858.1000724.9.82562364KFfT7W&brand=81156&s=60&q=%C8%FD%D0%C7%CA%D6%BB%FA&sort=s&style=g&from=.list.pc_1_searchbutton&type=pc#J_Filter",
                //vivo
                "https://list.tmall.com/search_product.htm?q=vivo%CA%D6%BB%FA&type=p&spm=a220m.1000858.a2227oh.d100&from=.list.pc_1_searchbutton",
//                "https://list.tmall.com/search_product.htm?spm=a220m.1000858.1000724.9.38f97f0bSo9bKO&brand=91621&s=60&q=vivo%CA%D6%BB%FA&sort=s&style=g&from=.list.pc_1_searchbutton&type=pc#J_Filter",
                //oppo
                "https://list.tmall.com/search_product.htm?q=oppo%CA%D6%BB%FA&type=p&spm=a220m.1000858.a2227oh.d100&from=.list.pc_1_searchbutton",
//                "https://list.tmall.com/search_product.htm?spm=a220m.1000858.1000724.9.1bc14cf66FUZiR&brand=28247&s=60&q=oppo%CA%D6%BB%FA&sort=s&style=g&from=.list.pc_1_searchbutton&type=pc#J_Filter"
        };

        for (int i = 0; i < urlArray.length; i++) {
            String html = httpUtils.doGetHtml(urlArray[i]);
            parse(html);
        }
    }

    //     解析页面, 获取手机数据并存储
    private void parse(String html) throws Exception {
        Document doc = Jsoup.parse(html);
        Elements itemList = doc.select("#J_ItemList > div.product ");

        for (Element item : itemList) {
            Phone phone = new Phone();
            phone.setWebsiteId(source);

            String sku = item.attr("data-id");
            phone.setSku(sku);

            String imageUrl = item.select("div.productImg-wrap > a img").attr("src");
            if ("".equals(imageUrl)) {
                imageUrl = item.select("div.productImg-wrap > a img").attr("data-ks-lazyload");
            }
            phone.setImageUrl("https:" + imageUrl);

            String detailUrl = item.select("p.productTitle > a").attr("href");
            phone.setDetailUrl("https:" + detailUrl);

            String title = item.select("p.productTitle > a").attr("title");
            //去除掉"【...】"里面的内容
            if (title.contains("】")) {
                int start = title.indexOf("】") + 1;
                if (start < title.length()) {
                    title = title.substring(start);
                }
            }
            phone.setTitle(title);

            double price = Double.parseDouble(item.select("p.productPrice > em")
                    .attr("title"));
            phone.setPrice(price);

            phoneService.addPhone(phone);
        }
    }

//    private int getScore(String reviewUrl) {
//        String html = httpUtils.doGetHtml(reviewUrl);
//        Document doc = Jsoup.parse(html);
//        System.out.println(doc.html());
//        String scoreStr = doc.select("#J_Reviews div.rate-score").html();
////        double score = Double.parseDouble(
////                doc.select("#J_Reviews div.rate-score > strong").text());
////        return (int) (score * 20);
//        return 0;
//    }
}
