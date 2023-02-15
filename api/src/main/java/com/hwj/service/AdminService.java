package com.hwj.service;

import com.hwj.dao.AdminDao;
import com.hwj.pojo.Admin;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AdminService {
    @Resource
    AdminDao adminDao;

    public Admin login(Admin admin){
        return adminDao.login(admin);
    }

}
