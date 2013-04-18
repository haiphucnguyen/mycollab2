package com.esofthead.mycollab.module.project.view.bug;

import java.util.GregorianCalendar;
import java.util.List;

import org.vaadin.dialogs.ConfirmDialog;

import com.esofthead.mycollab.common.MonitorTypeConstants;
import com.esofthead.mycollab.common.domain.MonitorItem;
import com.esofthead.mycollab.common.domain.RelayEmailNotification;
import com.esofthead.mycollab.common.domain.criteria.MonitorSearchCriteria;
import com.esofthead.mycollab.common.service.MonitorItemService;
import com.esofthead.mycollab.common.service.RelayEmailNotificationService;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.domain.ProjectMember;
import com.esofthead.mycollab.module.project.domain.SimpleProjectMember;
import com.esofthead.mycollab.module.project.service.ProjectMemberService;
import com.esofthead.mycollab.module.project.view.people.component.ProjectMemberMultiSelectField;
import com.esofthead.mycollab.module.project.view.people.component.ProjectUserLink;
import com.esofthead.mycollab.module.tracker.domain.SimpleBug;
import com.esofthead.mycollab.module.tracker.service.BugNotificationService;
import com.esofthead.mycollab.module.user.domain.SimpleUser;
import com.esofthead.mycollab.module.user.service.UserService;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.table.PagedBeanTable2;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.ColumnGenerator;
import com.vaadin.ui.VerticalLayout;

class BugFollowersSheet extends VerticalLayout {
	private static final long serialVersionUID = 1L;

	private SimpleBug bug;

	private PagedBeanTable2<MonitorItemService, MonitorSearchCriteria, MonitorItem> tableItem;

	public BugFollowersSheet(SimpleBug bug) {
		this.bug = bug;

		this.setMargin(true);
		this.setSpacing(true);

		initUI();
	}

	private void loadMonitorItems() {
		MonitorSearchCriteria searchCriteria = new MonitorSearchCriteria();
		searchCriteria.setTypeId(bug.getId());
		searchCriteria.setType(MonitorTypeConstants.PRJ_BUG);
		tableItem.setSearchCriteria(searchCriteria);
	}

	private void initUI() {
		Label lbInstruct = new Label(
				"Add people from your team or external to follow bug activity");
		this.addComponent(lbInstruct);

		HorizontalLayout layoutAdd = new HorizontalLayout();
		layoutAdd.setWidth("550px");

		final ProjectMemberMultiSelectField memberSelection = new ProjectMemberMultiSelectField();
		layoutAdd.addComponent(memberSelection);
		layoutAdd.setComponentAlignment(memberSelection, Alignment.MIDDLE_LEFT);

		Button btnSave = new Button("Add", new Button.ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {

				List<ProjectMember> members = memberSelection
						.getSelectedItems();
				
				boolean canSendEmail = false;

				for (ProjectMember member : members) {

					MonitorItemService monitorItemService = AppContext
							.getSpringBean(MonitorItemService.class);
					
					ProjectMemberService memberService = AppContext.getSpringBean(ProjectMemberService.class);
					
					SimpleProjectMember projectMember = memberService.findMemberByUsername(AppContext.getUsername(), CurrentProjectVariables.getProjectId());
					
					boolean haveRightToSave = projectMember.getIsadmin() || (AppContext.getUsername().equals(member.getUsername()));
					
					if (haveRightToSave && member != null
							&& member.getUsername() != null
							&& !monitorItemService.isUserWatchingItem(
									member.getUsername(),
									MonitorTypeConstants.PRJ_BUG, bug.getId())) {
						MonitorItem monitorItem = new MonitorItem();
						monitorItem.setMonitorDate(new GregorianCalendar()
								.getTime());
						monitorItem.setType(MonitorTypeConstants.PRJ_BUG);
						monitorItem.setTypeid(bug.getId());
						monitorItem.setUser(member.getUsername());
						monitorItemService.saveWithSession(monitorItem,
								AppContext.getUsername());
						
						canSendEmail = true;
						
					}
				}
				
				if (canSendEmail) {
					RelayEmailNotification relayNotification = new RelayEmailNotification();
					relayNotification.setChangeby(AppContext.getUsername());
					relayNotification.setChangecomment("");
					relayNotification.setSaccountid(AppContext
							.getAccountId());
					relayNotification.setType(MonitorTypeConstants.PRJ_BUG);
					relayNotification.setTypeid(bug.getId());
					relayNotification
							.setEmailhandlerbean(BugNotificationService.class
									.getName());
					relayNotification
							.setAction(MonitorTypeConstants.CREATE_ACTION);

					RelayEmailNotificationService relayEmailNotificationService = AppContext
							.getSpringBean(RelayEmailNotificationService.class);
					relayEmailNotificationService.saveWithSession(
							relayNotification, AppContext.getUsername());
				}

				memberSelection.resetComp();
				loadMonitorItems();
			}
		});

		btnSave.setEnabled(CurrentProjectVariables
				.canWrite(ProjectRolePermissionCollections.BUGS));
		btnSave.setStyleName(UIConstants.THEME_BLUE_LINK);
		btnSave.setIcon(new ThemeResource("icons/16/addRecord.png"));

		layoutAdd.addComponent(btnSave);
		layoutAdd.setComponentAlignment(btnSave, Alignment.MIDDLE_LEFT);

		this.addComponent(layoutAdd);

		tableItem = new PagedBeanTable2<MonitorItemService, MonitorSearchCriteria, MonitorItem>(
				AppContext.getSpringBean(MonitorItemService.class),
				MonitorItem.class,
				new String[] { "user", "monitorDate", "id" }, new String[] {
						"Name", "Monitor Date", "" });

		tableItem.addGeneratedColumn("user", new Table.ColumnGenerator() {
			private static final long serialVersionUID = 1L;

			@Override
			public com.vaadin.ui.Component generateCell(Table source,
					final Object itemId, Object columnId) {
				final MonitorItem monitorItem = tableItem
						.getBeanByIndex(itemId);

				UserService userService = AppContext
						.getSpringBean(UserService.class);
				SimpleUser user = userService.findUserByUserName(monitorItem
						.getUser());

				return new ProjectUserLink(user.getUsername(), user
						.getDisplayName(), true);

			}
		});

		tableItem.addGeneratedColumn("monitorDate", new ColumnGenerator() {
			private static final long serialVersionUID = 1L;

			@Override
			public com.vaadin.ui.Component generateCell(Table source,
					Object itemId, Object columnId) {
				final MonitorItem monitorItem = tableItem
						.getBeanByIndex(itemId);
				Label l = new Label();
				l.setValue(AppContext.formatDate(monitorItem.getMonitorDate()));
				return l;
			}
		});

		tableItem.addGeneratedColumn("id", new ColumnGenerator() {
			@Override
			public Object generateCell(Table source, Object itemId,
					Object columnId) {
				final MonitorItem monitorItem = tableItem
						.getBeanByIndex(itemId);

				Button deleteBtn = new Button(null, new Button.ClickListener() {
					@Override
					public void buttonClick(ClickEvent event) {
						ConfirmDialog
								.show(AppContext.getApplication()
										.getMainWindow(),
										"Please Confirm:",
										"Are you sure to remove this user from the notification of bug activity?",
										"Yes", "No",
										new ConfirmDialog.Listener() {
											private static final long serialVersionUID = 1L;

											@Override
											public void onClose(
													ConfirmDialog dialog) {
												if (dialog.isConfirmed()) {
													MonitorItemService monitorItemService = AppContext
															.getSpringBean(MonitorItemService.class);
													monitorItemService.removeWithSession(
															monitorItem.getId(),
															AppContext
																	.getUsername());
													BugFollowersSheet.this
															.loadMonitorItems();
												}
											}
										});
					}
				});
				deleteBtn.setStyleName("link");
				deleteBtn.setIcon(new ThemeResource("icons/16/delete.png"));
				monitorItem.setExtraData(deleteBtn);
				
				ProjectMemberService memberService = AppContext.getSpringBean(ProjectMemberService.class);
				SimpleProjectMember member = memberService.findMemberByUsername(AppContext.getUsername(), CurrentProjectVariables.getProjectId());
				
				if (member != null) {
					deleteBtn.setEnabled(member.getIsadmin() || (AppContext.getUsername().equals(monitorItem.getUser())));
				}
				return deleteBtn;
			}
		});

		tableItem.setWidth("100%");

		tableItem.setColumnExpandRatio("user", 1.0f);
		tableItem.setColumnWidth("monitorDate", UIConstants.TABLE_DATE_WIDTH);

		this.addComponent(tableItem);
		loadMonitorItems();
	}
}
