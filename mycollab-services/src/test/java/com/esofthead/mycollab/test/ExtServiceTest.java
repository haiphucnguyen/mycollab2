package com.esofthead.mycollab.test;

import org.mockito.Mock;

import com.esofthead.mycollab.module.billing.service.BillingPlanCheckerService;
import com.esofthead.mycollab.test.service.ServiceTest;

public class ExtServiceTest extends ServiceTest {

	@Mock
	protected BillingPlanCheckerService billingPlanCheckerService;
}
