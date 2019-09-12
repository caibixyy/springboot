package com.xyy.cache.service.Impl;

import com.alibaba.fastjson.JSON;
import com.xyy.cache.repository.UserRepository;
import com.xyy.cache.bean.mysqljpa.User;
import com.xyy.cache.service.UserService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Cacheable(value = "aa", key = "'1'")
    public String getUser() {
        // 该打印用来判断是否重复进入方法内
        System.out.println("in");
        // 如果return null 不会缓存，必须有返回值
        return "1";
    }

    @Override
    public void deleteById(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public User findByUsername(String username) {
       return userRepository.findByUsername(username);
    }

    @Override
    public void save(User user) {
        User user1=userRepository.save(user);
        System.out.println(user1);
    }

    @Override
    public int updateUsernameById(String username,Integer id) {
        return userRepository.updateUsernameById(username,id);
    }

    @Override
    public User findById(Integer id) {
        Optional<User> one = userRepository.findById(id);
        if(one.isPresent()){
            System.out.println(one.get());
            return one.get();
        }else {
            return null;
        }
    }
    @Cacheable(cacheNames = "user")
    @Override
    public List<User> findAll() {
      return userRepository.findAll();
    }

    @Override
    public User findByUsernameAndPassword(String userName, String password) {
        return userRepository.findByUsernameAndPassword(userName,password);
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        Page<User> p=userRepository.findAll(pageable);
        return p;
    }

    @Override
    public List<User> findAll(Sort sort) {
        return userRepository.findAll(sort);
    }

    @Override
    public List<User> findByUidGreaterThan(Integer id) {
        return userRepository.findByUidGreaterThan(id);
    }

}
