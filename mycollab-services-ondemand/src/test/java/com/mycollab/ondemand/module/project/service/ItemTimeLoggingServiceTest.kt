package com.mycollab.ondemand.module.project.service

import com.mycollab.db.arguments.BasicSearchRequest
import com.mycollab.db.arguments.BooleanSearchField
import com.mycollab.db.arguments.NumberSearchField
import com.mycollab.db.arguments.SetSearchField
import com.mycollab.module.project.domain.ItemTimeLogging
import com.mycollab.module.project.domain.criteria.ItemTimeLoggingSearchCriteria
import com.mycollab.module.project.service.ItemTimeLoggingService
import com.mycollab.ondemand.test.spring.IntegrationServiceTest
import com.mycollab.test.DataSet
import com.mycollab.test.rule.DbUnitInitializerRule
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.tuple
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.text.ParseException
import java.text.SimpleDateFormat

@ExtendWith(SpringExtension::class, DbUnitInitializerRule::class)
class ItemTimeLoggingServiceTest : IntegrationServiceTest() {

    @Autowired
    private lateinit var itemTimeLoggingService: ItemTimeLoggingService

    private val criteria: ItemTimeLoggingSearchCriteria
        get() {
            val criteria = ItemTimeLoggingSearchCriteria()
            criteria.saccountid = NumberSearchField(1)
            criteria.isBillable = BooleanSearchField(false)
            criteria.logUsers = SetSearchField("hai79", "nghiemle")
            return criteria
        }

    @DataSet
    @Test
    @Throws(ParseException::class)
    fun testGetListItemTimeLoggings() {
        val itemTimeLoggings = itemTimeLoggingService.findPageableListByCriteria(BasicSearchRequest(criteria)) as List<ItemTimeLogging>

        assertThat(itemTimeLoggings.size).isEqualTo(3)
        assertThat(itemTimeLoggings).extracting("id", "type", "logforday", "loguser", "name").contains(
                tuple(4, "Project-Task", DF.parse("2014-04-19 13:29:23"), "hai79", "task1"),
                tuple(2, "Project-Bug", DF.parse("2014-06-10 13:29:23"), "nghiemle", "name 2"))
    }

    @DataSet
    @Test
    fun testGetTotalCount() {
        val itemTimeLoggings = itemTimeLoggingService.findPageableListByCriteria(BasicSearchRequest(criteria)) as List<ItemTimeLogging>
        assertThat(itemTimeLoggings.size).isEqualTo(3)
    }

    companion object {
        private val DF = SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
    }
}
