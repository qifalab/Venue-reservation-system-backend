package com.qifalab.venuereservationbackend.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qifalab.venuereservationbackend.mapper.VenueMapper;
import com.qifalab.venuereservationbackend.model.TimeSlot;
import com.qifalab.venuereservationbackend.model.Venue;
import com.qifalab.venuereservationbackend.model.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qifalab.venuereservationbackend.mapper.ReservationMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class VenueService extends ServiceImpl<VenueMapper, Venue> {
    
    @Autowired
    private ReservationMapper reservationMapper;

    public List<Venue> getAvailableVenues(LocalDate date) {
        return this.lambdaQuery()
                .eq(Venue::getIsActive, true)
                .list();
    }

    public Map<String, Integer> getAvailableTimeSlots(Long venueId, LocalDate date) {
        // 获取场馆信息
        Venue venue = this.getById(venueId);
        if (venue == null) {
            throw new RuntimeException("场馆不存在");
        }

        // 获取该日期该场馆的所有有效预约
        QueryWrapper<Reservation> queryWrapper = new QueryWrapper<Reservation>()
            .eq("venue_id", venueId)
            .eq("date", date)
            .in("status", "PENDING", "CONFIRMED"); // 只统计待确认和已确认的预约

        List<Reservation> reservations = reservationMapper.selectList(queryWrapper);

        // 统计每个时间段的预约数量
        Map<String, Integer> bookedCounts = new HashMap<>();
        for (TimeSlot slot : TimeSlot.values()) {
            // 计算当前时间段的预约数量
            long count = reservations.stream()
                .filter(r -> slot.getDisplay().equals(r.getTimeSlot()))
                .count();
            bookedCounts.put(slot.getDisplay(), (int)count);
        }

        // 计算每个时间段的剩余数量
        Map<String, Integer> availableCounts = new HashMap<>();
        for (TimeSlot slot : TimeSlot.values()) {
            String timeSlot = slot.getDisplay();
            int booked = bookedCounts.getOrDefault(timeSlot, 0);
            int available = venue.getCapacity() - booked;
            availableCounts.put(timeSlot, Math.max(0, available));
        }

        return availableCounts;
    }
} 