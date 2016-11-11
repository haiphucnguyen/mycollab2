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
package com.mycollab.pro.module.crm.view.setting;

import com.mycollab.module.crm.CrmTypeConstants;
import com.mycollab.module.crm.view.CrmGenericPresenter;
import com.mycollab.module.crm.view.setting.CrmSettingContainer;
import com.mycollab.module.crm.view.setting.ICrmCustomView;
import com.mycollab.module.crm.view.setting.ICrmCustomViewPresenter;
import com.mycollab.vaadin.MyCollabUI;
import com.mycollab.vaadin.mvp.ScreenData;
import com.vaadin.ui.HasComponents;

/**
 * @author MyCollab Ltd.
 * @since 3.0
 */
public class CrmCustomViewPresenter extends CrmGenericPresenter<ICrmCustomView> implements ICrmCustomViewPresenter {
    private static final long serialVersionUID = 1L;

    public CrmCustomViewPresenter() {
        super(ICrmCustomView.class);
    }

    @Override
    protected void onGo(HasComponents container, ScreenData<?> data) {
        CrmSettingContainer settingContainer = (CrmSettingContainer) container;
        settingContainer.gotoSubView("customlayout");

        MyCollabUI.addFragment("crm/setting/customlayout", "Custom Layouts");

        view.display(CrmTypeConstants.ACCOUNT);
    }

}
