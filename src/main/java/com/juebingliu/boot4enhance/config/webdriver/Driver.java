package com.juebingliu.boot4enhance.config.webdriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author juebing
 * @version v1.0
 * @date 2019/1/25 10:48
 * @description
 */
@Service
public class Driver {

    private static volatile ChromeDriver instance = null;

    @Value("${driver.name}")
    private String driverName;

    @Value("${driver.path}")
    private String driverPath;

    public Driver() {
    }

    public ChromeDriver getInstance() {
        System.setProperty(driverName,driverPath);
        if(instance == null) {
        synchronized (Driver.class) {
                instance = new ChromeDriver();
                instance.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            }
        }
        return instance;
    }

}