package com.xyy.cache.repository;

import com.xyy.cache.bean.mysqljpa.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepositoty extends JpaRepository<Address,Integer> {

}
