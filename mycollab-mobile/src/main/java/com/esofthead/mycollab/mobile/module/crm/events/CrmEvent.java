package com.esofthead.mycollab.mobile.module.crm.events;

import com.esofthead.mycollab.eventmanager.ApplicationEvent;

/**
 * @author MyCollab Ltd.
 * @since 3.0
 */
public class CrmEvent {

    public static class GotoHome extends ApplicationEvent {
        public GotoHome(Object source, Object data) {
            super(source, data);
        }
    }
}