package com.juebingliu.boot4enhance;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

/**
 * @author juebing
 * @date 2018/12/3 17:15
 * @description
 */


@RunWith(SpringRunner.class)
@SpringBootTest
//由于是Web项目，Junit需要模拟ServletContext，因此我们需要给我们的测试类加上@WebAppConfiguration。
@WebAppConfiguration
public class ApplicationTests {

    private Logger logger = LoggerFactory.getLogger(ApplicationTests.class);

    private MockMvc mvc;

    @Before
    public void setUp() throws Exception {
        logger.info("【开始测试】-----------------");

    }

    @After
    public void after() {
        logger.info("【测试结束】-----------------");
    }

}
