package com.esofthead.mycollab.module.file.view;

import com.esofthead.mycollab.security.AccessPermissionFlag;
import com.esofthead.mycollab.security.RolePermissionCollections;
import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.mvp.ViewPermission;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.ComponentContainer;

@ViewPermission(permissionId = RolePermissionCollections.PUBLIC_DOCUMENT_ACCESS, impliedPermissionVal = AccessPermissionFlag.READ_ONLY)
public class FileMainPresenter extends AbstractPresenter<FileMainView> {
	private static final long serialVersionUID = 1L;

	public FileMainPresenter() {
		super(FileMainView.class);
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		FileModule fileModule = (FileModule) container;
		fileModule.removeAllComponents();

		fileModule.addComponent(view);
		view.display();

		AppContext.addFragment("document/list", "Documents");
	}

}
