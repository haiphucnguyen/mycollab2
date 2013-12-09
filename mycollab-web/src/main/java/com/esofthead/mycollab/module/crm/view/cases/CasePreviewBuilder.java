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
				return new UserLinkViewField(
						CasePreviewBuilder.this.cases.getAssignuser(),
						CasePreviewBuilder.this.cases.getAssignUserAvatarId(),
						CasePreviewBuilder.this.cases.getAssignUserFullName());
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
							CrmTypeConstants.CASE,
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
					window.setContent(printView);

					UI.getCurrent().addWindow(window);

					// Print automatically when the window opens
					JavaScript.getCurrent().execute(
							"setTimeout(function() {"
									+ "  print(); self.close();}, 0);");
				}

				@Override
				protected void showHistory() {
					AccountHistoryLogWindow historyLog = new AccountHistoryLogWindow(
							ModuleNameConstants.CRM, CrmTypeConstants.CASE,
							cases.getId());
					UI.getCurrent().addWindow(historyLog);
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
