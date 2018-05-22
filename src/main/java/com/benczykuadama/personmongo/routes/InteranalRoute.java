package com.benczykuadama.personmongo.routes;

import com.benczykuadama.personmongo.service.FriendServiceImpl;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class InteranalRoute extends RouteBuilder {


    @Override
    public void configure() throws Exception {


        from("direct:acceptInvitation")
                .to("bean:friendService?method=makeFriends(${header.userName}, ${header.friendName})");

        from("direct:addMessage")
                .to("bean:userPostService?method=save(${header.userName}, ${body})");

        from("direct:convert")
                .to("bean:userService?method=getUsers(${body})");

        from("direct:findAllByBirthDateBetween")
                .to("bean:userService?method=findAllByBirthDateBetween(${header.max}, ${header.min})");

        from("direct:findAllByBirthDateBetweenAndName")
                .to("bean:userService?method=findAllByBirthDateBetweenAndName(${header.max}, ${header.min}, ${header.name})");

        from("direct:findAllByCity")
                .to("bean:userService?method=findAllByCity(${header.city})");

        from("direct:findAllByNameAndCity")
                .to("bean:userService?method=findAllByNameAndCity(${header.name}, ${header.city})");

        from("direct:findByContaining")
                .to("bean:userService?method=findAllByNameContaining(${header.name})");

        from("direct:findByName")
                .to("bean:userService?method=findByName(${header.userName})");

        from("direct:friends")
                .bean(FriendServiceImpl.class, "findAllFriends(${header.userName})")
                .to("direct:convert");

        from("direct:friendsPosts")
                .to("direct:friends")
                .to("bean:userPostService?method=getPosts(${body}, FRIENDS)");

        from("direct:inviteFriend")
                .to("bean:friendService?method=invite(${header.userName}, ${header.another})");

        from("direct:mongo")
                .to("bean:userService?method=save(${body})");

        from("direct:myPosts")
                .to("bean:userPostService?method=getPosts(${header.userName})");

        from("direct:network")
                .to("bean:friendService?method=findNetwork(${header.userName})")
                .to("direct:convert");

        from("direct:networkPosts")
                .to("direct:network")
                .to("bean:userPostService?method=getPosts(${body}, NETWORK)");

        from("direct:path")
                .bean(FriendServiceImpl.class, "pathTo(${header.userName}, ${header.friendName})")
                .to("direct:convert");

        from("direct:queue")
                .to("activemq:distance");

        from("direct:register")
                .enrich("direct:mongo", (exchange, exchange1) -> exchange1)
                .to("bean:friendService?method=save(${body})");

        from("direct:showAll")
                .to("bean:userService?method=findAll()");

        from("direct:showInvitations")
                .to("bean:friendService?method=showInvitations(${header.userName})");

        from("direct:unfriend")
                .to("bean:friendService?method=unfriend(${header.userName}, ${header.friendName})");

        from("activemq:distance?concurrentConsumers=5")
                .to("bean:friendService?method=distanceBetween(${header.userName}, ${header.friendName})")
                .to("direct:saveInCache")
                .setBody(simple("Your request is in process, your result will be available on address user/${header.userName}/resultFor/${header.friendName}"));

    }
}
