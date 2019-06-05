package com.juebingliu.boot4enhance.domain.model;

import javax.xml.bind.annotation.XmlElement;

/**
 * @author juebing
 * @version v1.0
 * @date 2019/1/8 14:19
 * @description
 */
public class BaseXmlHeader {
    private String orderNo;
    private String orderTime;

    @XmlElement(name = "orderNo")
    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    @XmlElement(name = "orderTime")
    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }
}