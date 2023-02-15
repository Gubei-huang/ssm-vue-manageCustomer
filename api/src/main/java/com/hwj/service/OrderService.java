package com.hwj.service;

import com.hwj.dao.OrderDao;
import com.hwj.pojo.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrderService {
    @Autowired
    OrderDao orderDao;

    public List<Order> selectAll(){
        return orderDao.selectAll();
    }
    public Order selectOne(String num){
        return orderDao.selectOne(num);
    }
    public List<Order> findOrderByNum(String num){
        return orderDao.findOrderByNum(num);
    }
    public List<Order> findOrderByCustomer(String paymentUsername){
        return orderDao.findOrderByCustomer(paymentUsername);
    }
    public List<Order> findOrderByAdmin(String adminUsername){
        return orderDao.findOrderByAdmin(adminUsername);
    }
    public void delete(String num){
        orderDao.delete(num);
    }
    public void update(Order order){
        orderDao.update(order);
    }
    public void renew(Order order){
        orderDao.renew(order);
    }
}
