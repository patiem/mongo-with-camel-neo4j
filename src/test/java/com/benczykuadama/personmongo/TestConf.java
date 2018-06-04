package com.benczykuadama.personmongo;

import org.neo4j.ogm.config.Configuration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
@EnableAutoConfiguration
@ActiveProfiles("test")
@DataMongoTest
public class TestConf {

    public static void main(String[] args) {
        System.setProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, "test");
        SpringApplication.run(TestConf.class, args);
    }


//    @Bean
//    public Configuration getConfiguration() {
//        return new org.neo4j.ogm.config.Configuration.Builder()
//                .uri( getUri() )
//                .credentials( username, password )
//                .build();
//    }
}
