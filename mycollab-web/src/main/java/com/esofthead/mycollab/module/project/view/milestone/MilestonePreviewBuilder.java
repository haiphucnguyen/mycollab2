package com.esofthead.mycollab.module.project.view.milestone;

import com.esofthead.mycollab.common.CommentTypeConstants;
import com.esofthead.mycollab.common.ui.components.CommentListDepot.CommentDisplay;
import com.esofthead.mycollab.module.project.domain.Milestone;
import com.esofthead.mycollab.module.project.domain.SimpleMilestone;
import com.esofthead.mycollab.module.project.view.people.component.ProjectUserFormLinkField;
import com.esofthead.mycollab.vaadin.ui.AddViewLayout;
import com.esofthead.mycollab.vaadin.ui.AdvancedPreviewBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultFormViewFieldFactory;
import com.esofthead.mycollab.vaadin.ui.ProjectPreviewFormControlsGenerator;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.terminal.ExternalResource;
import com.vaadin.terminal.Sizeable;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.ProgressIndicator;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class MilestonePreviewBuilder extends VerticalLayout {
	protected class MilestoneFormFieldFactory extends
			DefaultFormViewFieldFactory {

		private static final long serialVersionUID = 1L;

		@Override
		protected Field onCreateField(final Item item, final Object propertyId,
				final Component uiContext) {
			if (propertyId.equals("startdate")) {
				return new DefaultFormViewFieldFactory.FormDateViewField(
						milestone.getStartdate());
			} else if (propertyId.equals("enddate")) {
				return new DefaultFormViewFieldFactory.FormDateViewField(
						milestone.getEnddate());
			} else if (propertyId.equals("owner")) {
				return new ProjectUserFormLinkField(milestone.getOwner(),
						milestone.getOwnerFullName());
			} else if (propertyId.equals("description")) {
				return new DefaultFormViewFieldFactory.FormViewField(
						milestone.getDescription(), Label.CONTENT_XHTML);
			} else if (propertyId.equals("numOpenTasks")) {
				final FormContainerHorizontalViewField taskComp = new FormContainerHorizontalViewField();

				final ProgressIndicator progressTask = new ProgressIndicator(
						new Float((float) (milestone.getNumTasks() - milestone
								.getNumOpenTasks()) / milestone.getNumTasks()));
				progressTask.setPollingInterval(1000000000);
				progressTask.setWidth("120px");
				taskComp.addComponentField(progressTask);
				final Label taskNumber = new Label("("
						+ milestone.getNumOpenTasks() + "/"
						+ milestone.getNumTasks() + ")");
				taskNumber.setWidth("90px");
				taskComp.addComponentField(taskNumber);
				return taskComp;
			} else if (propertyId.equals("numOpenBugs")) {
				final FormContainerHorizontalViewField bugComp = new FormContainerHorizontalViewField();

				final ProgressIndicator progressBug = new ProgressIndicator(
						new Float((float) (milestone.getNumBugs() - milestone
								.getNumOpenBugs()) / milestone.getNumBugs()));
				progressBug.setPollingInterval(1000000000);
				progressBug.setWidth("120px");
				bugComp.addComponentField(progressBug);
				final Label bugNumber = new Label("("
						+ milestone.getNumOpenBugs() + "/"
						+ milestone.getNumBugs() + ")");
				bugNumber.setWidth("90px");
				bugComp.addComponentField(bugNumber);
				return bugComp;
			}
			return null;
		}
	}

	public static class PrintView extends MilestonePreviewBuilder {
		class FormLayoutFactory extends MilestoneFormLayoutFactory {

			private static final long serialVersionUID = 1L;

			public FormLayoutFactory() {
				super(milestone.getName());
			}

			@Override
			protected Layout createBottomPanel() {
				final VerticalLayout relatedItemsPanel = new VerticalLayout();
				relatedItemsPanel.setWidth("100%");

				relatedItemsPanel.addComponent(associateTaskGroupListComp);
				relatedItemsPanel.addComponent(associateBugListComp);
				associateCommentListComp = new CommentDisplay(true);
				associateCommentListComp.setMargin(true);
				relatedItemsPanel.addComponent(associateCommentListComp);

				return relatedItemsPanel;
			}

			@Override
			protected Layout createTopPanel() {
				return null;
			}
		}

		private static final long serialVersionUID = 1L;

		public PrintView() {
			previewForm = new AdvancedPreviewBeanForm<Milestone>() {
				private static final long serialVersionUID = 1L;

				@Override
				public void setItemDataSource(final Item newDataSource) {
					setFormLayoutFactory(new FormLayoutFactory());
					setFormFieldFactory(new MilestoneFormFieldFactory());
					super.setItemDataSource(newDataSource);
				}
			};
			initRelatedComponents();

			this.addComponent(previewForm);
		}
	}

	public static class ReadView extends MilestonePreviewBuilder {

		private static final long serialVersionUID = 1L;
		private final TabSheet tabContainer;
		private final VerticalLayout milestoneInformation;
		private final AddViewLayout milestoneAddLayout;

		public ReadView() {
			milestoneAddLayout = new AddViewLayout("", new ThemeResource(
					"icons/48/project/milestone.png"));
			milestoneAddLayout.addStyleName(UIConstants.PREVIEW);
			this.addComponent(milestoneAddLayout);

			initRelatedComponents();

			previewForm = new AdvancedPreviewBeanForm<Milestone>() {
				private static final long serialVersionUID = 1L;

				@Override
				protected void doPrint() {
					// Create a window that contains what you want to print
					final Window window = new Window("Window to Print");

					final MilestonePreviewBuilder printView = new MilestonePreviewBuilder.PrintView();
					printView.previewItem(milestone);
					window.addComponent(printView);

					// Add the printing window as a new application-level window
					getApplication().addWindow(window);

					// Open it as a popup window with no decorations
					getWindow().open(new ExternalResource(window.getURL()),
							"_blank", 1100, 200, // Width and height
							Window.BORDER_NONE); // No decorations

					// Print automatically when the window opens.
					// This call will block until the print dialog exits!
					window.executeJavaScript("print();");

					// Close the window automatically after printing
					window.executeJavaScript("self.close();");
				}

				@Override
				public void setItemDataSource(final Item newDataSource) {
					setFormLayoutFactory(new MilestoneFormLayoutFactory.MilestoneInformationLayout());
					setFormFieldFactory(new MilestoneFormFieldFactory());
					super.setItemDataSource(newDataSource);
					milestoneAddLayout.setTitle(milestone.getName());
				}

				@Override
				protected void showHistory() {

				}
			};

			milestoneInformation = new VerticalLayout();
			milestoneInformation.setMargin(false);
			final HorizontalLayout actionControls = new ProjectPreviewFormControlsGenerator<Milestone>(
					previewForm).createButtonControls();

			final Button reportBtn = new Button(null,
					new Button.ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(final ClickEvent event) {
							// TODO Auto-generated method stub

						}

					});
			reportBtn.setIcon(new ThemeResource(
					"icons/16/project/release_note.png"));
			reportBtn.setStyleName("link");
			reportBtn.setDescription("Display Report View");

			actionControls.addComponent(reportBtn);
			actionControls.setComponentAlignment(reportBtn,
					Alignment.MIDDLE_LEFT);

			final VerticalLayout marginLayout = new VerticalLayout();
			marginLayout.setMargin(false);
			milestoneAddLayout.addTopControls(actionControls);
			marginLayout.addComponent(previewForm);

			milestoneInformation.addComponent(marginLayout);

			tabContainer = new TabSheet();
			// tabContainer.setStyleName(UIConstants.WHITE_TABSHEET);
			tabContainer.setWidth("100%");
			tabContainer.setHeight(Sizeable.SIZE_UNDEFINED, 0);

			associateCommentListComp = new CommentDisplay(true);
			associateCommentListComp.setMargin(true);
			tabContainer.addTab(associateCommentListComp, "Comments");
			tabContainer.addTab(associateTaskGroupListComp, "Related Tasks");
			tabContainer.addTab(associateBugListComp, "Related Bugs");

			milestoneInformation.addComponent(tabContainer);

			milestoneAddLayout.addBody(milestoneInformation);
		}
	}

	private static final long serialVersionUID = 1L;
	protected SimpleMilestone milestone;
	protected AdvancedPreviewBeanForm<Milestone> previewForm;

	protected CommentDisplay associateCommentListComp;

	protected MilestoneBugListComp associateBugListComp;

	protected MilestoneTaskGroupListComp associateTaskGroupListComp;

	private void displayBugs() {
		associateBugListComp.displayBugs(milestone);
	}

	private void displayComments() {
		associateCommentListComp.loadComments(
				CommentTypeConstants.PRJ_MILESTONE, milestone.getId());
	}

	private void displayTaskGroups() {
		associateTaskGroupListComp.displayTakLists(milestone);
	}

	public SimpleMilestone getMilestone() {
		return milestone;
	}

	public AdvancedPreviewBeanForm<Milestone> getPreviewForm() {
		return previewForm;
	}

	protected void initRelatedComponents() {
		associateBugListComp = new MilestoneBugListComp();
		associateTaskGroupListComp = new MilestoneTaskGroupListComp();
	}

	public void previewItem(final SimpleMilestone item) {
		milestone = item;
		previewForm.setItemDataSource(new BeanItem<Milestone>(milestone));

		displayComments();
		displayTaskGroups();
		displayBugs();
	}
}
