package au.com.autogeneral.swagger.integration;

import au.com.autogeneral.swagger.error.ValidationAdvice;
import au.com.autogeneral.swagger.validation.ValidateBracketsController;
import org.json.JSONException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.util.UriComponentsBuilder;

@RunWith(SpringRunner.class)
@EnableAutoConfiguration
@SpringBootTest(classes = ValidateBracketsController.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = {
        ValidationAdvice.class })
public class ValidateBracketsControllerIT {

    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();

    HttpHeaders headers = new HttpHeaders();

    @Test
    public void testGetValid() throws JSONException {

        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(createURLWithPort("/tasks/validateBrackets"))
                .queryParam("input", "[(]");
        String uri = builder.build(false).toUriString();
        ResponseEntity<String> response = restTemplate.exchange(
                uri,
                HttpMethod.GET, entity, String.class);

        String expected = "{\n" +
                "  \"input\": \"[(]\",\n" +
                "  \"isBalanced\": false\n" +
                "}";

        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        String obj = response.getBody();
        JSONAssert.assertEquals(expected, obj, false);
    }

    @Test
    public void testInvalid() throws JSONException {

        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(createURLWithPort("/tasks/validateBrackets"))
                .queryParam("input", "");
        String uri = builder.build(false).toUriString();
        ResponseEntity<String> response = restTemplate.exchange(
                uri,
                HttpMethod.GET, entity, String.class);

        String expected = "{\n" +
                "  \"details\": [\n" +
                "    {\n" +
                "      \"location\": \"params\",\n" +
                "      \"param\": \"text\",\n" +
                "      \"msg\": \"Must be between 1 and 50 chars long\",\n" +
                "      \"value\": \"\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"name\": \"ValidationError\"\n" +
                "}";

        Assert.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        String obj = response.getBody();
        JSONAssert.assertEquals(expected, obj, false);
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}