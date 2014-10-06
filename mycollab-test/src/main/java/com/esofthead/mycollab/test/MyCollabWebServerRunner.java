package com.esofthead.mycollab.test;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import org.apache.log4j.PropertyConfigurator;
import org.joda.time.DateTimeZone;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.Statement;

import com.esofthead.mycollab.test.module.db.DbUnitModule;
import com.esofthead.mycollab.test.module.server.JettyModule;
import com.esofthead.mycollab.test.service.ServiceTest;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.5.2
 *
 */
public class MyCollabWebServerRunner extends BlockJUnit4ClassRunner {

	private List<MyCollabTestModule> testModules;

	public MyCollabWebServerRunner(Class<?> klass) throws InitializationError {
		super(klass);
	}

	@Override
	protected Statement withBeforeClasses(Statement statement) {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		DateTimeZone.setDefault(DateTimeZone.UTC);

		URL resourceUrl = ServiceTest.class.getClassLoader().getResource(
				"log4j-test.properties");
		if (resourceUrl != null) {
			PropertyConfigurator.configure(resourceUrl);
		}
		return super.withBeforeClasses(statement);
	}

	@Override
	protected Statement methodInvoker(FrameworkMethod method, Object test) {
		preInvokeMethod(method);
		Statement methodInvoker = super.methodInvoker(method, test);
		postInvokeMethod(method);
		return methodInvoker;
	}

	private void preInvokeMethod(FrameworkMethod method) {
		testModules = new ArrayList<MyCollabTestModule>();

		WebServer serverAnno = method.getAnnotation(WebServer.class);
		if (serverAnno != null) {
			JettyModule jettyModule = new JettyModule();
			testModules.add(jettyModule);
		}

		DataSet dataSetAnno = method.getAnnotation(DataSet.class);
		if (dataSetAnno != null) {
			DbUnitModule dbModule = new DbUnitModule();
			dbModule.setHost(this.getTestClass().getJavaClass());
			testModules.add(dbModule);
		}

		for (MyCollabTestModule module : testModules) {
			module.setUp();
		}
	}

	private void postInvokeMethod(FrameworkMethod method) {
		for (MyCollabTestModule module : testModules) {
			module.tearDown();
		}
	}
}
