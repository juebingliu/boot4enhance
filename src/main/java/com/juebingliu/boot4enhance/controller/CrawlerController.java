package com.juebingliu.boot4enhance.controller;

import com.alibaba.fastjson.JSON;
import com.juebingliu.boot4enhance.domain.one.CrawlerContent;
import com.juebingliu.boot4enhance.service.CrawlerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author juebing
 * @version v1.0
 * @date 2019/6/5 13:25
 * @description
 */
@RestController
@Api(tags = "爬虫API")
public class CrawlerController {

    @Autowired
    private CrawlerService crawlerService;

    @GetMapping("/getContent")
    @ApiOperation(value="信息爬取")
    public String crawler() {
        String url = "http://www.gxhz.gov.cn";

        try {
            Document doc = Jsoup.connect(url).get();
            List<CrawlerContent> list = CrawlerProcess.processCrawl(doc);
            //持久化
            crawlerService.insertBatch(list);

            return "ok";
        } catch (IOException e) {
            e.printStackTrace();
            return "no";
        }
    }

    @GetMapping("/compare")
    @ApiOperation(value="信息比较")
    public String compare() {
        Map<String,String> map = crawlerService.compareContent();
        if(map != null && map.size() > 0) {
            return "以下是新增内容：" + JSON.toJSONString(map);
        }
        return "没有新增内容";
    }

}