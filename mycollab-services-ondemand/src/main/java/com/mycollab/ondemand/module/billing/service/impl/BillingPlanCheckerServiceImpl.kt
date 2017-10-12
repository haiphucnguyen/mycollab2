package com.mycollab.ondemand.module.billing.service.impl

import com.mycollab.module.billing.UsageExceedBillingPlanException
import com.mycollab.module.billing.service.BillingPlanCheckerService
import com.mycollab.module.ecm.service.DriveInfoService
import com.mycollab.module.project.service.ProjectService
import com.mycollab.module.user.service.UserService
import com.mycollab.ondemand.module.billing.service.BillingService
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

/**
 * @author MyCollab Ltd.
 * @since 4.1
 */
@Service
class BillingPlanCheckerServiceImpl(private val billingService: BillingService,
                                    private val projectService: ProjectService,
                                    private val userService: UserService,
                                    private val driveInfoService: DriveInfoService) : BillingPlanCheckerService {

    @Throws(UsageExceedBillingPlanException::class)
    override fun validateAccountCanCreateMoreProject(sAccountId: Int) {
        val billingPlan = billingService.findBillingPlan(sAccountId)
        val numOfActiveProjects = projectService.getTotalActiveProjectsInAccount(sAccountId)

        if (numOfActiveProjects >= billingPlan.numprojects) {
            throw UsageExceedBillingPlanException()
        }
    }

    @Throws(UsageExceedBillingPlanException::class)
    override fun validateAccountCanCreateNewUser(sAccountId: Int) {
        val billingPlan = billingService.findBillingPlan(sAccountId)
        val numOfUsers = userService.getTotalActiveUsersInAccount(sAccountId)
        if (numOfUsers >= billingPlan.numusers) {
            throw UsageExceedBillingPlanException()
        }
    }

    @Throws(UsageExceedBillingPlanException::class)
    override fun validateAccountCanUploadMoreFiles(sAccountId: Int, extraBytes: Long) {
        val billingPlan = billingService.findBillingPlan(sAccountId)
        if (billingPlan == null) {
            LOG.error("Can not define the billing plan for account ", sAccountId)
            return
        }

        val driveInfo = driveInfoService.getDriveInfo(sAccountId)
        if (driveInfo.usedvolume!! + extraBytes >= billingPlan.volume) {
            throw UsageExceedBillingPlanException()
        }
    }

    companion object {
        private val LOG = LoggerFactory.getLogger(BillingPlanCheckerServiceImpl::class.java)
    }
}
