package com.juebingliu.boot4enhance.domain.model;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * @author juebing
 * @version v1.0
 * @date 2019/1/25 11:28
 * @description
 */
public class JsonDataCollection {
    private List<JSONObject> data;

    public List<JSONObject> getData() {
        return data;
    }

    public void setData(List<JSONObject> data) {
        this.data = data;
    }
}