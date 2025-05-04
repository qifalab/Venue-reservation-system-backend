package com.qifalab.venuereservationbackend.dto;

import lombok.Data;

@Data
public class DashboardStats {
    private Long totalUsers;        // 总用户数
    private Long todayReservations; // 今日预约数
    private Long totalVenues;       // 场馆总数
    private Double occupancyRate;   // 场地使用率
} 