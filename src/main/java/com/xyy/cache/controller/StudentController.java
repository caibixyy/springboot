package com.xyy.cache.controller;

import com.xyy.cache.bean.mysqljpa.Student;
import com.xyy.cache.repository.StudentRepositoty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudentController {

    @Autowired
    private StudentRepositoty studentRepositoty;

    @GetMapping("/findAllStudent")
    public List<Student> findAll(){
        return studentRepositoty.findAll();
    }
}
