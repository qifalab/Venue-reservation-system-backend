package com.qifalab.venuereservationbackend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.qifalab.venuereservationbackend.mapper")
public class VenueReservationBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(VenueReservationBackendApplication.class, args);
    }

}
