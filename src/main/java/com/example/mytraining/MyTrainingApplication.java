package com.example.mytraining;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan(value = "com.example.mytraining.mapper")
@EnableAsync
@EnableTransactionManagement
public class MyTrainingApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyTrainingApplication.class, args);
    }
}
