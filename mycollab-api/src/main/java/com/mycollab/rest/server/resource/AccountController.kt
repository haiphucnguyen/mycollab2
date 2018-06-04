package com.mycollab.rest.server.resource

import com.mycollab.configuration.IDeploymentMode
import com.mycollab.ondemand.module.billing.service.BillingService
import com.mycollab.ondemand.module.support.service.EmailReferenceService
import io.swagger.annotations.Api
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.*

/**
 * @author MyCollab Ltd
 * @since 5.1.2
 */
@RestController
@RequestMapping(value = "/account")
@Api
class AccountController(private var billingService: BillingService,
                        private var emailReferenceService: EmailReferenceService,
                        private var deploymentMode: IDeploymentMode) {

    @CrossOrigin
    @RequestMapping(value = "signUp", method = [(RequestMethod.POST)], headers = ["Content-Type=application/x-www-form-urlencoded"])
    fun signUp(@RequestParam("subDomain") subDomain: String, @RequestParam("planId") planId: Int,
               @RequestParam("password") password: String, @RequestParam("email") email: String,
               @RequestParam("timezone") timezoneId: String, @RequestParam("isEmailVerified") isEmailVerified: Boolean?): String {
        var emailVerifiedMutableVal = isEmailVerified
        LOG.debug("Register account with subDomain $subDomain, username $email")
        if (emailVerifiedMutableVal == null) {
            emailVerifiedMutableVal = java.lang.Boolean.FALSE
        }
        billingService.registerAccount(subDomain, planId, email, password, email, timezoneId, emailVerifiedMutableVal!!)

        emailReferenceService.save(email)
        return deploymentMode.getSiteUrl(subDomain)
    }

    companion object {
        private val LOG = LoggerFactory.getLogger(AccountController::class.java)
    }
}
