package com.esofthead.mycollab.module.crm.view.lead;

import com.esofthead.mycollab.eventmanager.EventBus;
import com.esofthead.mycollab.module.crm.domain.Opportunity;
import com.esofthead.mycollab.module.crm.domain.SimpleLead;
import com.esofthead.mycollab.module.crm.events.LeadEvent;
import com.esofthead.mycollab.module.crm.service.LeadService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.mvp.AbstractPageView;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.esofthead.mycollab.vaadin.ui.AbstractBeanFieldGroupEditFieldFactory;
import com.esofthead.mycollab.vaadin.ui.AddViewLayout2;
import com.esofthead.mycollab.vaadin.ui.AdvancedEditBeanForm;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.vaadin.ui.IFormLayoutFactory;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * @author MyCollab Ltd.
 * @since 3.0
 * 
 */
@ViewComponent
public class LeadConvertInfoViewImpl extends AbstractPageView implements
		LeadConvertInfoView {
	private static final long serialVersionUID = 1L;

	private SimpleLead lead;
	private LeadOpportunityForm opportunityForm;

	@Override
	public void displayConvertLead(SimpleLead lead) {
		this.lead = lead;
		this.removeAllComponents();

		String formTitle = "Convert Lead (%s - %s)";
		AddViewLayout2 formAddLayout = new AddViewLayout2(formTitle,
				MyCollabResource.newResource("icons/22/crm/lead.png"));

		ComponentContainer buttonControls = createButtonControls();
		if (buttonControls != null) {
			final HorizontalLayout controlPanel = new HorizontalLayout();
			buttonControls.setSizeUndefined();
			controlPanel.addComponent(buttonControls);
			controlPanel.setWidth("100%");
			controlPanel.setMargin(true);
			controlPanel.setComponentAlignment(buttonControls,
					Alignment.MIDDLE_CENTER);
			formAddLayout.addControlButtons(controlPanel);
		}

		formAddLayout.addBody(createBodyControls());

		this.addComponent(formAddLayout);
	}

	private ComponentContainer createButtonControls() {
		final HorizontalLayout layout = new HorizontalLayout();
		layout.setSpacing(true);
		layout.setStyleName("addNewControl");

		Button convertButton = new Button("Convert",
				new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(ClickEvent event) {
						LeadService leadService = ApplicationContextUtil
								.getSpringBean(LeadService.class);
						lead.setStatus("Converted");
						leadService.updateWithSession(lead,
								AppContext.getUsername());
						Opportunity opportunity = null;
						if (opportunityForm.isVisible()) {
							if (opportunityForm.validateForm()) {
								opportunity = opportunityForm.getBean();
							}
						}

						leadService.convertLead(lead, opportunity);
						EventBus.getInstance().fireEvent(
								new LeadEvent.GotoRead(
										LeadConvertInfoViewImpl.this, lead
												.getId()));
					}
				});
		convertButton.setStyleName(UIConstants.THEME_BLUE_LINK);
		layout.addComponent(convertButton);
		layout.setComponentAlignment(convertButton, Alignment.MIDDLE_CENTER);

		Button cancelButton = new Button("Cancel", new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				EventBus.getInstance().fireEvent(
						new LeadEvent.GotoList(this, null));

			}
		});
		cancelButton.setStyleName(UIConstants.THEME_BLUE_LINK);
		layout.addComponent(cancelButton);
		layout.setComponentAlignment(cancelButton, Alignment.MIDDLE_CENTER);
		return layout;
	}

	private ComponentContainer createBodyControls() {
		final VerticalLayout layout = new VerticalLayout();
		layout.setSizeFull();

		String createAccountTxt = "Create Account: " + lead.getAccountname();
		Label createAccountLbl = new Label(createAccountTxt);
		layout.addComponent(createAccountLbl);

		String createContactTxt = "Create Contact: " + lead.getLastname() + " "
				+ lead.getFirstname();
		Label createContactLbl = new Label(createContactTxt);
		layout.addComponent(createContactLbl);

		final CheckBox isCreateOpportunityChk = new CheckBox(
				"Create an new opportunity for this account");
		isCreateOpportunityChk
				.addValueChangeListener(new ValueChangeListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void valueChange(ValueChangeEvent event) {
						Boolean isSelected = isCreateOpportunityChk.getValue();
						if (isSelected) {
							opportunityForm = new LeadOpportunityForm();
							Opportunity opportunity = new Opportunity();
							opportunityForm.setBean(opportunity);
							layout.addComponent(opportunityForm);
						} else {
							layout.removeComponent(opportunityForm);
						}

					}
				});

		layout.addComponent(isCreateOpportunityChk);
		return layout;
	}

	private class LeadOpportunityForm extends AdvancedEditBeanForm<Opportunity> {
		private static final long serialVersionUID = 1L;

		public LeadOpportunityForm() {
			super();
			this.setFormLayoutFactory(new IFormLayoutFactory() {
				private static final long serialVersionUID = 1L;

				private GridFormLayoutHelper informationLayout;

				@Override
				public Layout getLayout() {
					this.informationLayout = new GridFormLayoutHelper(2, 3,
							"100%", "167px", Alignment.MIDDLE_LEFT);
					this.informationLayout.getLayout().setWidth("100%");
					this.informationLayout.getLayout().setMargin(false);
					this.informationLayout.getLayout().addStyleName(
							"colored-gridlayout");
					return informationLayout.getLayout();
				}

				@Override
				public boolean attachField(Object propertyId, Field<?> field) {
					if (propertyId.equals("opportunityname")) {
						informationLayout.addComponent(field, "Opportunity", 0,
								0);
					} else if (propertyId.equals("expectedcloseddate")) {
						informationLayout.addComponent(field,
								"Expected Close Date", 1, 0);
					} else if (propertyId.equals("salesstage")) {
						informationLayout.addComponent(field, "Sales Stage", 0,
								1);
					} else if (propertyId.equals("campaignid")) {
						informationLayout.addComponent(field, "Campaign", 1, 1);
					} else if (propertyId.equals("amount")) {
						informationLayout.addComponent(field, "Amount", 0, 2);
					} else if (propertyId.equals("currencyid")) {
						informationLayout.addComponent(field, "Currency", 1, 2);
					}
					return false;
				}
			});

			this.setBeanFormFieldFactory(new AbstractBeanFieldGroupEditFieldFactory<Opportunity>(
					this) {
				private static final long serialVersionUID = 1L;

				@Override
				protected Field<?> onCreateField(Object propertyId) {
					return null;
				}

			});
		}
	}
}
