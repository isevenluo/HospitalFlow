package com.seventech.hospitalflow.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author Seven Luo
 * @date 2024/2/14 21:18
 */

@Data
@Entity // 表示是一个数据库关联的Java实体
@Table(name = "medicines") // 指定表名字
public class Medicine extends BaseEntity{


    @NotNull
    private String name;

    private String manufacturer;

    private Integer stock;


}
