package com.hanwul.kbscbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class KbscBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(KbscBackendApplication.class, args);
    }

}
