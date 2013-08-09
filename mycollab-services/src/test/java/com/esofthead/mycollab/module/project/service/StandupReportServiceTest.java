package com.esofthead.mycollab.module.project.service;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.esofthead.mycollab.common.domain.GroupItem;
import com.esofthead.mycollab.core.arguments.DateSearchField;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.RangeDateSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.core.utils.BeanUtility;
import com.esofthead.mycollab.module.project.domain.criteria.StandupReportSearchCriteria;
import com.esofthead.mycollab.test.DataSet;
import com.esofthead.mycollab.test.EngroupClassRunner;
import com.esofthead.mycollab.test.service.ServiceTest;

@RunWith(EngroupClassRunner.class)
@ContextConfiguration(locations = { "classpath:META-INF/spring/service-context-test.xml" })
public class StandupReportServiceTest extends ServiceTest {
	@Autowired
	protected StandupReportService reportService;

	@Test
	@DataSet
	public void gatherStandupList() {
		StandupReportSearchCriteria criteria = new StandupReportSearchCriteria();
		criteria.setProjectId(new NumberSearchField(1));
		criteria.setLogBy(new StringSearchField(SearchField.AND, "hainguyen"));
		Date d = new GregorianCalendar(2013, 2, 13).getTime();
		criteria.setOnDate(new DateSearchField(SearchField.AND, d));
		List reports = reportService
				.findPagableListByCriteria(new SearchRequest<StandupReportSearchCriteria>(
						criteria, 0, Integer.MAX_VALUE));
		Assert.assertEquals(1, reports.size());
	}

	@Test
	@DataSet
	public void testGetListCount() {
		StandupReportSearchCriteria criteria = new StandupReportSearchCriteria();
		criteria.setProjectId(new NumberSearchField(1));
		Date from = new GregorianCalendar(2013, 2, 1).getTime();
		Date to = new GregorianCalendar(2013, 2, 31).getTime();
		criteria.setReportDateRange(new RangeDateSearchField(from, to));
		List<GroupItem> reportsCount = reportService.getReportsCount(criteria);

		Assert.assertEquals(2, reportsCount.size());
		for (GroupItem item:reportsCount) {
			System.out.println(BeanUtility.printBeanObj(item));
		}
		
	}
}
