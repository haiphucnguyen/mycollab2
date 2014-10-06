package com.esofthead.mycollab.rest.server.resource;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.esofthead.mycollab.configuration.SiteConfiguration;
import com.esofthead.mycollab.test.MyCollabWebServerRunner;
import com.esofthead.mycollab.test.WebServer;

@RunWith(MyCollabWebServerRunner.class)
public class AccountResourceTest {

	@BeforeClass
	public static void setUp() {
		SiteConfiguration.loadInstance(8080);
	}

	@WebServer
	@Test
	public void testSignup() {
		System.out.println("A");
	}
}
