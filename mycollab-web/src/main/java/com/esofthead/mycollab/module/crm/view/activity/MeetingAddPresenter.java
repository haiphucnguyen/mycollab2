/**
 * This file is part of mycollab-web.
 *
 * mycollab-web is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-web is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-web.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.module.crm.view.activity;

import com.esofthead.mycollab.common.UrlEncodeDecoder;
import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.eventmanager.EventBus;
import com.esofthead.mycollab.module.crm.domain.MeetingWithBLOBs;
import com.esofthead.mycollab.module.crm.events.ActivityEvent;
import com.esofthead.mycollab.module.crm.service.MeetingService;
import com.esofthead.mycollab.module.crm.view.CrmGenericPresenter;
import com.esofthead.mycollab.security.RolePermissionCollections;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.events.EditFormHandler;
import com.esofthead.mycollab.vaadin.mvp.HistoryViewManager;
import com.esofthead.mycollab.vaadin.mvp.NullViewState;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.mvp.ViewState;
import com.esofthead.mycollab.vaadin.ui.MessageBox;
import com.esofthead.mycollab.vaadin.ui.NotificationUtil;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.ComponentContainer;

public class MeetingAddPresenter extends CrmGenericPresenter<MeetingAddView> {

	private static final long serialVersionUID = 1L;

	public MeetingAddPresenter() {
		super(MeetingAddView.class);

		view.getEditFormHandlers().addFormHandler(
				new EditFormHandler<MeetingWithBLOBs>() {
					@Override
					public void onSave(final MeetingWithBLOBs item) {
						save(item);
						ViewState viewState = HistoryViewManager.back();
						if (viewState instanceof NullViewState) {
							EventBus.getInstance().fireEvent(
									new ActivityEvent.GotoTodoList(this, null));
						}
					}

					@Override
					public void onCancel() {
						ViewState viewState = HistoryViewManager.back();
						if (viewState instanceof NullViewState) {
							EventBus.getInstance().fireEvent(
									new ActivityEvent.GotoTodoList(this, null));
						}
					}

					@Override
					public void onSaveAndNew(final MeetingWithBLOBs item) {
						save(item);
						EventBus.getInstance().fireEvent(
								new ActivityEvent.MeetingAdd(this, null));
					}
				});
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		if (AppContext.canWrite(RolePermissionCollections.CRM_MEETING)) {
			MeetingWithBLOBs meeting = null;
			if (data.getParams() instanceof MeetingWithBLOBs) {
				meeting = (MeetingWithBLOBs) data.getParams();
			} else if (data.getParams() instanceof Integer) {
				MeetingService meetingService = ApplicationContextUtil
						.getSpringBean(MeetingService.class);
				meeting = meetingService.findByPrimaryKey(
						(Integer) data.getParams(), AppContext.getAccountId());
				if (meeting == null) {
					NotificationUtil.showRecordNotExistNotification();
					return;
				}
			}

			container.removeAllComponents();
			container.addComponent(view.getWidget());

			view.editItem(meeting);

			if (meeting.getId() == null) {
				AppContext.addFragment("crm/activity/meeting/add/",
						LocalizationHelper.getMessage(
								GenericI18Enum.BROWSER_ADD_ITEM_TITLE,
								"Meeting"));
			} else {
				AppContext.addFragment("crm/activity/meeting/edit/"
						+ UrlEncodeDecoder.encode(meeting.getId()),
						LocalizationHelper.getMessage(
								GenericI18Enum.BROWSER_EDIT_ITEM_TITLE,
								"Meeting", meeting.getSubject()));
			}
		} else {
			MessageBox.showMessagePermissionAlert();
		}
	}

	public void save(MeetingWithBLOBs item) {
		MeetingService meetingService = ApplicationContextUtil
				.getSpringBean(MeetingService.class);

		item.setSaccountid(AppContext.getAccountId());
		if (item.getId() == null) {
			meetingService.saveWithSession(item, AppContext.getUsername());
		} else {
			meetingService.updateWithSession(item, AppContext.getUsername());
		}
	}
}
