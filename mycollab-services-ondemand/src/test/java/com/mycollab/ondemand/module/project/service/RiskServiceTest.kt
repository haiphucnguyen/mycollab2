package com.mycollab.ondemand.module.project.service

import com.mycollab.db.arguments.BasicSearchRequest
import com.mycollab.db.arguments.NumberSearchField
import com.mycollab.db.arguments.StringSearchField
import com.mycollab.module.project.domain.Risk
import com.mycollab.module.project.domain.SimpleRisk
import com.mycollab.module.project.domain.criteria.RiskSearchCriteria
import com.mycollab.module.project.i18n.OptionI18nEnum.Priority
import com.mycollab.module.project.service.RiskService
import com.mycollab.ondemand.test.spring.IntegrationServiceTest
import com.mycollab.test.DataSet
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.tuple

@RunWith(SpringJUnit4ClassRunner::class)
class RiskServiceTest : IntegrationServiceTest() {

    @Autowired
    private lateinit var riskService: RiskService

    @DataSet
    @Test
    fun testGetListRisks() {
        val risks = riskService.findPageableListByCriteria(BasicSearchRequest(RiskSearchCriteria())) as List<SimpleRisk>
        assertThat(risks.size).isEqualTo(3)
        assertThat(risks).extracting("id", "name").contains(tuple(1, "a"), tuple(2, "ab"), tuple(3, "c"))
    }

    @DataSet
    @Test
    fun testSearchRisksByName() {
        val criteria = RiskSearchCriteria()
        criteria.name = StringSearchField.and("a")
        criteria.saccountid = NumberSearchField(1)
        val risks = riskService.findPageableListByCriteria(BasicSearchRequest(criteria)) as List<SimpleRisk>

        assertThat(risks.size).isEqualTo(2)
        assertThat(risks).extracting("id", "name").contains(tuple(1, "a"), tuple(2, "ab"))
    }

    @DataSet
    @Test
    fun testInsertAndReturnKey() {
        val record = Risk()
        record.projectid = 1
        record.name = "New projectMember"
        record.description = "aaa"
        record.saccountid = 1
        record.priority = Priority.High.name
        val newId = riskService.saveWithSession(record, "hainguyen")

        val risk = riskService.findByPrimaryKey(newId, 1)
        assertThat(risk!!.name).isEqualTo("New projectMember")
    }
}
