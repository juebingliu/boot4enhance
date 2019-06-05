package com.juebingliu.boot4enhance.controller;

import com.juebingliu.boot4enhance.common.enums.OrderStatusChangeEvent;
import com.juebingliu.boot4enhance.config.statemachine.OrderStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author juebing
 * @date 2018/12/12 16:39
 * @description
 */
@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderStateService orderStateService;

    /**
     * 列出所有的订单列表
     *
     * @return
     */
    @RequestMapping(method = {RequestMethod.GET})
    public ResponseEntity orders() {
        String orders = orderStateService.listDbEntries();
        return new ResponseEntity(orders, HttpStatus.OK);

    }


    /**
     * 通过触发一个事件，改变一个订单的状态
     * @param orderId
     * @param event
     * @return
     */
    @RequestMapping(value = "/{orderId}", method = {RequestMethod.POST})
    public ResponseEntity processOrderState(@PathVariable("orderId") Integer orderId, @RequestParam("event") OrderStatusChangeEvent event) {
        Boolean result = orderStateService.change(orderId, event);
        return new ResponseEntity(result, HttpStatus.OK);
    }
}