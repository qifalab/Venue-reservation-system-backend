package com.qifalab.venuereservationbackend.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qifalab.venuereservationbackend.mapper.PartnerMapper;
import com.qifalab.venuereservationbackend.model.Partner;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PartnerService extends ServiceImpl<PartnerMapper, Partner> {
    
    public List<Partner> getAvailablePartners(Long venueId) {
        return this.lambdaQuery()
                .eq(Partner::getIsAvailable, true)
                .like(Partner::getVenueIds, venueId.toString())
                .list();
    }

    public boolean isPartnerAvailable(Long partnerId, Long venueId, LocalDate date, String timeSlot) {
        Partner partner = this.getById(partnerId);
        if (partner == null || !partner.getIsAvailable()) {
            return false;
        }
        return partner.getVenueIds().contains(venueId.toString());
    }
} 