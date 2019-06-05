package com.juebingliu.boot4enhance;

import com.juebingliu.boot4enhance.config.datasource.DatabaseContextHolder;
import com.juebingliu.boot4enhance.config.datasource.DatabaseType;
import com.juebingliu.boot4enhance.config.webdriver.Driver;
import com.juebingliu.boot4enhance.service.TestService;
import com.juebingliu.boot4enhance.service.TestService2;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;

/**
 * @author juebing
 * @date 2018/12/11 18:12
 * @description
 */
public class BootTest extends ApplicationTests {

    private Logger logger = LoggerFactory.getLogger(BootTest.class);

    @Autowired
    private TestService testService;

    @Autowired
    private TestService2 testService2;

    @Value("classpath:static/url/url.json")
    private Resource data;

    @Autowired
    private Driver driver;

    @Test
    public void test() throws Exception {
        //动态数据源
        System.out.println(testService.getAccinfo().toString());
        DatabaseContextHolder.setDatabaseType(DatabaseType.test);
        logger.info("[显式切换数据源->test]");
        System.out.println(testService2.getProductinfo().toString());
        System.out.println(testService.getBill().toString());
        System.out.println(testService.getAccinfo().toString());

        //ui自动化
        /*ChromeDriver browser = driver.getInstance();
        String areaData = IOUtils.toString(data.getInputStream(), Charset.forName("UTF-8"));
        areaData = areaData.replaceAll("\r\n","").trim();
        JsonDataCollection col = JSONObject.parseObject(areaData,JsonDataCollection.class);
        for (JSONObject o : col.getData()) {
            browser.get(o.getString("url"));
        }*/
    }
}