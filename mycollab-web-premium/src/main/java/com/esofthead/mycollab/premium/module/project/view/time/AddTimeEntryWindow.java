package com.esofthead.mycollab.premium.module.project.view.time;

import java.util.Date;
import java.util.GregorianCalendar;

import org.vaadin.hene.popupbutton.PopupButton;

import com.esofthead.mycollab.core.UserInvalidInputException;
import com.esofthead.mycollab.module.project.view.settings.component.ProjectMemberSelectionBox;
import com.esofthead.mycollab.vaadin.ui.StandupStyleCalendarExp;
import com.esofthead.vaadin.popupbutton.PopupButtonExt;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.InlineDateField;
import com.vaadin.ui.Label;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.RichTextArea;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.0
 * 
 */
public class AddTimeEntryWindow extends Window {
	private static final long serialVersionUID = 1L;

	private static String[] DAY_IN_WEEK = { "Monday", "Tuesday", "Wednesday",
			"Thursday", "Friday", "Saturday", "Sunday" };

	private PopupDateField weekSelectionCalendar;
	private CheckBox isBillableCheckBox;
	private Table timeInputTable;
	private ProjectMemberSelectionBox projectMemberSelectionBox;
	private RichTextArea descArea;
	private Date selectedDate;

	public AddTimeEntryWindow(TimeTrackingListView view) {
		this.setModal(true);
		this.setCaption("Log time on this project");

		VerticalLayout content = new VerticalLayout();
		content.setSpacing(true);
		content.setMargin(true);
		GridLayout grid = new GridLayout(3, 2);
		grid.setSpacing(true);
		content.addComponent(grid);

		grid.addComponent(new Label("Who"), 0, 0);
		grid.addComponent(new Label("Week"), 1, 0);
		Label billableTitle = new Label("Is Billable");
		billableTitle.setWidth("100px");
		grid.addComponent(billableTitle, 2, 0);

		projectMemberSelectionBox = new ProjectMemberSelectionBox();
		grid.addComponent(projectMemberSelectionBox, 0, 1);

		weekSelectionCalendar = new PopupDateField();
		weekSelectionCalendar.setInputPrompt("Select week");
		weekSelectionCalendar.addValueChangeListener(
				new ValueChangeListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void valueChange(ValueChangeEvent event) {
						Date dateVal = weekSelectionCalendar.getValue();
						System.out.println("DATE: " + dateVal);
					}
				});
		grid.addComponent(weekSelectionCalendar, 1, 1);

		isBillableCheckBox = new CheckBox();
		grid.addComponent(isBillableCheckBox, 2, 1);

		timeInputTable = new Table();
		timeInputTable.setImmediate(true);
		timeInputTable.addContainerProperty("Monday", Double.class, 0);
		timeInputTable.addContainerProperty("Tuesday", Double.class, 0);
		timeInputTable.addContainerProperty("Wednesday", Double.class, 0);
		timeInputTable.addContainerProperty("Thursday", Double.class, 0);
		timeInputTable.addContainerProperty("Friday", Double.class, 0);
		timeInputTable.addContainerProperty("Saturday", Double.class, 0);
		timeInputTable.addContainerProperty("Sunday", Double.class, 0);

		timeInputTable.addItem(new Double[] { 0d, 0d, 0d, 0d, 0d, 0d, 0d },
				"timeEntry");
		timeInputTable.setEditable(true);
		content.addComponent(timeInputTable);

		Label descriptionLbl = new Label("Description");
		content.addComponent(descriptionLbl);

		descArea = new RichTextArea();
		descArea.setWidth("100%");
		content.addComponent(descArea);

		HorizontalLayout taskLayout = new HorizontalLayout();
		Button attachTaskBtn = new Button("Link with task");
		taskLayout.addComponent(attachTaskBtn);
		content.addComponent(taskLayout);

		HorizontalLayout controlsLayout = new HorizontalLayout();
		controlsLayout.setSpacing(true);
		Button saveBtn = new Button("Log time", new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				saveTimeLoggingItems();
				AddTimeEntryWindow.this.close();
			}
		});
		controlsLayout.addComponent(saveBtn);

		Button cancelBtn = new Button("Cancel", new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				AddTimeEntryWindow.this.close();
			}
		});
		controlsLayout.addComponent(cancelBtn);

		content.addComponent(controlsLayout);
		this.setContent(content);
		this.center();
	}

	private void calculateDateInWeek() {

	}

	private void saveTimeLoggingItems() {
		String user = (String) projectMemberSelectionBox.getValue();
		if (user == null) {
			throw new UserInvalidInputException("You must select a member");
		}

		Boolean isBillable = isBillableCheckBox.getValue();
		String desc = descArea.getValue();
		Object value = timeInputTable.getValue();
		Item timeEntries = (Item) timeInputTable.getItem("timeEntry");
		Property itemProperty = timeEntries.getItemProperty("Monday");
		Double timeVal = (Double) itemProperty.getValue();
		System.out.println("Hello: " + timeVal);
	}
}
