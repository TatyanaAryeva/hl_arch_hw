package org.hlarch;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@Slf4j
@SpringBootApplication
@EnableConfigurationProperties
public class HLArchApplication {
    public static void main(String[] args) {
        SpringApplication.run(HLArchApplication.class, args);
        System.out.println();
    }
}