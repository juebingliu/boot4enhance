package com.juebingliu.boot4enhance.domain.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author juebing
 * @version v1.0
 * @date 2019/1/8 14:32
 * @description
 */
@XmlRootElement(name = "order")
@XmlType(propOrder = {"head", "body"})
public class OrderRequest {

    private BaseXmlHeader head;
    private BaseXmlBody body;

    @XmlElement(name = "head")
    public BaseXmlHeader getHead() {
        return head;
    }

    public void setHead(BaseXmlHeader head) {
        this.head = head;
    }

    @XmlElement(name = "body")
    public BaseXmlBody getBody() {
        return body;
    }

    public void setBody(BaseXmlBody body) {
        this.body = body;
    }
}