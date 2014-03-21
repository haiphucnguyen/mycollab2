package com.esofthead.mycollab.premium.module.project.view.time;

import com.esofthead.mycollab.module.project.view.settings.component.ProjectMemberSelectionBox;
import com.esofthead.mycollab.vaadin.ui.NumberField;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.DateField;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.RichTextArea;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
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

	public AddTimeEntryWindow(TimeTrackingListView view) {
		this.setCaption("Log time on this project");

		VerticalLayout content = new VerticalLayout();
		content.setSpacing(true);
		content.setMargin(true);
		GridLayout grid = new GridLayout(3, 2);
		grid.setSpacing(true);
		content.addComponent(grid);

		grid.addComponent(new Label("Who"), 0, 0);
		grid.addComponent(new Label("Week"), 1, 0);
		grid.addComponent(new Label("Time Spent"), 2, 0);

		grid.addComponent(new ProjectMemberSelectionBox(), 0, 1);
		grid.addComponent(new DateField(), 1, 1);
		NumberField logValue = new NumberField();
		grid.addComponent(logValue, 2, 1);

		Table timeInputTable = new Table();
		timeInputTable.addContainerProperty("Monday", Double.class, 0);
		timeInputTable.addContainerProperty("Tuesday", Double.class, 0);
		timeInputTable.addContainerProperty("Wednesday", Double.class, 0);
		timeInputTable.addContainerProperty("Thursday", Double.class, 0);
		timeInputTable.addContainerProperty("Friday", Double.class, 0);
		timeInputTable.addContainerProperty("Saturday", Double.class, 0);
		timeInputTable.addContainerProperty("Sunday", Double.class, 0);

		timeInputTable.addItem(new Double[] { 0d, 0d, 0d, 0d, 0d, 0d, 0d });
		timeInputTable.setEditable(true);
		content.addComponent(timeInputTable);

		Label descriptionLbl = new Label("Description");
		content.addComponent(descriptionLbl);

		RichTextArea desc = new RichTextArea();
		desc.setWidth("100%");
		content.addComponent(desc);

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
}
