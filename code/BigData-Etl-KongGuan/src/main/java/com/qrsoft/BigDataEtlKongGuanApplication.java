package com.qrsoft;

import com.qrsoft.etl.spark.SparkStreamingApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BigDataEtlKongGuanApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(BigDataEtlKongGuanApplication.class, args);
        SparkStreamingApplication bean = run.getBean(SparkStreamingApplication.class);
        bean.SparkEtlStart();
    }
}
