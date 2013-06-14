package com.esofthead.mycollab.module.project.view.bug;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.esofthead.mycollab.core.arguments.DateSearchField;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.RangeDateSearchField;
import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.core.utils.StringUtil;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectDataTypeFactory;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.domain.SimpleProject;
import com.esofthead.mycollab.module.project.events.BugEvent;
import com.esofthead.mycollab.module.project.localization.BugI18nEnum;
import com.esofthead.mycollab.module.tracker.domain.Component;
import com.esofthead.mycollab.module.tracker.domain.Version;
import com.esofthead.mycollab.module.tracker.domain.criteria.BugSearchCriteria;
import com.esofthead.mycollab.shell.view.ScreenSize;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.ui.DateSelectionField;
import com.esofthead.mycollab.vaadin.ui.GenericSearchPanel;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.UiUtils;
import com.esofthead.mycollab.web.AppContext;
import com.esofthead.mycollab.web.LocalizationHelper;
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
	private final SimpleProject project;
	protected BugSearchCriteria searchCriteria;

	public BugSearchPanel() {
		this.project = CurrentProjectVariables.getProject();
	}

	@Override
	public void attach() {
		super.attach();
		this.createBasicSearchLayout();
	}

	private void createBasicSearchLayout() {

		this.setCompositionRoot(new BugBasicSearchLayout());
	}

	private void createAdvancedSearchLayout() {
		final BugAdvancedSearchLayout layout = new BugAdvancedSearchLayout();
		this.setCompositionRoot(layout);
	}

	private HorizontalLayout createSearchTopPanel() {
		final HorizontalLayout layout = new HorizontalLayout();
		layout.setWidth("100%");
		layout.setSpacing(true);

		final Label searchtitle = new Label("Search Bugs");
		searchtitle.setStyleName(Reindeer.LABEL_H2);
		layout.addComponent(searchtitle);

		final Button createAccountBtn = new Button(
				LocalizationHelper.getMessage(BugI18nEnum.NEW_BUG_ACTION),
				new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(final ClickEvent event) {
						EventBus.getInstance().fireEvent(
								new BugEvent.GotoAdd(this, null));
					}
				});
		createAccountBtn.setEnabled(CurrentProjectVariables
				.canWrite(ProjectRolePermissionCollections.BUGS));
		createAccountBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
		createAccountBtn.setIcon(new ThemeResource("icons/16/addRecord.png"));

		UiUtils.addComponent(layout, createAccountBtn, Alignment.MIDDLE_RIGHT);

		return layout;
	}

	@SuppressWarnings("rawtypes")
	private class BugBasicSearchLayout extends BasicSearchLayout {

		@SuppressWarnings("unchecked")
		public BugBasicSearchLayout() {
			super(BugSearchPanel.this);
		}

		private static final long serialVersionUID = 1L;
		private TextField nameField;
		private CheckBox myItemCheckbox;

		@Override
		public ComponentContainer constructHeader() {
			return BugSearchPanel.this.createSearchTopPanel();
		}

		@SuppressWarnings("serial")
		@Override
		public ComponentContainer constructBody() {
			final HorizontalLayout basicSearchBody = new HorizontalLayout();
			basicSearchBody.setSpacing(true);
			basicSearchBody.addComponent(new Label("Name"));
			this.nameField = new TextField();
			this.nameField.setWidth(UIConstants.DEFAULT_CONTROL_WIDTH);
			UiUtils.addComponent(basicSearchBody, this.nameField,
					Alignment.MIDDLE_CENTER);
			this.myItemCheckbox = new CheckBox("My Items");
			UiUtils.addComponent(basicSearchBody, this.myItemCheckbox,
					Alignment.MIDDLE_CENTER);

			final Button searchBtn = new Button("Search");
			searchBtn.setStyleName(UIConstants.THEME_ROUND_BUTTON);
			searchBtn.addListener(new Button.ClickListener() {
				@Override
				public void buttonClick(final ClickEvent event) {

					BugSearchPanel.this
							.notifySearchHandler(BugSearchPanel.this.searchCriteria);
				}
			});

			basicSearchBody.addComponent(searchBtn);

			final Button cancelBtn = new Button("Clear");
			cancelBtn.setStyleName(UIConstants.THEME_ROUND_BUTTON);
			cancelBtn.addListener(new Button.ClickListener() {
				@Override
				public void buttonClick(final ClickEvent event) {
					BugBasicSearchLayout.this.nameField.setValue("");
				}
			});
			basicSearchBody.addComponent(cancelBtn);

			final Button advancedSearchBtn = new Button("Advanced Search",
					new Button.ClickListener() {
						@Override
						public void buttonClick(final ClickEvent event) {
							BugSearchPanel.this.createAdvancedSearchLayout();
						}
					});
			advancedSearchBtn.setStyleName("link");
			UiUtils.addComponent(basicSearchBody, advancedSearchBtn,
					Alignment.MIDDLE_CENTER);

			return basicSearchBody;
		}

		@Override
		protected SearchCriteria fillupSearchCriteria() {
			BugSearchPanel.this.searchCriteria = new BugSearchCriteria();
			BugSearchPanel.this.searchCriteria
					.setProjectId(new NumberSearchField(SearchField.AND,
							BugSearchPanel.this.project.getId()));
			BugSearchPanel.this.searchCriteria
					.setSummary(new StringSearchField(this.nameField.getValue()
							.toString().trim()));
			return BugSearchPanel.this.searchCriteria;
		}
	}

	@SuppressWarnings({ "serial", "rawtypes" })
	private class BugAdvancedSearchLayout extends AdvancedSearchLayout {

		private TextField nameField;
		private CheckBox summaryField;
		private CheckBox descriptionField;

		private BugStaticItemMultiSelectField priorityField;
		private BugStaticItemMultiSelectField statusField;
		private BugStaticItemMultiSelectField resolutionField;
		private BugStaticItemMultiSelectField severityField;

		private ComponentMultiSelectField componentField;
		private VersionMultiSelectField affectedVersionField;
		private VersionMultiSelectField fixedVersionField;

		private DateSelectionField updateDateField;
		private DateSelectionField dueDateField;
		private DateSelectionField resolveDateField;

		@SuppressWarnings("unchecked")
		public BugAdvancedSearchLayout() {
			super(BugSearchPanel.this);
		}

		@Override
		public ComponentContainer constructHeader() {
			return BugSearchPanel.this.createSearchTopPanel();
		}

		@Override
		public ComponentContainer constructBody() {
			GridFormLayoutHelper gridLayout = new GridFormLayoutHelper(2, 6,
					"150px");

			String nameFieldWidth = "400px";
			String dateFieldWidth = "245px";
			String componentFieldWidth = "225px";

			if (ScreenSize.hasSupport1024Pixels()) {
				gridLayout = new GridFormLayoutHelper(2, 6,
						UIConstants.DEFAULT_CONTROL_WIDTH_1024_RESOLUTION,
						"150px");
				nameFieldWidth = "380px";
				dateFieldWidth = "195px";
				componentFieldWidth = "170px";
			} else if (ScreenSize.hasSupport1280Pixels()) {
				gridLayout = new GridFormLayoutHelper(2, 6, "150px");
			}
			gridLayout.getLayout().setWidth("100%");
			gridLayout.getLayout().setSpacing(true);

			HorizontalLayout layoutCheckbox;

			layoutCheckbox = (HorizontalLayout) gridLayout.addComponent(
					new HorizontalLayout(), "Name", 0, 0, 2, "100%");
			layoutCheckbox.setSpacing(false);

			this.nameField = new TextField();
			this.nameField.setWidth(nameFieldWidth);
			this.summaryField = new CheckBox("Summary", true);
			this.descriptionField = new CheckBox("Description", true);
			layoutCheckbox.addComponent(this.nameField);
			layoutCheckbox.addComponent(this.summaryField);
			layoutCheckbox.addComponent(this.descriptionField);

			this.updateDateField = (DateSelectionField) gridLayout
					.addComponent(new DateSelectionField(dateFieldWidth),
							"Update Date", 0, 1);
			this.updateDateField.setDateFormat(AppContext.getDateFormat());

			this.dueDateField = (DateSelectionField) gridLayout.addComponent(
					new DateSelectionField(dateFieldWidth), "Due Date", 0, 3);
			this.dueDateField.setDateFormat(AppContext.getDateFormat());

			this.resolveDateField = (DateSelectionField) gridLayout
					.addComponent(new DateSelectionField(dateFieldWidth),
							"Resolve Date", 0, 2);
			this.resolveDateField.setDateFormat(AppContext.getDateFormat());

			this.componentField = (ComponentMultiSelectField) gridLayout
					.addComponent(new ComponentMultiSelectField(
							componentFieldWidth), "Component", 1, 2);

			this.affectedVersionField = (VersionMultiSelectField) gridLayout
					.addComponent(new VersionMultiSelectField(
							componentFieldWidth), "Affected Version", 1, 1);

			this.fixedVersionField = (VersionMultiSelectField) gridLayout
					.addComponent(new VersionMultiSelectField(
							componentFieldWidth), "Fixed Version", 1, 3);

			this.priorityField = (BugStaticItemMultiSelectField) gridLayout
					.addComponent(new BugStaticItemMultiSelectField(
							ProjectDataTypeFactory.getBugPriorityList(),
							componentFieldWidth), "Priority", 0, 4);

			this.statusField = (BugStaticItemMultiSelectField) gridLayout
					.addComponent(new BugStaticItemMultiSelectField(
							ProjectDataTypeFactory.getBugStatusList(),
							componentFieldWidth), "Status", 1, 4);

			this.resolutionField = (BugStaticItemMultiSelectField) gridLayout
					.addComponent(new BugStaticItemMultiSelectField(
							ProjectDataTypeFactory.getBugResolutionList(),
							componentFieldWidth), "Resolution", 0, 5);
			this.severityField = (BugStaticItemMultiSelectField) gridLayout
					.addComponent(new BugStaticItemMultiSelectField(
							ProjectDataTypeFactory.getBugSeverityList(),
							componentFieldWidth), "Severity", 1, 5);

			return gridLayout.getLayout();
		}

		@Override
		public ComponentContainer constructFooter() {
			final HorizontalLayout buttonControls = new HorizontalLayout();
			buttonControls.setSpacing(true);

			final Button searchBtn = new Button("Search",
					new Button.ClickListener() {
						@SuppressWarnings("unchecked")
						@Override
						public void buttonClick(final ClickEvent event) {
							BugAdvancedSearchLayout.this.callSearchAction();
						}
					});

			buttonControls.addComponent(searchBtn);
			searchBtn.setStyleName(UIConstants.THEME_ROUND_BUTTON);

			final Button clearBtn = new Button("Clear",
					new Button.ClickListener() {
						@Override
						public void buttonClick(final ClickEvent event) {
							BugAdvancedSearchLayout.this.nameField.setValue("");
							BugAdvancedSearchLayout.this.updateDateField
									.setDefaultSelection();
							BugAdvancedSearchLayout.this.resolveDateField
									.setDefaultSelection();
							BugAdvancedSearchLayout.this.dueDateField
									.setDefaultSelection();
							BugAdvancedSearchLayout.this.priorityField
									.resetComp();
							BugAdvancedSearchLayout.this.resolutionField
									.resetComp();
							BugAdvancedSearchLayout.this.affectedVersionField
									.resetComp();
							BugAdvancedSearchLayout.this.componentField
									.resetComp();
							BugAdvancedSearchLayout.this.fixedVersionField
									.resetComp();
							BugAdvancedSearchLayout.this.statusField
									.resetComp();
							BugAdvancedSearchLayout.this.severityField
									.resetComp();
							BugAdvancedSearchLayout.this.summaryField
									.setValue(true);
							BugAdvancedSearchLayout.this.descriptionField
									.setValue(true);
						}
					});
			clearBtn.setStyleName(UIConstants.THEME_ROUND_BUTTON);
			buttonControls.addComponent(clearBtn);

			final Button basicSearchBtn = new Button("Basic Search",
					new Button.ClickListener() {
						@Override
						public void buttonClick(final ClickEvent event) {
							BugSearchPanel.this.createBasicSearchLayout();

						}
					});
			basicSearchBtn.setStyleName("link");
			UiUtils.addComponent(buttonControls, basicSearchBtn,
					Alignment.MIDDLE_CENTER);
			return buttonControls;
		}

		@Override
		protected SearchCriteria fillupSearchCriteria() {
			BugSearchPanel.this.searchCriteria = new BugSearchCriteria();
			BugSearchPanel.this.searchCriteria
					.setProjectId(new NumberSearchField(SearchField.AND,
							BugSearchPanel.this.project.getId()));

			if (StringUtil.isNotNullOrEmpty((String) this.nameField.getValue())) {

				if (((Boolean) this.summaryField.getValue()) == true) {
					BugSearchPanel.this.searchCriteria
							.setSummary(new StringSearchField(SearchField.AND,
									((String) this.nameField.getValue()).trim()));
				}

				if (((Boolean) this.descriptionField.getValue()) == true) {
					if (((Boolean) this.summaryField.getValue()) == true) {
						BugSearchPanel.this.searchCriteria
								.setDescription(new StringSearchField(
										SearchField.OR,
										((String) this.nameField.getValue())
												.trim()));
					} else {
						BugSearchPanel.this.searchCriteria
								.setDescription(new StringSearchField(
										SearchField.AND,
										((String) this.nameField.getValue())
												.trim()));
					}
				}
			}

			final SearchField updateDate = this.updateDateField.getValue();
			if (updateDate != null && (updateDate instanceof DateSearchField)) {
				BugSearchPanel.this.searchCriteria
						.setUpdatedDate((DateSearchField) updateDate);
			} else if (updateDate != null
					&& (updateDate instanceof RangeDateSearchField)) {
				BugSearchPanel.this.searchCriteria
						.setUpdatedDateRange((RangeDateSearchField) updateDate);
			}

			final SearchField resolvedDate = this.resolveDateField.getValue();
			if (resolvedDate != null
					&& (resolvedDate instanceof DateSearchField)) {
				BugSearchPanel.this.searchCriteria
						.setResolvedDate((DateSearchField) resolvedDate);
			} else if (resolvedDate != null
					&& (resolvedDate instanceof RangeDateSearchField)) {
				BugSearchPanel.this.searchCriteria
						.setResolvedDateRange((RangeDateSearchField) resolvedDate);
			}

			final SearchField dueDate = this.dueDateField.getValue();
			if (dueDate != null && (dueDate instanceof DateSearchField)) {
				BugSearchPanel.this.searchCriteria
						.setDueDate((DateSearchField) dueDate);
			} else if (dueDate != null
					&& (dueDate instanceof RangeDateSearchField)) {
				BugSearchPanel.this.searchCriteria
						.setDueDateRange((RangeDateSearchField) dueDate);
			}

			final Collection<String> priorities = this.priorityField
					.getSelectedItems();
			if (priorities != null && priorities.size() > 0) {
				BugSearchPanel.this.searchCriteria
						.setPriorities(new SetSearchField<String>(
								SearchField.AND, priorities));
			}

			final Collection<String> resolutions = this.resolutionField
					.getSelectedItems();
			if (resolutions != null && resolutions.size() > 0) {
				BugSearchPanel.this.searchCriteria
						.setResolutions(new SetSearchField<String>(
								SearchField.AND, resolutions));
			}

			final Collection<String> statues = this.statusField
					.getSelectedItems();
			if (statues != null && statues.size() > 0) {
				BugSearchPanel.this.searchCriteria
						.setStatuses(new SetSearchField<String>(
								SearchField.AND, statues));
			}

			final Collection<String> severities = this.severityField
					.getSelectedItems();
			if (severities != null && severities.size() > 0) {
				BugSearchPanel.this.searchCriteria
						.setSeverities(new SetSearchField<String>(
								SearchField.AND, severities));
			}

			final Collection<Version> afftectVersions = this.affectedVersionField
					.getSelectedItems();

			final List<Integer> lstIdAfectedVersion = new ArrayList<Integer>();
			for (final Version itemVersion : afftectVersions) {
				lstIdAfectedVersion.add(itemVersion.getId());
			}
			if (lstIdAfectedVersion != null && lstIdAfectedVersion.size() > 0) {
				BugSearchPanel.this.searchCriteria
						.setVersionids(new SetSearchField<Integer>(
								SearchField.AND, lstIdAfectedVersion));
			}

			final Collection<Version> fixedVersions = this.fixedVersionField
					.getSelectedItems();

			final List<Integer> lstIdFixedVersion = new ArrayList<Integer>();
			for (final Version itemVersion : fixedVersions) {
				lstIdFixedVersion.add(itemVersion.getId());
			}
			if (lstIdFixedVersion != null && lstIdFixedVersion.size() > 0) {
				BugSearchPanel.this.searchCriteria
						.setFixedversionids(new SetSearchField<Integer>(
								SearchField.AND, lstIdFixedVersion));
			}

			final Collection<Component> components = this.componentField
					.getSelectedItems();

			final List<Integer> lstIdComponent = new ArrayList<Integer>();
			for (final Component itemComp : components) {
				lstIdFixedVersion.add(itemComp.getId());
			}
			if (lstIdComponent != null && lstIdComponent.size() > 0) {
				BugSearchPanel.this.searchCriteria
						.setComponentids(new SetSearchField<Integer>(
								SearchField.AND, lstIdComponent));
			}
			return BugSearchPanel.this.searchCriteria;
		}
	}
}
