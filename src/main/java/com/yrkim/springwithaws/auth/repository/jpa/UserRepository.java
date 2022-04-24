package com.yrkim.springwithaws.auth.repository.jpa;

import com.yrkim.springwithaws.auth.model.entity.User;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface UserRepository extends PagingAndSortingRepository<User, String> {

    Optional<User> findUsersByUserId(String userId);

    Optional<User> findUsersByUserName(String userName);

    Boolean existsByUserName(String userName);
}
