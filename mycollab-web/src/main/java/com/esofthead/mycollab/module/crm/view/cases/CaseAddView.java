package com.esofthead.mycollab.module.crm.view.cases;

import com.esofthead.mycollab.module.crm.domain.CaseWithBLOBs;
import com.esofthead.mycollab.vaadin.events.HasEditFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.IFormAddView;

public interface CaseAddView  extends IFormAddView<CaseWithBLOBs> {
	HasEditFormHandlers<CaseWithBLOBs> getEditFormHandlers();
}
