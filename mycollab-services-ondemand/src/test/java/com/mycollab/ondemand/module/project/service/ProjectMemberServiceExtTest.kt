package com.mycollab.ondemand.module.project.service

import com.mycollab.module.project.service.ProjectMemberService
import com.mycollab.ondemand.test.spring.IntegrationServiceTest
import com.mycollab.test.DataSet
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner

@RunWith(SpringJUnit4ClassRunner::class)
class ProjectMemberServiceExtTest : IntegrationServiceTest() {

    @Autowired
    private lateinit var projectMemberService: ProjectMemberService

    @Test
    @DataSet
    fun testGetActiveMembersInProjectEmpty() {
        val activeUsersInProject = projectMemberService.getActiveUsersInProject(2, 1)
        assertThat(activeUsersInProject.size).isEqualTo(0)
    }
}
