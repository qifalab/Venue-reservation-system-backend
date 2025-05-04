package com.qifalab.venuereservationbackend.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@TableName("reservation")
public class Reservation {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private LocalDate date;         // 预约日期
    private Long venueId;           // 场馆id
    private String timeSlot;        // 预约时间段
    private Boolean needPartner;    // 是否需要陪练
    private Long partnerId;         // 陪练id
    private String userName;        // 用户姓名
    private String studentId;       // 学号
    private String phoneNumber;     // 手机号
    private String remarks;         // 备注
    private LocalDateTime createTime;// 创建时间
    private String status;          // 预约状态
}