package com.hwj.controller;

import com.hwj.pojo.Customer;
import com.hwj.service.CustomerService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Resource
    CustomerService customerService;

    @GetMapping("/selectAll")
    public List<Customer> selectAll(){
        return customerService.selectAll();
    }

    @PostMapping("/findCustomer")
    public List<Customer> selectType(@RequestBody Map<String,Object> data){
        String username = (String)data.get("username");
        String type = (String)data.get("type");

        Customer customer = new Customer();
        customer.setUsername(username);
        customer.setType(type);
        return customerService.findCustomer(customer);
    }

    @PostMapping("/delete")
    public boolean delete(@RequestBody String username){
        List<Customer> customers = customerService.selectAll();
        for(Customer cust :customers){
            if(cust.getUsername().equals(username)){
                customerService.delete(username);
                return true;
            }
        }
        return false;
    }

    @PostMapping("/insert")
    public boolean insert(@RequestBody Customer customer){
        List<Customer> customers = customerService.selectAll();
        for(Customer cust :customers){
            if(cust.getUsername().equals(customer.getUsername())){
                return false;
            }
        }
        customerService.insert(customer);
        return true;
    }

    @PostMapping("/update")
    public boolean update(@RequestBody Customer customer){
        List<Customer> customers = customerService.selectAll();
        for(Customer cust :customers){
            if(cust.getUsername().equals(customer.getUsername())){
                customerService.update(customer);
                return true;
            }
        }
        return false;
    }


}
