/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.milestone;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.domain.Milestone;
import com.esofthead.mycollab.module.project.domain.SimpleMilestone;
import com.esofthead.mycollab.module.project.domain.SimpleProject;
import com.esofthead.mycollab.module.project.domain.criteria.MilestoneSearchCriteria;
import com.esofthead.mycollab.module.project.service.MilestoneService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.terminal.Resource;
import com.vaadin.ui.ComboBox;

/**
 * 
 * @author haiphucnguyen
 */
@SuppressWarnings("serial")
public class MilestoneComboBox extends ComboBox {

	public MilestoneComboBox() {
		super();
		this.setItemCaptionMode(ITEM_CAPTION_MODE_EXPLICIT);

		MilestoneSearchCriteria criteria = new MilestoneSearchCriteria();
		SimpleProject project = CurrentProjectVariables.getProject();
		if (project != null) {
			criteria.setProjectId(new NumberSearchField(SearchField.AND,
					project.getId()));

			MilestoneService milestoneService = ApplicationContextUtil
					.getSpringBean(MilestoneService.class);
			List<SimpleMilestone> milestoneList = (List<SimpleMilestone>) milestoneService
					.findPagableListByCriteria(new SearchRequest<MilestoneSearchCriteria>(
							criteria, 0, Integer.MAX_VALUE));

			Collections.sort(milestoneList, new MilestoneComparator());

			for (SimpleMilestone milestone : milestoneList) {
				this.addItem(milestone.getId());
				this.setItemCaption(milestone.getId(), milestone.getName());

				Resource iconRes = null;
				if (MilestoneStatusConstant.IN_PROGRESS.equals(milestone
						.getStatus())) {
					iconRes = MyCollabResource
							.newResource("icons/16/project/phase_progress.png");
				} else if (MilestoneStatusConstant.FUTURE.equals(milestone
						.getStatus())) {
					iconRes = MyCollabResource
							.newResource("icons/16/project/phase_future.png");
				} else {
					iconRes = MyCollabResource
							.newResource("icons/16/project/phase_closed.png");
				}

				this.setItemIcon(milestone.getId(), iconRes);
			}
		}

	}

	private static class MilestoneComparator implements Comparator<Milestone> {

		@Override
		public int compare(Milestone milestone1, Milestone milestone2) {
			if (MilestoneStatusConstant.IN_PROGRESS.equals(milestone1
					.getStatus())) {
				return -1;
			} else if (MilestoneStatusConstant.FUTURE.equals(milestone1
					.getStatus())) {
				return MilestoneStatusConstant.IN_PROGRESS.equals(milestone2
						.getStatus()) ? 1 : -1;
			} else {
				return 1;
			}
		}

	}
}
