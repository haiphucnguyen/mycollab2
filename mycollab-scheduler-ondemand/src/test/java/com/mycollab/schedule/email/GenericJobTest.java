package com.mycollab.schedule.email;

import org.junit.Before;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.quartz.JobExecutionContext;

import com.mycollab.module.mail.service.IContentGenerator;
import com.mycollab.module.mail.service.ExtMailService;
import com.mycollab.module.user.service.BillingAccountService;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.3.0
 * 
 */
public class GenericJobTest {

	@Mock
	protected JobExecutionContext context;

	@Mock
	protected IContentGenerator contentGenerator;

	@Mock
	protected ExtMailService extMailService;

	@Mock
	protected BillingAccountService billingAccountService;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
}
