package com.benczykuadama.personmongo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ActiveProfiles;

@Configuration
@EnableAutoConfiguration
@ActiveProfiles("test")
public class TestConf {

    public static void main(String[] args) {
        SpringApplication.run(TestConf.class, args);
    }

}
