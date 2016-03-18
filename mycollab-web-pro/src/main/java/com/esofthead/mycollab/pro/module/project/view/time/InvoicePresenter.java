package com.esofthead.mycollab.pro.module.project.view.time;

import com.esofthead.mycollab.module.project.i18n.ProjectCommonI18nEnum;
import com.esofthead.mycollab.module.project.view.ProjectBreadcrumb;
import com.esofthead.mycollab.module.project.view.time.IInvoiceContainer;
import com.esofthead.mycollab.module.project.view.time.IInvoiceListPresenter;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.mvp.ViewManager;
import com.esofthead.mycollab.vaadin.web.ui.AbstractPresenter;
import com.vaadin.ui.ComponentContainer;

/**
 * @author MyCollab Ltd
 * @since 5.2.10
 */
public class InvoicePresenter extends AbstractPresenter<IInvoiceContainer> implements IInvoiceListPresenter {
    public InvoicePresenter() {
        super(IInvoiceContainer.class);
    }

    @Override
    protected void onGo(ComponentContainer container, ScreenData<?> data) {
        FinanceContainer timeContainer = (FinanceContainer) container;
        timeContainer.gotoSubView(AppContext.getMessage(ProjectCommonI18nEnum.VIEW_INVOICE));

        view.display();

        ProjectBreadcrumb breadCrumb = ViewManager.getCacheComponent(ProjectBreadcrumb.class);
        breadCrumb.gotoInvoiceView();
    }
}