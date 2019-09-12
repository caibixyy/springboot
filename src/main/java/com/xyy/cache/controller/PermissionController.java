package com.xyy.cache.controller;


import com.xyy.cache.bean.mysqljpa.Permission;
import com.xyy.cache.repository.PermissionRepositity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.PublicKey;
import java.util.List;

@Controller
public class PermissionController {

    @Autowired
    private PermissionRepositity permissionRepositity;


    @ResponseBody
    @GetMapping("/select/pers")
    public List<Permission> getPers(){
        return permissionRepositity.findAll();
    }
}
