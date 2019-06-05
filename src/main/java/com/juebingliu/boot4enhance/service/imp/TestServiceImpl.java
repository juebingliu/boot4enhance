package com.juebingliu.boot4enhance.service.imp;

import com.juebingliu.boot4enhance.domain.one.Bill;
import com.juebingliu.boot4enhance.domain.three.Product;
import com.juebingliu.boot4enhance.domain.two.Accinfo;
import com.juebingliu.boot4enhance.mapper.one.BillMapper;
import com.juebingliu.boot4enhance.mapper.three.ProductMapper;
import com.juebingliu.boot4enhance.mapper.two.AccinfoMapper;
import com.juebingliu.boot4enhance.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author juebing
 * @date 2018/12/11 18:18
 * @description
 */
@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private BillMapper billMapper;

    @Autowired
    private AccinfoMapper accinfoMapper;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public Bill getBill() {
        return billMapper.selectByPrimaryKey("123");
    }

    @Override
    public Accinfo getAccinfo() {
        return accinfoMapper.selectByPrimaryKey("1");
    }

    @Override
    public Product getProductInfo() {
        return productMapper.selectByPrimaryKey("1");
    }
}