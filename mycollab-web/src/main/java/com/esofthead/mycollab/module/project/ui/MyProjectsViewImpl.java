package com.esofthead.mycollab.module.project.ui;

import java.util.List;

import com.esofthead.mycollab.module.project.domain.SimpleProject;
import com.esofthead.mycollab.module.project.domain.criteria.ProjectSearchCriteria;
import com.esofthead.mycollab.module.project.ui.components.ProjectAddWindow;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.BeanTable;
import com.jensjansson.pagedtable.PagedTableContainer;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.ColumnGenerator;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.BaseTheme;
import com.vaadin.ui.themes.ChameleonTheme;

@SuppressWarnings("serial")
public class MyProjectsViewImpl extends AbstractView implements MyProjectsView {

	private BeanTable<SimpleProject> tableItem;
	private MyProjectLayout myProjectLayout;

	public MyProjectsViewImpl() {
		myProjectLayout = new MyProjectLayout();
		this.addComponent(myProjectLayout);
	}

	@Override
	public void doDefaultSearch() {
		

	}

	@Override
	public void doSearch(ProjectSearchCriteria searchCriteria) {
		// TODO Auto-generated method stub

	}

	private class MyProjectLayout extends MyTemplateViewLayout {

		private VerticalLayout bodyContainer;

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
			bodyContainer = new VerticalLayout();
			return bodyContainer;
		}

		public void setBodyComponent(com.vaadin.ui.Component component) {
			bodyContainer.removeAllComponents();
			bodyContainer.addComponent(component);
		}

	}

	@Override
	public void displayProjects(List<SimpleProject> projects) {
		tableItem = new BeanTable<SimpleProject>();
		tableItem.addStyleName("striped");
		Container.Indexed container = new BeanItemContainer<SimpleProject>(
				SimpleProject.class, projects);

		tableItem.setContainerDataSource(container);
		tableItem.setVisibleColumns(new String[] { "name", "accountName",
				"projecttype", "projectstatus" });
		tableItem.setColumnHeaders(new String[] { "Name", "Client", "Type",
				"Status" });

		tableItem.addGeneratedColumn("name", new ColumnGenerator() {
			private static final long serialVersionUID = 1L;

			@Override
			public com.vaadin.ui.Component generateCell(Table source,
					final Object itemId, Object columnId) {
				PagedTableContainer tableContainer = (PagedTableContainer) source
						.getContainerDataSource();
				@SuppressWarnings("unchecked")
				BeanItemContainer<SimpleProject> container = (BeanItemContainer<SimpleProject>) tableContainer
						.getContainer();
				final SimpleProject project = container.getItem(itemId).getBean();
				Button projectBtn = new Button(project.getName(),
						new Button.ClickListener() {

							@Override
							public void buttonClick(ClickEvent event) {

							}
						});
				projectBtn.setStyleName(BaseTheme.BUTTON_LINK);
				return projectBtn;
			}
		});

		myProjectLayout.setBodyComponent(tableItem);
	}
}
