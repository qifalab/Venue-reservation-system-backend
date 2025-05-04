package com.qifalab.venuereservationbackend.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("partner")
public class Partner {
    private Long id;
    private String name;        // 陪练姓名
    private String introduction;// 简介
    private String venueIds;    // 可用场馆id，用逗号分隔
    private String phoneNumber; // 联系电话
    private Boolean isAvailable;// 是否可用
}