package com.esofthead.mycollab.module.project.view.bug;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.module.project.ProjectContants;
import com.esofthead.mycollab.module.project.domain.SimpleProject;
import com.esofthead.mycollab.module.project.events.BugEvent;
import com.esofthead.mycollab.module.tracker.domain.criteria.BugSearchCriteria;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.ui.DateSelectionField;
import com.esofthead.mycollab.vaadin.ui.GenericSearchPanel;
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
import com.vaadin.ui.themes.Reindeer;

public class BugSearchPanel extends GenericSearchPanel<BugSearchCriteria> {
	private static final long serialVersionUID = 1L;

	private SimpleProject project;
	protected BugSearchCriteria searchCriteria;

	public BugSearchPanel() {
		this.project = (SimpleProject) AppContext
				.getVariable(ProjectContants.PROJECT_NAME);
	}

	@Override
	public void attach() {
		super.attach();
		createBasicSearchLayout();
	}

	private void createBasicSearchLayout() {

		this.setCompositionRoot(new BugBasicSearchLayout());
	}
	
	private void createAdvancedSearchLayout() {
		BugAdvancedSearchLayout layout = new BugAdvancedSearchLayout();
		this.setCompositionRoot(layout);
	}

	private HorizontalLayout createSearchTopPanel() {
		HorizontalLayout layout = new HorizontalLayout();
		layout.setWidth("100%");
		layout.setSpacing(true);

		Label searchtitle = new Label("Search Bugs");
		searchtitle.setStyleName(Reindeer.LABEL_H2);
		layout.addComponent(searchtitle);

		Button createAccountBtn = new Button("Create",
				new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(ClickEvent event) {
						EventBus.getInstance().fireEvent(
								new BugEvent.GotoAdd(this, null));
					}
				});
		createAccountBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
		createAccountBtn.setIcon(new ThemeResource("icons/16/addRecord.png"));

		UiUtils.addComponent(layout, createAccountBtn, Alignment.MIDDLE_RIGHT);

		return layout;
	}

	private class BugBasicSearchLayout extends BasicSearchLayout {
		private static final long serialVersionUID = 1L;

		private TextField nameField;
		private CheckBox myItemCheckbox;

		@Override
		public ComponentContainer constructHeader() {
			return createSearchTopPanel();
		}

		@SuppressWarnings("serial")
		@Override
		public ComponentContainer constructBody() {
			HorizontalLayout basicSearchBody = new HorizontalLayout();
			basicSearchBody.setSpacing(true);
			basicSearchBody.addComponent(new Label("Name"));
			nameField = new TextField();
			nameField.setWidth(UIConstants.DEFAULT_CONTROL_WIDTH);
			UiUtils.addComponent(basicSearchBody, nameField,
					Alignment.MIDDLE_CENTER);
			myItemCheckbox = new CheckBox("My Items");
			UiUtils.addComponent(basicSearchBody, myItemCheckbox,
					Alignment.MIDDLE_CENTER);
			
			Button searchBtn = new Button("Search");
			searchBtn.setStyleName(UIConstants.THEME_ROUND_BUTTON);
			searchBtn.addListener(new Button.ClickListener() {
				@Override
				public void buttonClick(ClickEvent event) {
					searchCriteria = new BugSearchCriteria();
					searchCriteria.setProjectId(new NumberSearchField(
                            SearchField.AND, project.getId()));
					searchCriteria.setSummary(new StringSearchField(nameField.getValue().toString().trim()));
					BugSearchPanel.this
							.notifySearchHandler(searchCriteria);
				}
			});

			basicSearchBody.addComponent(searchBtn);

			Button cancelBtn = new Button("Clear");
			cancelBtn.setStyleName(UIConstants.THEME_ROUND_BUTTON);
			cancelBtn.addListener(new Button.ClickListener() {
				@Override
				public void buttonClick(ClickEvent event) {
					nameField.setValue("");
				}
			});
			basicSearchBody.addComponent(cancelBtn);
			
			Button advancedSearchBtn = new Button("Advanced Search",
					new Button.ClickListener() {
						@Override
						public void buttonClick(ClickEvent event) {
							BugSearchPanel.this
									.createAdvancedSearchLayout();
						}
					});
			advancedSearchBtn.setStyleName("link");
			UiUtils.addComponent(basicSearchBody, advancedSearchBtn,
					Alignment.MIDDLE_CENTER);
			
			return basicSearchBody;
		}
	}
	
	@SuppressWarnings("serial")
	private class BugAdvancedSearchLayout extends AdvancedSearchLayout {

		private TextField nameField;
		private CheckBox summaryField;
		private CheckBox descriptionField;
		
		private BugPriorityComboBox priorityField;
		private TextField statusField;
		private TextField resolutionField;
		
		private ComponentMultiSelectField componentField;
		private VersionMultiSelectField affectedVersionField;
		private VersionMultiSelectField fixedVersionField;
		
		private DateSelectionField updateDateField;
		private DateSelectionField dueDateField;
		private DateSelectionField resolveDateField;

		public BugAdvancedSearchLayout() {
			super();
		}

		@Override
		public ComponentContainer constructHeader() {
			return createSearchTopPanel();
		}

		@Override
		public ComponentContainer constructBody() {
			GridFormLayoutHelper gridLayout = new GridFormLayoutHelper(3, 6);

			nameField = (TextField) gridLayout.addComponent(new TextField(),
					"Name", 0, 0);
			
			HorizontalLayout layoutCheckbox;
			
			layoutCheckbox = (HorizontalLayout) gridLayout.addComponent(new HorizontalLayout(),
					null, 1, 0);
			layoutCheckbox.setSpacing(true);
			summaryField = new CheckBox("Summary", true);
			descriptionField = new CheckBox("Description", true);
			layoutCheckbox.addComponent(summaryField);
			layoutCheckbox.addComponent(descriptionField);
			
			updateDateField = (DateSelectionField) gridLayout.addComponent(
					new DateSelectionField(), "Update Date", 0, 1);
			updateDateField.setDateFormat(AppContext.getDateFormat());

			dueDateField = (DateSelectionField) gridLayout.addComponent(
					new DateSelectionField(), "Due Date", 1, 1);
			dueDateField.setDateFormat(AppContext.getDateFormat());
			
			resolveDateField = (DateSelectionField) gridLayout.addComponent(
					new DateSelectionField(), "Resolve Date", 0, 2);
			resolveDateField.setDateFormat(AppContext.getDateFormat());
			
			componentField = (ComponentMultiSelectField) gridLayout.addComponent(new ComponentMultiSelectField(),
					"Component", 1, 2);
			
			affectedVersionField = (VersionMultiSelectField) gridLayout.addComponent(new VersionMultiSelectField(),
					"Affected Version", 0, 3);
			
			fixedVersionField = (VersionMultiSelectField) gridLayout.addComponent(new VersionMultiSelectField(),
					"Description", 1, 3);
			
			priorityField = (BugPriorityComboBox) gridLayout.addComponent(new BugPriorityComboBox(),
					"Priority", 0, 4);
			
			statusField = (TextField) gridLayout.addComponent(new TextField(),
					"Status", 1, 4);
			
			resolutionField = (TextField) gridLayout.addComponent(new TextField(),
					"Description", 0, 5);


			return gridLayout.getLayout();
		}

		@Override
		public ComponentContainer constructFooter() {
			HorizontalLayout buttonControls = new HorizontalLayout();
			buttonControls.setSpacing(true);
			
			Button searchBtn = new Button("Search", new Button.ClickListener() {
				@SuppressWarnings({ "unchecked" })
				@Override
				public void buttonClick(ClickEvent event) {
					searchCriteria = new BugSearchCriteria();
					searchCriteria.setProjectId(new NumberSearchField(
                            SearchField.AND, project.getId()));

//					if (StringUtil.isNotNullOrEmpty((String) nameField
//							.getValue())) {
//						searchCriteria.setSummary(new StringSearchField(
//								SearchField.AND,
//								((String) nameField.getValue()).trim()));
//					}
//
//					SearchField startDate = startDateField.getValue();
//					if (startDate != null
//							&& (startDate instanceof DateSearchField)) {
//						searchCriteria
//								.setStartDate((DateSearchField) startDate);
//					} else if (startDate != null
//							&& (startDate instanceof RangeDateSearchField)) {
//						searchCriteria
//								.setStartDateRange((RangeDateSearchField) startDate);
//					}
//
//					SearchField endDate = endDateField.getValue();
//					if (endDate != null && (endDate instanceof DateSearchField)) {
//						searchCriteria.setStartDate((DateSearchField) endDate);
//					} else if (endDate != null
//							&& (endDate instanceof RangeDateSearchField)) {
//						searchCriteria
//								.setStartDateRange((RangeDateSearchField) endDate);
//					}
//
//					Collection<String> types = (Collection<String>) typeField
//							.getValue();
//					if (types != null && types.size() > 0) {
//						searchCriteria.setTypes(new SetSearchField<String>(
//								SearchField.AND, types));
//					}
//
//					Collection<String> statuses = (Collection<String>) statusField
//							.getValue();
//					if (statuses != null && statuses.size() > 0) {
//						searchCriteria.setStatuses(new SetSearchField<String>(
//								SearchField.AND, statuses));
//					}
//
//					Collection<String> assignUsers = (Collection<String>) assignUserField
//							.getValue();
//					if (assignUsers != null && assignUsers.size() > 0) {
//						searchCriteria
//								.setAssignUsers(new SetSearchField<String>(
//										SearchField.AND, assignUsers));
//					}

					BugSearchPanel.this
							.notifySearchHandler(searchCriteria);

				}
			});

			buttonControls.addComponent(searchBtn);
			searchBtn.setStyleName(UIConstants.THEME_ROUND_BUTTON);

			Button clearBtn = new Button("Clear", new Button.ClickListener() {
				@Override
				public void buttonClick(ClickEvent event) {
					nameField.setValue("");
//					startDateField.setDefaultSelection();
//					endDateField.setDefaultSelection();
//					typeField.setValue(null);
//					statusField.setValue(null);
//					assignUserField.setValue(null);
				}
			});
			clearBtn.setStyleName(UIConstants.THEME_ROUND_BUTTON);
			buttonControls.addComponent(clearBtn);

			Button basicSearchBtn = new Button("Basic Search",
					new Button.ClickListener() {
						@Override
						public void buttonClick(ClickEvent event) {
							BugSearchPanel.this.createBasicSearchLayout();

						}
					});
			basicSearchBtn.setStyleName("link");
			UiUtils.addComponent(buttonControls, basicSearchBtn,
					Alignment.MIDDLE_CENTER);
			return buttonControls;
		}
	}

}
