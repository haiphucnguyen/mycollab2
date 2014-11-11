package com.esofthead.mycollab.premium.module.project.view.problem;

import org.vaadin.dialogs.ConfirmDialog;

import com.esofthead.mycollab.common.MyCollabSession;
import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.configuration.SiteConfiguration;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.eventmanager.EventBusFactory;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.domain.SimpleProblem;
import com.esofthead.mycollab.module.project.domain.SimpleProject;
import com.esofthead.mycollab.module.project.domain.criteria.ProblemSearchCriteria;
import com.esofthead.mycollab.module.project.events.ProblemEvent;
import com.esofthead.mycollab.module.project.service.ProblemService;
import com.esofthead.mycollab.module.project.view.ProjectBreadcrumb;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.events.DefaultPreviewFormHandler;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.mvp.ViewManager;
import com.esofthead.mycollab.vaadin.ui.AbstractPresenter;
import com.esofthead.mycollab.vaadin.ui.ConfirmDialogExt;
import com.esofthead.mycollab.vaadin.ui.NotificationUtil;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.UI;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 * 
 */
public class ProblemReadPresenter extends AbstractPresenter<ProblemReadView> {

	private static final long serialVersionUID = 1L;

	public ProblemReadPresenter() {
		super(ProblemReadView.class);
	}

	@Override
	protected void postInitView() {
		view.getPreviewFormHandlers().addFormHandler(
				new DefaultPreviewFormHandler<SimpleProblem>() {
					@Override
					public void onEdit(SimpleProblem data) {
						EventBusFactory.getInstance().post(
								new ProblemEvent.GotoEdit(this, data));
					}

					@Override
					public void onAdd(SimpleProblem data) {
						EventBusFactory.getInstance().post(
								new ProblemEvent.GotoAdd(this, null));
					}

					@Override
					public void onDelete(final SimpleProblem data) {
						ConfirmDialogExt.show(
								UI.getCurrent(),
								AppContext.getMessage(
										GenericI18Enum.DIALOG_DELETE_TITLE,
										SiteConfiguration.getSiteName()),
								AppContext
										.getMessage(GenericI18Enum.DIALOG_DELETE_SINGLE_ITEM_MESSAGE),
								AppContext
										.getMessage(GenericI18Enum.BUTTON_YES),
								AppContext
										.getMessage(GenericI18Enum.BUTTON_NO),
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
											EventBusFactory.getInstance().post(
													new ProblemEvent.GotoList(
															this, null));
										}
									}
								});
					}

					@Override
					public void onClone(SimpleProblem data) {
						SimpleProblem cloneData = (SimpleProblem) data.copy();
						cloneData.setId(null);
						EventBusFactory.getInstance().post(
								new ProblemEvent.GotoEdit(this, cloneData));
					}

					@Override
					public void onCancel() {
						EventBusFactory.getInstance().post(
								new ProblemEvent.GotoList(this, null));
					}

					@Override
					public void gotoNext(SimpleProblem data) {
						ProblemService problemService = ApplicationContextUtil
								.getSpringBean(ProblemService.class);
						ProblemSearchCriteria criteria = new ProblemSearchCriteria();
						SimpleProject project = (SimpleProject) MyCollabSession
								.getVariable("project");
						criteria.setProjectId(new NumberSearchField(
								SearchField.AND, project.getId()));
						criteria.setId(new NumberSearchField(data.getId(),
								NumberSearchField.GREATER));
						Integer nextId = problemService
								.getNextItemKey(criteria);
						if (nextId != null) {
							EventBusFactory.getInstance().post(
									new ProblemEvent.GotoRead(this, nextId));
						} else {
							NotificationUtil.showGotoLastRecordNotification();
						}

					}

					@Override
					public void gotoPrevious(SimpleProblem data) {
						ProblemService problemService = ApplicationContextUtil
								.getSpringBean(ProblemService.class);
						ProblemSearchCriteria criteria = new ProblemSearchCriteria();
						SimpleProject project = (SimpleProject) MyCollabSession
								.getVariable("project");
						criteria.setProjectId(new NumberSearchField(
								SearchField.AND, project.getId()));
						criteria.setId(new NumberSearchField(data.getId(),
								NumberSearchField.LESSTHAN));
						Integer nextId = problemService
								.getPreviousItemKey(criteria);
						if (nextId != null) {
							EventBusFactory.getInstance().post(
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
							.getCacheComponent(ProjectBreadcrumb.class);
					breadcrumb.gotoProblemRead(problem);
				} else {
					NotificationUtil.showRecordNotExistNotification();
					return;
				}

			}
		} else {
			NotificationUtil.showMessagePermissionAlert();
		}
	}
}
