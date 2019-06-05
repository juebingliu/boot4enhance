package com.juebingliu.boot4enhance.config.statemachine;

import com.juebingliu.boot4enhance.common.enums.OrderStatusChangeEvent;
import com.juebingliu.boot4enhance.domain.two.Order;
import com.juebingliu.boot4enhance.mapper.two.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.StringJoiner;

/**
 * @author juebing
 * @date 2018/12/12 16:32
 * @description
 */
@Component
public class OrderStateService {

    private PersistStateMachineHandler handler;


    public OrderStateService(PersistStateMachineHandler handler) {
        this.handler = handler;
    }

    @Autowired
    private OrderMapper orderMapper;


    public String listDbEntries() {
        List<Order> orders = orderMapper.findAll();
        StringJoiner sj = new StringJoiner(",");
        for (Order order : orders) {
            sj.add(order.toString());
        }
        return sj.toString();
    }


    public boolean change(int order, OrderStatusChangeEvent event) {
        Order o = orderMapper.getOrderByOrderId(order+"");
        return handler.handleEventWithState(MessageBuilder.withPayload(event).setHeader("order", order).build(), o);
    }

}