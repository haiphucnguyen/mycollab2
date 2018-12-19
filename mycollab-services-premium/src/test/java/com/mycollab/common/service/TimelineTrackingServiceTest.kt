package com.mycollab.common.service

import com.mycollab.common.domain.criteria.TimelineTrackingSearchCriteria
import com.mycollab.common.i18n.OptionI18nEnum.StatusI18nEnum
import com.mycollab.db.arguments.NumberSearchField
import com.mycollab.db.arguments.SetSearchField
import com.mycollab.module.project.ProjectTypeConstants
import com.mycollab.test.DataSet
import com.mycollab.test.rule.DbUnitInitializerRule
import com.mycollab.test.spring.IntegrationServiceTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.util.*

/**
 * @author MyCollab Ltd
 * @since 5.2.2
 */
@ExtendWith(SpringExtension::class, DbUnitInitializerRule::class)
class TimelineTrackingServiceTest : IntegrationServiceTest() {
    @Autowired
    private lateinit var timelineTrackingService: TimelineTrackingService

    @Test
    @DataSet
    fun testFindTimeline() {
        val criteria = TimelineTrackingSearchCriteria()
        criteria.saccountid = NumberSearchField(1)
        criteria.types = SetSearchField(ProjectTypeConstants.BUG)
        val timelineItems = timelineTrackingService.findTimelineItems("status",
                Arrays.asList(StatusI18nEnum.ReOpen.name, StatusI18nEnum.Resolved.name),
                GregorianCalendar(2015, 9, 2).time, GregorianCalendar(2015, 9, 31).time, criteria)
        println(timelineItems)
    }
}
