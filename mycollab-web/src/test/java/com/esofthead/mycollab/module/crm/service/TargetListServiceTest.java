package com.esofthead.mycollab.module.crm.service;

import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.module.crm.domain.criteria.TargetGroupSearchCriteria;
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
public class TargetListServiceTest extends ServiceTest{

    @Autowired
    protected TargetGroupService targetListService;

    @DataSet
    @Test
    public void testGetAll() {
        Assert.assertEquals(
                3,
                targetListService.findPagableListByCriteria(
                new SearchRequest<TargetGroupSearchCriteria>(null, 0,
                Integer.MAX_VALUE)).size());
    }

    @DataSet
    @Test
    public void testSearchByCriteria() {
        TargetGroupSearchCriteria criteria = new TargetGroupSearchCriteria();
        criteria.setAssignUserName(new StringSearchField(SearchField.AND, "Hai"));
        criteria.setListName(new StringSearchField(SearchField.AND, "a"));
        criteria.setAssignUser(new StringSearchField(SearchField.AND, "admin"));

        Assert.assertEquals(
                1,
                targetListService.findPagableListByCriteria(
                new SearchRequest<TargetGroupSearchCriteria>(criteria,
                0, Integer.MAX_VALUE)).size());

        Assert.assertEquals(1, targetListService.getTotalCount(criteria));
    }
}
