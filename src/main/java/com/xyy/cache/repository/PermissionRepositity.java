package com.xyy.cache.repository;

import com.xyy.cache.bean.mysqljpa.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepositity extends JpaRepository<Permission,Integer> {
}
