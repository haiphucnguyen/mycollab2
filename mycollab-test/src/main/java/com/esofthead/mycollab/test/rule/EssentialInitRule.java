package com.esofthead.mycollab.test.rule;

import java.net.URL;
import java.util.TimeZone;

import org.apache.log4j.PropertyConfigurator;
import org.joda.time.DateTimeZone;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import com.esofthead.mycollab.test.service.IntergrationServiceTest;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.5.2
 *
 */
public class EssentialInitRule implements TestRule {

	@Override
	public Statement apply(Statement base, Description description) {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		DateTimeZone.setDefault(DateTimeZone.UTC);

		URL resourceUrl = IntergrationServiceTest.class.getClassLoader().getResource(
				"log4j-test.properties");
		if (resourceUrl != null) {
			PropertyConfigurator.configure(resourceUrl);
		}
		return base;
	}

}
