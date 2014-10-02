package com.esofthead.mycollab.mobile.module.project.view.milestone;

import com.esofthead.mycollab.mobile.ui.DefaultPagedBeanList;
import com.esofthead.mycollab.module.project.domain.SimpleMilestone;
import com.esofthead.mycollab.module.project.domain.criteria.MilestoneSearchCriteria;
import com.esofthead.mycollab.module.project.service.MilestoneService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.vaadin.ui.Component;

/**
 * @author MyCollab Ltd.
 *
 * @since 4.5.2
 */
public class MilestoneListDisplay
		extends
		DefaultPagedBeanList<MilestoneService, MilestoneSearchCriteria, SimpleMilestone> {

	private static final long serialVersionUID = 253054104668116456L;

	public MilestoneListDisplay() {
		super(ApplicationContextUtil.getSpringBean(MilestoneService.class),
				new MilestoneRowDisplayHandler());
	}

	private static class MilestoneRowDisplayHandler implements
			RowDisplayHandler<SimpleMilestone> {

		@Override
		public Component generateRow(SimpleMilestone milestone, int rowIndex) {
			return null;
		}

	}
}
