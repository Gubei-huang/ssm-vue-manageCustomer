package com.hwj.controller;

import com.hwj.pojo.Order;
import com.hwj.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderService orderService;

    @GetMapping("/selectAll")
    public List<Order> selectAll(){
        return orderService.selectAll();
    }

    @PostMapping("findOrderByNum")
    public List<Order> findOrderByNum(@RequestBody String num){
        if(num==null||num.equals("")){
            return orderService.selectAll();
        }else {
            return orderService.findOrderByNum(num);
        }
    }
    @PostMapping("findOrderByCustomer")
    public List<Order> findOrderByCustomer(@RequestBody String customer){
        if(customer==null||customer.equals("")){
            return orderService.selectAll();
        }else {
            return orderService.findOrderByCustomer(customer);
        }
    }
    @PostMapping("findOrderByAdmin")
    public List<Order> findOrderByAdmin(@RequestBody String admin){
        if(admin==null||admin.equals("")){
            return orderService.selectAll();
        }else {
            return orderService.findOrderByAdmin(admin);
        }
    }
    @PostMapping("/delete")
    public boolean delete(@RequestBody String num){
        List<Order> orders = orderService.selectAll();
        for(Order order:orders){
            if(order.getNum().equals(num)){
                orderService.delete(num);
                return true;
            }
        }
        return false;
    }
    @PostMapping("/update")
    public boolean update(@RequestBody Order order){
        List<Order> orders = orderService.selectAll();
        for(Order o:orders){
            if(order.getNum().equals(order.getNum())){
                orderService.update(order);
                return true;
            }
        }
        return false;
    }
    @PostMapping("/renew")
    public boolean renew(@RequestBody Map<String,Object> data){
        String num = (String)data.get("num");
        String admin = (String)data.get("admin");
        Order order = orderService.selectOne(num);
        order.setAdminUsername(admin);
        Date expireDate = order.getExpireDate();
        Calendar cal = Calendar.getInstance();
        cal.setTime(expireDate);
        if(order.getType().equals("year")){
            cal.add(Calendar.YEAR,1);
            order.setCost(order.getCost()+500);
        }else{
            cal.add(Calendar.MONTH,1);
            order.setCost(order.getCost()+50);
        }
        order.setExpireDate(cal.getTime());
        orderService.renew(order);
        return true;
    }
    @PostMapping("/getTotal")
    public float[] getTotal(){
        float[] total = {0, 0, 0, 0};
        List<Order> orders = orderService.selectAll();
        for(Order order:orders){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date d1 = null;
            Date d2 = null;
            Date d3 = null;
            Date d4 = null;
            Date d5 = null;
            try {
                d1  = sdf.parse("2019-01-01");
                d2  = sdf.parse("2020-01-01");
                d3  = sdf.parse("2021-01-01");
                d4  = sdf.parse("2022-01-01");
                d5  = sdf.parse("2023-01-01");
            }catch (Exception e){
                e.printStackTrace();
            }
            if(order.getPaymentDate().after(d1)&&order.getPaymentDate().before(d2)){
                total[0]=total[0]+order.getCost();
            }else if(order.getPaymentDate().after(d2)&&order.getPaymentDate().before(d3)){
                total[1]=total[1]+order.getCost();
            }else if(order.getPaymentDate().after(d3)&&order.getPaymentDate().before(d4)){
                total[2]=total[2]+order.getCost();
            }else if(order.getPaymentDate().after(d4)&&order.getPaymentDate().before(d5)){
                total[3]=total[3]+order.getCost();
            }
        }
        return total;
    }
}
