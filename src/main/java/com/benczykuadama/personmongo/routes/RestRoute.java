package com.benczykuadama.personmongo.routes;

import com.benczykuadama.personmongo.model.Invitation;
import com.benczykuadama.personmongo.model.PostWall;
import com.benczykuadama.personmongo.model.User;
import com.benczykuadama.personmongo.model.UserPost;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.apache.camel.model.rest.RestParamType;
import org.springframework.stereotype.Component;

@Component
public class RestRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        rest("/users")
                .produces("application/json")
                .consumes("application/json")

                .post().type(User.class)
                    .to("direct:register")

                .get().outType(User[].class)
                    .to("direct:showAll")

                .get("findBy/name").outType(User.class)
                    .param().name("userName").type(RestParamType.query).description("User name").required(true).dataType("string").endParam()
                .to("direct:findByName")

                .get("findBy/containing").outType(User[].class)
                    .param().name("name").type(RestParamType.query).description("Part of name").required(true).dataType("string").endParam()
                .to("direct:findByContaining")

                .get("findBy/city").outType(User[].class)
                    .param().name("city").type(RestParamType.query).dataType("string").endParam()
                .to("direct:findAllByCity")

                .get("findBy/nameAndCity").outType(User[].class)
                    .param().name("name").type(RestParamType.query).description("User name").required(true).dataType("string").endParam()
                    .param().name("city").type(RestParamType.query).description("User city").required(true).dataType("string").endParam()
                .to("direct:findAllByNameAndCity")

                .get("findBy/ageBetween").outType(User[].class)
                    .param().name("min").type(RestParamType.query).description("Minimal age").required(true).dataType("integer").endParam()
                    .param().name("max").type(RestParamType.query).description("Maximal age").required(true).dataType("integer").endParam()
                .to("direct:findAllByBirthDateBetween")

                .get("findBy/ageBetweenAndName").outType(User[].class)
                    .param().name("min").type(RestParamType.query).description("Minimal age").required(true).dataType("integer").endParam()
                    .param().name("max").type(RestParamType.query).description("Maximal age").required(true).dataType("integer").endParam()
                    .param().name("city").type(RestParamType.query).description("User city").required(true).dataType("string").endParam()
                .to("direct:findAllByBirthDateBetweenAndName");


        rest("/user/{userName}")
                .produces("application/json")
                .consumes("application/json")

            .get().outType(User.class)
                .param().name("userName").type(RestParamType.path).dataType("string").endParam()
                .to("direct:findByName")

            .get("/all").outType(User[].class)
                .to("direct:showAll")

            .get("/friends").outType(User[].class)
                .to("direct:friends")

            .get("/network").outType(User[].class)
                .to("direct:network")

            .get("/invitations").outType(Invitation[].class)
                .to("direct:showInvitations")

            .post("/invite/{another}")
                .param().name("userName").type(RestParamType.path).dataType("string").endParam()
                .param().name("another").type(RestParamType.path).dataType("string").endParam()
                .to("direct:inviteFriend")

            .post("/accept/{friendName}")
                .param().name("userName").type(RestParamType.path).dataType("string").endParam()
                .param().name("friendName").type(RestParamType.path).dataType("string").endParam()
                .to("direct:acceptInvitation")

            .post("/unfriend/{friendName}")
                .param().name("userName").type(RestParamType.path).dataType("string").endParam()
                .param().name("friendName").type(RestParamType.path).dataType("string").endParam()
                .to("direct:unfriend")

            .get("/pathTo/{friendName}").outType(User[].class)
                .param().name("userName").type(RestParamType.path).dataType("string").endParam()
                .param().name("friendName").type(RestParamType.path).dataType("string").endParam()
                .to("direct:path")

            .get("/countDistance/{friendName}").outType(String.class)
                .responseMessage().code(200).message("Your request is in process").endResponseMessage()
                .param().name("userName").type(RestParamType.path).dataType("string").endParam()
                .param().name("friendName").type(RestParamType.path).dataType("string").endParam()
                .to("direct:queue")

            .get("/distance/{friendName}").outType(Integer.class)
                .param().name("userName").type(RestParamType.path).dataType("string").endParam()
                .param().name("friendName").type(RestParamType.path).dataType("string").endParam()
                .to("direct:result")

            .post("/message/add").type(UserPost.class)
                .to("direct:addMessage")

            .get("/message/my").outType(PostWall.class)
                .to("direct:myPosts")

            .get("/message/friends").outType(PostWall.class)
                .to("direct:friendsPosts")

            .get("/message/network").outType(PostWall.class)
                .to("direct:networkPosts");

    }
}
