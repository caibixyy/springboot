package com.xyy.cache.repository;

import com.xyy.cache.bean.mysqljpa.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    //1.Jpa命名规范，查询
    //通过账户和密码查询用户
    User findByUsernameAndPassword(String username,String password);

    User findByUsername(String username);

    //本地查询，命名参数查询
    @Modifying
    @Query(value = "update user set username = ?1 where id = ?2",nativeQuery = true)
    int updateUsernameById(String userName,Integer id);

    //自定义repository。手写sql,nativeQyery指是否使用原生sql查询
    //在更新数据的情况下不能单独使用query，要加modify注解
    //更好的支持多表查询的工具框架 QueryDSL
    //2.本地查询

    //3.使用仓库封装的常见增删改查
    //List<User> findAll();
    //4.分页和排序
    void deleteById(Integer id);

    List<User> findAll();

    Page<User> findAll(Pageable pageable);

    List<User> findAll(Sort sort);

    @Query(value = "select * from user u where u.id in (select user_id from user_role where role_id > ?1)",nativeQuery = true)
    List<User> findByUidGreaterThan(@Param(value = "id") Integer id);







}
