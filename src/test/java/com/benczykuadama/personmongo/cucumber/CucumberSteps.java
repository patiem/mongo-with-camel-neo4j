package com.benczykuadama.personmongo.cucumber;

import com.benczykuadama.personmongo.TestConf;
import com.benczykuadama.personmongo.model.User;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@SpringBootTest(classes = TestConf.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class CucumberSteps {

    @LocalServerPort
    private Integer port;

    private User userJohn;

    private Response response;
    private ValidatableResponse json;
    private RequestSpecification request;

    @Before
    public void setup() {
        RestAssured.basePath = "/api/";
    }

    @Given("(^\\w+) who was born in (\\d{2}-\\d{2}-\\d{4}) and lives in ([a-zA-Z\\s]*)")
    public void user_who_was_born_in_and_lives_in(String name, String date, String city) {

        userJohn = new User(name, city, date);

    }

    @When("POST is performed on ([a-zA-Z/]+)")
    public void post_is_performed_on_users(String url) {
        response = given().port(port).contentType("application/json").body(userJohn)
                    .when().post(url);
    }

    @Then("User is added to system registry and returned with MongoID")
    public void user_is_added_to_system_registry() {
        String mongoID = response.getBody().as(User.class).getId();
        assertNotNull(mongoID);

    }

    @Then("StatusCode is (\\d{3})")
    public void statusCode_is(String status) {
        int stat = Integer.valueOf(status);
        assertEquals(stat, response.getStatusCode());
    }



}
