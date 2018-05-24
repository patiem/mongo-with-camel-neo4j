package com.benczykuadama.personmongo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootApplication
@EnableAutoConfiguration
@ActiveProfiles("test")
@DataMongoTest
public class TestConf {

    public static void main(String[] args) {
        SpringApplication.run(TestConf.class, args);
    }

}
