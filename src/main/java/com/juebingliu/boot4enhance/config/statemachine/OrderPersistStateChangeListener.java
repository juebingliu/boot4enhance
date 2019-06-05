package com.juebingliu.boot4enhance.config.statemachine;

import com.juebingliu.boot4enhance.common.enums.OrderStatus;
import com.juebingliu.boot4enhance.common.enums.OrderStatusChangeEvent;
import com.juebingliu.boot4enhance.domain.two.Order;
import com.juebingliu.boot4enhance.domain.two.OrderExample;
import com.juebingliu.boot4enhance.mapper.two.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.transition.Transition;

/**
 * @author juebing
 * @date 2018/12/12 16:09
 * @description
 */
public class OrderPersistStateChangeListener implements PersistStateMachineHandler.PersistStateChangeListener {


    @Autowired
    private OrderMapper orderMapper;


    @Override
    public void onPersist(State<OrderStatus, OrderStatusChangeEvent> state, Message<OrderStatusChangeEvent> message,
                          Transition<OrderStatus, OrderStatusChangeEvent> transition, StateMachine<OrderStatus, OrderStatusChangeEvent> stateMachine) {
        if (message != null && message.getHeaders().containsKey("order")) {
            Integer order = message.getHeaders().get("order", Integer.class);
            OrderExample e = new OrderExample();
            e.createCriteria().andOrderIdEqualTo(order.toString());
            Order o = new Order();
            OrderStatus status = state.getId();
            o.setOrderStatus(status);
            orderMapper.updateByExampleSelective(o,e);

        }
    }
}