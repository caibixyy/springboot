package com.xyy.cache.controller;

import com.xyy.cache.bean.mysqljpa.Person;
import com.xyy.cache.repository.PersonRepositoty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PersonController {

    @Autowired
    private PersonRepositoty personRepositoty;

    @GetMapping("/findAllPerson")
    public List<Person> findAll(){
        return personRepositoty.findAll();
    }

    @DeleteMapping("/deleteOne/{id}")
    public void deleteOne(@PathVariable Integer id){
        personRepositoty.deleteById(id);
    }
}
