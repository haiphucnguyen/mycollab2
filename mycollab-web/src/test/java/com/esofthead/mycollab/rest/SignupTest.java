package com.esofthead.mycollab.rest;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.restlet.data.Form;
import org.restlet.ext.spring.SpringComponent;
import org.restlet.resource.ClientResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.esofthead.mycollab.module.billing.ExistingUserRegisterException;
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
	public void setUp() {
		
	}

	@org.junit.Test(expected = ExistingUserRegisterException.class)
	@DataSet
	public void testSignupFailDuetoExistingUserRegister() throws Exception {
		restServer.start();
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
		testResource.signup(form);
		// restUserResource.doPost(form);
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
