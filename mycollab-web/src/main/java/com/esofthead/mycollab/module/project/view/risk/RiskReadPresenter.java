package com.esofthead.mycollab.module.project.view.risk;

import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.module.project.domain.Risk;
import com.esofthead.mycollab.module.project.domain.SimpleProject;
import com.esofthead.mycollab.module.project.domain.SimpleRisk;
import com.esofthead.mycollab.module.project.domain.criteria.RiskSearchCriteria;
import com.esofthead.mycollab.module.project.events.RiskEvent;
import com.esofthead.mycollab.module.project.service.RiskService;
import com.esofthead.mycollab.vaadin.events.DefaultPreviewFormHandler;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.Window;

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

                    @Override
                    public void gotoNext(Risk data) {
                        RiskService riskeService = AppContext
                                .getSpringBean(RiskService.class);
                        RiskSearchCriteria criteria = new RiskSearchCriteria();
                        SimpleProject project = (SimpleProject) AppContext
                                .getVariable("project");
                        criteria.setProjectId(new NumberSearchField(
                                SearchField.AND, project.getId()));
                        criteria.setId(new NumberSearchField(data.getId(),
                                NumberSearchField.GREATHER));
                        Integer nextId = riskeService.getNextItemKey(criteria);
                        if (nextId != null) {
                            EventBus.getInstance().fireEvent(
                                    new RiskEvent.GotoRead(this, nextId));
                        } else {
                            view.getWindow().showNotification("Information",
                                    "You are already in the last record",
                                    Window.Notification.TYPE_HUMANIZED_MESSAGE);
                        }

                    }

                    @Override
                    public void gotoPrevious(Risk data) {
                        RiskService riskeService = AppContext
                                .getSpringBean(RiskService.class);
                        RiskSearchCriteria criteria = new RiskSearchCriteria();
                        SimpleProject project = (SimpleProject) AppContext
                                .getVariable("project");
                        criteria.setProjectId(new NumberSearchField(
                                SearchField.AND, project.getId()));
                        criteria.setId(new NumberSearchField(data.getId(),
                                NumberSearchField.LESSTHAN));
                        Integer nextId = riskeService
                                .getPreviousItemKey(criteria);
                        if (nextId != null) {
                            EventBus.getInstance().fireEvent(
                                    new RiskEvent.GotoRead(this, nextId));
                        } else {
                            view.getWindow().showNotification("Information",
                                    "You are already in the first record",
                                    Window.Notification.TYPE_HUMANIZED_MESSAGE);
                        }
                    }
                });
    }

    @Override
    protected void onGo(ComponentContainer container, ScreenData<?> data) {
        if (data.getParams() instanceof Integer) {
            RiskService riskService = AppContext
                    .getSpringBean(RiskService.class);
            SimpleRisk risk = riskService.findRiskById((Integer) data
                    .getParams());
            if (risk != null) {
                RiskContainer riskContainer = (RiskContainer) container;
                riskContainer.removeAllComponents();
                riskContainer.addComponent(view.getWidget());
                view.previewItem(risk);
            } else {
                AppContext.getApplication().getMainWindow().showNotification("Information", "The record is not existed", Window.Notification.TYPE_HUMANIZED_MESSAGE);
                return;
            }
        } else {
            throw new MyCollabException("Unhanddle this case yet");
        }
    }
}
