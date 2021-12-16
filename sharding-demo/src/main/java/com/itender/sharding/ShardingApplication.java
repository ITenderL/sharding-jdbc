package com.itender;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @Author: ITender
 * @CreateTime: 2021-12-16 10:56
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class ShardingApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShardingApplication.class, args);
    }
}
