package com.juebingliu.boot4enhance.config.listener;

import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPool;

/**
 * @author juebing
 * @version v1.0
 * @date 2019/1/18 19:33
 * @description 启动监听事件
 */
@Component
public class ApplicationPreparedEventListener implements ApplicationListener<ApplicationReadyEvent> {

    private Logger logger = LoggerFactory.getLogger(ApplicationPreparedEventListener.class);

    @Autowired
    private JedisPool pool;

    @Value("${server.port}")
    private int port;

    @Value("${api.url}")
    private String url;

    private static ChromeDriver browser;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        //logger.info("【ui自动化开始】");
        //pool.getResource().set("key1","abc");
//        System.setProperty("webdriver.chrome.driver","/webdriver/chromedriver.exe");
//        browser = new ChromeDriver();
//        browser.manage().timeouts()
//                .implicitlyWait(10, TimeUnit.SECONDS);
//        browser.get("http://10.100.140.62:8200/amplat-web/");
//        browser.findElementByName("userName").sendKeys("admin1");
//        browser.findElementByName("passWord").sendKeys("123456");
//        browser.findElementById("login").click();
//        browser.quit();
    }
}