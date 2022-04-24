package com.yrkim.springwithaws.model.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.yrkim.springwithaws.auth.model.entity.User;
import com.yrkim.springwithaws.common.model.entity.BaseTime;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "ordermaster")
@Getter
@EqualsAndHashCode(of = "id")
@DynamicUpdate
@DynamicInsert
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class OrderMaster extends BaseTime implements Persistable<Long> {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "total_price",
            nullable = false,
            updatable = false)
    private Long totalPrice;

    @Column(name = "order_count",
            nullable = false,
            updatable = false)
    private Long orderCount;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Collection<OrderDetail> orderDetails = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",
            referencedColumnName = "id",
            nullable = false)
    private User user;

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public boolean isNew() {
        return getCreatedt() != null;
    }
}
