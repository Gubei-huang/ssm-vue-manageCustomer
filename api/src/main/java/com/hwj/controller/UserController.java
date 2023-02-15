package com.hwj.controller;

import com.hwj.pojo.User;
import com.hwj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/selectAll")
    public List<User> selectAll(){
        return userService.selectAll();
    }

    @PostMapping("/findUser")
    public List<User> findUser(@RequestBody String username){
        if(username==null||username.equals("")){
            return userService.selectAll();
        }
        return userService.findUser(username);
    }

    @PostMapping("/login")
    public String login(@RequestBody User user){
        User login = userService.login(user);
        if(login!=null){
            if(login.getDisable()==1){
                return null;
            }
            return login.getUsername();
        }else {
            return null;
        }
    }

    @PostMapping("/insert")
    public boolean insert(@RequestBody User user){
        List<User> users = userService.selectAll();
        for(User u:users){
            if(u.getUsername().equals(user.getUsername())){
                return false;
            }
        }
        userService.insertUser(user);
        return true;
    }

    @PostMapping("/update")
    public boolean update(@RequestBody User user){
        userService.updateUser(user);
        return true;
    }
    @PostMapping("/ban")
    public boolean ban(@RequestBody User user){
        if(user.getDisable()==1) {
            user.setDisable(0);
        }else {
            user.setDisable(1);
        }
        userService.banUser(user);
        return true;
    }
}
