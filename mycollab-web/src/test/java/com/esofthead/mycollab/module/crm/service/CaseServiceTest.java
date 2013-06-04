package com.esofthead.mycollab.module.crm.service;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.module.crm.domain.criteria.CaseSearchCriteria;
import com.esofthead.mycollab.module.crm.service.CaseService;
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
public class CaseServiceTest extends ServiceTest {

    @Autowired
    protected CaseService caseService;

    @DataSet
    @Test
    public void testGetAll() {
        Assert.assertEquals(
                2,
                caseService.findPagableListByCriteria(
                new SearchRequest<CaseSearchCriteria>(null, 0,
                Integer.MAX_VALUE)).size());
    }

    @DataSet
    @Test
    public void testGetSearchCriteria() {
        CaseSearchCriteria criteria = new CaseSearchCriteria();
        criteria.setAccountId(new NumberSearchField(SearchField.AND, 1));
        criteria.setAccountName(new StringSearchField(SearchField.AND, "a"));
        criteria.setAssignUserName(new StringSearchField(SearchField.AND, "Hai"));
        criteria.setAssignUser(new StringSearchField(SearchField.AND, "admin"));
        criteria.setSubject(new StringSearchField(SearchField.AND, "a"));
        criteria.setSaccountid(new NumberSearchField(SearchField.AND, 1));

        Assert.assertEquals(
                1,
                caseService.findPagableListByCriteria(
                new SearchRequest<CaseSearchCriteria>(criteria, 0,
                Integer.MAX_VALUE)).size());
        Assert.assertEquals(1, caseService.getTotalCount(criteria));
    }

    @Test
    @DataSet
    public void testSearchAssignUsers() {
        CaseSearchCriteria criteria = new CaseSearchCriteria();
        criteria.setAssignUsers(new SetSearchField<String>(SearchField.AND, new String[]{"linh", "admin"}));
        Assert.assertEquals(2, caseService.getTotalCount(criteria));
        Assert.assertEquals(
                2,
                caseService.findPagableListByCriteria(
                new SearchRequest<CaseSearchCriteria>(criteria, 0,
                Integer.MAX_VALUE)).size());
    }

    @Test
    @DataSet
    public void testSearchPriorities() {
        CaseSearchCriteria criteria = new CaseSearchCriteria();
        criteria.setPriorities(new SetSearchField<String>(SearchField.AND, new String[]{"High", "Medium"}));
        Assert.assertEquals(2, caseService.getTotalCount(criteria));
        Assert.assertEquals(
                2,
                caseService.findPagableListByCriteria(
                new SearchRequest<CaseSearchCriteria>(criteria, 0,
                Integer.MAX_VALUE)).size());
    }

    @Test
    @DataSet
    public void testSearchStatuses() {
        CaseSearchCriteria criteria = new CaseSearchCriteria();
        criteria.setStatuses(new SetSearchField<String>(SearchField.AND, new String[]{"New", "Test Status"}));
        Assert.assertEquals(2, caseService.getTotalCount(criteria));
        Assert.assertEquals(
                2,
                caseService.findPagableListByCriteria(
                new SearchRequest<CaseSearchCriteria>(criteria, 0,
                Integer.MAX_VALUE)).size());
    }
}
