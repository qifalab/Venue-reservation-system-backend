package com.qifalab.venuereservationbackend.controller;

import com.qifalab.venuereservationbackend.model.Partner;
import com.qifalab.venuereservationbackend.model.Reservation;
import com.qifalab.venuereservationbackend.model.Venue;
import com.qifalab.venuereservationbackend.service.PartnerService;
import com.qifalab.venuereservationbackend.service.ReservationService;
import com.qifalab.venuereservationbackend.service.VenueService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Tag(name = "场馆预约", description = "场馆预约相关接口")
@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    @Autowired
    private VenueService venueService;
    
    @Autowired
    private PartnerService partnerService;
    
    @Autowired
    private ReservationService reservationService;

    @Operation(summary = "获取可用场馆", description = "获取指定日期可预约的场馆列表")
    @GetMapping("/venues")
    public List<Venue> getAvailableVenues(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return venueService.getAvailableVenues(date);
    }

    @Operation(summary = "获取时间段状态", description = "获取指定日期和场馆的时间段预约状态")
    @GetMapping("/venues/{venueId}/time-slots")
    public Map<String, Integer> getTimeSlots(
            @PathVariable Long venueId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return venueService.getAvailableTimeSlots(venueId, date);
    }

    @Operation(summary = "获取可用陪练", description = "获取指定场馆的可用陪练列表")
    @GetMapping("/venues/{venueId}/partners")
    public List<Partner> getAvailablePartners(@PathVariable Long venueId) {
        return partnerService.getAvailablePartners(venueId);
    }

    @Operation(summary = "提交预约", description = "提交场馆预约信息")
    @PostMapping
    public ResponseEntity<String> submitReservation(@RequestBody Reservation reservation) {
        try {
            reservationService.createReservation(reservation);
            return ResponseEntity.ok("预约成功");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("预约失败：" + e.getMessage());
        }
    }

    @Operation(summary = "获取所有预约订单", description = "获取系统中的所有预约订单信息")
    @GetMapping("/list")
    public List<Reservation> getAllReservations(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @RequestParam(required = false) Long venueId,
            @RequestParam(required = false) String status) {
        
        return reservationService.getReservations(date, venueId, status);
    }
}