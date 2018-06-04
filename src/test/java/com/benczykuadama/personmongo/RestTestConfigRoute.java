package com.benczykuadama.personmongo;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("test")
public class RestTestConfigRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        restConfiguration()
                .component("servlet")
                .bindingMode(RestBindingMode.json)
                .contextPath("/api")
//                .port(2020)
                .host("0.0.0.0");
    }
}
