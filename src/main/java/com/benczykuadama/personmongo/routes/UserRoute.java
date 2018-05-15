package com.benczykuadama.personmongo.routes;

import com.benczykuadama.personmongo.model.Friend;
import com.benczykuadama.personmongo.model.User;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.apache.camel.model.rest.RestParamType;
import org.springframework.stereotype.Component;

@Component
public class UserRoute extends RouteBuilder {

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

        rest("/users")
                .produces("aplication/json")
                .consumes("aplication/json")

                .post().type(User.class)
                    .to("bean:userService?method=save(${body})")

                .get().outType(User[].class)
                    .to("bean:userService?method=findAll()")

                .get("findBy/name").outType(User.class)
                    .param().name("name").type(RestParamType.query).description("User name").required(true).dataType("string").endParam()
                .to("bean:userService?method=findAllByName(${header.name})")

                .get("findBy/containing").outTypeList(User.class)
                    .param().name("name").type(RestParamType.query).description("Part of name").required(true).dataType("string").endParam()
                .to("bean:userService?method=findAllByNameContaining(${header.name})")

                .get("findBy/city").outType(User[].class)
                    .param().name("city").type(RestParamType.query).dataType("string").endParam()
                .to("bean:userService?method=findAllByCity(${header.city})")

                .get("findBy/nameAndCity").outTypeList(User.class)
                    .param().name("name").type(RestParamType.query).description("User name").required(true).dataType("string").endParam()
                    .param().name("city").type(RestParamType.query).description("User city").required(true).dataType("string").endParam()
                .to("bean:userService?method=findAllByNameAndCity(${header.name}, ${header.city})")

                .get("findBy/ageBetween").outTypeList(User.class)
                    .param().name("min").type(RestParamType.query).description("Minimal age").required(true).dataType("integer").endParam()
                    .param().name("max").type(RestParamType.query).description("Maximal age").required(true).dataType("integer").endParam()
                .to("bean:userService?method=findAllByBirthDateBetween(${header.max}, ${header.min})")

                .get("findBy/ageBetweenAndName").outTypeList(User.class)
                    .param().name("min").type(RestParamType.query).description("Minimal age").required(true).dataType("integer").endParam()
                    .param().name("max").type(RestParamType.query).description("Maximal age").required(true).dataType("integer").endParam()
                    .param().name("city").type(RestParamType.query).description("User city").required(true).dataType("string").endParam()
                .to("bean:userService?method=findAllByBirthDateBetweenAndName(${header.max}, ${header.min}, ${header.name})");


        rest("/{user}")
                .produces("aplication/json")
                .consumes("aplication/json")
            .get().outType(Friend.class)
                .param().name("user").type(RestParamType.path).dataType("string").endParam()
                .to("bean:friendService?method=findByName(${header.user})");

    }
}
