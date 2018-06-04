package com.benczykuadama.personmongo.cucumber;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty"}, features = "classpath:cucumber/postuser.feature")
@Profile("test")
@ActiveProfiles("test")
public class RunCucumberTest {

}