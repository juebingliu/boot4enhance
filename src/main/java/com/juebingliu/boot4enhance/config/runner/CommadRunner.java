package com.juebingliu.boot4enhance.config.runner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author juebing
 * @version v1.0
 * @date 2019/1/23 15:40
 * @description
 */
@Component
@Order(1)
public class CommadRunner implements CommandLineRunner {
    private Logger logger = LoggerFactory.getLogger(CommadRunner.class);
    @Override
    public void run(String... args) throws Exception {
        //logger.info("【这是runner1】");
    }
}