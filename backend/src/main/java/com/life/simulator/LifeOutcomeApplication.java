package com.life.simulator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class LifeOutcomeApplication {

    public static void main(String[] args) {
        SpringApplication.run(LifeOutcomeApplication.class, args);
    }
}
