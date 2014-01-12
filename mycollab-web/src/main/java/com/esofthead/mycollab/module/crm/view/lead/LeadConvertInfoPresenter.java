package com.esofthead.mycollab.module.crm.view.lead;

import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.module.crm.domain.SimpleLead;
import com.esofthead.mycollab.module.crm.localization.CrmCommonI18nEnum;
import com.esofthead.mycollab.module.crm.view.CrmGenericPresenter;
import com.esofthead.mycollab.module.crm.view.CrmToolbar;
import com.esofthead.mycollab.security.RolePermissionCollections;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.mvp.ViewManager;
import com.esofthead.mycollab.vaadin.ui.NotificationUtil;
import com.vaadin.ui.ComponentContainer;

/**
 * 
 * @author MyCollab Ltd.
 * @since 3.0
 * 
 */
public class LeadConvertInfoPresenter extends
		CrmGenericPresenter<LeadConvertInfoView> {
	private static final long serialVersionUID = 1L;

	public LeadConvertInfoPresenter() {
		super(LeadConvertInfoView.class);
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		if (AppContext.canWrite(RolePermissionCollections.CRM_LEAD)) {
			CrmToolbar crmToolbar = ViewManager.getView(CrmToolbar.class);
			crmToolbar.gotoItem(LocalizationHelper
					.getMessage(CrmCommonI18nEnum.TOOLBAR_LEADS_HEADER));

			if (data.getParams() instanceof SimpleLead) {
				super.onGo(container, data);
				view.displayConvertLead((SimpleLead) data.getParams());
			} else {
				throw new MyCollabException("Do not support such param");
			}
		} else {
			NotificationUtil.showMessagePermissionAlert();
		}
	}
}
