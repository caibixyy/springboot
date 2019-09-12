package com.xyy.cache.controller;

import com.mongodb.client.result.UpdateResult;
import com.xyy.cache.bean.mongojpa.MongoUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 控制层
 * @author linhongcun
 *
 */
@RestController
@RequestMapping("/user")
public class MongoUserController {

    @Autowired
    MongoTemplate mongoTemplate;

    /**
     * 1、增
     * @param user
     * @return
     */
    @PostMapping("/insert")
    public MongoUser insertUser(@RequestBody MongoUser user) {
        System.out.println(user);
        // 可以用 save 替代
        mongoTemplate.insert(user);
        return user;
    }

    /**
     * 2、查
     * @param id
     * @return
     */
    @GetMapping("/get/{id}")
    public MongoUser getUserById(@PathVariable String id) {
        //5d4810796a73dc04f4001442
        return mongoTemplate.findById(id, MongoUser.class);
    }

    /**
     * 3、删
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public MongoUser deleteUserById(@PathVariable String id) {
        MongoUser user = mongoTemplate.findById(id, MongoUser.class);
        mongoTemplate.remove(user);
        return user;
    }

    /**
     * 4、改
     * @param
     * @return
     */
    @PutMapping("/update")
    public UpdateResult updateUser() {
        UpdateResult result=mongoTemplate.upsert(new Query(Criteria.where("userName").is("liuchunling").and("password").is("123")),new Update().set("password","liuchunling123"),"t_user");
        return result;
    }

    /**
     * 5、全
     * @return
     */
    @GetMapping("/get/all")
    public List<MongoUser> getAllUsers() {
        return mongoTemplate.findAll(MongoUser.class);
    }

    /**
     * 6、查 ++：属性、分页
     * @param user
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/select/{page}/{size}")
    public Map<String, Object> selectUserByProperty(MongoUser user, @PathVariable int page, @PathVariable int size) {
        // 条件
        Criteria criteria1 = Criteria.where("userName").is(user.getUserName());
        Criteria criteria2 = Criteria.where("password").is(user.getPassword());
        Query query = new Query();
        if (user.getUserName() != null) {
            query.addCriteria(criteria1);
        }
        if (user.getPassword() != null) {
            query.addCriteria(criteria2);
        }
        // 数量
        long total = mongoTemplate.count(query, MongoUser.class);
        // 分页
        query.skip((page - 1) * size).limit(size);
        List<MongoUser> data = mongoTemplate.find(query, MongoUser.class);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("data", data);
        map.put("total", total);
        return map;
    }

}
