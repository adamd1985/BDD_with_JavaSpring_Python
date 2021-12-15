package org.adamd.tutorials;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features",
        plugin = {"pretty", "html:target/cucumber/test"},
        glue = {"org.adamd.tutorials", "org.adamd.tutorials.bagcommons"})
public class IntegrationTest {
}
