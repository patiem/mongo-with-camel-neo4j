package com.benczykuadama.personmongo.routes;

import com.benczykuadama.personmongo.model.Friend;
import com.benczykuadama.personmongo.model.Invitation;
import com.benczykuadama.personmongo.model.User;
import com.benczykuadama.personmongo.service.FriendService;
import com.benczykuadama.personmongo.service.FriendServiceImpl;
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
                .produces("application/json")
                .consumes("application/json")

                .post().type(User.class)
                    .to("direct:register")

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


        rest("/user/{userName}")
                .produces("application/json")
                .consumes("application/json")

            .get().outType(User.class)
                .param().name("userName").type(RestParamType.path).dataType("string").endParam()
                .to("bean:userService?method=findByName(${header.userName})")

            .get("/all").outTypeList(User.class)
                .to("bean:userService?method=findAll()")

            .get("/friends").outTypeList(User.class)
                .to("direct:friends")

            .get("/network").outTypeList(User.class)
                .to("bean:friendService?method=findNetwork(${header.userName})")
                .to("direct:network")

            .get("/invitations").outTypeList(Invitation.class)
                .to("bean:friendService?method=showInvitations(${header.userName})")

            .post("/invite/{another}")
                .param().name("userName").type(RestParamType.path).dataType("string").endParam()
                .param().name("another").type(RestParamType.path).dataType("string").endParam()
                .to("bean:friendService?method=invite(${header.userName}, ${header.another})")

            .post("/accept/{friendName}")
                .param().name("userName").type(RestParamType.path).dataType("string").endParam()
                .param().name("friendName").type(RestParamType.path).dataType("string").endParam()
                .to("bean:friendService?method=makeFriends(${header.userName}, ${header.friendName})")

            .post("/unfriend/{friendName}")
                .param().name("userName").type(RestParamType.path).dataType("string").endParam()
                .param().name("friendName").type(RestParamType.path).dataType("string").endParam()
                .to("bean:friendService?method=unfriend(${header.userName}, ${header.friendName})")

            .get("/pathTo/{friendName}").outTypeList(User.class)
                .param().name("userName").type(RestParamType.path).dataType("string").endParam()
                .param().name("friendName").type(RestParamType.path).dataType("string").endParam()
                .to("direct:path")

            .get("/distanceBetween/{friendName}").outTypeList(Long.class)
                .param().name("userName").type(RestParamType.path).dataType("string").endParam()
                .param().name("friendName").type(RestParamType.path).dataType("string").endParam()
                .to("bean:friendService?method=distanceBetween(${header.userName}, ${header.friendName})");



        from("direct:register")
                .enrich("direct:mongo", (exchange, exchange1) -> exchange1)
                .to("bean:friendService?method=save(${body})");

        from("direct:mongo")
                .to("bean:userService?method=save(${body})");

        from("direct:friends")
                .bean(FriendServiceImpl.class, "findAllFriends(${header.userName})")
                .to("direct:convert");

        from("direct:path")
                .bean(FriendServiceImpl.class, "pathTo(${header.userName}, ${header.friendName})")
                .to("direct:convert");

        from("direct:network")
                .bean(FriendServiceImpl.class, "findNetwork(${header.userName}, ${header.friendName})")
                .to("direct:convert");

        from("direct:convert")
                .to("bean:userService?method=getUsers(${body})");
    }
}
