package com.mycollab.mobile.module.crm.view.activity;

import com.mycollab.module.crm.domain.SimpleMeeting;
import com.mycollab.vaadin.events.HasPreviewFormHandlers;
import com.mycollab.vaadin.mvp.IPreviewView;

/**
 * @author MyCollab Ltd.
 * @since 4.1
 */
public interface MeetingReadView extends IPreviewView<SimpleMeeting> {
    HasPreviewFormHandlers<SimpleMeeting> getPreviewFormHandlers();
}
