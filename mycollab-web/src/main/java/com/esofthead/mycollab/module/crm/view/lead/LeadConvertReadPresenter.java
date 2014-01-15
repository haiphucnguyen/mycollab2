package com.esofthead.mycollab.module.crm.view.lead;

import com.esofthead.mycollab.module.crm.domain.SimpleLead;
import com.esofthead.mycollab.module.crm.view.CrmGenericPresenter;
import com.esofthead.mycollab.security.RolePermissionCollections;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.ui.NotificationUtil;
import com.vaadin.ui.ComponentContainer;

/**
 * 
 * @author MyCollab Ltd.
 * @since 3.0
 * 
 */
public class LeadConvertReadPresenter extends
		CrmGenericPresenter<LeadConvertReadView> {
	private static final long serialVersionUID = 1L;

	public LeadConvertReadPresenter() {
		super(LeadConvertReadView.class);
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		if (AppContext.canRead(RolePermissionCollections.CRM_LEAD)) {
			SimpleLead lead = (SimpleLead) data.getParams();
			super.onGo(container, data);
			view.displayConvertLeadInfo(lead);
		} else {
			NotificationUtil.showMessagePermissionAlert();
		}
	}
}
