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
package com.mycollab.premium.module.user.accountsettings.billing.view;

import com.mycollab.common.i18n.LicenseI18nEnum;
import com.mycollab.module.user.accountsettings.billing.view.IBillingContainer;
import com.mycollab.premium.license.service.LicenseResolver;
import com.mycollab.premium.shell.view.components.LicenseActivationWindow;
import com.mycollab.pro.license.LicenseInfo;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.AppContext;
import com.mycollab.vaadin.mvp.AbstractPageView;
import com.mycollab.vaadin.mvp.ViewComponent;
import com.mycollab.vaadin.ui.ELabel;
import com.mycollab.vaadin.web.ui.UIConstants;
import com.mycollab.vaadin.web.ui.grid.GridFormLayoutHelper;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import org.vaadin.viritin.button.MButton;

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
        try {
            LicenseInfo licenseInfo = licenseResolver.getLicenseInfo();
            if (licenseInfo.isExpired()) {
                with(ELabel.h2(AppContext.getMessage(LicenseI18nEnum.OPT_LICENSE_EXPIRE_DATE, AppContext.formatDate
                        (licenseInfo.getExpireDate()))).withStyleName(UIConstants.LABEL_OVERDUE).withWidthUndefined());
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
                MButton licenseBtn = new MButton(AppContext.getMessage(LicenseI18nEnum.ACTION_ENTER_LICENSE),
                        clickEvent -> UI.getCurrent().addWindow(new LicenseActivationWindow())).withStyleName(UIConstants.BUTTON_ACTION);

                with(licenseBtn).withAlign(licenseBtn, Alignment.TOP_CENTER);
            }
        } catch (Exception e) {
            buildInvalidLicenseComp();
        }
    }

    private void buildInvalidLicenseComp() {
        with(ELabel.h2(AppContext.getMessage(LicenseI18nEnum.ERROR_LICENSE_INVALID)).withWidthUndefined());
    }
}
