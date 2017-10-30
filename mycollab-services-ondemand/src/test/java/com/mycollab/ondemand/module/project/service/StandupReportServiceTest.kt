package com.mycollab.ondemand.module.project.service

import com.mycollab.db.arguments.*
import com.mycollab.module.project.domain.SimpleStandupReport
import com.mycollab.module.project.domain.criteria.StandupReportSearchCriteria
import com.mycollab.module.project.service.StandupReportService
import com.mycollab.ondemand.test.spring.IntegrationServiceTest
import com.mycollab.test.DataSet
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.tuple
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import java.util.*

@RunWith(SpringJUnit4ClassRunner::class)
class StandupReportServiceTest : IntegrationServiceTest() {
    @Autowired
    private lateinit var reportService: StandupReportService

    @Test
    @DataSet
    fun gatherStandupList() {
        val criteria = StandupReportSearchCriteria()
        criteria.projectIds = SetSearchField(1)
        criteria.logBy = StringSearchField.and("hainguyen")
        val d = GregorianCalendar(2013, 2, 13).time
        criteria.onDate = DateSearchField(d)
        criteria.saccountid = NumberSearchField(1)
        val reports = reportService.findPageableListByCriteria(BasicSearchRequest(criteria)) as List<SimpleStandupReport>
        assertThat(reports.size).isEqualTo(1)
        assertThat(reports).extracting("id", "logby", "whattoday").contains(tuple(1, "hainguyen", "a"))
    }

    @Test
    @DataSet
    fun testFindUsersNotDoReportYet() {
        val d = GregorianCalendar(2013, 2, 13).time
        val users = reportService.findUsersNotDoReportYet(1, d, 1)
        assertThat(users.size).isEqualTo(1)
        assertThat(users[0].username).isEqualTo("linhduong")
    }
}
