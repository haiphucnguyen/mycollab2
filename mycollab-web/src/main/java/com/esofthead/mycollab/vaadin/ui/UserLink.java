/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.vaadin.ui;

import com.esofthead.mycollab.module.crm.CrmLinkGenerator;
import com.esofthead.mycollab.module.user.domain.User;
import com.esofthead.mycollab.module.user.service.UserService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

/**
 * 
 * @author haiphucnguyen
 */
public class UserLink extends Button {
	private static final long serialVersionUID = 1L;

	private Integer sAccountId;

	public UserLink(final String username, String userAvatarId,
			final String displayName, boolean useWordWrap) {
		super(displayName);
		this.addStyleName("link");

		if (username != null && !username.equals("")) {
			this.setIcon(UserAvatarControlFactory.createAvatarResource(
					userAvatarId, 16));
		}

		if (useWordWrap) {
			this.addStyleName(UIConstants.WORD_WRAP);
		}
		this.addListener(new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				UserService userService = ApplicationContextUtil
						.getSpringBean(UserService.class);
				User user = userService.findUserByUserNameInAccount(username,
						AppContext.getAccountId());
				UserLink.this.getParent().getWindow()
						.addWindow(new UserQuickPreviewWindow(user));
			}
		});
	}

	public void setSAccountId(Integer sAccountId) {
		this.sAccountId = sAccountId;
	}

	public UserLink(final String username, String userAvatarId,
			final String displayName) {
		this(username, userAvatarId, displayName, true);
	}

	public class UserQuickPreviewWindow extends Window {
		private static final long serialVersionUID = 1L;
		private User user;

		public UserQuickPreviewWindow(User user) {
			super("User preview");
			this.center();
			this.setWidth("500px");
			this.user = user;
			constructBody();
		}

		private void constructBody() {
			VerticalLayout layout = new VerticalLayout();
			layout.setSpacing(true);
			HorizontalLayout topLayout = new HorizontalLayout();
			layout.addComponent(topLayout);

			StringBuffer userActionScript = new StringBuffer("");
			userActionScript
					.append("onClick=\" <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js\">"
							+ "$('#userLink').bind('click',fuction(){"
							+ "$('.v-window').html('');"
							+ "$('.v-shadow-window').html('');"
							+ "})</script>\"");
			// ---------define top layout
			topLayout.setSpacing(true);
			topLayout.addComponent(new Label("View full profile at: "));

			Label userFullLinkLabel = new Label("<a href=\""
					+ CrmLinkGenerator.generatePreviewFullUserLink(
							AppContext.getSiteUrl(), user.getUsername())
					+ "\">"
					+ CrmLinkGenerator.generatePreviewFullUserLink(
							AppContext.getSiteUrl(), user.getUsername())
					+ userActionScript + "</a>");
			userFullLinkLabel.setContentMode(Label.CONTENT_XHTML);
			topLayout.addComponent(userFullLinkLabel);
			// -----------------------------------
			CssLayout mainBodyWapper = new CssLayout();
			mainBodyWapper.addStyleName("border-box2-color");
			mainBodyWapper.setSizeFull();

			layout.addComponent(mainBodyWapper);
			VerticalLayout bodyLayout = new VerticalLayout();
			mainBodyWapper.addComponent(bodyLayout);
			HorizontalLayout infoHorizontalWapper = new HorizontalLayout();
			VerticalLayout mainUserInfoLayout = new VerticalLayout();
			VerticalLayout userImageLayout = new VerticalLayout();
			infoHorizontalWapper.addComponent(mainUserInfoLayout);
			infoHorizontalWapper.addComponent(userImageLayout);
			bodyLayout.addComponent(infoHorizontalWapper);

			// Construct mainUserInfoLayout ------------------
			mainUserInfoLayout.setWidth("360px");
			mainUserInfoLayout.setSpacing(true);
			mainUserInfoLayout.setMargin(true);

			Label userNameLbl = new Label(getDisplayName());
			userNameLbl.addStyleName("h2");
			mainUserInfoLayout.addComponent(userNameLbl);

			HorizontalLayout userNameLayout = new HorizontalLayout();
			Label userNameTitle = new Label("User name");
			userNameTitle.setWidth("100px");
			userNameLayout.addComponent(userNameTitle);
			Label userNameLink = new Label("<a href=\"mailto:"
					+ user.getUsername() + "\">" + user.getUsername() + "</a>");
			userNameLink.setContentMode(Label.CONTENT_XHTML);
			userNameLayout.addComponent(userNameLink);
			mainUserInfoLayout.addComponent(userNameLayout);

			HorizontalLayout emailLayout = new HorizontalLayout();
			Label emailTitle = new Label("Email");
			emailTitle.setWidth("100px");
			emailLayout.addComponent(emailTitle);

			Label emailLink = new Label("<a href=\"mailto:" + user.getEmail()
					+ "\">" + user.getEmail() + "</a>", Label.CONTENT_XHTML);
			emailLayout.addComponent(emailLink);
			mainUserInfoLayout.addComponent(emailLayout);

			HorizontalLayout timeZoneLayout = new HorizontalLayout();
			Label timeLabel = new Label("Time");
			timeLabel.setWidth("100px");
			timeZoneLayout.addComponent(timeLabel);
			timeZoneLayout.addComponent(new Label(user.getTimezone()));
			mainUserInfoLayout.addComponent(timeZoneLayout);

			HorizontalLayout countryLayout = new HorizontalLayout();
			Label countryTitle = new Label("Country");
			countryTitle.setWidth("100px");
			countryLayout.addComponent(countryTitle);
			countryLayout.addComponent(new Label(user.getCountry()));
			mainUserInfoLayout.addComponent(countryLayout);

			HorizontalLayout phoneLayout = new HorizontalLayout();
			Label phoneLbl = new Label("Phone");
			phoneLbl.setWidth("100px");
			phoneLayout.addComponent(phoneLbl);
			phoneLayout.addComponent(new Label(user.getWorkphone()));
			mainUserInfoLayout.addComponent(phoneLayout);
			// Construct userImageLayout ---------------------
			Embedded embeedIcon = new Embedded("",
					UserAvatarControlFactory.createAvatarResource(
							user.getAvatarid(), 100));
			userImageLayout.addComponent(embeedIcon);
			this.addComponent(layout);
		}

		public String getDisplayName() {
			String result = user.getFirstname() + " " + user.getLastname();
			if (result.trim().equals("")) {
				String displayName = user.getUsername();
				int index = (displayName != null) ? displayName.indexOf("@")
						: 0;
				if (index > 0) {
					return displayName.substring(0, index);
				}
			}
			return result;
		}
	}
}
