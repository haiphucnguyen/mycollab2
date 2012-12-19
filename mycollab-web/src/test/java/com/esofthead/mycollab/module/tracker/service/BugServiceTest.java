package com.esofthead.mycollab.module.tracker.service;

import java.util.Calendar;
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
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.module.tracker.domain.SimpleBug;
import com.esofthead.mycollab.module.tracker.domain.criteria.BugSearchCriteria;
import com.esofthead.mycollab.test.DataSet;
import com.esofthead.mycollab.test.EngroupClassRunner;

@RunWith(EngroupClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:META-INF/spring/audit-context.xml",
		"classpath:META-INF/spring/common-context.xml",
		"classpath:META-INF/spring/file-context.xml",
		"classpath:META-INF/spring/project-context.xml",
		"classpath:META-INF/spring/tracker-context.xml",
		"classpath:META-INF/spring/project-service-test-context.xml",
		"classpath:META-INF/spring/datasource-test-context.xml" })
public class BugServiceTest {
	@Autowired
	protected BugService bugService;

	@DataSet
	@Test
	public void testGetListBugs() {
		List bugs = bugService
				.findPagableListByCriteria(new SearchRequest<BugSearchCriteria>(
						null, 0, Integer.MAX_VALUE));
		Assert.assertEquals(3, bugs.size());
	}

	@DataSet
	@Test
	public void testSearchDefectsByUserCriteria() {
		BugSearchCriteria criteria = new BugSearchCriteria();
		criteria.setAssignuser(new StringSearchField("user1"));
		criteria.setLoguser(new StringSearchField("admin"));
		criteria.setSummary(new StringSearchField("summary"));
		criteria.setDetail(new StringSearchField("detail"));

		Assert.assertEquals(1, bugService.getTotalCount(criteria));
		Assert.assertEquals(
				1,
				bugService.findPagableListByCriteria(
						new SearchRequest<BugSearchCriteria>(criteria, 0,
								Integer.MAX_VALUE)).size());
	}

	@DataSet
	@Test
	public void testSearchInvolvedUserCriteria() {
		BugSearchCriteria criteria = new BugSearchCriteria();
		criteria.setInvoleduser(new StringSearchField("admin"));

		Assert.assertEquals(3, bugService.getTotalCount(criteria));
		Assert.assertEquals(
				3,
				bugService.findPagableListByCriteria(
						new SearchRequest<BugSearchCriteria>(criteria, 0,
								Integer.MAX_VALUE)).size());
	}

	@DataSet
	@Test
	public void testGetExtBug() {
		SimpleBug bug = bugService.findBugById(1);
		Assert.assertEquals("Nguyen Hai", bug.getLoguserFullName());
		Assert.assertEquals("Nguyen Hai", bug.getAssignuserFullName());
		Assert.assertEquals(bug.getAffectedVersions().size(), 1);
		Assert.assertEquals(bug.getFixedVersions().size(), 2);
		Assert.assertEquals(bug.getComponents().size(), 1);
	}

	@DataSet
	@Test
	public void testSearchByComponents() {
		BugSearchCriteria criteria = new BugSearchCriteria();
		criteria.setComponentids(new SetSearchField<Integer>(1, 2));

		Assert.assertEquals(1, bugService.getTotalCount(criteria));
		Assert.assertEquals(
				1,
				bugService.findPagableListByCriteria(
						new SearchRequest<BugSearchCriteria>(criteria, 0,
								Integer.MAX_VALUE)).size());
	}

	@DataSet
	@Test
	public void testGetComponentDefectsSummary() {
		BugSearchCriteria criteria = new BugSearchCriteria();
		criteria.setProjectid(new NumberSearchField(1));
		bugService.getComponentDefectsSummary(criteria);
	}

	@DataSet
	@Test
	public void testSearchByVersions() {
		BugSearchCriteria criteria = new BugSearchCriteria();
		criteria.setFixedversionids(new SetSearchField<Integer>(1, 2));
		criteria.setAffectedversionids(new SetSearchField<Integer>(1, 2));

		Assert.assertEquals(1, bugService.getTotalCount(criteria));
		Assert.assertEquals(
				1,
				bugService.findPagableListByCriteria(
						new SearchRequest<BugSearchCriteria>(criteria, 0,
								Integer.MAX_VALUE)).size());
	}

	@DataSet
	@Test
	public void testSearchByVersions2() {
		BugSearchCriteria criteria = new BugSearchCriteria();
		criteria.setVersionids(new SetSearchField<Integer>(1, 2));

		Assert.assertEquals(1, bugService.getTotalCount(criteria));
		Assert.assertEquals(
				1,
				bugService.findPagableListByCriteria(
						new SearchRequest<BugSearchCriteria>(criteria, 0,
								Integer.MAX_VALUE)).size());
	}

	@DataSet
	@Test
	public void testSearchByAssignedUser() {
		BugSearchCriteria criteria = new BugSearchCriteria();
		List<GroupItem> assignedDefectsSummary = bugService
				.getAssignedDefectsSummary(criteria);
		Assert.assertEquals(2, assignedDefectsSummary.size());
	}

	@DataSet
	@Test
	public void testSearchByDateCriteria() {
		BugSearchCriteria criteria = new BugSearchCriteria();
		Calendar date = new GregorianCalendar();
		date.set(Calendar.YEAR, 2009);
		date.set(Calendar.MONTH, 0);
		date.set(Calendar.DAY_OF_MONTH, 2);

		criteria.setPostedDateFrom(new DateSearchField(SearchField.AND, date
				.getTime()));
		Assert.assertEquals(1, bugService.getTotalCount(criteria));
		Assert.assertEquals(
				1,
				bugService.findPagableListByCriteria(
						new SearchRequest<BugSearchCriteria>(criteria, 0,
								Integer.MAX_VALUE)).size());
	}

	@DataSet
	@Test
	public void testSearchByDateCriteria2() {
		BugSearchCriteria criteria = new BugSearchCriteria();
		Calendar date = new GregorianCalendar();
		date.set(Calendar.YEAR, 2009);
		date.set(Calendar.MONTH, 0);
		date.set(Calendar.DAY_OF_MONTH, 2);

		criteria.setUpdatedDateTo(new DateSearchField(SearchField.AND, date
				.getTime()));
		Assert.assertEquals(0, bugService.getTotalCount(criteria));
		Assert.assertEquals(
				0,
				bugService.findPagableListByCriteria(
						new SearchRequest<BugSearchCriteria>(criteria, 0,
								Integer.MAX_VALUE)).size());
	}

	@DataSet
	@Test
	public void testBugStatus() {
		BugSearchCriteria criteria = new BugSearchCriteria();
		List<GroupItem> groupitems = bugService.getStatusSummary(criteria);
		Assert.assertEquals(1, groupitems.size());
	}
}
