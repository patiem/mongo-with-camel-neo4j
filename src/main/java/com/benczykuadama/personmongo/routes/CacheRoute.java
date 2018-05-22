package com.benczykuadama.personmongo.routes;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class CacheRoute extends RouteBuilder {




    @Override
    public void configure() {


        from("direct:saveInCache")
                .setHeader("CamelEhcacheAction", constant("PUT"))
                .log("${header.friendName}-${header.userName}")
                .setHeader("CamelEhcacheKey", constant("${header.friendName}-${header.userName}"))
                .to("ehcache://distance");

        from("direct:result")
                .log("${header.friendName}-${header.userName}")
                .setHeader("CamelEhcacheAction", constant("GET"))
                .setHeader("CamelEhcacheKey", constant("${header.friendName}-${header.userName}"))
                .to("ehcache:distance")
                .choice()
                    .when(header("CamelEhcacheActionHasResult").isEqualTo(false))
                    .setBody(simple("Your result is not ready yet"));


//        from("direct:saveInCache")
//                .setHeader("CamelEhcacheAction", constant("PUT_IF_ABSENT"))
//                .setHeader("CamelEhcacheKey", constant(String.format("%s-%s", header("${header.friendName}"), header("${header.userName"))))
//            .to("ehcache://distance");

//        from("direct:ShowData").process(new Processor() {
//            public void process(Exchange exchange) throws Exception {
//                String operation = (String) exchange.getIn().getHeader(
//                        CacheConstants.CACHE_OPERATION);
//                String key = (String) exchange.getIn().getHeader(
//                        CacheConstants.CACHE_KEY);
//                Object body = exchange.getIn().getBody();
//                String data = exchange.getContext().getTypeConverter()
//                        .convertTo(String.class, body);
//                if (operation.equals("ADD")){
//                    logger.info("------- Cache element was not found, Add the element to the cache ---------");
//
//                }else {
//                    logger.info("------- Element found in the cache ---------");
//                }
//                logger.info("Show Data from: ServerCacheTest");
//                logger.info("Operation = " + operation);
//                logger.info("Key = " + key);
//                logger.info("Value = " + data);
//                logger.info("------ End  ------");
//            }
//        });
    }
}
