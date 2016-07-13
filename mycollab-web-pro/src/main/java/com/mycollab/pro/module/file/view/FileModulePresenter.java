package com.mycollab.pro.module.file.view;

import com.mycollab.common.ModuleNameConstants;
import com.mycollab.module.file.view.IFileModule;
import com.mycollab.module.file.view.IFileModulePresenter;
import com.mycollab.shell.view.MainView;
import com.mycollab.vaadin.AppContext;
import com.mycollab.vaadin.mvp.LoadPolicy;
import com.mycollab.vaadin.mvp.PresenterResolver;
import com.mycollab.vaadin.mvp.ScreenData;
import com.mycollab.vaadin.mvp.ViewScope;
import com.mycollab.vaadin.web.ui.AbstractPresenter;
import com.vaadin.ui.ComponentContainer;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
@LoadPolicy(scope = ViewScope.PROTOTYPE)
public class FileModulePresenter extends AbstractPresenter<IFileModule> implements IFileModulePresenter {
    private static final long serialVersionUID = 1L;

    public FileModulePresenter() {
        super(IFileModule.class);
    }

    @Override
    protected void onGo(ComponentContainer container, ScreenData<?> data) {
        MainView mainView = (MainView) container;
        mainView.addModule(view);

        FileMainPresenter mainPresenter = PresenterResolver.getPresenter(FileMainPresenter.class);
        mainPresenter.go(view, null);
        AppContext.updateLastModuleVisit(ModuleNameConstants.FILE);
    }
}