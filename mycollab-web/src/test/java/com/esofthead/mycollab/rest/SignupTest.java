package com.esofthead.mycollab.rest;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.restlet.data.Form;
import org.restlet.ext.spring.SpringComponent;
import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.esofthead.mycollab.rest.server.resource.UserHubResource;
import com.esofthead.mycollab.test.DataSet;
import com.esofthead.mycollab.test.EngroupClassRunner;
import com.esofthead.mycollab.test.ServiceTest;

@RunWith(EngroupClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:META-INF/spring/service-context-test.xml",
		"classpath:META-INF/spring/rest-server-context-test.xml" })
public class SignupTest extends ServiceTest {

	@Autowired
	private UserHubResource restUserResource;

	@Autowired
	private SpringComponent restServer;

	@Before
	public void setUp() throws Exception {
		restServer.start();
	}

	@After
	public void tearDown() throws Exception {
		restServer.stop();
	}

	@DataSet
	@org.junit.Test
	public void testSignupFailDuetoExistingUserRegister() throws Exception {

		final Form form = new Form();
		form.set("subdomain", "esofthead1");
		form.set("username", "admin");
		form.set("email", "baohan@esofthead.com");
		form.set("password", "admin123");
		form.set("timezone", "1");
		form.set("planId", "1");

		ClientResource clientResource = new ClientResource(
				"http://localhost:3000/signup");
		UserHubResource testResource = clientResource
				.wrap(UserHubResource.class);

		try {
			testResource.signup(form);
		} catch (ResourceException e) {
			System.out.println(clientResource.getResponse().getEntity()
					.getText());
		}
	}

	// @org.junit.Test(expected = SubdomainExistedException.class)
	// @DataSet
	// public void testSignupFailDuetoExistingDomainRegister()
	// throws JSONException {
	// final Form form = new Form();
	// form.set("subdomain", "esofthead");
	// form.set("username", "hainguyen");
	// form.set("email", "baohan@esofthead.com");
	// form.set("password", "admin123");
	// form.set("timezone", "1");
	// form.set("planId", "1");
	//
	// restUserResource.doPost(form);
	// }
	//
	// @org.junit.Test
	// @DataSet
	// public void testSignupSuccessfully() throws JSONException {
	// final Form form = new Form();
	// form.set("subdomain", "esofthead1");
	// form.set("username", "hainguyen1");
	// form.set("email", "baohan@esofthead.com");
	// form.set("password", "admin123");
	// form.set("timezone", "1");
	// form.set("planId", "1");
	//
	// restUserResource.doPost(form);
	// }
}
