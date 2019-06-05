package com.juebingliu.boot4enhance.service;

import com.juebingliu.boot4enhance.domain.one.CrawlerContent;

import java.util.List;

/**
 * @author juebing
 * @version v1.0
 * @date 2019/6/5 16:26
 * @description
 */
public interface CrawlerService {
    public void insertBatch(List<CrawlerContent> list);
}