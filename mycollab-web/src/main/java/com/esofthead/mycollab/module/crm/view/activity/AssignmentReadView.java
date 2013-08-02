package com.esofthead.mycollab.module.crm.view.activity;

import com.esofthead.mycollab.module.crm.domain.SimpleTask;
import com.esofthead.mycollab.vaadin.events.HasPreviewFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.IPreviewView;

public interface AssignmentReadView extends IPreviewView<SimpleTask> {

    HasPreviewFormHandlers<SimpleTask> getPreviewFormHandlers();
}
