package com.esofthead.mycollab.module.crm.service;

import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.module.crm.domain.criteria.TaskSearchCriteria;
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
    protected TaskService taskService;

    @DataSet
    @Test
    public void testSearchByCriteria() {
        Assert.assertEquals(1,
                taskService.findPagableListByCriteria(new SearchRequest<TaskSearchCriteria>(getCriteria(), 0,
                2))
                .size());
    }

    @DataSet
    @Test
    public void testGetTotalCounts() {
        Assert.assertEquals(1, taskService.getTotalCount(getCriteria()));
    }

    private TaskSearchCriteria getCriteria() {
        TaskSearchCriteria criteria = new TaskSearchCriteria();
        return criteria;
    }
}
