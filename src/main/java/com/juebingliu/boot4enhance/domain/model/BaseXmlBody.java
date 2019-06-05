package com.juebingliu.boot4enhance.domain.model;

import javax.xml.bind.annotation.XmlElement;

/**
 * @author juebing
 * @version v1.0
 * @date 2019/1/8 14:26
 * @description
 */
public class BaseXmlBody {
    private String amount;
    private String prodName;

    @XmlElement(name = "amount")
    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    @XmlElement(name = "prodName")
    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }
}