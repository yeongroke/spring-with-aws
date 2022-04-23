package com.yrkim.springwithaws.repository.jpa;

import com.yrkim.springwithaws.model.entity.User;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends PagingAndSortingRepository<User, String> {
}
