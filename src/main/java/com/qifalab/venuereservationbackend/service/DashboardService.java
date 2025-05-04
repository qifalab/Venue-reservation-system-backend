package com.qifalab.venuereservationbackend.service;

import com.qifalab.venuereservationbackend.dto.DashboardStats;
import com.qifalab.venuereservationbackend.mapper.ReservationMapper;
import com.qifalab.venuereservationbackend.mapper.UserMapper;
import com.qifalab.venuereservationbackend.mapper.VenueMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Service
public class DashboardService {

    @Autowired
    private UserMapper userMapper;
    
    @Autowired
    private ReservationMapper reservationMapper;
    
    @Autowired
    private VenueMapper venueMapper;

    public DashboardStats getStats() {
        DashboardStats stats = new DashboardStats();
        stats.setTotalUsers((long) userMapper.selectCount(null));
        stats.setTotalVenues((long) venueMapper.selectCount(null));
        // 其他统计数据计算...
        return stats;
    }

    public Map<String, Long> getTrends() {
        Map<String, Long> trends = new HashMap<>();
        // 实现预约趋势统��逻辑
        return trends;
    }

    public Map<String, Double> getVenueUsage() {
        Map<String, Double> usage = new HashMap<>();
        // 实现场地使用率统计逻辑
        return usage;
    }
} 