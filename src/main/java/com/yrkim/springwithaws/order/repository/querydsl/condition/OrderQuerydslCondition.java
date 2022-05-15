package com.yrkim.springwithaws.order.repository.querydsl.condition;

import com.yrkim.springwithaws.common.tools.querydsl.QuerydslRepositorySupport;
import com.yrkim.springwithaws.order.model.entity.OrderMaster;

public class OrderQuerydslCondition extends QuerydslRepositorySupport {
    public OrderQuerydslCondition() {
        super(OrderMaster.class);
    }
}
