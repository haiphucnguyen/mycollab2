package com.esofthead.mycollab.module.crm.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import com.esofthead.mycollab.core.arguments.DateTimeSearchField;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.module.crm.domain.criteria.EventSearchCriteria;
import com.esofthead.mycollab.test.DataSet;
import com.esofthead.mycollab.test.MyCollabClassRunner;
import com.esofthead.mycollab.test.service.ServiceTest;

@RunWith(MyCollabClassRunner.class)
public class EventServiceTest extends ServiceTest {

	@Autowired
	protected EventService eventService;

	@DataSet
	@Test
	public void testSearchByCriteria() throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date startDate = format.parse("2012-11-11 00:00:00");
		Date endDate = format.parse("2012-11-15 00:00:00");
		EventSearchCriteria criteria = new EventSearchCriteria();
		criteria.setStartDate(new DateTimeSearchField(SearchField.AND,
				DateTimeSearchField.GREATERTHANEQUAL, startDate));
		criteria.setEndDate(new DateTimeSearchField(SearchField.AND,
				DateTimeSearchField.LESSTHANEQUAL, endDate));
		criteria.setSaccountid(new NumberSearchField(1));

		List list = eventService
				.findPagableListByCriteria(new SearchRequest<EventSearchCriteria>(
						criteria, 0, Integer.MAX_VALUE));
		Assert.assertEquals(1, list.size());

	}
}
