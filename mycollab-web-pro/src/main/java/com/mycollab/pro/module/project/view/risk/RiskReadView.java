package com.mycollab.pro.module.project.view.risk;

import com.mycollab.module.project.domain.SimpleRisk;
import com.mycollab.vaadin.events.HasPreviewFormHandlers;
import com.mycollab.vaadin.mvp.IPreviewView;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
public interface RiskReadView extends IPreviewView<SimpleRisk> {

    HasPreviewFormHandlers<SimpleRisk> getPreviewFormHandlers();
}
