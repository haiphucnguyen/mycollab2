package com.esofthead.mycollab.rest.server.resource;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;

import com.esofthead.mycollab.test.DataSet;
import com.esofthead.mycollab.test.WebServer;
import com.jayway.restassured.http.ContentType;

public class AccountResourceTest extends RestServiceTest {

	@WebServer
	@DataSet
	@Test
	public void testSignupSuccessfully() {
		given().contentType(ContentType.URLENC).formParam("subdomain", "xyz")
				.formParam("planId", "1").formParam("password", "1a2b3c4d5e")
				.formParam("email", "baohan@esofthead.com")
				.formParam("timezoneId", "3")
				.formParam("isEmailVerified", false).when()
				.post("account/signup").then().assertThat().statusCode(200)
				.and().header("Access-Control-Allow-Origin", "*").and()
				.body(equalTo("http://localhost:8080/mycollab-web/"));
	}

	@WebServer
	@DataSet
	@Test
	public void testSignupWithExistingDomain() {
		given().contentType(ContentType.URLENC).formParam("subdomain", "abc")
				.formParam("planId", "1").formParam("password", "1a2b3c4d5e")
				.formParam("email", "baohan@esofthead.com")
				.formParam("timezoneId", "3")
				.formParam("isEmailVerified", false).when()
				.post("account/signup").then().assertThat().statusCode(400)
				.and().header("Access-Control-Allow-Origin", "*");
	}
}
