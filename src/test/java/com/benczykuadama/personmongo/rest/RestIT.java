package com.benczykuadama.personmongo.rest;


import com.benczykuadama.personmongo.TestConf;
import com.benczykuadama.personmongo.model.User;
import com.benczykuadama.personmongo.model.UserPost;
import com.benczykuadama.personmongo.repository.UserRepository;
import com.github.fge.jsonschema.cfg.ValidationConfiguration;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import io.restassured.parsing.Parser;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static com.github.fge.jsonschema.SchemaVersion.DRAFTV4;
import static io.restassured.RestAssured.expect;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConf.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class RestIT {

    @Autowired
    UserRepository repository;

    @LocalServerPort
    private Integer port;

    private User user;
    private User user2;

    @Before
    public void setup() throws Exception {
        repository.deleteAll();
        user = new User("Dorota", "Zakopane", "01-12-1990");
        user2 = new User("Ludwik", "Pcim", "01-12-2000");

        UserPost post = new UserPost("text");
        UserPost post2 = new UserPost("text2");

        user.publish(post);
        user.publish(post2);

        repository.save(user);
        repository.save(user2);
    }

    @Test
    public void get_userUserName_returnsValidJsonUser() {

        given()
                .port(port)
                .pathParam("userName", "Dorota")

        .when()
                    .get("/api/user/{userName}")

        .then()
                    .statusCode(HttpStatus.SC_OK)
                    .body("name", is("Dorota"))
                    .body("city", is("Zakopane"))
                    .body("posts[0].message", is("text"))
                    .body("posts.size()", is(2));

    }

    @Test
    public void get_users_returnsJsonWithValidScheme() {

        JsonSchemaFactory jsonSchemaFactory = JsonSchemaFactory.newBuilder().setValidationConfiguration(ValidationConfiguration.newBuilder().setDefaultVersion(DRAFTV4).freeze()).freeze();


        given()
                .port(port)

        .when()
                .get("/api/users")

        .then()
                .statusCode(HttpStatus.SC_OK)
                .assertThat().body(matchesJsonSchemaInClasspath("users-schema.json"));

    }

    @Test
    public void get_findByName_returnsValidUserObject() {

        User userWithName = expect().parser("application/json", Parser.JSON)

            .given()
                .port(port)
                .queryParam("userName", "Ludwik")
            .when()
                .get("/api/users/findBy/name")
                .as(User.class);

        assertEquals(user2, userWithName);

    }

    @Test
    public void post_users_createsNewUser() {


    }

}
