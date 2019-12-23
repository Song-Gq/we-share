package com.tongji.weshare;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.tongji.weshare.mapper")
public class WeShareApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeShareApplication.class, args);
    }

}
