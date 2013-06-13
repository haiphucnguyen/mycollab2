package com.esofthead.mycollab.module.project.view.bug;

import java.util.GregorianCalendar;
import java.util.List;

import org.vaadin.dialogs.ConfirmDialog;
import org.vaadin.peter.buttongroup.ButtonGroup;

import com.esofthead.mycollab.common.ApplicationProperties;
import com.esofthead.mycollab.common.CommentTypeConstants;
import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.common.ui.components.CommentListDepot.CommentDisplay;
import com.esofthead.mycollab.core.utils.StringUtil;
import com.esofthead.mycollab.module.file.AttachmentConstants;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.events.BugComponentEvent;
import com.esofthead.mycollab.module.project.events.BugEvent;
import com.esofthead.mycollab.module.project.events.BugVersionEvent;
import com.esofthead.mycollab.module.project.events.MilestoneEvent;
import com.esofthead.mycollab.module.project.view.people.component.ProjectUserFormLinkField;
import com.esofthead.mycollab.module.tracker.BugStatusConstants;
import com.esofthead.mycollab.module.tracker.domain.Component;
import com.esofthead.mycollab.module.tracker.domain.SimpleBug;
import com.esofthead.mycollab.module.tracker.domain.Version;
import com.esofthead.mycollab.module.tracker.service.BugNotificationService;
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
import com.esofthead.mycollab.web.LocalizationHelper;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.TabSheet;

@ViewComponent
public class BugReadViewImpl extends AbstractView implements BugReadView,
		IBugCallbackStatusComp {

	private class BugPreviewForm extends AdvancedPreviewBeanForm<SimpleBug> {
		private class FormLayoutFactory implements IFormLayoutFactory {
			private static final long serialVersionUID = 1L;
			private GridFormLayoutHelper informationLayout;

			@Override
			public void attachField(final Object propertyId, final Field field) {
				if (propertyId.equals("summary")) {
					this.informationLayout.addComponent(field, "Summary", 0, 0,
							2, "100%");
				} else if (propertyId.equals("description")) {
					this.informationLayout.addComponent(field, "Description",
							0, 1, 2, "100%");
				} else if (propertyId.equals("status")) {
					this.informationLayout.addComponent(field, "Status", 0, 2);
				} else if (propertyId.equals("priority")) {
					this.informationLayout
							.addComponent(field, "Priority", 1, 2);
				} else if (propertyId.equals("severity")) {
					this.informationLayout
							.addComponent(field, "Severity", 0, 3);
				} else if (propertyId.equals("resolution")) {
					this.informationLayout.addComponent(field, "Resolution", 1,
							3);
				} else if (propertyId.equals("duedate")) {
					this.informationLayout.addComponent(field, "Due Date", 0,
							4, 2, "100%");
				} else if (propertyId.equals("createdTime")) {
					this.informationLayout.addComponent(field, "Created Time",
							1, 4);
				} else if (propertyId.equals("loguserFullName")) {
					this.informationLayout.addComponent(field, "Logged by", 0,
							5);
				} else if (propertyId.equals("assignuserFullName")) {
					this.informationLayout.addComponent(field, "Assigned to",
							1, 5);
				} else if (propertyId.equals("milestoneName")) {
					this.informationLayout.addComponent(field, "Phase", 0, 6,
							2, "100%");
				} else if (propertyId.equals("components")) {
					this.informationLayout.addComponent(field, "Components", 0,
							7, 2, "100%");
				} else if (propertyId.equals("affectedVersions")) {
					this.informationLayout.addComponent(field,
							"Affected Versions", 0, 8, 2, "100%");
				} else if (propertyId.equals("fixedVersions")) {
					this.informationLayout.addComponent(field,
							"Fixed Versions", 0, 9, 2, "100%");
				} else if (propertyId.equals("id")) {
					this.informationLayout.addComponent(field, "Attachments",
							0, 10, 2, "100%");
				}
			}

			private ComponentContainer createBottomLayout() {
				final TabSheet tabBugDetail = new TabSheet();
				tabBugDetail.setWidth("100%");
				// tabBugDetail.setStyleName(UIConstants.WHITE_TABSHEET);

				final CommentDisplay commentList = new CommentDisplay(
						CommentTypeConstants.PRJ_BUG,
						BugReadViewImpl.this.bug.getId(), true, true,
						BugNotificationService.class);
				commentList.setMargin(true);
				tabBugDetail.addTab(commentList, "Comments");

				BugPreviewForm.this.historyList = new BugHistoryList(
						BugReadViewImpl.this.bug.getId());
				BugPreviewForm.this.historyList.setMargin(true);
				tabBugDetail.addTab(BugPreviewForm.this.historyList, "History");

				BugPreviewForm.this.bugRelatedField = new BugRelatedField(
						BugReadViewImpl.this.bug);
				tabBugDetail.addTab(BugPreviewForm.this.bugRelatedField,
						"Related Bugs");

				BugPreviewForm.this.bugFollowersList = new BugFollowersSheet(
						BugReadViewImpl.this.bug);
				tabBugDetail.addTab(BugPreviewForm.this.bugFollowersList,
						"Followers");

				BugPreviewForm.this.bugTimeLogList = new BugTimeLogSheet(
						BugReadViewImpl.this.bug);
				tabBugDetail.addTab(BugPreviewForm.this.bugTimeLogList, "Time");
				return tabBugDetail;
			}

			@Override
			public Layout getLayout() {
				final AddViewLayout bugAddLayout = new AddViewLayout("[Issue "
						+ BugReadViewImpl.this.bug.getBugkey() + "#]: "
						+ BugReadViewImpl.this.bug.getSummary(),
						new ThemeResource("icons/48/project/bug.png"));

				if (BugStatusConstants.CLOSE.equals(BugReadViewImpl.this.bug
						.getStatus())) {
					bugAddLayout.addTitleStyleName(UIConstants.LINK_COMPLETED);
				} else if (BugReadViewImpl.this.bug.getDuedate() != null
						&& (BugReadViewImpl.this.bug.getDuedate()
								.before(new GregorianCalendar().getTime()))) {
					bugAddLayout.addTitleStyleName(UIConstants.LINK_OVERDUE);
				}

				final Button createAccountBtn = new Button("Create",
						new Button.ClickListener() {
							private static final long serialVersionUID = 1L;

							@Override
							public void buttonClick(final ClickEvent event) {
								EventBus.getInstance().fireEvent(
										new BugEvent.GotoAdd(this, null));
							}
						});
				createAccountBtn.setEnabled(CurrentProjectVariables
						.canWrite(ProjectRolePermissionCollections.BUGS));
				createAccountBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
				createAccountBtn.setIcon(new ThemeResource(
						"icons/16/addRecord.png"));

				final HorizontalLayout headerRight = new HorizontalLayout();
				headerRight.addComponent(createAccountBtn);
				headerRight.setMargin(true);
				bugAddLayout.addHeaderRight(headerRight);

				final HorizontalLayout topPanel = new HorizontalLayout();
				topPanel.setSpacing(false);
				topPanel.setMargin(false);
				topPanel.setWidth("100%");

				final HorizontalLayout buttonControls = new HorizontalLayout();
				buttonControls.addStyleName("edit-btn");
				buttonControls.setSpacing(true);

				Button backBtn;
				backBtn = new Button(null, new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(final ClickEvent event) {
						EventBus.getInstance().fireEvent(
								new BugEvent.GotoList(this, null));
					}
				});
				backBtn.setIcon(new ThemeResource("icons/16/back.png"));
				backBtn.setDescription("Back to list");
				backBtn.setStyleName("link");
				topPanel.addComponent(backBtn);
				topPanel.setComponentAlignment(backBtn, Alignment.MIDDLE_LEFT);

				final Button assignBtn = new Button("Assign",
						new Button.ClickListener() {
							private static final long serialVersionUID = 1L;

							@Override
							public void buttonClick(final ClickEvent event) {
								AppContext
										.getApplication()
										.getMainWindow()
										.addWindow(
												new AssignBugWindow(
														BugReadViewImpl.this,
														BugReadViewImpl.this.bug));
							}
						});
				assignBtn.setEnabled(CurrentProjectVariables
						.canWrite(ProjectRolePermissionCollections.BUGS));
				assignBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
				buttonControls.addComponent(assignBtn);
				buttonControls.setComponentAlignment(assignBtn,
						Alignment.MIDDLE_CENTER);

				final Button editBtn = new Button("Edit",
						new Button.ClickListener() {
							private static final long serialVersionUID = 1L;

							@Override
							public void buttonClick(final ClickEvent event) {
								EventBus.getInstance().fireEvent(
										new BugEvent.GotoEdit(
												BugReadViewImpl.this,
												BugReadViewImpl.this.bug));
							}
						});
				editBtn.setEnabled(CurrentProjectVariables
						.canWrite(ProjectRolePermissionCollections.BUGS));
				editBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
				buttonControls.addComponent(editBtn);
				buttonControls.setComponentAlignment(editBtn,
						Alignment.MIDDLE_CENTER);

				final Button deleteBtn = new Button("Delete",
						new Button.ClickListener() {
							private static final long serialVersionUID = 1L;

							@Override
							public void buttonClick(final ClickEvent event) {
								ConfirmDialog.show(
										AppContext.getApplication()
												.getMainWindow(),
										LocalizationHelper
												.getMessage(
														GenericI18Enum.DELETE_DIALOG_TITLE,
														ApplicationProperties
																.getString(ApplicationProperties.SITE_NAME)),
										LocalizationHelper
												.getMessage(GenericI18Enum.CONFIRM_DELETE_RECORD_DIALOG_MESSAGE),
										LocalizationHelper
												.getMessage(GenericI18Enum.BUTTON_YES_LABEL),
										LocalizationHelper
												.getMessage(GenericI18Enum.BUTTON_NO_LABEL),
										new ConfirmDialog.Listener() {
											private static final long serialVersionUID = 1L;

											@Override
											public void onClose(
													final ConfirmDialog dialog) {
												if (dialog.isConfirmed()) {
													final BugService bugService = AppContext
															.getSpringBean(BugService.class);
													bugService
															.removeWithSession(
																	BugReadViewImpl.this.bug
																			.getId(),
																	AppContext
																			.getUsername());
													EventBus.getInstance()
															.fireEvent(
																	new BugEvent.GotoList(
																			BugReadViewImpl.this,
																			BugReadViewImpl.this.bug));
												}
											}
										});
							}
						});
				deleteBtn.setEnabled(CurrentProjectVariables
						.canAccess(ProjectRolePermissionCollections.BUGS));
				deleteBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
				buttonControls.addComponent(deleteBtn);
				buttonControls.setComponentAlignment(deleteBtn,
						Alignment.MIDDLE_CENTER);
				buttonControls.setMargin(false);

				topPanel.addComponent(buttonControls);
				topPanel.setComponentAlignment(buttonControls,
						Alignment.MIDDLE_CENTER);
				topPanel.setExpandRatio(buttonControls, 1);

				BugReadViewImpl.this.bugWorkflowControl = new HorizontalLayout();
				BugReadViewImpl.this.bugWorkflowControl.setMargin(false);
				BugReadViewImpl.this.bugWorkflowControl
						.addStyleName("workflow-controls");
				topPanel.addComponent(BugReadViewImpl.this.bugWorkflowControl);
				topPanel.setComponentAlignment(
						BugReadViewImpl.this.bugWorkflowControl,
						Alignment.MIDDLE_RIGHT);

				bugAddLayout.addTopControls(topPanel);

				this.informationLayout = new GridFormLayoutHelper(2, 11);
				this.informationLayout.getLayout().addStyleName(
						"colored-gridlayout");
				this.informationLayout.getLayout().setMargin(false);
				this.informationLayout.getLayout().setWidth("100%");
				bugAddLayout.addBody(this.informationLayout.getLayout());

				bugAddLayout.addBottomControls(this.createBottomLayout());
				return bugAddLayout;
			}
		}

		private class PreviewFormFieldFactory extends
				DefaultFormViewFieldFactory {

			private static final long serialVersionUID = 1L;

			@Override
			protected Field onCreateField(final Item item,
					final Object propertyId,
					final com.vaadin.ui.Component uiContext) {
				if (propertyId.equals("duedate")) {
					return new FormDateViewField(
							BugReadViewImpl.this.bug.getDuedate());
				} else if (propertyId.equals("assignuserFullName")) {
					return new ProjectUserFormLinkField(
							BugReadViewImpl.this.bug.getAssignuser(),
							BugReadViewImpl.this.bug.getAssignuserFullName());
				} else if (propertyId.equals("loguserFullName")) {
					return new ProjectUserFormLinkField(
							BugReadViewImpl.this.bug.getLogby(),
							BugReadViewImpl.this.bug.getLoguserFullName());
				} else if (propertyId.equals("id")) {
					return new FormAttachmentDisplayField(
							AttachmentConstants.PROJECT_BUG_TYPE,
							BugReadViewImpl.this.bug.getId());
				} else if (propertyId.equals("components")) {
					List<Component> components = BugReadViewImpl.this.bug
							.getComponents();
					if (components != null && components.size() > 0) {
						final FormContainerViewField componentContainer = new FormContainerViewField();
						for (final Component component : BugReadViewImpl.this.bug
								.getComponents()) {
							final Button componentLink = new Button(
									component.getComponentname(),
									new Button.ClickListener() {
										private static final long serialVersionUID = 1L;

										@Override
										public void buttonClick(
												final ClickEvent event) {
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
					List<Version> affectedVersions = BugReadViewImpl.this.bug
							.getAffectedVersions();
					if (affectedVersions != null && affectedVersions.size() > 0) {
						final FormContainerViewField componentContainer = new FormContainerViewField();
						for (final Version version : BugReadViewImpl.this.bug
								.getAffectedVersions()) {
							final Button versionLink = new Button(
									version.getVersionname(),
									new Button.ClickListener() {
										private static final long serialVersionUID = 1L;

										@Override
										public void buttonClick(
												final ClickEvent event) {
											EventBus.getInstance()
													.fireEvent(
															new BugVersionEvent.GotoRead(
																	BugReadViewImpl.this,
																	version.getId()));
										}
									});
							componentContainer.addComponentField(versionLink);
							versionLink.setStyleName("link");
						}
						return componentContainer;
					}
				} else if (propertyId.equals("fixedVersions")) {
					List<Version> fixedVersions = BugReadViewImpl.this.bug
							.getFixedVersions();
					if (fixedVersions != null && fixedVersions.size() > 0) {
						final FormContainerViewField componentContainer = new FormContainerViewField();
						for (final Version version : BugReadViewImpl.this.bug
								.getFixedVersions()) {
							final Button versionLink = new Button(
									version.getVersionname(),
									new Button.ClickListener() {
										private static final long serialVersionUID = 1L;

										@Override
										public void buttonClick(
												final ClickEvent event) {
											EventBus.getInstance()
													.fireEvent(
															new BugVersionEvent.GotoRead(
																	BugReadViewImpl.this,
																	version.getId()));
										}
									});
							componentContainer.addComponentField(versionLink);
							versionLink.setStyleName("link");
						}
						return componentContainer;
					}

				} else if (propertyId.equals("milestoneName")) {
					if (BugReadViewImpl.this.bug.getMilestoneid() != null) {
						final FormLinkViewField componentContainer = new FormLinkViewField(
								BugReadViewImpl.this.bug.getMilestoneName(),
								new Button.ClickListener() {
									private static final long serialVersionUID = 1L;

									@Override
									public void buttonClick(
											final ClickEvent event) {
										EventBus.getInstance()
												.fireEvent(
														new MilestoneEvent.GotoRead(
																BugReadViewImpl.this,
																BugReadViewImpl.this.bug
																		.getMilestoneid()));
									}
								});
						return componentContainer;
					} else {
						return new FormViewField("");
					}

				} else if (propertyId.equals("environment")) {
					return new DefaultFormViewFieldFactory.FormViewField(
							BugReadViewImpl.this.bug.getEnvironment(),
							Label.CONTENT_XHTML);
				} else if (propertyId.equals("description")) {
					return new DefaultFormViewFieldFactory.FormViewField(
							BugReadViewImpl.this.bug.getDescription(),
							Label.CONTENT_XHTML);
				} else if (propertyId.equals("priority")) {
					if (StringUtil.isNotNullOrEmpty(BugReadViewImpl.this.bug
							.getPriority())) {
						final ThemeResource iconPriority = BugPriorityComboBox
								.getIconResourceByPriority(BugReadViewImpl.this.bug
										.getPriority());
						final Embedded iconEmbedded = new Embedded(null,
								iconPriority);
						final Label lbPriority = new Label(
								BugReadViewImpl.this.bug.getPriority());

						final FormContainerHorizontalViewField containerField = new FormContainerHorizontalViewField();
						containerField.addComponentField(iconEmbedded);
						lbPriority.setWidth("220px");
						containerField.addComponentField(lbPriority);
						return containerField;
					}
				} else if (propertyId.equals("severity")) {
					if (StringUtil.isNotNullOrEmpty(BugReadViewImpl.this.bug
							.getSeverity())) {
						final ThemeResource iconPriority = BugSeverityComboBox
								.getIconResourceBySeverity(BugReadViewImpl.this.bug
										.getSeverity());
						final Embedded iconEmbedded = new Embedded(null,
								iconPriority);
						final Label lbPriority = new Label(
								BugReadViewImpl.this.bug.getSeverity());

						final FormContainerHorizontalViewField containerField = new FormContainerHorizontalViewField();
						containerField.addComponentField(iconEmbedded);
						lbPriority.setWidth("220px");
						containerField.addComponentField(lbPriority);
						return containerField;
					}
				}
				return null;
			}
		}

		private static final long serialVersionUID = 1L;
		private BugHistoryList historyList;
		private BugFollowersSheet bugFollowersList;

		private BugTimeLogSheet bugTimeLogList;

		private BugRelatedField bugRelatedField;

		@Override
		public void setItemDataSource(final Item newDataSource) {
			this.setFormLayoutFactory(new FormLayoutFactory());
			this.setFormFieldFactory(new PreviewFormFieldFactory());
			super.setItemDataSource(newDataSource);
			BugReadViewImpl.this.displayWorkflowControl();
		}
	}

	private static final long serialVersionUID = 1L;
	private SimpleBug bug;
	private final BugPreviewForm previewForm;

	private HorizontalLayout bugWorkflowControl;

	public BugReadViewImpl() {
		super();

		this.previewForm = new BugPreviewForm();
		this.addComponent(this.previewForm);
		this.setMargin(true);
	}

	private void displayWorkflowControl() {
		if (BugStatusConstants.OPEN.equals(this.bug.getStatus())
				|| BugStatusConstants.REOPENNED.equals(this.bug.getStatus())) {
			this.bugWorkflowControl.removeAllComponents();
			final ButtonGroup navButton = new ButtonGroup();
			navButton.addButton(new Button("Start Progress",
					new Button.ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(final ClickEvent event) {
							BugReadViewImpl.this.bug
									.setStatus(BugStatusConstants.INPROGRESS);
							final BugService bugService = AppContext
									.getSpringBean(BugService.class);
							bugService.updateWithSession(
									BugReadViewImpl.this.bug,
									AppContext.getUsername());
							BugReadViewImpl.this.displayWorkflowControl();
						}
					}));
			navButton.addButton(new Button("Resolved",
					new Button.ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(final ClickEvent event) {
							AppContext
									.getApplication()
									.getMainWindow()
									.addWindow(
											new ResolvedInputWindow(
													BugReadViewImpl.this,
													BugReadViewImpl.this.bug));
						}
					}));

			navButton.addButton(new Button("Won't Fix",
					new Button.ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(final ClickEvent event) {
							AppContext
									.getApplication()
									.getMainWindow()
									.addWindow(
											new WontFixExplainWindow(
													BugReadViewImpl.this,
													BugReadViewImpl.this.bug));
						}
					}));
			this.bugWorkflowControl.addComponent(navButton);
		} else if (BugStatusConstants.INPROGRESS.equals(this.bug.getStatus())) {
			this.bugWorkflowControl.removeAllComponents();
			final ButtonGroup navButton = new ButtonGroup();
			navButton.addButton(new Button("Stop Progress",
					new Button.ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(final ClickEvent event) {
							BugReadViewImpl.this.bug
									.setStatus(BugStatusConstants.OPEN);
							final BugService bugService = AppContext
									.getSpringBean(BugService.class);
							bugService.updateWithSession(
									BugReadViewImpl.this.bug,
									AppContext.getUsername());
							BugReadViewImpl.this.displayWorkflowControl();
						}
					}));
			navButton.addButton(new Button("Resolved",
					new Button.ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(final ClickEvent event) {
							AppContext
									.getApplication()
									.getMainWindow()
									.addWindow(
											new ResolvedInputWindow(
													BugReadViewImpl.this,
													BugReadViewImpl.this.bug));
						}
					}));
			this.bugWorkflowControl.addComponent(navButton);
		} else if (BugStatusConstants.CLOSE.equals(this.bug.getStatus())) {
			this.bugWorkflowControl.removeAllComponents();
			final ButtonGroup navButton = new ButtonGroup();
			final Button reopenBtn = new Button("Reopen",
					new Button.ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(final ClickEvent event) {
							AppContext
									.getApplication()
									.getMainWindow()
									.addWindow(
											new ReOpenWindow(
													BugReadViewImpl.this,
													BugReadViewImpl.this.bug));
						}
					});
			reopenBtn.setStyleName(UIConstants.THEME_ROUND_BUTTON);
			navButton.addButton(reopenBtn);

			this.bugWorkflowControl.addComponent(navButton);
		} else if (BugStatusConstants.TESTPENDING.equals(this.bug.getStatus())) {
			this.bugWorkflowControl.removeAllComponents();
			final ButtonGroup navButton = new ButtonGroup();
			navButton.addButton(new Button("Reopen",
					new Button.ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(final ClickEvent event) {
							AppContext
									.getApplication()
									.getMainWindow()
									.addWindow(
											new ReOpenWindow(
													BugReadViewImpl.this,
													BugReadViewImpl.this.bug));
						}
					}));
			navButton.addButton(new Button("Approve & Close",
					new Button.ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(final ClickEvent event) {
							AppContext
									.getApplication()
									.getMainWindow()
									.addWindow(
											new ApproveInputWindow(
													BugReadViewImpl.this,
													BugReadViewImpl.this.bug));
						}
					}));
			this.bugWorkflowControl.addComponent(navButton);
		} else if (BugStatusConstants.WONFIX.equals(this.bug.getStatus())) {
			this.bugWorkflowControl.removeAllComponents();
			final ButtonGroup navButton = new ButtonGroup();
			final Button reopenBtn = new Button("Reopen",
					new Button.ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(final ClickEvent event) {
							AppContext
									.getApplication()
									.getMainWindow()
									.addWindow(
											new ReOpenWindow(
													BugReadViewImpl.this,
													BugReadViewImpl.this.bug));
						}
					});
			reopenBtn.setStyleName(UIConstants.THEME_ROUND_BUTTON);
			navButton.addButton(reopenBtn);

			this.bugWorkflowControl.addComponent(navButton);
		}
		this.bugWorkflowControl.setEnabled(CurrentProjectVariables
				.canWrite(ProjectRolePermissionCollections.BUGS));
	}

	@Override
	public SimpleBug getItem() {
		return this.bug;
	}

	@Override
	public void previewItem(final SimpleBug item) {
		this.bug = item;
		this.previewForm.setItemDataSource(new BeanItem<SimpleBug>(this.bug));
	}

	@Override
	public void refreshBugItem() {
		EventBus.getInstance().fireEvent(
				new BugEvent.GotoRead(BugReadViewImpl.this, this.bug.getId()));
	}
}
