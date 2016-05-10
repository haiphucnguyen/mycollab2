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

import com.esofthead.mycollab.license.LicenseInfo;
import com.esofthead.mycollab.license.LicenseResolver;
import com.esofthead.mycollab.module.user.accountsettings.billing.view.IBillingContainer;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.mvp.AbstractPageView;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.esofthead.mycollab.vaadin.ui.ELabel;
import com.esofthead.mycollab.vaadin.web.ui.grid.GridFormLayoutHelper;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Label;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
@ViewComponent
public class BillingContainer extends AbstractPageView implements IBillingContainer {
    private static final long serialVersionUID = 1L;

    public BillingContainer() {
        withMargin(true);
        setDefaultComponentAlignment(Alignment.TOP_CENTER);
    }

    public void display() {
        removeAllComponents();

        LicenseResolver licenseResolver = ApplicationContextUtil.getSpringBean(LicenseResolver.class);
        if (licenseResolver != null) {
            try {
                LicenseInfo licenseInfo = licenseResolver.getLicenseInfo();
                if (licenseInfo.isExpired()) {
                    with(ELabel.h2("Your license is expired at " + AppContext.formatDate(licenseInfo.getExpireDate())).withWidthUndefined());
                } else if (licenseInfo.isTrial()) {
                    with(ELabel.h2("Your license is expired soon at " + AppContext.formatDate(licenseInfo.getExpireDate())).withWidthUndefined());
                } else {
                    with(ELabel.h2("Your license is valid until " + AppContext.formatDate(licenseInfo.getExpireDate())).withWidthUndefined());
                }
                GridFormLayoutHelper layoutHelper = GridFormLayoutHelper.defaultFormLayoutHelper(1, 4);
                layoutHelper.addComponent(new Label(licenseInfo.getLicenseOrg()), "Organization", 0, 0);
                layoutHelper.addComponent(new Label(AppContext.formatDate(licenseInfo.getIssueDate())), "Issue Date", 0, 1);
                layoutHelper.addComponent(new Label(AppContext.formatDate(licenseInfo.getExpireDate())), "Expire Date", 0, 2);
                layoutHelper.addComponent(new Label(licenseInfo.getMaxUsers() + ""), "Max Users", 0, 3);
                layoutHelper.getLayout().setWidth("600px");
                with(layoutHelper.getLayout()).withAlign(layoutHelper.getLayout(), Alignment.TOP_CENTER);
            } catch (Exception e) {
                buildInvalidLicenseComp();
            }
        } else {
            buildInvalidLicenseComp();
        }
    }

    private void buildInvalidLicenseComp() {
        with(ELabel.h2("Your license is invalid").withWidthUndefined());
    }
}
