package com.xyy.cache.controller;

import com.xyy.cache.bean.mysqljpa.OfClass;
import com.xyy.cache.repository.OfClassRepositoty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OfClassController {

    @Autowired
    private OfClassRepositoty ofClassRepositoty;

    @GetMapping("/findAllOfClass")
    public List<OfClass> findAll(){
        return ofClassRepositoty.findAll();
    }
}
