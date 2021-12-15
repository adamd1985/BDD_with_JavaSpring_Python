package org.adamd.tutorials;

import io.cucumber.java.sl.In;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import static io.cucumber.spring.CucumberTestContext.SCOPE_CUCUMBER_GLUE;

/**
 * Spring mock httptemplate for REST integration tests.
 */
@Component
@Scope(SCOPE_CUCUMBER_GLUE)
public class TestHttpClient {

    private final String URL = "http://localhost";
    private final String ENDPOINT = "/calculation/addition/";

    @LocalServerPort
    private int port;
    private final RestTemplate restTemplate = new RestTemplate();


    private String serverEndpoint(String user) {
        StringBuffer sb = new StringBuffer();
        sb.append(URL);
        sb.append(":");
        sb.append(port);
        sb.append(ENDPOINT);
        if (user != null) {
            sb.append(user);
        }
        return sb.toString();
    }

    public int post(final String userName, final int num1, final int num2) {
        return restTemplate.postForEntity(serverEndpoint(userName), RequestTO.builder().num1(num1).num2(num2).build(), Void.class).getStatusCodeValue();
    }

    public Integer getResults(final String userName) {
        return restTemplate.getForEntity(serverEndpoint(userName), Integer.class).getBody();
    }

    public Integer getheartBeat() {
        return restTemplate.getForEntity(serverEndpoint(null), Integer.class).getBody();
    }
}
