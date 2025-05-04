package com.qifalab.venuereservationbackend.controller.admin;

import com.qifalab.venuereservationbackend.model.Venue;
import com.qifalab.venuereservationbackend.service.VenueService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "场馆管理", description = "场馆管理相关接口")
@RestController
@RequestMapping("/api/admin/venues")
public class AdminVenueController {

    @Autowired
    private VenueService venueService;

    @Operation(summary = "获取所有场馆")
    @GetMapping
    public List<Venue> getAllVenues() {
        return venueService.list();
    }

    @Operation(summary = "添加场馆")
    @PostMapping
    public boolean addVenue(@RequestBody Venue venue) {
        return venueService.save(venue);
    }

    @Operation(summary = "更新场馆")
    @PutMapping("/{id}")
    public boolean updateVenue(@PathVariable Long id, @RequestBody Venue venue) {
        venue.setId(id);
        return venueService.updateById(venue);
    }

    @Operation(summary = "删除场馆")
    @DeleteMapping("/{id}")
    public boolean deleteVenue(@PathVariable Long id) {
        return venueService.removeById(id);
    }
} 