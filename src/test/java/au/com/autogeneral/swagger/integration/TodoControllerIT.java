package au.com.autogeneral.swagger.integration;

import au.com.autogeneral.swagger.error.ValidationAdvice;
import au.com.autogeneral.swagger.todo.TodoController;
import au.com.autogeneral.swagger.todo.TodoRepository;
import org.json.JSONException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.util.UriComponentsBuilder;

@RunWith(SpringRunner.class)
@EnableAutoConfiguration

@ContextConfiguration(classes = {
        ValidationAdvice.class, TodoRepository.class})
@SpringBootTest(classes = TodoController.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TodoControllerIT {

    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();

    HttpHeaders headers = new HttpHeaders();

    @Test
    public void testGetNotFound() throws JSONException {

        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(createURLWithPort("/todo/10000"));
        String uri = builder.build(false).toUriString();
        ResponseEntity<String> response = restTemplate.exchange(
                uri,
                HttpMethod.GET, entity, String.class);

        String expected = "{\n" +
                "  \"details\": [\n" +
                "    {\n" +
                "      \"message\": \"Item with 9 not found\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"name\": \"NotFoundError\"\n" +
                "}";

        Assert.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        String obj = response.getBody();
        JSONAssert.assertEquals(expected, obj, false);
    }


    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}