package com.xyy.cache.repository;

import com.xyy.cache.bean.mysqljpa.Emp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpRepositoty extends JpaRepository<Emp,Integer> {
}
