package com.xyy.cache.controller;

import com.xyy.cache.bean.mysqljpa.Role;
import com.xyy.cache.bean.mysqljpa.User;
import com.xyy.cache.repository.RoleRepositoty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RoleController {

    @Autowired
    private RoleRepositoty roleRepositoty;

    @GetMapping("/findAllRole")
    public List<Role> findAll(){
        return roleRepositoty.findAll();
    }

    @ResponseBody
    @RequestMapping("/gt/{uid}")
    public List<Role> findByUidGreaterThan(@PathVariable Integer uid){
        System.out.println(uid);
        return roleRepositoty.findByUidGreaterThan(uid);
    }


    @ResponseBody
    @RequestMapping("/findRoleByUser/{uid}")
    public List<Role> findRoleByUser(@PathVariable Integer uid){
        System.out.println(uid);
        User user=new User();
        user.setUid(uid);
        return roleRepositoty.findByUser(user);
    }
}
