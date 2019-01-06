package com.xumou.ssh.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 实体类定时示范
 */
@Entity
@Table(
        name = "entity_demo",//表名
        indexes = {@Index(name = "idx_entity_demo", columnList = "idCard")} // 索引, 写两个就是复合索引
)
public class EntityDemo implements Serializable{

    @Id
    @SequenceGenerator(name = "seqId", sequenceName = "seq_entity_demo") // 定义序列
    @GeneratedValue(generator = "seqId") // 使用序列
    private Long id;

    private String name;

    @Column(nullable = false, columnDefinition = "integer default 0") // 非空默认0
    private Integer age;

    private String idCard;

}