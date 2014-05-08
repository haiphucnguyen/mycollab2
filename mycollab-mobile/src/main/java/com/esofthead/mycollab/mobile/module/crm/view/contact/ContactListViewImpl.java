package com.esofthead.mycollab.mobile.module.crm.view.contact;

import com.esofthead.mycollab.eventmanager.ApplicationEvent;
import com.esofthead.mycollab.eventmanager.ApplicationEventListener;
import com.esofthead.mycollab.eventmanager.EventBus;
import com.esofthead.mycollab.mobile.module.crm.events.ContactEvent;
import com.esofthead.mycollab.mobile.ui.AbstractListViewComp;
import com.esofthead.mycollab.mobile.ui.AbstractPagedBeanList;
import com.esofthead.mycollab.mobile.ui.TableClickEvent;
import com.esofthead.mycollab.module.crm.domain.SimpleContact;
import com.esofthead.mycollab.module.crm.domain.criteria.ContactSearchCriteria;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;

/**
 * 
 * 
 * @author MyCollab Ltd.
 * @since 4.0
 * 
 */

@ViewComponent
public class ContactListViewImpl extends
		AbstractListViewComp<ContactSearchCriteria, SimpleContact> implements
		ContactListView {
	private static final long serialVersionUID = 8271856163726726780L;

	public ContactListViewImpl() {
		super();

		setCaption("Contacts");
		setToggleButton(true);
	}

	@Override
	protected AbstractPagedBeanList<ContactSearchCriteria, SimpleContact> createBeanTable() {
		ContactListDisplay contactListDisplay = new ContactListDisplay(
				"contactName");
		contactListDisplay
				.addTableListener(new ApplicationEventListener<TableClickEvent>() {
					private static final long serialVersionUID = 2099605014707508671L;

					@Override
					public Class<? extends ApplicationEvent> getEventType() {
						return TableClickEvent.class;
					}

					@Override
					public void handle(TableClickEvent event) {
						SimpleContact simpleContact = (SimpleContact) event
								.getData();
						if ("contactName".equals(event.getFieldName())) {
							EventBus.getInstance().fireEvent(
									new ContactEvent.GotoRead(
											ContactListViewImpl.this,
											simpleContact.getId()));
						}
					}
				});
		return contactListDisplay;
	}

	@Override
	protected Component createRightComponent() {
		Button addContact = new Button(null, new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent arg0) {
				EventBus.getInstance().fireEvent(
						new ContactEvent.GotoAdd(this, null));
			}
		});
		addContact.setStyleName("add-btn");
		return addContact;
	}

}
