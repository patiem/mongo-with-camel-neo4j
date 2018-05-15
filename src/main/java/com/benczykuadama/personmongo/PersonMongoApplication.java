package com.benczykuadama.personmongo;

import org.apache.camel.component.servlet.CamelHttpTransportServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
//@ImportResource({"classpath:spring/camel-context.xml"})
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
