package com.esofthead.mycollab.premium.module.project.view.problem;

import com.esofthead.mycollab.eventmanager.EventBusFactory;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.domain.Problem;
import com.esofthead.mycollab.module.project.events.ProblemEvent;
import com.esofthead.mycollab.module.project.service.ProblemService;
import com.esofthead.mycollab.module.project.view.ProjectBreadcrumb;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.events.EditFormHandler;
import com.esofthead.mycollab.vaadin.mvp.HistoryViewManager;
import com.esofthead.mycollab.vaadin.mvp.NullViewState;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.mvp.ViewManager;
import com.esofthead.mycollab.vaadin.mvp.ViewState;
import com.esofthead.mycollab.vaadin.ui.AbstractPresenter;
import com.esofthead.mycollab.vaadin.ui.NotificationUtil;
import com.vaadin.ui.ComponentContainer;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 */
public class ProblemAddPresenter extends AbstractPresenter<ProblemAddView> {

	private static final long serialVersionUID = 1L;

	public ProblemAddPresenter() {
		super(ProblemAddView.class);
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		if (CurrentProjectVariables
				.canWrite(ProjectRolePermissionCollections.PROBLEMS)) {
			ProblemContainer problemContainer = (ProblemContainer) container;
			problemContainer.removeAllComponents();
			problemContainer.addComponent(view.getWidget());

			Problem problem = (Problem) data.getParams();
			view.editItem(problem);

			ProjectBreadcrumb breadcrumb = ViewManager
					.getCacheComponent(ProjectBreadcrumb.class);
			if (problem.getId() == null) {
				breadcrumb.gotoProblemAdd();
			} else {
				breadcrumb.gotoProblemEdit(problem);
			}
		} else {
			NotificationUtil.showMessagePermissionAlert();
		}
	}

	@Override
	protected void postInitView() {
		view.getEditFormHandlers().addFormHandler(
				new EditFormHandler<Problem>() {
					private static final long serialVersionUID = 1L;

					@Override
					public void onSave(final Problem problem) {
						saveProblem(problem);
						ViewState viewState = HistoryViewManager.back();
						if (viewState instanceof NullViewState) {
							EventBusFactory.getInstance().post(
									new ProblemEvent.GotoList(this, null));
						}
					}

					@Override
					public void onCancel() {
						ViewState viewState = HistoryViewManager.back();
						if (viewState instanceof NullViewState) {
							EventBusFactory.getInstance().post(
									new ProblemEvent.GotoList(this, null));
						}
					}

					@Override
					public void onSaveAndNew(final Problem problem) {
						saveProblem(problem);
						EventBusFactory.getInstance().post(
								new ProblemEvent.GotoAdd(this, null));
					}
				});
	}

	public void saveProblem(Problem problem) {
		ProblemService problemService = ApplicationContextUtil
				.getSpringBean(ProblemService.class);
		problem.setProjectid(CurrentProjectVariables.getProjectId());
		problem.setSaccountid(AppContext.getAccountId());

		if (problem.getId() == null) {
			problemService.saveWithSession(problem, AppContext.getUsername());

		} else {
			problemService.updateWithSession(problem, AppContext.getUsername());
		}

	}
}
