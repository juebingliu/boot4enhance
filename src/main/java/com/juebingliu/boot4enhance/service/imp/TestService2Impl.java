package com.juebingliu.boot4enhance.service.imp;

import com.juebingliu.boot4enhance.domain.three.Product;
import com.juebingliu.boot4enhance.mapper.three.ProductMapper;
import com.juebingliu.boot4enhance.service.TestService2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author juebing
 * @date 2018/12/11 19:33
 * @description
 */
@Service
public class TestService2Impl implements TestService2 {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public Product getProductinfo() {
        return productMapper.selectByPrimaryKey("1");
    }
}