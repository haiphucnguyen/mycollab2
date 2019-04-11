package com.mycollab.ondemand.module.project.service

import com.mycollab.common.i18n.OptionI18nEnum.StatusI18nEnum
import com.mycollab.module.project.domain.Task
import com.mycollab.module.project.service.TaskService
import com.mycollab.ondemand.test.spring.IntegrationServiceTest
import com.mycollab.test.rule.DbUnitInitializerRule
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.util.concurrent.ExecutionException
import java.util.concurrent.Executors

@ExtendWith(SpringExtension::class, DbUnitInitializerRule::class)
class ProjectTaskServiceExtTest : IntegrationServiceTest() {

    @Autowired
    private lateinit var projectTaskService: TaskService

    @Test
    @Throws(ExecutionException::class, InterruptedException::class)
    fun testSaveWithoutSaveTheSameKey() {
        val baseRecord = Task()
        baseRecord.projectid = 1
        baseRecord.name = "Hello world"
        baseRecord.status = StatusI18nEnum.Open.name
        baseRecord.saccountid = 1
        baseRecord.percentagecomplete = 10.0

        val executorService = Executors.newFixedThreadPool(100)

        for (i in 0..99) {
            LOG.info("Initialize thread $i")
            val exeService = {
                val record = baseRecord.copy() as Task
                projectTaskService.saveWithSession(record, "hainguyen@esofthead.com")
            }

            val result = executorService.submit(exeService)
            LOG.info("Result:${result.get()}")
        }
    }

    companion object {
        private val LOG = LoggerFactory.getLogger(ProjectTaskServiceExtTest::class.java)
    }
}
