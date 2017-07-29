package com.mycollab.module.project.service;

import com.mycollab.common.domain.GroupItem;
import com.mycollab.db.arguments.BasicSearchRequest;
import com.mycollab.db.arguments.NumberSearchField;
import com.mycollab.db.arguments.RangeDateSearchField;
import com.mycollab.db.arguments.SetSearchField;
import com.mycollab.module.project.domain.ProjectTicket;
import com.mycollab.module.project.domain.criteria.ProjectTicketSearchCriteria;
import com.mycollab.module.user.domain.BillingAccount;
import com.mycollab.test.DataSet;
import com.mycollab.test.service.IntegrationServiceTest;
import org.joda.time.LocalDate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

@RunWith(SpringJUnit4ClassRunner.class)
public class ProjectTicketServiceTest extends IntegrationServiceTest {
    @Autowired
    private ProjectTicketService projectTicketService;

    @DataSet
    @Test
    public void testGetAccountsHasOverdueAssignments() {
        ProjectTicketSearchCriteria criteria = new ProjectTicketSearchCriteria();
        criteria.setSaccountid(null);
        LocalDate now = new LocalDate();
        RangeDateSearchField rangeDateSearchField = new RangeDateSearchField(now.minusDays(10000).toDate(), now.toDate());
        criteria.setDateInRange(rangeDateSearchField);
        List<BillingAccount> accounts = projectTicketService.getAccountsHasOverdueAssignments(criteria);
        assertThat(accounts).isNotEmpty().hasSize(2);
        assertThat(accounts).extracting("subdomain", "id").contains(tuple("a", 1), tuple("b", 2));
    }

    @DataSet
    @Test
    public void testGetAssignments() {
        ProjectTicketSearchCriteria criteria = new ProjectTicketSearchCriteria();
        criteria.setSaccountid(NumberSearchField.equal(2));
        criteria.setProjectIds(new SetSearchField<>(3));
        List<ProjectTicket> tickets = projectTicketService.findTicketsByCriteria(new BasicSearchRequest<>(criteria));
        assertThat(tickets).hasSize(3);
        assertThat(tickets).extracting("name", "assignUser", "assignUserFullName", "assignUserAvatarId", "type")
                .contains(tuple("Task 4", "linhduong", "Duong Linh", "linh123", "Project-Task"),
                        tuple("Bug 1", "hai79", "Nguyen Hai", null, "Project-Bug"),
                        tuple("Risk 1", "hai79", "Nguyen Hai", null, "Project-Risk"));
    }

    @DataSet
    @Test
    public void testGetAssigneeSummary() {
        ProjectTicketSearchCriteria criteria = new ProjectTicketSearchCriteria();
        criteria.setSaccountid(NumberSearchField.equal(2));
        criteria.setProjectIds(new SetSearchField<>(3));
        List<GroupItem> groupItems = projectTicketService.getAssigneeSummary(criteria);
        assertThat(groupItems).hasSize(2);
        assertThat(groupItems).extracting("groupid", "value", "extraValue").contains(tuple("hai79", 2d, null), tuple
                ("linhduong", 1d, "linh123"));
    }
}
