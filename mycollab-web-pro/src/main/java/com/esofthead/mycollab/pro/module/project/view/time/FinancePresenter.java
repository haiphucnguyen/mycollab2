package com.esofthead.mycollab.pro.module.project.view.time;

import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.module.project.ProjectTypeConstants;
import com.esofthead.mycollab.module.project.view.ProjectView;
import com.esofthead.mycollab.module.project.view.parameters.InvoiceScreenData;
import com.esofthead.mycollab.module.project.view.parameters.TimeTrackingScreenData;
import com.esofthead.mycollab.module.project.view.time.IFinanceContainer;
import com.esofthead.mycollab.module.project.view.time.IFinancePresenter;
import com.esofthead.mycollab.vaadin.mvp.PresenterResolver;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.web.ui.AbstractPresenter;
import com.vaadin.ui.ComponentContainer;

/**
 * @author MyCollab Ltd
 * @since 2.0
 */
public class FinancePresenter extends AbstractPresenter<IFinanceContainer> implements IFinancePresenter {
    private static final long serialVersionUID = 1L;

    public FinancePresenter() {
        super(IFinanceContainer.class);
    }

    @Override
    protected void onGo(ComponentContainer container, ScreenData<?> data) {
        ProjectView projectViewContainer = (ProjectView) container;
        AbstractPresenter presenter;

        if (data instanceof TimeTrackingScreenData.Search) {
            projectViewContainer.gotoSubView(ProjectTypeConstants.FINANCE);
            presenter = PresenterResolver.getPresenter(TimeTrackingListPresenter.class);
        } else if (data instanceof InvoiceScreenData.GotoInvoiceList) {
            projectViewContainer.gotoSubView(ProjectTypeConstants.FINANCE);
            presenter = PresenterResolver.getPresenter(InvoicePresenter.class);
        } else {
            throw new MyCollabException("No support screen data " + data);
        }

        presenter.go(view, data);
    }
}
