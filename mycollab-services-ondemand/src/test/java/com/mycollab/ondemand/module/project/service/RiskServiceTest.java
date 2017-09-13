package com.mycollab.ondemand.module.project.service;

import com.mycollab.db.arguments.BasicSearchRequest;
import com.mycollab.db.arguments.NumberSearchField;
import com.mycollab.db.arguments.StringSearchField;
import com.mycollab.module.project.domain.Risk;
import com.mycollab.module.project.domain.SimpleRisk;
import com.mycollab.module.project.domain.criteria.RiskSearchCriteria;
import com.mycollab.module.project.i18n.OptionI18nEnum.Priority;
import com.mycollab.module.project.service.RiskService;
import com.mycollab.ondemand.test.spring.IntegrationServiceTest;
import com.mycollab.test.DataSet;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

@RunWith(SpringJUnit4ClassRunner.class)
public class RiskServiceTest extends IntegrationServiceTest {

    @Autowired
    private RiskService riskService;

    @DataSet
    @Test
    public void testGetListRisks() {
        List<SimpleRisk> risks = (List<SimpleRisk>)riskService.findPageableListByCriteria(new BasicSearchRequest<>(null));
        assertThat(risks.size()).isEqualTo(3);
        assertThat(risks).extracting("id", "name").contains(tuple(1, "a"), tuple(2, "ab"), tuple(3, "c"));
    }

    @DataSet
    @Test
    public void testSearchRisksByName() {
        RiskSearchCriteria criteria = new RiskSearchCriteria();
        criteria.setName(StringSearchField.and("a"));
        criteria.setSaccountid(new NumberSearchField(1));
        List<SimpleRisk> risks = (List<SimpleRisk>)riskService.findPageableListByCriteria(new BasicSearchRequest<>(criteria));

        assertThat(risks.size()).isEqualTo(2);
        assertThat(risks).extracting("id", "name").contains(tuple(1, "a"), tuple(2, "ab"));
    }

    @DataSet
    @Test
    public void testInsertAndReturnKey() {
        Risk record = new Risk();
        record.setProjectid(1);
        record.setName("New projectMember");
        record.setDescription("aaa");
        record.setSaccountid(1);
        record.setPriority(Priority.High.name());
        int newId = riskService.saveWithSession(record, "hainguyen");

        Risk risk = riskService.findByPrimaryKey(newId, 1);
        assertThat(risk.getName()).isEqualTo("New projectMember");
    }
}
