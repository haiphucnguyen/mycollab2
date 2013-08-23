package com.esofthead.mycollab.module.crm.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.module.crm.domain.criteria.TodoSearchCriteria;
import com.esofthead.mycollab.test.DataSet;
import com.esofthead.mycollab.test.MyCollabClassRunner;
import com.esofthead.mycollab.test.service.ServiceTest;

@RunWith(MyCollabClassRunner.class)
@ContextConfiguration(locations = {"classpath:META-INF/spring/service-context-test.xml"})
public class TaskServiceTest extends ServiceTest{

    @Autowired
    protected TaskService taskService;

    @DataSet
    @Test
    public void testSearchByCriteria() {
        Assert.assertEquals(1,
                taskService.findPagableListByCriteria(new SearchRequest<TodoSearchCriteria>(getCriteria(), 0,
                2))
                .size());
    }

    @DataSet
    @Test
    public void testGetTotalCounts() {
        Assert.assertEquals(1, taskService.getTotalCount(getCriteria()));
    }

    private TodoSearchCriteria getCriteria() {
        TodoSearchCriteria criteria = new TodoSearchCriteria();
        return criteria;
    }
}
