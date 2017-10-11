/**
 * mycollab-ui - Parent pom providing dependency and plugin management for applications
		built with Maven
 * Copyright © 2017 MyCollab (support@mycollab.com)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
/**
 * mycollab-ui - Parent pom providing dependency and plugin management for applications
		built with Maven
 * Copyright © 2017 MyCollab (support@mycollab.com)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.mycollab.vaadin

import com.google.common.base.MoreObjects
import com.mycollab.common.SessionIdGenerator
import com.mycollab.common.i18n.ErrorI18nEnum
import com.mycollab.configuration.IDeploymentMode
import com.mycollab.configuration.SiteConfiguration
import com.mycollab.core.utils.StringUtils
import com.mycollab.db.arguments.GroupIdProvider
import com.mycollab.module.billing.SubDomainNotExistException
import com.mycollab.module.user.domain.SimpleBillingAccount
import com.mycollab.module.user.service.BillingAccountService
import com.mycollab.spring.AppContextUtil
import com.mycollab.vaadin.ui.ThemeManager
import com.vaadin.server.Page
import com.vaadin.server.VaadinRequest
import com.vaadin.ui.UI
import org.slf4j.LoggerFactory
import java.util.*

/**
 * @author MyCollab Ltd.
 * @since 4.3.2
 */
abstract class AppUI : UI() {

    /**
     * Context of current logged in user
     */
    protected var currentContext: UserUIContext? = null

    private var initialSubDomain = "1"
    var currentFragmentUrl:String? = null
    private var billingAccount: SimpleBillingAccount? = null
    private val attributes = mutableMapOf<String, Any?>()

    protected fun postSetupApp(request: VaadinRequest) {
        initialSubDomain = Utils.getSubDomain(request)
        val billingService = AppContextUtil.getSpringBean(BillingAccountService::class.java)
        billingAccount = billingService.getAccountByDomain(initialSubDomain)

        if (billingAccount == null) {
            throw SubDomainNotExistException(UserUIContext.getMessage(ErrorI18nEnum.SUB_DOMAIN_IS_NOT_EXISTED, initialSubDomain))
        } else {
            val accountId = billingAccount!!.id
            ThemeManager.loadDesktopTheme(accountId!!)
        }
    }

    fun setAttribute(key: String, value: Any?) {
        attributes.put(key, value)
    }

    fun getAttribute(key: String): Any? {
        return attributes[key]
    }

    override fun close() {
        LOG.debug("Application is closed. Clean all resources")
        currentContext?.clearSessionVariables()
        currentContext = null
        super.close()
    }

    companion object {
        private val serialVersionUID = 1L

        private val LOG = LoggerFactory.getLogger(AppUI::class.java)

        init {
            GroupIdProvider.registerAccountIdProvider(object : GroupIdProvider() {
                override val groupId: Int
                    get() = AppUI.accountId

                override val groupRequestedUser: String
                    get() = UserUIContext.getUsername()
            })

            SessionIdGenerator.registerSessionIdGenerator(object : SessionIdGenerator() {
                override val sessionIdApp: String
                    get() = UI.getCurrent().toString()
            })
        }

        /**
         * @return
         */
        @JvmStatic
        val siteUrl: String
            get() {
                val deploymentMode = AppContextUtil.getSpringBean(IDeploymentMode::class.java)
                return deploymentMode.getSiteUrl(getBillingAccount()!!.subdomain)
            }

        @JvmStatic
        fun getBillingAccount(): SimpleBillingAccount? {
            return instance.billingAccount
        }

        @JvmStatic
        val instance: AppUI
            get() = UI.getCurrent() as AppUI

        @JvmStatic
        val subDomain: String
            get() = instance.billingAccount!!.subdomain

        /**
         * Get account id of current user
         *
         * @return account id of current user. Return 0 if can not get
         */
        @JvmStatic
        val accountId: Int
            get() {
                try {
                    return instance.billingAccount!!.id
                } catch (e: Exception) {
                    return 0
                }

            }

        @JvmStatic
        val siteName: String
            get() {
                return try {
                    MoreObjects.firstNonNull(instance.billingAccount!!.sitename, SiteConfiguration.getDefaultSiteName())
                } catch (e: Exception) {
                    SiteConfiguration.getDefaultSiteName()
                }
            }

        @JvmStatic
        val defaultCurrency: Currency
            get() = instance.billingAccount!!.currencyInstance

        @JvmStatic
        val longDateFormat: String
            get() = instance.billingAccount!!.longDateFormatInstance

        @JvmStatic
        fun showEmailPublicly(): Boolean? {
            return instance.billingAccount!!.displayemailpublicly
        }

        @JvmStatic
        val shortDateFormat: String
            get() = instance.billingAccount!!.shortDateFormatInstance

        @JvmStatic
        val dateFormat: String
            get() = instance.billingAccount!!.dateFormatInstance

        @JvmStatic
        val dateTimeFormat: String
            get() = instance.billingAccount!!.dateTimeFormatInstance

        @JvmStatic
        val defaultLocale: Locale
            get() = instance.billingAccount!!.localeInstance

        /**
         * @param fragment
         * @param windowTitle
         */
        @JvmStatic
        fun addFragment(fragment: String, windowTitle: String) {
            Page.getCurrent().setUriFragment(fragment, false)
            Page.getCurrent().setTitle(String.format("%s [%s]", StringUtils.trim(windowTitle, 150), siteName))
        }
    }
}
