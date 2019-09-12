package com.xyy.cache.controller;

import com.xyy.cache.bean.mysqljpa.Address;
import com.xyy.cache.repository.AddressRepositoty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AddressController {

    @Autowired
    private AddressRepositoty addressRepositoty;


    @GetMapping("/findAllAddress")
    public List<Address> findAll(){
        return addressRepositoty.findAll();
    }
}
