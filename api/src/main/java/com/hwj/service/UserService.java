package com.hwj.service;

import com.hwj.dao.UserDao;
import com.hwj.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;
    public List<User> selectAll(){
        return userDao.selectAll();
    }
    public List<User> findUser(String username){
        return userDao.findUser(username);
    }
    public User login(User user){
        return userDao.login(user);
    }
    public void insertUser(User user){
        userDao.insertUser(user);
    }
    public void updateUser(User user){
        userDao.updateUser(user);
    }
    public void banUser(User user){
        userDao.banUser(user);
    }

}
