package com.esofthead.mycollab.module.project.view.settings;

import java.util.Arrays;
import java.util.List;

import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.domain.ProjectNotificationSetting;
import com.esofthead.mycollab.module.project.service.ProjectNotificationSettingService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.VerticalLayout;

@ViewComponent
public class ProjectNotificationSettingViewImpl extends AbstractView implements
		ProjectNotificationSettingView {
	private static final long serialVersionUID = 1L;

	private CssLayout mainLayout;
	private String level;

	@Override
	public void showNotificationSettings(
			final ProjectNotificationSetting notification) {
		this.removeAllComponents();
		this.setSizeFull();

		mainLayout = new CssLayout();
		mainLayout.setSizeFull();
		mainLayout.addStyleName(UIConstants.BORDER_BOX_2);

		VerticalLayout body = new VerticalLayout();
		body.setMargin(true, true, false, true);
		body.setSpacing(true);
		body.setSizeFull();

		mainLayout.addComponent(body);

		Label notificationLabel = new Label("Notification Levels");
		notificationLabel.addStyleName("h2");
		body.addComponent(notificationLabel);

		final List<String> options = Arrays
				.asList(new String[] {
						"Default- By default you will receive notifications about items that you are involved in. "
								+ "To be involved with and item you need to have added a comment, been assigned the item, or when the item was created you were specified as a person to notify. Within the email notifications you can unsubscribe from any item.",
						"None - You won't be notified of anything, this can be a great option if you just wanted to get the daily email with an overview.",
						"Minimal - We won't do any magic behind the scences to subscribe you to any items, you will only be notified about things you are currently assigned.",
						"Full - You will be notified every things about your project." });
		final OptionGroup optionGroup = new OptionGroup("", options);

		body.addComponent(optionGroup);
		body.setComponentAlignment(optionGroup, Alignment.MIDDLE_LEFT);

		if (notification == null)
			optionGroup.select(options.get(0));
		for (String str : options) {
			if (str.startsWith(notification.getLevel())) {
				optionGroup.select(str);
				break;
			}
		}
		optionGroup.addListener(new ValueChangeListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void valueChange(ValueChangeEvent event) {
				String strValue = (String) optionGroup.getValue();
				if (strValue.startsWith("Default")) {
					level = "Default";
				} else if (strValue.startsWith("None")) {
					level = "None";
				} else if (strValue.startsWith("Minimal")) {
					level = "Minimal";
				} else if (strValue.startsWith("Full")) {
					level = "Full";
				}
			}
		});

		Button upgradeBtn = new Button("Upgrade", new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				ProjectNotificationSettingService service = ApplicationContextUtil
						.getSpringBean(ProjectNotificationSettingService.class);
				try {
					if (notification == null) {
						ProjectNotificationSetting notification = new ProjectNotificationSetting();
						notification.setLevel(level);
						notification.setProjectid(CurrentProjectVariables
								.getProjectId());
						notification.setSaccountid(AppContext.getAccountId());
						notification.setUsername(AppContext.getUsername());
						service.saveWithSession(notification,
								AppContext.getUsername());
					} else {
						notification.setLevel(level);
						service.updateWithSession(notification,
								AppContext.getUsername());
					}
					getWindow().showNotification(
							"Update notification setting successfully.");
				} catch (Exception e) {
					throw new MyCollabException(e);
				}
			}
		});
		upgradeBtn.addStyleName(UIConstants.THEME_BLUE_LINK);
		body.addComponent(upgradeBtn);
		body.setComponentAlignment(upgradeBtn, Alignment.MIDDLE_LEFT);

		this.addComponent(mainLayout);
	}
}
