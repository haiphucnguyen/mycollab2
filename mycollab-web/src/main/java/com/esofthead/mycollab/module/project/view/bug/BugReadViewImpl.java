package com.esofthead.mycollab.module.project.view.bug;

import org.vaadin.dialogs.ConfirmDialog;
import org.vaadin.peter.buttongroup.ButtonGroup;

import com.esofthead.mycollab.common.CommentTypeConstants;
import com.esofthead.mycollab.common.ui.components.CommentListDepot.CommentDisplay;
import com.esofthead.mycollab.core.utils.BeanUtility;
import com.esofthead.mycollab.module.file.AttachmentConstants;
import com.esofthead.mycollab.module.project.events.BugComponentEvent;
import com.esofthead.mycollab.module.project.events.BugEvent;
import com.esofthead.mycollab.module.project.events.BugVersionEvent;
import com.esofthead.mycollab.module.project.events.MilestoneEvent;
import com.esofthead.mycollab.module.project.view.people.component.ProjectUserFormLinkField;
import com.esofthead.mycollab.module.tracker.BugStatusConstants;
import com.esofthead.mycollab.module.tracker.domain.Component;
import com.esofthead.mycollab.module.tracker.domain.SimpleBug;
import com.esofthead.mycollab.module.tracker.domain.Version;
import com.esofthead.mycollab.module.tracker.service.BugService;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.AddViewLayout;
import com.esofthead.mycollab.vaadin.ui.AdvancedPreviewBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultFormViewFieldFactory;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.vaadin.ui.IFormLayoutFactory;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.TabSheet;

@ViewComponent
public class BugReadViewImpl extends AbstractView implements BugReadView {

	private static final long serialVersionUID = 1L;
	private SimpleBug bug;
	private final BugPreviewForm previewForm;
	private HorizontalLayout bugWorkflowControl;

	public BugReadViewImpl() {
		super();

		previewForm = new BugPreviewForm();
		this.addComponent(previewForm);
	}

	@Override
	public void previewItem(SimpleBug item) {
		this.bug = item;
		previewForm.setItemDataSource(new BeanItem<SimpleBug>(bug));
		System.out.println("Assign user: " + BeanUtility.printBeanObj(item));
	}

	@Override
	public SimpleBug getItem() {
		return bug;
	}

	private void displayWorkflowControl() {
		if (BugStatusConstants.OPEN.equals(bug.getStatus())
				|| BugStatusConstants.REOPENNED.equals(bug.getStatus())) {
			bugWorkflowControl.removeAllComponents();
			ButtonGroup navButton = new ButtonGroup();
			navButton.addButton(new Button("Start Progress",
					new Button.ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(ClickEvent event) {
							bug.setStatus(BugStatusConstants.INPROGRESS);
							BugService bugService = AppContext
									.getSpringBean(BugService.class);
							bugService.updateWithSession(bug,
									AppContext.getUsername());
							displayWorkflowControl();
						}
					}));
			navButton.addButton(new Button("Won't Fix",
					new Button.ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(ClickEvent event) {
							AppContext.getApplication().getMainWindow()
									.addWindow(new WontFixExplainWindow(bug));
						}
					}));
			bugWorkflowControl.addComponent(navButton);
		} else if (BugStatusConstants.INPROGRESS.equals(bug.getStatus())) {
			bugWorkflowControl.removeAllComponents();
			ButtonGroup navButton = new ButtonGroup();
			navButton.addButton(new Button("Stop Progress",
					new Button.ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(ClickEvent event) {
							bug.setStatus(BugStatusConstants.OPEN);
							BugService bugService = AppContext
									.getSpringBean(BugService.class);
							bugService.updateWithSession(bug,
									AppContext.getUsername());
							displayWorkflowControl();
						}
					}));
			navButton.addButton(new Button("Resolved",
					new Button.ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(ClickEvent event) {
							AppContext.getApplication().getMainWindow()
									.addWindow(new ResolvedInputWindow(bug));
						}
					}));
			bugWorkflowControl.addComponent(navButton);
		} else if (BugStatusConstants.CLOSE.equals(bug.getStatus())) {
			bugWorkflowControl.removeAllComponents();
			ButtonGroup navButton = new ButtonGroup();
			Button reopenBtn = new Button("Reopen", new Button.ClickListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(ClickEvent event) {
					AppContext.getApplication().getMainWindow()
							.addWindow(new ReOpenWindow(bug));
				}
			});
			reopenBtn.setStyleName(UIConstants.THEME_ROUND_BUTTON);
			navButton.addButton(reopenBtn);

			bugWorkflowControl.addComponent(navButton);
		} else if (BugStatusConstants.TESTPENDING.equals(bug.getStatus())) {
			bugWorkflowControl.removeAllComponents();
			ButtonGroup navButton = new ButtonGroup();
			navButton.addButton(new Button("Reopen",
					new Button.ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(ClickEvent event) {
							AppContext.getApplication().getMainWindow()
									.addWindow(new ReOpenWindow(bug));
						}
					}));
			navButton.addButton(new Button("Approve & Close",
					new Button.ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(ClickEvent event) {
							AppContext.getApplication().getMainWindow()
									.addWindow(new ApproveInputWindow(bug));
						}
					}));
			bugWorkflowControl.addComponent(navButton);
		} else if (BugStatusConstants.WONFIX.equals(bug.getStatus())) {
			bugWorkflowControl.removeAllComponents();
			ButtonGroup navButton = new ButtonGroup();
			Button reopenBtn = new Button("Reopen", new Button.ClickListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(ClickEvent event) {
					AppContext.getApplication().getMainWindow()
							.addWindow(new ReOpenWindow(bug));
				}
			});
			reopenBtn.setStyleName(UIConstants.THEME_ROUND_BUTTON);
			navButton.addButton(reopenBtn);

			bugWorkflowControl.addComponent(navButton);
		}
	}

	private class BugPreviewForm extends AdvancedPreviewBeanForm<SimpleBug> {
		private static final long serialVersionUID = 1L;
		private BugHistoryList historyList;
		private BugFollowersSheet bugFollowersList;
		private BugTimeLogSheet bugTimeLogList;
		private BugRelatedItemSheet bugRelatedItemsList;

		@Override
		public void setItemDataSource(Item newDataSource) {
			this.setFormLayoutFactory(new FormLayoutFactory());
			this.setFormFieldFactory(new PreviewFormFieldFactory());
			super.setItemDataSource(newDataSource);
			displayWorkflowControl();
		}

		private class FormLayoutFactory implements IFormLayoutFactory {
			private static final long serialVersionUID = 1L;
			private GridFormLayoutHelper informationLayout;

			@Override
			public Layout getLayout() {
				AddViewLayout taskListAddLayout = new AddViewLayout(
						bug.getSummary(), new ThemeResource(
								"icons/48/project/bug.png"));

				Button createAccountBtn = new Button("Create",
						new Button.ClickListener() {
							private static final long serialVersionUID = 1L;

							@Override
							public void buttonClick(ClickEvent event) {
								EventBus.getInstance().fireEvent(
										new BugEvent.GotoAdd(this, null));
							}
						});
				createAccountBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
				createAccountBtn.setIcon(new ThemeResource(
						"icons/16/addRecord.png"));

				HorizontalLayout headerRight = new HorizontalLayout();
				headerRight.addComponent(createAccountBtn);
				headerRight.setMargin(true);
				taskListAddLayout.addHeaderRight(headerRight);

				HorizontalLayout topPanel = new HorizontalLayout();
				topPanel.setSpacing(true);
				topPanel.setMargin(true);
				topPanel.setWidth("100%");

				HorizontalLayout buttonControls = new HorizontalLayout();
				buttonControls.setSpacing(true);

				Button backBtn;
				backBtn = new Button(null, new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(ClickEvent event) {
						EventBus.getInstance().fireEvent(
								new BugEvent.GotoList(this, null));
					}
				});
				backBtn.setIcon(new ThemeResource("icons/16/back.png"));
				backBtn.setDescription("Back to list");
				backBtn.setStyleName("link");
				topPanel.addComponent(backBtn);
				topPanel.setComponentAlignment(backBtn, Alignment.MIDDLE_LEFT);

				Button assignBtn = new Button("Assign",
						new Button.ClickListener() {
							private static final long serialVersionUID = 1L;

							@Override
							public void buttonClick(ClickEvent event) {
								AppContext.getApplication().getMainWindow()
										.addWindow(new AssignBugWindow(bug));
							}
						});
				assignBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
				buttonControls.addComponent(assignBtn);
				buttonControls.setComponentAlignment(assignBtn,
						Alignment.MIDDLE_CENTER);

				Button editBtn = new Button("Edit", new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(ClickEvent event) {
						EventBus.getInstance()
								.fireEvent(
										new BugEvent.GotoEdit(
												BugReadViewImpl.this, bug));
					}
				});
				editBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
				buttonControls.addComponent(editBtn);
				buttonControls.setComponentAlignment(editBtn,
						Alignment.MIDDLE_CENTER);

				Button deleteBtn = new Button("Delete",
						new Button.ClickListener() {
							private static final long serialVersionUID = 1L;

							@Override
							public void buttonClick(ClickEvent event) {
								ConfirmDialog.show(AppContext.getApplication()
										.getMainWindow(), "Please Confirm:",
										"Are you sure to delete this item: "
												+ bug.getSummary() + " ?",
										"Yes", "No",
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
													EventBus.getInstance()
															.fireEvent(
																	new BugEvent.GotoList(
																			BugReadViewImpl.this,
																			bug));
												}
											}
										});
							}
						});
				deleteBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
				buttonControls.addComponent(deleteBtn);
				buttonControls.setComponentAlignment(deleteBtn,
						Alignment.MIDDLE_CENTER);

				topPanel.addComponent(buttonControls);
				topPanel.setComponentAlignment(buttonControls,
						Alignment.MIDDLE_CENTER);
				topPanel.setExpandRatio(buttonControls, 1);

				bugWorkflowControl = new HorizontalLayout();
				topPanel.addComponent(bugWorkflowControl);
				topPanel.setComponentAlignment(bugWorkflowControl,
						Alignment.MIDDLE_RIGHT);

				taskListAddLayout.addTopControls(topPanel);

				informationLayout = new GridFormLayoutHelper(2, 11);
				informationLayout.getLayout().setMargin(true);
				taskListAddLayout.addBody(informationLayout.getLayout());

				taskListAddLayout.addBottomControls(createBottomLayout());
				return taskListAddLayout;
			}

			private ComponentContainer createBottomLayout() {
				TabSheet tabBugDetail = new TabSheet();
				tabBugDetail.setWidth("100%");
				tabBugDetail.setStyleName(UIConstants.WHITE_TABSHEET);

				CommentDisplay commentList = new CommentDisplay(
						CommentTypeConstants.PRJ_BUG, bug.getId());
				commentList.setMargin(true);
				tabBugDetail.addTab(commentList, "Comments");

				historyList = new BugHistoryList(bug.getId());
				historyList.setMargin(true);
				tabBugDetail.addTab(historyList, "History");

				bugRelatedItemsList = new BugRelatedItemSheet();
				tabBugDetail.addTab(bugRelatedItemsList, "Related Bugs");

				bugFollowersList = new BugFollowersSheet();
				tabBugDetail.addTab(bugFollowersList, "Followers");

				bugTimeLogList = new BugTimeLogSheet();
				tabBugDetail.addTab(bugTimeLogList, "Time");
				return tabBugDetail;
			}

			@Override
			public void attachField(Object propertyId, Field field) {
				if (propertyId.equals("summary")) {
					informationLayout.addComponent(field, "Summary", 0, 0, 2,
							"100%");
				} else if (propertyId.equals("description")) {
					informationLayout.addComponent(field, "Description", 0, 1,
							2, "100%");
				} else if (propertyId.equals("status")) {
					informationLayout.addComponent(field, "Status", 0, 2);
				} else if (propertyId.equals("priority")) {
					informationLayout.addComponent(field, "Priority", 1, 2);
				} else if (propertyId.equals("severity")) {
					informationLayout.addComponent(field, "Severity", 0, 3);
				} else if (propertyId.equals("resolution")) {
					informationLayout.addComponent(field, "Resolution", 1, 3);
				} else if (propertyId.equals("duedate")) {
					informationLayout.addComponent(field, "Due Date", 0, 4);
				} else if (propertyId.equals("createdTime")) {
					informationLayout.addComponent(field, "Created Time", 1, 4);
				} else if (propertyId.equals("loguserFullName")) {
					informationLayout.addComponent(field, "Logged by", 0, 5);
				} else if (propertyId.equals("assignuserFullName")) {
					informationLayout.addComponent(field, "Assigned to", 1, 5);
				} else if (propertyId.equals("milestoneName")) {
					informationLayout.addComponent(field, "Milestone", 0, 6);
				} else if (propertyId.equals("components")) {
					informationLayout.addComponent(field, "Components", 0, 7,
							2, "100%");
				} else if (propertyId.equals("affectedVersions")) {
					informationLayout.addComponent(field, "Affected Versions",
							0, 8, 2, "100%");
				} else if (propertyId.equals("fixedVersions")) {
					informationLayout.addComponent(field, "Fixed Versions", 0,
							9, 2, "100%");
				} else if (propertyId.equals("id")) {
					informationLayout.addComponent(field, "Attachments", 0, 10,
							2, "100%");
				}
			}
		}

		private class PreviewFormFieldFactory extends
				DefaultFormViewFieldFactory {

			private static final long serialVersionUID = 1L;

			@Override
			protected Field onCreateField(Item item, Object propertyId,
					com.vaadin.ui.Component uiContext) {
				if (propertyId.equals("duedate")) {
					return new FormDateViewField(bug.getDuedate());
				} else if (propertyId.equals("assignuserFullName")) {
					return new ProjectUserFormLinkField(bug.getAssignuser(),
							bug.getAssignuserFullName());
				} else if (propertyId.equals("loguserFullName")) {
					return new ProjectUserFormLinkField(bug.getLogby(),
							bug.getLoguserFullName());
				} else if (propertyId.equals("id")) {
					return new FormAttachmentDisplayField(
							AttachmentConstants.PROJECT_BUG_TYPE, bug.getId());
				} else if (propertyId.equals("components")) {
					if (bug.getComponents() != null) {
						FormContainerViewField componentContainer = new FormContainerViewField();
						for (final Component component : bug.getComponents()) {
							Button componentLink = new Button(
									component.getComponentname(),
									new Button.ClickListener() {
										private static final long serialVersionUID = 1L;

										@Override
										public void buttonClick(ClickEvent event) {
											EventBus.getInstance()
													.fireEvent(
															new BugComponentEvent.GotoRead(
																	BugReadViewImpl.this,
																	component
																			.getId()));
										}
									});
							componentContainer.addComponentField(componentLink);
							componentLink.setStyleName("link");
						}
						componentContainer
								.setStyleName(UIConstants.FORM_CONTAINER_VIEW);
						return componentContainer;
					}
				} else if (propertyId.equals("affectedVersions")) {
					FormContainerViewField componentContainer = new FormContainerViewField();
					for (final Version version : bug.getAffectedVersions()) {
						Button versionLink = new Button(
								version.getVersionname(),
								new Button.ClickListener() {
									private static final long serialVersionUID = 1L;

									@Override
									public void buttonClick(ClickEvent event) {
										EventBus.getInstance().fireEvent(
												new BugVersionEvent.GotoRead(
														BugReadViewImpl.this,
														version.getId()));
									}
								});
						componentContainer.addComponentField(versionLink);
						versionLink.setStyleName("link");
					}
					return componentContainer;
				} else if (propertyId.equals("fixedVersions")) {
					FormContainerViewField componentContainer = new FormContainerViewField();
					for (final Version version : bug.getFixedVersions()) {
						Button versionLink = new Button(
								version.getVersionname(),
								new Button.ClickListener() {
									private static final long serialVersionUID = 1L;

									@Override
									public void buttonClick(ClickEvent event) {
										EventBus.getInstance().fireEvent(
												new BugVersionEvent.GotoRead(
														BugReadViewImpl.this,
														version.getId()));
									}
								});
						componentContainer.addComponentField(versionLink);
						versionLink.setStyleName("link");
					}
					return componentContainer;
				} else if (propertyId.equals("milestoneName")) {
					if (bug.getMilestoneid() != null) {
						FormLinkViewField componentContainer = new FormLinkViewField(
								bug.getMilestoneName(),
								new Button.ClickListener() {
									private static final long serialVersionUID = 1L;

									@Override
									public void buttonClick(ClickEvent event) {
										EventBus.getInstance().fireEvent(
												new MilestoneEvent.GotoRead(
														BugReadViewImpl.this,
														bug.getMilestoneid()));
									}
								});
						return componentContainer;
					} else {
						return new FormViewField("");
					}

				} else if (propertyId.equals("environment")) {
					return new DefaultFormViewFieldFactory.FormViewField(
							bug.getEnvironment(), Label.CONTENT_XHTML);
				} else if (propertyId.equals("description")) {
					return new DefaultFormViewFieldFactory.FormViewField(
							bug.getDescription(), Label.CONTENT_XHTML);
				}
				return null;
			}
		}
	}
}
