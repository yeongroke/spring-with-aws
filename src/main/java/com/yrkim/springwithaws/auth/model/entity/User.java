package com.yrkim.springwithaws.auth.model.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.yrkim.springwithaws.common.model.entity.BaseTime;
import com.yrkim.springwithaws.order.model.entity.OrderItem;
import com.yrkim.springwithaws.order.model.entity.OrderMaster;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.NaturalId;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "users")
@Getter
@EqualsAndHashCode(of = "id")
@DynamicUpdate
@DynamicInsert
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class User extends BaseTime implements Persistable<Long> {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id",
            nullable = false,
            updatable = false)
    private String userId;

    @NotBlank
    @Column(name = "username",
            nullable = false,
            updatable = false)
    @Size(max = 14)
    private String userName;

    @NotBlank
    @NaturalId
    @Size(max = 100)
    @Column(name = "password",
            nullable = false,
            updatable = false)
    private String password;

    @NotBlank
    @Column(name = "email",
            nullable = false,
            updatable = false)
    @Size(max = 50)
    @Email
    private String email;

    // @Enumerated
    // jpa로 데이터베이스에 저장을 할 때 Enum 형식의 값은 어느 형ㅌ내로 저장할지 결정한다.
    // 기본적으로 int형식으로 저장이 된다.
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Collection<OrderMaster> orderMaster = new ArrayList<>();

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Collection<OrderItem> orderItem = new ArrayList<>();

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public boolean isNew() {
        return getCreatedt() != null;
    }
}
