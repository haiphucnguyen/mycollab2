package com.esofthead.mycollab.module.crm.view.contact;

import java.util.Set;

import org.vaadin.dialogs.ConfirmDialog;

import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.configuration.SiteConfiguration;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.module.crm.CrmLinkGenerator;
import com.esofthead.mycollab.module.crm.CrmTypeConstants;
import com.esofthead.mycollab.module.crm.domain.Contact;
import com.esofthead.mycollab.module.crm.domain.ContactOpportunity;
import com.esofthead.mycollab.module.crm.domain.SimpleOpportunity;
import com.esofthead.mycollab.module.crm.domain.criteria.OpportunitySearchCriteria;
import com.esofthead.mycollab.module.crm.service.ContactService;
import com.esofthead.mycollab.module.crm.service.OpportunityService;
import com.esofthead.mycollab.module.crm.ui.components.RelatedListComp2;
import com.esofthead.mycollab.security.RolePermissionCollections;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.ui.ConfirmDialogExt;
import com.esofthead.mycollab.vaadin.ui.MyCollabResource;
import com.esofthead.mycollab.vaadin.ui.SplitButton;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.vaadin.event.MouseEvents;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class ContactOpportunityListComp2 extends
RelatedListComp2<OpportunityService, OpportunitySearchCriteria, SimpleOpportunity> {
	private static final long serialVersionUID = 8849210168154580096L;

	private Contact contact;

	public ContactOpportunityListComp2() {
		super(ApplicationContextUtil.getSpringBean(OpportunityService.class), 20);
		this.setBlockDisplayHandler(new ContactOpportunityBlockDisplay());
	}

	@Override
	protected Component generateTopControls() {
		VerticalLayout controlsBtnWrap = new VerticalLayout();
		controlsBtnWrap.setWidth("100%");
		final SplitButton controlsBtn = new SplitButton();
		controlsBtn.addStyleName(UIConstants.THEME_GREEN_LINK);
		controlsBtn.setCaption("New Opportunity");
		controlsBtn.setIcon(MyCollabResource
				.newResource("icons/16/addRecord.png"));
		controlsBtn
		.addClickListener(new SplitButton.SplitButtonClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void splitButtonClick(
					SplitButton.SplitButtonClickEvent event) {
				fireNewRelatedItem("");
			}
		});
		controlsBtn.setSizeUndefined();
		Button selectBtn = new Button("Select from existing opportunities",
				new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(Button.ClickEvent event) {
				ContactOpportunitySelectionWindow2 opportunitiesWindow = new ContactOpportunitySelectionWindow2(
						ContactOpportunityListComp2.this);
				OpportunitySearchCriteria criteria = new OpportunitySearchCriteria();
				criteria.setSaccountid(new NumberSearchField(AppContext
						.getAccountId()));
				UI.getCurrent().addWindow(opportunitiesWindow);
				opportunitiesWindow.setSearchCriteria(criteria);
				controlsBtn.setPopupVisible(false);
			}
		});
		selectBtn.setIcon(MyCollabResource.newResource("icons/16/select.png"));
		selectBtn.setStyleName("link");

		VerticalLayout buttonControlsLayout = new VerticalLayout();
		buttonControlsLayout.addComponent(selectBtn);
		controlsBtn.setContent(buttonControlsLayout);

		controlsBtn.setEnabled(AppContext
				.canWrite(RolePermissionCollections.CRM_OPPORTUNITY));

		controlsBtnWrap.addComponent(controlsBtn);
		controlsBtnWrap.setComponentAlignment(controlsBtn, Alignment.MIDDLE_RIGHT);
		return controlsBtnWrap;
	}

	public void displayOpportunities(final Contact contact) {
		this.contact = contact;
		loadOpportunities();
	}

	private void loadOpportunities() {
		final OpportunitySearchCriteria criteria = new OpportunitySearchCriteria();
		criteria.setSaccountid(new NumberSearchField(SearchField.AND,
				AppContext.getAccountId()));
		criteria.setContactId(new NumberSearchField(SearchField.AND, contact
				.getId()));
		setSearchCriteria(criteria);
	}

	@Override
	public void refresh() {
		loadOpportunities();
	}

	@Override
	public void setSelectedItems(final Set selectedItems) {
		fireSelectedRelatedItems(selectedItems);
	}

	public class ContactOpportunityBlockDisplay implements BlockDisplayHandler<SimpleOpportunity> {

		@Override
		public Component generateBlock(final SimpleOpportunity opportunity, int blockIndex) {
			CssLayout beanBlock = new CssLayout();
			beanBlock.addStyleName("bean-block");
			beanBlock.setWidth("350px");

			VerticalLayout blockContent = new VerticalLayout();
			HorizontalLayout blockTop = new HorizontalLayout();
			blockTop.setSpacing(true);
			CssLayout iconWrap = new CssLayout();
			iconWrap.setStyleName("icon-wrap");
			Image opportunityIcon = new Image(null, MyCollabResource.newResource("icons/48/crm/opportunity.png"));
			iconWrap.addComponent(opportunityIcon);
			blockTop.addComponent(iconWrap);

			VerticalLayout opportunityInfo = new VerticalLayout();
			opportunityInfo.setSpacing(true);

			Image btnDelete = new Image(null, MyCollabResource
					.newResource("icons/12/project/icon_x.png"));
			btnDelete.addClickListener(new MouseEvents.ClickListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void click(MouseEvents.ClickEvent event) {
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
										ContactService contactService = ApplicationContextUtil
												.getSpringBean(ContactService.class);
										ContactOpportunity associateOpportunity = new ContactOpportunity();
										associateOpportunity
										.setContactid(contact
												.getId());
										associateOpportunity
										.setOpportunityid(opportunity
												.getId());
										contactService
										.removeContactOpportunityRelationship(
												associateOpportunity,
												AppContext
												.getAccountId());
										ContactOpportunityListComp2.this.refresh();
									}
								}
							});
				}
			});
			btnDelete.addStyleName("icon-btn");

			blockContent.addComponent(btnDelete);
			blockContent.setComponentAlignment(btnDelete, Alignment.TOP_RIGHT);

			Label opportunityName = new Label("Name: <a href='" + SiteConfiguration.getSiteUrl(AppContext.getSession().getSubdomain()) 
					+ CrmLinkGenerator.generateCrmItemLink(CrmTypeConstants.OPPORTUNITY, opportunity.getId()) 
					+ "'>" + opportunity.getOpportunityname() + "</a>", ContentMode.HTML);

			opportunityInfo.addComponent(opportunityName);

			Label opportunityAmount = new Label("Amount: " + (opportunity.getAmount() != null ? opportunity.getAmount() : ""));
			if (opportunity.getCurrency() != null && opportunity.getAmount() != null) {
				opportunityAmount.setValue(opportunityAmount.getValue() + opportunity.getCurrency().getSymbol());
			}
			opportunityInfo.addComponent(opportunityAmount);

			Label opportunitySaleStage = new Label("Sale Stage: " + (opportunity.getSalesstage() != null ? opportunity.getSalesstage() : ""));
			opportunityInfo.addComponent(opportunitySaleStage);

			Label opportunityExpectedCloseDate = new Label("Expected Close Date: " + (opportunity.getExpectedcloseddate() != null ? AppContext.formatDate(opportunity.getExpectedcloseddate()) : ""));
			opportunityInfo.addComponent(opportunityExpectedCloseDate);

			blockTop.addComponent(opportunityInfo);
			blockTop.setExpandRatio(opportunityInfo, 1.0f);
			blockTop.setWidth("100%");
			blockContent.addComponent(blockTop);

			blockContent.setWidth("100%");

			beanBlock.addComponent(blockContent);
			return beanBlock;
		}

	}

}
