package com.esofthead.mycollab.mobile.module.crm;

import com.esofthead.mycollab.vaadin.mvp.ScreenData;

/**
 * @author MyCollab Ltd.
 *
 * @since 4.4.0
 *
 */
public class CrmModuleScreenData {
	public static class GotoModule extends ScreenData<String[]> {
		public GotoModule(String... params) {
			super(params);
		}
	}
}
