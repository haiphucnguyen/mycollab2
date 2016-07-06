package com.mycollab.rest.server.resource;

import static com.jayway.restassured.RestAssured.given;

import com.jayway.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.Test;

import com.mycollab.test.DataSet;
import com.mycollab.test.WebServer;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.parsing.Parser;

public class UserResourceTest extends RestServiceTest {

	@WebServer
	@DataSet
	@Test
	public void testGetSubDomainsOfUsersSuccessfully() {
		String expected = String.format("[\"%s\",\"%s\"]", "abc", "xyz");
		RestAssured.given().contentType(ContentType.TEXT).when()
				.get("/user/subdomains/hai79").then().statusCode(200).and()
				.contentType(ContentType.JSON)
				.header("Access-Control-Allow-Origin", "*").and()
				.body(Matchers.equalTo(expected));
	}

	@WebServer
	@DataSet
	@Test
	public void testGetEmptySubDomainsOfUsers() {
		RestAssured.given().contentType(ContentType.TEXT).when()
				.get("/user/subdomains/linhduong").then().statusCode(200).and()
				.contentType(ContentType.JSON)
				.header("Access-Control-Allow-Origin", "*").and()
				.parser(ContentType.JSON.name(), Parser.JSON).assertThat()
				.body(Matchers.equalTo("[]"));
	}
}
