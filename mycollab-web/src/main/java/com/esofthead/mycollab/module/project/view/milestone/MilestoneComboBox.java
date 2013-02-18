/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.milestone;

import java.util.List;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.domain.SimpleMilestone;
import com.esofthead.mycollab.module.project.domain.SimpleProject;
import com.esofthead.mycollab.module.project.domain.criteria.MilestoneSearchCriteria;
import com.esofthead.mycollab.module.project.service.MilestoneService;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.ui.ComboBox;

/**
 * 
 * @author haiphucnguyen
 */
@SuppressWarnings("serial")
public class MilestoneComboBox extends ComboBox {

	public MilestoneComboBox() {
		super();
		this.setItemCaptionMode(ITEM_CAPTION_MODE_PROPERTY);

		MilestoneSearchCriteria criteria = new MilestoneSearchCriteria();
		SimpleProject project = CurrentProjectVariables.getProject();
		if (project != null) {
			criteria.setProjectId(new NumberSearchField(SearchField.AND,
					project.getId()));

			MilestoneService milestoneService = AppContext
					.getSpringBean(MilestoneService.class);
			List<SimpleMilestone> milestoneList = milestoneService
					.findPagableListByCriteria(new SearchRequest<MilestoneSearchCriteria>(
							criteria, 0, Integer.MAX_VALUE));

			BeanContainer<String, SimpleMilestone> beanItem = new BeanContainer<String, SimpleMilestone>(
					SimpleMilestone.class);
			beanItem.setBeanIdProperty("id");

			for (SimpleMilestone milestone : milestoneList) {
				beanItem.addBean(milestone);
			}

			this.setContainerDataSource(beanItem);
			this.setItemCaptionPropertyId("name");
		}

	}
}
