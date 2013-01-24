package com.esofthead.mycollab.module.project.service;

import com.esofthead.mycollab.core.arguments.BooleanSearchField;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.module.project.domain.criteria.TaskSearchCriteria;
import com.esofthead.mycollab.test.DataSet;
import com.esofthead.mycollab.test.EngroupClassRunner;
import com.esofthead.mycollab.test.ServiceTest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

@RunWith(EngroupClassRunner.class)
@ContextConfiguration(locations = {"classpath:META-INF/spring/service-test-context.xml"})
public class TaskServiceTest extends ServiceTest{

    @Autowired
    protected ProjectTaskService projectTaskService;

    @DataSet
    @Test
    public void testGetUnCompletedTaskOfUser() {
        TaskSearchCriteria criteria = new TaskSearchCriteria();
        criteria.setStatus(new StringSearchField(SearchField.AND, "And"));
        criteria.setProjectid(new NumberSearchField(SearchField.AND, 1));

        Assert.assertEquals(1, projectTaskService.getTotalCount(criteria));
        Assert.assertEquals(
                1,
                projectTaskService.findPagableListByCriteria(
                new SearchRequest<TaskSearchCriteria>(criteria, 0,
                Integer.MAX_VALUE)).size());
    }
}
