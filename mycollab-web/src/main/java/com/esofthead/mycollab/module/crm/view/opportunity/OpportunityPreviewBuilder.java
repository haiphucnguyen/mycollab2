package com.esofthead.mycollab.module.crm.view.opportunity;

import com.esofthead.mycollab.common.ModuleNameConstants;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.module.crm.CrmTypeConstants;
import com.esofthead.mycollab.module.crm.domain.Opportunity;
import com.esofthead.mycollab.module.crm.domain.SimpleOpportunity;
import com.esofthead.mycollab.module.crm.domain.criteria.EventSearchCriteria;
import com.esofthead.mycollab.module.crm.events.AccountEvent;
import com.esofthead.mycollab.module.crm.events.CampaignEvent;
import com.esofthead.mycollab.module.crm.ui.components.NoteListItems;
import com.esofthead.mycollab.module.crm.view.activity.EventRelatedItemListComp;
import com.esofthead.mycollab.module.user.RolePermissionCollections;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.ui.AdvancedPreviewBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultFormViewFieldFactory;
import com.esofthead.mycollab.vaadin.ui.PreviewFormControlsGenerator2;
import com.esofthead.mycollab.vaadin.ui.ReadViewLayout;
import com.esofthead.mycollab.web.AppContext;
import com.esofthead.mycollab.web.MyCollabResource;
import com.github.wolfie.detachedtabs.DetachedTabs;
import com.github.wolfie.detachedtabs.DetachedTabs.TabChangedEvent;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.terminal.ExternalResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

@SuppressWarnings("serial")
public class OpportunityPreviewBuilder extends VerticalLayout {

	protected AdvancedPreviewBeanForm<Opportunity> previewForm;
	protected SimpleOpportunity opportunity;
	protected OpportunityContactListComp associateContactList;
	protected OpportunityLeadListComp associateLeadList;
	protected NoteListItems noteListItems;
	protected EventRelatedItemListComp associateActivityList;

	protected void initRelatedComponent() {
		associateContactList = new OpportunityContactListComp();
		associateLeadList = new OpportunityLeadListComp();
		associateActivityList = new EventRelatedItemListComp(true);
		noteListItems = new NoteListItems("Notes");
	}

	public void previewItem(SimpleOpportunity item) {
		this.opportunity = item;
		previewForm.setItemDataSource(new BeanItem<Opportunity>(opportunity));

		displayActivities();
		displayContacts();
		displayLeads();
	}

	public SimpleOpportunity getOpportunity() {
		return opportunity;
	}

	public void displayActivities() {
		EventSearchCriteria criteria = new EventSearchCriteria();
		criteria.setSaccountid(new NumberSearchField(AppContext.getAccountId()));
		criteria.setType(new StringSearchField(SearchField.AND,
				CrmTypeConstants.OPPORTUNITY));
		criteria.setTypeid(new NumberSearchField(opportunity.getId()));
		associateActivityList.setSearchCriteria(criteria);
	}

	private void displayContacts() {
		associateContactList.displayContacts(opportunity);
	}

	private void displayLeads() {
		associateLeadList.displayLeads(opportunity);
	}

	public AdvancedPreviewBeanForm<Opportunity> getPreviewForm() {
		return previewForm;
	}

	public EventRelatedItemListComp getAssociateActivityList() {
		return associateActivityList;
	}

	public OpportunityContactListComp getAssociateContactList() {
		return associateContactList;
	}

	public OpportunityLeadListComp getAssociateLeadList() {
		return associateLeadList;
	}

	public static class ReadView extends OpportunityPreviewBuilder {

		private VerticalLayout opportunityInformationLayout;
		private VerticalLayout relatedItemsContainer;
		private ReadViewLayout opportunityAddLayout;

		public ReadView() {
			opportunityAddLayout = new ReadViewLayout(
					MyCollabResource
							.newResource("icons/22/crm/opportunity.png"));
			this.addComponent(opportunityAddLayout);

			initRelatedComponent();

			previewForm = new AdvancedPreviewBeanForm<Opportunity>() {
				@Override
				public void setItemDataSource(Item newDataSource) {
					this.setFormLayoutFactory(new OpportunityFormLayoutFactory.OpportunityInformationLayout());
					this.setFormFieldFactory(new OpportunityFormFieldFactory());
					super.setItemDataSource(newDataSource);
					opportunityAddLayout.setTitle(opportunity
							.getOpportunityname());
				}

				@Override
				protected void doPrint() {
					// Create a window that contains what you want to print
					Window window = new Window("Window to Print");

					OpportunityPreviewBuilder printView = new OpportunityPreviewBuilder.PrintView();
					printView.previewItem(opportunity);
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
					OpportunityHistoryLogWindow historyLog = new OpportunityHistoryLogWindow(
							ModuleNameConstants.CRM,
							CrmTypeConstants.OPPORTUNITY, opportunity.getId());
					getWindow().addWindow(historyLog);
				}
			};

			final Layout optionalActionControls = PreviewFormControlsGenerator2
					.createFormOptionalControls(previewForm,
							RolePermissionCollections.CRM_OPPORTUNITY);

			opportunityAddLayout.addControlButtons(optionalActionControls);

			opportunityInformationLayout = new VerticalLayout();

			opportunityInformationLayout.addStyleName("main-info");

			final Layout actionControls = PreviewFormControlsGenerator2
					.createFormControls(previewForm,
							RolePermissionCollections.CRM_OPPORTUNITY);
			actionControls.addStyleName("control-buttons");
			opportunityInformationLayout.addComponent(actionControls);

			opportunityInformationLayout.addComponent(previewForm);
			opportunityInformationLayout.addComponent(noteListItems);

			opportunityAddLayout.addTab(opportunityInformationLayout,
					"Opportunity Information");

			relatedItemsContainer = new VerticalLayout();
			relatedItemsContainer.setMargin(true);
			opportunityAddLayout.addTab(relatedItemsContainer,
					"More Information");
			opportunityAddLayout
					.addTabChangedListener(new DetachedTabs.TabChangedListener() {
						@Override
						public void tabChanged(final TabChangedEvent event) {
							final Button btn = event.getSource();
							final String caption = btn.getCaption();
							if ("Opportunity Information".equals(caption)) {

							} else if ("More Information".equals(caption)) {
								relatedItemsContainer
										.addComponent(associateActivityList);
								relatedItemsContainer
										.addComponent(associateContactList);
								relatedItemsContainer
										.addComponent(associateLeadList);
							}
							opportunityAddLayout.selectTab(caption);
						}
					});
		}
	}

	/**
     *
     */
	public static class PrintView extends OpportunityPreviewBuilder {

		public PrintView() {
			previewForm = new AdvancedPreviewBeanForm<Opportunity>() {
				@Override
				public void setItemDataSource(Item newDataSource) {
					this.setFormLayoutFactory(new FormLayoutFactory());
					this.setFormFieldFactory(new OpportunityFormFieldFactory());
					super.setItemDataSource(newDataSource);
				}
			};
			initRelatedComponent();

			this.addComponent(previewForm);
		}

		class FormLayoutFactory extends OpportunityFormLayoutFactory {

			private static final long serialVersionUID = 1L;

			public FormLayoutFactory() {
				super(opportunity.getOpportunityname());
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
				relatedItemsPanel.addComponent(associateContactList);
				relatedItemsPanel.addComponent(associateLeadList);

				return relatedItemsPanel;
			}
		}
	}

	protected class OpportunityFormFieldFactory extends
			DefaultFormViewFieldFactory {

		@Override
		protected Field onCreateField(Item item, Object propertyId,
				Component uiContext) {
			Field field = null;
			if (propertyId.equals("accountid")) {
				field = new FormLinkViewField(opportunity.getAccountName(),
						new Button.ClickListener() {
							private static final long serialVersionUID = 1L;

							@Override
							public void buttonClick(ClickEvent event) {
								EventBus.getInstance().fireEvent(
										new AccountEvent.GotoRead(this,
												opportunity.getAccountid()));
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
				field = new FormLinkViewField(
						opportunity.getAssignUserFullName(),
						new Button.ClickListener() {
							private static final long serialVersionUID = 1L;

							@Override
							public void buttonClick(ClickEvent event) {

							}
						});
			} else if (propertyId.equals("expectedcloseddate")) {
				field = new FormViewField(AppContext.formatDate(opportunity
						.getExpectedcloseddate()));
			} else if (propertyId.equals("currencyid")) {
				if (opportunity.getCurrency() != null) {
					return new FormViewField(opportunity.getCurrency()
							.getName());
				} else {
					return new FormViewField("");
				}
			}
			return field;
		}
	}
}
