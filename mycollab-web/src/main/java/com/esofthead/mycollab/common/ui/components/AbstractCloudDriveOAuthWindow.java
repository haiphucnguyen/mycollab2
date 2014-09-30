package com.esofthead.mycollab.common.ui.components;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.EventListener;

import com.esofthead.mycollab.eventmanager.ApplicationEvent;
import com.esofthead.mycollab.module.ecm.domain.ExternalDrive;
import com.vaadin.ui.Window;
import com.vaadin.util.ReflectTools;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.5.2
 *
 */
public abstract class AbstractCloudDriveOAuthWindow extends Window {
	private static final long serialVersionUID = 1L;

	public void addExternalDriveConnectedListener(
			ExternalDriveConnectedListener listener) {
		this.addListener(ExternalDriveConnectedEvent.VIEW_IDENTIFIER,
				ExternalDriveConnectedEvent.class, listener,
				ExternalDriveConnectedListener.viewInitMethod);
	}

	public static interface ExternalDriveConnectedListener extends
			EventListener, Serializable {
		public static final Method viewInitMethod = ReflectTools.findMethod(
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
