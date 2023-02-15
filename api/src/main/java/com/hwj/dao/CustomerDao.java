package com.hwj.dao;

import com.hwj.pojo.Customer;

import java.util.List;

public interface CustomerDao {
    public List<Customer> selectAll();
    public List<Customer> findCustomer(Customer customer);
    public void delete(String username);
    public void insert(Customer customer);
    public void update(Customer customer);
}
