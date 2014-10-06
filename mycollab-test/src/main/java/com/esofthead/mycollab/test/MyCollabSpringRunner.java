/**
 * This file is part of mycollab-test.
 *
 * mycollab-test is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-test is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-test.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.test;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import org.apache.log4j.PropertyConfigurator;
import org.joda.time.DateTimeZone;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.Statement;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.esofthead.mycollab.test.module.db.DbUnitModule;
import com.esofthead.mycollab.test.service.ServiceTest;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 * 
 */
public class MyCollabSpringRunner extends SpringJUnit4ClassRunner {

	private List<MyCollabTestModule> testModules;

	public MyCollabSpringRunner(Class<?> clazz) throws InitializationError {
		super(clazz);
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
