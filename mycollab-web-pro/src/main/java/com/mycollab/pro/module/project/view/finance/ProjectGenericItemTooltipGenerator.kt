package com.mycollab.pro.module.project.view.finance

import com.mycollab.core.UnsupportedFeatureException
import com.mycollab.module.project.ProjectTooltipGenerator
import com.mycollab.module.project.ProjectTypeConstants
import com.mycollab.module.project.domain.SimpleRisk
import com.mycollab.module.project.domain.SimpleStandupReport
import com.mycollab.module.project.domain.SimpleTask
import com.mycollab.module.project.service.TaskService
import com.mycollab.module.project.service.RiskService
import com.mycollab.module.project.service.StandupReportService
import com.mycollab.module.project.domain.SimpleBug
import com.mycollab.module.project.domain.SimpleComponent
import com.mycollab.module.project.domain.SimpleVersion
import com.mycollab.module.project.service.BugService
import com.mycollab.module.project.service.ComponentService
import com.mycollab.module.project.service.VersionService
import com.mycollab.spring.AppContextUtil
import com.mycollab.vaadin.AppUI
import com.mycollab.vaadin.UserUIContext

import java.time.ZoneId

/**
 * @author MyCollab Ltd.
 * @since 4.0.0
 */
internal object ProjectGenericItemTooltipGenerator {

    @JvmStatic
    fun generateTooltipForEntity(type: String, typeId: Int): String? {
        val sAccountId = AppUI.accountId
        val timeZone = UserUIContext.getUserTimeZone()
        val siteURL = AppUI.siteUrl

        when (type) {
            ProjectTypeConstants.BUG -> {
                val service = AppContextUtil.getSpringBean(BugService::class.java)
                val bug = service.findById(typeId, sAccountId)
                return ProjectTooltipGenerator.generateToolTipBug(UserUIContext.getUserLocale(), AppUI.dateFormat,
                        bug, siteURL, timeZone, false)
            }
            ProjectTypeConstants.TASK -> {
                val service = AppContextUtil.getSpringBean(TaskService::class.java)
                val task = service.findById(typeId, sAccountId)
                return ProjectTooltipGenerator.generateToolTipTask(UserUIContext.getUserLocale(), AppUI.dateFormat,
                        task, siteURL, timeZone, false)
            }
            ProjectTypeConstants.RISK -> {
                val service = AppContextUtil.getSpringBean(RiskService::class.java)
                val risk = service.findById(typeId, sAccountId)
                return ProjectTooltipGenerator.generateToolTipRisk(UserUIContext.getUserLocale(), AppUI.dateFormat,
                        risk, siteURL, timeZone, false)
            }
            ProjectTypeConstants.VERSION -> {
                val service = AppContextUtil.getSpringBean(VersionService::class.java)
                val version = service.findById(typeId, sAccountId)
                return ProjectTooltipGenerator.generateToolTipVersion(UserUIContext.getUserLocale(), AppUI.dateFormat,
                        version, siteURL, timeZone)
            }
            ProjectTypeConstants.COMPONENT -> {
                val service = AppContextUtil.getSpringBean(ComponentService::class.java)
                val component = service.findById(typeId, sAccountId)
                return ProjectTooltipGenerator.generateToolTipComponent(UserUIContext.getUserLocale(), component, siteURL, timeZone)
            }
            ProjectTypeConstants.STANDUP -> {
                val service = AppContextUtil.getSpringBean(StandupReportService::class.java)
                val standup = service.findById(typeId, sAccountId)
                return ProjectTooltipGenerator.generateToolTipStandUp(UserUIContext.getUserLocale(), AppUI.dateFormat,
                        standup, siteURL, timeZone)
            }
            else -> throw UnsupportedFeatureException("Not support tooltip with type")
        }
    }
}
