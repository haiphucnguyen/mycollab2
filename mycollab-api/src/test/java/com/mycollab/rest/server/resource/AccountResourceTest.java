package com.mycollab.rest.server.resource;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.mycollab.test.DataSet;
import com.mycollab.test.WebServer;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class AccountResourceTest extends RestServiceTest {

    @WebServer
    @DataSet
    @Test
    public void testSignupSuccessfully() {
        RestAssured.given().contentType(ContentType.URLENC).formParam("subDomain", "xyz")
                .formParam("planId", "1").formParam("password", "1a2b3c4d5e")
                .formParam("email", "baohan@esofthead.com")
                .formParam("timezoneId", "3")
                .formParam("isEmailVerified", false).when()
                .post("account/signup").then().assertThat().statusCode(200)
                .and().header("Access-Control-Allow-Origin", "*").and()
                .body(Matchers.equalTo("http://localhost:8080/mycollab-web/"));
    }

    @WebServer
    @DataSet
    @Test
    public void testSignupWithExistingDomain() {
        RestAssured.given().contentType(ContentType.URLENC).formParam("subDomain", "abc")
                .formParam("planId", "1").formParam("password", "1a2b3c4d5e")
                .formParam("email", "baohan@esofthead.com")
                .formParam("timezoneId", "3")
                .formParam("isEmailVerified", false).when()
                .post("account/signup").then().assertThat().statusCode(400)
                .and().header("Access-Control-Allow-Origin", "*");
    }
}
