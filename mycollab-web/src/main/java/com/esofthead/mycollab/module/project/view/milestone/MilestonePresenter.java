/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.milestone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.module.project.view.ProjectView;
import com.esofthead.mycollab.module.project.view.parameters.MilestoneScreenData;
import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.PageActionChain;
import com.esofthead.mycollab.vaadin.mvp.PresenterResolver;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.vaadin.ui.ComponentContainer;

/**
 * 
 * @author haiphucnguyen
 */
public class MilestonePresenter extends AbstractPresenter<MilestoneContainer> {

	private static final long serialVersionUID = 1L;
	private static Logger log = LoggerFactory
			.getLogger(MilestonePresenter.class);

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
		projectViewContainer.gotoSubView("Phases");

		AbstractPresenter presenter = null;
		if (data instanceof MilestoneScreenData.Search) {
			log.debug("Go to milestone list view");
			presenter = PresenterResolver
					.getPresenter(MilestoneListPresenter.class);
		} else if (data instanceof MilestoneScreenData.Add
				|| data instanceof MilestoneScreenData.Edit) {
			log.debug("Go to milestone add view");
			presenter = PresenterResolver
					.getPresenter(MilestoneAddPresenter.class);
		} else if (data instanceof MilestoneScreenData.Read) {
			log.debug("Go to milestone preview view");
			presenter = PresenterResolver
					.getPresenter(MilestoneReadPresenter.class);
		} else {
			throw new MyCollabException("Do not support screen data " + data);
		}

		presenter.go(view, data);
	}

	@Override
	public void handleChain(ComponentContainer container,
			PageActionChain pageActionChain) {

		ScreenData pageAction = pageActionChain.peek();
		onGo(container, pageAction);
	}

}
