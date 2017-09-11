package com.mycollab.pro.module.project.view.risk;

import com.mycollab.core.MyCollabException;
import com.mycollab.module.project.ProjectTypeConstants;
import com.mycollab.module.project.view.ProjectView;
import com.mycollab.module.project.view.parameters.RiskScreenData;
import com.mycollab.module.project.view.risk.IRiskContainer;
import com.mycollab.module.project.view.risk.IRiskPresenter;
import com.mycollab.module.project.view.ticket.TicketContainer;
import com.mycollab.vaadin.mvp.PresenterResolver;
import com.mycollab.vaadin.mvp.ScreenData;
import com.mycollab.vaadin.web.ui.AbstractPresenter;
import com.vaadin.ui.HasComponents;

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
    protected void onGo(HasComponents container, ScreenData<?> data) {
        ProjectView projectViewContainer = (ProjectView) container;
        TicketContainer ticketContainer = (TicketContainer) projectViewContainer.gotoSubView(ProjectTypeConstants.INSTANCE.getTICKET());
        ticketContainer.setContent(view);

        AbstractPresenter presenter;

        if (data instanceof RiskScreenData.Add || data instanceof RiskScreenData.Edit) {
            presenter = PresenterResolver.getPresenter(RiskAddPresenter.class);
        } else if (data instanceof RiskScreenData.Read) {
            presenter = PresenterResolver.getPresenter(RiskReadPresenter.class);
        } else {
            throw new MyCollabException("No support screen data " + data);
        }

        presenter.go(view, data);
    }
}
