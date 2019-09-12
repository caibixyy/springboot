package com.xyy.cache.repository;

import com.xyy.cache.bean.mysqljpa.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepositoty extends JpaRepository<Person,Integer> {
}
