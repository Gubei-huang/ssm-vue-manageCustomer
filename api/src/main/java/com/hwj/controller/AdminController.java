package com.hwj.controller;

import com.hwj.pojo.Admin;
import com.hwj.service.AdminService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Resource
    AdminService adminService;

    @PostMapping("/login")
    public String login(@RequestBody Admin admin){
        Admin login = adminService.login(admin);
        if(login!=null){
            return login.getUsername();
        }else {
            return null;
        }

    }
}
