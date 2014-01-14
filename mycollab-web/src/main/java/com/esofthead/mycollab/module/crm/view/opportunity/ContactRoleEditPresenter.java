package com.esofthead.mycollab.module.crm.view.opportunity;

import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.module.crm.domain.Opportunity;
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
public class ContactRoleEditPresenter extends
		CrmGenericPresenter<ContactRoleEditView> {
	private static final long serialVersionUID = 1L;

	public ContactRoleEditPresenter() {
		super(ContactRoleEditView.class);
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		if (AppContext.canWrite(RolePermissionCollections.CRM_OPPORTUNITY)) {
			CrmToolbar crmToolbar = ViewManager.getView(CrmToolbar.class);
			crmToolbar.gotoItem(LocalizationHelper
					.getMessage(CrmCommonI18nEnum.TOOLBAR_CONTACTS_HEADER));

			Opportunity opportunity = (Opportunity) data.getParams();
			super.onGo(container, data);
			view.display(opportunity);

			AppContext.addFragment("crm/opportunity/addcontactroles",
					"Add Contact Roles");
		} else {
			NotificationUtil.showMessagePermissionAlert();
		}

	}

}
