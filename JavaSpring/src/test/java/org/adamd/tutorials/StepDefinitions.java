package org.adamd.tutorials;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.java.Log;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Log
public class StepDefinitions {
    private final List<String> users = new ArrayList<>();
    private final List<Integer> results = new ArrayList<>();

    @Autowired
    private TestHttpClient testHttpClient;

    @Given("^at least (\\d+) calculation service is running$")
    public void at_least_some_calculation_service_is_running(final String userName) {
        Integer resp = testHttpClient.getheartBeat();
        assertThat(resp).isNotNull().isEqualTo(1);
    }

    @Given("^user with name (\\S+)$")
    public void user_with_name(final String userName) {
        users.add(userName);
    }

    @Given("^there are (\\d+) more result\\(s\\)$")
    public void given_there_are_more_result(final int nResults) {
        Integer resp = testHttpClient.getResults(users.get(0));
        if (nResults > 0) {
            assertThat(resp).isNotNull();
        } else {
            assertThat(resp).isNull();
        }
    }

    @When("^user (\\S+) starts an addition of (-?\\d+) and (-?\\d+)$")
    public void user_starts_an_addition(final String userName, final int num1, final int num2) {
        Integer resp = testHttpClient.post(users.get(users.lastIndexOf(userName)), num1, num2);
        assertThat(resp).isNotNull().isEqualTo(200);
    }

    @When("^user (\\S+) downloads the result$")
    public void user_downloads_result(final String userName) {
        Integer resp = testHttpClient.getResults(users.get(users.lastIndexOf(userName)));
        if (resp != null) {
            results.add(resp);
        }
    }

    @Then("^(\\d+) result\\(s\\) of (\\d+) is downloaded$")
    public void results_downloaded(final int quantity, final int answer) {
        assertThat(results.size()).isEqualTo(quantity);
        if (quantity > 0) {
            assertThat(results).contains(answer);
        }
    }
}
