/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.message;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.domain.criteria.MessageSearchCriteria;
import com.esofthead.mycollab.module.project.view.ProjectView;
import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.PageAction;
import com.esofthead.mycollab.vaadin.mvp.PageActionChain;
import com.esofthead.mycollab.vaadin.mvp.PresenterResolver;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.vaadin.ui.ComponentContainer;

/**
 * 
 * @author haiphucnguyen
 */
public class MessagePresenter extends AbstractPresenter<MessageContainer> {
	private static final long serialVersionUID = 1L;

	public MessagePresenter() {
		super(MessageContainer.class);
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		ProjectView projectViewContainer = (ProjectView) container;
		projectViewContainer.gotoSubView("Messages");

		view.removeAllComponents();

		if (data instanceof ScreenData.Preview) {
			MessageReadPresenter presenter = PresenterResolver
					.getPresenter(MessageReadPresenter.class);
			presenter.go(view, data);
		} else if (data instanceof ScreenData.Search) {
			MessageListPresenter presenter = PresenterResolver
					.getPresenter(MessageListPresenter.class);
			presenter.go(view, data);
		} else if (data == null) {
			MessageSearchCriteria searchCriteria = new MessageSearchCriteria();
			searchCriteria.setProjectid(new NumberSearchField(
					CurrentProjectVariables.getProjectId()));
			MessageListPresenter presenter = PresenterResolver
					.getPresenter(MessageListPresenter.class);
			presenter.go(view, new ScreenData.Preview<MessageSearchCriteria>(
					searchCriteria));
		}
	}

	@Override
	public void handleChain(ComponentContainer container,
			PageActionChain pageActionChain) {
		ProjectView projectViewContainer = (ProjectView) container;
		projectViewContainer.gotoSubView("Messages");

		view.removeAllComponents();

		PageAction pageAction = pageActionChain.pop();
		if (pageAction instanceof MessageReadPageAction) {
			MessageReadPresenter presenter = PresenterResolver
					.getPresenter(MessageReadPresenter.class);
			presenter.go(this.view, pageAction.getScreenData());
		}
	}

}
