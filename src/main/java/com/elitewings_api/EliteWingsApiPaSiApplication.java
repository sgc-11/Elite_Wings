package com.elitewings_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class EliteWingsApiPaSiApplication {

    public static void main(String[] args) {

        SpringApplication.run(EliteWingsApiPaSiApplication.class, args);
    }

}
