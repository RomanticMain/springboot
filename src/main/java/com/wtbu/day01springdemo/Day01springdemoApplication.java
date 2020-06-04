package com.wtbu.day01springdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.wtbu.day01springdemo.dao")
public class Day01springdemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(Day01springdemoApplication.class, args);
    }

}
