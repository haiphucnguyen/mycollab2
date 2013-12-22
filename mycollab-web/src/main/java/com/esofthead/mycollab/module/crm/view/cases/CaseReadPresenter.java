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
package com.esofthead.mycollab.module.crm.view.cases;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Set;

import org.vaadin.dialogs.ConfirmDialog;

import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.configuration.SiteConfiguration;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.eventmanager.EventBus;
import com.esofthead.mycollab.module.crm.CrmLinkGenerator;
import com.esofthead.mycollab.module.crm.CrmTypeConstants;
import com.esofthead.mycollab.module.crm.domain.ContactCase;
import com.esofthead.mycollab.module.crm.domain.SimpleCall;
import com.esofthead.mycollab.module.crm.domain.SimpleCase;
import com.esofthead.mycollab.module.crm.domain.SimpleContact;
import com.esofthead.mycollab.module.crm.domain.SimpleEvent;
import com.esofthead.mycollab.module.crm.domain.SimpleMeeting;
import com.esofthead.mycollab.module.crm.domain.SimpleTask;
import com.esofthead.mycollab.module.crm.domain.criteria.CaseSearchCriteria;
import com.esofthead.mycollab.module.crm.events.ActivityEvent;
import com.esofthead.mycollab.module.crm.events.CaseEvent;
import com.esofthead.mycollab.module.crm.events.ContactEvent;
import com.esofthead.mycollab.module.crm.localization.CrmCommonI18nEnum;
import com.esofthead.mycollab.module.crm.service.CaseService;
import com.esofthead.mycollab.module.crm.service.ContactService;
import com.esofthead.mycollab.module.crm.view.AbstractRelatedListHandler;
import com.esofthead.mycollab.module.crm.view.CrmGenericPresenter;
import com.esofthead.mycollab.module.crm.view.CrmToolbar;
import com.esofthead.mycollab.security.RolePermissionCollections;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.events.DefaultPreviewFormHandler;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.mvp.ViewManager;
import com.esofthead.mycollab.vaadin.ui.ConfirmDialogExt;
import com.esofthead.mycollab.vaadin.ui.NotificationUtil;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.UI;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 * 
 */
public class CaseReadPresenter extends CrmGenericPresenter<CaseReadView> {

	private static final long serialVersionUID = 1L;

	public CaseReadPresenter() {
		super(CaseReadView.class);
	}

	@Override
	protected void postInitView() {
		view.getPreviewFormHandlers().addFormHandler(
				new DefaultPreviewFormHandler<SimpleCase>() {
					@Override
					public void onEdit(SimpleCase data) {
						EventBus.getInstance().fireEvent(
								new CaseEvent.GotoEdit(this, data));
					}

					@Override
					public void onDelete(final SimpleCase data) {
						ConfirmDialogExt.show(
								UI.getCurrent(),
								LocalizationHelper.getMessage(
										GenericI18Enum.DELETE_DIALOG_TITLE,
										SiteConfiguration.getSiteName()),
								LocalizationHelper
										.getMessage(GenericI18Enum.CONFIRM_DELETE_RECORD_DIALOG_MESSAGE),
								LocalizationHelper
										.getMessage(GenericI18Enum.BUTTON_YES_LABEL),
								LocalizationHelper
										.getMessage(GenericI18Enum.BUTTON_NO_LABEL),
								new ConfirmDialog.Listener() {
									private static final long serialVersionUID = 1L;

									@Override
									public void onClose(ConfirmDialog dialog) {
										if (dialog.isConfirmed()) {
											CaseService caseService = ApplicationContextUtil
													.getSpringBean(CaseService.class);
											caseService.removeWithSession(
													data.getId(),
													AppContext.getUsername(),
													AppContext.getAccountId());
											EventBus.getInstance().fireEvent(
													new CaseEvent.GotoList(
															this, null));
										}
									}
								});
					}

					@Override
					public void onClone(SimpleCase data) {
						SimpleCase cloneData = (SimpleCase) data.copy();
						cloneData.setId(null);
						EventBus.getInstance().fireEvent(
								new CaseEvent.GotoEdit(this, cloneData));
					}

					@Override
					public void onCancel() {
						EventBus.getInstance().fireEvent(
								new CaseEvent.GotoList(this, null));
					}

					@Override
					public void gotoNext(SimpleCase data) {
						CaseService caseService = ApplicationContextUtil
								.getSpringBean(CaseService.class);
						CaseSearchCriteria criteria = new CaseSearchCriteria();
						criteria.setSaccountid(new NumberSearchField(AppContext
								.getAccountId()));
						criteria.setId(new NumberSearchField(data.getId(),
								NumberSearchField.GREATER));
						Integer nextId = caseService.getNextItemKey(criteria);
						if (nextId != null) {
							EventBus.getInstance().fireEvent(
									new CaseEvent.GotoRead(this, nextId));
						} else {
							NotificationUtil.showGotoLastRecordNotification();
						}

					}

					@Override
					public void gotoPrevious(SimpleCase data) {
						CaseService caseService = ApplicationContextUtil
								.getSpringBean(CaseService.class);
						CaseSearchCriteria criteria = new CaseSearchCriteria();
						criteria.setSaccountid(new NumberSearchField(AppContext
								.getAccountId()));
						criteria.setId(new NumberSearchField(data.getId(),
								NumberSearchField.LESSTHAN));
						Integer nextId = caseService
								.getPreviousItemKey(criteria);
						if (nextId != null) {
							EventBus.getInstance().fireEvent(
									new CaseEvent.GotoRead(this, nextId));
						} else {
							NotificationUtil.showGotoFirstRecordNotification();
						}
					}
				});

		view.getRelatedActivityHandlers().addRelatedListHandler(
				new AbstractRelatedListHandler<SimpleEvent>() {
					@Override
					public void createNewRelatedItem(String itemId) {
						if (itemId.equals("task")) {
							SimpleTask task = new SimpleTask();
							task.setType(CrmTypeConstants.CASE);
							task.setTypeid(view.getItem().getId());
							EventBus.getInstance().fireEvent(
									new ActivityEvent.TaskEdit(
											CaseReadPresenter.this, task));
						} else if (itemId.equals("meeting")) {
							SimpleMeeting meeting = new SimpleMeeting();
							meeting.setType(CrmTypeConstants.CASE);
							meeting.setTypeid(view.getItem().getId());
							EventBus.getInstance().fireEvent(
									new ActivityEvent.MeetingEdit(
											CaseReadPresenter.this, meeting));
						} else if (itemId.equals("call")) {
							SimpleCall call = new SimpleCall();
							call.setType(CrmTypeConstants.CASE);
							call.setTypeid(view.getItem().getId());
							EventBus.getInstance().fireEvent(
									new ActivityEvent.CallEdit(
											CaseReadPresenter.this, call));
						}
					}
				});

		view.getRelatedContactHandlers().addRelatedListHandler(
				new AbstractRelatedListHandler<SimpleContact>() {
					@Override
					public void createNewRelatedItem(String itemId) {
						SimpleContact contact = new SimpleContact();
						contact.setExtraData(view.getItem());
						EventBus.getInstance().fireEvent(
								new ContactEvent.GotoEdit(
										CaseReadPresenter.this, contact));
					}

					@Override
					public void selectAssociateItems(Set<SimpleContact> items) {
						List<ContactCase> associateContacts = new ArrayList<ContactCase>();
						SimpleCase cases = view.getItem();
						for (SimpleContact contact : items) {
							ContactCase associateContact = new ContactCase();
							associateContact.setCaseid(cases.getId());
							associateContact.setContactid(contact.getId());
							associateContact
									.setCreatedtime(new GregorianCalendar()
											.getTime());

							associateContacts.add(associateContact);
						}

						ContactService contactService = ApplicationContextUtil
								.getSpringBean(ContactService.class);
						contactService.saveContactCaseRelationship(
								associateContacts, AppContext.getAccountId());

						view.getRelatedContactHandlers().refresh();
					}
				});
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		if (AppContext.canRead(RolePermissionCollections.CRM_CASE)) {
			CrmToolbar crmToolbar = ViewManager.getView(CrmToolbar.class);
			crmToolbar.gotoItem(LocalizationHelper
					.getMessage(CrmCommonI18nEnum.TOOLBAR_CASES_HEADER));

			if (data.getParams() instanceof Integer) {
				CaseService caseService = ApplicationContextUtil
						.getSpringBean(CaseService.class);
				SimpleCase cases = caseService.findById(
						(Integer) data.getParams(), AppContext.getAccountId());
				if (cases != null) {
					super.onGo(container, data);
					view.previewItem(cases);

					AppContext.addFragment(CrmLinkGenerator
							.generateCasePreviewLink(cases.getId()),
							LocalizationHelper.getMessage(
									GenericI18Enum.BROWSER_PREVIEW_ITEM_TITLE,
									"Case", cases.getSubject()));
				} else {
					NotificationUtil.showRecordNotExistNotification();
					return;
				}
			}
		} else {
			NotificationUtil.showMessagePermissionAlert();
		}
	}
}
