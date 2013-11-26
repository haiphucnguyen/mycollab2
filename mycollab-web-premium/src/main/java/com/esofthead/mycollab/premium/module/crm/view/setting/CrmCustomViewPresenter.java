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
package com.esofthead.mycollab.premium.module.crm.view.setting;

import com.esofthead.mycollab.module.crm.CrmTypeConstants;
import com.esofthead.mycollab.module.crm.view.CrmGenericPresenter;
import com.esofthead.mycollab.module.crm.view.setting.CrmSettingContainer;
import com.esofthead.mycollab.module.crm.view.setting.ICrmCustomView;
import com.esofthead.mycollab.module.crm.view.setting.ICrmCustomViewPresenter;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.ComponentContainer;

public class CrmCustomViewPresenter extends CrmGenericPresenter<ICrmCustomView>
		implements ICrmCustomViewPresenter {
	private static final long serialVersionUID = 1L;

	public CrmCustomViewPresenter() {
		super(ICrmCustomView.class);
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		CrmSettingContainer settingContainer = (CrmSettingContainer) container;
		settingContainer.gotoSubView("Custom Layouts");

		AppContext.addFragment("crm/setting/customlayout", "Custom Layouts");

		view.display(CrmTypeConstants.ACCOUNT);
	}

}
