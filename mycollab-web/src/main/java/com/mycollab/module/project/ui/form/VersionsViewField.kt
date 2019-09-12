package com.mycollab.module.project.ui.form

import com.hp.gagawa.java.elements.A
import com.mycollab.common.i18n.OptionI18nEnum
import com.mycollab.core.utils.StringUtils
import com.mycollab.module.project.ProjectLinkGenerator
import com.mycollab.module.project.domain.Version
import com.mycollab.vaadin.ui.ELabel
import com.mycollab.vaadin.web.ui.WebThemes
import com.vaadin.flow.component.customfield.CustomField
import com.vaadin.flow.component.html.Div
import org.apache.commons.collections4.CollectionUtils

/**
 * @author MyCollab Ltd
 * @since 7.0.0
 */
class VersionsViewField : CustomField<Collection<Version>>() {

    private val containerLayout = Div()

    override fun setPresentationValue(versions: Collection<Version>?) {
        if (CollectionUtils.isNotEmpty(versions)) {
            versions?.forEach { containerLayout.add(buildVersionLink(it)) }
        }
    }

    override fun generateModelValue(): Collection<Version> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun buildVersionLink(version: Version): ELabel {
        val componentLink = A(ProjectLinkGenerator.generateVersionPreviewLink(version.projectid!!, version.id!!))
                .appendText(StringUtils.trim(version.name, 25, true))

        val lbl = ELabel.html(componentLink.write()).withStyleName(WebThemes.FIELD_NOTE)

        if (version.status != null && version.status == OptionI18nEnum.StatusI18nEnum.Closed.name) {
            lbl.addClassName(WebThemes.LINK_COMPLETED)
        }

        return lbl
    }

    override fun getValue(): Collection<Version>? = null
}
