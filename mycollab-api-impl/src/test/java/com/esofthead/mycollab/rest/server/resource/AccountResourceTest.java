package com.esofthead.mycollab.rest.server.resource;

import static com.jayway.restassured.RestAssured.get;

import org.junit.BeforeClass;
import org.junit.Test;

import com.esofthead.mycollab.configuration.SiteConfiguration;
import com.esofthead.mycollab.test.DataSet;
import com.esofthead.mycollab.test.WebServer;

public class AccountResourceTest extends RestServiceTest {

	@BeforeClass
	public static void setUp() {
		SiteConfiguration.loadInstance(8080);
	}

	@WebServer
	@DataSet
	@Test
	public void testSignup() {
		System.out.println("A");
		get("/account/signup").then().assertThat().statusCode(200);
		System.out.println("B");
	}
}
