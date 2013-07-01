package com.esofthead.mycollab.module.project.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.module.project.domain.SimpleProblem;
import com.esofthead.mycollab.module.project.domain.criteria.ProblemSearchCriteria;
import com.esofthead.mycollab.test.DataSet;
import com.esofthead.mycollab.test.EngroupClassRunner;
import com.esofthead.mycollab.test.ServiceTest;

@RunWith(EngroupClassRunner.class)
@ContextConfiguration(locations = {"classpath:META-INF/spring/service-context-test.xml"})
public class ProblemServiceTest extends ServiceTest{

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
        Assert.assertEquals(1, problemService.getTotalCount(criteria));
    }
}
