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
package com.esofthead.mycollab.module.project.view.bug;

import java.util.Arrays;
import java.util.GregorianCalendar;

import org.vaadin.dialogs.ConfirmDialog;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.utils.StringUtils;
import com.esofthead.mycollab.eventmanager.EventBus;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.domain.SimpleProjectMember;
import com.esofthead.mycollab.module.project.events.BugEvent;
import com.esofthead.mycollab.module.project.service.ProjectMemberService;
import com.esofthead.mycollab.module.tracker.BugResolutionConstants;
import com.esofthead.mycollab.module.tracker.BugStatusConstants;
import com.esofthead.mycollab.module.tracker.domain.SimpleBug;
import com.esofthead.mycollab.module.tracker.domain.SimpleRelatedBug;
import com.esofthead.mycollab.module.tracker.domain.criteria.BugRelatedSearchCriteria;
import com.esofthead.mycollab.module.tracker.service.BugService;
import com.esofthead.mycollab.module.tracker.service.RelatedBugService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.ui.ButtonLink;
import com.esofthead.mycollab.vaadin.ui.ConfirmDialogExt;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.table.DefaultPagedBeanTable;
import com.esofthead.mycollab.vaadin.ui.table.TableViewField;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.server.Resource;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomField;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.RichTextArea;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.ColumnGenerator;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 * 
 */
public class BugRelatedField extends CustomField {

	private static final long serialVersionUID = 1L;
	private TextField itemField;
	private Button browseBtn;
	private Button clearBtn;

	private Button btnRelate;
	private BugRelationComboBox comboRelation;
	private DefaultPagedBeanTable<RelatedBugService, BugRelatedSearchCriteria, SimpleRelatedBug> tableItem;
	private RelatedBugService relatedBugService;
	private RichTextArea txtComment;

	private SimpleBug bean;
	private SimpleBug relatedBean;

	public BugRelatedField(final SimpleBug bean) {
		this.bean = bean;

		relatedBugService = ApplicationContextUtil
				.getSpringBean(RelatedBugService.class);
	}

	private void setCriteria() {
		BugRelatedSearchCriteria searchCriteria = new BugRelatedSearchCriteria();
		searchCriteria.setBugId(new NumberSearchField(bean.getId()));
		tableItem.setSearchCriteria(searchCriteria);
	}

	private void callItemSelectionWindow() {
		BugSelectionWindow bugSeletionWindow = new BugSelectionWindow(this);
		UI.getCurrent().addWindow(bugSeletionWindow);
		bugSeletionWindow.show();
	}

	public void fireValueChange(SimpleBug data) {
		relatedBean = data;

		String bugname = "[%s-%s] %s";
		bugname = String.format(bugname, CurrentProjectVariables.getProject()
				.getShortname(), data.getBugkey(), data.getSummary());
		setItemFieldValue(bugname);
	}

	@Override
	public Class<?> getType() {
		return (new String[2]).getClass();
	}

	private void setItemFieldValue(String value) {
		itemField.setReadOnly(false);
		itemField.setValue(value);
		itemField.setReadOnly(true);
	}

	@Override
	protected Component initContent() {
		VerticalLayout mainLayout = new VerticalLayout();
		mainLayout.setWidth("100%");
		mainLayout.setMargin(true);
		mainLayout.setSpacing(true);

		HorizontalLayout layoutAdd = new HorizontalLayout();
		layoutAdd.setSpacing(true);

		Label lbBug = new Label("Bug:");
		lbBug.setWidth("70px");
		layoutAdd.addComponent(lbBug);
		layoutAdd.setComponentAlignment(lbBug, Alignment.MIDDLE_LEFT);

		itemField = new TextField();
		itemField.setWidth("300px");
		itemField.setNullRepresentation("");
		itemField.setReadOnly(true);
		itemField.setEnabled(true);
		layoutAdd.addComponent(itemField);
		layoutAdd.setComponentAlignment(itemField, Alignment.MIDDLE_LEFT);

		browseBtn = new Button("", new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(com.vaadin.ui.Button.ClickEvent event) {
				callItemSelectionWindow();

			}
		});
		browseBtn.setIcon(MyCollabResource
				.newResource("icons/16/browseItem.png"));
		browseBtn.setStyleName("link");

		layoutAdd.addComponent(browseBtn);
		layoutAdd.setComponentAlignment(browseBtn, Alignment.MIDDLE_LEFT);

		clearBtn = new Button("", new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(com.vaadin.ui.Button.ClickEvent event) {
				setItemFieldValue("");
			}
		});
		clearBtn.setIcon(MyCollabResource.newResource("icons/16/clearItem.png"));
		clearBtn.setStyleName("link");

		layoutAdd.addComponent(clearBtn);
		layoutAdd.setComponentAlignment(clearBtn, Alignment.MIDDLE_LEFT);

		Label lbIs = new Label("is");
		layoutAdd.addComponent(lbIs);
		layoutAdd.setComponentAlignment(lbIs, Alignment.MIDDLE_LEFT);

		comboRelation = new BugRelationComboBox();
		comboRelation.setWidth("200px");
		layoutAdd.addComponent(comboRelation);
		layoutAdd.setComponentAlignment(comboRelation, Alignment.MIDDLE_LEFT);

		btnRelate = new Button("Relate");
		btnRelate.setStyleName(UIConstants.THEME_BLUE_LINK);
		btnRelate.setIcon(MyCollabResource
				.newResource("icons/16/addRecord.png"));

		ProjectMemberService memberService = ApplicationContextUtil
				.getSpringBean(ProjectMemberService.class);
		SimpleProjectMember member = memberService.findMemberByUsername(
				AppContext.getUsername(),
				CurrentProjectVariables.getProjectId(),
				AppContext.getAccountId());

		if (member != null) {
			btnRelate
					.setEnabled((member.getIsadmin()
							|| (AppContext.getUsername().equals(bean
									.getAssignuser())) || (AppContext
								.getUsername().equals(bean.getLogby())))
							&& CurrentProjectVariables
									.canWrite(ProjectRolePermissionCollections.BUGS));
		}

		btnRelate.addClickListener(new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(com.vaadin.ui.Button.ClickEvent event) {
				if (!itemField.getValue().toString().trim().equals("")
						&& relatedBean != null
						&& !relatedBean.getSummary().equals(bean.getSummary())) {
					SimpleRelatedBug relatedBug = new SimpleRelatedBug();
					relatedBug.setBugid(bean.getId());
					relatedBug.setRelatedid(relatedBean.getId());
					relatedBug.setRelatetype((String) comboRelation.getValue());
					relatedBug.setComment(txtComment.getValue().toString());
					relatedBugService.saveWithSession(relatedBug,
							AppContext.getUsername());

					SimpleRelatedBug oppositeRelation = new SimpleRelatedBug();
					oppositeRelation.setBugid(relatedBean.getId());
					oppositeRelation.setRelatedid(bean.getId());
					oppositeRelation.setComment(txtComment.getValue()
							.toString());

					if (comboRelation.getValue().toString()
							.equals(BugRelationConstants.PARENT)) {
						oppositeRelation
								.setRelatetype(BugRelationConstants.CHILD);
					} else if (comboRelation.getValue().toString()
							.equals(BugRelationConstants.CHILD)) {
						oppositeRelation
								.setRelatetype(BugRelationConstants.PARENT);
					} else if (comboRelation.getValue().toString()
							.equals(BugRelationConstants.RELATED)) {
						oppositeRelation
								.setRelatetype(BugRelationConstants.RELATED);
					} else if (comboRelation.getValue().toString()
							.equals(BugRelationConstants.BEFORE)) {
						oppositeRelation
								.setRelatetype(BugRelationConstants.AFTER);
					} else if (comboRelation.getValue().toString()
							.equals(BugRelationConstants.AFTER)) {
						oppositeRelation
								.setRelatetype(BugRelationConstants.BEFORE);
					} else if (comboRelation.getValue().toString()
							.equals(BugRelationConstants.DUPLICATED)) {
						oppositeRelation
								.setRelatetype(BugRelationConstants.DUPLICATED);
						BugService bugService = ApplicationContextUtil
								.getSpringBean(BugService.class);
						bean.setStatus(BugStatusConstants.RESOLVED);
						bean.setResolution(BugResolutionConstants.DUPLICATE);
						bugService.updateWithSession(bean,
								AppContext.getUsername());
					}
					relatedBugService.saveWithSession(oppositeRelation,
							AppContext.getUsername());

					setCriteria();

					setItemFieldValue("");
					txtComment.setValue("");
					relatedBean = null;
				}
			}
		});
		layoutAdd.addComponent(btnRelate);
		layoutAdd.setComponentAlignment(btnRelate, Alignment.MIDDLE_LEFT);

		Label lbInstruction = new Label(
				"<strong>Relate to an existing ticket</strong>",
				ContentMode.HTML);
		mainLayout.addComponent(lbInstruction);

		mainLayout.addComponent(layoutAdd);

		HorizontalLayout layoutComment = new HorizontalLayout();
		layoutComment.setSpacing(true);
		Label lbComment = new Label("Comment:");
		lbComment.setWidth("70px");
		layoutComment.addComponent(lbComment);
		layoutComment.setComponentAlignment(lbComment, Alignment.TOP_LEFT);
		txtComment = new RichTextArea();
		txtComment.setHeight("130px");
		txtComment.setWidth("565px");
		layoutComment.addComponent(txtComment);
		layoutComment.setComponentAlignment(txtComment, Alignment.MIDDLE_LEFT);
		mainLayout.addComponent(layoutComment);

		tableItem = new DefaultPagedBeanTable<RelatedBugService, BugRelatedSearchCriteria, SimpleRelatedBug>(
				ApplicationContextUtil.getSpringBean(RelatedBugService.class),
				SimpleRelatedBug.class, Arrays.asList(
						new TableViewField("Bug Name", "bugName",
								UIConstants.TABLE_EX_LABEL_WIDTH),
						new TableViewField("Related Type", "relatetype",
								UIConstants.TABLE_S_LABEL_WIDTH),
						new TableViewField("Comment", "comment",
								UIConstants.TABLE_EX_LABEL_WIDTH),
						new TableViewField("", "id",
								UIConstants.TABLE_CONTROL_WIDTH)));

		tableItem.addGeneratedColumn("bugName", new Table.ColumnGenerator() {
			private static final long serialVersionUID = 1L;

			@Override
			public com.vaadin.ui.Component generateCell(Table source,
					final Object itemId, Object columnId) {
				final SimpleRelatedBug relatedItem = tableItem
						.getBeanByIndex(itemId);
				String bugname = "[%s-%s] %s";
				bugname = String.format(bugname, CurrentProjectVariables
						.getProject().getShortname(), relatedItem
						.getRelatedid(), relatedItem.getBugName());

				BugService bugService = ApplicationContextUtil
						.getSpringBean(BugService.class);
				final SimpleBug bug = bugService.findById(
						relatedItem.getRelatedid(), AppContext.getAccountId());

				ButtonLink b = new ButtonLink(bugname,
						new Button.ClickListener() {
							private static final long serialVersionUID = 1L;

							@Override
							public void buttonClick(Button.ClickEvent event) {
								EventBus.getInstance()
										.fireEvent(
												new BugEvent.GotoRead(this, bug
														.getId()));
							}
						});

				if (StringUtils.isNotNullOrEmpty(bug.getPriority())) {
					Resource iconPriority = MyCollabResource
							.newResource(BugPriorityStatusConstants.PRIORITY_MAJOR_IMG_16);

					if (BugPriorityStatusConstants.PRIORITY_BLOCKER.equals(bug
							.getPriority())) {
						iconPriority = MyCollabResource
								.newResource(BugPriorityStatusConstants.PRIORITY_BLOCKER_IMG_16);
					} else if (BugPriorityStatusConstants.PRIORITY_CRITICAL
							.equals(bug.getPriority())) {
						iconPriority = MyCollabResource
								.newResource(BugPriorityStatusConstants.PRIORITY_CRITICAL_IMG_16);
					} else if (BugPriorityStatusConstants.PRIORITY_MAJOR
							.equals(bug.getPriority())) {
						iconPriority = MyCollabResource
								.newResource(BugPriorityStatusConstants.PRIORITY_MAJOR_IMG_16);
					} else if (BugPriorityStatusConstants.PRIORITY_MINOR
							.equals(bug.getPriority())) {
						iconPriority = MyCollabResource
								.newResource(BugPriorityStatusConstants.PRIORITY_MINOR_IMG_16);
					} else if (BugPriorityStatusConstants.PRIORITY_TRIVIAL
							.equals(bug.getPriority())) {
						iconPriority = MyCollabResource
								.newResource(BugPriorityStatusConstants.PRIORITY_TRIVIAL_IMG_16);
					}

					b.setIcon(iconPriority);
				}

				if (BugStatusConstants.VERIFIED.equals(bug.getStatus())) {
					b.addStyleName(UIConstants.LINK_COMPLETED);
				} else if (bug.getDuedate() != null
						&& (bug.getDuedate().before(new GregorianCalendar()
								.getTime()))) {
					b.addStyleName(UIConstants.LINK_OVERDUE);
				}
				b.setWidth("100%");
				return b;

			}
		});

		tableItem.addGeneratedColumn("id", new ColumnGenerator() {
			private static final long serialVersionUID = 1L;

			@Override
			public Object generateCell(Table source, Object itemId,
					Object columnId) {
				final SimpleRelatedBug relatedItem = tableItem
						.getBeanByIndex(itemId);

				Button deleteBtn = new Button(null, new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(
							com.vaadin.ui.Button.ClickEvent event) {
						ConfirmDialogExt.show(UI.getCurrent(),
								"Please Confirm:",
								"Are you sure to remove this relationship?",
								"Yes", "No", new ConfirmDialog.Listener() {
									private static final long serialVersionUID = 1L;

									@Override
									public void onClose(ConfirmDialog dialog) {
										if (dialog.isConfirmed()) {

											BugRelatedSearchCriteria relateBugIdCriteria = new BugRelatedSearchCriteria();
											relateBugIdCriteria
													.setBugId(new NumberSearchField(
															relatedItem
																	.getBugid()));
											relateBugIdCriteria
													.setRelatedId(new NumberSearchField(
															relatedItem
																	.getRelatedid()));

											relatedBugService.removeByCriteria(
													relateBugIdCriteria,
													AppContext.getAccountId());

											BugRelatedSearchCriteria relateIdCriteria = new BugRelatedSearchCriteria();
											relateIdCriteria
													.setBugId(new NumberSearchField(
															relatedItem
																	.getRelatedid()));
											relateIdCriteria
													.setRelatedId(new NumberSearchField(
															relatedItem
																	.getBugid()));

											relatedBugService.removeByCriteria(
													relateIdCriteria,
													AppContext.getAccountId());

											BugRelatedField.this.setCriteria();
										}
									}
								});
					}
				});
				deleteBtn.setStyleName("link");
				deleteBtn.setIcon(MyCollabResource
						.newResource("icons/16/delete.png"));
				relatedItem.setExtraData(deleteBtn);

				ProjectMemberService memberService = ApplicationContextUtil
						.getSpringBean(ProjectMemberService.class);
				SimpleProjectMember member = memberService
						.findMemberByUsername(AppContext.getUsername(),
								CurrentProjectVariables.getProjectId(),
								AppContext.getAccountId());

				if (member != null) {
					deleteBtn.setEnabled(member.getIsadmin()
							|| (AppContext.getUsername().equals(bean
									.getAssignuser()))
							|| (AppContext.getUsername().equals(bean.getLogby())));
				}
				return deleteBtn;
			}
		});

		mainLayout.addComponent(tableItem);

		setCriteria();

		return mainLayout;
	}
}
