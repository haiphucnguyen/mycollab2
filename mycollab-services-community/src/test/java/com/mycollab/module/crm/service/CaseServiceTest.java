package com.mycollab.module.crm.service;

import com.mycollab.db.arguments.BasicSearchRequest;
import com.mycollab.db.arguments.NumberSearchField;
import com.mycollab.db.arguments.SetSearchField;
import com.mycollab.db.arguments.StringSearchField;
import com.mycollab.module.crm.domain.SimpleCase;
import com.mycollab.module.crm.domain.criteria.CaseSearchCriteria;
import com.mycollab.test.DataSet;
import com.mycollab.test.service.IntegrationServiceTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

@RunWith(SpringJUnit4ClassRunner.class)
public class CaseServiceTest extends IntegrationServiceTest {

    @Autowired
    private CaseService caseService;

    @DataSet
    @Test
    public void testGetAll() {
        CaseSearchCriteria criteria = new CaseSearchCriteria();
        criteria.setSaccountid(new NumberSearchField(1));

        List<SimpleCase> cases = caseService.findPageableListByCriteria(new BasicSearchRequest<>(null));

        assertThat(cases.size()).isEqualTo(2);
        assertThat(cases).extracting("id", "subject", "status").contains(tuple(1, "abc", "New"), tuple(2, "a", "Test Status"));
    }

    @DataSet
    @Test
    public void testGetSearchCriteria() {
        CaseSearchCriteria criteria = new CaseSearchCriteria();
        criteria.setAccountId(new NumberSearchField(1));
        criteria.setAssignUser(StringSearchField.and("admin"));
        criteria.setSubject(StringSearchField.and("a"));
        criteria.setSaccountid(new NumberSearchField(1));

        List<SimpleCase> cases = caseService.findPageableListByCriteria(new BasicSearchRequest<>(null));

        assertThat(cases.size()).isEqualTo(2);
        assertThat(cases).extracting("id", "subject", "status").contains(tuple(1, "abc", "New"), tuple(2, "a", "Test Status"));
    }

    @Test
    @DataSet
    public void testSearchAssignUsers() {
        CaseSearchCriteria criteria = new CaseSearchCriteria();
        criteria.setAssignUsers(new SetSearchField<>("linh", "admin"));
        criteria.setSaccountid(new NumberSearchField(1));

        List<SimpleCase> cases = caseService.findPageableListByCriteria(new BasicSearchRequest<>(null));

        assertThat(cases.size()).isEqualTo(2);
        assertThat(cases).extracting("id", "subject", "status").contains(tuple(1, "abc", "New"), tuple(2, "a", "Test Status"));
    }

    @Test
    @DataSet
    public void testSearchPriorities() {
        CaseSearchCriteria criteria = new CaseSearchCriteria();
        criteria.setPriorities(new SetSearchField<>("High", "Medium"));
        criteria.setSaccountid(new NumberSearchField(1));

        List<SimpleCase> cases = caseService.findPageableListByCriteria(new BasicSearchRequest<>(null));

        assertThat(cases.size()).isEqualTo(2);
        assertThat(cases).extracting("id", "subject", "status").contains(tuple(1, "abc", "New"), tuple(2, "a", "Test Status"));
    }

    @Test
    @DataSet
    public void testSearchStatuses() {
        CaseSearchCriteria criteria = new CaseSearchCriteria();
        criteria.setStatuses(new SetSearchField<>("New", "Test Status"));
        criteria.setSaccountid(new NumberSearchField(1));

        List<SimpleCase> cases = caseService.findPageableListByCriteria(new BasicSearchRequest<>(null));

        assertThat(cases.size()).isEqualTo(2);
        assertThat(cases).extracting("id", "subject", "status").contains(tuple(1, "abc", "New"), tuple(2, "a", "Test Status"));
    }
}
