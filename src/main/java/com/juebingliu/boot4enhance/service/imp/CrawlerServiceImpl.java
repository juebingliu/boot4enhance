package com.juebingliu.boot4enhance.service.imp;

import com.juebingliu.boot4enhance.domain.one.CrawlerContent;
import com.juebingliu.boot4enhance.domain.one.CrawlerContentExample;
import com.juebingliu.boot4enhance.mapper.one.CrawlerContentMapper;
import com.juebingliu.boot4enhance.service.CrawlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Override
    public Map<String,String> compareContent() {
        LocalDate today = LocalDate.now();
        LocalDate yesterday = today.minusDays(1);

        CrawlerContentExample example = new CrawlerContentExample();
        example.createCriteria().andCrawlerDateEqualTo(today.toString());
        example.setOrderByClause("publish_time desc");
        List<CrawlerContent> todayContent = crawlerContentMapper.selectByExample(example);

        example.clear();
        example.createCriteria().andCrawlerDateEqualTo(yesterday.toString());
        example.setOrderByClause("publish_time desc");
        List<CrawlerContent> yesterdayContent = crawlerContentMapper.selectByExample(example);

        Map<String,String> map = new HashMap<>();
        for (CrawlerContent c : yesterdayContent) {
            map.put(c.getPublishContent(),c.getPublishTime());
        }
        Map<String,String> add = new HashMap<>();
        for (CrawlerContent c : todayContent) {
            if(!map.containsKey(c.getPublishContent())) {
                System.out.println(c.getPublishTime() + " : " + c.getPublishContent());
                add.put(c.getPublishTime(),c.getPublishContent());
            }else {
                continue;
            }
        }
        return add;
    }
}