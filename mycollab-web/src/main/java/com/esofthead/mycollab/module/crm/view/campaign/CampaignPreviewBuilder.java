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
package com.esofthead.mycollab.module.crm.view.campaign;

import com.esofthead.mycollab.common.ModuleNameConstants;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.form.view.DynaFormLayout;
import com.esofthead.mycollab.module.crm.CrmTypeConstants;
import com.esofthead.mycollab.module.crm.domain.CampaignWithBLOBs;
import com.esofthead.mycollab.module.crm.domain.SimpleCampaign;
import com.esofthead.mycollab.module.crm.domain.criteria.EventSearchCriteria;
import com.esofthead.mycollab.module.crm.ui.components.NoteListItems;
import com.esofthead.mycollab.module.crm.view.activity.EventRelatedItemListComp;
import com.esofthead.mycollab.security.RolePermissionCollections;
import com.esofthead.mycollab.vaadin.ui.AdvancedPreviewBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultFormViewFieldFactory;
import com.esofthead.mycollab.vaadin.ui.PreviewFormControlsGenerator2;
import com.esofthead.mycollab.vaadin.ui.ReadViewLayout;
import com.esofthead.mycollab.web.AppContext;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.JavaScript;
import com.vaadin.ui.Layout;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TabSheet.SelectedTabChangeEvent;
import com.vaadin.ui.TabSheet.Tab;
import com.vaadin.ui.UI;
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
		displayNotes();
	}

	protected void displayNotes() {
		noteListItems.showNotes(CrmTypeConstants.CAMPAIGN, campaign.getId());
	}

	protected void displayActivities() {
		EventSearchCriteria criteria = new EventSearchCriteria();
		criteria.setSaccountid(new NumberSearchField(AppContext.getAccountId()));
		criteria.setType(new StringSearchField(SearchField.AND,
				CrmTypeConstants.CAMPAIGN));
		criteria.setTypeid(new NumberSearchField(campaign.getId()));
		associateActivityList.setSearchCriteria(criteria);
	}

	protected void displayAccounts() {
		associateAccountList.displayAccounts(campaign);
	}

	protected void displayContacts() {
		associateContactList.displayContacts(campaign);
	}

	protected void displayLeads() {
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
				return new UserLinkViewField(
						CampaignPreviewBuilder.this.campaign.getAssignuser(),
						CampaignPreviewBuilder.this.campaign
								.getAssignUserAvatarId(),
						CampaignPreviewBuilder.this.campaign
								.getAssignUserFullName());
			} else if (propertyId.equals("startdate")) {
				return new FormViewField(AppContext.formatDate(campaign
						.getStartdate()));
			} else if (propertyId.equals("enddate")) {
				return new FormViewField(AppContext.formatDate(campaign
						.getEnddate()));
			} else if (propertyId.equals("currencyid")) {
				if (campaign.getCurrency() != null) {
					return new FormViewField(campaign.getCurrency()
							.getShortname());
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

		private boolean isLoadedRelateItem = false;

		public ReadView() {
			campaignAddLayout = new ReadViewLayout(
					MyCollabResource.newResource("icons/22/crm/campaign.png"));
			this.addComponent(campaignAddLayout);

			initRelatedComponent();

			previewForm = new AdvancedPreviewBeanForm<CampaignWithBLOBs>() {
				@Override
				public void setItemDataSource(Item newDataSource) {
					this.setFormLayoutFactory(new DynaFormLayout(
							CrmTypeConstants.CAMPAIGN,
							CampaignDefaultDynaFormLayoutFactory.getForm()));
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
					window.setContent(printView);

					UI.getCurrent().addWindow(window);

					// Print automatically when the window opens
					JavaScript.getCurrent().execute(
							"setTimeout(function() {"
									+ "  print(); self.close();}, 0);");
				}

				@Override
				protected void showHistory() {
					CampaignHistoryLogWindow historyLog = new CampaignHistoryLogWindow(
							ModuleNameConstants.CRM, CrmTypeConstants.CAMPAIGN,
							campaign.getId());
					UI.getCurrent().addWindow(historyLog);
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

			campaignAddLayout.addTab(campaignInformationLayout,
					"Campaign Information");

			relatedItemsContainer = new VerticalLayout();
			relatedItemsContainer.setMargin(true);
			relatedItemsContainer.addComponent(associateActivityList);
			relatedItemsContainer.addComponent(associateAccountList);
			relatedItemsContainer.addComponent(associateContactList);
			relatedItemsContainer.addComponent(associateLeadList);
			campaignAddLayout.addTab(relatedItemsContainer, "More Information");

			campaignAddLayout
					.addSelectedTabChangeListener(new TabSheet.SelectedTabChangeListener() {

						@Override
						public void selectedTabChange(
								SelectedTabChangeEvent event) {
							final Tab tab = (Tab) event.getTabSheet()
									.getSelectedTab();
							final String caption = tab.getCaption();
							if ("More Information".equals(caption)) {
								if (!isLoadedRelateItem) {
									displayActivities();
									displayAccounts();
									displayContacts();
									displayLeads();
									isLoadedRelateItem = true;
								}
							}

						}
					});
		}

		@Override
		public void previewItem(SimpleCampaign campaign) {
			super.previewItem(campaign);
			isLoadedRelateItem = false;
			campaignAddLayout.selectTab("Campaign Information");
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

		@Override
		public void previewItem(SimpleCampaign campaign) {
			super.previewItem(campaign);
			displayActivities();
			displayAccounts();
			displayContacts();
			displayLeads();
		}
	}
}
