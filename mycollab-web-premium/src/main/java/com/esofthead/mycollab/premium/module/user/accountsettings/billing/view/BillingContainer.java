/**
 * This file is part of mycollab-web-community.
 *
 * mycollab-web-community is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-web-community is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-web-community.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.premium.module.user.accountsettings.billing.view;

import com.esofthead.mycollab.common.i18n.LicenseI18nEnum;
import com.esofthead.mycollab.license.LicenseInfo;
import com.esofthead.mycollab.license.LicenseResolver;
import com.esofthead.mycollab.module.user.accountsettings.billing.view.IBillingContainer;
import com.esofthead.mycollab.spring.AppContextUtil;
import com.esofthead.mycollab.vaadin.AbstractLicenseActivationWindow;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.mvp.AbstractPageView;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.esofthead.mycollab.vaadin.mvp.ViewManager;
import com.esofthead.mycollab.vaadin.ui.ELabel;
import com.esofthead.mycollab.vaadin.web.ui.UIConstants;
import com.esofthead.mycollab.vaadin.web.ui.grid.GridFormLayoutHelper;
import com.vaadin.ui.*;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
@ViewComponent
public class BillingContainer extends AbstractPageView implements IBillingContainer {
    private static final long serialVersionUID = 1L;

    public BillingContainer() {
        withMargin(true).withSpacing(true);
        setDefaultComponentAlignment(Alignment.TOP_CENTER);
    }

    public void display() {
        removeAllComponents();

        LicenseResolver licenseResolver = AppContextUtil.getSpringBean(LicenseResolver.class);
        if (licenseResolver != null) {
            try {
                LicenseInfo licenseInfo = licenseResolver.getLicenseInfo();
                if (licenseInfo.isExpired()) {
                    with(ELabel.h2(AppContext.getMessage(LicenseI18nEnum.OPT_LICENSE_EXPIRE_DATE, AppContext.formatDate(licenseInfo.getExpireDate())))
                            .withWidthUndefined());
                } else if (licenseInfo.isTrial()) {
                    with(ELabel.h2(AppContext.getMessage(LicenseI18nEnum.OPT_LICENSE_EXPIRE_SOON_DATE, AppContext.formatDate(licenseInfo.getExpireDate())))
                            .withWidthUndefined());
                } else {
                    with(ELabel.h2(AppContext.getMessage(LicenseI18nEnum.OPT_LICENSE_VALID_TO_DATE, AppContext.formatDate(licenseInfo.getExpireDate())))
                            .withWidthUndefined());
                }
                GridFormLayoutHelper layoutHelper = GridFormLayoutHelper.defaultFormLayoutHelper(1, 4);
                layoutHelper.addComponent(new Label(licenseInfo.getLicenseOrg()), AppContext.getMessage
                        (LicenseI18nEnum.FORM_ORGANIZATION), 0, 0);
                layoutHelper.addComponent(new Label(AppContext.formatDate(licenseInfo.getIssueDate())), AppContext
                        .getMessage(LicenseI18nEnum.FORM_ISSUE_DATE), 0, 1);
                layoutHelper.addComponent(new Label(AppContext.formatDate(licenseInfo.getExpireDate())), AppContext
                        .getMessage(LicenseI18nEnum.FORM_EXPIRE_DATE), 0, 2);
                layoutHelper.addComponent(new Label(licenseInfo.getMaxUsers() + ""), AppContext.getMessage
                        (LicenseI18nEnum.FORM_MAX_USERS), 0, 3);
                layoutHelper.getLayout().setWidth("600px");
                with(layoutHelper.getLayout()).withAlign(layoutHelper.getLayout(), Alignment.TOP_CENTER);

                if (licenseInfo.isTrial() || licenseInfo.isExpired() || licenseInfo.isInvalid()) {
                    Button licenseBtn = new Button(AppContext.getMessage(LicenseI18nEnum.ACTION_ENTER_LICENSE), new Button.ClickListener() {
                        @Override
                        public void buttonClick(Button.ClickEvent clickEvent) {
                            Window activateWindow = ViewManager.getCacheComponent(AbstractLicenseActivationWindow.class);
                            UI.getCurrent().addWindow(activateWindow);
                        }
                    });
                    licenseBtn.addStyleName(UIConstants.BUTTON_ACTION);
                    with(licenseBtn).withAlign(licenseBtn, Alignment.TOP_CENTER);
                }
            } catch (Exception e) {
                buildInvalidLicenseComp();
            }
        } else {
            buildInvalidLicenseComp();
        }
    }

    private void buildInvalidLicenseComp() {
        with(ELabel.h2(AppContext.getMessage(LicenseI18nEnum.ERROR_LICENSE_INVALID)).withWidthUndefined());
    }
}
