package com.hwj.dao;

import com.hwj.pojo.Order;

import java.util.List;

public interface OrderDao {
    public List<Order> selectAll();
    public Order selectOne(String num);
    public List<Order> findOrderByNum(String num);
    public List<Order> findOrderByCustomer(String paymentUsername);
    public List<Order> findOrderByAdmin(String adminUsername);
    public void delete(String num);
    public void update(Order order);
    public void renew(Order order);
}
