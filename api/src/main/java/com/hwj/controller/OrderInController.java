package com.hwj.controller;

import com.hwj.dao.OrderInDao;
import com.hwj.pojo.OrderIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orderIn")
public class OrderInController {
    @Autowired
    OrderInDao orderInDao;



    @GetMapping("/list")
    public List<OrderIn> listOrderOut(){
        return orderInDao.listOrderIn();
    }

    @PostMapping("/find")
    public List<OrderIn> findOrderOut(@RequestBody String num){
        if(num==null||num.equals("")){
            return orderInDao.listOrderIn();
        }else {
            return orderInDao.findOrderInByNum(num);
        }
    }
    @PostMapping("/insert")
    public boolean insert(@RequestBody OrderIn orderIn){
        List<OrderIn> orderIns = orderInDao.listOrderIn();
        for(OrderIn o:orderIns){
            if(o.getNum().equals(orderIn.getNum())){
                return false;
            }
        }
        orderInDao.insertOrderIn(orderIn);
        return true;
    }
    @PostMapping("/delete")
    public boolean delete(@RequestBody String num){
        List<OrderIn> orderIns = orderInDao.listOrderIn();
        for(OrderIn orderIn:orderIns){
            if(orderIn.getNum().equals(num)){
                orderInDao.deleteOrderIn(num);
                return true;
            }
        }
        return false;
    }
    @PostMapping("/update")
    public boolean update(@RequestBody OrderIn orderIn){
        List<OrderIn> orderIns = orderInDao.listOrderIn();
        for(OrderIn o:orderIns){
            if(o.getNum().equals(orderIn.getNum())){
                orderInDao.updateOrderIn(orderIn);
                return true;
            }
        }
        return false;
    }
}
