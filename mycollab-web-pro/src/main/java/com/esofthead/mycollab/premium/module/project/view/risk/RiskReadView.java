package com.esofthead.mycollab.premium.module.project.view.risk;

import com.esofthead.mycollab.module.project.domain.SimpleRisk;
import com.esofthead.mycollab.vaadin.events.HasPreviewFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.IPreviewView;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
public interface RiskReadView extends IPreviewView<SimpleRisk> {

    HasPreviewFormHandlers<SimpleRisk> getPreviewFormHandlers();
}
