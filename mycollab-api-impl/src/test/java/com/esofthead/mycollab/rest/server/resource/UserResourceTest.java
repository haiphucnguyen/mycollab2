package com.esofthead.mycollab.rest.server.resource;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import org.junit.Test;

import com.esofthead.mycollab.test.DataSet;
import com.esofthead.mycollab.test.WebServer;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.parsing.Parser;

public class UserResourceTest extends RestServiceTest {

	@WebServer
	@DataSet
	@Test
	public void testGetSubDomainsOfUsersSuccessfully() {
		String expected = String.format("[\"%s\",\"%s\"]", "abc", "xyz");
		System.out.println(expected);
		given().contentType(ContentType.TEXT).when()
				.get("/user/subdomains/hai79").then().statusCode(200).and()
				.contentType(ContentType.JSON)
				.header("Access-Control-Allow-Origin", "*").and()
				.body(equalTo(expected));
	}

	@WebServer
	@DataSet
	@Test
	public void testGetEmptySubDomainsOfUsers() {
		given().contentType(ContentType.TEXT).when()
				.get("/user/subdomains/linhduong").then().statusCode(200).and()
				.contentType(ContentType.JSON)
				.header("Access-Control-Allow-Origin", "*").and()
				.parser(ContentType.JSON.name(), Parser.JSON).assertThat()
				.body(equalTo("[]"));
	}
}
