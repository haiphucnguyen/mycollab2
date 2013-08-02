package com.esofthead.mycollab.module.project.view.risk;

import com.esofthead.mycollab.module.project.domain.SimpleRisk;
import com.esofthead.mycollab.vaadin.events.HasPreviewFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.IPreviewView;

public interface RiskReadView extends IPreviewView<SimpleRisk> {

	HasPreviewFormHandlers<SimpleRisk> getPreviewFormHandlers();
}
