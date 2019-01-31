package au.com.autogeneral.swagger.integration;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;


/**
 * Test helper shared among @TodoControllerIT
 *
 *  @author Shawn Chang
 */
public class HttpRequestHelper {
    final private int port;
    final private TestRestTemplate restTemplate = new TestRestTemplate();
    final private RestTemplate patchRestTemplate;
    public HttpRequestHelper(int port) {
        this.port = port;

        patchRestTemplate = restTemplate.getRestTemplate();
        HttpClient httpClient = HttpClientBuilder.create().build();
        patchRestTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory(httpClient));
    }



    public ResponseEntity<String> requestHttp(String postBody, String restUri, HttpMethod method) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<String> entity = new HttpEntity<>(postBody, headers);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(createURLWithPort(restUri));
        String uri = builder.build(false).toUriString();
        return patchRestTemplate.exchange(
                uri,
                method, entity, String.class);
    }


    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}
