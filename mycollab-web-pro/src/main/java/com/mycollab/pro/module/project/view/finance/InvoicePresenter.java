package com.mycollab.pro.module.project.view.finance;

import com.mycollab.module.project.view.ProjectBreadcrumb;
import com.mycollab.module.project.view.ProjectView;
import com.mycollab.module.project.view.finance.IInvoiceContainer;
import com.mycollab.module.project.view.finance.IInvoiceListPresenter;
import com.mycollab.vaadin.mvp.ScreenData;
import com.mycollab.vaadin.mvp.ViewManager;
import com.mycollab.vaadin.web.ui.AbstractPresenter;
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
        ProjectView projectView = (ProjectView) container;
        projectView.gotoSubView(ProjectView.INVOICE_ENTRY, view);
        view.display();

        ProjectBreadcrumb breadCrumb = ViewManager.getCacheComponent(ProjectBreadcrumb.class);
        breadCrumb.gotoInvoiceView();
    }
}
