package com.qifalab.venuereservationbackend.model;

import lombok.Getter;

@Getter
public enum TimeSlot {
    SLOT_0800_1000("08:00-10:00"),
    SLOT_1000_1200("10:00-12:00"),
    SLOT_1400_1600("14:00-16:00"),
    SLOT_1600_1800("16:00-18:00"),
    SLOT_1900_2100("19:00-21:00");

    private final String display;

    TimeSlot(String display) {
        this.display = display;
    }
}