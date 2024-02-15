package com.seventech.hospitalflow.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import java.time.Instant;
import java.util.Objects;
import java.util.UUID;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * @author Seven Luo
 * @date 2024/2/15 12:04
 */
@MappedSuperclass // 指定一个类，其映射信息应用于从它继承的子类实体，被标记的类没有为它定义单独的表。
@EntityListeners(AuditingEntityListener.class)
@Data
public class BaseEntity {

    @Id // 指定这个字段是数据库表的主键
    @GeneratedValue // 指定ID的生成规则
    @Column(name = "id", nullable = false) // 指定Java字段和数据库表字段的名称映射
    private UUID id;


    @CreatedBy
    @Column(name = "created_by", length = 210, nullable = false)
    private String createdBy;

    @CreatedDate
    @CreationTimestamp
    @Column(name = "created", nullable = false)
    private Instant created = Instant.now();

    @LastModifiedBy
    @Column(name = "updated_by", length = 20)
    private String updatedBy;

    @LastModifiedDate
    @Column(name = "updated")
    private Instant updated = Instant.now();

    @Column(name = "deleted")
    private Instant deleted;

    @PrePersist
    protected void prePersist() {
        if (Objects.isNull(getId())) {
            setId(UUID.randomUUID());
        }
        if (created == null) {
            this.created = Instant.now();
        }
        if (updated == null) {
            this.updated = Instant.now();
        }
    }

    @PreUpdate
    protected void preUpdate() {
        updated = Instant.now();
    }
}


