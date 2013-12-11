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
package com.esofthead.mycollab.module.crm.view.opportunity;

import com.esofthead.mycollab.common.ModuleNameConstants;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.eventmanager.EventBus;
import com.esofthead.mycollab.form.view.DynaFormLayout;
import com.esofthead.mycollab.module.crm.CrmTypeConstants;
import com.esofthead.mycollab.module.crm.domain.Opportunity;
import com.esofthead.mycollab.module.crm.domain.SimpleOpportunity;
import com.esofthead.mycollab.module.crm.domain.criteria.EventSearchCriteria;
import com.esofthead.mycollab.module.crm.events.AccountEvent;
import com.esofthead.mycollab.module.crm.events.CampaignEvent;
import com.esofthead.mycollab.module.crm.ui.components.CrmPreviewFormControlsGenerator;
import com.esofthead.mycollab.module.crm.ui.components.NoteListItems;
import com.esofthead.mycollab.module.crm.view.activity.EventRelatedItemListComp;
import com.esofthead.mycollab.security.RolePermissionCollections;
import com.esofthead.mycollab.vaadin.ui.AdvancedPreviewBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultFormViewFieldFactory;
import com.esofthead.mycollab.vaadin.ui.ReadViewLayout;
import com.esofthead.mycollab.web.AppContext;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.JavaScript;
import com.vaadin.ui.Layout;
import com.vaadin.ui.TabSheet.SelectedTabChangeEvent;
import com.vaadin.ui.TabSheet.SelectedTabChangeListener;
import com.vaadin.ui.TabSheet.Tab;
import com.vaadin.ui.UI;
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
		displayNotes();
	}

	protected void displayNotes() {
		noteListItems.showNotes(CrmTypeConstants.OPPORTUNITY,
				opportunity.getId());
	}

	public SimpleOpportunity getOpportunity() {
		return opportunity;
	}

	protected void displayActivities() {
		EventSearchCriteria criteria = new EventSearchCriteria();
		criteria.setSaccountid(new NumberSearchField(AppContext.getAccountId()));
		criteria.setType(new StringSearchField(SearchField.AND,
				CrmTypeConstants.OPPORTUNITY));
		criteria.setTypeid(new NumberSearchField(opportunity.getId()));
		associateActivityList.setSearchCriteria(criteria);
	}

	protected void displayContacts() {
		associateContactList.displayContacts(opportunity);
	}

	protected void displayLeads() {
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

		private boolean isLoadedRelateItem = false;

		public ReadView() {
			opportunityAddLayout = new ReadViewLayout(
					MyCollabResource
							.newResource("icons/22/crm/opportunity.png"));
			this.addComponent(opportunityAddLayout);

			initRelatedComponent();

			previewForm = new AdvancedPreviewBeanForm<Opportunity>() {
				@Override
				public void setItemDataSource(Item newDataSource) {
					this.setFormLayoutFactory(new DynaFormLayout(
							CrmTypeConstants.OPPORTUNITY,
							OpportunityDefaultDynaFormLayoutFactory.getForm()));
					this.setFormFieldFactory(new OpportunityFormFieldFactory());
					super.setItemDataSource(newDataSource);
					opportunityAddLayout.setTitle(opportunity
							.getOpportunityname());
				}

				@Override
				public void doPrint() {
					// Create a window that contains what you want to print
					Window window = new Window("Window to Print");

					OpportunityPreviewBuilder printView = new OpportunityPreviewBuilder.PrintView();
					printView.previewItem(opportunity);
					window.setContent(printView);

					UI.getCurrent().addWindow(window);

					// Print automatically when the window opens
					JavaScript.getCurrent().execute(
							"setTimeout(function() {"
									+ "  print(); self.close();}, 0);");
				}

				@Override
				public void showHistory() {
					OpportunityHistoryLogWindow historyLog = new OpportunityHistoryLogWindow(
							ModuleNameConstants.CRM,
							CrmTypeConstants.OPPORTUNITY, opportunity.getId());
					UI.getCurrent().addWindow(historyLog);
				}
			};

			final Layout optionalActionControls = CrmPreviewFormControlsGenerator
					.createFormOptionalControls(previewForm,
							RolePermissionCollections.CRM_OPPORTUNITY);

			opportunityAddLayout.addControlButtons(optionalActionControls);

			opportunityInformationLayout = new VerticalLayout();

			opportunityInformationLayout.addStyleName("main-info");

			final Layout actionControls = CrmPreviewFormControlsGenerator
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
			relatedItemsContainer.addComponent(associateActivityList);
			relatedItemsContainer.addComponent(associateContactList);
			relatedItemsContainer.addComponent(associateLeadList);

			opportunityAddLayout.addTab(relatedItemsContainer,
					"More Information");
			opportunityAddLayout
					.addSelectedTabChangeListener(new SelectedTabChangeListener() {

						@Override
						public void selectedTabChange(
								SelectedTabChangeEvent event) {
							final Tab tab = (Tab) event.getTabSheet()
									.getSelectedTab();
							final String caption = tab.getCaption();

							if ("More Information".equals(caption)) {
								displayActivities();
								displayContacts();
								displayLeads();
								isLoadedRelateItem = true;
							}

						}
					});
		}

		@Override
		public void previewItem(SimpleOpportunity item) {
			super.previewItem(item);
			isLoadedRelateItem = false;
			opportunityAddLayout.selectTab("Opportunity Information");
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

		@Override
		public void previewItem(SimpleOpportunity item) {
			super.previewItem(item);
			displayActivities();
			displayContacts();
			displayLeads();
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
				field = new UserLinkViewField(
						OpportunityPreviewBuilder.this.opportunity
								.getAssignuser(),
						OpportunityPreviewBuilder.this.opportunity
								.getAssignUserAvatarId(),
						OpportunityPreviewBuilder.this.opportunity
								.getAssignUserFullName());
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
}
