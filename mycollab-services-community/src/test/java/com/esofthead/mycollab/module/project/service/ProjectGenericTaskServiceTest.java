package com.esofthead.mycollab.module.project.service;

import static org.assertj.core.api.Assertions.*;

import com.esofthead.mycollab.core.arguments.RangeDateSearchField;
import com.esofthead.mycollab.module.project.domain.criteria.ProjectGenericTaskSearchCriteria;
import com.esofthead.mycollab.test.DataSet;
import com.esofthead.mycollab.test.service.IntergrationServiceTest;
import org.joda.time.LocalDate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
public class ProjectGenericTaskServiceTest extends IntergrationServiceTest {
    @Autowired
    protected ProjectGenericTaskService projectGenericTaskService;

    @DataSet
    @Test
    public void testGetAccountsHasOverdueAssignments() {
        ProjectGenericTaskSearchCriteria criteria = new ProjectGenericTaskSearchCriteria();
        criteria.setSaccountid(null);
        LocalDate now = new LocalDate();
        RangeDateSearchField rangeDateSearchField = new RangeDateSearchField(now.minusDays(10000).toDate(), now.toDate());
        criteria.setDateInRange(rangeDateSearchField);
        List<Map> accounts = projectGenericTaskService.getAccountsHasOverdueAssignments(criteria);
        assertThat(accounts).isNotEmpty().hasSize(2);
        assertThat(accounts.get(0)).containsExactly(entry("subdomain", "a"), entry("id", 1));
        assertThat(accounts.get(1)).containsExactly(entry("subdomain", "b"), entry("id", 2));
    }
}
