package com.mycollab.pro.module.project.view.risk;

import com.mycollab.module.project.domain.SimpleRisk;
import com.mycollab.vaadin.events.HasEditFormHandlers;
import com.mycollab.vaadin.mvp.IFormAddView;
import com.mycollab.vaadin.web.ui.field.AttachmentUploadField;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
public interface RiskAddView extends IFormAddView<SimpleRisk> {
    HasEditFormHandlers<SimpleRisk> getEditFormHandlers();

    AttachmentUploadField getAttachUploadField();
}
