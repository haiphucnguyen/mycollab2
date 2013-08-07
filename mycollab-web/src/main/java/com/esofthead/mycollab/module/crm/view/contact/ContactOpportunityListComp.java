/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.crm.view.contact;

import java.util.Arrays;
import java.util.Set;

import org.vaadin.dialogs.ConfirmDialog;
import org.vaadin.hene.splitbutton.SplitButton;

import com.esofthead.mycollab.common.ApplicationProperties;
import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.module.crm.domain.ContactOpportunity;
import com.esofthead.mycollab.module.crm.domain.SimpleContact;
import com.esofthead.mycollab.module.crm.domain.SimpleOpportunity;
import com.esofthead.mycollab.module.crm.domain.criteria.OpportunitySearchCriteria;
import com.esofthead.mycollab.module.crm.events.OpportunityEvent;
import com.esofthead.mycollab.module.crm.localization.CrmCommonI18nEnum;
import com.esofthead.mycollab.module.crm.service.ContactService;
import com.esofthead.mycollab.module.crm.ui.components.RelatedListComp;
import com.esofthead.mycollab.module.crm.view.opportunity.OpportunityTableDisplay;
import com.esofthead.mycollab.module.crm.view.opportunity.OpportunityTableFieldDef;
import com.esofthead.mycollab.module.user.RolePermissionCollections;
import com.esofthead.mycollab.vaadin.events.ApplicationEvent;
import com.esofthead.mycollab.vaadin.events.ApplicationEventListener;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.ui.ConfirmDialogExt;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.table.TableClickEvent;
import com.esofthead.mycollab.web.AppContext;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * @author haiphucnguyen
 */
public class ContactOpportunityListComp extends
		RelatedListComp<SimpleOpportunity, OpportunitySearchCriteria> {

	private static final long serialVersionUID = 1L;

	private SimpleContact contact;

	public ContactOpportunityListComp() {
		super("Opportunities");
		initUI();
	}

	public void displayOpportunities(SimpleContact contact) {
		this.contact = contact;
		loadOpportunities();
	}

	private void loadOpportunities() {
		OpportunitySearchCriteria criteria = new OpportunitySearchCriteria();
		criteria.setSaccountid(new NumberSearchField(SearchField.AND,
				AppContext.getAccountId()));
		criteria.setContactId(new NumberSearchField(SearchField.AND, contact
				.getId()));
		this.setSearchCriteria(criteria);
	}

	private void initUI() {
		VerticalLayout contentContainer = (VerticalLayout) bodyContent;
		contentContainer.setSpacing(true);

		final SplitButton controlsBtn = new SplitButton();
		controlsBtn.addStyleName(UIConstants.THEME_BLUE_LINK);
		controlsBtn.setCaption("New Opportunity");
		controlsBtn.setIcon(MyCollabResource
				.newResource("icons/16/addRecord.png"));
		controlsBtn
				.addClickListener(new SplitButton.SplitButtonClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void splitButtonClick(
							SplitButton.SplitButtonClickEvent event) {
						fireNewRelatedItem("");
					}
				});
		Button selectBtn = new Button("Select from existing opportunities",
				new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(Button.ClickEvent event) {
						ContactOpportunitySelectionWindow opportunitiesWindow = new ContactOpportunitySelectionWindow(
								ContactOpportunityListComp.this);
						OpportunitySearchCriteria criteria = new OpportunitySearchCriteria();
						criteria.setSaccountid(new NumberSearchField(AppContext
								.getAccountId()));
						getWindow().addWindow(opportunitiesWindow);
						opportunitiesWindow.setSearchCriteria(criteria);
						controlsBtn.setPopupVisible(false);
					}
				});
		selectBtn.setIcon(MyCollabResource.newResource("icons/16/select.png"));
		selectBtn.setStyleName("link");
		controlsBtn.addComponent(selectBtn);
		controlsBtn.setEnabled(AppContext
				.canWrite(RolePermissionCollections.CRM_OPPORTUNITY));
		addHeaderElement(controlsBtn);

		tableItem = new OpportunityTableDisplay(Arrays.asList(
				OpportunityTableFieldDef.opportunityName,
				OpportunityTableFieldDef.saleStage,
				OpportunityTableFieldDef.amount,
				OpportunityTableFieldDef.expectedCloseDate,
				OpportunityTableFieldDef.assignUser,
				OpportunityTableFieldDef.action));

		tableItem
				.addTableListener(new ApplicationEventListener<TableClickEvent>() {
					private static final long serialVersionUID = 1L;

					@Override
					public Class<? extends ApplicationEvent> getEventType() {
						return TableClickEvent.class;
					}

					@Override
					public void handle(TableClickEvent event) {
						SimpleOpportunity opportunity = (SimpleOpportunity) event
								.getData();
						if ("opportunityname".equals(event.getFieldName())) {
							EventBus.getInstance().fireEvent(
									new OpportunityEvent.GotoRead(this,
											opportunity.getId()));
						}
					}
				});

		tableItem.addGeneratedColumn("id", new Table.ColumnGenerator() {
			private static final long serialVersionUID = 1L;

			@Override
			public Object generateCell(Table source, Object itemId,
					Object columnId) {
				final SimpleOpportunity opportunity = (SimpleOpportunity) tableItem
						.getBeanByIndex(itemId);
				HorizontalLayout controlLayout = new HorizontalLayout();
				Button editBtn = new Button(null, new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(Button.ClickEvent event) {
						EventBus.getInstance().fireEvent(
								new OpportunityEvent.GotoRead(
										ContactOpportunityListComp.this,
										opportunity.getId()));
					}
				});
				editBtn.setStyleName("link");
				editBtn.setIcon(MyCollabResource
						.newResource("icons/16/edit.png"));
				controlLayout.addComponent(editBtn);

				Button deleteBtn = new Button(null, new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(Button.ClickEvent event) {
						ConfirmDialogExt.show(
								AppContext.getApplication().getMainWindow(),
								LocalizationHelper
										.getMessage(
												GenericI18Enum.DELETE_DIALOG_TITLE,
												ApplicationProperties
														.getString(ApplicationProperties.SITE_NAME)),
								LocalizationHelper
										.getMessage(CrmCommonI18nEnum.DIALOG_DELETE_RELATIONSHIP_TITLE),
								LocalizationHelper
										.getMessage(GenericI18Enum.BUTTON_YES_LABEL),
								LocalizationHelper
										.getMessage(GenericI18Enum.BUTTON_NO_LABEL),
								new ConfirmDialog.Listener() {
									private static final long serialVersionUID = 1L;

									@Override
									public void onClose(ConfirmDialog dialog) {
										if (dialog.isConfirmed()) {
											ContactService contactService = AppContext
													.getSpringBean(ContactService.class);
											ContactOpportunity associateOpportunity = new ContactOpportunity();
											associateOpportunity
													.setContactid(contact
															.getId());
											associateOpportunity
													.setOpportunityid(opportunity
															.getId());
											contactService
													.removeContactOpportunityRelationship(associateOpportunity);
											ContactOpportunityListComp.this
													.refresh();
										}
									}
								});
					}
				});
				deleteBtn.setStyleName("link");
				deleteBtn.setIcon(MyCollabResource
						.newResource("icons/16/delete.png"));
				controlLayout.addComponent(deleteBtn);
				return controlLayout;
			}
		});

		contentContainer.addComponent(tableItem);
	}

	@Override
	public void setSelectedItems(Set selectedItems) {
		fireSelectedRelatedItems(selectedItems);
	}

	@Override
	public void refresh() {
		loadOpportunities();
	}
}
