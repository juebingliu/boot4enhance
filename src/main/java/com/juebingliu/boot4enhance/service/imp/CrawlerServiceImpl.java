package com.juebingliu.boot4enhance.service.imp;

import com.juebingliu.boot4enhance.domain.one.CrawlerContent;
import com.juebingliu.boot4enhance.mapper.one.CrawlerContentMapper;
import com.juebingliu.boot4enhance.service.CrawlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author juebing
 * @version v1.0
 * @date 2019/6/5 16:27
 * @description
 */
@Service
public class CrawlerServiceImpl implements CrawlerService {

    @Autowired
    private CrawlerContentMapper crawlerContentMapper;

    @Override
    public void insertBatch(List<CrawlerContent> list) {
        crawlerContentMapper.insertBatch(list);
    }
}