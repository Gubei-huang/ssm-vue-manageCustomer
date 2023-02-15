package com.hwj.dao;

import com.hwj.pojo.OrderIn;
import com.hwj.pojo.OrderOut;

import java.util.List;

public interface OrderOutDao {
    public List<OrderOut> listOrderOut();
    public List<OrderOut> findOrderOutByNum(String num);
    public void insertOrderOut(OrderOut orderOut);
    public OrderOut selectOrderOutOne(String num);
    public void deleteOrderOut(String num);
    public void updateOrderOut(OrderOut orderOut);
}
