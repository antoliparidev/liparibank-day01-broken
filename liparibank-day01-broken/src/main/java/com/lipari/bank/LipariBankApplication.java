package com.lipari.bank;

import com.lipari.bank.shared.config.LipariBankProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(LipariBankProperties.class)
public class LipariBankApplication {

    public static void main(String[] args) {
        SpringApplication.run(LipariBankApplication.class, args);
    }
}
