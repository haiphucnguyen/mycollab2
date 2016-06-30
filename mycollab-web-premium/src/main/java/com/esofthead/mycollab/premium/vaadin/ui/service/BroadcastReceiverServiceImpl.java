package com.esofthead.mycollab.premium.vaadin.ui.service;

import com.esofthead.mycollab.core.BroadcastMessage;
import com.esofthead.mycollab.vaadin.ui.service.AbstractBroadcastReceiverService;
import com.esofthead.mycollab.vaadin.ui.service.BroadcastReceiverService;
import org.springframework.stereotype.Service;

/**
 * @author MyCollab Ltd
 * @since 5.3.5
 */
public class BroadcastReceiverServiceImpl extends AbstractBroadcastReceiverService implements BroadcastReceiverService {
    @Override
    protected void onBroadcast(BroadcastMessage message) {

    }
}
