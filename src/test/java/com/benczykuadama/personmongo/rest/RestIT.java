package com.benczykuadama.personmongo.rest;


import com.benczykuadama.personmongo.RestTestConfigRoute;
import com.benczykuadama.personmongo.TestConf;
import com.benczykuadama.personmongo.model.User;
import com.benczykuadama.personmongo.repository.UserRepository;
import com.benczykuadama.personmongo.routes.CacheRoute;
import com.benczykuadama.personmongo.routes.InteranalRoute;
import com.benczykuadama.personmongo.routes.RestRoute;
import io.restassured.RestAssured;
import org.apache.camel.CamelContext;
import org.apache.camel.RoutesBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static io.restassured.RestAssured.given;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConf.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class RestIT extends CamelTestSupport {

    @Autowired
    UserRepository repository;

    @LocalServerPort
    private Integer portt;

    @Before
    public void setup() throws Exception {
        repository.deleteAll();
        User user = new User("Dorota", "Zakopane", "01-12-1990");
        repository.save(user);

    }

    @Test
    public void getOnUsersReturns200() {
        given().port(portt).when().get("/api/users").then().statusCode(200).;

    }

    @Override
    public RouteBuilder createRouteBuilder() {
        return new RestTestConfigRoute();
    }

}
