package com.yrkim.springwithaws.model.entity;

import com.yrkim.springwithaws.common.model.entity.BaseTime;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;

@Entity
@Table(name = "orderdetail")
@Getter
@EqualsAndHashCode(of = "id")
@DynamicUpdate
@DynamicInsert
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orderitem_id",
            referencedColumnName = "id",
            nullable = false)
    private OrderItem orderItem;

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public boolean isNew() {
        return getCreatedt() != null;
    }
}
