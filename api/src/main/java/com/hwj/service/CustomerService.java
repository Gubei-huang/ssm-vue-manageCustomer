package com.hwj.service;

import com.hwj.dao.CustomerDao;
import com.hwj.pojo.Customer;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CustomerService {
    @Resource
    CustomerDao customerDao;

    public List<Customer> selectAll(){
        return customerDao.selectAll();
    }
    public List<Customer> findCustomer(Customer customer){
        return customerDao.findCustomer(customer);
    }
    public void insert(Customer customer){
        customerDao.insert(customer);
    }
    public void delete(String username){
        customerDao.delete(username);
    }
    public void update(Customer customer){
        customerDao.update(customer);
    }
}
