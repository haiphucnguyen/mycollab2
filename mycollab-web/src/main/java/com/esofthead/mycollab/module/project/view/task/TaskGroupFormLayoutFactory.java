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
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Field;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * @author haiphucnguyen
 */
public abstract class TaskGroupFormLayoutFactory implements IFormLayoutFactory {
	private static final long serialVersionUID = 1L;
	private final String title;
	private TaskListInformationLayout informationLayout;
	private List<String> lstStyleTitle = new ArrayList<String>();

	public TaskGroupFormLayoutFactory(String title) {
		this.title = title;
	}

	@Override
	public Layout getLayout() {
		AddViewLayout accountAddLayout = new AddViewLayout(title,
				new ThemeResource("icons/48/project/tasklist.png"));
		
		for (int i = 0; i < lstStyleTitle.size(); i++) {
			accountAddLayout.addTitleStyleName(lstStyleTitle.get(i));
		}
		
		Layout topPanel = createTopPanel();
		if (topPanel != null) {
			accountAddLayout.addTopControls(topPanel);
		}

		informationLayout = new TaskListInformationLayout();
		accountAddLayout.addBody(informationLayout.getLayout());
		

		Layout bottomPanel = createBottomPanel();
		if (bottomPanel != null) {
			accountAddLayout.addBottomControls(bottomPanel);
		}

		return accountAddLayout;
	}
	
	protected void addTitleStyle(String styleName) {
		lstStyleTitle.add(styleName);
	}

	@Override
	public void attachField(Object propertyId, Field field) {
		informationLayout.attachField(propertyId, field);
	}

	protected abstract Layout createTopPanel();

	protected abstract Layout createBottomPanel();

	@SuppressWarnings("serial")
	public static class TaskListInformationLayout implements IFormLayoutFactory {

		private GridFormLayoutHelper informationLayout;

		@Override
		public Layout getLayout() {
			informationLayout = ProjectUiUtils.getGridFormLayoutHelper(2, 4);
			VerticalLayout layout = new VerticalLayout();
			layout.addComponent(informationLayout.getLayout());
			layout.setComponentAlignment(informationLayout.getLayout(),
					Alignment.BOTTOM_CENTER);
			return layout;
		}

		@Override
		public void attachField(Object propertyId, Field field) {
			if (propertyId.equals("name")) {
				informationLayout.addComponent(field, "Name", 0, 0, 2, "100%");
			} else if (propertyId.equals("description")) {
				informationLayout.addComponent(field, "Description", 0, 1, 2,
						"100%");
			} else if (propertyId.equals("owner")) {
				informationLayout.addComponent(field, "Responsible User", 0, 2);
			} else if (propertyId.equals("milestoneid")) {
				informationLayout
						.addComponent(field, "Related Milestone", 1, 2);
			} else if (propertyId.equals("percentageComplete")) {
				informationLayout.addComponent(field, "Progress", 0, 3);
			} else if (propertyId.equals("numOpenTasks")) {
				informationLayout.addComponent(field, "Number of open tasks", 1, 3);
			}
		}
	}
}
