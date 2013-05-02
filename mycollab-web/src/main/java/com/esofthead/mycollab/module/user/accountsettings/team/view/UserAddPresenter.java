/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.user.accountsettings.team.view;

import com.esofthead.mycollab.common.UrlEncodeDecoder;
import com.esofthead.mycollab.module.billing.RegisterSourceConstants;
import com.esofthead.mycollab.module.user.domain.User;
import com.esofthead.mycollab.module.user.events.UserEvent;
import com.esofthead.mycollab.module.user.service.UserService;
import com.esofthead.mycollab.vaadin.events.EditFormHandler;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.HistoryViewManager;
import com.esofthead.mycollab.vaadin.mvp.NullViewState;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.mvp.ViewState;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.ComponentContainer;

/**
 * 
 * @author haiphucnguyen
 */
public class UserAddPresenter extends AbstractPresenter<UserAddView> {
	private static final long serialVersionUID = 1L;

	public UserAddPresenter() {
		super(UserAddView.class);

		view.getEditFormHandlers().addFormHandler(new EditFormHandler<User>() {
			@Override
			public void onSave(final User item) {
				save(item);
				ViewState viewState = HistoryViewManager.back();
				if (viewState instanceof NullViewState) {
					EventBus.getInstance().fireEvent(
							new UserEvent.GotoList(this, null));
				}
			}

			@Override
			public void onCancel() {
				ViewState viewState = HistoryViewManager.back();
				if (viewState instanceof NullViewState) {
					EventBus.getInstance().fireEvent(
							new UserEvent.GotoList(this, null));
				}
			}

			@Override
			public void onSaveAndNew(final User item) {
				save(item);
				EventBus.getInstance().fireEvent(
						new UserEvent.GotoAdd(this, null));
			}
		});
	}

	public void save(User item) {
		UserService userService = AppContext.getSpringBean(UserService.class);

		item.setAccountid(AppContext.getAccountId());
		User user = userService.findByPrimaryKey(item.getUsername());
		item.setDateofbirth(view.getBirthday());
		item.setTimezone(view.getTimezone().getId());

		if (user == null) {
			item.setRegistrationsource(RegisterSourceConstants.WEB);
			userService.saveWithSession(item, AppContext.getUsername());
		} else {
			userService.updateWithSession(item, AppContext.getUsername());
		}

	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		UserContainer userContainer = (UserContainer) container;
		userContainer.removeAllComponents();
		userContainer.addComponent(view.getWidget());

		User user = (User) data.getParams();
		view.editItem(user);

		if (user.getUsername() == null) {
			AppContext.addFragment("account/user/add", "New User Account");
		} else {
			AppContext.addFragment(
					"account/user/edit/"
							+ UrlEncodeDecoder.encode(user.getUsername()),
					"Edit User " + user.getUsername());
		}

	}
}
