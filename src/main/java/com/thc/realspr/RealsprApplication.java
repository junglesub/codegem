package com.thc.realspr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
@EnableJpaAuditing
public class RealsprApplication {

    public static void main(String[] args) {
        SpringApplication.run(RealsprApplication.class, args);
    }

}
