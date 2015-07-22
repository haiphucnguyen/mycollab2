package com.esofthead.mycollab.premium.module.file.view;

import com.esofthead.mycollab.eventmanager.ApplicationEvent;
import com.esofthead.mycollab.module.ecm.domain.ExternalDrive;
import com.vaadin.ui.Window;
import com.vaadin.util.ReflectTools;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.EventListener;

/**
 * @author MyCollab Ltd
 * @since 5.1.1
 */
public class AbstractCloudDriveOAuthWindow extends Window {
    private static final long serialVersionUID = 1L;

    public void addExternalDriveConnectedListener(
            ExternalDriveConnectedListener listener) {
        this.addListener(ExternalDriveConnectedEvent.VIEW_IDENTIFIER,
                ExternalDriveConnectedEvent.class, listener,
                ExternalDriveConnectedListener.viewInitMethod);
    }

    public interface ExternalDriveConnectedListener extends EventListener, Serializable {
        Method viewInitMethod = ReflectTools.findMethod(
                ExternalDriveConnectedListener.class, "connectedSuccess",
                ExternalDriveConnectedEvent.class);

        void connectedSuccess(ExternalDriveConnectedEvent event);
    }

    public static class ExternalDriveConnectedEvent extends ApplicationEvent {
        private static final long serialVersionUID = 1L;

        public static final String VIEW_IDENTIFIER = "externalDriveConnectedSuccess";

        public ExternalDriveConnectedEvent(Object source, ExternalDrive data) {
            super(source, data);
        }
    }
}
