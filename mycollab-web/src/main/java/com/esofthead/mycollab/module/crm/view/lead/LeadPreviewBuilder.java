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
					LeadHistoryLogWindow historyLog = new LeadHistoryLogWindow(
							ModuleNameConstants.CRM, CrmTypeConstants.LEAD,
							lead.getId());
					getWindow().addWindow(historyLog);
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
					.addTabChangedListener(new DetachedTabs.TabChangedListener() {
						@Override
						public void tabChanged(final TabChangedEvent event) {
							final Button btn = event.getSource();
							final String caption = btn.getCaption();
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
