package com.mycollab.premium.mobile.module.project.view.risk;

import com.mycollab.core.MyCollabException;
import com.mycollab.mobile.module.project.view.parameters.RiskScreenData;
import com.mycollab.mobile.module.project.view.risk.IRiskContainer;
import com.mycollab.mobile.module.project.view.risk.IRiskPresenter;
import com.mycollab.mobile.mvp.AbstractPresenter;
import com.mycollab.module.project.CurrentProjectVariables;
import com.mycollab.module.project.ProjectRolePermissionCollections;
import com.mycollab.vaadin.mvp.IPresenter;
import com.mycollab.vaadin.mvp.PresenterResolver;
import com.mycollab.vaadin.mvp.ScreenData;
import com.mycollab.vaadin.ui.NotificationUtil;
import com.vaadin.ui.HasComponents;

/**
 * @author MyCollab Ltd
 * @since 5.4.3
 */
public class RiskPresenter extends AbstractPresenter<IRiskContainer> implements IRiskPresenter {
    public RiskPresenter() {
        super(IRiskContainer.class);
    }

    @Override
    protected void onGo(HasComponents container, ScreenData<?> data) {
        if (CurrentProjectVariables.canRead(ProjectRolePermissionCollections.RISKS)) {
            IPresenter<?> presenter;

            if (data instanceof RiskScreenData.Read) {
                presenter = PresenterResolver.getPresenter(RiskReadPresenter.class);
            } else if (data instanceof RiskScreenData.Add || data instanceof RiskScreenData.Edit) {
                presenter = PresenterResolver.getPresenter(RiskAddPresenter.class);
            } else {
                throw new MyCollabException("Do not support param: " + data);
            }
            presenter.go(container, data);
        } else {
            NotificationUtil.showMessagePermissionAlert();
        }
    }
}
