package com.qrsoft;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BigDataKongGuanApplication {
    public static ConfigurableApplicationContext appConfig;
    public static void main(String[] args) {

        appConfig=SpringApplication.run(BigDataKongGuanApplication.class, args);
    }
}
