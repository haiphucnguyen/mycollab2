package com.esofthead.mycollab.module.project.view.settings;

import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.eventmanager.EventBusFactory;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.domain.ProjectCustomizeView;
import com.esofthead.mycollab.module.project.events.CustomizeUIEvent;
import com.esofthead.mycollab.module.project.i18n.ProjectCommonI18nEnum;
import com.esofthead.mycollab.module.project.service.ProjectCustomizeViewService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.mvp.AbstractPageView;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.esofthead.mycollab.vaadin.ui.MyCollabResource;
import com.esofthead.mycollab.vaadin.ui.NotificationUtil;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.vaadin.server.Resource;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.4.0
 *
 */
@ViewComponent
public class CustomizeUIViewImpl extends AbstractPageView implements
		CustomizeUIView {
	private static final long serialVersionUID = 1L;

	public CustomizeUIViewImpl() {
		this.addStyleName("readview-layout");
		HorizontalLayout header = new HorizontalLayout();
		this.setMargin(new MarginInfo(true, true, false, true));
		header.setWidth("100%");
		header.setSpacing(true);
		header.setStyleName(UIConstants.HEADER_VIEW);

		final Image titleIcon = new Image(null,
				MyCollabResource.newResource("icons/22/crm/notification.png"));
		header.addComponent(titleIcon);
		header.setComponentAlignment(titleIcon, Alignment.MIDDLE_LEFT);

		final Label searchtitle = new Label("Customize Project Features");
		searchtitle.setStyleName(UIConstants.HEADER_TEXT);
		header.addComponent(searchtitle);
		header.setExpandRatio(searchtitle, 1.0f);
		header.setComponentAlignment(searchtitle, Alignment.MIDDLE_LEFT);

		this.addComponent(header);
	}

	@Override
	public void showFeatures() {
		
		final ProjectCustomizeView customizeView = CurrentProjectVariables
				.getFeatures();

		VerticalLayout layout = new VerticalLayout();
		layout.setSpacing(true);

		final SelectionBox displayMsgSelection = new SelectionBox(
				MyCollabResource.newResource("icons/16/project/message.png"),
				AppContext.getMessage(ProjectCommonI18nEnum.VIEW_MESSAGE),
				customizeView.getDisplaymessage());
		layout.addComponent(displayMsgSelection);

		final SelectionBox displayPhaseSelection = new SelectionBox(
				MyCollabResource.newResource("icons/16/project/milestone.png"),
				AppContext.getMessage(ProjectCommonI18nEnum.VIEW_MILESTONE),
				customizeView.getDisplaymilestone());
		layout.addComponent(displayPhaseSelection);

		final SelectionBox displayTaskSelection = new SelectionBox(
				MyCollabResource.newResource("icons/16/project/task.png"),
				AppContext.getMessage(ProjectCommonI18nEnum.VIEW_TASK),
				customizeView.getDisplaytask());
		layout.addComponent(displayTaskSelection);

		final SelectionBox displayBugSelection = new SelectionBox(
				MyCollabResource.newResource("icons/16/project/bug.png"),
				AppContext.getMessage(ProjectCommonI18nEnum.VIEW_BUG),
				customizeView.getDisplaybug());
		layout.addComponent(displayBugSelection);

		final SelectionBox displayPageSelection = new SelectionBox(
				MyCollabResource.newResource("icons/16/project/page.png"),
				AppContext.getMessage(ProjectCommonI18nEnum.VIEW_PAGE),
				customizeView.getDisplaypage());
		layout.addComponent(displayPageSelection);

		final SelectionBox displayFileSelection = new SelectionBox(
				MyCollabResource.newResource("icons/16/project/File.png"),
				AppContext.getMessage(ProjectCommonI18nEnum.VIEW_FILE),
				customizeView.getDisplayfile());
		layout.addComponent(displayFileSelection);

		final SelectionBox displayRiskSelection = new SelectionBox(
				MyCollabResource.newResource("icons/16/project/risk.png"),
				AppContext.getMessage(ProjectCommonI18nEnum.VIEW_RISK),
				customizeView.getDisplayrisk());
		layout.addComponent(displayRiskSelection);

		final SelectionBox displayProblemSelection = new SelectionBox(
				MyCollabResource.newResource("icons/16/project/problem.png"),
				AppContext.getMessage(ProjectCommonI18nEnum.VIEW_PROBLEM),
				customizeView.getDisplayproblem());
		layout.addComponent(displayProblemSelection);

		final SelectionBox displayTimeSelection = new SelectionBox(
				MyCollabResource
						.newResource("icons/16/project/time_tracking.png"),
				AppContext.getMessage(ProjectCommonI18nEnum.VIEW_TIME),
				customizeView.getDisplaytimelogging());
		layout.addComponent(displayTimeSelection);

		final SelectionBox displayStandupSelection = new SelectionBox(
				MyCollabResource.newResource("icons/16/project/standup.png"),
				AppContext.getMessage(ProjectCommonI18nEnum.VIEW_STANDAUP),
				customizeView.getDisplaystandup());
		layout.addComponent(displayStandupSelection);

		this.addComponent(layout);

		Button updateFeaturesBtn = new Button(
				AppContext.getMessage(GenericI18Enum.BUTTON_UPDATE_LABEL),
				new ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(ClickEvent event) {
						customizeView.setDisplaymessage(displayMsgSelection
								.getSelected());
						customizeView.setDisplaymilestone(displayPhaseSelection
								.getSelected());
						customizeView.setDisplaytask(displayTaskSelection
								.getSelected());
						customizeView.setDisplaybug(displayBugSelection
								.getSelected());
						customizeView.setDisplaypage(displayPageSelection
								.getSelected());
						customizeView.setDisplayfile(displayFileSelection
								.getSelected());
						customizeView.setDisplayrisk(displayRiskSelection
								.getSelected());
						customizeView.setDisplayproblem(displayProblemSelection
								.getSelected());
						customizeView
								.setDisplaytimelogging(displayTimeSelection
										.getSelected());
						customizeView.setDisplaystandup(displayStandupSelection
								.getSelected());

						ProjectCustomizeViewService projectCustomizeService = ApplicationContextUtil
								.getSpringBean(ProjectCustomizeViewService.class);
						if (customizeView.getId() == null) {
							projectCustomizeService.saveWithSession(
									customizeView, AppContext.getUsername());
						} else {
							projectCustomizeService.updateWithSession(
									customizeView, AppContext.getUsername());
						}

						CurrentProjectVariables.getProject().setCustomizeView(
								customizeView);
						EventBusFactory.getInstance().post(
								new CustomizeUIEvent.UpdateFeaturesList(
										CustomizeUIViewImpl.this));
						NotificationUtil.showNotification("Information",
								"Update successfully", Type.HUMANIZED_MESSAGE);

					}
				});
		this.addComponent(updateFeaturesBtn);
	}

	private class SelectionBox extends HorizontalLayout {
		private static final long serialVersionUID = 1L;

		private CheckBox checkbox;

		public SelectionBox(Resource iconResource, String caption,
				Boolean selected) {
			this.setSpacing(true);
			this.setMargin(new MarginInfo(true, true, false, true));
			Image image = new Image("", iconResource);
			this.addComponent(image);
			this.setComponentAlignment(image, Alignment.MIDDLE_LEFT);
			Label captionLbl = new Label(caption);
			this.addComponent(captionLbl);
			this.setComponentAlignment(captionLbl, Alignment.MIDDLE_LEFT);
			checkbox = new CheckBox("", selected);
			this.addComponent(checkbox);
			this.setComponentAlignment(checkbox, Alignment.MIDDLE_LEFT);
		}

		public Boolean getSelected() {
			return checkbox.getValue();
		}
	}

}
