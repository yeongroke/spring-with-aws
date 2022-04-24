package com.yrkim.springwithaws.order.repository.jpa;

import com.yrkim.springwithaws.model.entity.OrderMaster;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface OrderRepository extends PagingAndSortingRepository<OrderMaster, String> {
}
