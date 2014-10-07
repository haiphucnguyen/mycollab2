package com.esofthead.mycollab.rest.server.resource;

import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Rule;

import com.esofthead.mycollab.configuration.SiteConfiguration;
import com.esofthead.mycollab.test.rule.DbUnitInitializerRule;
import com.esofthead.mycollab.test.rule.EssentialInitRule;
import com.esofthead.mycollab.test.rule.ServerLifecycleRule;
import com.jayway.restassured.RestAssured;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.5.2
 *
 */
public class RestServiceTest {
	static {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = 8080;
		RestAssured.basePath = "/api";
	}

	@BeforeClass
	public static void setUp() {
		SiteConfiguration.loadInstance(8080);
	}

	@ClassRule
	public static EssentialInitRule essentialRule = new EssentialInitRule();

	@Rule
	public ServerLifecycleRule serverRule = new ServerLifecycleRule();

	@Rule
	public DbUnitInitializerRule dbRule = new DbUnitInitializerRule();
}
