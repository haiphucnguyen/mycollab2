package com.esofthead.mycollab.module.crm.view.activity;

import com.esofthead.mycollab.module.crm.domain.CallWithBLOBs;
import com.esofthead.mycollab.vaadin.events.HasEditFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.IFormAddView;

public interface CallAddView extends IFormAddView<CallWithBLOBs> {
	HasEditFormHandlers<CallWithBLOBs> getEditFormHandlers();

}
