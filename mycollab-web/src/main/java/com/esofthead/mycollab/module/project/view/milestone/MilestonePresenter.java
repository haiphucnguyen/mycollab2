/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.milestone;

import com.esofthead.mycollab.module.project.view.ProjectView;
import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.PresenterResolver;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.vaadin.ui.ComponentContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author haiphucnguyen
 */
public class MilestonePresenter  extends AbstractPresenter<MilestoneContainer> {

    private static final long serialVersionUID = 1L;
    private static Logger log = LoggerFactory.getLogger(MilestonePresenter.class);

    public MilestonePresenter() {
        super(MilestoneContainer.class);
    }

    @Override
    public void go(ComponentContainer container, ScreenData<?> data) {
        super.go(container, data, false);
    }

    @Override
    protected void onGo(ComponentContainer container, ScreenData<?> data) {
        ProjectView projectViewContainer = (ProjectView) container;
        projectViewContainer.gotoSubView("Milestones");

        view.removeAllComponents();

        if (data instanceof ScreenData.Search) {
            log.debug("Go to risk list view");
            MilestoneListPresenter presenter = PresenterResolver
                    .getPresenter(MilestoneListPresenter.class);
            presenter.go(view, data);

        } else if (data instanceof ScreenData.Add
                || data instanceof ScreenData.Edit) {
            log.debug("Go to risk add view");
            MilestoneAddPresenter presenter = PresenterResolver
                    .getPresenter(MilestoneAddPresenter.class);
            presenter.go(view, data);
        } else if (data instanceof ScreenData.Preview) {
            log.debug("Go to risk preview view");
            MilestoneReadPresenter presenter = PresenterResolver
                    .getPresenter(MilestoneReadPresenter.class);
            presenter.go(view, data);
        }
    }
    
}
