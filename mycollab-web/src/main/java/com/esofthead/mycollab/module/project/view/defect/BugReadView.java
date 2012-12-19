package com.esofthead.mycollab.module.project.view.defect;

import com.esofthead.mycollab.module.tracker.domain.Bug;
import com.esofthead.mycollab.module.tracker.domain.SimpleBug;
import com.esofthead.mycollab.vaadin.events.HasPreviewFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.IPreviewView;

public interface BugReadView extends IPreviewView<SimpleBug> {
	HasPreviewFormHandlers<Bug> getPreviewFormHandlers();

}
