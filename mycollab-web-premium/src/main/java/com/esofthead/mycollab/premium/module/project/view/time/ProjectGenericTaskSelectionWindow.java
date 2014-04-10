package com.esofthead.mycollab.premium.module.project.view.time;

import java.util.Arrays;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.eventmanager.ApplicationEvent;
import com.esofthead.mycollab.eventmanager.ApplicationEventListener;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.domain.ProjectGenericTask;
import com.esofthead.mycollab.module.project.domain.criteria.ProjectGenericTaskSearchCriteria;
import com.esofthead.mycollab.module.project.ui.components.GenericTaskTableDisplay;
import com.esofthead.mycollab.module.project.ui.components.GenericTaskTableFieldDef;
import com.esofthead.mycollab.vaadin.ui.table.TableClickEvent;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.0
 * 
 */
class ProjectGenericTaskSelectionWindow extends Window {
	private static final long serialVersionUID = 1L;

	private GenericTaskTableDisplay taskTableDisplay;

	public ProjectGenericTaskSelectionWindow(
			final AddTimeEntryWindow timeEntryWindow) {
		super("Select Assignments");
		this.center();
		this.setWidth("800px");
		VerticalLayout content = new VerticalLayout();
		content.setMargin(true);
		content.setSpacing(true);
		taskTableDisplay = new GenericTaskTableDisplay(
				Arrays.asList(GenericTaskTableFieldDef.name));
		taskTableDisplay
				.addTableListener(new ApplicationEventListener<TableClickEvent>() {
					private static final long serialVersionUID = 1L;

					@Override
					public Class<? extends ApplicationEvent> getEventType() {
						return TableClickEvent.class;
					}

					@Override
					public void handle(final TableClickEvent event) {
						final ProjectGenericTask task = (ProjectGenericTask) event
								.getData();
						if ("name".equals(event.getFieldName())) {
							timeEntryWindow.setSelectionTask(task);
							ProjectGenericTaskSelectionWindow.this.close();
						}
					}
				});

		ProjectGenericTaskSearchCriteria searchCriteria = new ProjectGenericTaskSearchCriteria();
		searchCriteria.setProjectId(new NumberSearchField(
				CurrentProjectVariables.getProjectId()));
		taskTableDisplay.setSearchCriteria(searchCriteria);

		content.addComponent(taskTableDisplay);
		this.setContent(content);
	}
}
