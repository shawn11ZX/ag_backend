package au.com.autogeneral.swagger.integration;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.Customization;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.skyscreamer.jsonassert.JSONParser;
import org.skyscreamer.jsonassert.comparator.CustomComparator;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class TodoControllerIT {

    @LocalServerPort
    private int port;

    private HttpRequestHelper helper;
    @Before
    public void initClass() {
        helper = new HttpRequestHelper(port);
    }



    @Test
    public void testGetNotFound() throws JSONException {

        ResponseEntity<String> response = helper.requestHttp(null, "/todo/9", HttpMethod.GET);

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

    @Test
    public void testGetOK() throws JSONException {

        ResponseEntity<String> responseAdd = helper.requestHttp("{\n" +
                "  \"text\": \"Uulwi ifis halahs gag erh'ongg w'ssh.\"\n" +
                "}", "/todo/", HttpMethod.POST);
        JSONObject todoItem = (JSONObject)JSONParser.parseJSON(responseAdd.getBody()) ;
        Integer id = todoItem.getInt("id");

        ResponseEntity<String> response = helper.requestHttp("", "/todo/" + id, HttpMethod.GET);

        String expected = "{\n" +
                "  \"id\": 42,\n" +
                "  \"text\": \"Uulwi ifis halahs gag erh'ongg w'ssh.\",\n" +
                "  \"isCompleted\": false,\n" +
                "  \"createdAt\": \"2017-10-13T01:50:58.735Z\"\n" +
                "}";

        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        String obj = response.getBody();
        JSONAssert.assertEquals(expected, obj, new CustomComparator(JSONCompareMode.LENIENT,
                new Customization("id", (o1, o2) -> true), new Customization("createdAt", (o1, o2) -> true)));
    }

    @Test
    public void testPostValid() throws JSONException {

        ResponseEntity<String> response = helper.requestHttp("{\n" +
                "  \"text\": \"Uulwi ifis halahs gag erh'ongg w'ssh.\"\n" +
                "}", "/todo/", HttpMethod.POST);

        String expected = "{\n" +
                "  \"id\": 42,\n" +
                "  \"text\": \"Uulwi ifis halahs gag erh'ongg w'ssh.\",\n" +
                "  \"isCompleted\": false,\n" +
                "  \"createdAt\": \"2017-10-13T01:50:58.735Z\"\n" +
                "}";

        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        String obj = response.getBody();
        JSONAssert.assertEquals(expected, obj, new CustomComparator(JSONCompareMode.LENIENT,
                new Customization("id", (o1, o2) -> true), new Customization("createdAt", (o1, o2) -> true)));
    }

    @Test
    public void testPostFail() throws JSONException {

        String postBody = "{\n" +
                "  \"text\": \"\"\n" +
                "}";

        String restUri = "/todo/";
        HttpMethod method = HttpMethod.POST;

        ResponseEntity<String> response = helper.requestHttp(postBody, restUri, method);

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
        JSONAssert.assertEquals(expected, obj, new CustomComparator(JSONCompareMode.LENIENT,
                new Customization("id", (o1, o2) -> true), new Customization("createdAt", (o1, o2) -> true)));
    }

    @Test
    public void testPatchOK() throws JSONException {

        ResponseEntity<String> responseAdd = helper.requestHttp("{\n" +
                "  \"text\": \"Uulwi ifis halahs gag erh'ongg w'ssh.\"\n" +
                "}", "/todo/", HttpMethod.POST);
        JSONObject todoItem = (JSONObject)JSONParser.parseJSON(responseAdd.getBody()) ;
        Integer id = todoItem.getInt("id");
        String date = todoItem.getString("createdAt");
        ResponseEntity<String> response = helper.requestHttp("{\n" +
                "  \"text\": \"aabbccdd\",\n" +
                "  \"isCompleted\": true\n" +
                "}", "/todo/" + id, HttpMethod.PATCH);

        String expected = "{\n" +
                "  \"id\": " + id + ",\n" +
                "  \"text\": \"aabbccdd\",\n" +
                "  \"isCompleted\": true,\n" +
                "  \"createdAt\": \"" +date+"\"\n" +
                "}";

        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        String obj = response.getBody();
        JSONAssert.assertEquals(expected, obj, new CustomComparator(JSONCompareMode.LENIENT));
    }

    @Test
    public void testPatchInvalid() throws JSONException {

        ResponseEntity<String> responseAdd = helper.requestHttp("{\n" +
                "  \"text\": \"Uulwi ifis halahs gag erh'ongg w'ssh.\"\n" +
                "}", "/todo/", HttpMethod.POST);
        JSONObject todoItem = (JSONObject)JSONParser.parseJSON(responseAdd.getBody()) ;
        Integer id = todoItem.getInt("id");
        String date = todoItem.getString("createdAt");
        ResponseEntity<String> response = helper.requestHttp("{\n" +
                "  \"text\": \"\",\n" +
                "  \"isCompleted\": true\n" +
                "}", "/todo/" + id, HttpMethod.PATCH);

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
        JSONAssert.assertEquals(expected, obj, new CustomComparator(JSONCompareMode.LENIENT));
    }

    @Test
    public void testPatchNotFound() throws JSONException {


        ResponseEntity<String> response = helper.requestHttp("{\n" +
                "  \"text\": \"aaa\",\n" +
                "  \"isCompleted\": true\n" +
                "}", "/todo/9", HttpMethod.PATCH);

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
        JSONAssert.assertEquals(expected, obj, new CustomComparator(JSONCompareMode.LENIENT));
    }


}