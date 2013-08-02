package com.esofthead.mycollab.module.crm.view.activity;

import com.esofthead.mycollab.module.crm.domain.SimpleCall;
import com.esofthead.mycollab.vaadin.events.HasPreviewFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.IPreviewView;

public interface CallReadView  extends IPreviewView<SimpleCall> {
	HasPreviewFormHandlers<SimpleCall> getPreviewFormHandlers();

}
