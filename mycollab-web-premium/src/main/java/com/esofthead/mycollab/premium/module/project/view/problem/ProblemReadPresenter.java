package com.esofthead.mycollab.premium.module.project.view.problem;

import org.vaadin.dialogs.ConfirmDialog;

import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.configuration.SiteConfiguration;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.eventmanager.EventBus;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.domain.Problem;
import com.esofthead.mycollab.module.project.domain.SimpleProblem;
import com.esofthead.mycollab.module.project.domain.SimpleProject;
import com.esofthead.mycollab.module.project.domain.criteria.ProblemSearchCriteria;
import com.esofthead.mycollab.module.project.events.ProblemEvent;
import com.esofthead.mycollab.module.project.service.ProblemService;
import com.esofthead.mycollab.module.project.view.ProjectBreadcrumb;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.events.DefaultPreviewFormHandler;
import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.mvp.ViewManager;
import com.esofthead.mycollab.vaadin.ui.ConfirmDialogExt;
import com.esofthead.mycollab.vaadin.ui.MessageBox;
import com.esofthead.mycollab.vaadin.ui.NotificationUtil;
import com.esofthead.mycollab.web.AppContext;
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
						ConfirmDialogExt.show(
								view.getWindow(),
								LocalizationHelper.getMessage(
										GenericI18Enum.DELETE_DIALOG_TITLE,
										SiteConfiguration.getSiteName()),
								LocalizationHelper
										.getMessage(GenericI18Enum.CONFIRM_DELETE_RECORD_DIALOG_MESSAGE),
								LocalizationHelper
										.getMessage(GenericI18Enum.BUTTON_YES_LABEL),
								LocalizationHelper
										.getMessage(GenericI18Enum.BUTTON_NO_LABEL),
								new ConfirmDialog.Listener() {
									private static final long serialVersionUID = 1L;

									@Override
									public void onClose(ConfirmDialog dialog) {
										if (dialog.isConfirmed()) {
											ProblemService riskService = ApplicationContextUtil
													.getSpringBean(ProblemService.class);
											riskService.removeWithSession(
													data.getId(),
													AppContext.getUsername(),
													AppContext.getAccountId());
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
						ProblemService problemService = ApplicationContextUtil
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
							NotificationUtil.showGotoLastRecordNotification();
						}

					}

					@Override
					public void gotoPrevious(Problem data) {
						ProblemService problemService = ApplicationContextUtil
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
							NotificationUtil.showGotoFirstRecordNotification();
						}
					}
				});
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		if (CurrentProjectVariables
				.canRead(ProjectRolePermissionCollections.PROBLEMS)) {
			if (data.getParams() instanceof Integer) {
				ProblemService problemService = ApplicationContextUtil
						.getSpringBean(ProblemService.class);
				SimpleProblem problem = problemService.findById(
						(Integer) data.getParams(), AppContext.getAccountId());
				if (problem != null) {
					ProblemContainer problemContainer = (ProblemContainer) container;
					problemContainer.removeAllComponents();
					problemContainer.addComponent(view.getWidget());
					view.previewItem(problem);

					ProjectBreadcrumb breadcrumb = ViewManager
							.getView(ProjectBreadcrumb.class);
					breadcrumb.gotoProblemRead(problem);
				} else {
					NotificationUtil.showRecordNotExistNotification();
					return;
				}

			}
		} else {
			MessageBox.showMessagePermissionAlert();
		}
	}
}
