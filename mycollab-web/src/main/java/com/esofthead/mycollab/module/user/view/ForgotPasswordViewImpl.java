/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.user.view;

import com.esofthead.mycollab.shell.events.ShellEvent;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.esofthead.mycollab.web.CustomLayoutLoader;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.Form;
import com.vaadin.ui.TextField;

/**
 * 
 * @author haiphucnguyen
 */
@ViewComponent
public class ForgotPasswordViewImpl extends AbstractView implements
		ForgotPasswordView {
	private static final long serialVersionUID = 1L;

	public ForgotPasswordViewImpl() {
		this.addComponent(new ForgotPwdForm());
	}

	private class ForgotPwdForm extends Form {
		private static final long serialVersionUID = 1L;
		private final TextField nameOrEmailField;

		public ForgotPwdForm() {
			CustomLayout customLayout = CustomLayoutLoader.createLayout("forgotPassword");
			customLayout.setStyleName("forgotPwdForm");

			nameOrEmailField = new TextField("Username or email");
			customLayout.addComponent(nameOrEmailField, "nameoremail");

			Button sendEmail = new Button("Send verification email");
			sendEmail.setStyleName(UIConstants.THEME_BLUE_LINK);
			sendEmail.addListener(new ClickListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(ClickEvent event) {
				}
			});
			customLayout.addComponent(sendEmail, "loginButton");

			Button memoBackBtn = new Button(
					"<<< No thanks, memory's back! Magika!!!");
			memoBackBtn.setStyleName(UIConstants.THEME_LINK);
			memoBackBtn.addListener(new ClickListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(ClickEvent event) {
					EventBus.getInstance().fireEvent(
							new ShellEvent.LogOut(this, null));
				}
			});
			customLayout.addComponent(memoBackBtn, "forgotLink");

			this.setLayout(customLayout);
		}

	}
}
