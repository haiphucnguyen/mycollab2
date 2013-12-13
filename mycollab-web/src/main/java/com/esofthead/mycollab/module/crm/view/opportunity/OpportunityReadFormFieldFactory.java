package com.esofthead.mycollab.module.crm.view.opportunity;

import com.esofthead.mycollab.eventmanager.EventBus;
import com.esofthead.mycollab.module.crm.domain.SimpleOpportunity;
import com.esofthead.mycollab.module.crm.events.AccountEvent;
import com.esofthead.mycollab.module.crm.events.CampaignEvent;
import com.esofthead.mycollab.vaadin.ui.AbstractBeanFieldGroupViewFieldFactory;
import com.esofthead.mycollab.vaadin.ui.GenericBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultFormViewFieldFactory.FormLinkViewField;
import com.esofthead.mycollab.vaadin.ui.DefaultFormViewFieldFactory.FormViewField;
import com.esofthead.mycollab.vaadin.ui.DefaultFormViewFieldFactory.UserLinkViewField;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.Button;
import com.vaadin.ui.Field;
import com.vaadin.ui.Button.ClickEvent;

public class OpportunityReadFormFieldFactory extends
		AbstractBeanFieldGroupViewFieldFactory<SimpleOpportunity> {
	private static final long serialVersionUID = 1L;

	public OpportunityReadFormFieldFactory(
			GenericBeanForm<SimpleOpportunity> form) {
		super(form);
	}

	@Override
	protected Field<?> onCreateField(Object propertyId) {
		Field<?> field = null;
		final SimpleOpportunity opportunity = attachForm.getBean();

		if (propertyId.equals("accountid")) {
			field = new FormLinkViewField(opportunity.getAccountName(),
					new Button.ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(ClickEvent event) {
							EventBus.getInstance().fireEvent(
									new AccountEvent.GotoRead(this, opportunity
											.getAccountid()));
						}
					});
		} else if (propertyId.equals("campaignid")) {
			field = new FormLinkViewField(opportunity.getCampaignName(),
					new Button.ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(ClickEvent event) {
							EventBus.getInstance().fireEvent(
									new CampaignEvent.GotoRead(this,
											opportunity.getCampaignid()));

						}
					});
		} else if (propertyId.equals("assignuser")) {
			field = new UserLinkViewField(opportunity.getAssignuser(),
					opportunity.getAssignUserAvatarId(),
					opportunity.getAssignUserFullName());
		} else if (propertyId.equals("expectedcloseddate")) {
			field = new FormViewField(AppContext.formatDate(opportunity
					.getExpectedcloseddate()));
		} else if (propertyId.equals("currencyid")) {
			if (opportunity.getCurrency() != null) {
				return new FormViewField(opportunity.getCurrency()
						.getShortname());
			} else {
				return new FormViewField("");
			}
		}
		return field;
	}

}
