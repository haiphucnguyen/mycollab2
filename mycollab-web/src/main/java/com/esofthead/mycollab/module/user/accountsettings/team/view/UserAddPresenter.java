/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.user.accountsettings.team.view;

import com.esofthead.mycollab.module.user.accountsettings.view.AccountSettingBreadcrumb;
import com.esofthead.mycollab.module.user.domain.SimpleUser;
import com.esofthead.mycollab.module.user.events.UserEvent;
import com.esofthead.mycollab.module.user.service.UserService;
import com.esofthead.mycollab.vaadin.events.EditFormHandler;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.HistoryViewManager;
import com.esofthead.mycollab.vaadin.mvp.NullViewState;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.mvp.ViewManager;
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

		view.getEditFormHandlers().addFormHandler(
				new EditFormHandler<SimpleUser>() {
					@Override
					public void onSave(final SimpleUser item) {
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
					public void onSaveAndNew(final SimpleUser item) {
						save(item);
						EventBus.getInstance().fireEvent(
								new UserEvent.GotoAdd(this, null));
					}
				});
	}

	public void save(SimpleUser item) {
		UserService userService = AppContext.getSpringBean(UserService.class);

		item.setAccountId(AppContext.getAccountId());

		item.setDateofbirth(view.getBirthday());
		item.setTimezone(view.getTimezone().getId());

		if (item.getUsername() == null) {
			userService.saveUserAccount(item, AppContext.getAccountId(),
					AppContext.getUsername());
		} else {
			userService.updateUserAccount(item, AppContext.getAccountId());
		}

	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		UserContainer userContainer = (UserContainer) container;
		userContainer.removeAllComponents();
		userContainer.addComponent(view.getWidget());

		SimpleUser user = (SimpleUser) data.getParams();
		view.editItem(user);

		AccountSettingBreadcrumb breadcrumb = ViewManager
				.getView(AccountSettingBreadcrumb.class);

		if (user.getUsername() == null) {
			breadcrumb.gotoUserAdd();
		} else {
			breadcrumb.gotoUserEdit(user);
		}

	}
}
