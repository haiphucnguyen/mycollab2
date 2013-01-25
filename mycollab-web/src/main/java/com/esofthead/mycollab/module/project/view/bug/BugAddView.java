package com.esofthead.mycollab.module.project.view.bug;

import com.esofthead.mycollab.module.tracker.domain.Component;
import com.esofthead.mycollab.module.tracker.domain.SimpleBug;
import com.esofthead.mycollab.module.tracker.domain.Version;
import com.esofthead.mycollab.vaadin.events.HasEditFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.IFormAddView;
import com.esofthead.mycollab.vaadin.ui.DefaultFormViewFieldFactory.AttachmentUploadField;
import java.util.List;

public interface BugAddView extends IFormAddView<SimpleBug> {

    HasEditFormHandlers<SimpleBug> getEditFormHandlers();

    AttachmentUploadField getAttachUploadField();
    
    List<Component> getComponents();
    
    List<Version> getAffectedVersions();
    
    List<Version> getFixedVersion();
}
