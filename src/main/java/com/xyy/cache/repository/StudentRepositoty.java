package com.xyy.cache.repository;

import com.xyy.cache.bean.mysqljpa.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepositoty extends JpaRepository<Student,Integer> {
}
