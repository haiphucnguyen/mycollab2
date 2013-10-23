package com.esofthead.mycollab.module.crm.view.cases;

import com.esofthead.mycollab.common.ModuleNameConstants;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.eventmanager.EventBus;
import com.esofthead.mycollab.form.view.DynaFormLayout;
import com.esofthead.mycollab.module.crm.CrmTypeConstants;
import com.esofthead.mycollab.module.crm.domain.CaseWithBLOBs;
import com.esofthead.mycollab.module.crm.domain.SimpleCase;
import com.esofthead.mycollab.module.crm.domain.criteria.EventSearchCriteria;
import com.esofthead.mycollab.module.crm.events.AccountEvent;
import com.esofthead.mycollab.module.crm.ui.components.NoteListItems;
import com.esofthead.mycollab.module.crm.view.account.AccountFormLayoutFactory;
import com.esofthead.mycollab.module.crm.view.account.AccountHistoryLogWindow;
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
public class CasePreviewBuilder extends VerticalLayout {

	protected SimpleCase cases;
	protected AdvancedPreviewBeanForm<CaseWithBLOBs> previewForm;
	protected CaseContactListComp associateContactList;
	protected NoteListItems noteListItems;
	protected EventRelatedItemListComp associateActivityList;

	protected void initRelatedComponent() {
		associateContactList = new CaseContactListComp();
		associateActivityList = new EventRelatedItemListComp(true);
		noteListItems = new NoteListItems("Notes");
	}

	public void previewItem(SimpleCase item) {
		cases = item;
		previewForm.setItemDataSource(new BeanItem<SimpleCase>(cases));
		displayNotes();
	}

	protected void displayNotes() {
		noteListItems.showNotes(CrmTypeConstants.CASE, cases.getId());
	}

	protected void displayActivities() {
		EventSearchCriteria criteria = new EventSearchCriteria();
		criteria.setSaccountid(new NumberSearchField(AppContext.getAccountId()));
		criteria.setType(new StringSearchField(SearchField.AND,
				CrmTypeConstants.CASE));
		criteria.setTypeid(new NumberSearchField(cases.getId()));
		associateActivityList.setSearchCriteria(criteria);
	}

	protected void displayContacts() {
		associateContactList.displayContacts(cases);
	}

	public EventRelatedItemListComp getAssociateActivityList() {
		return associateActivityList;
	}

	public CaseContactListComp getAssociateContactList() {
		return associateContactList;
	}

	public SimpleCase getCase() {
		return cases;
	}

	public AdvancedPreviewBeanForm<CaseWithBLOBs> getPreviewForm() {
		return previewForm;
	}

	protected class CaseFormFieldFactory extends DefaultFormViewFieldFactory {

		@Override
		protected Field onCreateField(Item item, Object propertyId,
				Component uiContext) {
			if (propertyId.equals("accountid")) {
				return new FormLinkViewField(cases.getAccountName(),
						new Button.ClickListener() {
							private static final long serialVersionUID = 1L;

							@Override
							public void buttonClick(ClickEvent event) {
								EventBus.getInstance().fireEvent(
										new AccountEvent.GotoRead(this, cases
												.getAccountid()));

							}
						});
			} else if (propertyId.equals("email")) {
				return new FormEmailLinkViewField(cases.getEmail());
			} else if (propertyId.equals("assignuser")) {
				return new FormLinkViewField(cases.getAssignUserFullName(),
						new Button.ClickListener() {
							private static final long serialVersionUID = 1L;

							@Override
							public void buttonClick(ClickEvent event) {
								// TODO Auto-generated method stub
							}
						});
			}

			return null;
		}
	}

	public static class ReadView extends CasePreviewBuilder {

		private VerticalLayout caseInformationLayout;
		private VerticalLayout relatedItemsContainer;
		private ReadViewLayout caseAddLayout;

		private boolean isLoadedRelateItem = false;

		public ReadView() {
			caseAddLayout = new ReadViewLayout(
					MyCollabResource.newResource("icons/22/crm/case.png"));
			this.addComponent(caseAddLayout);
			initRelatedComponent();

			previewForm = new AdvancedPreviewBeanForm<CaseWithBLOBs>() {
				@Override
				public void setItemDataSource(Item newDataSource) {
					this.setFormLayoutFactory(new DynaFormLayout(
							CasesDefaultFormLayoutFactory.getForm()));
					this.setFormFieldFactory(new CaseFormFieldFactory());
					super.setItemDataSource(newDataSource);
					caseAddLayout.setTitle(cases.getSubject());
				}

				@Override
				protected void doPrint() {
					// Create a window that contains what you want to print
					Window window = new Window("Window to Print");

					CasePreviewBuilder printView = new CasePreviewBuilder.PrintView();
					printView.previewItem(cases);
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
					AccountHistoryLogWindow historyLog = new AccountHistoryLogWindow(
							ModuleNameConstants.CRM, CrmTypeConstants.CASE,
							cases.getId());
					getWindow().addWindow(historyLog);
				}
			};

			final Layout optionalActionControls = PreviewFormControlsGenerator2
					.createFormOptionalControls(previewForm,
							RolePermissionCollections.CRM_CASE);

			caseAddLayout.addControlButtons(optionalActionControls);

			caseInformationLayout = new VerticalLayout();
			caseInformationLayout.addStyleName("main-info");

			final Layout actionControls = PreviewFormControlsGenerator2
					.createFormControls(previewForm,
							RolePermissionCollections.CRM_CASE);
			actionControls.addStyleName("control-buttons");
			caseInformationLayout.addComponent(actionControls);

			caseInformationLayout.addComponent(previewForm);
			caseInformationLayout.addComponent(noteListItems);

			caseAddLayout.addTab(caseInformationLayout, "Case Information");

			relatedItemsContainer = new VerticalLayout();
			relatedItemsContainer.setMargin(true);
			relatedItemsContainer.addComponent(associateActivityList);
			relatedItemsContainer.addComponent(associateContactList);

			caseAddLayout.addTab(relatedItemsContainer, "More Information");

			this.addComponent(caseAddLayout);

			caseAddLayout
					.addTabChangedListener(new DetachedTabs.TabChangedListener() {

						@Override
						public void tabChanged(final TabChangedEvent event) {
							final Button btn = event.getSource();
							final String caption = btn.getCaption();
							if ("More Information".equals(caption)) {
								if (!isLoadedRelateItem) {
									displayActivities();
									displayContacts();
									isLoadedRelateItem = true;
								}
							}
						}
					});
		}

		@Override
		public void previewItem(SimpleCase item) {
			super.previewItem(item);
			isLoadedRelateItem = false;
			caseAddLayout.selectTab("Case Information");
		}

	}

	/**
     *
     */
	public static class PrintView extends CasePreviewBuilder {

		public PrintView() {
			previewForm = new AdvancedPreviewBeanForm<CaseWithBLOBs>() {
				@Override
				public void setItemDataSource(Item newDataSource) {
					this.setFormLayoutFactory(new FormLayoutFactory());
					this.setFormFieldFactory(new CaseFormFieldFactory());
					super.setItemDataSource(newDataSource);
				}
			};
			initRelatedComponent();

			this.addComponent(previewForm);
		}

		@Override
		public void previewItem(SimpleCase item) {
			super.previewItem(item);
			displayActivities();
			displayContacts();
		}

		class FormLayoutFactory extends AccountFormLayoutFactory {

			private static final long serialVersionUID = 1L;

			public FormLayoutFactory() {
				super(cases.getSubject());
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

				return relatedItemsPanel;
			}
		}
	}
}
