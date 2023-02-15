package com.hwj.controller;

import com.hwj.dao.OrderOutDao;
import com.hwj.pojo.OrderOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orderOut")
public class OrderOutController {
    @Autowired
    OrderOutDao orderOutDao;



    @GetMapping("/list")
    public List<OrderOut> listOrderOut(){
        return orderOutDao.listOrderOut();
    }

    @PostMapping("/find")
    public List<OrderOut> findOrderOut(@RequestBody String num){
        if(num==null||num.equals("")){
            return orderOutDao.listOrderOut();
        }else {
            return orderOutDao.findOrderOutByNum(num);
        }
    }
    @PostMapping("/insert")
    public boolean insert(@RequestBody OrderOut orderOut){
        List<OrderOut> orderIns = orderOutDao.listOrderOut();
        for(OrderOut o:orderIns){
            if(o.getNum().equals(orderOut.getNum())){
                return false;
            }
        }
        orderOutDao.insertOrderOut(orderOut);
        return true;
    }
    @PostMapping("/delete")
    public boolean delete(@RequestBody String num){
        List<OrderOut> orderOuts = orderOutDao.listOrderOut();
        for(OrderOut orderOut:orderOuts){
            if(orderOut.getNum().equals(num)){
                orderOutDao.deleteOrderOut(num);
                return true;
            }
        }
        return false;
    }
    @PostMapping("/update")
    public boolean update(@RequestBody OrderOut orderOut){
        List<OrderOut> orderOuts = orderOutDao.listOrderOut();
        for(OrderOut o:orderOuts){
            if(o.getNum().equals(orderOut.getNum())){
                orderOutDao.updateOrderOut(orderOut);
                return true;
            }
        }
        return false;
    }
}
