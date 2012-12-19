package com.esofthead.mycollab.module.crm.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.esofthead.mycollab.core.arguments.DateTimeSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.module.crm.domain.criteria.EventSearchCriteria;
import com.esofthead.mycollab.test.DataSet;
import com.esofthead.mycollab.test.EngroupClassRunner;

@RunWith(EngroupClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:META-INF/spring/audit-context.xml",
		"classpath:META-INF/spring/file-context.xml",
		"classpath:META-INF/spring/crm-context.xml",
		"classpath:META-INF/spring/crm-service-test-context.xml",
		"classpath:META-INF/spring/datasource-test-context.xml" })
public class EventServiceTest {
	@Autowired
	protected EventService eventService;

	@DataSet
	@Test
	public void testSearchByCriteria() {
		try {
			SimpleDateFormat format = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			Date startDate = format.parse("2012-11-11 00:00:00");
			Date endDate = format.parse("2012-11-15 00:00:00");
			EventSearchCriteria criteria = new EventSearchCriteria();
			criteria.setStartDate(new DateTimeSearchField(SearchField.AND,
					DateTimeSearchField.GREATERTHANEQUAL, startDate));
			criteria.setEndDate(new DateTimeSearchField(SearchField.AND,
					DateTimeSearchField.LESSTHANEQUAL, endDate));

			List list = eventService
					.findPagableListByCriteria(new SearchRequest<EventSearchCriteria>(
							criteria, 0, Integer.MAX_VALUE));
			Assert.assertEquals(1, list.size());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
