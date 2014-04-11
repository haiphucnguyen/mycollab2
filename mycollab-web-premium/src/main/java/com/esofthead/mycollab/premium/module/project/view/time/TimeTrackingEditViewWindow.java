package com.esofthead.mycollab.premium.module.project.view.time;

import com.esofthead.mycollab.core.utils.StringUtils;
import com.esofthead.mycollab.module.project.domain.ProjectGenericTask;
import com.esofthead.mycollab.module.project.domain.SimpleItemTimeLogging;
import com.esofthead.mycollab.module.project.domain.SimpleProjectMember;
import com.esofthead.mycollab.module.project.view.settings.component.ProjectMemberSelectionBox;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.RichTextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.0
 * 
 */
public class TimeTrackingEditViewWindow extends Window implements
		AssignmentSelectableComp {
	private static final long serialVersionUID = 1L;

	private CheckBox isBillableCheckBox;
	private ProjectMemberSelectionBox projectMemberSelectionBox;
	private RichTextArea descArea;
	private TimeTrackingListView parentView;
	private ProjectGenericTask selectionTask;
	private HorizontalLayout taskLayout;
	private DateField dateField;
	private TextField timeField;
	private SimpleItemTimeLogging item;
	private ObjectProperty<Double> property;

	public TimeTrackingEditViewWindow(TimeTrackingListView view,
			SimpleItemTimeLogging itemInput) {
		this.item = itemInput;
		this.setModal(true);
		this.parentView = view;

		this.setCaption("Log time on this project");

		dateField = new DateField("Select date:", this.item.getLogforday());

		property = new ObjectProperty<Double>(item.getLogvalue());
		timeField = new TextField("Hours:", property);

		projectMemberSelectionBox = new ProjectMemberSelectionBox();
		projectMemberSelectionBox.setValue(this.item.getLoguser());
		projectMemberSelectionBox.setCaption("Who:");

		isBillableCheckBox = new CheckBox();
		isBillableCheckBox.setValue(this.item.getIsbillable());

		VerticalLayout content = new VerticalLayout();
		content.setSpacing(true);
		content.setMargin(true);
		GridLayout grid = new GridLayout(4, 1);
		grid.setMargin(new MarginInfo(false, false, true, false));
		grid.setSpacing(true);

		HorizontalLayout isBillable = new HorizontalLayout();
		isBillable.setSpacing(true);
		isBillable.setDefaultComponentAlignment(Alignment.BOTTOM_LEFT);
		Label billableTitle = new Label("Is Billable:");
		isBillable.addComponent(billableTitle);
		isBillable.addComponent(isBillableCheckBox);
		isBillable.setCaption("");

		grid.addComponent(projectMemberSelectionBox, 0, 0);
		grid.addComponent(dateField, 1, 0);
		grid.addComponent(timeField, 2, 0);
		grid.addComponent(isBillable, 3, 0);

		content.addComponent(grid);

		Label descriptionLbl = new Label("Description");
		content.addComponent(descriptionLbl);

		descArea = new RichTextArea();
		descArea.setValue(this.item.getNote());
		descArea.setWidth("100%");
		content.addComponent(descArea);
		HorizontalLayout footer = new HorizontalLayout();
		taskLayout = new HorizontalLayout();
		taskLayout.setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);
		taskLayout.setSpacing(true);
		createLinkTaskButton();
		
		String name = new String();
		if (this.item.getType() != null && this.item.getTypeid() != null) {
			ProjectGenericTask tempSelectionTask = new ProjectGenericTask();
			tempSelectionTask.setType(this.item.getType());
			tempSelectionTask.setTypeId(this.item.getTypeid());
			name = new GenericTaskDetailMapper(tempSelectionTask.getType(),
					tempSelectionTask.getTypeId()).getName();

			tempSelectionTask.setName(name);
			updateLinkTask(tempSelectionTask);
		}
		
		footer.addComponent(taskLayout);

		HorizontalLayout controlsLayout = new HorizontalLayout();
		controlsLayout.setSpacing(true);
		Button saveBtn = new Button("Log time", new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				saveTimeLoggingItems();
				TimeTrackingEditViewWindow.this.close();
			}
		});
		saveBtn.addStyleName(UIConstants.THEME_GREEN_LINK);
		controlsLayout.addComponent(saveBtn);

		Button cancelBtn = new Button("Cancel", new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				TimeTrackingEditViewWindow.this.close();
			}
		});
		cancelBtn.addStyleName(UIConstants.THEME_BLANK_LINK);
		controlsLayout.addComponent(cancelBtn);

		footer.addComponent(controlsLayout);
		footer.setSizeFull();
		footer.setComponentAlignment(controlsLayout, Alignment.TOP_RIGHT);
		content.addComponent(footer);
		this.setContent(content);
		this.center();
	}

	public void updateLinkTask(ProjectGenericTask selectionTask) {
		this.selectionTask = selectionTask;
		if (this.selectionTask != null) {
			final String taskName = this.selectionTask.getName();
			taskLayout.removeAllComponents();

			Button detachTaskBtn = new Button("Detach",
					new Button.ClickListener() {

						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(ClickEvent event) {
							createLinkTaskButton();
							updateLinkTask(null);
						}
					});
			detachTaskBtn.setStyleName(UIConstants.THEME_RED_LINK);
			taskLayout.addComponent(detachTaskBtn);

			Label attachTaskBtn = new Label(
					StringUtils.trim(taskName, 40, true));

			attachTaskBtn.addStyleName("task-attached");
			attachTaskBtn.setWidth("300px");

			attachTaskBtn.setDescription(new ProjectGenericTaskTooltipGenerator(
					this.selectionTask.getType(), this.selectionTask
							.getTypeId()).getContent());
			taskLayout.addComponent(attachTaskBtn);
			this.selectionTask.getTypeId();
		}
	}

	private void createLinkTaskButton() {
		taskLayout.removeAllComponents();
		Button attachTaskBtn = new Button("Link with task",
				new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(ClickEvent event) {

						ProjectGenericTaskSelectionWindow selectionTaskWindow = new ProjectGenericTaskSelectionWindow(
								TimeTrackingEditViewWindow.this);
						TimeTrackingEditViewWindow.this.getUI().addWindow(
								selectionTaskWindow);
					}
				});
		attachTaskBtn.addStyleName(UIConstants.THEME_GREEN_LINK);

		taskLayout.addComponent(attachTaskBtn);
	}

	private void saveTimeLoggingItems() {

		SimpleProjectMember user = (SimpleProjectMember) projectMemberSelectionBox
				.getValue();
		item.setCreateduser(AppContext.getUsername());
		item.setLoguser(user.getUsername());
		item.setLogUserFullName(user.getMemberFullName());
		item.setLogUserAvatarId(user.getMemberAvatarId());
		item.setLogforday(dateField.getValue());
		item.setLogvalue(property.getValue());
		item.setIsbillable(isBillableCheckBox.getValue());
		item.setNote(descArea.getValue());
		if (selectionTask != null) {
			item.setType(selectionTask.getType());
			item.setTypeid(selectionTask.getTypeId());
		}

		parentView.refresh();
	}
}
