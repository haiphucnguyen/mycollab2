/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.user;

import com.esofthead.mycollab.common.ModuleNameConstants;
import com.esofthead.mycollab.common.domain.criteria.ActivityStreamSearchCriteria;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.vaadin.ui.Depot;
import com.vaadin.lazyloadwrapper.LazyLoadWrapper;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * @author haiphucnguyen
 */
public class ProjectActivityStreamComponent extends Depot {
	private static final long serialVersionUID = 1L;

	private final ProjectActivityStreamPagedList activityStreamList;

	public ProjectActivityStreamComponent() {
		super("Project Feeds", new VerticalLayout());
		this.activityStreamList = new ProjectActivityStreamPagedList();
		((VerticalLayout) this.bodyContent).setMargin(false);
	}

	public void showProjectFeeds() {
		this.bodyContent.removeAllComponents();
		this.bodyContent.addComponent(new LazyLoadWrapper(
				this.activityStreamList));
		final ActivityStreamSearchCriteria searchCriteria = new ActivityStreamSearchCriteria();
		searchCriteria.setModuleSet(new SetSearchField<String>(SearchField.AND,
				new String[] { ModuleNameConstants.PRJ }));

		searchCriteria.setExtraTypeIds(new SetSearchField<Integer>(
				CurrentProjectVariables.getProjectId()));
		this.activityStreamList.setSearchCriteria(searchCriteria);
	}
}
