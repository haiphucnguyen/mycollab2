package com.mycollab.module.project.ui.form

import com.hp.gagawa.java.elements.A
import com.mycollab.common.i18n.OptionI18nEnum
import com.mycollab.core.utils.StringUtils
import com.mycollab.module.project.ProjectLinkGenerator
import com.mycollab.module.project.domain.Component
import com.mycollab.vaadin.ui.ELabel
import com.mycollab.vaadin.web.ui.WebThemes
import com.vaadin.flow.component.customfield.CustomField
import com.vaadin.flow.component.html.Div
import com.vaadin.flow.component.html.Label
import org.apache.commons.collections4.CollectionUtils

/**
 * @author MyCollab Ltd
 * @since 7.0.0
 */
class ComponentsViewField : CustomField<Collection<Component>>() {

    private val containerLayout = Div()

    override fun setPresentationValue(components: Collection<Component>?) {
        if (CollectionUtils.isNotEmpty(components)) {
            components?.forEach { component -> containerLayout.add(buildComponentLink(component)) }
        }
    }

    override fun generateModelValue(): Collection<Component> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun buildComponentLink(component: Component): Label {
        val componentLink = A(ProjectLinkGenerator.generateComponentPreviewLink(component.projectid!!, component.id!!))
                .appendText(StringUtils.trim(component.name, 25, true))

        val lbl = ELabel.html(componentLink.write()).withStyleName(WebThemes.FIELD_NOTE)

        if (component.status != null && component.status == OptionI18nEnum.StatusI18nEnum.Closed.name) {
            lbl.addClassName(WebThemes.LINK_COMPLETED)
        }

        return lbl
    }
}
