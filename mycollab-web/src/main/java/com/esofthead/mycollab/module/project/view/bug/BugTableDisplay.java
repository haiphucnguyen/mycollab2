/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.bug;

import java.util.GregorianCalendar;
import java.util.List;

import org.vaadin.dialogs.ConfirmDialog;
import org.vaadin.hene.popupbutton.PopupButton;
import org.vaadin.peter.contextmenu.ContextMenu;
import org.vaadin.peter.contextmenu.ContextMenu.ContextMenuItem;

import com.esofthead.mycollab.common.ApplicationProperties;
import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.core.utils.StringUtil;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectDataTypeFactory;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.events.BugEvent;
import com.esofthead.mycollab.module.project.view.people.component.ProjectUserLink;
import com.esofthead.mycollab.module.tracker.BugStatusConstants;
import com.esofthead.mycollab.module.tracker.domain.SimpleBug;
import com.esofthead.mycollab.module.tracker.domain.criteria.BugSearchCriteria;
import com.esofthead.mycollab.module.tracker.service.BugService;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.ui.ButtonLink;
import com.esofthead.mycollab.vaadin.ui.ConfirmDialogExt;
import com.esofthead.mycollab.vaadin.ui.DefaultFormViewFieldFactory.FormContainerHorizontalViewField;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.table.PagedBeanTable2;
import com.esofthead.mycollab.vaadin.ui.table.TableClickEvent;
import com.esofthead.mycollab.vaadin.ui.table.TableViewField;
import com.esofthead.mycollab.web.AppContext;
import com.esofthead.mycollab.web.LocalizationHelper;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.terminal.Resource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;

/**
 * 
 * @author haiphucnguyen
 */
public class BugTableDisplay extends
		PagedBeanTable2<BugService, BugSearchCriteria, SimpleBug> implements
		IBugCallbackStatusComp {
	private static final long serialVersionUID = 1L;

	public BugTableDisplay(List<TableViewField> displayColumns) {
		this(null, displayColumns);
	}

	public BugTableDisplay(TableViewField requiredColumn,
			List<TableViewField> displayColumns) {
		this(null, requiredColumn, displayColumns);

	}

	public BugTableDisplay(String viewId, TableViewField requiredColumn,
			List<TableViewField> displayColumns) {
		super(AppContext.getSpringBean(BugService.class), SimpleBug.class,
				viewId, requiredColumn, displayColumns);

		this.addGeneratedColumn("id", new Table.ColumnGenerator() {
			private static final long serialVersionUID = 1L;

			@Override
			public Object generateCell(final Table source, final Object itemId,
					Object columnId) {
				final SimpleBug bug = BugTableDisplay.this
						.getBeanByIndex(itemId);
				final PopupButton bugSettingBtn = new PopupButton();
				bugSettingBtn.setIcon(MyCollabResource
						.newResource("icons/16/item_settings.png"));
				bugSettingBtn.setStyleName("link");

				final ContextMenu menu = new ContextMenu();

				menu.addListener(new ContextMenu.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void contextItemClick(
							org.vaadin.peter.contextmenu.ContextMenu.ClickEvent event) {
						bugSettingBtn.setPopupVisible(false);
						String category = event.getClickedItem().getCategory();
						String value = event.getClickedItem().getValue();
						if ("status".equals(category)) {
							if (BugStatusConstants.CLOSE.equals(value)) {
								AppContext
										.getApplication()
										.getMainWindow()
										.addWindow(
												new ApproveInputWindow(
														BugTableDisplay.this,
														bug));
							} else if (BugStatusConstants.INPROGRESS
									.equals(value)) {
								bug.setStatus(BugStatusConstants.INPROGRESS);
								BugService bugService = AppContext
										.getSpringBean(BugService.class);
								bugService.updateWithSession(bug,
										AppContext.getUsername());
							} else if (BugStatusConstants.OPEN.equals(value)) {
								AppContext
										.getApplication()
										.getMainWindow()
										.addWindow(
												new ReOpenWindow(
														BugTableDisplay.this,
														bug));
							} else if (BugStatusConstants.TESTPENDING
									.equals(value)) {
								AppContext
										.getApplication()
										.getMainWindow()
										.addWindow(
												new ResolvedInputWindow(
														BugTableDisplay.this,
														bug));
							}
						} else if ("severity".equals(category)) {
							bug.setSeverity(value);
							BugService bugService = AppContext
									.getSpringBean(BugService.class);
							bugService.updateWithSession(bug,
									AppContext.getUsername());
							refresh();
						} else if ("priority".equals(category)) {
							bug.setPriority(value);
							BugService bugService = AppContext
									.getSpringBean(BugService.class);
							bugService.updateWithSession(bug,
									AppContext.getUsername());
							refresh();
						} else if ("action".equals(category)) {
							if ("edit".equals(value)) {
								EventBus.getInstance().fireEvent(
										new BugEvent.GotoEdit(
												BugTableDisplay.this, bug));
							} else if ("delete".equals(value)) {
								ConfirmDialogExt.show(
										BugTableDisplay.this.getWindow(),
										LocalizationHelper
												.getMessage(
														GenericI18Enum.DELETE_DIALOG_TITLE,
														ApplicationProperties
																.getString(ApplicationProperties.SITE_NAME)),
										LocalizationHelper
												.getMessage(GenericI18Enum.DELETE_SINGLE_ITEM_DIALOG_MESSAGE),
										LocalizationHelper
												.getMessage(GenericI18Enum.BUTTON_YES_LABEL),
										LocalizationHelper
												.getMessage(GenericI18Enum.BUTTON_NO_LABEL),
										new ConfirmDialog.Listener() {
											private static final long serialVersionUID = 1L;

											@Override
											public void onClose(
													ConfirmDialog dialog) {
												if (dialog.isConfirmed()) {
													BugService bugService = AppContext
															.getSpringBean(BugService.class);
													bugService.removeWithSession(
															bug.getId(),
															AppContext
																	.getUsername());
													refresh();
												}
											}
										});

							}
						}

					}

				});
				bugSettingBtn.addComponent(menu);
				bugSettingBtn.setEnabled(CurrentProjectVariables
						.canWrite(ProjectRolePermissionCollections.BUGS));

				bugSettingBtn.addListener(new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(ClickEvent event) {
						menu.show(event.getClientX() - 25, event.getClientY());
						menu.removeAllItems();

						menu.addItem("Edit", "action", "edit");

						ContextMenuItem statusMenuItem = menu.addItem("Status");
						if (BugStatusConstants.OPEN.equals(bug.getStatus())
								|| BugStatusConstants.REOPENNED.equals(bug
										.getStatus())) {
							statusMenuItem.addItem("Start Progress", "status",
									BugStatusConstants.INPROGRESS);
							statusMenuItem.addItem("Resolved", "status",
									BugStatusConstants.TESTPENDING);
							statusMenuItem.addItem("Won't Fix", "status",
									BugStatusConstants.WONFIX);
						} else if (BugStatusConstants.INPROGRESS.equals(bug
								.getStatus())) {
							statusMenuItem.addItem("Stop Progress", "status",
									BugStatusConstants.OPEN);
							statusMenuItem.addItem("Resolved", "status",
									BugStatusConstants.TESTPENDING);
						} else if (BugStatusConstants.CLOSE.equals(bug
								.getStatus())) {
							statusMenuItem.addItem("ReOpen", "status",
									BugStatusConstants.REOPENNED);
						} else if (BugStatusConstants.TESTPENDING.equals(bug
								.getStatus())) {
							statusMenuItem.addItem("ReOpen", "status",
									BugStatusConstants.REOPENNED);
							statusMenuItem.addItem("Approve & Close", "status",
									BugStatusConstants.CLOSE);
						} else if (BugStatusConstants.WONFIX.equals(bug
								.getStatus())) {
							statusMenuItem.addItem("ReOpen", "status",
									BugStatusConstants.REOPENNED);
						}

						// Show bug priority
						ContextMenuItem priorityMenuItem = menu
								.addItem("Priority");
						for (String bugPriority : ProjectDataTypeFactory
								.getBugPriorityList()) {
							ContextMenuItem prioritySubMenuItem = priorityMenuItem
									.addItem(bugPriority, "priority",
											bugPriority);
							if (bugPriority.equals(bug.getPriority())) {
								prioritySubMenuItem.setEnabled(false);
							}
						}

						// Show bug severity
						ContextMenuItem severityMenuItem = menu
								.addItem("Severity");
						for (String bugSeverity : ProjectDataTypeFactory
								.getBugSeverityList()) {
							ContextMenuItem severitySubMenuItem = severityMenuItem
									.addItem(bugSeverity, "severity",
											bugSeverity);
							if (bugSeverity.equals(bug.getSeverity())) {
								severitySubMenuItem.setEnabled(false);
							}
						}

						// Add delete button
						ContextMenuItem deleteMenuItem = menu.addItem("Delete",
								"action", "delete");
						deleteMenuItem.setEnabled(CurrentProjectVariables
								.canAccess(ProjectRolePermissionCollections.BUGS));
					}
				});
				return bugSettingBtn;
			}
		});

		this.addGeneratedColumn("assignuserFullName",
				new Table.ColumnGenerator() {
					private static final long serialVersionUID = 1L;

					@Override
					public com.vaadin.ui.Component generateCell(Table source,
							final Object itemId, Object columnId) {
						final SimpleBug bug = BugTableDisplay.this
								.getBeanByIndex(itemId);
						return new ProjectUserLink(bug.getAssignuser(), bug
								.getAssignUserAvatarId(), bug
								.getAssignuserFullName(), true, true);
					}
				});

		this.addGeneratedColumn("summary", new Table.ColumnGenerator() {
			private static final long serialVersionUID = 1L;

			@Override
			public com.vaadin.ui.Component generateCell(Table source,
					final Object itemId, Object columnId) {

				final SimpleBug bug = BugTableDisplay.this
						.getBeanByIndex(itemId);

				String bugname = "[%s-%s] %s";
				bugname = String.format(bugname, CurrentProjectVariables
						.getProject().getShortname(), bug.getBugkey(), bug
						.getSummary());

				ButtonLink b = new ButtonLink(bugname,
						new Button.ClickListener() {
							private static final long serialVersionUID = 1L;

							@Override
							public void buttonClick(Button.ClickEvent event) {
								fireTableEvent(new TableClickEvent(
										BugTableDisplay.this, bug, "summary"));
							}
						});

				if (StringUtil.isNotNullOrEmpty(bug.getPriority())) {
					Resource iconPriority = MyCollabResource
							.newResource(BugPriorityStatusConstants.PRIORITY_MAJOR_IMG);

					if (BugPriorityStatusConstants.PRIORITY_BLOCKER.equals(bug
							.getPriority())) {
						iconPriority = MyCollabResource
								.newResource(BugPriorityStatusConstants.PRIORITY_BLOCKER_IMG);
					} else if (BugPriorityStatusConstants.PRIORITY_CRITICAL
							.equals(bug.getPriority())) {
						iconPriority = MyCollabResource
								.newResource(BugPriorityStatusConstants.PRIORITY_CRITICAL_IMG);
					} else if (BugPriorityStatusConstants.PRIORITY_MAJOR
							.equals(bug.getPriority())) {
						iconPriority = MyCollabResource
								.newResource(BugPriorityStatusConstants.PRIORITY_MAJOR_IMG);
					} else if (BugPriorityStatusConstants.PRIORITY_MINOR
							.equals(bug.getPriority())) {
						iconPriority = MyCollabResource
								.newResource(BugPriorityStatusConstants.PRIORITY_MINOR_IMG);
					} else if (BugPriorityStatusConstants.PRIORITY_TRIVIAL
							.equals(bug.getPriority())) {
						iconPriority = MyCollabResource
								.newResource(BugPriorityStatusConstants.PRIORITY_TRIVIAL_IMG);
					}

					b.setIcon(iconPriority);
				}

				if (BugStatusConstants.CLOSE.equals(bug.getStatus())) {
					b.addStyleName(UIConstants.LINK_COMPLETED);
				} else if (bug.getDuedate() != null
						&& (bug.getDuedate().before(new GregorianCalendar()
								.getTime()))) {
					b.addStyleName(UIConstants.LINK_OVERDUE);
				}
				return b;

			}
		});

		this.addGeneratedColumn("severity", new Table.ColumnGenerator() {
			private static final long serialVersionUID = 1L;

			@Override
			public com.vaadin.ui.Component generateCell(Table source,
					final Object itemId, Object columnId) {

				final SimpleBug bug = BugTableDisplay.this
						.getBeanByIndex(itemId);

				Resource iconPriority = MyCollabResource
						.newResource(BugSeverityConstants.MAJOR_IMG);
				if (StringUtil.isNotNullOrEmpty(bug.getSeverity())) {

					if (BugSeverityConstants.CRITICAL.equals(bug.getSeverity())) {
						iconPriority = MyCollabResource
								.newResource(BugSeverityConstants.CRITICAL_IMG);
					} else if (BugSeverityConstants.MAJOR.equals(bug
							.getSeverity())) {
						iconPriority = MyCollabResource
								.newResource(BugSeverityConstants.MAJOR_IMG);
					} else if (BugSeverityConstants.MINOR.equals(bug
							.getSeverity())) {
						iconPriority = MyCollabResource
								.newResource(BugSeverityConstants.MINOR_IMG);
					} else if (BugSeverityConstants.TRIVIAL.equals(bug
							.getSeverity())) {
						iconPriority = MyCollabResource
								.newResource(BugSeverityConstants.TRIVIAL_IMG);
					}

				}

				Embedded iconEmbedded = new Embedded(null, iconPriority);
				Label lbPriority = new Label(bug.getSeverity());
				lbPriority.setWidth("70px");
				FormContainerHorizontalViewField containerField = new FormContainerHorizontalViewField();
				containerField.addComponentField(iconEmbedded);
				containerField.getLayout().setComponentAlignment(iconEmbedded,
						Alignment.MIDDLE_CENTER);
				containerField.addComponentField(lbPriority);
				containerField.getLayout().setComponentAlignment(lbPriority,
						Alignment.MIDDLE_CENTER);
				return containerField;

			}
		});

		this.addGeneratedColumn("duedate", new Table.ColumnGenerator() {
			private static final long serialVersionUID = 1L;

			@Override
			public com.vaadin.ui.Component generateCell(Table source,
					final Object itemId, Object columnId) {
				final SimpleBug bug = BugTableDisplay.this
						.getBeanByIndex(itemId);
				return new Label(AppContext.formatDate(bug.getDuedate()));

			}
		});

		this.setWidth("100%");
	}

	@Override
	public void refreshBugItem() {
		this.refresh();

	}
}
