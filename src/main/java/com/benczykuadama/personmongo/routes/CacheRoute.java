package com.benczykuadama.personmongo.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class CacheRoute extends RouteBuilder {

    @Override
    public void configure() {

        from("direct:saveInCache")
                .setHeader("CamelEhcacheAction", constant("PUT"))
                .setHeader("CamelEhcacheKey", constant("${header.friendName}-${header.userName}"))
                .to("ehcache://distance");

        from("direct:result")
                .setHeader("CamelEhcacheAction", constant("GET"))
                .setHeader("CamelEhcacheKey", constant("${header.friendName}-${header.userName}"))
                .to("ehcache:distance")
                .choice()
                    .when(header("CamelEhcacheActionHasResult").isEqualTo(false))
                    .setBody(simple("Your result is not ready yet"));
    }
}
