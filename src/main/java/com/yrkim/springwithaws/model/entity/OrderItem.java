package com.yrkim.springwithaws.model.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "orderitem")
@EqualsAndHashCode(of = "id")
@DynamicUpdate
@DynamicInsert
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class OrderItem extends BaseTime implements Persistable<Long> {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "item_name",
            nullable = false,
            updatable = false)
    private String itemName;

    @Column(name = "sell_price",
            nullable = false,
            updatable = false)
    private Long sellPrice;

    @Column(name = "sell_count",
            nullable = false,
            updatable = false)
    private Long sellCount;

    @Column(name = "sell_startts",
            nullable = false,
            updatable = false)
    private LocalDateTime sellStartTs;

    @Column(name = "sell_endts",
            nullable = false,
            updatable = false)
    private LocalDateTime sellEndTs;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",
            referencedColumnName = "id",
            nullable = false)
    private User user;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Collection<OrderFile> orderFiles = new ArrayList<>();

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public boolean isNew() {
        return getCreatedt() != null;
    }
}
