package com.esofthead.mycollab.pro.module.project.view.risk;

import com.esofthead.mycollab.module.project.domain.SimpleRisk;
import com.esofthead.mycollab.vaadin.events.HasEditFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.IFormAddView;
import com.esofthead.mycollab.vaadin.web.ui.field.AttachmentUploadField;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
public interface RiskAddView extends IFormAddView<SimpleRisk> {
    HasEditFormHandlers<SimpleRisk> getEditFormHandlers();

    AttachmentUploadField getAttachUploadField();
}
