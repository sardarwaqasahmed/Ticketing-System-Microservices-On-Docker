package com.callsign.orderservice.repository;

import com.callsign.orderservice.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
/**
 Author: waqas ahmed
 Date  : APR 14, 2022
 **/
public interface UserRepository extends CrudRepository<UserEntity, Long> {
    Optional<UserEntity> findByUserName(String userName);
}
