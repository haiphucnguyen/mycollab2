/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.milestone;

import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.module.project.domain.Milestone;
import com.esofthead.mycollab.module.project.domain.SimpleMilestone;
import com.esofthead.mycollab.module.project.events.MilestoneEvent;
import com.esofthead.mycollab.module.project.service.MilestoneService;
import com.esofthead.mycollab.vaadin.events.DefaultPreviewFormHandler;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.ComponentContainer;

/**
 *
 * @author haiphucnguyen
 */
public class MilestoneReadPresenter extends AbstractPresenter<MilestoneReadView> {

    private static final long serialVersionUID = 1L;

    public MilestoneReadPresenter() {
        super(MilestoneReadView.class);
        bind();
    }

    private void bind() {
        view.getPreviewFormHandlers().addFormHandler(
                new DefaultPreviewFormHandler<Milestone>() {
                    @Override
                    public void onEdit(Milestone data) {
                        EventBus.getInstance().fireEvent(
                                new MilestoneEvent.GotoEdit(this, data));
                    }

                    @Override
                    public void onDelete(Milestone data) {
                        MilestoneService riskService = AppContext
                                .getSpringBean(MilestoneService.class);
                        riskService.removeWithSession(data.getId(),
                                AppContext.getUsername());
                        EventBus.getInstance().fireEvent(
                                new MilestoneEvent.GotoList(this, null));
                    }

                    @Override
                    public void onClone(Milestone data) {
                        Milestone cloneData = (Milestone) data.copy();
                        cloneData.setId(null);
                        EventBus.getInstance().fireEvent(
                                new MilestoneEvent.GotoEdit(this, cloneData));
                    }

                    @Override
                    public void onCancel() {
                        EventBus.getInstance().fireEvent(
                                new MilestoneEvent.GotoList(this, null));
                    }
                });
    }

    @Override
    protected void onGo(ComponentContainer container, ScreenData<?> data) {
        MilestoneContainer riskContainer = (MilestoneContainer) container;
        riskContainer.removeAllComponents();
        riskContainer.addComponent(view.getWidget());

        if (data.getParams() instanceof Integer) {
            MilestoneService riskService = AppContext
                    .getSpringBean(MilestoneService.class);
            SimpleMilestone risk = riskService.findMilestoneById((Integer) data
                    .getParams());
            view.previewItem(risk);
        } else {
            throw new MyCollabException("Unhanddle this case yet");
        }
    }
}
