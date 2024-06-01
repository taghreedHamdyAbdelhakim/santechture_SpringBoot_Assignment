package com.santechture.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.santechture.api"})
public class AdminTestApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminTestApiApplication.class, args);
    }

}
