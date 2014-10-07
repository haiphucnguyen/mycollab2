package com.esofthead.mycollab.mobile.module.project.view.bug;

import com.esofthead.mycollab.mobile.ui.DefaultPagedBeanList;
import com.esofthead.mycollab.module.tracker.domain.SimpleBug;
import com.esofthead.mycollab.module.tracker.domain.criteria.BugSearchCriteria;
import com.esofthead.mycollab.module.tracker.service.BugService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.vaadin.ui.Component;

/**
 * @author MyCollab Ltd.
 *
 * @since 4.5.2
 */
public class BugListDisplay extends
		DefaultPagedBeanList<BugService, BugSearchCriteria, SimpleBug> {

	private static final long serialVersionUID = -8911176517887730007L;

	public BugListDisplay() {
		super(ApplicationContextUtil.getSpringBean(BugService.class),
				new BugRowDisplayHandler());
	}

	private static class BugRowDisplayHandler implements
			RowDisplayHandler<SimpleBug> {

		@Override
		public Component generateRow(final SimpleBug bug, int rowIndex) {
			// TODO Auto-generated method stub
			return null;
		}

	}
}
