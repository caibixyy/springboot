package com.xyy.cache.service;

import com.xyy.cache.bean.mysqljpa.Role;
import com.xyy.cache.bean.mysqljpa.User;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserService {

    public void deleteById(Integer id);

    User findByUsername(String username);
    /** 增加*/
    public void save(User user);
    /** 更新*/
    public int updateUsernameById(String username,Integer id);
    /** 查询单个*/
    public User findById(Integer id);

    List<User> findAll();

    User findByUsernameAndPassword(String username,String password);

    Page<User> findAll(Pageable pageable);

    List<User> findAll(Sort sort);
    List<User> findByUidGreaterThan(@Param(value = "id") Integer id);
}
