package com.esofthead.mycollab.module.project.ui.components;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.dialogs.ConfirmDialog;

import com.esofthead.mycollab.common.domain.MonitorItem;
import com.esofthead.mycollab.common.domain.SimpleMonitorItem;
import com.esofthead.mycollab.common.domain.criteria.MonitorSearchCriteria;
import com.esofthead.mycollab.common.service.MonitorItemService;
import com.esofthead.mycollab.core.utils.ValuedBean;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.domain.ProjectMember;
import com.esofthead.mycollab.module.project.domain.SimpleProjectMember;
import com.esofthead.mycollab.module.project.service.ProjectMemberService;
import com.esofthead.mycollab.module.project.view.people.component.ProjectMemberMultiSelectField;
import com.esofthead.mycollab.module.project.view.people.component.ProjectUserLink;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.table.PagedBeanTable2;
import com.esofthead.mycollab.web.AppContext;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.ColumnGenerator;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public abstract class CompFollowersSheet<V extends ValuedBean> extends
		VerticalLayout {

	protected PagedBeanTable2<MonitorItemService, MonitorSearchCriteria, SimpleMonitorItem> tableItem;
	protected MonitorItemService monitorItemService;
	protected V bean;
	protected Button btnSave;

	private static Logger log = LoggerFactory
			.getLogger(CompFollowersSheet.class);

	protected CompFollowersSheet(V bean) {
		this.bean = bean;
		this.setMargin(true);
		this.setSpacing(true);
		this.setWidth("100%");

		monitorItemService = AppContext.getSpringBean(MonitorItemService.class);

		initUI();
	}

	protected abstract void loadMonitorItems();

	protected abstract boolean saveMonitorItem(String username);

	protected abstract void saveRelayNotification();

	protected abstract boolean isEnableAdd();

	private void initUI() {
		Label lbInstruct = new Label(
				"Add people from your team or external to follow bug activity");
		this.addComponent(lbInstruct);

		HorizontalLayout layoutAdd = new HorizontalLayout();
		layoutAdd.setWidth("550px");

		final ProjectMemberMultiSelectField memberSelection = new ProjectMemberMultiSelectField();
		layoutAdd.addComponent(memberSelection);
		layoutAdd.setComponentAlignment(memberSelection, Alignment.MIDDLE_LEFT);

		btnSave = new Button("Add", new Button.ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {

				List<ProjectMember> members = memberSelection
						.getSelectedItems();

				boolean canSendEmail = false;

				for (ProjectMember member : members) {

					ProjectMemberService memberService = AppContext
							.getSpringBean(ProjectMemberService.class);

					SimpleProjectMember projectMember = memberService
							.findMemberByUsername(AppContext.getUsername(),
									CurrentProjectVariables.getProjectId());

					boolean haveRightToSave = projectMember.getIsadmin()
							|| (AppContext.getUsername().equals(member
									.getUsername()));

					if (haveRightToSave && member != null
							&& member.getUsername() != null) {

						if (saveMonitorItem(member.getUsername())) {
							canSendEmail = true;
						}
					}
				}

				if (canSendEmail) {
					saveRelayNotification();
				}

				memberSelection.resetComp();
				loadMonitorItems();
			}
		});

		btnSave.setEnabled(isEnableAdd());
		btnSave.setStyleName(UIConstants.THEME_BLUE_LINK);
		btnSave.setIcon(MyCollabResource.newResource("icons/16/addRecord.png"));

		layoutAdd.addComponent(btnSave);
		layoutAdd.setComponentAlignment(btnSave, Alignment.MIDDLE_LEFT);

		this.addComponent(layoutAdd);

		tableItem = new PagedBeanTable2<MonitorItemService, MonitorSearchCriteria, SimpleMonitorItem>(
				AppContext.getSpringBean(MonitorItemService.class),
				SimpleMonitorItem.class, new String[] { "user", "monitorDate",
						"id" }, new String[] { "Name", "Monitor Date", "" });

		tableItem.addGeneratedColumn("user", new Table.ColumnGenerator() {
			private static final long serialVersionUID = 1L;

			@Override
			public com.vaadin.ui.Component generateCell(Table source,
					final Object itemId, Object columnId) {
				final SimpleMonitorItem monitorItem = tableItem
						.getBeanByIndex(itemId);

				return new ProjectUserLink(monitorItem.getUser(), monitorItem
						.getUserFullname(), true, true);

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
										"Are you sure to remove this user from the notification of item activity?",
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
													CompFollowersSheet.this
															.loadMonitorItems();
												}
											}
										});
					}
				});
				deleteBtn.setStyleName("link");
				deleteBtn.setIcon(MyCollabResource
						.newResource("icons/16/delete.png"));
				monitorItem.setExtraData(deleteBtn);

				ProjectMemberService memberService = AppContext
						.getSpringBean(ProjectMemberService.class);
				SimpleProjectMember member = memberService
						.findMemberByUsername(AppContext.getUsername(),
								CurrentProjectVariables.getProjectId());

				if (member != null) {
					deleteBtn.setEnabled(member.getIsadmin()
							|| (AppContext.getUsername().equals(monitorItem
									.getUser())));
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
