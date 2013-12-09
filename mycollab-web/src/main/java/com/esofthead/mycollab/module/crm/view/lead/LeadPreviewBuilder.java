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
package com.esofthead.mycollab.module.crm.view.lead;

import com.esofthead.mycollab.common.ModuleNameConstants;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.form.view.DynaFormLayout;
import com.esofthead.mycollab.module.crm.CrmTypeConstants;
import com.esofthead.mycollab.module.crm.domain.Lead;
import com.esofthead.mycollab.module.crm.domain.SimpleLead;
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
public class LeadPreviewBuilder extends VerticalLayout {

	protected AdvancedPreviewBeanForm<Lead> previewForm;
	protected SimpleLead lead;
	protected LeadCampaignListComp associateCampaignList;
	protected EventRelatedItemListComp associateActivityList;
	protected NoteListItems noteListItems;

	protected void initRelatedComponent() {
		associateCampaignList = new LeadCampaignListComp();
		noteListItems = new NoteListItems("Notes");
		associateActivityList = new EventRelatedItemListComp(true);
	}

	public void previewItem(SimpleLead lead) {
		this.lead = lead;
		previewForm.setItemDataSource(new BeanItem<Lead>(lead));
		displayNotes();
	}

	public SimpleLead getLead() {
		return lead;
	}

	protected void displayCampaigns() {
		associateCampaignList.displayCampaigns(lead);
	}

	private void displayNotes() {
		noteListItems.showNotes(CrmTypeConstants.LEAD, lead.getId());
	}

	protected void displayActivities() {
		EventSearchCriteria criteria = new EventSearchCriteria();
		criteria.setSaccountid(new NumberSearchField(AppContext.getAccountId()));
		criteria.setType(new StringSearchField(SearchField.AND,
				CrmTypeConstants.LEAD));
		criteria.setTypeid(new NumberSearchField(lead.getId()));
		associateActivityList.setSearchCriteria(criteria);
	}

	public EventRelatedItemListComp getAssociateActivityList() {
		return associateActivityList;
	}

	public LeadCampaignListComp getAssociateCampaignList() {
		return associateCampaignList;
	}

	public AdvancedPreviewBeanForm<Lead> getPreviewForm() {
		return previewForm;
	}

	protected class LeadFormFieldFactory extends DefaultFormViewFieldFactory {

		@Override
		protected Field onCreateField(Item item, Object propertyId,
				Component uiContext) {
			if (propertyId.equals("firstname")) {
				if (lead.getPrefixname() == null) {
					return new FormViewField(lead.getFirstname());
				} else {
					return new FormViewField(lead.getPrefixname() + " "
							+ lead.getFirstname());
				}
			} else if (propertyId.equals("website")) {
				return new DefaultFormViewFieldFactory.FormUrlLinkViewField(
						lead.getWebsite());
			} else if (propertyId.equals("email")) {
				return new FormEmailLinkViewField(lead.getEmail());
			} else if (propertyId.equals("accountid")) {
				FormLinkViewField field = new FormLinkViewField(
						lead.getAccountname(), new Button.ClickListener() {
							private static final long serialVersionUID = 1L;

							@Override
							public void buttonClick(ClickEvent event) {
							}
						});

				return field;
			} else if (propertyId.equals("assignuser")) {
				return new UserLinkViewField(
						LeadPreviewBuilder.this.lead.getAssignuser(),
						LeadPreviewBuilder.this.lead.getAssignUserAvatarId(),
						LeadPreviewBuilder.this.lead.getAssignUserFullName());
			}

			return super.onCreateField(item, propertyId, uiContext);
		}
	}

	public static class ReadView extends LeadPreviewBuilder {

		private static final long serialVersionUID = 1L;
		private VerticalLayout leadInformationLayout;
		private VerticalLayout relatedItemsContainer;
		private ReadViewLayout leadAddLayout;

		private boolean isLoadedRelateItem = false;

		public ReadView() {
			leadAddLayout = new ReadViewLayout(
					MyCollabResource.newResource("icons/22/crm/lead.png"));
			this.addComponent(leadAddLayout);

			initRelatedComponent();

			previewForm = new AdvancedPreviewBeanForm<Lead>() {
				@Override
				public void setItemDataSource(Item newDataSource) {
					this.setFormLayoutFactory(new DynaFormLayout(
							CrmTypeConstants.LEAD,
							LeadDefaultDynaFormLayoutFactory.getForm()));
					this.setFormFieldFactory(new LeadFormFieldFactory());
					super.setItemDataSource(newDataSource);
					leadAddLayout.setTitle(lead.getLeadName());
				}

				@Override
				protected void doPrint() {
					// Create a window that contains what you want to print
					Window window = new Window("Window to Print");

					LeadPreviewBuilder printView = new LeadPreviewBuilder.PrintView();
					printView.previewItem(lead);
					window.setContent(printView);

					UI.getCurrent().addWindow(window);

					// Print automatically when the window opens
					JavaScript.getCurrent().execute(
							"setTimeout(function() {"
									+ "  print(); self.close();}, 0);");
				}

				@Override
				protected void showHistory() {
					LeadHistoryLogWindow historyLog = new LeadHistoryLogWindow(
							ModuleNameConstants.CRM, CrmTypeConstants.LEAD,
							lead.getId());
					UI.getCurrent().addWindow(historyLog);
				}
			};

			final Layout optionalActionControls = PreviewFormControlsGenerator2
					.createFormOptionalControls(previewForm,
							RolePermissionCollections.CRM_LEAD);

			leadAddLayout.addControlButtons(optionalActionControls);

			leadInformationLayout = new VerticalLayout();

			leadInformationLayout.addStyleName("main-info");

			final Layout actionControls = PreviewFormControlsGenerator2
					.createFormControls(previewForm,
							RolePermissionCollections.CRM_LEAD);
			actionControls.addStyleName("control-buttons");
			leadInformationLayout.addComponent(actionControls);

			leadInformationLayout.addComponent(previewForm);
			leadInformationLayout.addComponent(noteListItems);

			leadAddLayout.addTab(leadInformationLayout, "Lead Information");

			relatedItemsContainer = new VerticalLayout();
			relatedItemsContainer.setMargin(true);
			relatedItemsContainer.addComponent(associateActivityList);
			relatedItemsContainer.addComponent(associateCampaignList);
			leadAddLayout.addTab(relatedItemsContainer, "More Information");
			leadAddLayout
					.addSelectedTabChangeListener(new SelectedTabChangeListener() {

						@Override
						public void selectedTabChange(
								SelectedTabChangeEvent event) {
							final Tab tab = (Tab) event.getTabSheet()
									.getSelectedTab();
							final String caption = tab.getCaption();
							if ("More Information".equals(caption)) {
								if (!isLoadedRelateItem) {
									displayActivities();
									displayCampaigns();
									isLoadedRelateItem = true;
								}
							}
						}
					});

		}

		@Override
		public void previewItem(SimpleLead lead) {
			super.previewItem(lead);
			isLoadedRelateItem = false;
			leadAddLayout.selectTab("Lead Information");
		}

	}

	public static class PrintView extends LeadPreviewBuilder {

		public PrintView() {
			previewForm = new AdvancedPreviewBeanForm<Lead>() {
				@Override
				public void setItemDataSource(Item newDataSource) {
					this.setFormLayoutFactory(new FormLayoutFactory());
					this.setFormFieldFactory(new LeadFormFieldFactory());
					super.setItemDataSource(newDataSource);
				}
			};
			initRelatedComponent();

			this.addComponent(previewForm);
		}

		class FormLayoutFactory extends LeadFormLayoutFactory {

			private static final long serialVersionUID = 1L;

			public FormLayoutFactory() {
				super(lead.getLeadName());
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
				relatedItemsPanel.addComponent(associateCampaignList);

				return relatedItemsPanel;
			}
		}

		@Override
		public void previewItem(SimpleLead lead) {
			super.previewItem(lead);
			displayActivities();
			displayCampaigns();
		}

	}
}
