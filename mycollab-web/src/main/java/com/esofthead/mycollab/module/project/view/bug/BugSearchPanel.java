package com.esofthead.mycollab.module.project.view.bug;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.esofthead.mycollab.core.arguments.DateSearchField;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.RangeDateSearchField;
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

		Button createAccountBtn = new Button(
				AppContext.getMessage(BugI18nEnum.NEW_BUG_ACTION),
				new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(ClickEvent event) {
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
					searchCriteria.setSummary(new StringSearchField(nameField
							.getValue().toString().trim()));
					BugSearchPanel.this.notifySearchHandler(searchCriteria);
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
							BugSearchPanel.this.createAdvancedSearchLayout();
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

		public BugAdvancedSearchLayout() {
			super();
		}

		@Override
		public ComponentContainer constructHeader() {
			return createSearchTopPanel();
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

			HorizontalLayout layoutCheckbox;

			layoutCheckbox = (HorizontalLayout) gridLayout.addComponent(
					new HorizontalLayout(), "Name", 0, 0, 2, "100%");
			layoutCheckbox.setSpacing(false);

			nameField = new TextField();
			nameField.setWidth(nameFieldWidth);
			summaryField = new CheckBox("Summary", true);
			descriptionField = new CheckBox("Description", true);
			layoutCheckbox.addComponent(nameField);
			layoutCheckbox.addComponent(summaryField);
			layoutCheckbox.addComponent(descriptionField);

			updateDateField = (DateSelectionField) gridLayout
					.addComponent(new DateSelectionField(dateFieldWidth),
							"Update Date", 0, 1);
			updateDateField.setDateFormat(AppContext.getDateFormat());

			dueDateField = (DateSelectionField) gridLayout.addComponent(
					new DateSelectionField(dateFieldWidth), "Due Date", 0, 3);
			dueDateField.setDateFormat(AppContext.getDateFormat());

			resolveDateField = (DateSelectionField) gridLayout.addComponent(
					new DateSelectionField(dateFieldWidth), "Resolve Date", 0,
					2);
			resolveDateField.setDateFormat(AppContext.getDateFormat());

			componentField = (ComponentMultiSelectField) gridLayout
					.addComponent(new ComponentMultiSelectField(
							componentFieldWidth), "Component", 1, 2);

			affectedVersionField = (VersionMultiSelectField) gridLayout
					.addComponent(new VersionMultiSelectField(
							componentFieldWidth), "Affected Version", 1, 1);

			fixedVersionField = (VersionMultiSelectField) gridLayout
					.addComponent(new VersionMultiSelectField(
							componentFieldWidth), "Fixed Version", 1, 3);

			priorityField = (BugStaticItemMultiSelectField) gridLayout
					.addComponent(new BugStaticItemMultiSelectField(
							ProjectDataTypeFactory.getBugPriorityList(),
							componentFieldWidth), "Priority", 0, 4);

			statusField = (BugStaticItemMultiSelectField) gridLayout
					.addComponent(new BugStaticItemMultiSelectField(
							ProjectDataTypeFactory.getBugStatusList(),
							componentFieldWidth), "Status", 1, 4);

			resolutionField = (BugStaticItemMultiSelectField) gridLayout
					.addComponent(new BugStaticItemMultiSelectField(
							ProjectDataTypeFactory.getBugResolutionList(),
							componentFieldWidth), "Resolution", 0, 5);
			severityField = (BugStaticItemMultiSelectField) gridLayout
					.addComponent(new BugStaticItemMultiSelectField(
							ProjectDataTypeFactory.getBugSeverityList(),
							componentFieldWidth), "Severity", 1, 5);

			return gridLayout.getLayout();
		}

		@Override
		public ComponentContainer constructFooter() {
			HorizontalLayout buttonControls = new HorizontalLayout();
			buttonControls.setSpacing(true);

			Button searchBtn = new Button("Search", new Button.ClickListener() {
				@SuppressWarnings("unchecked")
				@Override
				public void buttonClick(ClickEvent event) {
					searchCriteria = new BugSearchCriteria();
					searchCriteria.setProjectId(new NumberSearchField(
							SearchField.AND, project.getId()));

					if (StringUtil.isNotNullOrEmpty((String) nameField
							.getValue())) {

						if (((Boolean) summaryField.getValue()) == true) {
							searchCriteria.setSummary(new StringSearchField(
									SearchField.AND, ((String) nameField
											.getValue()).trim()));
						}

						if (((Boolean) descriptionField.getValue()) == true) {
							if (((Boolean) summaryField.getValue()) == true) {
								searchCriteria
										.setDescription(new StringSearchField(
												SearchField.OR,
												((String) nameField.getValue())
														.trim()));
							} else {
								searchCriteria
										.setDescription(new StringSearchField(
												SearchField.AND,
												((String) nameField.getValue())
														.trim()));
							}
						}
					}

					SearchField updateDate = updateDateField.getValue();
					if (updateDate != null
							&& (updateDate instanceof DateSearchField)) {
						searchCriteria
								.setUpdatedDate((DateSearchField) updateDate);
					} else if (updateDate != null
							&& (updateDate instanceof RangeDateSearchField)) {
						searchCriteria
								.setUpdatedDateRange((RangeDateSearchField) updateDate);
					}

					SearchField resolvedDate = resolveDateField.getValue();
					if (resolvedDate != null
							&& (resolvedDate instanceof DateSearchField)) {
						searchCriteria
								.setResolvedDate((DateSearchField) resolvedDate);
					} else if (resolvedDate != null
							&& (resolvedDate instanceof RangeDateSearchField)) {
						searchCriteria
								.setResolvedDateRange((RangeDateSearchField) resolvedDate);
					}

					SearchField dueDate = dueDateField.getValue();
					if (dueDate != null && (dueDate instanceof DateSearchField)) {
						searchCriteria.setDueDate((DateSearchField) dueDate);
					} else if (dueDate != null
							&& (dueDate instanceof RangeDateSearchField)) {
						searchCriteria
								.setDueDateRange((RangeDateSearchField) dueDate);
					}

					Collection<String> priorities = priorityField
							.getSelectedItems();
					if (priorities != null && priorities.size() > 0) {
						searchCriteria
								.setPriorities(new SetSearchField<String>(
										SearchField.AND, priorities));
					}

					Collection<String> resolutions = resolutionField
							.getSelectedItems();
					if (resolutions != null && resolutions.size() > 0) {
						searchCriteria
								.setResolutions(new SetSearchField<String>(
										SearchField.AND, resolutions));
					}

					Collection<String> statues = statusField.getSelectedItems();
					if (statues != null && statues.size() > 0) {
						searchCriteria.setStatuses(new SetSearchField<String>(
								SearchField.AND, statues));
					}

					Collection<String> severities = severityField
							.getSelectedItems();
					if (severities != null && severities.size() > 0) {
						searchCriteria
								.setSeverities(new SetSearchField<String>(
										SearchField.AND, severities));
					}

					Collection<Version> afftectVersions = affectedVersionField
							.getSelectedItems();

					List<Integer> lstIdAfectedVersion = new ArrayList<Integer>();
					for (Version itemVersion : afftectVersions) {
						lstIdAfectedVersion.add(itemVersion.getId());
					}
					if (lstIdAfectedVersion != null
							&& lstIdAfectedVersion.size() > 0) {
						searchCriteria
								.setVersionids(new SetSearchField<Integer>(
										SearchField.AND, lstIdAfectedVersion));
					}

					Collection<Version> fixedVersions = fixedVersionField
							.getSelectedItems();

					List<Integer> lstIdFixedVersion = new ArrayList<Integer>();
					for (Version itemVersion : fixedVersions) {
						lstIdFixedVersion.add(itemVersion.getId());
					}
					if (lstIdFixedVersion != null
							&& lstIdFixedVersion.size() > 0) {
						searchCriteria
								.setFixedversionids(new SetSearchField<Integer>(
										SearchField.AND, lstIdFixedVersion));
					}

					Collection<Component> components = componentField
							.getSelectedItems();

					List<Integer> lstIdComponent = new ArrayList<Integer>();
					for (Component itemComp : components) {
						lstIdFixedVersion.add(itemComp.getId());
					}
					if (lstIdComponent != null && lstIdComponent.size() > 0) {
						searchCriteria
								.setComponentids(new SetSearchField<Integer>(
										SearchField.AND, lstIdComponent));
					}

					BugSearchPanel.this.notifySearchHandler(searchCriteria);

				}
			});

			buttonControls.addComponent(searchBtn);
			searchBtn.setStyleName(UIConstants.THEME_ROUND_BUTTON);

			Button clearBtn = new Button("Clear", new Button.ClickListener() {
				@Override
				public void buttonClick(ClickEvent event) {
					nameField.setValue("");
					updateDateField.setDefaultSelection();
					resolveDateField.setDefaultSelection();
					dueDateField.setDefaultSelection();
					priorityField.resetComp();
					resolutionField.resetComp();
					affectedVersionField.resetComp();
					componentField.resetComp();
					fixedVersionField.resetComp();
					statusField.resetComp();
					severityField.resetComp();
					summaryField.setValue(true);
					descriptionField.setValue(true);
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
