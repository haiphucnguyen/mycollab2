package com.esofthead.mycollab.module.project.view.people;

import java.util.Arrays;

import org.vaadin.hene.popupbutton.PopupButton;

import com.esofthead.mycollab.common.ModuleNameConstants;
import com.esofthead.mycollab.common.domain.criteria.ActivityStreamSearchCriteria;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectContants;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.domain.ProjectMember;
import com.esofthead.mycollab.module.project.domain.SimpleProjectMember;
import com.esofthead.mycollab.module.project.domain.SimpleTask;
import com.esofthead.mycollab.module.project.domain.criteria.StandupReportSearchCriteria;
import com.esofthead.mycollab.module.project.domain.criteria.TaskSearchCriteria;
import com.esofthead.mycollab.module.project.events.BugEvent;
import com.esofthead.mycollab.module.project.events.TaskEvent;
import com.esofthead.mycollab.module.project.localization.TaskI18nEnum;
import com.esofthead.mycollab.module.project.view.bug.BugTableDisplay;
import com.esofthead.mycollab.module.project.view.bug.BugTableFieldDef;
import com.esofthead.mycollab.module.project.view.standup.StandupReportListDisplay;
import com.esofthead.mycollab.module.project.view.task.TaskTableDisplay;
import com.esofthead.mycollab.module.project.view.user.ProjectActivityStreamPagedList;
import com.esofthead.mycollab.module.tracker.BugStatusConstants;
import com.esofthead.mycollab.module.tracker.domain.SimpleBug;
import com.esofthead.mycollab.module.tracker.domain.criteria.BugSearchCriteria;
import com.esofthead.mycollab.vaadin.events.ApplicationEvent;
import com.esofthead.mycollab.vaadin.events.ApplicationEventListener;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.ui.AdvancedPreviewBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultFormViewFieldFactory;
import com.esofthead.mycollab.vaadin.ui.Depot;
import com.esofthead.mycollab.vaadin.ui.ProjectPreviewFormControlsGenerator;
import com.esofthead.mycollab.vaadin.ui.ReadViewLayout;
import com.esofthead.mycollab.vaadin.ui.UserAvatarControlFactory;
import com.esofthead.mycollab.vaadin.ui.table.TableClickEvent;
import com.esofthead.mycollab.web.LocalizationHelper;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.lazyloadwrapper.LazyLoadWrapper;
import com.vaadin.terminal.ExternalResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class ProjectMemberPreviewBuilder extends VerticalLayout {
	private static final long serialVersionUID = 1L;
	protected SimpleProjectMember projectMember;
	protected AdvancedPreviewBeanForm<ProjectMember> previewForm;

	protected class ProjectMemberFormFieldFactory extends
			DefaultFormViewFieldFactory {
		private static final long serialVersionUID = 1L;

		@Override
		protected Field onCreateField(final Item item, final Object propertyId,
				final Component uiContext) {
			if (propertyId.equals("isAdmin")) {
				if (ProjectMemberPreviewBuilder.this.projectMember.getIsadmin() != null
						&& ProjectMemberPreviewBuilder.this.projectMember
								.getIsadmin() == Boolean.FALSE) {
					final HorizontalLayout layout = new HorizontalLayout();
					layout.setSpacing(true);
					layout.addComponent(new Label("False"));
					layout.addComponent(new Label("Role: "
							+ ProjectMemberPreviewBuilder.this.projectMember
									.getRoleName()));
					return new DefaultFormViewFieldFactory.FormContainerField(
							layout);
				}
			} else if (propertyId.equals("username")) {
				return new FormViewField(
						ProjectMemberPreviewBuilder.this.projectMember
								.getMemberFullName());
			}
			return null;
		}
	}

	public static class PrintView extends ProjectMemberPreviewBuilder {
		private static final long serialVersionUID = 1L;

		public PrintView() {
			this.previewForm = new AdvancedPreviewBeanForm<ProjectMember>() {
				private static final long serialVersionUID = 1L;

				@Override
				public void setItemDataSource(final Item newDataSource) {
					this.setFormLayoutFactory(new FormLayoutFactory());
					this.setFormFieldFactory(new ProjectMemberFormFieldFactory());
					super.setItemDataSource(newDataSource);
				}
			};

			this.addComponent(this.previewForm);
		}

		class FormLayoutFactory extends ProjectMemberFormLayoutFactory {

			private static final long serialVersionUID = 1L;

			public FormLayoutFactory() {
				super(PrintView.this.projectMember.getMemberFullName(),
						UserAvatarControlFactory.createAvatarResource(
								PrintView.this.projectMember
										.getMemberAvatarId(), 24));
			}

			@Override
			protected Layout createTopPanel() {
				return null;
			}

			@Override
			protected Layout createBottomPanel() {
				final VerticalLayout relatedItemsPanel = new VerticalLayout();
				relatedItemsPanel.addComponent(new UserTaskDepot());
				relatedItemsPanel.addComponent(new UserBugDepot());
				return relatedItemsPanel;
			}
		}
	}

	public static class ReadView extends ProjectMemberPreviewBuilder {
		private static final long serialVersionUID = 1L;

		private final ReadViewLayout projectMemberReadViewLayout;
		private final VerticalLayout assignmentViewLayout;
		private final VerticalLayout standupReportViewLayout;
		private final VerticalLayout basicInformationLayout;

		public ReadView() {
			this.basicInformationLayout = new VerticalLayout();

			this.projectMemberReadViewLayout = new ReadViewLayout(null);
			this.projectMemberReadViewLayout
					.addStyleName("project-member-readview");
			this.addComponent(this.projectMemberReadViewLayout);

			this.standupReportViewLayout = new VerticalLayout();
			this.assignmentViewLayout = new VerticalLayout();

			this.previewForm = new AdvancedPreviewBeanForm<ProjectMember>() {
				private static final long serialVersionUID = 1L;

				@Override
				public void setItemDataSource(final Item newDataSource) {
					this.setFormLayoutFactory(new ProjectMemberFormLayoutFactory.ProjectMemberInformationLayout());
					this.setFormFieldFactory(new ProjectMemberFormFieldFactory());
					super.setItemDataSource(newDataSource);
					ReadView.this.projectMemberReadViewLayout
							.setTitle(ReadView.this.projectMember
									.getMemberFullName());
					ReadView.this.projectMemberReadViewLayout
							.setTitleIcon(UserAvatarControlFactory
									.createAvatarResource(
											ReadView.this.projectMember
													.getMemberAvatarId(), 24));
					ReadView.this.standupReportViewLayout.removeAllComponents();
					ReadView.this.assignmentViewLayout.removeAllComponents();
					ReadView.this.basicInformationLayout.removeAllComponents();
					ReadView.this.standupReportViewLayout
							.addComponent(new UserStandupReportDepot());
					final HorizontalLayout controlButtons = (new ProjectPreviewFormControlsGenerator<ProjectMember>(
							ReadView.this.previewForm))
							.createButtonControls(ProjectRolePermissionCollections.USERS);
					controlButtons.addStyleName("control-buttons");
					controlButtons.setMargin(true);
					ReadView.this.basicInformationLayout
							.addComponent(controlButtons);
					ReadView.this.basicInformationLayout
							.addComponent(ReadView.this.previewForm);
					ReadView.this.basicInformationLayout
							.addComponent(new UserActivityStreamDepot());
					ReadView.this.assignmentViewLayout
							.addComponent(new UserTaskDepot());
					ReadView.this.assignmentViewLayout
							.addComponent(new UserBugDepot());
				}

				@Override
				protected void doPrint() {
					// Create a window that contains what you want to print
					final Window window = new Window("Window to Print");

					final ProjectMemberPreviewBuilder printView = new ProjectMemberPreviewBuilder.PrintView();
					printView.previewItem(ReadView.this.projectMember);
					window.addComponent(printView);

					// Add the printing window as a new application-level window
					this.getApplication().addWindow(window);

					// Open it as a popup window with no decorations
					this.getWindow().open(
							new ExternalResource(window.getURL()), "_blank",
							1100, 200, // Width and height
							Window.BORDER_NONE); // No decorations

					// Print automatically when the window opens.
					// This call will block until the print dialog exits!
					window.executeJavaScript("print();");

					// Close the window automatically after printing
					window.executeJavaScript("self.close();");
				}

				@Override
				protected void showHistory() {
					final ProjectMemberHistoryLogWindow historyLog = new ProjectMemberHistoryLogWindow(
							ModuleNameConstants.PRJ,
							ProjectContants.PROJECT_MEMBER,
							ReadView.this.projectMember.getId());
					this.getWindow().addWindow(historyLog);
				}

			};

			// this.basicInformationLayout.addComponent(this.previewForm);
			this.basicInformationLayout.addStyleName("main-info");

			this.projectMemberReadViewLayout.addTab(
					this.basicInformationLayout, "Basic Information");

			this.projectMemberReadViewLayout.addTab(
					this.standupReportViewLayout, "Standup Reports");

			this.projectMemberReadViewLayout.addTab(this.assignmentViewLayout,
					"Assignments");

		}
	}

	public void previewItem(final SimpleProjectMember item) {
		this.projectMember = item;
		this.previewForm.setItemDataSource(new BeanItem<ProjectMember>(
				this.projectMember));
	}

	public SimpleProjectMember getProjectMember() {
		return this.projectMember;
	}

	public AdvancedPreviewBeanForm<ProjectMember> getPreviewForm() {
		return this.previewForm;
	}

	protected class UserTaskDepot extends Depot {
		private static final long serialVersionUID = 1L;

		private PopupButton taskListFilterControl;
		private TaskTableDisplay taskDisplay;

		private TaskSearchCriteria taskSearchCriteria;

		public UserTaskDepot() {
			super("Tasks", new HorizontalLayout(), new VerticalLayout());

			this.taskDisplay = new TaskTableDisplay(
					new String[] { "id", "taskname", "startdate", "deadline",
							"percentagecomplete" },
					new String[] {
							"",
							LocalizationHelper
									.getMessage(TaskI18nEnum.TABLE_TASK_NAME_HEADER),
							LocalizationHelper
									.getMessage(TaskI18nEnum.TABLE_START_DATE_HEADER),
							LocalizationHelper
									.getMessage(TaskI18nEnum.TABLE_DUE_DATE_HEADER),
							LocalizationHelper
									.getMessage(TaskI18nEnum.TABLE_PER_COMPLETE_HEADER) });

			this.taskDisplay
					.addTableListener(new ApplicationEventListener<TableClickEvent>() {
						private static final long serialVersionUID = 1L;

						@Override
						public Class<? extends ApplicationEvent> getEventType() {
							return TableClickEvent.class;
						}

						@Override
						public void handle(final TableClickEvent event) {
							final SimpleTask task = (SimpleTask) event
									.getData();
							if ("taskname".equals(event.getFieldName())) {
								EventBus.getInstance()
										.fireEvent(
												new TaskEvent.GotoRead(
														ProjectMemberPreviewBuilder.this,
														task.getId()));
							} else if ("closeTask".equals(event.getFieldName())
									|| "reopenTask".equals(event.getFieldName())
									|| "pendingTask".equals(event
											.getFieldName())
									|| "reopenTask".equals(event.getFieldName())
									|| "deleteTask".equals(event.getFieldName())) {

								UserTaskDepot.this.taskDisplay
										.setSearchCriteria(UserTaskDepot.this.taskSearchCriteria);
							}
						}
					});

			this.bodyContent.addComponent(this.taskDisplay);
			this.initHeader();
			this.displayActiveTasksOnly();
		}

		private void initHeader() {
			final HorizontalLayout headerLayout = (HorizontalLayout) this.headerContent;
			headerLayout.setSpacing(true);

			this.taskListFilterControl = new PopupButton("Active Tasks");
			this.taskListFilterControl.addStyleName("link");

			final VerticalLayout filterBtnLayout = new VerticalLayout();
			filterBtnLayout.setMargin(true);
			filterBtnLayout.setSpacing(true);
			filterBtnLayout.setWidth("200px");

			final Button allTasksFilterBtn = new Button("All Tasks",
					new Button.ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(final ClickEvent event) {
							UserTaskDepot.this.taskListFilterControl
									.setPopupVisible(false);
							UserTaskDepot.this.taskListFilterControl
									.setCaption("All Tasks");
							UserTaskDepot.this.displayAllTasks();
						}
					});
			allTasksFilterBtn.setStyleName("link");
			filterBtnLayout.addComponent(allTasksFilterBtn);

			final Button activeTasksFilterBtn = new Button("Active Tasks Only",
					new Button.ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(final ClickEvent event) {
							UserTaskDepot.this.taskListFilterControl
									.setPopupVisible(false);
							UserTaskDepot.this.taskListFilterControl
									.setCaption("Active Tasks");
							UserTaskDepot.this.displayActiveTasksOnly();
						}
					});
			activeTasksFilterBtn.setStyleName("link");
			filterBtnLayout.addComponent(activeTasksFilterBtn);

			final Button pendingTasksFilterBtn = new Button(
					"Pending Tasks Only", new Button.ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(final ClickEvent event) {
							UserTaskDepot.this.taskListFilterControl
									.setPopupVisible(false);
							UserTaskDepot.this.taskListFilterControl
									.setCaption("Pending Tasks");
							UserTaskDepot.this.displayPendingTasksOnly();
						}
					});
			pendingTasksFilterBtn.setStyleName("link");
			filterBtnLayout.addComponent(pendingTasksFilterBtn);

			final Button archievedTasksFilterBtn = new Button(
					"Archieved Tasks Only", new Button.ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(final ClickEvent event) {
							UserTaskDepot.this.taskListFilterControl
									.setCaption("Archieved Tasks");
							UserTaskDepot.this.taskListFilterControl
									.setPopupVisible(false);
							UserTaskDepot.this.displayInActiveTasks();
						}
					});
			archievedTasksFilterBtn.setStyleName("link");
			filterBtnLayout.addComponent(archievedTasksFilterBtn);
			this.taskListFilterControl.addComponent(filterBtnLayout);
			headerLayout.addComponent(this.taskListFilterControl);
		}

		private TaskSearchCriteria createBaseSearchCriteria() {
			final TaskSearchCriteria criteria = new TaskSearchCriteria();
			criteria.setProjectid(new NumberSearchField(CurrentProjectVariables
					.getProjectId()));
			criteria.setAssignUser(new StringSearchField(
					ProjectMemberPreviewBuilder.this.projectMember
							.getUsername()));
			return criteria;
		}

		private void displayActiveTasksOnly() {
			this.taskSearchCriteria = this.createBaseSearchCriteria();
			this.taskSearchCriteria.setStatuses(new SetSearchField<String>(
					SearchField.AND, new String[] { "Open" }));
			this.taskDisplay.setSearchCriteria(this.taskSearchCriteria);
		}

		private void displayPendingTasksOnly() {
			this.taskSearchCriteria = this.createBaseSearchCriteria();
			this.taskSearchCriteria.setStatuses(new SetSearchField<String>(
					SearchField.AND, new String[] { "Pending" }));
			this.taskDisplay.setSearchCriteria(this.taskSearchCriteria);
		}

		private void displayAllTasks() {
			this.taskSearchCriteria = this.createBaseSearchCriteria();
			this.taskSearchCriteria.setStatuses(new SetSearchField<String>(
					SearchField.AND,
					new String[] { "Open", "Pending", "Closed" }));
			this.taskDisplay.setSearchCriteria(this.taskSearchCriteria);
		}

		private void displayInActiveTasks() {
			this.taskSearchCriteria = this.createBaseSearchCriteria();
			this.taskSearchCriteria.setStatuses(new SetSearchField<String>(
					SearchField.AND, new String[] { "Closed" }));
			this.taskDisplay.setSearchCriteria(this.taskSearchCriteria);
		}
	}

	protected class UserBugDepot extends Depot {
		private static final long serialVersionUID = 1L;
		private PopupButton bugActionControl;
		private BugTableDisplay bugDisplay;

		public UserBugDepot() {
			super("Bugs", new HorizontalLayout(), new VerticalLayout());

			this.bugDisplay = new BugTableDisplay(BugTableFieldDef.action,
					Arrays.asList(BugTableFieldDef.summary,
							BugTableFieldDef.severity,
							BugTableFieldDef.resolution,
							BugTableFieldDef.duedate));

			this.bugDisplay
					.addTableListener(new ApplicationEventListener<TableClickEvent>() {
						private static final long serialVersionUID = 1L;

						@Override
						public Class<? extends ApplicationEvent> getEventType() {
							return TableClickEvent.class;
						}

						@Override
						public void handle(final TableClickEvent event) {
							final SimpleBug bug = (SimpleBug) event.getData();
							if ("summary".equals(event.getFieldName())) {
								EventBus.getInstance()
										.fireEvent(
												new BugEvent.GotoRead(
														ProjectMemberPreviewBuilder.this,
														bug.getId()));
							}
						}
					});

			this.bodyContent.addComponent(this.bugDisplay);

			this.initHeader();

			this.displayOpenBugs();
		}

		private void initHeader() {
			final HorizontalLayout headerLayout = (HorizontalLayout) this.headerContent;
			headerLayout.setSpacing(true);

			this.bugActionControl = new PopupButton("Open Bugs");
			this.bugActionControl.addStyleName("link");
			headerLayout.addComponent(this.bugActionControl);

			final VerticalLayout actionBtnLayout = new VerticalLayout();
			actionBtnLayout.setMargin(true);
			actionBtnLayout.setSpacing(true);
			actionBtnLayout.setWidth("200px");
			this.bugActionControl.addComponent(actionBtnLayout);

			final Button openBugBtn = new Button("Open Bugs",
					new Button.ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(final ClickEvent event) {
							UserBugDepot.this.bugActionControl
									.setPopupVisible(false);
							UserBugDepot.this.bugActionControl
									.setCaption("Open Bugs");
							UserBugDepot.this.displayOpenBugs();
						}
					});
			openBugBtn.setEnabled(CurrentProjectVariables
					.canRead(ProjectRolePermissionCollections.BUGS));
			openBugBtn.setStyleName("link");
			actionBtnLayout.addComponent(openBugBtn);

			final Button pendingBugBtn = new Button("Pending Bugs",
					new Button.ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(final ClickEvent event) {
							UserBugDepot.this.bugActionControl
									.setPopupVisible(false);
							UserBugDepot.this.bugActionControl
									.setCaption("Pending Bugs");
							UserBugDepot.this.displayPendingBugs();
						}
					});
			pendingBugBtn.setEnabled(CurrentProjectVariables
					.canRead(ProjectRolePermissionCollections.BUGS));
			pendingBugBtn.setStyleName("link");
			actionBtnLayout.addComponent(pendingBugBtn);

			final Button closeBugBtn = new Button("Closed Bug",
					new Button.ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(final ClickEvent event) {
							UserBugDepot.this.bugActionControl
									.setPopupVisible(false);
							UserBugDepot.this.bugActionControl
									.setCaption("Closed Bug");
							UserBugDepot.this.displayClosedBugs();
						}
					});
			closeBugBtn.setEnabled(CurrentProjectVariables
					.canWrite(ProjectRolePermissionCollections.BUGS));
			closeBugBtn.setStyleName("link");
			actionBtnLayout.addComponent(closeBugBtn);
		}

		private BugSearchCriteria createBugSearchCriteria() {
			final BugSearchCriteria criteria = new BugSearchCriteria();
			criteria.setProjectId(new NumberSearchField(CurrentProjectVariables
					.getProjectId()));
			criteria.setAssignuser(new StringSearchField(
					ProjectMemberPreviewBuilder.this.projectMember
							.getUsername()));
			return criteria;
		}

		private void displayOpenBugs() {
			final BugSearchCriteria criteria = this.createBugSearchCriteria();
			criteria.setStatuses(new SetSearchField<String>(SearchField.AND,
					new String[] { BugStatusConstants.INPROGRESS,
							BugStatusConstants.OPEN,
							BugStatusConstants.REOPENNED }));
			this.bugDisplay.setSearchCriteria(criteria);
		}

		private void displayPendingBugs() {
			final BugSearchCriteria criteria = this.createBugSearchCriteria();
			criteria.setStatuses(new SetSearchField<String>(SearchField.AND,
					new String[] { BugStatusConstants.TESTPENDING }));
			this.bugDisplay.setSearchCriteria(criteria);
		}

		private void displayClosedBugs() {
			final BugSearchCriteria criteria = this.createBugSearchCriteria();
			criteria.setStatuses(new SetSearchField<String>(SearchField.AND,
					new String[] { BugStatusConstants.CLOSE,
							BugStatusConstants.WONFIX }));
			this.bugDisplay.setSearchCriteria(criteria);
		}
	}

	protected class UserStandupReportDepot extends VerticalLayout {
		private static final long serialVersionUID = 1L;

		public UserStandupReportDepot() {
			super();

			final StandupReportListDisplay standupReportListDisplay = new StandupReportListDisplay();
			final StandupReportSearchCriteria searchCriteria = new StandupReportSearchCriteria();
			searchCriteria.setProjectId(new NumberSearchField(
					CurrentProjectVariables.getProjectId()));
			searchCriteria.setLogBy(new StringSearchField(
					ProjectMemberPreviewBuilder.this.projectMember
							.getUsername()));
			standupReportListDisplay.setSearchCriteria(searchCriteria);
			this.addComponent(standupReportListDisplay);
		}
	}

	protected class UserActivityStreamDepot extends Depot {
		private static final long serialVersionUID = 1L;

		private ProjectActivityStreamPagedList activityStreamList;

		public UserActivityStreamDepot() {
			super("User Feeds", new VerticalLayout());

			activityStreamList = new ProjectActivityStreamPagedList();
			displayActivityStream();
		}

		private void displayActivityStream() {
			this.bodyContent.removeAllComponents();
			this.bodyContent.addComponent(new LazyLoadWrapper(
					this.activityStreamList));
			ActivityStreamSearchCriteria searchCriteria = new ActivityStreamSearchCriteria();
			searchCriteria.setModuleSet(new SetSearchField<String>(
					SearchField.AND, new String[] { ModuleNameConstants.PRJ }));
			searchCriteria.setCreatedUser(new StringSearchField(
					SearchField.AND, projectMember.getUsername()));
			searchCriteria.setExtraTypeIds(new SetSearchField<Integer>(
					CurrentProjectVariables.getProjectId()));
			this.activityStreamList.setSearchCriteria(searchCriteria);
		}
	}
}
