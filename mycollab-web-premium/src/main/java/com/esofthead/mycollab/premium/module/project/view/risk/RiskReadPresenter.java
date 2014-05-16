package com.esofthead.mycollab.premium.module.project.view.risk;

import org.vaadin.dialogs.ConfirmDialog;

import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.configuration.SiteConfiguration;
import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.eventmanager.EventBus;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.domain.Risk;
import com.esofthead.mycollab.module.project.domain.SimpleProject;
import com.esofthead.mycollab.module.project.domain.SimpleRisk;
import com.esofthead.mycollab.module.project.domain.criteria.RiskSearchCriteria;
import com.esofthead.mycollab.module.project.events.RiskEvent;
import com.esofthead.mycollab.module.project.service.RiskService;
import com.esofthead.mycollab.module.project.view.ProjectBreadcrumb;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.MyCollabSession;
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
public class RiskReadPresenter extends AbstractPresenter<RiskReadView> {

	private static final long serialVersionUID = 1L;

	public RiskReadPresenter() {
		super(RiskReadView.class);
	}

	@Override
	protected void postInitView() {
		view.getPreviewFormHandlers().addFormHandler(
				new DefaultPreviewFormHandler<SimpleRisk>() {
					@Override
					public void onEdit(SimpleRisk data) {
						EventBus.getInstance().fireEvent(
								new RiskEvent.GotoEdit(this, data));
					}

					@Override
					public void onAdd(SimpleRisk data) {
						EventBus.getInstance().fireEvent(
								new RiskEvent.GotoAdd(this, null));
					}

					@Override
					public void onDelete(final SimpleRisk data) {

						ConfirmDialogExt.show(
								UI.getCurrent(),
								AppContext.getMessage(
										GenericI18Enum.DELETE_DIALOG_TITLE,
										SiteConfiguration.getSiteName()),
								AppContext
										.getMessage(GenericI18Enum.CONFIRM_DELETE_RECORD_DIALOG_MESSAGE),
								AppContext
										.getMessage(GenericI18Enum.BUTTON_YES_LABEL),
								AppContext
										.getMessage(GenericI18Enum.BUTTON_NO_LABEL),
								new ConfirmDialog.Listener() {
									private static final long serialVersionUID = 1L;

									@Override
									public void onClose(ConfirmDialog dialog) {
										if (dialog.isConfirmed()) {
											RiskService riskService = ApplicationContextUtil
													.getSpringBean(RiskService.class);
											riskService.removeWithSession(
													data.getId(),
													AppContext.getUsername(),
													AppContext.getAccountId());
											EventBus.getInstance().fireEvent(
													new RiskEvent.GotoList(
															this, null));
										}
									}
								});
					}

					@Override
					public void onClone(SimpleRisk data) {
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
					public void gotoNext(SimpleRisk data) {
						RiskService riskeService = ApplicationContextUtil
								.getSpringBean(RiskService.class);
						RiskSearchCriteria criteria = new RiskSearchCriteria();
						SimpleProject project = (SimpleProject) MyCollabSession
								.getVariable("project");
						criteria.setProjectId(new NumberSearchField(
								SearchField.AND, project.getId()));
						criteria.setId(new NumberSearchField(data.getId(),
								NumberSearchField.GREATER));
						Integer nextId = riskeService.getNextItemKey(criteria);
						if (nextId != null) {
							EventBus.getInstance().fireEvent(
									new RiskEvent.GotoRead(this, nextId));
						} else {
							NotificationUtil.showGotoLastRecordNotification();
						}

					}

					@Override
					public void gotoPrevious(SimpleRisk data) {
						RiskService riskeService = ApplicationContextUtil
								.getSpringBean(RiskService.class);
						RiskSearchCriteria criteria = new RiskSearchCriteria();
						SimpleProject project = (SimpleProject) MyCollabSession
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
							NotificationUtil.showGotoFirstRecordNotification();
						}
					}
				});
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		if (CurrentProjectVariables
				.canRead(ProjectRolePermissionCollections.RISKS)) {
			if (data.getParams() instanceof Integer) {
				RiskService riskService = ApplicationContextUtil
						.getSpringBean(RiskService.class);
				SimpleRisk risk = riskService.findById(
						(Integer) data.getParams(), AppContext.getAccountId());
				if (risk != null) {
					RiskContainer riskContainer = (RiskContainer) container;
					riskContainer.removeAllComponents();
					riskContainer.addComponent(view.getWidget());
					view.previewItem(risk);

					ProjectBreadcrumb breadCrumb = ViewManager
							.getView(ProjectBreadcrumb.class);
					breadCrumb.gotoRiskRead(risk);
				} else {
					NotificationUtil.showRecordNotExistNotification();
					return;
				}
			} else {
				throw new MyCollabException("Unhanddle this case yet");
			}
		} else {
			NotificationUtil.showMessagePermissionAlert();
		}
	}
}
