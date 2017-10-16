package com.mycollab.rest.server.resource

import com.mycollab.ondemand.module.billing.service.BillingService
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

/**
 * @author MyCollab Ltd
 * @since 5.1.2
 */
@RestController
@RequestMapping(value = "/user")
class UserController(private val billingService: BillingService) {

    @RequestMapping(value = "/subdomains/{username:.+}", method = arrayOf(RequestMethod.GET))
    fun retrieveSubdomainsOfUser(@PathVariable("username") username: String): Array<String> {
        val subDomains = billingService.getSubDomainsOfUser(username)
        val result: Array<String>
        when {
            subDomains.isNotEmpty() -> {
                result = subDomains.toTypedArray()
                LOG.debug("There are subdomains for user $username $result")
            }
            else -> {
                LOG.debug("There is no subDomain for user $username")
                result = emptyArray()
            }
        }
        return result
    }

    companion object {
        private val LOG = LoggerFactory.getLogger(UserController::class.java)
    }
}
