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
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import java.util.Currency
import java.util.HashMap
import java.util.Locale

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
    var currentFragmentUrl = ""
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
        currentContext!!.clearSessionVariables()
        currentContext = null
        super.close()
    }

    companion object {
        private val serialVersionUID = 1L

        private val LOG = LoggerFactory.getLogger(AppUI::class.java)

        init {
            GroupIdProvider.registerAccountIdProvider(object : GroupIdProvider() {
                override val groupId: Int?
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
        @JvmStatic val siteUrl: String
            get() {
                val deploymentMode = AppContextUtil.getSpringBean(IDeploymentMode::class.java)
                return deploymentMode.getSiteUrl(getBillingAccount()!!.subdomain)
            }

        fun getBillingAccount(): SimpleBillingAccount? {
            return instance.billingAccount
        }

        val instance: AppUI
            get() = UI.getCurrent() as AppUI

        val subDomain: String
            get() = instance.billingAccount!!.subdomain

        /**
         * Get account id of current user
         *
         * @return account id of current user. Return 0 if can not get
         */
        @JvmStatic val accountId: Int
            get() {
                try {
                    return instance.billingAccount!!.id
                } catch (e: Exception) {
                    return 0
                }

            }

        val siteName: String
            get() {
                try {
                    return MoreObjects.firstNonNull(instance.billingAccount!!.sitename, SiteConfiguration.getDefaultSiteName())
                } catch (e: Exception) {
                    return SiteConfiguration.getDefaultSiteName()
                }

            }

        val defaultCurrency: Currency
            get() = instance.billingAccount!!.currencyInstance

        val longDateFormat: String
            get() = instance.billingAccount!!.longDateFormatInstance

        fun showEmailPublicly(): Boolean? {
            return instance.billingAccount!!.displayemailpublicly
        }

        val shortDateFormat: String
            get() = instance.billingAccount!!.shortDateFormatInstance

        val dateFormat: String
            get() = instance.billingAccount!!.dateFormatInstance

        val dateTimeFormat: String
            get() = instance.billingAccount!!.dateTimeFormatInstance

        val defaultLocale: Locale
            get() = instance.billingAccount!!.localeInstance

        /**
         * @param fragment
         * @param windowTitle
         */
        @JvmStatic fun addFragment(fragment: String, windowTitle: String) {
            Page.getCurrent().setUriFragment(fragment, false)
            Page.getCurrent().setTitle(String.format("%s [%s]", StringUtils.trim(windowTitle, 150), siteName))
        }
    }
}
