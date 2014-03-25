package com.esofthead.mycollab.module.crm.view.opportunity;

import java.util.Set;

import org.vaadin.dialogs.ConfirmDialog;

import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.configuration.SiteConfiguration;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.module.crm.CrmLinkGenerator;
import com.esofthead.mycollab.module.crm.CrmTypeConstants;
import com.esofthead.mycollab.module.crm.domain.Opportunity;
import com.esofthead.mycollab.module.crm.domain.SimpleContactOpportunityRel;
import com.esofthead.mycollab.module.crm.domain.criteria.ContactSearchCriteria;
import com.esofthead.mycollab.module.crm.service.ContactOpportunityService;
import com.esofthead.mycollab.module.crm.service.ContactService;
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
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class OpportunityContactListComp2 extends
RelatedListComp2<ContactOpportunityService, ContactSearchCriteria, SimpleContactOpportunityRel> {
	private static final long serialVersionUID = 5717208523696358616L;

	private Opportunity opportunity;

	public OpportunityContactListComp2() {
		super(ApplicationContextUtil.getSpringBean(ContactOpportunityService.class), 20);
		this.setBlockDisplayHandler(new OpportunityContactBlockDisplay());
	}

	@Override
	protected Component generateTopControls() {
		VerticalLayout controlsBtnWrap = new VerticalLayout();
		controlsBtnWrap.setWidth("100%");
		final SplitButton controlsBtn = new SplitButton();
		controlsBtn.setSizeUndefined();
		controlsBtn.setEnabled(AppContext
				.canWrite(RolePermissionCollections.CRM_CONTACT));
		controlsBtn.addStyleName(UIConstants.THEME_GREEN_LINK);
		controlsBtn.setCaption("New Contact");
		controlsBtn.setIcon(MyCollabResource
				.newResource("icons/16/addRecord.png"));
		controlsBtn
		.addClickListener(new SplitButton.SplitButtonClickListener() {
			@Override
			public void splitButtonClick(
					final SplitButton.SplitButtonClickEvent event) {
				fireNewRelatedItem("");
			}
		});
		final Button selectBtn = new Button("Select from existing contacts",
				new Button.ClickListener() {
			@Override
			public void buttonClick(final ClickEvent event) {
				final OpportunityContactSelectionWindow2 contactsWindow = new OpportunityContactSelectionWindow2(
						OpportunityContactListComp2.this);
				final ContactSearchCriteria criteria = new ContactSearchCriteria();
				criteria.setSaccountid(new NumberSearchField(AppContext
						.getAccountId()));
				UI.getCurrent().addWindow(contactsWindow);
				contactsWindow.setSearchCriteria(criteria);
				controlsBtn.setPopupVisible(false);
			}
		});
		selectBtn.setIcon(MyCollabResource.newResource("icons/16/select.png"));
		selectBtn.setStyleName("link");
		VerticalLayout buttonControlLayout = new VerticalLayout();
		buttonControlLayout.addComponent(selectBtn);
		controlsBtn.setContent(buttonControlLayout);

		controlsBtnWrap.addComponent(controlsBtn);
		controlsBtnWrap.setComponentAlignment(controlsBtn, Alignment.MIDDLE_RIGHT);
		return controlsBtnWrap;
	}

	public void displayContacts(final Opportunity opportunity) {
		this.opportunity = opportunity;
		loadContacts();
	}

	private void loadContacts() {
		final ContactSearchCriteria criteria = new ContactSearchCriteria();
		criteria.setSaccountid(new NumberSearchField(SearchField.AND,
				AppContext.getAccountId()));
		criteria.setOpportunityId(new NumberSearchField(SearchField.AND, opportunity
				.getId()));
		setSearchCriteria(criteria);
	}

	@Override
	public void refresh() {
		loadContacts();
	}

	@Override
	public void setSelectedItems(final Set selectedItems) {
		fireSelectedRelatedItems(selectedItems);
	}

	public class OpportunityContactBlockDisplay implements BlockDisplayHandler<SimpleContactOpportunityRel> {

		@Override
		public Component generateBlock(final SimpleContactOpportunityRel contact, int blockIndex) {
			CssLayout beanBlock = new CssLayout();
			beanBlock.addStyleName("bean-block");
			beanBlock.setWidth("350px");

			VerticalLayout blockContent = new VerticalLayout();
			HorizontalLayout blockTop = new HorizontalLayout();
			blockTop.setSpacing(true);
			CssLayout iconWrap = new CssLayout();
			iconWrap.setStyleName("icon-wrap");
			Image contactAvatar = new Image(null, MyCollabResource.newResource("icons/48/crm/contact.png"));
			iconWrap.addComponent(contactAvatar);
			blockTop.addComponent(iconWrap);

			VerticalLayout contactInfo = new VerticalLayout();
			contactInfo.setSpacing(true);

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
										final ContactService contactService = ApplicationContextUtil
												.getSpringBean(ContactService.class);
										contact.setAccountid(null);
										contactService
										.updateWithSession(
												contact,
												AppContext
												.getUsername());
										OpportunityContactListComp2.this.refresh();
									}
								}
							});
				}
			});
			btnDelete.addStyleName("icon-btn");

			blockContent.addComponent(btnDelete);
			blockContent.setComponentAlignment(btnDelete, Alignment.TOP_RIGHT);

			Label contactName = new Label("Name: <a href='" + SiteConfiguration.getSiteUrl(AppContext.getSession().getSubdomain()) 
					+ CrmLinkGenerator.generateCrmItemLink(CrmTypeConstants.CONTACT, contact.getId()) 
					+ "'>" + contact.getContactName() + "</a>", ContentMode.HTML);

			contactInfo.addComponent(contactName);

			Label contactTitle = new Label("Title: " + (contact.getTitle() != null ? contact.getTitle() : ""));
			contactInfo.addComponent(contactTitle);

			Label contactEmail = new Label("Email: " 
					+ (contact.getEmail() != null ? 
							"<a href='mailto:" + contact.getEmail() + "'>" + contact.getEmail() + "</a>" 
							: "")
							, ContentMode.HTML);
			contactInfo.addComponent(contactEmail);

			Label contactOfficePhone = new Label("Office Phone: " + (contact.getOfficephone() != null ? contact.getOfficephone() : ""));
			contactInfo.addComponent(contactOfficePhone);

			blockTop.addComponent(contactInfo);
			blockTop.setExpandRatio(contactInfo, 1.0f);
			blockTop.setWidth("100%");
			blockContent.addComponent(blockTop);

			blockContent.setWidth("100%");

			beanBlock.addComponent(blockContent);
			return beanBlock;
		}

	}

}
