package com.hwj.dao;

import com.hwj.pojo.User;

import java.util.List;

public interface UserDao {
    public List<User> selectAll();
    public List<User> findUser(String username);
    public User login(User user);
    public void insertUser(User user);
    public void updateUser(User user);
    public void banUser(User user);
}
