package com.esofthead.mycollab.pro.module.project.view.risk;

import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.module.project.ProjectTypeConstants;
import com.esofthead.mycollab.module.project.view.ProjectView;
import com.esofthead.mycollab.module.project.view.parameters.RiskScreenData;
import com.esofthead.mycollab.module.project.view.risk.IRiskContainer;
import com.esofthead.mycollab.module.project.view.risk.IRiskPresenter;
import com.esofthead.mycollab.vaadin.mvp.PresenterResolver;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.web.ui.AbstractPresenter;
import com.vaadin.ui.ComponentContainer;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
public class RiskPresenter extends AbstractPresenter<IRiskContainer> implements IRiskPresenter {
    private static final long serialVersionUID = 1L;

    public RiskPresenter() {
        super(IRiskContainer.class);
    }

    @Override
    protected void onGo(ComponentContainer container, ScreenData<?> data) {
        ProjectView projectViewContainer = (ProjectView) container;
        projectViewContainer.gotoSubView(ProjectTypeConstants.RISK);

        view.removeAllComponents();
        AbstractPresenter presenter;

        if (data instanceof RiskScreenData.Search) {
            presenter = PresenterResolver.getPresenter(RiskListPresenter.class);
        } else if (data instanceof RiskScreenData.Add || data instanceof RiskScreenData.Edit) {
            presenter = PresenterResolver.getPresenter(RiskAddPresenter.class);
        } else if (data instanceof RiskScreenData.Read) {
            presenter = PresenterResolver.getPresenter(RiskReadPresenter.class);
        } else {
            throw new MyCollabException("No support screen data " + data);
        }

        presenter.go(view, data);
    }
}
