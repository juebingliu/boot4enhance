package com.juebingliu.boot4enhance.service;

import com.juebingliu.boot4enhance.domain.one.CrawlerContent;

import java.util.List;
import java.util.Map;

/**
 * @author juebing
 * @version v1.0
 * @date 2019/6/5 16:26
 * @description
 */
public interface CrawlerService {
    public void insertBatch(List<CrawlerContent> list);

    Map<String,String> compareContent();
}