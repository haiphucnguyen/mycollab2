package com.esofthead.mycollab.schedule.email;

import org.junit.Before;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.quartz.JobExecutionContext;

import com.esofthead.mycollab.configuration.SiteConfiguration;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.3.0
 * 
 */
public class GenericJobTest {

	@Mock
	protected JobExecutionContext context;

	@Before
	public void setUp() {
		SiteConfiguration.loadInstance(8080);
		MockitoAnnotations.initMocks(this);
	}
}
