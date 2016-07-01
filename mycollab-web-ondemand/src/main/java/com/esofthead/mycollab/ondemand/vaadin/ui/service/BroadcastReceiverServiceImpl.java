package com.esofthead.mycollab.ondemand.vaadin.ui.service;

import com.esofthead.mycollab.core.BroadcastMessage;
import com.esofthead.mycollab.vaadin.ui.service.AbstractBroadcastReceiverService;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * @author MyCollab Ltd
 * @since 5.3.5
 */
@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class BroadcastReceiverServiceImpl extends AbstractBroadcastReceiverService {
    @Override
    protected void onBroadcast(BroadcastMessage message) {

    }
}
