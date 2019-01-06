package com.mycollab.premium.mobile.module.project.view.risk

import com.mycollab.mobile.module.project.ui.form.field.ProjectFormAttachmentUploadField
import com.mycollab.module.project.domain.SimpleRisk
import com.mycollab.vaadin.mvp.IFormAddView

/**
 * @author MyCollab Ltd
 * @since 5.4.3
 */
interface RiskAddView : IFormAddView<SimpleRisk> {
    val attachUploadField: ProjectFormAttachmentUploadField
}
