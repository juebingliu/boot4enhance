package com.juebingliu.boot4enhance.controller;

import com.juebingliu.boot4enhance.domain.one.CrawlerContent;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.UUID;

/**
 * @author juebing
 * @version v1.0
 * @date 2019/6/5 13:25
 * @description 爬虫解析类
 */
public class CrawlerProcess {

    public static ArrayList<CrawlerContent> processCrawl(Document doc) {
        ArrayList<CrawlerContent> list = new ArrayList<>();

        //提取数据
        //定位到新闻版块
        Element newsTable = doc.select("table").get(5)
                .select("tr").get(3)
                .select("table").get(21);

        //获取新闻板块内容
        Elements content = newsTable.select("tr");
        String nowDate = LocalDate.now().toString();
        String nowTime = LocalTime.now().toString();
        //移除标题
        content.remove(0);
        for (int i=0; i<content.size(); i++) {
            CrawlerContent content1 = new CrawlerContent();
            content1.setId(UUID.randomUUID().toString().replace("-",""));
            content1.setPublishTime(content.get(i).select("td").get(1).text().trim());
            content1.setPublishContent(content.get(i).select("td").get(0).text().replace(" ● ","").trim());
            content1.setCrawlerDate(nowDate);
            content1.setCrawlerTime(nowTime);

            list.add(content1);
        }

        return list;
    }

    public static void main(String[] args) {
        System.out.println(LocalDate.now().toString() + LocalTime.now().toString());
    }
}