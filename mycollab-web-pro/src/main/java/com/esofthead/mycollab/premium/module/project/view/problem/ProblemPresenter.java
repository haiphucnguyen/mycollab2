package com.esofthead.mycollab.premium.module.project.view.problem;

import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.module.project.ProjectTypeConstants;
import com.esofthead.mycollab.module.project.view.ProjectView;
import com.esofthead.mycollab.module.project.view.parameters.ProblemScreenData;
import com.esofthead.mycollab.module.project.view.problem.IProblemContainer;
import com.esofthead.mycollab.module.project.view.problem.IProblemPresenter;
import com.esofthead.mycollab.vaadin.mvp.PageActionChain;
import com.esofthead.mycollab.vaadin.mvp.PresenterResolver;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.ui.AbstractPresenter;
import com.vaadin.ui.ComponentContainer;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 * 
 */
public class ProblemPresenter extends AbstractPresenter<IProblemContainer>
		implements IProblemPresenter {

	private static final long serialVersionUID = 1L;

	public ProblemPresenter() {
		super(IProblemContainer.class);
	}

	@Override
	public void go(ComponentContainer container, ScreenData<?> data) {
		super.go(container, data, false);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		ProjectView projectViewContainer = (ProjectView) container;
		projectViewContainer.gotoSubView(ProjectTypeConstants.PROBLEM);

		view.removeAllComponents();

		AbstractPresenter presenter;

		if (data instanceof ProblemScreenData.Search) {
			presenter = PresenterResolver.getPresenter(ProblemListPresenter.class);
		} else if (data instanceof ProblemScreenData.Add || data instanceof ProblemScreenData.Edit) {
			presenter = PresenterResolver.getPresenter(ProblemAddPresenter.class);
		} else if (data instanceof ProblemScreenData.Read) {
			presenter = PresenterResolver
					.getPresenter(ProblemReadPresenter.class);
		} else {
			throw new MyCollabException("Do not support screen data " + data);
		}

		presenter.go(view, data);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void handleChain(ComponentContainer container,
			PageActionChain pageActionChain) {
		ScreenData pageAction = pageActionChain.pop();
		onGo(container, pageAction);
	}

}
