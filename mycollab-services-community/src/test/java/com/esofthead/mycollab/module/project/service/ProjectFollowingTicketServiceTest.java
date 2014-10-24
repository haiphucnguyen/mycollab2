package com.esofthead.mycollab.module.project.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.esofthead.mycollab.common.domain.criteria.MonitorSearchCriteria;
import com.esofthead.mycollab.configuration.SiteConfiguration;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.module.project.domain.FollowingTicket;
import com.esofthead.mycollab.test.DataSet;
import com.esofthead.mycollab.test.service.IntergrationServiceTest;

@RunWith(SpringJUnit4ClassRunner.class)
public class ProjectFollowingTicketServiceTest extends IntergrationServiceTest {

	private static final DateFormat DATE_FORMAT = new SimpleDateFormat(
			"yyyy-MM-dd hh:mm:ss");

	@Autowired
	private ProjectFollowingTicketService projectFollowingTicketService;

	@Before
	public void init() {
		SiteConfiguration.loadInstance(8080);
	}

	private MonitorSearchCriteria getCriteria() {
		MonitorSearchCriteria criteria = new MonitorSearchCriteria();
		criteria.setExtraTypeIds(new SetSearchField<Integer>(1));
		criteria.setSaccountid(new NumberSearchField(1));
		criteria.setUser(new StringSearchField("hainguyen@esofthead.com"));
		return criteria;
	}

	@SuppressWarnings("unchecked")
	@DataSet
	@Test
	public void testGetListProjectFollowingTicket() throws ParseException {
		List<FollowingTicket> projectFollowingTickets = projectFollowingTicketService
				.findPagableListByCriteria(new SearchRequest<MonitorSearchCriteria>(
						getCriteria(), 0, Integer.MAX_VALUE));
		assertThat(projectFollowingTickets).extracting("type", "summary",
				"monitorDate").contains(
				tuple("Project-Task", "task1",
						DATE_FORMAT.parse("2014-10-21 00:00:00")),
				tuple("Project-Task", "task2",
						DATE_FORMAT.parse("2014-10-22 00:00:00")),
				tuple("Project-Bug", "bug 1",
						DATE_FORMAT.parse("2014-10-23 00:00:00")),
				tuple("Project-Bug", "bug 2",
						DATE_FORMAT.parse("2014-10-24 00:00:00")));
		assertThat(projectFollowingTickets.size()).isEqualTo(4);
	}

	@SuppressWarnings("unchecked")
	@DataSet
	@Test
	public void testGetListProjectFollowingTicketOfTask() throws ParseException {
		MonitorSearchCriteria criteria = getCriteria();
		criteria.setType(new StringSearchField("Project-Task"));
		List<FollowingTicket> projectFollowingTickets = projectFollowingTicketService
				.findPagableListByCriteria(new SearchRequest<MonitorSearchCriteria>(
						criteria, 0, Integer.MAX_VALUE));
		assertThat(projectFollowingTickets.size()).isEqualTo(2);
		assertThat(projectFollowingTickets).extracting("type", "summary",
				"monitorDate").contains(
				tuple("Project-Task", "task1",
						DATE_FORMAT.parse("2014-10-21 00:00:00")),
				tuple("Project-Task", "task2",
						DATE_FORMAT.parse("2014-10-22 00:00:00")));
		assertThat(projectFollowingTickets.size()).isEqualTo(2);
	}

	@SuppressWarnings("unchecked")
	@DataSet
	@Test
	public void testGetListProjectFollowingTicketOfBug() throws ParseException {
		MonitorSearchCriteria criteria = getCriteria();
		criteria.setType(new StringSearchField("Project-Bug"));
		List<FollowingTicket> projectFollowingTickets = projectFollowingTicketService
				.findPagableListByCriteria(new SearchRequest<MonitorSearchCriteria>(
						criteria, 0, Integer.MAX_VALUE));

		assertThat(projectFollowingTickets).extracting("type", "summary",
				"monitorDate").contains(
				tuple("Project-Bug", "bug 1",
						DATE_FORMAT.parse("2014-10-23 00:00:00")),
				tuple("Project-Bug", "bug 2",
						DATE_FORMAT.parse("2014-10-24 00:00:00")));
		assertThat(projectFollowingTickets.size()).isEqualTo(2);
	}

	@DataSet
	@Test
	public void testGetTotalCount() throws ParseException {
		assertThat(projectFollowingTicketService.getTotalCount(getCriteria()))
				.isEqualTo(4);
	}
}
