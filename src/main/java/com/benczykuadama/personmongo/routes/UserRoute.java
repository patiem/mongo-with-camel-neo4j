package com.benczykuadama.personmongo.routes;

import com.benczykuadama.personmongo.model.User;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

@Component
public class UserRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        restConfiguration()
                .component("servlet")
//                .host("0.0.0.0")
//                .port(8085)
                .bindingMode(RestBindingMode.json)

        .contextPath("/api")
        .apiContextPath("/swagger")
        .apiContextRouteId("swagger")
        .apiProperty("api.title", "Mordabook API")
        .apiProperty("api.version", "6.6.6")
                .scheme("http,https")
                .host("localhost:8080");

        rest("/users")
                .produces("aplication/json")
                .consumes("aplication/json")

                .post().type(User.class)
                    .to("bean:userService?method=save(${body})")

                .get().outType(User[].class)
                    .to("bean:userService?method=findAll()")

                .get("findBy/name")
                    .to("bean:userService?method=findAllByName(${header.name})")

                .get("findBy/city")
                    .to("bean:userService?method=findAllByName(${header.city})")

                .get("findBy/nameAndCity")
                    .to("bean:userService?method=findAllByName(${header.name}, ${header.city})")

                .get("findBy/ageBetween")
                    .to("bean:userService?method=findAllByName(${header.min}, ${header.max})")

                .get("find/ageBetweenAndName")
                    .to("bean:userService?method=findAllByName(${header.min}, ${header.max}, ${header.name})");

    }
}
