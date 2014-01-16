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
package com.esofthead.mycollab.module.crm.view.opportunity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.eventmanager.EventBus;
import com.esofthead.mycollab.module.crm.domain.Contact;
import com.esofthead.mycollab.module.crm.domain.ContactOpportunity;
import com.esofthead.mycollab.module.crm.domain.SimpleContact;
import com.esofthead.mycollab.module.crm.domain.SimpleContactOpportunityRel;
import com.esofthead.mycollab.module.crm.domain.SimpleOpportunity;
import com.esofthead.mycollab.module.crm.domain.criteria.ContactSearchCriteria;
import com.esofthead.mycollab.module.crm.events.AccountEvent;
import com.esofthead.mycollab.module.crm.events.ContactEvent;
import com.esofthead.mycollab.module.crm.events.OpportunityEvent;
import com.esofthead.mycollab.module.crm.service.ContactOpportunityService;
import com.esofthead.mycollab.module.crm.service.ContactService;
import com.esofthead.mycollab.module.crm.view.contact.ContactSelectionField;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.mvp.AbstractPageView;
import com.esofthead.mycollab.vaadin.mvp.HistoryViewManager;
import com.esofthead.mycollab.vaadin.mvp.NullViewState;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.esofthead.mycollab.vaadin.mvp.ViewState;
import com.esofthead.mycollab.vaadin.ui.AddViewLayout2;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.ValueComboBox;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * @author MyCollab Ltd.
 * @since 3.0
 * 
 */
@ViewComponent
public class ContactRoleEditViewImpl extends AbstractPageView implements
		ContactRoleEditView {
	private static final long serialVersionUID = 1L;

	private VerticalLayout contactRoleList;
	private SimpleOpportunity opportunity;

	@Override
	public void display(SimpleOpportunity opportunity) {
		this.opportunity = opportunity;
		this.removeAllComponents();

		AddViewLayout2 previewLayout = new AddViewLayout2(
				"Add or Edit Contact Roles",
				MyCollabResource.newResource("icons/22/crm/contact.png"));
		this.addComponent(previewLayout);

		VerticalLayout informationLayout = new VerticalLayout();
		informationLayout.addStyleName("main-info");

		ComponentContainer actionControls = createButtonControls();
		if (actionControls != null) {
			actionControls.addStyleName("control-buttons");
			informationLayout.addComponent(actionControls);
			informationLayout.setComponentAlignment(actionControls,
					Alignment.MIDDLE_CENTER);
		}

		previewLayout.addBody(informationLayout);

		contactRoleList = constructContactOpportunityList();
		previewLayout.addBody(contactRoleList);

		Button addMoreContactRolesBtn = new Button("Add more contact roles",
				new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(ClickEvent event) {
						SimpleContactOpportunityRel contactRole = new SimpleContactOpportunityRel();
						ContactRoleRowComp row = new ContactRoleRowComp(
								contactRole);
						contactRoleList.addComponent(row);
					}
				});
		addMoreContactRolesBtn.setStyleName(UIConstants.THEME_BLUE_LINK);

		HorizontalLayout buttonControls = new HorizontalLayout();
		buttonControls.addComponent(addMoreContactRolesBtn);

		previewLayout.addBody(buttonControls);
	}

	private VerticalLayout constructContactOpportunityList() {
		VerticalLayout layout = new VerticalLayout();
		ContactOpportunityService contactOppoService = ApplicationContextUtil
				.getSpringBean(ContactOpportunityService.class);
		ContactSearchCriteria criteria = new ContactSearchCriteria();
		criteria.setSaccountid(new NumberSearchField(SearchField.AND,
				AppContext.getAccountId()));
		criteria.setOpportunityId(new NumberSearchField(SearchField.AND,
				opportunity.getId()));
		List<SimpleContactOpportunityRel> contactOppoRels = contactOppoService
				.findPagableListByCriteria(new SearchRequest<ContactSearchCriteria>(
						criteria));
		if (contactOppoRels != null && contactOppoRels.size() > 0) {
			for (SimpleContactOpportunityRel contactOppoRel : contactOppoRels) {
				ContactRoleRowComp rowComp = new ContactRoleRowComp(
						contactOppoRel);
				layout.addComponent(rowComp);
			}
		}
		return layout;
	}

	private ComponentContainer createButtonControls() {
		HorizontalLayout layout = new HorizontalLayout();
		layout.setSpacing(true);
		layout.setMargin(true);

		Button updateBtn = new Button("Update", new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				updateContactRoles();
			}
		});
		updateBtn.setIcon(MyCollabResource.newResource("icons/16/save.png"));
		updateBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
		layout.addComponent(updateBtn);
		layout.setComponentAlignment(updateBtn, Alignment.MIDDLE_CENTER);

		Button cancelBtn = new Button("Cancel", new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				ViewState viewState = HistoryViewManager.back();

				if (viewState instanceof NullViewState) {
					EventBus.getInstance().fireEvent(
							new ContactEvent.GotoList(this, null));
				}

			}
		});
		cancelBtn.setIcon(MyCollabResource.newResource("icons/16/cancel.png"));
		cancelBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
		layout.addComponent(cancelBtn);
		layout.setComponentAlignment(cancelBtn, Alignment.MIDDLE_CENTER);

		return layout;
	}

	private void updateContactRoles() {
		Iterator<Component> components = contactRoleList.iterator();
		List<ContactOpportunity> contactOpps = new ArrayList<ContactOpportunity>();

		while (components.hasNext()) {
			Component component = components.next();
			if (component instanceof ContactRoleRowComp) {
				ContactOpportunity contactVal = ((ContactRoleRowComp) component)
						.getContactVal();
				if (contactVal != null) {
					contactOpps.add(contactVal);
				}
			}
		}

		if (contactOpps.size() > 0) {
			ContactService contactService = ApplicationContextUtil
					.getSpringBean(ContactService.class);
			contactService.saveContactOpportunityRelationship(contactOpps,
					AppContext.getAccountId());
		}

		// lead user to opportunity view
		EventBus.getInstance().fireEvent(
				new OpportunityEvent.GotoRead(ContactRoleEditViewImpl.this,
						opportunity.getId()));
	}

	private class ContactRoleRowComp extends HorizontalLayout {
		private static final long serialVersionUID = 1L;

		private ContactSelectionField contactField;
		private RoleDecisionComboBox roleBox;

		public ContactRoleRowComp(final SimpleContactOpportunityRel contactOpp) {
			super();
			this.setMargin(true);
			this.setSpacing(true);
			this.setWidth("100%");

			contactField = new ContactSelectionField();
			this.addComponent(contactField);
			contactField
					.setPropertyDataSource(new AbstractField<SimpleContact>() {
						private static final long serialVersionUID = 1L;

						@Override
						public SimpleContact getValue() {
							return contactOpp;
						}

						@Override
						public Class<? extends SimpleContact> getType() {
							return SimpleContact.class;
						}

					});

			Button accountLink = new Button(contactOpp.getAccountName(),
					new Button.ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(ClickEvent event) {
							EventBus.getInstance().fireEvent(
									new AccountEvent.GotoRead(
											ContactRoleRowComp.this, contactOpp
													.getAccountid()));

						}
					});
			accountLink.setIcon(MyCollabResource
					.newResource("icons/16/crm/account.png"));
			accountLink.setStyleName("link");
			this.addComponent(accountLink);

			roleBox = new RoleDecisionComboBox();
			if (contactOpp.getDecisionRole() != null) {
				roleBox.setValue(contactOpp.getDecisionRole());
			}
			this.addComponent(roleBox);

			Button deleteBtn = new Button("", new Button.ClickListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(ClickEvent event) {
					((VerticalLayout) ContactRoleRowComp.this.getParent())
							.removeComponent(ContactRoleRowComp.this);

				}
			});
			deleteBtn.setIcon(MyCollabResource
					.newResource("icons/16/delete.png"));
			deleteBtn.setStyleName("link");
			this.addComponent(deleteBtn);
		}

		public ContactOpportunity getContactVal() {
			ContactOpportunity contactOppRel = new ContactOpportunity();
			Contact contact = (Contact) contactField.getContact();
			if (contact != null && contact.getId() != null) {
				contactOppRel.setContactid(contact.getId());
				contactOppRel.setOpportunityid(opportunity.getId());
				contactOppRel.setDecisionrole((String) roleBox.getValue());
				return contactOppRel;
			} else {
				return null;
			}
		}
	}

	private static class RoleDecisionComboBox extends ValueComboBox {
		private static final long serialVersionUID = 1L;

		public RoleDecisionComboBox() {
			super();
			this.setNullSelectionAllowed(false);
			this.loadData("Primary Decision Marker", "Evaluator", "Influencer",
					"Other");
		}
	}
}
