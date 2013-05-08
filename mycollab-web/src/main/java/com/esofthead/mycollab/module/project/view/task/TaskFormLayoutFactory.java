/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.task;

import java.util.ArrayList;
import java.util.List;

import com.esofthead.mycollab.module.project.ui.components.ProjectUiUtils;
import com.esofthead.mycollab.vaadin.ui.AddViewLayout;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.vaadin.ui.IFormLayoutFactory;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.Field;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * @author haiphucnguyen
 */
public abstract class TaskFormLayoutFactory implements IFormLayoutFactory {
	private static final long serialVersionUID = 1L;
	private final String title;
	private TaskInformationLayout informationLayout;

	private List<String> lstStyleTitle = new ArrayList<String>();

	public TaskFormLayoutFactory(String title) {
		this.title = title;
	}

	@Override
	public Layout getLayout() {
		AddViewLayout taskAddLayout = new AddViewLayout(title,
				new ThemeResource("icons/48/project/task.png"));

		for (int i = 0; i < lstStyleTitle.size(); i++) {
			taskAddLayout.addTitleStyleName(lstStyleTitle.get(i));
		}

		ComponentContainer topPanel = createTopPanel();
		if (topPanel != null) {
			taskAddLayout.addTopControls(topPanel);
		}

		informationLayout = new TaskInformationLayout();
		taskAddLayout.addBody(informationLayout.getLayout());

		ComponentContainer bottomPanel = createBottomPanel();
		if (bottomPanel != null) {
			taskAddLayout.addBottomControls(bottomPanel);
		}

		return taskAddLayout;
	}

	protected void addTitleStyle(String styleName) {
		lstStyleTitle.add(styleName);
	}

	@Override
	public void attachField(Object propertyId, Field field) {
		informationLayout.attachField(propertyId, field);
	}

	protected abstract ComponentContainer createTopPanel();

	protected abstract ComponentContainer createBottomPanel();

	@SuppressWarnings("serial")
	public static class TaskInformationLayout implements IFormLayoutFactory {

		private GridFormLayoutHelper informationLayout;

		@Override
		public Layout getLayout() {
			informationLayout = ProjectUiUtils.getGridFormLayoutHelper(2, 8);
			VerticalLayout layout = new VerticalLayout();
			layout.setMargin(true);
			layout.addComponent(informationLayout.getLayout());
			layout.setComponentAlignment(informationLayout.getLayout(),
					Alignment.BOTTOM_CENTER);
			return layout;
		}

		@Override
		public void attachField(Object propertyId, Field field) {
			if (propertyId.equals("taskname")) {
				informationLayout.addComponent(field, "Task Name", 0, 0, 2,
						"100%");
			} else if (propertyId.equals("startdate")) {
				informationLayout.addComponent(field, "Start Date", 0, 1);
			} else if (propertyId.equals("enddate")) {
				informationLayout.addComponent(field, "End Date", 0, 2);
			} else if (propertyId.equals("actualstartdate")) {
				informationLayout
						.addComponent(field, "Actual Start Date", 1, 1);
			} else if (propertyId.equals("actualenddate")) {
				informationLayout.addComponent(field, "Actual End Date", 1, 2);
			} else if (propertyId.equals("deadline")) {
				informationLayout.addComponent(field, "Deadline", 0, 3);
			} else if (propertyId.equals("priority")) {
				informationLayout.addComponent(field, "Priority", 1, 3);
			} else if (propertyId.equals("assignuser")) {
				informationLayout.addComponent(field, "Assign", 0, 4);
			} else if (propertyId.equals("tasklistid")) {
				informationLayout.addComponent(field, "Task List", 1, 4);
			} else if (propertyId.equals("percentagecomplete")) {
				informationLayout.addComponent(field, "Complete(%)", 0, 5);
			} else if (propertyId.equals("notes")) {
				field.setSizeUndefined();
				informationLayout.addComponent(field, "Notes", 0, 6, 2,
						UIConstants.DEFAULT_2XCONTROL_WIDTH);
			} else if (propertyId.equals("id")) {
				informationLayout.addComponent(field, "Attachments", 0, 7, 2,
						"100%");
			}
		}
	}
}
