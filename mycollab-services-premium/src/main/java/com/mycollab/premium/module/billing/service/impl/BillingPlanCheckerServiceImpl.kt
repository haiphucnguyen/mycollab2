package com.mycollab.premium.module.billing.service.impl

import com.mycollab.license.LicenseInfo
import com.mycollab.module.billing.UsageExceedBillingPlanException
import com.mycollab.module.billing.service.BillingPlanCheckerService
import com.mycollab.module.user.service.UserService
import com.mycollab.license.service.LicenseResolver
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * @author MyCollab Ltd.
 * @since 4.1
 */
@Service
class BillingPlanCheckerServiceImpl(private val licenseResolver: LicenseResolver,
                                    private val userService: UserService) : BillingPlanCheckerService {

    @Throws(UsageExceedBillingPlanException::class)
    override fun validateAccountCanCreateMoreProject(sAccountId: Int) {}

    @Throws(UsageExceedBillingPlanException::class)
    override fun validateAccountCanCreateNewUser(sAccountId: Int) {
        val licenseInfo = licenseResolver.licenseInfo
        val numOfUsers = userService.getTotalActiveUsersInAccount(sAccountId)
        if (licenseInfo != null) {
            if (numOfUsers >= licenseInfo.maxUsers) {
                throw UsageExceedBillingPlanException()
            }
        }
    }

    @Throws(UsageExceedBillingPlanException::class)
    override fun validateAccountCanUploadMoreFiles(sAccountId: Int, extraBytes: Long) {}
}
