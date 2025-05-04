package com.qifalab.venuereservationbackend.controller;

import com.qifalab.venuereservationbackend.model.Venue;
import com.qifalab.venuereservationbackend.service.VenueService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Tag(name = "场馆管理", description = "场馆相关接口")
@RestController
@RequestMapping("/api/venues")
public class VenueController {

    @Autowired
    private VenueService venueService;

    @Operation(summary = "获取可用场馆", description = "根据日期获取可用场馆列表")
    @GetMapping
    public List<Venue> getAvailableVenues(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return venueService.getAvailableVenues(date);
    }

    @Operation(summary = "获取可用时间段", description = "根据场馆ID和日期获取可用时间段")
    @GetMapping("/{venueId}/time-slots")
    public Map<String, Integer> getAvailableTimeSlots(
            @PathVariable Long venueId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return venueService.getAvailableTimeSlots(venueId, date);
    }

    @Operation(summary = "获取可预约日期", description = "获取从今天开始的未来7天日期列表")
    @GetMapping("/available-dates")
    public Map<String, List<String>> getAvailableDates() {
        List<String> dates = LocalDate.now()
                .datesUntil(LocalDate.now().plusDays(7))
                .map(date -> date.format(DateTimeFormatter.ISO_LOCAL_DATE))
                .collect(Collectors.toList());
        
        return Map.of("data", dates);
    }
} 