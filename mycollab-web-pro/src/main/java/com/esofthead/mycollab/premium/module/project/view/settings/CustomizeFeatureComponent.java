package com.esofthead.mycollab.premium.module.project.view.settings;

import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.eventmanager.EventBusFactory;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.domain.ProjectCustomizeView;
import com.esofthead.mycollab.module.project.events.CustomizeUIEvent;
import com.esofthead.mycollab.module.project.i18n.ProjectCommonI18nEnum;
import com.esofthead.mycollab.module.project.i18n.ProjectSettingI18nEnum;
import com.esofthead.mycollab.module.project.service.ProjectCustomizeViewService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.ui.BlockWidget;
import com.esofthead.mycollab.vaadin.ui.MyCollabResource;
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
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.4.0
 *
 */

public class CustomizeFeatureComponent extends BlockWidget {
	private static final long serialVersionUID = 1L;

	public CustomizeFeatureComponent() {
		super(AppContext
				.getMessage(ProjectSettingI18nEnum.WIDGET_CUSTOMIZE_FEATURES));
		constructBody();
	}

	public void constructBody() {
		final ProjectCustomizeView customizeView = CurrentProjectVariables
				.getFeatures();

		VerticalLayout body = new VerticalLayout();
		body.setWidth("100%");
		body.setSpacing(true);
		body.setMargin(true);

		HorizontalLayout layout = new HorizontalLayout();
		layout.setSpacing(true);
		layout.setMargin(new MarginInfo(true, false, true, false));
		layout.setWidth("100%");

		VerticalLayout leftColLayout = new VerticalLayout();
		leftColLayout.setSpacing(true);
		leftColLayout.setWidth("100%");

		final SelectionBox displayMsgSelection = new SelectionBox(
				MyCollabResource.newResource("icons/16/project/message.png"),
				AppContext.getMessage(ProjectCommonI18nEnum.VIEW_MESSAGE),
				customizeView.getDisplaymessage());
		leftColLayout.addComponent(displayMsgSelection);

		final SelectionBox displayPhaseSelection = new SelectionBox(
				MyCollabResource.newResource("icons/16/project/milestone.png"),
				AppContext.getMessage(ProjectCommonI18nEnum.VIEW_MILESTONE),
				customizeView.getDisplaymilestone());
		leftColLayout.addComponent(displayPhaseSelection);

		final SelectionBox displayTaskSelection = new SelectionBox(
				MyCollabResource.newResource("icons/16/project/task.png"),
				AppContext.getMessage(ProjectCommonI18nEnum.VIEW_TASK),
				customizeView.getDisplaytask());
		leftColLayout.addComponent(displayTaskSelection);

		final SelectionBox displayBugSelection = new SelectionBox(
				MyCollabResource.newResource("icons/16/project/bug.png"),
				AppContext.getMessage(ProjectCommonI18nEnum.VIEW_BUG),
				customizeView.getDisplaybug());
		leftColLayout.addComponent(displayBugSelection);

		final SelectionBox displayPageSelection = new SelectionBox(
				MyCollabResource.newResource("icons/16/project/page.png"),
				AppContext.getMessage(ProjectCommonI18nEnum.VIEW_PAGE),
				customizeView.getDisplaypage());
		leftColLayout.addComponent(displayPageSelection);

		layout.addComponent(leftColLayout);

		VerticalLayout rightColLayout = new VerticalLayout();
		rightColLayout.setWidth("100%");
		rightColLayout.setSpacing(true);

		final SelectionBox displayFileSelection = new SelectionBox(
				MyCollabResource.newResource("icons/16/project/File.png"),
				AppContext.getMessage(ProjectCommonI18nEnum.VIEW_FILE),
				customizeView.getDisplayfile());
		rightColLayout.addComponent(displayFileSelection);

		final SelectionBox displayRiskSelection = new SelectionBox(
				MyCollabResource.newResource("icons/16/project/risk.png"),
				AppContext.getMessage(ProjectCommonI18nEnum.VIEW_RISK),
				customizeView.getDisplayrisk());
		rightColLayout.addComponent(displayRiskSelection);

		final SelectionBox displayProblemSelection = new SelectionBox(
				MyCollabResource.newResource("icons/16/project/problem.png"),
				AppContext.getMessage(ProjectCommonI18nEnum.VIEW_PROBLEM),
				customizeView.getDisplayproblem());
		rightColLayout.addComponent(displayProblemSelection);

		final SelectionBox displayTimeSelection = new SelectionBox(
				MyCollabResource
						.newResource("icons/16/project/time_tracking.png"),
				AppContext.getMessage(ProjectCommonI18nEnum.VIEW_TIME),
				customizeView.getDisplaytimelogging());
		rightColLayout.addComponent(displayTimeSelection);

		final SelectionBox displayStandupSelection = new SelectionBox(
				MyCollabResource.newResource("icons/16/project/standup.png"),
				AppContext.getMessage(ProjectCommonI18nEnum.VIEW_STANDAUP),
				customizeView.getDisplaystandup());
		rightColLayout.addComponent(displayStandupSelection);

		layout.addComponent(rightColLayout);

		body.addComponent(layout);

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
										CustomizeFeatureComponent.this));

					}
				});
		updateFeaturesBtn.setEnabled(CurrentProjectVariables.isAdmin());
		updateFeaturesBtn.setStyleName(UIConstants.THEME_GREEN_LINK);
		updateFeaturesBtn.setIcon(MyCollabResource
				.newResource("icons/16/crm/refresh.png"));
		body.addComponent(updateFeaturesBtn);
		this.addToBody(body);
	}

	private class SelectionBox extends HorizontalLayout {
		private static final long serialVersionUID = 1L;

		private CheckBox checkbox;

		public SelectionBox(Resource iconResource, String caption,
				Boolean selected) {
			this.setSpacing(true);
			this.setMargin(true);
			this.setWidth("100%");
			this.setStyleName("feature-select-box");
			Image image = new Image(null, iconResource);
			this.addComponent(image);
			this.setComponentAlignment(image, Alignment.MIDDLE_LEFT);
			Label captionLbl = new Label(caption);
			this.addComponent(captionLbl);
			this.setExpandRatio(captionLbl, 1.0f);
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
