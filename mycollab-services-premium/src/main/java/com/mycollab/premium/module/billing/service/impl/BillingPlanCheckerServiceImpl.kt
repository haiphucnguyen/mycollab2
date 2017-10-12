package com.mycollab.premium.module.billing.service.impl

import com.mycollab.db.arguments.NumberSearchField
import com.mycollab.db.arguments.SetSearchField
import com.mycollab.license.service.LicenseResolver
import com.mycollab.module.billing.RegisterStatusConstants
import com.mycollab.module.billing.UsageExceedBillingPlanException
import com.mycollab.module.billing.service.BillingPlanCheckerService
import com.mycollab.module.user.dao.UserMapperExt
import com.mycollab.module.user.domain.criteria.UserSearchCriteria
import org.springframework.stereotype.Service

/**
 * @author MyCollab Ltd.
 * @since 4.1
 */
@Service
class BillingPlanCheckerServiceImpl(private val licenseResolver: LicenseResolver,
                                    private val userMapper: UserMapperExt) : BillingPlanCheckerService {

    @Throws(UsageExceedBillingPlanException::class)
    override fun validateAccountCanCreateMoreProject(sAccountId: Int) {
    }

    @Throws(UsageExceedBillingPlanException::class)
    override fun validateAccountCanCreateNewUser(sAccountId: Int) {
        val licenseInfo = licenseResolver.licenseInfo

        val criteria = UserSearchCriteria()
        criteria.registerStatuses = SetSearchField(RegisterStatusConstants.ACTIVE)
        criteria.saccountid = NumberSearchField(sAccountId)
        val numOfUsers = userMapper.getTotalCount(criteria)
        if (licenseInfo != null) {
            if (numOfUsers >= licenseInfo.maxUsers) {
                throw UsageExceedBillingPlanException()
            }
        }
    }

    @Throws(UsageExceedBillingPlanException::class)
    override fun validateAccountCanUploadMoreFiles(sAccountId: Int, extraBytes: Long) {
    }
}
