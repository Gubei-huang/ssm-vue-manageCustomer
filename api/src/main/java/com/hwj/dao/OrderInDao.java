package com.hwj.dao;

import com.hwj.pojo.OrderIn;

import java.util.List;

public interface OrderInDao {
    public List<OrderIn> listOrderIn();
    public List<OrderIn> findOrderInByNum(String num);
    public void insertOrderIn(OrderIn orderIn);
    public OrderIn selectOrderInOne(String num);
    public void deleteOrderIn(String num);
    public void updateOrderIn(OrderIn orderIn);
}
