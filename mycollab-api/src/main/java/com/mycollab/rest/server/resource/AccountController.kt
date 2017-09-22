package com.mycollab.rest.server.resource

import com.mycollab.configuration.IDeploymentMode
import com.mycollab.ondemand.module.billing.service.BillingService
import com.mycollab.ondemand.module.support.service.EmailReferenceService
import io.swagger.annotations.Api
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

/**
 * @author MyCollab Ltd
 * @since 5.1.2
 */
@RestController
@RequestMapping(value = "/account")
@Api
class AccountController(private val billingService: BillingService,
                        private val emailReferenceService: EmailReferenceService,
                        private val deploymentMode: IDeploymentMode) {

    @RequestMapping(value = "signUp", method = arrayOf(RequestMethod.POST), headers = arrayOf("Content-Type=application/x-www-form-urlencoded"))
    fun signUp(@RequestParam("subDomain") subdomain: String, @RequestParam("planId") planId: Int?,
               @RequestParam("password") password: String, @RequestParam("email") email: String,
               @RequestParam("timezone") timezoneId: String, @RequestParam("isEmailVerified") isEmailVerified: Boolean?): String {
        var emailVerifiedMutableVal = isEmailVerified
        LOG.debug("Register account with subDomain {}, username {}", subdomain, email)
        if (emailVerifiedMutableVal == null) {
            emailVerifiedMutableVal = java.lang.Boolean.FALSE
        }
        billingService.registerAccount(subdomain, planId!!, email, password, email, timezoneId, emailVerifiedMutableVal!!)

        emailReferenceService.save(email)
        return deploymentMode.getSiteUrl(subdomain)
    }

    companion object {
        private val LOG = LoggerFactory.getLogger(AccountController::class.java)
    }
}
