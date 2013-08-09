package com.esofthead.mycollab.module.project.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.module.project.domain.criteria.ProjectSearchCriteria;
import com.esofthead.mycollab.test.DataSet;
import com.esofthead.mycollab.test.EngroupClassRunner;
import com.esofthead.mycollab.test.service.ServiceTest;

@RunWith(EngroupClassRunner.class)
@ContextConfiguration(locations = {"classpath:META-INF/spring/service-context-test.xml"})
public class ProjectServiceTest extends ServiceTest{

    @Autowired
    protected ProjectService projectService;

    @DataSet
    @Test
    public void testGetListProjects() {
        List projects = projectService
                .findPagableListByCriteria(new SearchRequest<ProjectSearchCriteria>(
                null, 0, Integer.MAX_VALUE));
        Assert.assertEquals(4, projects.size());
    }

    @DataSet
    @Test
    public void testGetListProjectsByCriteria() {
        ProjectSearchCriteria criteria = new ProjectSearchCriteria();
        criteria.setAccountName(new StringSearchField(StringSearchField.AND,
                "a"));
        List projects = projectService
                .findPagableListByCriteria(new SearchRequest<ProjectSearchCriteria>(
                criteria, 0, Integer.MAX_VALUE));
        Assert.assertEquals(1, projects.size());
    }

    @DataSet
    @Test
    public void testGetTotalCountProjectsByCriteria() {
        ProjectSearchCriteria criteria = new ProjectSearchCriteria();
        criteria.setOwnerName(new StringSearchField(StringSearchField.AND,
                "Nguyen"));
        criteria.setSaccountid(new NumberSearchField(SearchField.AND, 1));
        Assert.assertEquals(2, projectService.getTotalCount(criteria));
    }

    @DataSet
    @Test
    public void testGetListProjectsByUsername() {
        ProjectSearchCriteria criteria = new ProjectSearchCriteria();
        criteria.setUsername(new StringSearchField(SearchField.AND, "admin"));
        List projects = projectService
                .findPagableListByCriteria(new SearchRequest<ProjectSearchCriteria>(
                criteria, 0, Integer.MAX_VALUE));
        Assert.assertEquals(2, projects.size());
    }
}
