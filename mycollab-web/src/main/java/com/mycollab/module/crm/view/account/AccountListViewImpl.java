/**
 * Copyright © MyCollab
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.mycollab.module.crm.view.account;

import com.mycollab.module.crm.CrmTypeConstants;
import com.mycollab.module.crm.domain.SimpleAccount;
import com.mycollab.module.crm.domain.criteria.AccountSearchCriteria;
import com.mycollab.module.crm.fielddef.AccountTableFieldDef;
import com.mycollab.module.crm.ui.components.AbstractListItemComp;
import com.mycollab.module.crm.ui.components.ComponentUtils;
import com.mycollab.security.RolePermissionCollections;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.mvp.ViewComponent;
import com.mycollab.vaadin.ui.DefaultMassItemActionHandlerContainer;
import com.mycollab.vaadin.web.ui.DefaultGenericSearchPanel;
import com.mycollab.vaadin.web.ui.table.AbstractPagedGrid;
import com.vaadin.ui.UI;
import org.vaadin.viritin.button.MButton;

import java.util.Arrays;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
@ViewComponent
public class AccountListViewImpl extends AbstractListItemComp<AccountSearchCriteria, SimpleAccount> implements AccountListView {
    private static final long serialVersionUID = 1L;

    @Override
    protected AbstractPagedGrid<AccountSearchCriteria, SimpleAccount> createGrid() {
        return new AccountTableDisplay(CrmTypeConstants.ACCOUNT, AccountTableFieldDef.selected,
                Arrays.asList(AccountTableFieldDef.accountname, AccountTableFieldDef.city,
                        AccountTableFieldDef.phoneoffice, AccountTableFieldDef.email,
                        AccountTableFieldDef.assignUser));
    }

    @Override
    protected DefaultGenericSearchPanel<AccountSearchCriteria> createSearchPanel() {
        return new AccountSearchPanel(true);
    }

    @Override
    protected DefaultMassItemActionHandlerContainer createActionControls() {
        DefaultMassItemActionHandlerContainer container = new DefaultMassItemActionHandlerContainer();

        if (UserUIContext.canAccess(RolePermissionCollections.CRM_ACCOUNT)) {
            container.addDeleteActionItem();
        }

        container.addMailActionItem();
        container.addDownloadPdfActionItem();
        container.addDownloadExcelActionItem();
        container.addDownloadCsvActionItem();

        if (UserUIContext.canWrite(RolePermissionCollections.CRM_ACCOUNT)) {
            container.addMassUpdateActionItem();
        }

        return container;
    }

    @Override
    protected void buildExtraControls() {
        MButton customizeViewBtn = ComponentUtils.createCustomizeViewButton()
                .withListener(clickEvent -> UI.getCurrent().addWindow(new AccountListCustomizeWindow((AccountTableDisplay) grid)));
        this.addExtraButton(customizeViewBtn);
    }

    @Override
    public void showNoItemView() {
        removeAllComponents();
        addComponent(new AccountCrmListNoItemView());
    }
}
