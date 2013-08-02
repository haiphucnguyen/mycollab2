package com.esofthead.mycollab.module.crm.view.file;

import com.esofthead.mycollab.module.crm.localization.CrmCommonI18nEnum;
import com.esofthead.mycollab.module.crm.view.CrmGenericPresenter;
import com.esofthead.mycollab.module.crm.view.CrmToolbar;
import com.esofthead.mycollab.module.file.domain.criteria.FileSearchCriteria;
import com.esofthead.mycollab.module.user.RolePermissionCollections;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.mvp.ViewManager;
import com.esofthead.mycollab.vaadin.ui.MessageConstants;
import com.esofthead.mycollab.web.AppContext;
import com.esofthead.mycollab.web.LocalizationHelper;
import com.vaadin.ui.ComponentContainer;

public class FileSearchResultPresenter extends
		CrmGenericPresenter<FileSearchResultView> {
	private static final long serialVersionUID = 1L;

	public FileSearchResultPresenter() {
		super(FileSearchResultView.class);
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		if (AppContext.canRead(RolePermissionCollections.CRM_DOCUMENT)) {
			CrmToolbar crmToolbar = ViewManager.getView(CrmToolbar.class);
			crmToolbar.gotoItem(LocalizationHelper
					.getMessage(CrmCommonI18nEnum.TOOLBAR_DOCUMENT_HEADER));

			super.onGo(container, data);
			view.displaySearchResult((FileSearchCriteria) data.getParams());
		} else {
			MessageConstants.showMessagePermissionAlert();
		}

	}

}
