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
package com.esofthead.mycollab.module.project.view.milestone;

import com.esofthead.mycollab.common.CommentType;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.domain.Milestone;
import com.esofthead.mycollab.module.project.domain.SimpleMilestone;
import com.esofthead.mycollab.module.project.ui.components.CommentListDepot.CommentDisplay;
import com.esofthead.mycollab.module.project.view.settings.component.ProjectUserFormLinkField;
import com.esofthead.mycollab.schedule.email.project.ProjectMilestoneRelayEmailNotificationAction;
import com.esofthead.mycollab.vaadin.ui.AddViewLayout;
import com.esofthead.mycollab.vaadin.ui.AdvancedPreviewBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultFormViewFieldFactory;
import com.esofthead.mycollab.vaadin.ui.ProgressBar;
import com.esofthead.mycollab.vaadin.ui.ProjectPreviewFormControlsGenerator;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.JavaScript;
import com.vaadin.ui.Layout;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TabSheet.SelectedTabChangeEvent;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class MilestonePreviewBuilder extends VerticalLayout {
	private static final long serialVersionUID = 1L;

	protected SimpleMilestone milestone;
	protected AdvancedPreviewBeanForm<Milestone> previewForm;

	protected CommentDisplay associateCommentListComp;

	protected MilestoneBugListComp associateBugListComp;

	protected MilestoneTaskGroupListComp associateTaskGroupListComp;

	protected void displayBugs() {
		this.associateBugListComp.displayBugs(this.milestone);
	}

	private void displayComments() {
		this.associateCommentListComp.loadComments(CommentType.PRJ_MILESTONE,
				this.milestone.getId());
	}

	protected void displayTaskGroups() {
		this.associateTaskGroupListComp.displayTakLists(this.milestone);
	}

	public SimpleMilestone getMilestone() {
		return this.milestone;
	}

	public AdvancedPreviewBeanForm<Milestone> getPreviewForm() {
		return this.previewForm;
	}

	protected void initRelatedComponents() {
		this.associateBugListComp = new MilestoneBugListComp();
		this.associateTaskGroupListComp = new MilestoneTaskGroupListComp();
	}

	public void previewItem(final SimpleMilestone item) {
		this.milestone = item;
		this.previewForm.setItemDataSource(new BeanItem<Milestone>(
				this.milestone));
		this.displayComments();
	}

	protected class MilestoneFormFieldFactory extends
			DefaultFormViewFieldFactory {

		private static final long serialVersionUID = 1L;

		@Override
		protected Field onCreateField(final Item item, final Object propertyId,
				final Component uiContext) {
			if (propertyId.equals("startdate")) {
				return new DefaultFormViewFieldFactory.FormDateViewField(
						MilestonePreviewBuilder.this.milestone.getStartdate());
			} else if (propertyId.equals("enddate")) {
				return new DefaultFormViewFieldFactory.FormDateViewField(
						MilestonePreviewBuilder.this.milestone.getEnddate());
			} else if (propertyId.equals("owner")) {
				return new ProjectUserFormLinkField(
						MilestonePreviewBuilder.this.milestone.getOwner(),
						MilestonePreviewBuilder.this.milestone
								.getOwnerAvatarId(),
						MilestonePreviewBuilder.this.milestone
								.getOwnerFullName());
			} else if (propertyId.equals("description")) {
				return new DefaultFormViewFieldFactory.FormDetectAndDisplayUrlViewField(
						MilestonePreviewBuilder.this.milestone.getDescription());
			} else if (propertyId.equals("numOpenTasks")) {
				final FormContainerHorizontalViewField taskComp = new FormContainerHorizontalViewField();

				final ProgressBar progressTask = new ProgressBar(
						MilestonePreviewBuilder.this.milestone.getNumTasks(),
						MilestonePreviewBuilder.this.milestone
								.getNumOpenTasks());
				progressTask.setWidth("100%");
				taskComp.addComponentField(progressTask);
				return taskComp;
			} else if (propertyId.equals("numOpenBugs")) {
				final FormContainerHorizontalViewField bugComp = new FormContainerHorizontalViewField();

				final ProgressBar progressBug = new ProgressBar(
						MilestonePreviewBuilder.this.milestone.getNumBugs(),
						MilestonePreviewBuilder.this.milestone.getNumOpenBugs());
				progressBug.setWidth("100%");
				bugComp.addComponentField(progressBug);
				return bugComp;
			}
			return null;
		}
	}

	public static class PrintView extends MilestonePreviewBuilder {
		private static final long serialVersionUID = 1L;

		public PrintView() {
			this.previewForm = new AdvancedPreviewBeanForm<Milestone>() {
				private static final long serialVersionUID = 1L;

				@Override
				public void setItemDataSource(final Item newDataSource) {
					this.setFormLayoutFactory(new FormLayoutFactory());
					this.setFormFieldFactory(new MilestoneFormFieldFactory());
					super.setItemDataSource(newDataSource);
				}
			};
			this.initRelatedComponents();

			this.addComponent(this.previewForm);
		}

		class FormLayoutFactory extends MilestoneFormLayoutFactory {

			private static final long serialVersionUID = 1L;

			public FormLayoutFactory() {
				super(PrintView.this.milestone.getName());
			}

			@Override
			protected Layout createBottomPanel() {
				final VerticalLayout relatedItemsPanel = new VerticalLayout();
				relatedItemsPanel.setWidth("100%");

				relatedItemsPanel
						.addComponent(PrintView.this.associateTaskGroupListComp);
				relatedItemsPanel
						.addComponent(PrintView.this.associateBugListComp);
				PrintView.this.associateCommentListComp = new CommentDisplay(
						CommentType.PRJ_MILESTONE, milestone.getId(),
						CurrentProjectVariables.getProjectId(), false, true,
						ProjectMilestoneRelayEmailNotificationAction.class);
				PrintView.this.associateCommentListComp.setMargin(true);
				relatedItemsPanel
						.addComponent(PrintView.this.associateCommentListComp);

				return relatedItemsPanel;
			}

			@Override
			protected Layout createTopPanel() {
				return null;
			}
		}

		@Override
		public void previewItem(SimpleMilestone item) {
			super.previewItem(item);

			displayTaskGroups();
			displayBugs();
		}
	}

	public static class ReadView extends MilestonePreviewBuilder {

		private static final long serialVersionUID = 1L;
		private final TabSheet tabContainer;
		private final VerticalLayout milestoneInformation;
		private final AddViewLayout milestoneAddLayout;

		private boolean isLoadedRelateBugs = false;
		private boolean isLoadedRelateTasks = false;

		public ReadView() {
			this.milestoneAddLayout = new AddViewLayout("",
					MyCollabResource.newResource("icons/24/project/phase.png"));
			this.milestoneAddLayout.addStyleName(UIConstants.PREVIEW);
			this.addComponent(this.milestoneAddLayout);

			this.initRelatedComponents();

			this.previewForm = new AdvancedPreviewBeanForm<Milestone>() {
				private static final long serialVersionUID = 1L;

				@Override
				public void doPrint() {
					// Create a window that contains what you want to print
					final Window window = new Window("Window to Print");

					final MilestonePreviewBuilder printView = new MilestonePreviewBuilder.PrintView();
					printView.previewItem(ReadView.this.milestone);
					window.setContent(printView);

					UI.getCurrent().addWindow(window);

					// Print automatically when the window opens
					JavaScript.getCurrent().execute(
							"setTimeout(function() {"
									+ "  print(); self.close();}, 0);");
				}

				@Override
				public void setItemDataSource(final Item newDataSource) {
					this.setFormLayoutFactory(new MilestoneFormLayoutFactory.MilestoneInformationLayout());
					this.setFormFieldFactory(new MilestoneFormFieldFactory());
					super.setItemDataSource(newDataSource);
					ReadView.this.milestoneAddLayout
							.setTitle(ReadView.this.milestone.getName());
				}

				@Override
				public void showHistory() {

				}
			};

			this.milestoneInformation = new VerticalLayout();
			this.milestoneInformation.setMargin(false);
			final HorizontalLayout actionControls = new ProjectPreviewFormControlsGenerator<Milestone>(
					this.previewForm)
					.createButtonControls(ProjectRolePermissionCollections.MILESTONES);

			final VerticalLayout marginLayout = new VerticalLayout();
			marginLayout.setMargin(false);
			this.milestoneAddLayout.addTopControls(actionControls);
			marginLayout.addComponent(this.previewForm);

			this.milestoneInformation.addComponent(marginLayout);

			this.tabContainer = new TabSheet();
			this.tabContainer.setWidth("100%");

			this.associateCommentListComp = new CommentDisplay(
					CommentType.PRJ_MILESTONE, null,
					CurrentProjectVariables.getProjectId(), true, true,
					ProjectMilestoneRelayEmailNotificationAction.class);

			this.associateCommentListComp.setMargin(true);
			this.tabContainer.addTab(this.associateCommentListComp, "Comments");
			this.tabContainer.addTab(this.associateTaskGroupListComp,
					"Related Tasks");
			this.tabContainer.addTab(this.associateBugListComp, "Related Bugs");

			this.tabContainer
					.addSelectedTabChangeListener(new TabSheet.SelectedTabChangeListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void selectedTabChange(
								SelectedTabChangeEvent event) {
							if (event.getTabSheet().getSelectedTab() == associateTaskGroupListComp) {
								if (!isLoadedRelateTasks) {
									displayTaskGroups();
									isLoadedRelateTasks = true;
								}

							} else if (event.getTabSheet().getSelectedTab() == associateBugListComp) {
								if (!isLoadedRelateBugs) {
									displayBugs();
									isLoadedRelateBugs = true;
								}
							}

						}
					});

			this.milestoneInformation.addComponent(this.tabContainer);

			this.milestoneAddLayout.addBody(this.milestoneInformation);
		}

		@Override
		public void previewItem(SimpleMilestone item) {
			super.previewItem(item);
			isLoadedRelateBugs = false;
			isLoadedRelateTasks = false;
			tabContainer.setSelectedTab(0);
		}
	}

}
