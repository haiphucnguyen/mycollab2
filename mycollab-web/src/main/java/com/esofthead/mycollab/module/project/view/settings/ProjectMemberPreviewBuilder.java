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
package com.esofthead.mycollab.module.project.view.settings;

import java.util.Arrays;

import org.vaadin.hene.popupbutton.PopupButton;

import com.esofthead.mycollab.common.ModuleNameConstants;
import com.esofthead.mycollab.common.domain.criteria.ActivityStreamSearchCriteria;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.eventmanager.ApplicationEvent;
import com.esofthead.mycollab.eventmanager.ApplicationEventListener;
import com.esofthead.mycollab.eventmanager.EventBus;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectContants;
import com.esofthead.mycollab.module.project.ProjectMemberStatusConstants;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.domain.ProjectMember;
import com.esofthead.mycollab.module.project.domain.SimpleProjectMember;
import com.esofthead.mycollab.module.project.domain.SimpleTask;
import com.esofthead.mycollab.module.project.domain.criteria.StandupReportSearchCriteria;
import com.esofthead.mycollab.module.project.domain.criteria.TaskSearchCriteria;
import com.esofthead.mycollab.module.project.events.BugEvent;
import com.esofthead.mycollab.module.project.events.ProjectRoleEvent;
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
import com.esofthead.mycollab.vaadin.ui.AdvancedPreviewBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultFormViewFieldFactory;
import com.esofthead.mycollab.vaadin.ui.Depot;
import com.esofthead.mycollab.vaadin.ui.ProjectPreviewFormControlsGenerator;
import com.esofthead.mycollab.vaadin.ui.ReadViewLayout;
import com.esofthead.mycollab.vaadin.ui.UserAvatarControlFactory;
import com.esofthead.mycollab.vaadin.ui.table.TableClickEvent;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.JavaScript;
import com.vaadin.ui.Layout;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class ProjectMemberPreviewBuilder extends VerticalLayout {
	private static final long serialVersionUID = 1L;
	protected SimpleProjectMember projectMember;
	protected AdvancedPreviewBeanForm<ProjectMember> previewForm;

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

	protected class ProjectMemberFormFieldFactory extends
			DefaultFormViewFieldFactory {
		private static final long serialVersionUID = 1L;

		@Override
		protected Field onCreateField(final Item item, final Object propertyId,
				final Component uiContext) {
			if (propertyId.equals("isadmin")) {
				if (ProjectMemberPreviewBuilder.this.projectMember.getIsadmin() != null
						&& ProjectMemberPreviewBuilder.this.projectMember
								.getIsadmin() == Boolean.FALSE) {
					FormLinkViewField roleLink = new FormLinkViewField(
							ProjectMemberPreviewBuilder.this.projectMember
									.getRoleName(),
							new Button.ClickListener() {
								private static final long serialVersionUID = 1L;

								@Override
								public void buttonClick(ClickEvent event) {
									EventBus.getInstance()
											.fireEvent(
													new ProjectRoleEvent.GotoRead(
															ProjectMemberFormFieldFactory.this,
															projectMember
																	.getProjectroleid()));
								}
							});
					return roleLink;
				} else {
					return new DefaultFormViewFieldFactory.FormViewField(
							"Project Admin");
				}
			} else if (propertyId.equals("username")) {
				return new UserLinkViewField(
						ProjectMemberPreviewBuilder.this.projectMember
								.getUsername(),
						ProjectMemberPreviewBuilder.this.projectMember
								.getMemberAvatarId(),
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

					String formTitle = (ProjectMemberStatusConstants.INACTIVE
							.equals(projectMember.getStatus())) ? (projectMember
							.getMemberFullName() + " (In active)")
							: projectMember.getMemberFullName();
					ReadView.this.projectMemberReadViewLayout
							.setTitle(formTitle);
					ReadView.this.projectMemberReadViewLayout
							.setTitleIcon(UserAvatarControlFactory
									.createAvatarResource(
											projectMember.getMemberAvatarId(),
											24));
					ReadView.this.standupReportViewLayout.removeAllComponents();
					ReadView.this.assignmentViewLayout.removeAllComponents();
					ReadView.this.basicInformationLayout.removeAllComponents();
					ReadView.this.standupReportViewLayout
							.addComponent(new UserStandupReportDepot());

					ProjectPreviewFormControlsGenerator<ProjectMember> previewForm = new ProjectPreviewFormControlsGenerator<ProjectMember>(
							ReadView.this.previewForm);
					previewForm
							.createButtonControls(ProjectRolePermissionCollections.USERS);

					final HorizontalLayout controlButtons = previewForm
							.getLayoutWithRemoveCloneBtn();

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
				public void doPrint() {
					// Create a window that contains what you want to print
					final Window window = new Window("Window to Print");

					final ProjectMemberPreviewBuilder printView = new ProjectMemberPreviewBuilder.PrintView();
					printView.previewItem(ReadView.this.projectMember);
					window.setContent(printView);

					UI.getCurrent().addWindow(window);

					// Print automatically when the window opens
					JavaScript.getCurrent().execute(
							"setTimeout(function() {"
									+ "  print(); self.close();}, 0);");
				}

				@Override
				public void showHistory() {
					final ProjectMemberHistoryLogWindow historyLog = new ProjectMemberHistoryLogWindow(
							ModuleNameConstants.PRJ,
							ProjectContants.PROJECT_MEMBER,
							ReadView.this.projectMember.getId());
					UI.getCurrent().addWindow(historyLog);
				}

			};

			this.basicInformationLayout.addStyleName("main-info");

			this.projectMemberReadViewLayout.addTab(
					this.basicInformationLayout, "Basic Information");

			this.projectMemberReadViewLayout.addTab(
					this.standupReportViewLayout, "Standup Reports");

			this.projectMemberReadViewLayout.addTab(this.assignmentViewLayout,
					"Assignments");

		}
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
					"Archived Tasks Only", new Button.ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(final ClickEvent event) {
							UserTaskDepot.this.taskListFilterControl
									.setCaption("Archived Tasks");
							UserTaskDepot.this.taskListFilterControl
									.setPopupVisible(false);
							UserTaskDepot.this.displayInActiveTasks();
						}
					});
			archievedTasksFilterBtn.setStyleName("link");
			filterBtnLayout.addComponent(archievedTasksFilterBtn);
			this.taskListFilterControl.setContent(filterBtnLayout);
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
			this.bugActionControl.setContent(actionBtnLayout);

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

			final Button pendingBugBtn = new Button("Resolved Bugs",
					new Button.ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(final ClickEvent event) {
							UserBugDepot.this.bugActionControl
									.setPopupVisible(false);
							UserBugDepot.this.bugActionControl
									.setCaption("Resolved Bugs");
							UserBugDepot.this.displayResolvedBugs();
						}
					});
			pendingBugBtn.setEnabled(CurrentProjectVariables
					.canRead(ProjectRolePermissionCollections.BUGS));
			pendingBugBtn.setStyleName("link");
			actionBtnLayout.addComponent(pendingBugBtn);

			final Button closeBugBtn = new Button("Verified Bugs",
					new Button.ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(final ClickEvent event) {
							UserBugDepot.this.bugActionControl
									.setPopupVisible(false);
							UserBugDepot.this.bugActionControl
									.setCaption("Verified Bugs");
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

		private void displayResolvedBugs() {
			final BugSearchCriteria criteria = this.createBugSearchCriteria();
			criteria.setStatuses(new SetSearchField<String>(SearchField.AND,
					new String[] { BugStatusConstants.RESOLVED }));
			this.bugDisplay.setSearchCriteria(criteria);
		}

		private void displayClosedBugs() {
			final BugSearchCriteria criteria = this.createBugSearchCriteria();
			criteria.setStatuses(new SetSearchField<String>(SearchField.AND,
					new String[] { BugStatusConstants.VERIFIED }));
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
			this.bodyContent.addComponent(this.activityStreamList);
			ActivityStreamSearchCriteria searchCriteria = new ActivityStreamSearchCriteria();
			searchCriteria.setModuleSet(new SetSearchField<String>(
					SearchField.AND, new String[] { ModuleNameConstants.PRJ }));
			searchCriteria.setCreatedUser(new StringSearchField(
					SearchField.AND, projectMember.getUsername()));
			searchCriteria.setExtraTypeIds(new SetSearchField<Integer>(
					CurrentProjectVariables.getProjectId()));
			searchCriteria.setSaccountid(new NumberSearchField(AppContext
					.getAccountId()));
			this.activityStreamList.setSearchCriteria(searchCriteria);
		}
	}
}
