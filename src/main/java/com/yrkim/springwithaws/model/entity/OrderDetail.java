package com.yrkim.springwithaws.model.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;

@Entity
@Table(name = "orderdetail")
@EqualsAndHashCode(of = "id")
@DynamicUpdate
@DynamicInsert
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class OrderDetail extends BaseTime implements Persistable<Long> {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "purchase_price",
            nullable = false,
            updatable = false)
    private Long purchasePrice;

    @Column(name = "purchase_count",
            nullable = false,
            updatable = false)
    private Long purchaseCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ordermaster_id",
            referencedColumnName = "id",
            nullable = false)
    private OrderMaster orderMaster;

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public boolean isNew() {
        return getCreatedt() != null;
    }
}
