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
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.domain.SimpleProjectMember;
import com.esofthead.mycollab.module.project.domain.SimpleTask;
import com.esofthead.mycollab.module.project.domain.criteria.StandupReportSearchCriteria;
import com.esofthead.mycollab.module.project.domain.criteria.TaskSearchCriteria;
import com.esofthead.mycollab.module.project.events.BugEvent;
import com.esofthead.mycollab.module.project.events.ProjectRoleEvent;
import com.esofthead.mycollab.module.project.events.TaskEvent;
import com.esofthead.mycollab.module.project.localization.TaskI18nEnum;
import com.esofthead.mycollab.module.project.ui.components.AbstractPreviewItemComp;
import com.esofthead.mycollab.module.project.view.bug.BugTableDisplay;
import com.esofthead.mycollab.module.project.view.bug.BugTableFieldDef;
import com.esofthead.mycollab.module.project.view.standup.StandupReportListDisplay;
import com.esofthead.mycollab.module.project.view.task.TaskTableDisplay;
import com.esofthead.mycollab.module.project.view.user.ProjectActivityStreamPagedList;
import com.esofthead.mycollab.module.tracker.BugStatusConstants;
import com.esofthead.mycollab.module.tracker.domain.SimpleBug;
import com.esofthead.mycollab.module.tracker.domain.criteria.BugSearchCriteria;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.ui.AbstractBeanFieldGroupViewFieldFactory;
import com.esofthead.mycollab.vaadin.ui.DefaultFormViewFieldFactory;
import com.esofthead.mycollab.vaadin.ui.DefaultFormViewFieldFactory.FormLinkViewField;
import com.esofthead.mycollab.vaadin.ui.DefaultFormViewFieldFactory.UserLinkViewField;
import com.esofthead.mycollab.vaadin.ui.Depot;
import com.esofthead.mycollab.vaadin.ui.GenericBeanForm;
import com.esofthead.mycollab.vaadin.ui.IFormLayoutFactory;
import com.esofthead.mycollab.vaadin.ui.table.TableClickEvent;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * @author MyCollab Ltd.
 * @since 3.0
 * 
 */
abstract class AbstractMemberPreviewComp extends
		AbstractPreviewItemComp<SimpleProjectMember> {
	private static final long serialVersionUID = 1L;

	protected UserActivityStream userActivityComp;

	protected UserTaskComp userTaskComp;

	protected UserBugComp userBugComp;

	protected UserStandupReportDepot standupComp;

	public AbstractMemberPreviewComp() {
		super(null);
		// super(UserAvatarControlFactory.createAvatarResource(
		// PrintView.this.projectMember
		// .getMemberAvatarId(), 24));
	}

	@Override
	protected void initRelatedComponents() {
		userActivityComp = new UserActivityStream();
		userTaskComp = new UserTaskComp();
		userBugComp = new UserBugComp();
		standupComp = new UserStandupReportDepot();

	}

	@Override
	protected void onPreviewItem() {
		userActivityComp.displayActivityStream();
		userTaskComp.displayActiveTasksOnly();
		userBugComp.displayOpenBugs();
		standupComp.displayStandupReports();
	}

	@Override
	protected String initFormTitle() {
		return beanItem.getMemberFullName();
	}

	@Override
	protected IFormLayoutFactory initFormLayoutFactory() {
		return new ProjectMemberFormLayoutFactory();
	}

	@Override
	protected AbstractBeanFieldGroupViewFieldFactory<SimpleProjectMember> initBeanFormFieldFactory() {
		return new ProjectMemberFormFieldFactory(previewForm);
	}

	protected class ProjectMemberFormFieldFactory extends
			AbstractBeanFieldGroupViewFieldFactory<SimpleProjectMember> {

		private static final long serialVersionUID = 1L;

		public ProjectMemberFormFieldFactory(
				GenericBeanForm<SimpleProjectMember> form) {
			super(form);
		}

		@Override
		protected Field<?> onCreateField(final Object propertyId) {
			if (propertyId.equals("isadmin")) {
				if (attachForm.getBean().getIsadmin() != null
						&& (attachForm.getBean().getIsadmin() == Boolean.FALSE)) {
					FormLinkViewField roleLink = new FormLinkViewField(
							attachForm.getBean().getRoleName(),
							new Button.ClickListener() {
								private static final long serialVersionUID = 1L;

								@Override
								public void buttonClick(ClickEvent event) {
									EventBus.getInstance()
											.fireEvent(
													new ProjectRoleEvent.GotoRead(
															ProjectMemberFormFieldFactory.this,
															attachForm
																	.getBean()
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
						attachForm.getBean().getUsername(), attachForm
								.getBean().getMemberAvatarId(), attachForm
								.getBean().getMemberFullName());
			}
			return null;
		}
	}

	protected class UserTaskComp extends VerticalLayout {
		private static final long serialVersionUID = 1L;

		private PopupButton taskListFilterControl;
		private TaskTableDisplay taskDisplay;

		private TaskSearchCriteria taskSearchCriteria;

		public UserTaskComp() {
			super();

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
								EventBus.getInstance().fireEvent(
										new TaskEvent.GotoRead(
												AbstractMemberPreviewComp.this,
												task.getId()));
							} else if ("closeTask".equals(event.getFieldName())
									|| "reopenTask".equals(event.getFieldName())
									|| "pendingTask".equals(event
											.getFieldName())
									|| "reopenTask".equals(event.getFieldName())
									|| "deleteTask".equals(event.getFieldName())) {

								UserTaskComp.this.taskDisplay
										.setSearchCriteria(UserTaskComp.this.taskSearchCriteria);
							}
						}
					});

			this.initHeader();
			this.addComponent(this.taskDisplay);
		}

		private void initHeader() {
			final HorizontalLayout headerLayout = new HorizontalLayout();
			headerLayout.setMargin(true);
			headerLayout.setStyleName("comp-header");

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
							UserTaskComp.this.taskListFilterControl
									.setPopupVisible(false);
							UserTaskComp.this.taskListFilterControl
									.setCaption("All Tasks");
							UserTaskComp.this.displayAllTasks();
						}
					});
			allTasksFilterBtn.setStyleName("link");
			filterBtnLayout.addComponent(allTasksFilterBtn);

			final Button activeTasksFilterBtn = new Button("Active Tasks Only",
					new Button.ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(final ClickEvent event) {
							UserTaskComp.this.taskListFilterControl
									.setPopupVisible(false);
							UserTaskComp.this.taskListFilterControl
									.setCaption("Active Tasks");
							UserTaskComp.this.displayActiveTasksOnly();
						}
					});
			activeTasksFilterBtn.setStyleName("link");
			filterBtnLayout.addComponent(activeTasksFilterBtn);

			final Button pendingTasksFilterBtn = new Button(
					"Pending Tasks Only", new Button.ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(final ClickEvent event) {
							UserTaskComp.this.taskListFilterControl
									.setPopupVisible(false);
							UserTaskComp.this.taskListFilterControl
									.setCaption("Pending Tasks");
							UserTaskComp.this.displayPendingTasksOnly();
						}
					});
			pendingTasksFilterBtn.setStyleName("link");
			filterBtnLayout.addComponent(pendingTasksFilterBtn);

			final Button archievedTasksFilterBtn = new Button(
					"Archived Tasks Only", new Button.ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(final ClickEvent event) {
							UserTaskComp.this.taskListFilterControl
									.setCaption("Archived Tasks");
							UserTaskComp.this.taskListFilterControl
									.setPopupVisible(false);
							UserTaskComp.this.displayInActiveTasks();
						}
					});
			archievedTasksFilterBtn.setStyleName("link");
			filterBtnLayout.addComponent(archievedTasksFilterBtn);
			this.taskListFilterControl.setContent(filterBtnLayout);
			headerLayout.addComponent(this.taskListFilterControl);
			this.addComponent(headerLayout);
		}

		private TaskSearchCriteria createBaseSearchCriteria() {
			final TaskSearchCriteria criteria = new TaskSearchCriteria();
			criteria.setProjectid(new NumberSearchField(CurrentProjectVariables
					.getProjectId()));
			criteria.setAssignUser(new StringSearchField(previewForm.getBean()
					.getUsername()));
			return criteria;
		}

		public void displayActiveTasksOnly() {
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

	protected class UserBugComp extends VerticalLayout {
		private static final long serialVersionUID = 1L;
		private PopupButton bugActionControl;
		private BugTableDisplay bugDisplay;

		public UserBugComp() {
			super();

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
								EventBus.getInstance().fireEvent(
										new BugEvent.GotoRead(
												AbstractMemberPreviewComp.this,
												bug.getId()));
							}
						}
					});
			
			this.initHeader();

			this.addComponent(this.bugDisplay);			
		}

		private void initHeader() {
			final HorizontalLayout headerLayout = new HorizontalLayout();
			headerLayout.setMargin(true);
			headerLayout.setStyleName("comp-header");

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
							UserBugComp.this.bugActionControl
									.setPopupVisible(false);
							UserBugComp.this.bugActionControl
									.setCaption("Open Bugs");
							UserBugComp.this.displayOpenBugs();
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
							UserBugComp.this.bugActionControl
									.setPopupVisible(false);
							UserBugComp.this.bugActionControl
									.setCaption("Resolved Bugs");
							UserBugComp.this.displayResolvedBugs();
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
							UserBugComp.this.bugActionControl
									.setPopupVisible(false);
							UserBugComp.this.bugActionControl
									.setCaption("Verified Bugs");
							UserBugComp.this.displayClosedBugs();
						}
					});
			closeBugBtn.setEnabled(CurrentProjectVariables
					.canWrite(ProjectRolePermissionCollections.BUGS));
			closeBugBtn.setStyleName("link");
			actionBtnLayout.addComponent(closeBugBtn);
			this.addComponent(headerLayout);
		}

		private BugSearchCriteria createBugSearchCriteria() {
			final BugSearchCriteria criteria = new BugSearchCriteria();
			criteria.setProjectId(new NumberSearchField(CurrentProjectVariables
					.getProjectId()));
			criteria.setAssignuser(new StringSearchField(previewForm.getBean()
					.getUsername()));
			return criteria;
		}

		public void displayOpenBugs() {
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

		private StandupReportListDisplay standupReportListDisplay;

		public UserStandupReportDepot() {
			super();

			standupReportListDisplay = new StandupReportListDisplay();
			this.addComponent(standupReportListDisplay);
		}

		public void displayStandupReports() {
			final StandupReportSearchCriteria searchCriteria = new StandupReportSearchCriteria();
			searchCriteria.setProjectId(new NumberSearchField(
					CurrentProjectVariables.getProjectId()));
			searchCriteria.setLogBy(new StringSearchField(previewForm.getBean()
					.getUsername()));
			standupReportListDisplay.setSearchCriteria(searchCriteria);
		}
	}

	protected class UserActivityStream extends VerticalLayout {
		private static final long serialVersionUID = 1L;

		private ProjectActivityStreamPagedList activityStreamList;

		public UserActivityStream() {
			super();

			activityStreamList = new ProjectActivityStreamPagedList();
		}

		public void displayActivityStream() {
			this.removeAllComponents();
			this.addComponent(this.activityStreamList);
			ActivityStreamSearchCriteria searchCriteria = new ActivityStreamSearchCriteria();
			searchCriteria.setModuleSet(new SetSearchField<String>(
					SearchField.AND, new String[] { ModuleNameConstants.PRJ }));
			searchCriteria.setCreatedUser(new StringSearchField(
					SearchField.AND, previewForm.getBean().getUsername()));
			searchCriteria.setExtraTypeIds(new SetSearchField<Integer>(
					CurrentProjectVariables.getProjectId()));
			searchCriteria.setSaccountid(new NumberSearchField(AppContext
					.getAccountId()));
			this.activityStreamList.setSearchCriteria(searchCriteria);
		}
	}
}
