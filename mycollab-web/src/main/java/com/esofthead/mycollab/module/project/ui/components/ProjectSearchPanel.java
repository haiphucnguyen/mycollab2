package com.esofthead.mycollab.module.project.ui.components;

import com.esofthead.mycollab.module.crm.ui.components.GenericSearchPanel;
import com.esofthead.mycollab.module.project.domain.criteria.ProjectSearchCriteria;
import com.esofthead.mycollab.module.project.ui.events.ProjectEvent;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.ui.UiUtils;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.themes.BaseTheme;
import com.vaadin.ui.themes.Reindeer;

public class ProjectSearchPanel extends
		GenericSearchPanel<ProjectSearchCriteria> {
	private static final long serialVersionUID = 1L;

	private ProjectSearchCriteria searchCriteria;

	public ProjectSearchPanel() {
	}

	@Override
	public void attach() {
		super.attach();

		HorizontalLayout layout = new HorizontalLayout();
		layout.setWidth("100%");
		layout.setSpacing(true);

		Label searchtitle = new Label("Your Projects");
		searchtitle.setStyleName(Reindeer.LABEL_H2);
		layout.addComponent(searchtitle);

		Button createProjectBtn = new Button("Create",
				new Button.ClickListener() {

					@Override
					public void buttonClick(ClickEvent event) {
						getApplication().addWindow(new ProjectAddWindow());

					}

				});
		createProjectBtn.setIcon(new ThemeResource("icons/16/addRecord.png"));
		createProjectBtn.setStyleName(BaseTheme.BUTTON_LINK);

		UiUtils.addComponent(layout, createProjectBtn, Alignment.MIDDLE_RIGHT);
		this.setCompositionRoot(layout);
	}
}
