package com.mycollab.premium.mobile.module.project.view.risk;

import com.mycollab.module.project.domain.SimpleRisk;
import com.mycollab.vaadin.event.HasPreviewFormHandlers;
import com.mycollab.vaadin.mvp.IPreviewView;

/**
 * @author MyCollab Ltd
 * @since 5.4.3
 */
public interface RiskReadView extends IPreviewView<SimpleRisk> {
    HasPreviewFormHandlers<SimpleRisk> getPreviewFormHandlers();
}
