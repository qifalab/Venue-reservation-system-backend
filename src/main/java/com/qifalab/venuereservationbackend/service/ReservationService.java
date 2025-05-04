package com.qifalab.venuereservationbackend.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qifalab.venuereservationbackend.mapper.ReservationMapper;
import com.qifalab.venuereservationbackend.model.Reservation;
import com.qifalab.venuereservationbackend.model.Venue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
public class ReservationService extends ServiceImpl<ReservationMapper, Reservation> {

    @Autowired
    private VenueService venueService;
    
    @Autowired
    private PartnerService partnerService;

    @Transactional
    public void createReservation(Reservation reservation) {
        // 验证场馆是否存在
        Venue venue = venueService.getById(reservation.getVenueId());
        if (venue == null) {
            throw new RuntimeException("场馆不存在");
        }

        // 验证时间段是否已满
        Map<String, Integer> availableSlots = venueService.getAvailableTimeSlots(
                reservation.getVenueId(), 
                reservation.getDate()
        );
        
        Integer available = availableSlots.get(reservation.getTimeSlot());
        if (available == null || available <= 0) {
            throw new RuntimeException("该时间段已约满");
        }

        // 如果需要陪练，验证陪练是否可用
        if (reservation.getNeedPartner() && reservation.getPartnerId() != null) {
            boolean partnerAvailable = partnerService.isPartnerAvailable(
                    reservation.getPartnerId(),
                    reservation.getVenueId(),
                    reservation.getDate(),
                    reservation.getTimeSlot()
            );
            if (!partnerAvailable) {
                throw new RuntimeException("所选陪练在该时间段不可用");
            }
        }

        // 设置创建时间和状态
        reservation.setCreateTime(LocalDateTime.now());
        reservation.setStatus("PENDING");

        // 保存预约
        this.save(reservation);
    }

    public List<Reservation> getReservations(LocalDate date, Long venueId, String status) {
        return this.lambdaQuery()
                .eq(date != null, Reservation::getDate, date)
                .eq(venueId != null, Reservation::getVenueId, venueId)
                .eq(status != null, Reservation::getStatus, status)
                .orderByDesc(Reservation::getCreateTime)
                .list();
    }
} 