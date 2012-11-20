package com.esofthead.mycollab.module.project.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.esofthead.mycollab.core.arguments.BooleanSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.module.project.domain.SimpleProblem;
import com.esofthead.mycollab.module.project.domain.criteria.ProblemSearchCriteria;
import com.esofthead.mycollab.test.DataSet;
import com.esofthead.mycollab.test.EngroupClassRunner;

@RunWith(EngroupClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:META-INF/spring/audit-context.xml",
		"classpath:META-INF/spring/common-context.xml",
		"classpath:META-INF/spring/file-context.xml",
		"classpath:META-INF/spring/tracker-context.xml",
		"classpath:META-INF/spring/project-context.xml",
		"classpath:META-INF/spring/project-service-test-context.xml",
		"classpath:META-INF/spring/datasource-test-context.xml" })
public class ProblemServiceTest {

	@Autowired
	protected ProblemService problemService;

	@DataSet
	@Test
	public void testGetListIssues() {
		List issues = problemService
				.findPagableListByCriteria(new SearchRequest<ProblemSearchCriteria>(
						null, 0, Integer.MAX_VALUE));
		Assert.assertEquals(3, issues.size());
	}

	@DataSet
	@Test
	public void testSearchIssuesByName() {
		ProblemSearchCriteria criteria = new ProblemSearchCriteria();
		criteria.setProblemname(new StringSearchField(StringSearchField.AND,
				"a"));
		List<SimpleProblem> problems = problemService
				.findPagableListByCriteria(new SearchRequest<ProblemSearchCriteria>(
						criteria, 0, Integer.MAX_VALUE));
		Assert.assertEquals(1, problems.size());

		SimpleProblem problem = problems.get(0);
		Assert.assertEquals("a1 b1", problem.getAssignedUserFullName());
		Assert.assertEquals("a2 b2", problem.getRaisedByUserFullName());
		Assert.assertEquals("source", problem.getProblemsource());
		Assert.assertEquals(1, problemService.getTotalCount(criteria));
	}

	@DataSet
	@Test
	public void testgetTotalCount() {
		ProblemSearchCriteria criteria = new ProblemSearchCriteria();
		criteria.setProblemname(new StringSearchField(StringSearchField.AND,
				"a"));
		criteria.setIsCompleted(new BooleanSearchField(SearchField.AND, true));
		Assert.assertEquals(1, problemService.getTotalCount(criteria));
	}
}
