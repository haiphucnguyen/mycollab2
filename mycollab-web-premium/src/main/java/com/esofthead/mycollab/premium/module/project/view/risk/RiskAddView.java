package com.esofthead.mycollab.premium.module.project.view.risk;

import com.esofthead.mycollab.module.project.domain.Risk;
import com.esofthead.mycollab.vaadin.events.HasEditFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.IFormAddView;

public interface RiskAddView extends IFormAddView<Risk> {
	HasEditFormHandlers<Risk> getEditFormHandlers();

}
