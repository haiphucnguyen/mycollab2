/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.people;

import org.vaadin.hene.popupbutton.PopupButton;

import com.esofthead.mycollab.common.ModuleNameConstants;
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
import com.esofthead.mycollab.module.project.events.ProjectRoleEvent;
import com.esofthead.mycollab.module.project.events.TaskEvent;
import com.esofthead.mycollab.module.project.view.bug.BugTableDisplay;
import com.esofthead.mycollab.module.project.view.standup.StandupReportListDisplay;
import com.esofthead.mycollab.module.project.view.task.TaskTableDisplay;
import com.esofthead.mycollab.module.tracker.BugStatusConstants;
import com.esofthead.mycollab.module.tracker.domain.SimpleBug;
import com.esofthead.mycollab.module.tracker.domain.criteria.BugSearchCriteria;
import com.esofthead.mycollab.vaadin.events.ApplicationEvent;
import com.esofthead.mycollab.vaadin.events.ApplicationEventListener;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.events.HasPreviewFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.AdvancedPreviewBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultFormViewFieldFactory;
import com.esofthead.mycollab.vaadin.ui.Depot;
import com.esofthead.mycollab.vaadin.ui.PreviewFormControlsGenerator;
import com.esofthead.mycollab.vaadin.ui.UserAvatarControlFactory;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.esofthead.mycollab.vaadin.ui.table.TableClickEvent;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
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

/**
 * 
 * @author haiphucnguyen
 */
@ViewComponent
public class ProjectMemberReadViewImpl extends AbstractView implements
		ProjectMemberReadView {

	private static final long serialVersionUID = 1L;
	protected SimpleProjectMember projectMember;
	protected AdvancedPreviewBeanForm<ProjectMember> previewForm;

	public ProjectMemberReadViewImpl() {
		super();
		this.setMargin(false, true, true, true);
		previewForm = new PreviewForm();
		this.addComponent(previewForm);
	}

	@Override
	public void previewItem(SimpleProjectMember item) {
		projectMember = item;
		previewForm.setItemDataSource(new BeanItem<ProjectMember>(item));
	}

	@Override
	public SimpleProjectMember getItem() {
		return projectMember;
	}

	@Override
	public HasPreviewFormHandlers<ProjectMember> getPreviewFormHandlers() {
		return previewForm;
	}

	private class PreviewForm extends AdvancedPreviewBeanForm<ProjectMember> {

		private static final long serialVersionUID = 1L;

		@Override
		public void setItemDataSource(Item newDataSource) {
			this.setFormLayoutFactory(new FormLayoutFactory());
			this.setFormFieldFactory(new DefaultFormViewFieldFactory() {
				private static final long serialVersionUID = 1L;

				@Override
				protected Field onCreateField(Item item, Object propertyId,
						Component uiContext) {
					if (propertyId.equals("isadmin")) {
						if (projectMember.getIsadmin() != null
								&& projectMember.getIsadmin() == Boolean.FALSE) {
							String role = projectMember.getRoleName();
							HorizontalLayout layout = new HorizontalLayout();
							layout.setSpacing(true);
							layout.addComponent(new Label("False"));
							layout.addComponent(new Label("Role: "));
							Button roleBtn = new Button(role,
									new Button.ClickListener() {
										private static final long serialVersionUID = 1L;

										@Override
										public void buttonClick(ClickEvent event) {
											EventBus.getInstance()
													.fireEvent(
															new ProjectRoleEvent.GotoRead(
																	PreviewForm.this,
																	projectMember
																			.getProjectroleid()));

										}
									});
							roleBtn.setStyleName("link");
							layout.addComponent(roleBtn);
							return new DefaultFormViewFieldFactory.FormContainerField(
									layout);
						}
					} else if (propertyId.equals("username")) {
						return new FormViewField(projectMember
								.getMemberFullName());
					}
					return null;
				}
			});
			super.setItemDataSource(newDataSource);
		}

		@Override
		protected void doPrint() {
			// Create a window that contains what you want to print
			Window window = new Window("Window to Print");

			ProjectMemberReadViewImpl printView = new ProjectMemberReadViewImpl.PrintView();
			printView.previewItem(projectMember);
			window.addComponent(printView);

			// Add the printing window as a new application-level window
			getApplication().addWindow(window);

			// Open it as a popup window with no decorations
			getWindow().open(new ExternalResource(window.getURL()), "_blank",
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
			ProjectMemberHistoryLogWindow historyLog = new ProjectMemberHistoryLogWindow(
					ModuleNameConstants.PRJ, ProjectContants.PROJECT_MEMBER,
					projectMember.getId());
			getWindow().addWindow(historyLog);
		}

		class FormLayoutFactory extends ProjectMemberFormLayoutFactory {

			private static final long serialVersionUID = 1L;

			public FormLayoutFactory() {
				super(projectMember.getMemberFullName(),
						UserAvatarControlFactory.getResource(
								projectMember.getUsername(), 48));
			}

			@Override
			protected Layout createTopPanel() {
				return (new PreviewFormControlsGenerator<ProjectMember>(
						PreviewForm.this)).createButtonControls(
						ProjectRolePermissionCollections.USERS,
						ModuleNameConstants.PRJ);
			}

			@Override
			protected Layout createBottomPanel() {
				VerticalLayout relatedItemsPanel = new VerticalLayout();
				relatedItemsPanel.addComponent(new UserTaskDepot());
				relatedItemsPanel.addComponent(new UserBugDepot());
				relatedItemsPanel.addComponent(new UserStandupReportDepot());
				return relatedItemsPanel;
			}
		}
	}

	private class UserTaskDepot extends Depot {
		private static final long serialVersionUID = 1L;

		private PopupButton taskListFilterControl;
		private TaskTableDisplay taskDisplay;

		public UserTaskDepot() {
			super("Tasks", new HorizontalLayout(), new VerticalLayout());

			taskDisplay = new TaskTableDisplay(
					new String[] { "id", "taskkey", "taskname", "startdate",
							"deadline", "percentagecomplete" },
					new String[] { "", "#", "Task Name", "Start", "Due",
							"% Complete" });

			taskDisplay
					.addTableListener(new ApplicationEventListener<TableClickEvent>() {
						private static final long serialVersionUID = 1L;

						@Override
						public Class<? extends ApplicationEvent> getEventType() {
							return TableClickEvent.class;
						}

						@Override
						public void handle(TableClickEvent event) {
							SimpleTask task = (SimpleTask) event.getData();
							if ("taskname".equals(event.getFieldName())) {
								EventBus.getInstance().fireEvent(
										new TaskEvent.GotoRead(
												ProjectMemberReadViewImpl.this,
												task.getId()));
							} else if ("closeTask".equals(event.getFieldName())
									|| "reopenTask".equals(event.getFieldName())
									|| "pendingTask".equals(event
											.getFieldName())
									|| "reopenTask".equals(event.getFieldName())
									|| "deleteTask".equals(event.getFieldName())) {

								// taskDisplay.setSearchCriteria(searchCriteria);
							}
						}
					});

			bodyContent.addComponent(taskDisplay);
			initHeader();
			displayActiveTasksOnly();
		}

		private void initHeader() {
			HorizontalLayout headerLayout = (HorizontalLayout) this.headerContent;
			headerLayout.setSpacing(true);

			taskListFilterControl = new PopupButton("Active Tasks");
			taskListFilterControl.setWidth("120px");
			taskListFilterControl.addStyleName("link");

			VerticalLayout filterBtnLayout = new VerticalLayout();
			filterBtnLayout.setMargin(true);
			filterBtnLayout.setSpacing(true);
			filterBtnLayout.setWidth("200px");

			Button allTasksFilterBtn = new Button("All Tasks",
					new Button.ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(ClickEvent event) {
							taskListFilterControl.setPopupVisible(false);
							taskListFilterControl.setCaption("All Tasks");
							displayAllTasks();
						}
					});
			allTasksFilterBtn.setStyleName("link");
			filterBtnLayout.addComponent(allTasksFilterBtn);

			Button activeTasksFilterBtn = new Button("Active Tasks Only",
					new Button.ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(ClickEvent event) {
							taskListFilterControl.setPopupVisible(false);
							taskListFilterControl.setCaption("Active Tasks");
							displayActiveTasksOnly();
						}
					});
			activeTasksFilterBtn.setStyleName("link");
			filterBtnLayout.addComponent(activeTasksFilterBtn);

			Button archievedTasksFilterBtn = new Button("Archieved Tasks Only",
					new Button.ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(ClickEvent event) {
							taskListFilterControl.setCaption("Archieved Tasks");
							taskListFilterControl.setPopupVisible(false);
							displayInActiveTasks();
						}
					});
			archievedTasksFilterBtn.setStyleName("link");
			filterBtnLayout.addComponent(archievedTasksFilterBtn);
			taskListFilterControl.addComponent(filterBtnLayout);
			headerLayout.addComponent(taskListFilterControl);
		}

		private TaskSearchCriteria createBaseSearchCriteria() {
			TaskSearchCriteria criteria = new TaskSearchCriteria();
			criteria.setProjectid(new NumberSearchField(CurrentProjectVariables
					.getProjectId()));
			criteria.setAssignUser(new StringSearchField(AppContext
					.getUsername()));
			return criteria;
		}

		private void displayActiveTasksOnly() {
			TaskSearchCriteria criteria = createBaseSearchCriteria();
			criteria.setStatuses(new SetSearchField<String>(SearchField.AND,
					new String[] { "Open", "Pending" }));
			taskDisplay.setSearchCriteria(criteria);
		}

		private void displayAllTasks() {
			TaskSearchCriteria criteria = createBaseSearchCriteria();
			criteria.setStatuses(new SetSearchField<String>(SearchField.AND,
					new String[] { "Open", "Pending", "Closed" }));
			taskDisplay.setSearchCriteria(criteria);
		}

		private void displayInActiveTasks() {
			TaskSearchCriteria criteria = createBaseSearchCriteria();
			criteria.setStatuses(new SetSearchField<String>(SearchField.AND,
					new String[] { "Closed" }));
			taskDisplay.setSearchCriteria(criteria);
		}
	}

	private class UserBugDepot extends Depot {
		private static final long serialVersionUID = 1L;
		private PopupButton bugActionControl;

		public UserBugDepot() {
			super("Bugs", new HorizontalLayout(), new VerticalLayout());

			BugTableDisplay bugDisplay = new BugTableDisplay(new String[] {
					"bugkey", "summary", "severity", "resolution", "duedate" },
					new String[] { "#", "Summary", "Severity", "Resolution",
							"Due Date" });
			bugDisplay
					.addTableListener(new ApplicationEventListener<TableClickEvent>() {
						private static final long serialVersionUID = 1L;

						@Override
						public Class<? extends ApplicationEvent> getEventType() {
							return TableClickEvent.class;
						}

						@Override
						public void handle(TableClickEvent event) {
							SimpleBug bug = (SimpleBug) event.getData();
							if ("summary".equals(event.getFieldName())) {
								EventBus.getInstance().fireEvent(
										new BugEvent.GotoRead(
												ProjectMemberReadViewImpl.this,
												bug.getId()));
							}
						}
					});

			BugSearchCriteria searchCriteria = new BugSearchCriteria();
			searchCriteria.setProjectId(new NumberSearchField(
					CurrentProjectVariables.getProjectId()));
			searchCriteria.setAssignuser(new StringSearchField(projectMember
					.getUsername()));
			searchCriteria.setStatuses(new SetSearchField<String>(
					SearchField.AND, new String[] {
							BugStatusConstants.INPROGRESS,
							BugStatusConstants.OPEN,
							BugStatusConstants.REOPENNED }));
			bugDisplay.setSearchCriteria(searchCriteria);

			this.bodyContent.addComponent(bugDisplay);

			initHeader();
		}

		private void initHeader() {
			HorizontalLayout headerLayout = (HorizontalLayout) this.headerContent;
			headerLayout.setSpacing(true);

			bugActionControl = new PopupButton("Open Bugs");
			bugActionControl.addStyleName("link");
			headerLayout.addComponent(bugActionControl);

			VerticalLayout actionBtnLayout = new VerticalLayout();
			actionBtnLayout.setMargin(true);
			actionBtnLayout.setSpacing(true);
			actionBtnLayout.setWidth("200px");
			bugActionControl.addComponent(actionBtnLayout);

			Button pendingBugBtn = new Button("Pending Bugs",
					new Button.ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(ClickEvent event) {
							bugActionControl.setPopupVisible(false);

						}
					});
			pendingBugBtn.setEnabled(CurrentProjectVariables
					.canRead(ProjectRolePermissionCollections.BUGS));
			pendingBugBtn.setStyleName("link");
			actionBtnLayout.addComponent(pendingBugBtn);

			Button closeBugBtn = new Button("Closed Bug",
					new Button.ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(ClickEvent event) {
							bugActionControl.setPopupVisible(false);
						}
					});
			closeBugBtn.setEnabled(CurrentProjectVariables
					.canWrite(ProjectRolePermissionCollections.BUGS));
			closeBugBtn.setStyleName("link");
			actionBtnLayout.addComponent(closeBugBtn);
		}
	}

	private class UserStandupReportDepot extends Depot {
		private static final long serialVersionUID = 1L;

		public UserStandupReportDepot() {
			super("StandUp Reports", new VerticalLayout());

			StandupReportListDisplay standupReportListDisplay = new StandupReportListDisplay();
			StandupReportSearchCriteria searchCriteria = new StandupReportSearchCriteria();
			searchCriteria.setProjectId(new NumberSearchField(
					CurrentProjectVariables.getProjectId()));
			searchCriteria.setLogBy(new StringSearchField(projectMember
					.getUsername()));
			standupReportListDisplay.setSearchCriteria(searchCriteria);
			bodyContent.addComponent(standupReportListDisplay);
		}
	}

	@SuppressWarnings("serial")
	private class PrintView extends ProjectMemberReadViewImpl {

		public PrintView() {
			previewForm = new AdvancedPreviewBeanForm<ProjectMember>() {
				@Override
				public void setItemDataSource(Item newDataSource) {
					this.setFormLayoutFactory(new FormLayoutFactory());
					this.setFormFieldFactory(new DefaultFormViewFieldFactory() {
						private static final long serialVersionUID = 1L;

						@Override
						protected Field onCreateField(Item item,
								Object propertyId, Component uiContext) {
							if (propertyId.equals("isAdmin")) {
								if (projectMember.getIsadmin() != null
										&& projectMember.getIsadmin() == Boolean.FALSE) {
									HorizontalLayout layout = new HorizontalLayout();
									layout.setSpacing(true);
									layout.addComponent(new Label("False"));
									layout.addComponent(new Label("Role: "
											+ projectMember.getRoleName()));
									return new DefaultFormViewFieldFactory.FormContainerField(
											layout);
								}
							} else if (propertyId.equals("username")) {
								return new FormViewField(projectMember
										.getMemberFullName());
							}
							return null;
						}
					});
					super.setItemDataSource(newDataSource);
				}
			};

			this.addComponent(previewForm);
		}

		class FormLayoutFactory extends ProjectMemberFormLayoutFactory {

			private static final long serialVersionUID = 1L;

			public FormLayoutFactory() {
				super(projectMember.getMemberFullName(),
						UserAvatarControlFactory.getResource(
								projectMember.getUsername(), 48));
			}

			@Override
			protected Layout createTopPanel() {
				return null;
			}

			@Override
			protected Layout createBottomPanel() {
				VerticalLayout relatedItemsPanel = new VerticalLayout();
				relatedItemsPanel.addComponent(new UserTaskDepot());
				relatedItemsPanel.addComponent(new UserBugDepot());
				relatedItemsPanel.addComponent(new UserStandupReportDepot());
				return relatedItemsPanel;
			}
		}
	}
}
