package com.esofthead.mycollab.module.crm.view.opportunity;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.module.crm.domain.criteria.OpportunitySearchCriteria;
import com.esofthead.mycollab.module.crm.events.OpportunityEvent;
import com.esofthead.mycollab.module.crm.ui.components.AdvancedSearchLayout;
import com.esofthead.mycollab.module.crm.ui.components.BasicSearchLayout;
import com.esofthead.mycollab.module.crm.ui.components.GenericSearchPanel;
import com.esofthead.mycollab.module.crm.ui.components.SalesStageListSelect;
import com.esofthead.mycollab.module.crm.view.account.AccountSelectionField;
import com.esofthead.mycollab.module.crm.view.lead.LeadSourceListSelect;
import com.esofthead.mycollab.module.user.ui.components.UserListSelect;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.UiUtils;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.BaseTheme;
import com.vaadin.ui.themes.Reindeer;

@SuppressWarnings("serial")
public class OpportunitySearchPanel extends
		GenericSearchPanel<OpportunitySearchCriteria> {
	protected OpportunitySearchCriteria searchCriteria;

	public OpportunitySearchPanel() {
		searchCriteria = new OpportunitySearchCriteria();
	}

	@Override
	public void attach() {
		createBasicSearchLayout();
	}

	private void createBasicSearchLayout() {
		OpportunityBasicSearchLayout layout = new OpportunityBasicSearchLayout();
		this.setCompositionRoot(layout);
	}

	private void createAdvancedSearchLayout() {
		OpportunityAdvancedSearchLayout layout = new OpportunityAdvancedSearchLayout();
		this.setCompositionRoot(layout);
	}

	private HorizontalLayout createSearchTopPanel() {
		HorizontalLayout layout = new HorizontalLayout();
		layout.setWidth("100%");
		layout.setSpacing(true);

		Label searchtitle = new Label("Search");
		searchtitle.setStyleName(Reindeer.LABEL_H2);
		layout.addComponent(searchtitle);

		Button createAccountBtn = new Button("Create",
				new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(ClickEvent event) {
						EventBus.getInstance().fireEvent(
								new OpportunityEvent.GotoAdd(
										OpportunitySearchPanel.this, null));
					}
				});
		createAccountBtn.setIcon(new ThemeResource("icons/16/addRecord.png"));
		createAccountBtn.setStyleName(BaseTheme.BUTTON_LINK);

		UiUtils.addComponent(layout, createAccountBtn, Alignment.MIDDLE_RIGHT);

		return layout;
	}

	private class OpportunityBasicSearchLayout extends BasicSearchLayout {
		private static final long serialVersionUID = 1L;

		private TextField nameField;
		private CheckBox myItemCheckbox;

		public OpportunityBasicSearchLayout() {
			super();
		}

		@Override
		public ComponentContainer constructHeader() {
			return createSearchTopPanel();
		}

		@Override
		public ComponentContainer constructBody() {
			HorizontalLayout layout = new HorizontalLayout();
			layout.setSpacing(true);
			layout.addComponent(new Label("Name"));
			nameField = new TextField();
			nameField.setWidth(UIConstants.DEFAULT_CONTROL_WIDTH);
			UiUtils.addComponent(layout, nameField, Alignment.MIDDLE_CENTER);
			myItemCheckbox = new CheckBox("My Items");
			UiUtils.addComponent(layout, myItemCheckbox,
					Alignment.MIDDLE_CENTER);

			layout.addComponent(new Button("Search Opportunities",
					new Button.ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(ClickEvent event) {
							searchCriteria = new OpportunitySearchCriteria();
							searchCriteria.setSaccountid(new NumberSearchField(
									SearchField.AND, AppContext.getAccountId()));
							OpportunitySearchPanel.this
									.notifySearchHandler(searchCriteria);
						}
					}));

			layout.addComponent(new Button("Cancel",
					new Button.ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(ClickEvent event) {
							nameField.setValue("");
						}
					}));

			Button advancedSearchBtn = new Button("Advanced Search",
					new Button.ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(ClickEvent event) {
							OpportunitySearchPanel.this
									.createAdvancedSearchLayout();
						}
					});
			advancedSearchBtn.setStyleName("link");
			UiUtils.addComponent(layout, advancedSearchBtn,
					Alignment.MIDDLE_CENTER);
			return layout;
		}
	}

	private class OpportunityAdvancedSearchLayout extends AdvancedSearchLayout {
		private static final long serialVersionUID = 1L;

		private TextField opportunityNameField;
		private AccountSelectionField accountField;
		private TextField nextStepField;
		private UserListSelect userField;
		private SalesStageListSelect stageField;
		private LeadSourceListSelect sourceField;

		public OpportunityAdvancedSearchLayout() {
			super();
		}

		@Override
		public ComponentContainer constructHeader() {
			return createSearchTopPanel();
		}

		@Override
		public ComponentContainer constructBody() {
			GridFormLayoutHelper gridLayout = new GridFormLayoutHelper(3, 2);

			opportunityNameField = (TextField) gridLayout.addComponent(
					new TextField(), "Opportunity Name", 0, 0);
			accountField = (AccountSelectionField) gridLayout.addComponent(
					AppContext.getSpringBean(AccountSelectionField.class),
					"Account", 1, 0);
			nextStepField = (TextField) gridLayout.addComponent(
					new TextField(), "Next Step", 2, 0);

			userField = (UserListSelect) gridLayout.addComponent(
					AppContext.getSpringBean(UserListSelect.class),
					"Assigned to", 0, 1);
			stageField = (SalesStageListSelect) gridLayout.addComponent(
					AppContext.getSpringBean(SalesStageListSelect.class),
					"Sales Stage", 1, 1);
			sourceField = (LeadSourceListSelect) gridLayout.addComponent(
					AppContext.getSpringBean(LeadSourceListSelect.class),
					"Lead Source", 2, 1);

			return gridLayout.getLayout();
		}

		@Override
		public ComponentContainer constructFooter() {
			HorizontalLayout buttonControls = new HorizontalLayout();
			buttonControls.setSpacing(true);
			buttonControls.addComponent(new Button("Search",
					new Button.ClickListener() {

						@Override
						public void buttonClick(ClickEvent event) {
							searchCriteria = new OpportunitySearchCriteria();
							searchCriteria.setSaccountid(new NumberSearchField(
									SearchField.AND, AppContext.getAccountId()));
							OpportunitySearchPanel.this
									.notifySearchHandler(searchCriteria);
						}

					}));

			buttonControls.addComponent(new Button("Clear",
					new Button.ClickListener() {

						@Override
						public void buttonClick(ClickEvent event) {

						}

					}));

			Button basicSearchBtn = new Button("Basic Search",
					new Button.ClickListener() {

						@Override
						public void buttonClick(ClickEvent event) {
							OpportunitySearchPanel.this
									.createBasicSearchLayout();

						}
					});
			basicSearchBtn.setStyleName("link");
			UiUtils.addComponent(buttonControls, basicSearchBtn,
					Alignment.MIDDLE_CENTER);

			return buttonControls;
		}
	}
}
