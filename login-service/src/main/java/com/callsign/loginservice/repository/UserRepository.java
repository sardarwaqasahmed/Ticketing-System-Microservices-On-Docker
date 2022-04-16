package com.callsign.loginservice.repository;

import com.callsign.loginservice.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
/**
 Author: waqas ahmed
 Date  : APR 14, 2022
 **/
public interface UserRepository extends CrudRepository<UserEntity, Long> {
    Optional<UserEntity> findByUserName(String userName);
}
