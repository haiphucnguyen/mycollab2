package com.esofthead.mycollab.module.project.view.problem;

import org.vaadin.dialogs.ConfirmDialog;

import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.domain.Problem;
import com.esofthead.mycollab.module.project.domain.SimpleProblem;
import com.esofthead.mycollab.module.project.domain.SimpleProject;
import com.esofthead.mycollab.module.project.domain.criteria.ProblemSearchCriteria;
import com.esofthead.mycollab.module.project.events.ProblemEvent;
import com.esofthead.mycollab.module.project.service.ProblemService;
import com.esofthead.mycollab.module.project.view.ProjectBreadcrumb;
import com.esofthead.mycollab.vaadin.events.DefaultPreviewFormHandler;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.mvp.ViewManager;
import com.esofthead.mycollab.vaadin.ui.MessageConstants;
import com.esofthead.mycollab.web.AppContext;
import com.esofthead.mycollab.web.LocalizationHelper;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.Window;

public class ProblemReadPresenter extends AbstractPresenter<ProblemReadView> {

	private static final long serialVersionUID = 1L;

	public ProblemReadPresenter() {
		super(ProblemReadView.class);
		bind();
	}

	private void bind() {
		view.getPreviewFormHandlers().addFormHandler(
				new DefaultPreviewFormHandler<Problem>() {
					@Override
					public void onEdit(Problem data) {
						EventBus.getInstance().fireEvent(
								new ProblemEvent.GotoEdit(this, data));
					}

					@Override
					public void onDelete(final Problem data) {
						ConfirmDialog.show(
								view.getWindow(),
								"Please Confirm:",
								"Are you sure to delete this item: "
										+ data.getIssuename(), "Yes", "No",
								new ConfirmDialog.Listener() {
									private static final long serialVersionUID = 1L;

									@Override
									public void onClose(ConfirmDialog dialog) {
										if (dialog.isConfirmed()) {
											ProblemService riskService = AppContext
													.getSpringBean(ProblemService.class);
											riskService.removeWithSession(
													data.getId(),
													AppContext.getUsername());
											EventBus.getInstance().fireEvent(
													new ProblemEvent.GotoList(
															this, null));
										}
									}
								});
					}

					@Override
					public void onClone(Problem data) {
						Problem cloneData = (Problem) data.copy();
						cloneData.setId(null);
						EventBus.getInstance().fireEvent(
								new ProblemEvent.GotoEdit(this, cloneData));
					}

					@Override
					public void onCancel() {
						EventBus.getInstance().fireEvent(
								new ProblemEvent.GotoList(this, null));
					}

					@Override
					public void gotoNext(Problem data) {
						ProblemService problemService = AppContext
								.getSpringBean(ProblemService.class);
						ProblemSearchCriteria criteria = new ProblemSearchCriteria();
						SimpleProject project = (SimpleProject) AppContext
								.getVariable("project");
						criteria.setProjectId(new NumberSearchField(
								SearchField.AND, project.getId()));
						criteria.setId(new NumberSearchField(data.getId(),
								NumberSearchField.GREATER));
						Integer nextId = problemService
								.getNextItemKey(criteria);
						if (nextId != null) {
							EventBus.getInstance().fireEvent(
									new ProblemEvent.GotoRead(this, nextId));
						} else {
							view.getWindow()
									.showNotification(
											LocalizationHelper
													.getMessage(GenericI18Enum.INFORMATION_WINDOW_TITLE),
											LocalizationHelper
													.getMessage(GenericI18Enum.INFORMATION_GOTO_LAST_RECORD),
											Window.Notification.TYPE_HUMANIZED_MESSAGE);
						}

					}

					@Override
					public void gotoPrevious(Problem data) {
						ProblemService problemService = AppContext
								.getSpringBean(ProblemService.class);
						ProblemSearchCriteria criteria = new ProblemSearchCriteria();
						SimpleProject project = (SimpleProject) AppContext
								.getVariable("project");
						criteria.setProjectId(new NumberSearchField(
								SearchField.AND, project.getId()));
						criteria.setId(new NumberSearchField(data.getId(),
								NumberSearchField.LESSTHAN));
						Integer nextId = problemService
								.getPreviousItemKey(criteria);
						if (nextId != null) {
							EventBus.getInstance().fireEvent(
									new ProblemEvent.GotoRead(this, nextId));
						} else {
							view.getWindow()
									.showNotification(
											LocalizationHelper
													.getMessage(GenericI18Enum.INFORMATION_WINDOW_TITLE),
											LocalizationHelper
													.getMessage(GenericI18Enum.INFORMATION_GOTO_FIRST_RECORD),
											Window.Notification.TYPE_HUMANIZED_MESSAGE);
						}
					}
				});
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		if (CurrentProjectVariables
				.canRead(ProjectRolePermissionCollections.PROBLEMS)) {
			if (data.getParams() instanceof Integer) {
				ProblemService problemService = AppContext
						.getSpringBean(ProblemService.class);
				SimpleProblem problem = problemService
						.findProblemById((Integer) data.getParams());
				if (problem != null) {
					ProblemContainer problemContainer = (ProblemContainer) container;
					problemContainer.removeAllComponents();
					problemContainer.addComponent(view.getWidget());
					view.previewItem(problem);

					ProjectBreadcrumb breadcrumb = ViewManager
							.getView(ProjectBreadcrumb.class);
					breadcrumb.gotoProblemRead(problem);
				} else {
					AppContext
							.getApplication()
							.getMainWindow()
							.showNotification(
									LocalizationHelper
											.getMessage(GenericI18Enum.INFORMATION_WINDOW_TITLE),
									LocalizationHelper
											.getMessage(GenericI18Enum.INFORMATION_RECORD_IS_NOT_EXISTED_MESSAGE),
									Window.Notification.TYPE_HUMANIZED_MESSAGE);
					return;
				}

			}
		} else {
			MessageConstants.showMessagePermissionAlert();
		}
	}
}
