package com.esofthead.mycollab.module.project.events;

import com.esofthead.mycollab.eventmanager.ApplicationEvent;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.4.0
 *
 */
public class CustomizeUIEvent {
	public static class UpdateFeaturesList extends ApplicationEvent {
		private static final long serialVersionUID = 1L;

		public UpdateFeaturesList(Object source) {
			super(source, null);
		}
	}
}
