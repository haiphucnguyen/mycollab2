package com.esofthead.mycollab.module.user.accountsettings.profile.view;

import com.esofthead.mycollab.module.user.accountsettings.view.events.ProfileEvent;
import com.esofthead.mycollab.module.user.domain.User;
import com.esofthead.mycollab.module.user.service.UserService;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

@SuppressWarnings("serial")
public class AdvancedInfoChangeWindow extends Window {

	private TextField txtWebsite;
	private TextField txtCompany;
	
	private User user;
	
	public AdvancedInfoChangeWindow(User user) {
		this.user = user;
		this.setWidth("450px");
		this.setHeight("250px");
		initUI();
		center();
		this.setCaption("Change your advanced information");
	}
	
	private void initUI() {
		
		VerticalLayout mainLayout = new VerticalLayout();
		mainLayout.setWidth("100%");
		mainLayout.setMargin(true);
		mainLayout.setSpacing(true);
		
		GridFormLayoutHelper passInfo = new GridFormLayoutHelper(1, 4,
				UIConstants.DEFAULT_CONTROL_WIDTH,
				"150px");
		
		txtWebsite = (TextField) passInfo.addComponent(new TextField(), "Website", 0, 0);
		txtCompany = (TextField) passInfo.addComponent(new TextField(), "Company", 0, 1);
		
		txtWebsite.setValue(user.getWebsite() == null ? "" : user.getWebsite());
		txtCompany.setValue(user.getCompany() == null ? "" : user.getCompany());
		
		mainLayout.addComponent(passInfo.getLayout());
		mainLayout.setComponentAlignment(passInfo.getLayout(), Alignment.TOP_LEFT);
		
		Label lbSpace = new Label();
		mainLayout.addComponent(lbSpace);
		mainLayout.setExpandRatio(lbSpace, 1.0f);
		
		HorizontalLayout hlayoutControls = new HorizontalLayout();
		
		Button cancelBtn = new Button("Cancel", new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				AdvancedInfoChangeWindow.this.close();
			}
		});

		cancelBtn.setStyleName("link");
		hlayoutControls.addComponent(cancelBtn);
		hlayoutControls.setComponentAlignment(cancelBtn, Alignment.MIDDLE_CENTER);
		
		Button sendBtn = new Button("Save", new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				changePassword();
			}
		});
		sendBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
		hlayoutControls.addComponent(sendBtn);
		hlayoutControls.setComponentAlignment(sendBtn, Alignment.MIDDLE_CENTER);
		
		mainLayout.addComponent(hlayoutControls);
		mainLayout.setComponentAlignment(hlayoutControls, Alignment.MIDDLE_RIGHT);
		
		this.setModal(true);
		this.setContent(mainLayout);
	}
	
	private void changePassword() {
		user.setWebsite((String) txtWebsite.getValue());
		user.setCompany((String) txtCompany.getValue());

		UserService userService = AppContext.getSpringBean(UserService.class);
		userService.updateWithSession(user, AppContext.getUsername());
		
		EventBus.getInstance().fireEvent(
				new ProfileEvent.GotoProfileView(
						AdvancedInfoChangeWindow.this, null));
		AdvancedInfoChangeWindow.this.close();
	}
}
