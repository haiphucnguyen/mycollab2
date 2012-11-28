package com.esofthead.mycollab.module.project.view;

import java.util.List;

import com.esofthead.mycollab.module.project.domain.SimpleProject;
import com.esofthead.mycollab.module.project.domain.criteria.ProjectSearchCriteria;
import com.esofthead.mycollab.module.project.service.ProjectService;
import com.esofthead.mycollab.module.project.ui.components.ProjectSearchPanel;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.IPagedBeanTable;
import com.esofthead.mycollab.vaadin.ui.PagedBeanTable2;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class MyProjectsViewImpl extends AbstractView implements MyProjectsView {

	private PagedBeanTable2<ProjectService, ProjectSearchCriteria, SimpleProject> tableItem;
	
	private ProjectSearchPanel searchPanel;
	
	private VerticalLayout listLayout;


	public MyProjectsViewImpl() {
		this.setSpacing(true);
		this.setMargin(true);

		searchPanel = new ProjectSearchPanel();
		this.addComponent(searchPanel);

		listLayout = new VerticalLayout();
		listLayout.setSpacing(true);
		this.addComponent(listLayout);

		generateDisplayTable();
	}
	
	private void generateDisplayTable() {
		tableItem = new PagedBeanTable2<ProjectService, ProjectSearchCriteria, SimpleProject>(
				AppContext.getSpringBean(ProjectService.class),
				SimpleProject.class, new String[] { "name", "accountName",
					"projecttype", "projectstatus" }, new String[] {"Name", "Account", "Type", "Status" });
		
		tableItem.setWidth("100%");
		
		tableItem.setColumnExpandRatio("name", 1);
		tableItem.setColumnWidth("accountName", UIConstants.TABLE_X_LABEL_WIDTH);
		tableItem.setColumnWidth("projecttype", UIConstants.TABLE_M_LABEL_WIDTH);
		tableItem.setColumnWidth("projectstatus",
				UIConstants.TABLE_M_LABEL_WIDTH);
		
		listLayout.addComponent(tableItem);
		listLayout.addComponent(tableItem.createControls());
	}

	@Override
	public IPagedBeanTable<ProjectService, ProjectSearchCriteria, SimpleProject> getPagedBeanTable() {
		return tableItem;
	}
}
