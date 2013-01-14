package com.esofthead.mycollab.module.project.view.risk;

import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.module.project.domain.Risk;
import com.esofthead.mycollab.module.project.domain.SimpleRisk;
import com.esofthead.mycollab.module.project.events.RiskEvent;
import com.esofthead.mycollab.module.project.service.RiskService;
import com.esofthead.mycollab.vaadin.events.DefaultPreviewFormHandler;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.ComponentContainer;

public class RiskReadPresenter extends AbstractPresenter<RiskReadView> {

    private static final long serialVersionUID = 1L;

    public RiskReadPresenter() {
        super(RiskReadView.class);
        bind();
    }

    private void bind() {
        view.getPreviewFormHandlers().addFormHandler(
                new DefaultPreviewFormHandler<Risk>() {
                    @Override
                    public void onEdit(Risk data) {
                        EventBus.getInstance().fireEvent(
                                new RiskEvent.GotoEdit(this, data));
                    }

                    @Override
                    public void onDelete(Risk data) {
                        RiskService riskService = AppContext
                                .getSpringBean(RiskService.class);
                        riskService.removeWithSession(data.getId(),
                                AppContext.getUsername());
                        EventBus.getInstance().fireEvent(
                                new RiskEvent.GotoList(this, null));
                    }

                    @Override
                    public void onClone(Risk data) {
                        Risk cloneData = (Risk) data.copy();
                        cloneData.setId(null);
                        EventBus.getInstance().fireEvent(
                                new RiskEvent.GotoEdit(this, cloneData));
                    }

                    @Override
                    public void onCancel() {
                        EventBus.getInstance().fireEvent(
                                new RiskEvent.GotoList(this, null));
                    }
                });
    }

    @Override
    protected void onGo(ComponentContainer container, ScreenData<?> data) {
        RiskContainer riskContainer = (RiskContainer) container;
        riskContainer.removeAllComponents();
        riskContainer.addComponent(view.getWidget());

        if (data.getParams() instanceof Integer) {
            RiskService riskService = AppContext
                    .getSpringBean(RiskService.class);
            SimpleRisk risk = riskService.findRiskById((Integer) data
                    .getParams());
            view.previewItem(risk);
        } else {
            throw new MyCollabException("Unhanddle this case yet");
        }
    }
}
