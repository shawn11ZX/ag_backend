package au.com.autogeneral.swagger.integration;

import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.web.util.UriComponentsBuilder;

public class HttpRequestHelper {
    final private int port;
    final private TestRestTemplate restTemplate = new TestRestTemplate();

    public HttpRequestHelper(int port) {
        this.port = port;
    }



    public ResponseEntity<String> requestHttp(String postBody, String restUri, HttpMethod method) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<String> entity = new HttpEntity<>(postBody, headers);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(createURLWithPort(restUri));
        String uri = builder.build(false).toUriString();
        return restTemplate.exchange(
                uri,
                method, entity, String.class);
    }


    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}
