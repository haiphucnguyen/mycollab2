package com.esofthead.mycollab.vaadin.web.ui.service;

import com.esofthead.mycollab.core.BroadcastListener;
import com.esofthead.mycollab.web.DesktopApplication;

/**
 * @author MyCollab Ltd
 * @since 5.3.5
 */
public interface BroadcastReceiverService extends BroadcastListener {
    void registerApp(DesktopApplication myCollabApp);
}
