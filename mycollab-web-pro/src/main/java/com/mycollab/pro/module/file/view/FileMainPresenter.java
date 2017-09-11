package com.mycollab.pro.module.file.view;

import com.mycollab.module.file.view.FileMainView;
import com.mycollab.security.AccessPermissionFlag;
import com.mycollab.security.RolePermissionCollections;
import com.mycollab.vaadin.AppUI;
import com.mycollab.vaadin.mvp.LoadPolicy;
import com.mycollab.vaadin.mvp.ScreenData;
import com.mycollab.vaadin.mvp.ViewPermission;
import com.mycollab.vaadin.mvp.ViewScope;
import com.mycollab.vaadin.web.ui.AbstractPresenter;
import com.vaadin.ui.HasComponents;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
@LoadPolicy(scope = ViewScope.PROTOTYPE)
@ViewPermission(permissionId = RolePermissionCollections.INSTANCE.getPUBLIC_DOCUMENT_ACCESS(), impliedPermissionVal = AccessPermissionFlag.Companion.getREAD_ONLY())
public class FileMainPresenter extends AbstractPresenter<FileMainView> {
    private static final long serialVersionUID = 1L;

    public FileMainPresenter() {
        super(FileMainView.class);
    }

    @Override
    protected void onGo(HasComponents container, ScreenData<?> data) {
        FileModule fileModule = (FileModule) container;
        fileModule.setContent(view);
        view.lazyLoadView();

        AppUI.addFragment("document/list", "Documents");
    }
}
