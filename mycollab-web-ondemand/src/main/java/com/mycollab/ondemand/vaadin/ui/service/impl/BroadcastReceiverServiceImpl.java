package com.mycollab.ondemand.vaadin.ui.service.impl;

import com.mycollab.core.BroadcastMessage;
import com.mycollab.vaadin.AppContext;
import com.mycollab.vaadin.web.ui.service.AbstractBroadcastReceiverService;
import org.springframework.stereotype.Service;

/**
 * @author MyCollab Ltd
 * @since 5.3.5
 */
@Service
public class BroadcastReceiverServiceImpl extends AbstractBroadcastReceiverService {
    @Override
    protected void onBroadcast(BroadcastMessage message) {
        if (message.getsAccountId() != null) {
            AppContext context = myCollabApp.getAssociateContext();
            if (context.isMatchAccount(message.getsAccountId())) {
                myCollabApp.reloadPage();
            }
        }
    }
}