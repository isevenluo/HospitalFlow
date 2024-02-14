package com.seventech.hospitalflow.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import java.time.Instant;
import lombok.Data;

/**
 * @author Seven Luo
 * @date 2024/2/14 21:18
 */

@Data
@Entity // 表示是一个数据库关联的Java实体
@Table(name = "medicine") // 指定表名字
public class Medicine {

    @Id // 指定ID
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 指定ID的生成规则
    @Column(name = "id", nullable = false) // 指定Java字段和数据库表字段的名称映射
    private Long id;

    @NotNull
    private String name;

    private String manufacturer;

    private Integer stock;

    @Column(name = "created_time")
    private Instant createdTime;

}
