package com.esofthead.mycollab.rest;

import org.json.JSONException;
import org.junit.runner.RunWith;
import org.restlet.data.Form;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.esofthead.mycollab.module.billing.ExistingDomainRegisterException;
import com.esofthead.mycollab.module.billing.ExistingUserRegisterException;
import com.esofthead.mycollab.rest.server.resource.SignupResource;
import com.esofthead.mycollab.test.DataSet;
import com.esofthead.mycollab.test.EngroupClassRunner;
import com.esofthead.mycollab.test.ServiceTest;

@RunWith(EngroupClassRunner.class)
@ContextConfiguration(locations = { "classpath:META-INF/spring/service-test-context.xml" })
public class SignupTest extends ServiceTest {

	@Autowired
	private SignupResource restUserResource;

	@org.junit.Test(expected = ExistingUserRegisterException.class)
	@DataSet
	public void testSignupFailDuetoExistingUserRegister() throws JSONException {
		Form form = new Form();
		form.set("subdomain", "esofthead1");
		form.set("username", "admin");
		form.set("email", "baohan@esofthead.com");
		form.set("password", "admin123");
		form.set("timezone", "1");
		form.set("planId", "1");

		restUserResource.doPost(form);
	}

	@org.junit.Test(expected = ExistingDomainRegisterException.class)
	@DataSet
	public void testSignupFailDuetoExistingDomainRegister()
			throws JSONException {
		System.out.println(restUserResource.getClass());
		Form form = new Form();
		form.set("subdomain", "esofthead");
		form.set("username", "hainguyen");
		form.set("email", "baohan@esofthead.com");
		form.set("password", "admin123");
		form.set("timezone", "1");
		form.set("planId", "1");

		restUserResource.doPost(form);
	}
	
	@org.junit.Test
	@DataSet
	public void testSignupSuccessfully()
			throws JSONException {
		System.out.println(restUserResource.getClass());
		Form form = new Form();
		form.set("subdomain", "esofthead1");
		form.set("username", "hainguyen1");
		form.set("email", "baohan@esofthead.com");
		form.set("password", "admin123");
		form.set("timezone", "1");
		form.set("planId", "1");

		restUserResource.doPost(form);
	}
}
