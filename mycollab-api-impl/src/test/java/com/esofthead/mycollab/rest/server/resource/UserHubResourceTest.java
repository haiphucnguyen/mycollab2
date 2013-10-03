package com.esofthead.mycollab.rest.server.resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.restlet.Component;
import org.restlet.data.Form;
import org.restlet.data.Protocol;
import org.restlet.ext.spring.SpringComponent;
import org.restlet.resource.ClientResource;
import org.springframework.test.context.ContextConfiguration;

import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.test.DataSet;
import com.esofthead.mycollab.test.MyCollabClassRunner;
import com.esofthead.mycollab.test.service.ServiceTest;

@RunWith(MyCollabClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:META-INF/spring/service-context-test.xml",
		"classpath:META-INF/spring-rest/rest-context.xml" })
public class UserHubResourceTest extends ServiceTest {

	private Component component;

	@Before
	public void setup() throws Exception {
		component = ApplicationContextUtil.getSpringBean(SpringComponent.class);
		// Add a new HTTP server listening on port 8182.
		component.getServers().add(Protocol.HTTP, 8182);

		// Start the component.
		component.start();
	}

	@After
	public void tearDown() throws Exception {
		component.stop();
	}

	@Test
	@DataSet
	public void testGetSubDomainsOfUser() {
		ClientResource clientResource = new ClientResource(
				"http://localhost:8182/signup");
		UserHubResource testResource = clientResource
				.wrap(UserHubResource.class);

		Form form = new Form();
		form.set("subdomain", "esofthead6");
		form.set("planId", "1");
		form.set("username", "hainguyen6");
		form.set("password", "123");
		form.set("email", "hainguyen@esofthead6.com");
		form.set("timezoneId", "10");
		form.set("firstname", "Nguyen");
		form.set("lastname", "Hai");

		String result = testResource.signup(form);
		if (result != null) {
			System.out.println(result);
		}
	}
}
