package com.qifalab.venuereservationbackend.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("venue")
public class Venue {
    private Long id;
    private String name;        // 场馆名称
    private String type;        // 场馆类型
    private Integer capacity;   // 可预约个数
    private String description; // 场馆描述
    private Boolean isActive;   // 是否启用
}