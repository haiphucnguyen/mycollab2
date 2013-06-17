/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.task;

import java.util.ArrayList;
import java.util.List;

import com.esofthead.mycollab.vaadin.ui.AddViewLayout;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.vaadin.ui.IFormLayoutFactory;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.web.MyCollabResource;
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

	private final List<String> lstStyleTitle = new ArrayList<String>();

	public TaskFormLayoutFactory(final String title) {
		this.title = title;
	}

	@Override
	public Layout getLayout() {
		final AddViewLayout taskAddLayout = new AddViewLayout(this.title,
				MyCollabResource.newResource("icons/24/project/task.png"));

		for (int i = 0; i < this.lstStyleTitle.size(); i++) {
			taskAddLayout.addTitleStyleName(this.lstStyleTitle.get(i));
		}

		final ComponentContainer topPanel = this.createTopPanel();
		if (topPanel != null) {
			taskAddLayout.addTopControls(topPanel);
		}

		this.informationLayout = new TaskInformationLayout();
		taskAddLayout.addBody(this.informationLayout.getLayout());

		final ComponentContainer bottomPanel = this.createBottomPanel();
		if (bottomPanel != null) {
			taskAddLayout.addBottomControls(bottomPanel);
		}

		return taskAddLayout;
	}

	protected void addTitleStyle(final String styleName) {
		this.lstStyleTitle.add(styleName);
	}

	@Override
	public void attachField(final Object propertyId, final Field field) {
		this.informationLayout.attachField(propertyId, field);
	}

	protected abstract ComponentContainer createTopPanel();

	protected abstract ComponentContainer createBottomPanel();

	@SuppressWarnings("serial")
	public static class TaskInformationLayout implements IFormLayoutFactory {

		private GridFormLayoutHelper informationLayout;

		@Override
		public Layout getLayout() {
			this.informationLayout = new GridFormLayoutHelper(2, 8, "100%",
					"167px", Alignment.MIDDLE_LEFT);
			final VerticalLayout layout = new VerticalLayout();
			layout.setMargin(false);
			this.informationLayout.getLayout().setMargin(false);
			this.informationLayout.getLayout().setWidth("100%");
			this.informationLayout.getLayout().addStyleName(
					"colored-gridlayout");
			layout.addComponent(this.informationLayout.getLayout());
			layout.setComponentAlignment(this.informationLayout.getLayout(),
					Alignment.BOTTOM_CENTER);
			return layout;
		}

		@Override
		public void attachField(final Object propertyId, final Field field) {
			if (propertyId.equals("taskname")) {
				this.informationLayout.addComponent(field, "Task Name", 0, 0,
						2, "100%");
			} else if (propertyId.equals("startdate")) {
				this.informationLayout.addComponent(field, "Start Date", 0, 1);
			} else if (propertyId.equals("enddate")) {
				this.informationLayout.addComponent(field, "End Date", 0, 2);
			} else if (propertyId.equals("actualstartdate")) {
				this.informationLayout.addComponent(field, "Actual Start Date",
						1, 1);
			} else if (propertyId.equals("actualenddate")) {
				this.informationLayout.addComponent(field, "Actual End Date",
						1, 2);
			} else if (propertyId.equals("deadline")) {
				this.informationLayout.addComponent(field, "Deadline", 0, 3);
			} else if (propertyId.equals("priority")) {
				this.informationLayout.addComponent(field, "Priority", 1, 3);
			} else if (propertyId.equals("assignuser")) {
				this.informationLayout.addComponent(field, "Assign", 0, 4);
			} else if (propertyId.equals("tasklistid")) {
				this.informationLayout.addComponent(field, "Task List", 1, 4);
			} else if (propertyId.equals("percentagecomplete")) {
				this.informationLayout.addComponent(field, "Complete(%)", 0, 5,
						2, "100%", Alignment.MIDDLE_LEFT);
			} else if (propertyId.equals("notes")) {
				field.setSizeUndefined();
				this.informationLayout.addComponent(field, "Notes", 0, 6, 2,
						UIConstants.DEFAULT_2XCONTROL_WIDTH);
			} else if (propertyId.equals("id")) {
				this.informationLayout.addComponent(field, "Attachments", 0, 7,
						2, "100%");
			}
		}
	}
}
