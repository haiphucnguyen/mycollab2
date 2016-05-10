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
package com.esofthead.mycollab.module.project.view;

import com.esofthead.mycollab.common.TableViewField;
import com.esofthead.mycollab.common.XStreamJsonDeSerializer;
import com.esofthead.mycollab.module.crm.view.contact.ContactTableFieldDef;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.web.ui.AbstractPresenter;
import com.vaadin.ui.ComponentContainer;

/**
 * @author MyCollab Ltd
 * @since 5.2.4
 */
public class SettingPresenter extends AbstractPresenter<SettingView> {
    public SettingPresenter() {
        super(SettingView.class);
    }

    @Override
    protected void onGo(ComponentContainer container, ScreenData<?> data) {
        view.display();
    }

    public static void main(String[] args) {
        System.out.println(XStreamJsonDeSerializer.toJson(ContactTableFieldDef.account()));
        TableViewField o = (TableViewField)XStreamJsonDeSerializer.fromJson("{\"com.esofthead.mycollab.common" +
                ".TableViewField\":{\"descKey\":{\"@class\":\"com.esofthead.mycollab.module.crm.i18n.ContactI18nEnum\",\"$\":\"com.esofthead.mycollab.module.crm.i18n.ContactI18nEnum:FORM_ACCOUNTS\"},\"field\":\"accountName\",\"defaultWidth\":200}}");
        System.out.println("O: " + o.getDescKey());
    }
}
