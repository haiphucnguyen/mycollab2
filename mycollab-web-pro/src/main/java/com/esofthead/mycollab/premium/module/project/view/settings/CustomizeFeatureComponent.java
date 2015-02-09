package com.esofthead.mycollab.premium.module.project.view.settings;

import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.eventmanager.EventBusFactory;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectTypeConstants;
import com.esofthead.mycollab.module.project.domain.ProjectCustomizeView;
import com.esofthead.mycollab.module.project.events.CustomizeUIEvent;
import com.esofthead.mycollab.module.project.i18n.ProjectCommonI18nEnum;
import com.esofthead.mycollab.module.project.i18n.ProjectSettingI18nEnum;
import com.esofthead.mycollab.module.project.service.ProjectCustomizeViewService;
import com.esofthead.mycollab.module.project.ui.AssetsManager;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.ui.BlockWidget;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import org.vaadin.maddon.layouts.MHorizontalLayout;
import org.vaadin.maddon.layouts.MVerticalLayout;

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

		MVerticalLayout body = new MVerticalLayout().withWidth("100%");

		MHorizontalLayout layout = new MHorizontalLayout().withMargin(new MarginInfo(true, false, true, false))
                .withWidth("100%");

		VerticalLayout leftColLayout = new VerticalLayout();
		leftColLayout.setSpacing(true);
		leftColLayout.setWidth("100%");

		final SelectionBox displayMsgSelection = new SelectionBox(
                AssetsManager.getAsset(ProjectTypeConstants.MESSAGE),
				AppContext.getMessage(ProjectCommonI18nEnum.VIEW_MESSAGE),
				customizeView.getDisplaymessage());
		leftColLayout.addComponent(displayMsgSelection);

		final SelectionBox displayPhaseSelection = new SelectionBox(
                AssetsManager.getAsset(ProjectTypeConstants.MILESTONE),
				AppContext.getMessage(ProjectCommonI18nEnum.VIEW_MILESTONE),
				customizeView.getDisplaymilestone());
		leftColLayout.addComponent(displayPhaseSelection);

		final SelectionBox displayTaskSelection = new SelectionBox(
                AssetsManager.getAsset(ProjectTypeConstants.TASK),
				AppContext.getMessage(ProjectCommonI18nEnum.VIEW_TASK),
				customizeView.getDisplaytask());
		leftColLayout.addComponent(displayTaskSelection);

		final SelectionBox displayBugSelection = new SelectionBox(
                AssetsManager.getAsset(ProjectTypeConstants.BUG),
				AppContext.getMessage(ProjectCommonI18nEnum.VIEW_BUG),
				customizeView.getDisplaybug());
		leftColLayout.addComponent(displayBugSelection);

		final SelectionBox displayPageSelection = new SelectionBox(
                AssetsManager.getAsset(ProjectTypeConstants.PAGE),
				AppContext.getMessage(ProjectCommonI18nEnum.VIEW_PAGE),
				customizeView.getDisplaypage());
		leftColLayout.addComponent(displayPageSelection);

		layout.addComponent(leftColLayout);

		VerticalLayout rightColLayout = new VerticalLayout();
		rightColLayout.setWidth("100%");
		rightColLayout.setSpacing(true);

		final SelectionBox displayFileSelection = new SelectionBox(
                AssetsManager.getAsset(ProjectTypeConstants.FILE),
				AppContext.getMessage(ProjectCommonI18nEnum.VIEW_FILE),
				customizeView.getDisplayfile());
		rightColLayout.addComponent(displayFileSelection);

		final SelectionBox displayRiskSelection = new SelectionBox(
                AssetsManager.getAsset(ProjectTypeConstants.RISK),
				AppContext.getMessage(ProjectCommonI18nEnum.VIEW_RISK),
				customizeView.getDisplayrisk());
		rightColLayout.addComponent(displayRiskSelection);

		final SelectionBox displayProblemSelection = new SelectionBox(
                AssetsManager.getAsset(ProjectTypeConstants.PROBLEM),
				AppContext.getMessage(ProjectCommonI18nEnum.VIEW_PROBLEM),
				customizeView.getDisplayproblem());
		rightColLayout.addComponent(displayProblemSelection);

		final SelectionBox displayTimeSelection = new SelectionBox(
                AssetsManager.getAsset(ProjectTypeConstants.TIME),
				AppContext.getMessage(ProjectCommonI18nEnum.VIEW_TIME),
				customizeView.getDisplaytimelogging());
		rightColLayout.addComponent(displayTimeSelection);

		final SelectionBox displayStandupSelection = new SelectionBox(
                AssetsManager.getAsset(ProjectTypeConstants.STANDUP),
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
		updateFeaturesBtn.setIcon(FontAwesome.REFRESH);
		body.addComponent(updateFeaturesBtn);
		this.addToBody(body);
	}

	private class SelectionBox extends MHorizontalLayout {
		private static final long serialVersionUID = 1L;

		private CheckBox checkbox;

		public SelectionBox(FontAwesome iconResource, String caption,
				Boolean selected) {
			this.withMargin(true).withWidth("100%").withStyleName("feature-select-box");
			Label captionLbl = new Label(iconResource.getHtml() + " " + caption, ContentMode.HTML);
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
