package com.qifalab.venuereservationbackend.controller.admin;

import com.qifalab.venuereservationbackend.dto.DashboardStats;
import com.qifalab.venuereservationbackend.service.DashboardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Tag(name = "仪表盘", description = "仪表盘相关接口")
@RestController
@RequestMapping("/api/admin/dashboard")
public class AdminDashboardController {

    @Autowired
    private DashboardService dashboardService;

    @Operation(summary = "获取统计数据")
    @GetMapping("/stats")
    public DashboardStats getStats() {
        return dashboardService.getStats();
    }

    @Operation(summary = "获取预约趋势")
    @GetMapping("/trends")
    public Map<String, Long> getTrends() {
        return dashboardService.getTrends();
    }

    @Operation(summary = "获取场地使用率")
    @GetMapping("/venue-usage")
    public Map<String, Double> getVenueUsage() {
        return dashboardService.getVenueUsage();
    }
} 