package com.mycollab.module.project.service

import com.mycollab.module.project.ProjectTypeConstants
import com.mycollab.test.DataSet
import com.mycollab.test.rule.DbUnitInitializerRule
import com.mycollab.test.spring.IntegrationServiceTest
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class, DbUnitInitializerRule::class)
class TicketRelationServiceTest : IntegrationServiceTest() {

    @Autowired
    private lateinit var ticketRelationService: TicketRelationService

    @DataSet
    @Test
    fun testFindRelatedTickets() {
        val ticketRelations = ticketRelationService.findRelatedTickets(1, ProjectTypeConstants.BUG)
        Assertions.assertThat(ticketRelations.size).isEqualTo(2)
    }
}