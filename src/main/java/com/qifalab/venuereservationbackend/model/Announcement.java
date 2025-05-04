package com.qifalab.venuereservationbackend.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@TableName("announcement")
public class Announcement {
    private Long id;
    private String title;           // 公告标题
    private String content;         // 公告内容
    private Boolean isActive;       // 是否激活
    private LocalDateTime createTime;// 创建时间
    private LocalDateTime updateTime;// 更新时间
    private String createdBy;       // 创建人
} 