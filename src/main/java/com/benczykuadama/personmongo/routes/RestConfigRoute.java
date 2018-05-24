package com.benczykuadama.personmongo.routes;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("!test")
public class RestConfigRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        restConfiguration()
                .component("servlet")
                .bindingMode(RestBindingMode.json)
                .contextPath("/api")
                .apiContextPath("/swagger")
                .apiContextRouteId("swagger")
                .apiProperty("api.title", "Mordabook API")
                .apiProperty("api.version", "6.6.6")
                .scheme("http,https")
                .host("localhost:8080");
    }
}
