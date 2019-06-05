package com.juebingliu.boot4enhance.service;

import com.juebingliu.boot4enhance.domain.one.Bill;
import com.juebingliu.boot4enhance.domain.three.Product;
import com.juebingliu.boot4enhance.domain.two.Accinfo;

/**
 * @author juebing
 * @date 2018/12/11 18:17
 * @description
 */
public interface TestService {

    public Bill getBill();

    public Accinfo getAccinfo();

    public Product getProductInfo();
}