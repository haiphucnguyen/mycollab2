package com.esofthead.mycollab.module.crm.view.cases;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Case;
import org.vaadin.dialogs.ConfirmDialog;

import com.esofthead.mycollab.common.UrlEncodeDecoder;
import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.module.crm.CrmTypeConstants;
import com.esofthead.mycollab.module.crm.domain.CallWithBLOBs;
import com.esofthead.mycollab.module.crm.domain.CaseWithBLOBs;
import com.esofthead.mycollab.module.crm.domain.Contact;
import com.esofthead.mycollab.module.crm.domain.ContactCase;
import com.esofthead.mycollab.module.crm.domain.Meeting;
import com.esofthead.mycollab.module.crm.domain.SimpleCase;
import com.esofthead.mycollab.module.crm.domain.SimpleContact;
import com.esofthead.mycollab.module.crm.domain.Task;
import com.esofthead.mycollab.module.crm.domain.criteria.CaseSearchCriteria;
import com.esofthead.mycollab.module.crm.events.ActivityEvent;
import com.esofthead.mycollab.module.crm.events.CaseEvent;
import com.esofthead.mycollab.module.crm.events.ContactEvent;
import com.esofthead.mycollab.module.crm.service.CaseService;
import com.esofthead.mycollab.module.crm.service.ContactService;
import com.esofthead.mycollab.module.crm.view.AbstractRelatedListHandler;
import com.esofthead.mycollab.module.crm.view.CrmGenericPresenter;
import com.esofthead.mycollab.module.user.RolePermissionCollections;
import com.esofthead.mycollab.vaadin.events.DefaultPreviewFormHandler;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.ui.MessageConstants;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.Window;

public class CaseReadPresenter extends CrmGenericPresenter<CaseReadView> {

	private static final long serialVersionUID = 1L;

	public CaseReadPresenter() {
		super(CaseReadView.class);
		bind();
	}

	private void bind() {
		view.getPreviewFormHandlers().addFormHandler(
				new DefaultPreviewFormHandler<CaseWithBLOBs>() {
					@Override
					public void onEdit(CaseWithBLOBs data) {
						EventBus.getInstance().fireEvent(
								new CaseEvent.GotoEdit(this, data));
					}

					@Override
					public void onDelete(final CaseWithBLOBs data) {
						ConfirmDialog.show(
								view.getWindow(),
								"Please Confirm:",
								"Are you sure to delete case '"
										+ data.getSubject() + "' ?", "Yes",
								"No", new ConfirmDialog.Listener() {
									private static final long serialVersionUID = 1L;

									@Override
									public void onClose(ConfirmDialog dialog) {
										if (dialog.isConfirmed()) {
											CaseService caseService = AppContext
													.getSpringBean(CaseService.class);
											caseService.removeWithSession(
													data.getId(),
													AppContext.getUsername());
											EventBus.getInstance().fireEvent(
													new CaseEvent.GotoList(
															this, null));
										}
									}
								});
					}

					@Override
					public void onClone(CaseWithBLOBs data) {
						CaseWithBLOBs cloneData = (CaseWithBLOBs) data.copy();
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
					public void gotoNext(CaseWithBLOBs data) {
						CaseService caseService = AppContext
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
							view.getWindow()
									.showNotification(
											AppContext
													.getMessage(GenericI18Enum.INFORMATION_WINDOW_TITLE),
											AppContext
													.getMessage(GenericI18Enum.INFORMATION_GOTO_LAST_RECORD),
											Window.Notification.TYPE_HUMANIZED_MESSAGE);
						}

					}

					@Override
					public void gotoPrevious(CaseWithBLOBs data) {
						CaseService caseService = AppContext
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
							view.getWindow()
									.showNotification(
											AppContext
													.getMessage(GenericI18Enum.INFORMATION_WINDOW_TITLE),
											AppContext
													.getMessage(GenericI18Enum.INFORMATION_GOTO_FIRST_RECORD),
											Window.Notification.TYPE_HUMANIZED_MESSAGE);
						}
					}
				});

		view.getRelatedActivityHandlers().addRelatedListHandler(
				new AbstractRelatedListHandler() {
					@Override
					public void createNewRelatedItem(String itemId) {
						if (itemId.equals("task")) {
							Task task = new Task();
							task.setType(CrmTypeConstants.CASE);
							task.setTypeid(view.getItem().getId());
							EventBus.getInstance().fireEvent(
									new ActivityEvent.TaskEdit(
											CaseReadPresenter.this, task));
						} else if (itemId.equals("meeting")) {
							Meeting meeting = new Meeting();
							meeting.setType(CrmTypeConstants.CASE);
							meeting.setTypeid(view.getItem().getId());
							EventBus.getInstance().fireEvent(
									new ActivityEvent.MeetingEdit(
											CaseReadPresenter.this, meeting));
						} else if (itemId.equals("call")) {
							CallWithBLOBs call = new CallWithBLOBs();
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
						Contact contact = new Contact();
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

						ContactService contactService = AppContext
								.getSpringBean(ContactService.class);
						contactService
								.saveContactCaseRelationship(associateContacts);

						view.getRelatedContactHandlers().refresh();
					}
				});
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		if (AppContext.canRead(RolePermissionCollections.CRM_CASE)) {
			if (data.getParams() instanceof Integer) {
				CaseService caseService = AppContext
						.getSpringBean(CaseService.class);
				SimpleCase cases = caseService.findCaseById((Integer) data
						.getParams());
				if (cases != null) {
					super.onGo(container, data);
					view.previewItem(cases);

					AppContext.addFragment("crm/cases/preview/"
							+ UrlEncodeDecoder.encode(cases.getId()),
							"Preview: " + cases.getSubject());
				} else {
					AppContext
							.getApplication()
							.getMainWindow()
							.showNotification(
									AppContext
											.getMessage(GenericI18Enum.INFORMATION_WINDOW_TITLE),
									AppContext
											.getMessage(GenericI18Enum.INFORMATION_RECORD_IS_NOT_EXISTED_MESSAGE),
									Window.Notification.TYPE_HUMANIZED_MESSAGE);
					return;
				}
			}
		} else {
			MessageConstants.showMessagePermissionAlert();
		}
	}
}
