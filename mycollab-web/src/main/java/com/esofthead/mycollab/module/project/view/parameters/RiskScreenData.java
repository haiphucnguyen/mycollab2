/**
 * This file is part of mycollab-web.
 *
 * mycollab-web is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-web is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-web.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.module.project.view.parameters;

import com.esofthead.mycollab.module.project.domain.Risk;
import com.esofthead.mycollab.module.project.domain.criteria.RiskSearchCriteria;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;

public class RiskScreenData {
	public static class Read extends ScreenData<Integer> {

		public Read(Integer params) {
			super(params);
		}
	}
	
	public static class Add extends ScreenData<Risk> {

		public Add(Risk params) {
			super(params);
		}
	}
	
	public static class Edit extends ScreenData<Risk> {

		public Edit(Risk params) {
			super(params);
		}
	}

	public static class Search extends ScreenData<RiskSearchCriteria> {

		public Search(RiskSearchCriteria criteria) {
			super(criteria);
		}
	}
}
