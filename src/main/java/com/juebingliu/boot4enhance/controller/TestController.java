package com.juebingliu.boot4enhance.controller;

import com.alibaba.fastjson.JSONObject;
import com.juebingliu.boot4enhance.common.utils.BaseUtil;
import com.juebingliu.boot4enhance.common.utils.JsonUtil;
import com.juebingliu.boot4enhance.domain.model.VertifyPasswdRequest;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.nio.charset.Charset;
import java.util.Map;

/**
 * @author juebing
 * @version v1.0
 * @date 2019/1/25 11:06
 * @description
 */
@RestController
@RequestMapping("/boot")
public class TestController {

    @Value("classpath:static/data/verifyPasswd.json")
    private Resource data;

    @Value("${signinfo.key}")
    private String key;

    @RequestMapping("/test")
    public String test() {
        return "test";
    }

    @RequestMapping("/vertifyTest")
    public ModelAndView vertifyPassWord() throws Exception {
        String reqData = IOUtils.toString(data.getInputStream(), Charset.forName("UTF-8"));
        reqData = reqData.replaceAll("\r\n","").trim();
        VertifyPasswdRequest request = JSONObject.parseObject(reqData,VertifyPasswdRequest.class);
        Map map = JsonUtil.objectToMap(request);
        map.put("signInfo", BaseUtil.generateSignInfoNew(map, key));
        return new ModelAndView(new RedirectView("http://10.100.137.12:8800/p2p_adapter/cgb/verifyPasswd.htm"), map);
    }
}