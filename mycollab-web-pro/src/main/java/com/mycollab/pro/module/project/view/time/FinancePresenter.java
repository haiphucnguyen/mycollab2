package com.mycollab.pro.module.project.view.time;

import com.mycollab.core.MyCollabException;
import com.mycollab.module.project.CurrentProjectVariables;
import com.mycollab.module.project.ProjectTypeConstants;
import com.mycollab.module.project.view.ProjectView;
import com.mycollab.module.project.view.parameters.InvoiceScreenData;
import com.mycollab.module.project.view.parameters.TimeTrackingScreenData;
import com.mycollab.module.project.view.time.IFinanceContainer;
import com.mycollab.module.project.view.time.IFinancePresenter;
import com.mycollab.vaadin.mvp.PresenterResolver;
import com.mycollab.vaadin.mvp.ScreenData;
import com.mycollab.vaadin.web.ui.AbstractPresenter;
import com.vaadin.ui.HasComponents;

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
    protected void onGo(HasComponents container, ScreenData<?> data) {
        ProjectView projectViewContainer = (ProjectView) container;
        projectViewContainer.gotoSubView(ProjectTypeConstants.FINANCE);
        AbstractPresenter presenter;

        if (data instanceof TimeTrackingScreenData.Search) {
            presenter = PresenterResolver.getPresenter(TimeTrackingListPresenter.class);
            presenter.go(view, data);
        } else if (data instanceof InvoiceScreenData.GotoInvoiceList) {
            presenter = PresenterResolver.getPresenter(InvoicePresenter.class);
            presenter.go(view, data);
        } else {
            if (CurrentProjectVariables.hasTimeFeature()) {
                view.showTimeView();
            } else if (CurrentProjectVariables.hasInvoiceFeature()) {
                view.showInvoiceView();
            } else {
                throw new MyCollabException("Not support screen data type null");
            }
        }
    }
}
