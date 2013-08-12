package com.esofthead.mycollab.common.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.esofthead.mycollab.common.domain.criteria.RelayEmailNotificationSearchCriteria;
import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.test.DataSet;
import com.esofthead.mycollab.test.MyCollabClassRunner;

//@RunWith(MyCollabClassRunner.class)
//@ContextConfiguration(locations = { "classpath:META-INF/spring/service-context-test.xml" })
public class RelayEmailNotificationServiceTest {
	@Autowired
	protected RelayEmailNotificationService relayEmailNotificationService;

//	@Test
//	@DataSet
	public void testRemoveItems() {
		RelayEmailNotificationSearchCriteria criteria = new RelayEmailNotificationSearchCriteria();
		List items = relayEmailNotificationService
				.findPagableListByCriteria(new SearchRequest<RelayEmailNotificationSearchCriteria>(
						criteria, 0, Integer.MAX_VALUE));
		Assert.assertEquals(1, items.size());

	}
}
