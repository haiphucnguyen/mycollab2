/**
 * This file is part of mycollab-mobile.
 *
 * mycollab-mobile is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-mobile is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-mobile.  If not, see <http://www.gnu.org/licenses/>.
 */
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
