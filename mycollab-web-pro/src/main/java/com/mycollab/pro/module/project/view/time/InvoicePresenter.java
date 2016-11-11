package com.mycollab.pro.module.project.view.time;

import com.mycollab.module.project.i18n.InvoiceI18nEnum;
import com.mycollab.module.project.view.ProjectBreadcrumb;
import com.mycollab.module.project.view.time.IInvoiceContainer;
import com.mycollab.module.project.view.time.IInvoiceListPresenter;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.mvp.ScreenData;
import com.mycollab.vaadin.mvp.ViewManager;
import com.mycollab.vaadin.web.ui.AbstractPresenter;
import com.mycollab.vaadin.web.ui.TabSheetDecorator;
import com.vaadin.ui.HasComponents;

/**
 * @author MyCollab Ltd
 * @since 5.2.10
 */
public class InvoicePresenter extends AbstractPresenter<IInvoiceContainer> implements IInvoiceListPresenter {
    public InvoicePresenter() {
        super(IInvoiceContainer.class);
    }

    @Override
    protected void onGo(HasComponents container, ScreenData<?> data) {
        FinanceContainer timeContainer = (FinanceContainer) container;
        TabSheetDecorator.WrappedTab contentLayout = (TabSheetDecorator.WrappedTab) timeContainer.gotoSubView(
                UserUIContext.getMessage(InvoiceI18nEnum.LIST));
        contentLayout.addView(view);
        view.display();

        ProjectBreadcrumb breadCrumb = ViewManager.getCacheComponent(ProjectBreadcrumb.class);
        breadCrumb.gotoInvoiceView();
    }
}
