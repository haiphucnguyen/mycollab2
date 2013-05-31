package com.esofthead.mycollab.module.crm.view.contact;

import com.esofthead.mycollab.common.ModuleNameConstants;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.module.crm.CrmTypeConstants;
import com.esofthead.mycollab.module.crm.domain.Contact;
import com.esofthead.mycollab.module.crm.domain.SimpleContact;
import com.esofthead.mycollab.module.crm.domain.criteria.EventSearchCriteria;
import com.esofthead.mycollab.module.crm.domain.criteria.OpportunitySearchCriteria;
import com.esofthead.mycollab.module.crm.ui.components.NoteListItems;
import com.esofthead.mycollab.module.crm.view.activity.EventRelatedItemListComp;
import com.esofthead.mycollab.module.user.RolePermissionCollections;
import com.esofthead.mycollab.vaadin.ui.AdvancedPreviewBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultFormViewFieldFactory;
import com.esofthead.mycollab.vaadin.ui.PreviewFormControlsGenerator;
import com.esofthead.mycollab.vaadin.ui.PreviewFormControlsGenerator2;
import com.esofthead.mycollab.vaadin.ui.ReadViewLayout;
import com.esofthead.mycollab.web.AppContext;
import com.github.wolfie.detachedtabs.DetachedTabs;
import com.github.wolfie.detachedtabs.DetachedTabs.TabChangedEvent;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.terminal.ExternalResource;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

@SuppressWarnings("serial")
public abstract class ContactPreviewBuilder extends VerticalLayout {

	protected AdvancedPreviewBeanForm<Contact> previewForm;
	protected SimpleContact contact;
	protected ContactOpportunityListComp associateOpportunityList;
	protected EventRelatedItemListComp associateActivityList;
	protected NoteListItems noteListItems;

	protected void initRelatedComponent() {
		associateOpportunityList = new ContactOpportunityListComp();
		associateActivityList = new EventRelatedItemListComp(true);
		noteListItems = new NoteListItems("Notes");
	}

	public void previewItem(SimpleContact item) {
		contact = item;
		previewForm.setItemDataSource(new BeanItem<SimpleContact>(contact));
		displayNotes();
		displayActivities();
		displayAssociateOpportunityList();
	}

	public ContactOpportunityListComp getAssociateOpportunityList() {
		return associateOpportunityList;
	}

	public EventRelatedItemListComp getAssociateActivityList() {
		return associateActivityList;
	}

	public SimpleContact getContact() {
		return contact;
	}

	public AdvancedPreviewBeanForm<Contact> getPreviewForm() {
		return previewForm;
	}

	private void displayNotes() {
		noteListItems.showNotes(CrmTypeConstants.CONTACT, contact.getId());
	}

	public void displayActivities() {
		EventSearchCriteria criteria = new EventSearchCriteria();
		criteria.setSaccountid(new NumberSearchField(AppContext.getAccountId()));
		criteria.setType(new StringSearchField(SearchField.AND,
				CrmTypeConstants.CONTACT));
		criteria.setTypeid(new NumberSearchField(contact.getId()));
		associateActivityList.setSearchCriteria(criteria);
	}

	private void displayAssociateOpportunityList() {
		OpportunitySearchCriteria criteria = new OpportunitySearchCriteria();
		criteria.setSaccountid(new NumberSearchField(SearchField.AND,
				AppContext.getAccountId()));
		criteria.setContactId(new NumberSearchField(SearchField.AND, contact
				.getId()));
		associateOpportunityList.displayOpportunities(contact);
	}

	protected class ContactFormFieldFactory extends DefaultFormViewFieldFactory {

		@Override
		protected Field onCreateField(Item item, Object propertyId,
				Component uiContext) {
			// if (propertyId.equals("accountId")) {
			// return new FormLinkViewField(contact.getAccountName(),
			// new Button.ClickListener() {
			// @Override
			// public void buttonClick(ClickEvent event) {
			// EventBus.getInstance()
			// .fireEvent(
			// new AccountEvent.GotoRead(
			// this,
			// contact.getAccountId()));
			//
			// }
			// });
			if (propertyId.equals("email")) {
				return new FormEmailLinkViewField(contact.getEmail());
			} else if (propertyId.equals("assignuser")) {
				return new FormLinkViewField(contact.getAssignUserFullName(),
						new Button.ClickListener() {
							@Override
							public void buttonClick(ClickEvent event) {
								// TODO Auto-generated method stub
							}
						});
			} else if (propertyId.equals("iscallable")) {
				if (contact.getIscallable() == null
						|| Boolean.FALSE == contact.getIscallable()) {
					return new FormViewField("No");
				} else {
					return new FormViewField("Yes");
				}
			}

			return null;
		}
	}

	public static class ReadView extends ContactPreviewBuilder {
		private VerticalLayout contactInformation;
		private VerticalLayout relatedItemsContainer;
		private ReadViewLayout contactAddLayout;

		public ReadView() {
			contactAddLayout = new ReadViewLayout(new ThemeResource(
					"icons/18/crm/account.png"));
			this.addComponent(contactAddLayout);

			initRelatedComponent();

			previewForm = new AdvancedPreviewBeanForm<Contact>() {
				@Override
				public void setItemDataSource(Item newDataSource) {
					this.setFormLayoutFactory(new ContactFormLayoutFactory.ContactInformationLayout(true));
					this.setFormFieldFactory(new ContactFormFieldFactory());
					super.setItemDataSource(newDataSource);
					contactAddLayout.setTitle(contact.getContactName());
				}

				@Override
				protected void doPrint() {
					// Create a window that contains what you want to print
					Window window = new Window("Window to Print");

					ContactPreviewBuilder printView = new ContactPreviewBuilder.PrintView();
					printView.previewItem(contact);
					window.addComponent(printView);

					// Add the printing window as a new application-level window
					getApplication().addWindow(window);

					// Open it as a popup window with no decorations
					getWindow().open(new ExternalResource(window.getURL()),
							"_blank", 1100, 200, // Width and height
							Window.BORDER_NONE); // No decorations

					// Print automatically when the window opens.
					// This call will block until the print dialog exits!
					window.executeJavaScript("print();");

					// Close the window automatically after printing
					window.executeJavaScript("self.close();");
				}

				@Override
				protected void showHistory() {
					ContactHistoryLogWindow historyLog = new ContactHistoryLogWindow(
							ModuleNameConstants.CRM, CrmTypeConstants.CONTACT,
							contact.getId());
					getWindow().addWindow(historyLog);
				}
			};

			final Layout optionalActionControls = PreviewFormControlsGenerator2 
					.createFormOptionalControls(previewForm,
							RolePermissionCollections.CRM_CONTACT);
			
			contactAddLayout.addControlButtons(optionalActionControls);
			
			contactInformation = new VerticalLayout();
			contactInformation.addStyleName("main-info");
			
			final Layout actionControls = PreviewFormControlsGenerator2
					.createFormControls(previewForm,
							RolePermissionCollections.CRM_CONTACT);
			actionControls.addStyleName("control-buttons");
			contactInformation.addComponent(actionControls);
			
			contactInformation.addComponent(previewForm);
			
			contactInformation.addComponent(noteListItems);

			contactAddLayout.addTab(contactInformation, "Contact Information");

			relatedItemsContainer = new VerticalLayout();
			relatedItemsContainer.setMargin(true);
			
			contactAddLayout.addTab(relatedItemsContainer, "More Information");
			
			this.addComponent(contactAddLayout);

			contactAddLayout
					.addTabChangedListener(new DetachedTabs.TabChangedListener() {

						@Override
						public void tabChanged(final TabChangedEvent event) {
							final Button btn = event.getSource();
							final String caption = btn.getCaption();
							if ("Contact Information".equals(caption)) {

							} else if ("More Information".equals(caption)) {
								relatedItemsContainer.addComponent(associateActivityList);
								relatedItemsContainer.addComponent(associateOpportunityList);
							}
							contactAddLayout.selectTab(caption);
						}
					});
		}
	}

	public static class PrintView extends ContactPreviewBuilder {

		public PrintView() {
			previewForm = new AdvancedPreviewBeanForm<Contact>() {
				@Override
				public void setItemDataSource(Item newDataSource) {
					this.setFormLayoutFactory(new FormLayoutFactory());
					this.setFormFieldFactory(new ContactFormFieldFactory());
					super.setItemDataSource(newDataSource);
				}
			};
			initRelatedComponent();

			this.addComponent(previewForm);
		}

		class FormLayoutFactory extends ContactFormLayoutFactory {

			private static final long serialVersionUID = 1L;

			public FormLayoutFactory() {
				super(contact.getContactName());
			}

			@Override
			protected Layout createTopPanel() {
				return null;
			}

			@Override
			protected Layout createBottomPanel() {
				VerticalLayout relatedItemsPanel = new VerticalLayout();
				relatedItemsPanel.setWidth("100%");

				relatedItemsPanel.addComponent(noteListItems);
				relatedItemsPanel.addComponent(associateActivityList);
				relatedItemsPanel.addComponent(associateOpportunityList);

				return relatedItemsPanel;
			}
		}
	}
}
