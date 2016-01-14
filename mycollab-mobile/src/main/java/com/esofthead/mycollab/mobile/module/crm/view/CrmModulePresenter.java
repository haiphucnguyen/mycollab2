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
package com.esofthead.mycollab.mobile.module.crm.view;

import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.configuration.PasswordEncryptHelper;
import com.esofthead.mycollab.eventmanager.EventBusFactory;
import com.esofthead.mycollab.mobile.MobileApplication;
import com.esofthead.mycollab.mobile.module.crm.events.CrmEvent;
import com.esofthead.mycollab.mobile.ui.AbstractMobilePresenter;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.vaadin.addon.touchkit.extensions.LocalStorage;
import com.vaadin.addon.touchkit.extensions.LocalStorageCallback;
import com.vaadin.ui.ComponentContainer;

/**
 * @author MyCollab Ltd.
 * @since 4.2
 */
public class CrmModulePresenter extends AbstractMobilePresenter<CrmModule> {
    private static final long serialVersionUID = -3370467477599009160L;

    public CrmModulePresenter() {
        super(CrmModule.class);
    }

    @Override
    protected void onGo(ComponentContainer navigator, ScreenData<?> data) {
        AppContext.addFragment("crm/", AppContext.getMessage(GenericI18Enum.MODULE_CRM));
        checkLocalData();
    }

    private void checkLocalData() {
        LocalStorage.detectValue(MobileApplication.NAME_COOKIE, new LocalStorageCallback() {
            private static final long serialVersionUID = 3217947479690600476L;

            @Override
            public void onSuccess(String value) {
                if (value != null) {
                    String[] loginParams = value.split("\\$");
                    EventBusFactory.getInstance().post(new CrmEvent.PlainLogin(this,
                            new String[]{loginParams[0], PasswordEncryptHelper.decryptText(loginParams[1]), String.valueOf(false)}));

                } else {
                    EventBusFactory.getInstance().post(new CrmEvent.GotoLogin(this, null));
                }
            }

            @Override
            public void onFailure(FailureEvent error) {
                EventBusFactory.getInstance().post(new CrmEvent.GotoLogin(this, null));
            }
        });
    }

}
