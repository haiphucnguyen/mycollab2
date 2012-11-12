package com.esofthead.mycollab.module.project.ui;

import org.springframework.stereotype.Component;

import com.esofthead.mycollab.module.project.domain.criteria.ProjectSearchCriteria;
import com.esofthead.mycollab.module.project.ui.components.ProjectAddWindow;
import com.esofthead.mycollab.vaadin.mvp.ui.AbstractView;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.themes.ChameleonTheme;

@SuppressWarnings("serial")
@Component
public class MyProjectsViewImpl extends AbstractView implements MyProjectsView {

	@Override
	protected ComponentContainer initMainLayout() {
		MyProjectLayout layout = new MyProjectLayout();
		layout.addComponent(new Label("My Projects"));
		return layout;
	}
	
	@Override
	public void doDefaultSearch() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doSearch(ProjectSearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		
	}

	private class MyProjectLayout extends MyTemplateViewLayout {

		@Override
		protected ComponentContainer constructHeader() {
			HorizontalLayout layout = new HorizontalLayout();
			layout.setWidth("100%");
			layout.setMargin(true);
			layout.setSpacing(true);

			Label title = new Label("All Projects");
			title.setStyleName(ChameleonTheme.LABEL_H2);
			layout.addComponent(title);
			layout.setExpandRatio(title, 1f);
			layout.setComponentAlignment(title, Alignment.MIDDLE_LEFT);

			Button createProjectBtn = new Button("Create Project",
					new Button.ClickListener() {

						@Override
						public void buttonClick(ClickEvent event) {
							ProjectAddWindow projectAddWindow = new ProjectAddWindow();
							getWindow().addWindow(projectAddWindow);
						}
					});
			layout.addComponent(createProjectBtn);
			createProjectBtn.setStyleName("link");
			layout.setComponentAlignment(createProjectBtn,
					Alignment.MIDDLE_RIGHT);
			return layout;
		}

		@Override
		protected ComponentContainer constructBody() {
			return new VerticalLayout();
		}

	}
}
