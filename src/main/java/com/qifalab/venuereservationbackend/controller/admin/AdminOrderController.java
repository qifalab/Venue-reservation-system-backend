package com.qifalab.venuereservationbackend.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qifalab.venuereservationbackend.model.Reservation;
import com.qifalab.venuereservationbackend.service.ReservationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Tag(name = "订单管理", description = "订单管理相关接口")
@RestController
@RequestMapping("/api/admin/orders")
public class AdminOrderController {

    @Autowired
    private ReservationService reservationService;

    @Operation(summary = "获取订单列表")
    @GetMapping
    public Page<Reservation> getOrders(
            @RequestParam(defaultValue = "1") Integer current,
            @RequestParam(defaultValue = "10") Integer size) {
        return reservationService.page(new Page<>(current, size));
    }

    @Operation(summary = "更新订单状态")
    @PutMapping("/{id}/status")
    public boolean updateOrderStatus(
            @PathVariable Long id,
            @RequestParam String status) {
        Reservation reservation = reservationService.getById(id);
        reservation.setStatus(status);
        return reservationService.updateById(reservation);
    }

    @Operation(summary = "删除订单")
    @DeleteMapping("/{id}")
    public boolean deleteOrder(@PathVariable Long id) {
        return reservationService.removeById(id);
    }
} 