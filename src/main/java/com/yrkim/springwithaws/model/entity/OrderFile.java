package com.yrkim.springwithaws.model.entity;

import com.yrkim.springwithaws.common.model.entity.BaseTime;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;

@Entity
@Table(name = "orderfile")
@Getter
@EqualsAndHashCode(of = "id")
@DynamicUpdate
@DynamicInsert
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class OrderFile extends BaseTime implements Persistable<Long> {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "origin_file_name",
            nullable = false,
            updatable = false)
    private String originFileName;

    @Column(name = "remote_file_name",
            nullable = false,
            updatable = false)
    private String remoteFileName;

    @Column(name = "filePath",
            nullable = false,
            updatable = false)
    private String filePath;

    @Column(name = "content_type",
            nullable = false,
            updatable = false)
    private String contentType;

    @Column(name = "original_file_extension",
            nullable = false,
            updatable = false)
    private String originalFileExtension;

    @Column(name = "file_Size",
            nullable = false,
            updatable = false)
    private Long fileSize;

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
