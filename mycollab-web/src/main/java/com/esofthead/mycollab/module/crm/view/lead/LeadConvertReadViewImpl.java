package com.esofthead.mycollab.module.crm.view.lead;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esofthead.mycollab.eventmanager.EventBus;
import com.esofthead.mycollab.module.crm.domain.SimpleAccount;
import com.esofthead.mycollab.module.crm.domain.SimpleContact;
import com.esofthead.mycollab.module.crm.domain.SimpleLead;
import com.esofthead.mycollab.module.crm.domain.SimpleOpportunity;
import com.esofthead.mycollab.module.crm.events.AccountEvent;
import com.esofthead.mycollab.module.crm.events.ContactEvent;
import com.esofthead.mycollab.module.crm.events.LeadEvent;
import com.esofthead.mycollab.module.crm.events.OpportunityEvent;
import com.esofthead.mycollab.module.crm.service.AccountService;
import com.esofthead.mycollab.module.crm.service.ContactService;
import com.esofthead.mycollab.module.crm.service.OpportunityService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.mvp.AbstractPageView;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.esofthead.mycollab.vaadin.ui.AddViewLayout2;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * @author MyCollab Ltd.
 * @since 3.0
 * 
 */
@ViewComponent
public class LeadConvertReadViewImpl extends AbstractPageView implements
		LeadConvertReadView {
	private static final long serialVersionUID = 1L;

	private static Logger log = LoggerFactory
			.getLogger(LeadConvertReadViewImpl.class);

	@Override
	public void displayConvertLeadInfo(SimpleLead lead) {
		this.removeAllComponents();

		AddViewLayout2 previewLayout = new AddViewLayout2(
				"You can not access the Lead, it is converted already",
				MyCollabResource.newResource("icons/22/crm/lead.png"));

		HorizontalLayout buttonControls = new HorizontalLayout();
		buttonControls.setMargin(true);
		buttonControls.addStyleName("main-info");
		Button gotoLeadListBtn = new Button(null, new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				EventBus.getInstance().fireEvent(
						new LeadEvent.GotoList(this, null));
			}
		});
		gotoLeadListBtn.setIcon(MyCollabResource
				.newResource("icons/16/back.png"));
		gotoLeadListBtn.setDescription("Back to Leads");
		gotoLeadListBtn.setStyleName("link");
		buttonControls.addComponent(gotoLeadListBtn);
		buttonControls.setWidth("100%");

		previewLayout.addControlButtons(buttonControls);

		VerticalLayout convertLeadInfoLayout = new VerticalLayout();
		previewLayout.addBody(convertLeadInfoLayout);

		Label header = new Label("Conversion Details");
		header.addStyleName("h2");
		convertLeadInfoLayout.addComponent(header);

		GridFormLayoutHelper layoutHelper = new GridFormLayoutHelper(1, 3);
		layoutHelper.getLayout().setWidth("100%");
		layoutHelper.getLayout().setMargin(false);
		layoutHelper.getLayout().addStyleName("colored-gridlayout");

		log.debug("Display associate account");
		AccountService accountService = ApplicationContextUtil
				.getSpringBean(AccountService.class);
		final SimpleAccount account = accountService
				.findAccountAssoWithConvertedLead(lead.getId(),
						AppContext.getAccountId());
		if (account != null) {
			Button accountLink = new Button(account.getAccountname(),
					new Button.ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(ClickEvent event) {
							EventBus.getInstance().fireEvent(
									new AccountEvent.GotoRead(this, account
											.getId()));

						}
					});
			accountLink.setIcon(MyCollabResource
					.newResource("icons/16/crm/account.png"));
			accountLink.setStyleName("link");
			layoutHelper.addComponent(accountLink, "Account", 0, 0);
		} else {
			layoutHelper.addComponent(new Label(""), "Account", 0, 0);
		}

		log.debug("Display associate contact");
		ContactService contactService = ApplicationContextUtil
				.getSpringBean(ContactService.class);
		final SimpleContact contact = contactService
				.findContactAssoWithConvertedLead(lead.getId(),
						AppContext.getAccountId());
		if (contact != null) {
			Button contactLink = new Button(contact.getContactName(),
					new Button.ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(ClickEvent event) {
							EventBus.getInstance().fireEvent(
									new ContactEvent.GotoRead(this, contact
											.getId()));

						}
					});
			contactLink.setIcon(MyCollabResource
					.newResource("icons/16/crm/contact.png"));
			contactLink.setStyleName("link");
			layoutHelper.addComponent(contactLink, "Contact", 0, 1);
		} else {
			layoutHelper.addComponent(new Label(""), "Contact", 0, 1);
		}

		log.debug("Display associate opportunity");
		OpportunityService opportunityService = ApplicationContextUtil
				.getSpringBean(OpportunityService.class);
		final SimpleOpportunity opportunity = opportunityService
				.findOpportunityAssoWithConvertedLead(lead.getId(),
						AppContext.getAccountId());
		if (opportunity != null) {
			Button opportunityLink = new Button(
					opportunity.getOpportunityname(),
					new Button.ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(ClickEvent event) {
							EventBus.getInstance().fireEvent(
									new OpportunityEvent.GotoRead(this,
											opportunity.getId()));

						}
					});
			opportunityLink.setIcon(MyCollabResource
					.newResource("icons/16/crm/opportunity.png"));
			opportunityLink.setStyleName("link");
			layoutHelper.addComponent(opportunityLink, "Opportunity", 0, 2);
		} else {
			layoutHelper.addComponent(new Label(""), "Opportunity", 0, 2);
		}

		convertLeadInfoLayout.addComponent(layoutHelper.getLayout());
		this.addComponent(previewLayout);

	}

}
