package com.esofthead.mycollab.module.project.view.milestone;

import com.esofthead.mycollab.common.CommentTypeConstants;
import com.esofthead.mycollab.common.ui.components.CommentListDepot;
import com.esofthead.mycollab.module.project.domain.Milestone;
import com.esofthead.mycollab.module.project.domain.SimpleMilestone;
import com.esofthead.mycollab.module.project.view.people.component.ProjectUserFormLinkField;
import com.esofthead.mycollab.vaadin.ui.AddViewLayout;
import com.esofthead.mycollab.vaadin.ui.AdvancedPreviewBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultFormViewFieldFactory;
import com.esofthead.mycollab.vaadin.ui.PreviewFormControlsGenerator;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.terminal.ExternalResource;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class MilestonePreviewBuilder extends VerticalLayout {
	private static final long serialVersionUID = 1L;

	protected SimpleMilestone milestone;
	protected AdvancedPreviewBeanForm<Milestone> previewForm;
	protected CommentListDepot associateCommentListComp;
	protected MilestoneBugListComp associateBugListComp;
	protected MilestoneTaskGroupListComp associateTaskGroupListComp;

	protected void initRelatedComponents() {
		associateBugListComp = new MilestoneBugListComp();
		associateTaskGroupListComp = new MilestoneTaskGroupListComp();
	}

	public void previewItem(SimpleMilestone item) {
		this.milestone = item;
		previewForm.setItemDataSource(new BeanItem<Milestone>(milestone));

		displayComments();
		displayTaskGroups();
		displayBugs();
	}

	private void displayComments() {
		associateCommentListComp.loadComments(
				CommentTypeConstants.PRJ_MILESTONE, milestone.getId());
	}

	private void displayTaskGroups() {
		associateTaskGroupListComp.displayTakLists(milestone);
	}

	private void displayBugs() {
		associateBugListComp.displayBugs(milestone);
	}

	public SimpleMilestone getMilestone() {
		return milestone;
	}

	public AdvancedPreviewBeanForm<Milestone> getPreviewForm() {
		return previewForm;
	}

	protected class MilestoneFormFieldFactory extends
			DefaultFormViewFieldFactory {

		private static final long serialVersionUID = 1L;

		@Override
		protected Field onCreateField(Item item, Object propertyId,
				Component uiContext) {
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
			}
			return null;
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
				public void setItemDataSource(Item newDataSource) {
					this.setFormLayoutFactory(new MilestoneFormLayoutFactory.MilestoneInformationLayout());
					this.setFormFieldFactory(new MilestoneFormFieldFactory());
					super.setItemDataSource(newDataSource);
					milestoneAddLayout.setTitle(milestone.getName());
				}

				@Override
				protected void doPrint() {
					// Create a window that contains what you want to print
					Window window = new Window("Window to Print");

					MilestonePreviewBuilder printView = new MilestonePreviewBuilder.PrintView();
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
				protected void showHistory() {

				}
			};

			milestoneInformation = new VerticalLayout();
			milestoneInformation.setMargin(true);
			HorizontalLayout actionControls = new PreviewFormControlsGenerator<Milestone>(
					previewForm).createButtonControls();

			Button reportBtn = new Button(null, new Button.ClickListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(ClickEvent event) {
					// TODO Auto-generated method stub

				}

			});
			reportBtn.setIcon(new ThemeResource("icons/16/statistic.png"));
			reportBtn.setStyleName("link");
			reportBtn.setDescription("Display Report View");

			actionControls.addComponent(reportBtn);

			milestoneInformation.addComponent(actionControls);
			milestoneInformation.addComponent(previewForm);

			tabContainer = new TabSheet();
			tabContainer.setStyleName(UIConstants.WHITE_TABSHEET);
			tabContainer.setWidth("100%");

			associateCommentListComp = new CommentListDepot(true);
			tabContainer.addTab(associateCommentListComp, "Comments");
			tabContainer.addTab(associateTaskGroupListComp, "Related Tasks");
			tabContainer.addTab(associateBugListComp, "Related Bugs");

			milestoneInformation.addComponent(tabContainer);

			milestoneAddLayout.addBody(milestoneInformation);
		}
	}

	public static class PrintView extends MilestonePreviewBuilder {
		private static final long serialVersionUID = 1L;

		public PrintView() {
			previewForm = new AdvancedPreviewBeanForm<Milestone>() {
				private static final long serialVersionUID = 1L;

				@Override
				public void setItemDataSource(Item newDataSource) {
					this.setFormLayoutFactory(new FormLayoutFactory());
					this.setFormFieldFactory(new MilestoneFormFieldFactory());
					super.setItemDataSource(newDataSource);
				}
			};
			initRelatedComponents();

			this.addComponent(previewForm);
		}

		class FormLayoutFactory extends MilestoneFormLayoutFactory {

			private static final long serialVersionUID = 1L;

			public FormLayoutFactory() {
				super(milestone.getName());
			}

			@Override
			protected Layout createTopPanel() {
				return null;
			}

			@Override
			protected Layout createBottomPanel() {
				VerticalLayout relatedItemsPanel = new VerticalLayout();
				relatedItemsPanel.setWidth("100%");

				relatedItemsPanel.addComponent(associateTaskGroupListComp);
				relatedItemsPanel.addComponent(associateBugListComp);
				associateCommentListComp = new CommentListDepot(true);
				relatedItemsPanel.addComponent(associateCommentListComp);

				return relatedItemsPanel;
			}
		}
	}
}
