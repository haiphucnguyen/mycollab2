package com.esofthead.mycollab.pro.module.file.view;

import com.esofthead.mycollab.common.ModuleNameConstants;
import com.esofthead.mycollab.module.file.view.IFileModule;
import com.esofthead.mycollab.module.file.view.IFileModulePresenter;
import com.esofthead.mycollab.shell.view.MainView;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.mvp.LoadPolicy;
import com.esofthead.mycollab.vaadin.mvp.PresenterResolver;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.mvp.ViewScope;
import com.esofthead.mycollab.vaadin.web.ui.AbstractPresenter;
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
        AppContext.getInstance().updateLastModuleVisit(ModuleNameConstants.FILE);
    }
}