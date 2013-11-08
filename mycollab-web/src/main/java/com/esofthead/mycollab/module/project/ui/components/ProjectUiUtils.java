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
package com.esofthead.mycollab.module.project.ui.components;

import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;

public class ProjectUiUtils {
	
	public static GridFormLayoutHelper getGridFormLayoutHelper(int col, int row, String fieldControlWidth, String captionWidth) {
		return new GridFormLayoutHelper(col, row, fieldControlWidth, captionWidth);
	}
	
	public static GridFormLayoutHelper getGridFormLayoutHelper(int col, int row) {
		return new GridFormLayoutHelper(col, row, "220px", "120px");
	}
}
