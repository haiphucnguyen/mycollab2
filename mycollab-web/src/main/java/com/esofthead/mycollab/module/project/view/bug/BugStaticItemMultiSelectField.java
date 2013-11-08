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
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.bug;

import com.esofthead.mycollab.module.project.ui.components.MultiSelectComp;


/**
 * 
 * @author haiphucnguyen
 */
@SuppressWarnings("serial")
public class BugStaticItemMultiSelectField extends MultiSelectComp {

	private String[] arrItemData;
	
	public BugStaticItemMultiSelectField(String[] arrItemData, String width) {
		super("", width);
		this.arrItemData = arrItemData;
	}

	@Override
	protected void initData() {
		for (int i = 0; i < arrItemData.length; i++) {
			dataList.add(arrItemData[i]);
		}

	}
}
