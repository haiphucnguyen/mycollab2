package com.esofthead.mycollab.module.crm.view.opportunity;

import com.esofthead.mycollab.module.crm.domain.Opportunity;
import com.esofthead.mycollab.module.crm.domain.SimpleOpportunity;
import com.esofthead.mycollab.module.crm.events.AccountEvent;
import com.esofthead.mycollab.module.crm.events.CampaignEvent;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.events.HasPreviewFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.AdvancedPreviewBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultFormViewFieldFactory;
import com.esofthead.mycollab.vaadin.ui.PreviewFormControlsGenerator;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Button.ClickEvent;

public class OpportunityReadViewImpl extends AbstractView implements
		OpportunityReadView {
	private static final long serialVersionUID = 1L;

	private PreviewForm previewForm;

	private SimpleOpportunity opportunity;

	public OpportunityReadViewImpl() {
		super();
		previewForm = new PreviewForm();
		this.addComponent(previewForm);
	}

	@Override
	public void previewItem(SimpleOpportunity item) {
		this.opportunity = item;
		previewForm.setItemDataSource(new BeanItem<Opportunity>(opportunity));
	}

	@Override
	public HasPreviewFormHandlers<Opportunity> getPreviewFormHandlers() {
		return previewForm;
	}

	private class PreviewForm extends AdvancedPreviewBeanForm<Opportunity> {
		private static final long serialVersionUID = 1L;

		@Override
		public void setItemDataSource(Item newDataSource) {
			this.setFormLayoutFactory(new FormLayoutFactory());
			this.setFormFieldFactory(new DefaultFormViewFieldFactory() {
				private static final long serialVersionUID = 1L;

				@Override
				protected Field onCreateField(Item item, Object propertyId,
						Component uiContext) {
					Field field = null;
					if (propertyId.equals("accountid")) {
						field = new FormLinkViewField(opportunity
								.getAccountName(), new Button.ClickListener() {
							private static final long serialVersionUID = 1L;

							@Override
							public void buttonClick(ClickEvent event) {
								EventBus.getInstance().fireEvent(
										new AccountEvent.GotoRead(this,
												opportunity.getAccountid()));
							}
						});
					} else if (propertyId.equals("campaignid")) {
						field = new FormLinkViewField(opportunity
								.getCampaignName(), new Button.ClickListener() {
							private static final long serialVersionUID = 1L;

							@Override
							public void buttonClick(ClickEvent event) {
								EventBus.getInstance().fireEvent(
										new CampaignEvent.GotoRead(this,
												opportunity.getCampaignid()));

							}
						});
					} else if (propertyId.equals("assignuser")) {
						field = new FormLinkViewField(opportunity
								.getAssignUserFullName(),
								new Button.ClickListener() {
									private static final long serialVersionUID = 1L;

									@Override
									public void buttonClick(ClickEvent event) {
										// TODO Auto-generated method stub

									}
								});
					} else if (propertyId.equals("expectedcloseddate")) {
						field = new FormViewField(
								AppContext.formatDate(opportunity
										.getExpectedcloseddate()));
					} 
					return field;
				}

			});
			super.setItemDataSource(newDataSource);
		}

		class FormLayoutFactory extends OpportunityFormLayoutFactory {
			private static final long serialVersionUID = 1L;

			@Override
			protected HorizontalLayout createButtonControls() {
				return (new PreviewFormControlsGenerator<Opportunity>(
						PreviewForm.this)).createButtonControls();
			}
		}
	}

	@Override
	public SimpleOpportunity getItem() {
		return opportunity;
	}

}
