package com.esofthead.mycollab.rest.server.resource;

import org.jboss.resteasy.plugins.server.tjws.TJWSEmbeddedJaxrsServer;
import org.jboss.resteasy.plugins.spring.SpringBeanProcessor;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.test.context.ContextConfiguration;

import com.esofthead.mycollab.test.DataSet;
import com.esofthead.mycollab.test.MyCollabClassRunner;
import com.esofthead.mycollab.test.service.ServiceTest;

@RunWith(MyCollabClassRunner.class)
@ContextConfiguration(locations = { "classpath:META-INF/spring/service-context-test.xml" })
public class UserResourceTest extends ServiceTest {

	private static TJWSEmbeddedJaxrsServer tjws;

	@BeforeClass
	public static void setup() {
		tjws = new TJWSEmbeddedJaxrsServer();
		tjws.setPort(8080);
		tjws.start();

		SpringBeanProcessor processor = new SpringBeanProcessor(
				tjws.getDeployment());
		ConfigurableBeanFactory factory = new DefaultListableBeanFactory();
//	      factory.addBeanPostProcessor(processor.);
	}

	public static void tearDown() {
		tjws.stop();
	}

	@Test
	@DataSet
	public void testGetSubDomainOfUsers() {

	}
}
