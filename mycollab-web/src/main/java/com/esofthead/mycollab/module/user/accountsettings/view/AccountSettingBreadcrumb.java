package com.esofthead.mycollab.module.user.accountsettings.view;

import com.esofthead.mycollab.common.UrlEncodeDecoder;
import com.esofthead.mycollab.module.user.accountsettings.view.events.ProfileEvent;
import com.esofthead.mycollab.module.user.domain.Role;
import com.esofthead.mycollab.module.user.domain.SimpleRole;
import com.esofthead.mycollab.module.user.domain.SimpleUser;
import com.esofthead.mycollab.module.user.events.RoleEvent;
import com.esofthead.mycollab.module.user.events.UserEvent;
import com.esofthead.mycollab.vaadin.events.ApplicationEvent;
import com.esofthead.mycollab.vaadin.events.ApplicationEventListener;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.View;
import com.esofthead.mycollab.vaadin.ui.CommonUIFactory;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.esofthead.mycollab.vaadin.ui.utils.LabelStringGenerator;
import com.esofthead.mycollab.web.AppContext;
import com.lexaden.breadcrumb.Breadcrumb;
import com.vaadin.terminal.Sizeable;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComponentContainer;

@ViewComponent
public class AccountSettingBreadcrumb extends Breadcrumb implements View {
	private static final long serialVersionUID = 1L;

	private static LabelStringGenerator menuLinkGenerator = new BreadcrumbLabelStringGenerator();

	public AccountSettingBreadcrumb() {
		this.setShowAnimationSpeed(Breadcrumb.AnimSpeed.SLOW);
		this.setHideAnimationSpeed(Breadcrumb.AnimSpeed.SLOW);
		this.setUseDefaultClickBehaviour(false);
		this.addLink(new Button(null, new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				EventBus.getInstance().fireEvent(
						new ProfileEvent.GotoProfileView(this, null));
			}
		}));

		this.setHeight(25, Sizeable.UNITS_PIXELS);
	}

	public void gotoProfile() {
		this.select(0);
		this.addLink(new Button("Profile"));
		AppContext.addFragment("account/preview", "User Profile");
	}

	public void gotoBillingPage() {
		this.select(0);
		this.addLink(new Button("Billing"));
		AppContext.addFragment("account/billing", "Billing");
	}

	public void gotoCancelAccountPage() {
		this.select(0);
		this.addLink(new Button("Cancel Account"));
		AppContext.addFragment("account/cancel_account", "Cancel Account");
	}

	public void gotoUserList() {
		this.select(0);
		this.addLink(new Button("Users"));
		AppContext.addFragment("account/user/list", "Account Users");
	}

	public void gotoUserRead(SimpleUser user) {
		this.select(0);
		this.addLink(new Button("Users", new GotoUserListListener()));
		this.addLink(generateBreadcrumbLink(user.getDisplayName()));

		AppContext.addFragment(
				"account/user/preview/"
						+ UrlEncodeDecoder.encode(user.getUsername()),
				"Preview User " + user.getDisplayName());
	}

	public void gotoUserAdd() {
		this.select(0);
		this.addLink(new Button("Users", new GotoRoleListListener()));
		this.setLinkEnabled(true, 1);
		this.addLink(new Button("Add"));
		AppContext.addFragment("account/user/add", "Invite New User");
	}

	public void gotoUserEdit(final SimpleUser user) {
		this.select(0);
		this.addLink(new Button("Users", new GotoUserListListener()));
		this.setLinkEnabled(true, 1);
		this.addLink(generateBreadcrumbLink(user.getDisplayName(),
				new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(ClickEvent event) {
						EventBus.getInstance().fireEvent(
								new UserEvent.GotoRead(this, user));

					}
				}));
		this.setLinkEnabled(true, 2);
		this.addLink(new Button("Edit"));
		AppContext.addFragment(
				"account/user/edit/"
						+ UrlEncodeDecoder.encode(user.getUsername()),
				"Edit User " + user.getUsername());
	}

	private static class GotoUserListListener implements Button.ClickListener {
		private static final long serialVersionUID = 1L;

		@Override
		public void buttonClick(ClickEvent event) {
			EventBus.getInstance()
					.fireEvent(new UserEvent.GotoList(this, null));
		}
	}

	public void gotoRoleList() {
		this.select(0);
		this.addLink(new Button("Roles"));
		AppContext.addFragment("account/role/list", "List Roles");
	}

	public void gotoRoleAdd() {
		this.select(0);
		this.addLink(new Button("Roles", new GotoRoleListListener()));
		this.setLinkEnabled(true, 1);
		this.addLink(new Button("Add"));
		AppContext.addFragment("account/role/add", "New Role");
	}

	public void gotoRoleRead(SimpleRole role) {
		this.select(0);
		this.addLink(new Button("Roles", new GotoRoleListListener()));
		this.setLinkEnabled(true, 1);
		this.addLink(generateBreadcrumbLink(role.getRolename()));
		AppContext
				.addFragment(
						"account/role/preview/"
								+ UrlEncodeDecoder.encode(role.getId()),
						"Preview Role " + role.getRolename());
	}

	public void gotoRoleEdit(final Role role) {
		this.select(0);
		this.addLink(new Button("Roles", new GotoRoleListListener()));
		this.setLinkEnabled(true, 1);
		this.addLink(generateBreadcrumbLink(role.getRolename(),
				new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(ClickEvent event) {
						EventBus.getInstance().fireEvent(
								new RoleEvent.GotoRead(this, role.getId()));

					}
				}));
		this.setLinkEnabled(true, 2);
		this.addLink(new Button("Edit"));
		AppContext.addFragment(
				"account/role/edit/" + UrlEncodeDecoder.encode(role.getId()),
				"Edit Role: " + role.getRolename());
	}

	private static class GotoRoleListListener implements Button.ClickListener {
		private static final long serialVersionUID = 1L;

		@Override
		public void buttonClick(ClickEvent event) {
			EventBus.getInstance()
					.fireEvent(new RoleEvent.GotoList(this, null));
		}
	}

	private static Button generateBreadcrumbLink(String linkname) {
		return CommonUIFactory.createButtonTooltip(
				menuLinkGenerator.handleText(linkname), linkname);
	}

	private static Button generateBreadcrumbLink(String linkname,
			Button.ClickListener listener) {
		return CommonUIFactory.createButtonTooltip(
				menuLinkGenerator.handleText(linkname), linkname, listener);
	}

	@Override
	public ComponentContainer getWidget() {

		return null;
	}

	@Override
	public void addViewListener(
			ApplicationEventListener<? extends ApplicationEvent> listener) {

	}

	private static class BreadcrumbLabelStringGenerator implements
			LabelStringGenerator {

		@Override
		public String handleText(String value) {
			if (value.length() > 35) {
				return value.substring(0, 35) + "...";
			}
			return value;
		}

	}

}
