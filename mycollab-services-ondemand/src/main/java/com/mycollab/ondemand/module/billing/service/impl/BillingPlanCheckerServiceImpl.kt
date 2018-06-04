package com.mycollab.ondemand.module.billing.service.impl

import com.mycollab.common.i18n.OptionI18nEnum
import com.mycollab.db.arguments.NumberSearchField
import com.mycollab.db.arguments.SetSearchField
import com.mycollab.module.billing.UsageExceedBillingPlanException
import com.mycollab.module.billing.service.BillingPlanCheckerService
import com.mycollab.module.ecm.service.DriveInfoService
import com.mycollab.module.project.dao.ProjectMapperExt
import com.mycollab.module.project.domain.criteria.ProjectSearchCriteria
import com.mycollab.module.user.service.BillingAccountService
import com.mycollab.ondemand.module.billing.service.BillingService
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

/**
 * @author MyCollab Ltd.
 * @since 4.1
 */
@Service
class BillingPlanCheckerServiceImpl(private val billingService: BillingService,
                                    private val projectMapperExt: ProjectMapperExt,
                                    private val billingAccountService: BillingAccountService,
                                    private val driveInfoService: DriveInfoService) : BillingPlanCheckerService {

    @Throws(UsageExceedBillingPlanException::class)
    override fun validateAccountCanCreateMoreProject(sAccountId: Int) {
        val billingPlan = billingService.findBillingPlan(sAccountId)
        val criteria = ProjectSearchCriteria()
        criteria.saccountid = NumberSearchField(sAccountId)
        criteria.projectStatuses = SetSearchField(OptionI18nEnum.StatusI18nEnum.Open.name)
        val numOfActiveProjects = projectMapperExt.getTotalCount(criteria)

        if (numOfActiveProjects >= billingPlan!!.numprojects) {
            throw UsageExceedBillingPlanException()
        }
    }

    @Throws(UsageExceedBillingPlanException::class)
    override fun validateAccountCanCreateNewUser(sAccountId: Int) {
        val billingPlan = billingService.findBillingPlan(sAccountId)
        val numOfUsers = billingAccountService.getTotalActiveUsersInAccount(sAccountId)
        if (numOfUsers >= billingPlan!!.numusers) {
            throw UsageExceedBillingPlanException()
        }
    }

    @Throws(UsageExceedBillingPlanException::class)
    override fun validateAccountCanUploadMoreFiles(sAccountId: Int, extraBytes: Long) {
        val billingPlan = billingService.findBillingPlan(sAccountId)
        if (billingPlan == null) {
            LOG.error("Can not define the billing plan for account $sAccountId")
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
