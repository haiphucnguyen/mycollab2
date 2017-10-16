package com.mycollab.ondemand.vaadin.ui.service.impl;

import com.mycollab.core.BroadcastMessage;
import com.mycollab.vaadin.UserUIContext;
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
            UserUIContext context = getMyCollabApp().getAssociateContext();
            if (context.isMatchAccount(message.getsAccountId())) {
                getMyCollabApp().reloadPage();
            }
        }
    }
}
