package com.esofthead.mycollab.module.crm.view.campaign;

import com.esofthead.mycollab.common.ModuleNameConstants;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.module.crm.CrmTypeConstants;
import com.esofthead.mycollab.module.crm.domain.CampaignWithBLOBs;
import com.esofthead.mycollab.module.crm.domain.SimpleCampaign;
import com.esofthead.mycollab.module.crm.domain.criteria.EventSearchCriteria;
import com.esofthead.mycollab.module.crm.ui.components.NoteListItems;
import com.esofthead.mycollab.module.crm.view.activity.EventRelatedItemListComp;
import com.esofthead.mycollab.module.user.RolePermissionCollections;
import com.esofthead.mycollab.vaadin.ui.AdvancedPreviewBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultFormViewFieldFactory;
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
public class CampaignPreviewBuilder extends VerticalLayout {

	protected AdvancedPreviewBeanForm<CampaignWithBLOBs> previewForm;
	protected SimpleCampaign campaign;
	protected CampaignAccountListComp associateAccountList;
	protected CampaignContactListComp associateContactList;
	protected CampaignLeadListComp associateLeadList;
	protected EventRelatedItemListComp associateActivityList;
	protected NoteListItems noteListItems;

	public CampaignPreviewBuilder() {
	}

	protected void initRelatedComponent() {
		associateAccountList = new CampaignAccountListComp();
		associateContactList = new CampaignContactListComp();
		associateLeadList = new CampaignLeadListComp();
		associateActivityList = new EventRelatedItemListComp(true);
		noteListItems = new NoteListItems("Notes");
	}

	public void previewItem(SimpleCampaign campaign) {
		this.campaign = campaign;
		previewForm
				.setItemDataSource(new BeanItem<CampaignWithBLOBs>(campaign));
		displayActivities();
		displayAccounts();
		displayContacts();
		displayLeads();
	}

	private void displayActivities() {
		EventSearchCriteria criteria = new EventSearchCriteria();
		criteria.setSaccountid(new NumberSearchField(AppContext.getAccountId()));
		criteria.setType(new StringSearchField(SearchField.AND,
				CrmTypeConstants.CAMPAIGN));
		criteria.setTypeid(new NumberSearchField(campaign.getId()));
		associateActivityList.setSearchCriteria(criteria);
	}

	private void displayAccounts() {
		associateAccountList.displayAccounts(campaign);
	}

	private void displayContacts() {
		associateContactList.displayContacts(campaign);
	}

	private void displayLeads() {
		associateLeadList.displayLeads(campaign);
	}

	public SimpleCampaign getCampaign() {
		return campaign;
	}

	public AdvancedPreviewBeanForm<CampaignWithBLOBs> getPreviewForm() {
		return previewForm;
	}

	public EventRelatedItemListComp getAssociateActivityList() {
		return associateActivityList;
	}

	public CampaignAccountListComp getAssociateAccountList() {
		return associateAccountList;
	}

	public CampaignContactListComp getAssociateContactList() {
		return associateContactList;
	}

	public CampaignLeadListComp getAssociateLeadList() {
		return associateLeadList;
	}

	protected class CampaignFormFieldFactory extends
			DefaultFormViewFieldFactory {

		private static final long serialVersionUID = 1L;

		@Override
		protected Field onCreateField(Item item, Object propertyId,
				Component uiContext) {
			if (propertyId.equals("assignuser")) {
				return new FormLinkViewField(campaign.getAssignUserFullName(),
						new Button.ClickListener() {
							@Override
							public void buttonClick(ClickEvent event) {
							}
						});
			} else if (propertyId.equals("startdate")) {
				return new FormViewField(AppContext.formatDate(campaign
						.getStartdate()));
			} else if (propertyId.equals("enddate")) {
				return new FormViewField(AppContext.formatDate(campaign
						.getEnddate()));
			} else if (propertyId.equals("currencyid")) {
				if (campaign.getCurrency() != null) {
					return new FormViewField(campaign.getCurrency().getName());
				} else {
					return new FormViewField("");
				}
			}

			return null;
		}
	}

	public static class ReadView extends CampaignPreviewBuilder {

		private static final long serialVersionUID = 1L;
		private VerticalLayout campaignInformationLayout;
		private VerticalLayout relatedItemsContainer;
		private ReadViewLayout campaignAddLayout;

		public ReadView() {
			campaignAddLayout = new ReadViewLayout(new ThemeResource(
					"icons/22/crm/campaign.png"));
			this.addComponent(campaignAddLayout);
			
			initRelatedComponent();

			previewForm = new AdvancedPreviewBeanForm<CampaignWithBLOBs>() {
				@Override
				public void setItemDataSource(Item newDataSource) {
					this.setFormLayoutFactory(new CampaignFormLayoutFactory.CampaignInformationLayout());
					this.setFormFieldFactory(new CampaignFormFieldFactory());
					super.setItemDataSource(newDataSource);
					campaignAddLayout.setTitle(campaign.getCampaignname());
				}

				@Override
				protected void doPrint() {
					// Create a window that contains what you want to print
					Window window = new Window("Window to Print");

					CampaignPreviewBuilder printView = new CampaignPreviewBuilder.PrintView();
					printView.previewItem(campaign);
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
					CampaignHistoryLogWindow historyLog = new CampaignHistoryLogWindow(
							ModuleNameConstants.CRM, CrmTypeConstants.CAMPAIGN,
							campaign.getId());
					getWindow().addWindow(historyLog);
				}
			};

			final Layout optionalActionControls = PreviewFormControlsGenerator2
					.createFormOptionalControls(previewForm,
							RolePermissionCollections.CRM_CAMPAIGN);
			campaignAddLayout.addControlButtons(optionalActionControls);

			campaignInformationLayout = new VerticalLayout();
			
			campaignInformationLayout.addStyleName("main-info");
			
			final Layout actionControls = PreviewFormControlsGenerator2
					.createFormControls(previewForm,
							RolePermissionCollections.CRM_CAMPAIGN);
			actionControls.addStyleName("control-buttons");
			campaignInformationLayout.addComponent(actionControls);
			
			campaignInformationLayout.addComponent(previewForm);
			campaignInformationLayout.addComponent(noteListItems);
			
			campaignAddLayout.addTab(campaignInformationLayout, "Contact Information");

			relatedItemsContainer = new VerticalLayout();
			relatedItemsContainer.setMargin(true);
			campaignAddLayout.addTab(relatedItemsContainer, "More Information");

			campaignAddLayout
			.addTabChangedListener(new DetachedTabs.TabChangedListener() {

				@Override
				public void tabChanged(final TabChangedEvent event) {
					final Button btn = event.getSource();
					final String caption = btn.getCaption();
					if ("Account Information".equals(caption)) {

					} else if ("More Information".equals(caption)) {
						relatedItemsContainer.addComponent(associateActivityList);
						relatedItemsContainer.addComponent(associateAccountList);
						relatedItemsContainer.addComponent(associateContactList);
						relatedItemsContainer.addComponent(associateLeadList);
					}
					campaignAddLayout.selectTab(caption);
				}
			});
		}
	}

	public static class PrintView extends CampaignPreviewBuilder {

		public PrintView() {
			previewForm = new AdvancedPreviewBeanForm<CampaignWithBLOBs>() {
				@Override
				public void setItemDataSource(Item newDataSource) {
					this.setFormLayoutFactory(new FormLayoutFactory());
					this.setFormFieldFactory(new CampaignFormFieldFactory());
					super.setItemDataSource(newDataSource);
				}
			};
			initRelatedComponent();

			this.addComponent(previewForm);
		}

		class FormLayoutFactory extends CampaignFormLayoutFactory {

			private static final long serialVersionUID = 1L;

			public FormLayoutFactory() {
				super(campaign.getCampaignname());
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
				relatedItemsPanel.addComponent(associateAccountList);
				relatedItemsPanel.addComponent(associateContactList);
				relatedItemsPanel.addComponent(associateLeadList);

				return relatedItemsPanel;
			}
		}
	}
}
