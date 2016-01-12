package com.esofthead.mycollab.premium.module.file.view;

import com.esofthead.mycollab.module.file.view.FileMainView;
import com.esofthead.mycollab.security.AccessPermissionFlag;
import com.esofthead.mycollab.security.RolePermissionCollections;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.mvp.LoadPolicy;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.mvp.ViewPermission;
import com.esofthead.mycollab.vaadin.mvp.ViewScope;
import com.esofthead.mycollab.vaadin.web.ui.AbstractPresenter;
import com.vaadin.ui.ComponentContainer;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 * 
 */
@LoadPolicy(scope = ViewScope.PROTOTYPE)
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
