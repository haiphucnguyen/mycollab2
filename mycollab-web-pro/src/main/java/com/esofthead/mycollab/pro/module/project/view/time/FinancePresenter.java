package com.esofthead.mycollab.pro.module.project.view.time;

import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.core.db.query.DateParam;
import com.esofthead.mycollab.core.db.query.VariableInjecter;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectTypeConstants;
import com.esofthead.mycollab.module.project.domain.criteria.ItemTimeLoggingSearchCriteria;
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
            FinanceContainer financeContainer = (FinanceContainer) projectViewContainer.gotoSubView(ProjectTypeConstants.FINANCE);
            financeContainer.initContent();
            if (CurrentProjectVariables.hasTimeFeature()) {
                ItemTimeLoggingSearchCriteria searchCriteria = new ItemTimeLoggingSearchCriteria();
                searchCriteria.setProjectIds(new SetSearchField<>(CurrentProjectVariables.getProjectId()));
                searchCriteria.addExtraField(DateParam.inRangeDate(ItemTimeLoggingSearchCriteria.p_logDates,
                        VariableInjecter.THIS_WEEK));
                presenter = PresenterResolver.getPresenter(TimeTrackingListPresenter.class);
                presenter.go(view, new TimeTrackingScreenData.Search(searchCriteria));
                return;
            } else if (CurrentProjectVariables.hasInvoiceFeature()) {
                presenter = PresenterResolver.getPresenter(InvoicePresenter.class);
                presenter.go(view, null);
                return;
            } else {
                throw new MyCollabException("Not support screen data type null");
            }
        }

        presenter.go(view, data);
    }
}