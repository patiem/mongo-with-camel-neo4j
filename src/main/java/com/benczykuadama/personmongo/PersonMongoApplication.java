package com.benczykuadama.personmongo;

import org.apache.camel.component.servlet.CamelHttpTransportServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableNeo4jRepositories
@EnableAutoConfiguration
public class PersonMongoApplication {

    public static void main(String[] args) {
        SpringApplication.run(PersonMongoApplication.class, args);
    }

    @Bean
    ServletRegistrationBean servletRegistrationBean() {
        ServletRegistrationBean servlet =  new ServletRegistrationBean(new CamelHttpTransportServlet(), "/api/*");
        servlet.setName("CamelServlet");
        return servlet;
    }


}
