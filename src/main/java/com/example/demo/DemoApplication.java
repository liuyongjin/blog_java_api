package com.example.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

// import javax.annotation.PostConstruct;
// import java.util.TimeZone;

@SpringBootApplication
@MapperScan("com.example.demo.model.dao")
@EnableCaching
public class DemoApplication {
    // @PostConstruct
    // void setDefaultTimezone() {
    // TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));
    // }

    public static void main(String[] args) {
        // TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));
        SpringApplication.run(DemoApplication.class, args);
    }
}
