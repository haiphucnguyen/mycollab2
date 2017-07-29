package com.mycollab.module.crm.service;

import com.mycollab.db.arguments.BasicSearchRequest;
import com.mycollab.db.arguments.NumberSearchField;
import com.mycollab.db.arguments.SetSearchField;
import com.mycollab.db.arguments.StringSearchField;
import com.mycollab.module.crm.domain.SimpleOpportunity;
import com.mycollab.module.crm.domain.criteria.OpportunitySearchCriteria;
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
public class OpportunityServiceTest extends IntegrationServiceTest {

    @Autowired
    private OpportunityService opportunityService;

    @DataSet
    @Test
    public void testSearchByCriteria() {
        List<SimpleOpportunity> opportunities = opportunityService.findPageableListByCriteria(new BasicSearchRequest<>(getCriteria()));

        assertThat(opportunities.size()).isEqualTo(2);
        assertThat(opportunities).extracting("id", "salesstage", "source").contains(
                tuple(1, "1", "Cold Call"), tuple(2, "2", "Employee"));
    }

    @DataSet
    @Test
    public void testGetTotalCount() {
        List<SimpleOpportunity> opportunities = opportunityService.findPageableListByCriteria(new BasicSearchRequest<>(getCriteria()));
        assertThat(opportunities.size()).isEqualTo(2);
    }

    private OpportunitySearchCriteria getCriteria() {
        OpportunitySearchCriteria criteria = new OpportunitySearchCriteria();
        criteria.setAccountId(new NumberSearchField(1));
        criteria.setCampaignId(new NumberSearchField(1));
        criteria.setOpportunityName(StringSearchField.and("aa"));
        criteria.setSaccountid(new NumberSearchField(1));
        return criteria;
    }

    @Test
    @DataSet
    public void testSearchAssignUsers() {
        OpportunitySearchCriteria criteria = new OpportunitySearchCriteria();
        criteria.setAssignUsers(new SetSearchField<>("hai", "linh"));
        criteria.setSaccountid(new NumberSearchField(1));

        List<SimpleOpportunity> opportunities = opportunityService.findPageableListByCriteria(new BasicSearchRequest<>(criteria));

        assertThat(opportunities.size()).isEqualTo(2);
        assertThat(opportunities).extracting("id", "salesstage", "source").contains(
                tuple(1, "1", "Cold Call"), tuple(2, "2", "Employee"));
    }
}
