package com.card;

import com.card.util.JwtUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FakaApplication {

    public static void main(String[] args) {
        SpringApplication.run(FakaApplication.class, args);
    }
}