package com.card;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;

@SpringBootApplication
public class FakaApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(FakaApplication.class, args);
    }

    @Override
    public void run(String... args) {
        File file = new File("/data/faka/exportFile/");
        if (!file.exists()) {
            file.mkdir();
        }
    }
}