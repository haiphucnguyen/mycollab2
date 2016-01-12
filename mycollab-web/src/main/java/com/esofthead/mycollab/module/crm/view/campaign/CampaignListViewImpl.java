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
package com.esofthead.mycollab.module.crm.view.campaign;

import com.esofthead.mycollab.eventmanager.EventBusFactory;
import com.esofthead.mycollab.module.crm.domain.SimpleCampaign;
import com.esofthead.mycollab.module.crm.domain.criteria.CampaignSearchCriteria;
import com.esofthead.mycollab.module.crm.events.CampaignEvent;
import com.esofthead.mycollab.module.crm.ui.components.AbstractListItemComp;
import com.esofthead.mycollab.module.crm.ui.components.ComponentUtils;
import com.esofthead.mycollab.security.RolePermissionCollections;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.esofthead.mycollab.vaadin.web.ui.DefaultGenericSearchPanel;
import com.esofthead.mycollab.vaadin.ui.DefaultMassItemActionHandlerContainer;
import com.esofthead.mycollab.vaadin.web.ui.table.AbstractPagedBeanTable;
import com.esofthead.mycollab.vaadin.web.ui.table.IPagedBeanTable.TableClickEvent;
import com.esofthead.mycollab.vaadin.web.ui.table.IPagedBeanTable.TableClickListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.UI;
import org.vaadin.viritin.button.MButton;

import java.util.Arrays;

/**
 * 
 * @author MyCollab Ltd.
 * @since 2.0
 * 
 */
@ViewComponent
public class CampaignListViewImpl extends AbstractListItemComp<CampaignSearchCriteria, SimpleCampaign>
        implements CampaignListView {
    private static final long serialVersionUID = 1L;

    @Override
    protected void buildExtraControls() {
        MButton customizeViewBtn = ComponentUtils.createCustomizeViewButton().withListener(new Button.ClickListener() {
            @Override
            public void buttonClick(ClickEvent clickEvent) {
                UI.getCurrent().addWindow(
                        new CampaignListCustomizeWindow(
                                CampaignListView.VIEW_DEF_ID, tableItem));
            }
        });
        this.addExtraButton(customizeViewBtn);

        Button importBtn = ComponentUtils.createImportEntitiesButton().withListener(new Button.ClickListener() {
            @Override
            public void buttonClick(ClickEvent clickEvent) {
                UI.getCurrent().addWindow(new CampaignImportWindow());
            }
        });
        importBtn.setEnabled(AppContext
                .canWrite(RolePermissionCollections.CRM_CAMPAIGN));
        this.addExtraButton(importBtn);

    }

    @Override
    protected DefaultGenericSearchPanel<CampaignSearchCriteria> createSearchPanel() {
        return new CampaignSearchPanel();
    }

    @Override
    protected AbstractPagedBeanTable<CampaignSearchCriteria, SimpleCampaign> createBeanTable() {
        CampaignTableDisplay campaignTableDisplay = new CampaignTableDisplay(
                CampaignListView.VIEW_DEF_ID, CampaignTableFieldDef.selected(),
                Arrays.asList(CampaignTableFieldDef.campaignname(),
                        CampaignTableFieldDef.status(),
                        CampaignTableFieldDef.type(),
                        CampaignTableFieldDef.expectedRevenue(),
                        CampaignTableFieldDef.endDate(),
                        CampaignTableFieldDef.assignUser()));

        campaignTableDisplay.addTableListener(new TableClickListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void itemClick(final TableClickEvent event) {
                final SimpleCampaign campaign = (SimpleCampaign) event
                        .getData();
                if ("campaignname".equals(event.getFieldName())) {
                    EventBusFactory.getInstance()
                            .post(new CampaignEvent.GotoRead(CampaignListViewImpl.this, campaign.getId()));
                }
            }
        });

        return campaignTableDisplay;
    }

    @Override
    protected DefaultMassItemActionHandlerContainer createActionControls() {
        DefaultMassItemActionHandlerContainer container = new DefaultMassItemActionHandlerContainer();

        if (AppContext.canAccess(RolePermissionCollections.CRM_CAMPAIGN)) {
            container.addDeleteActionItem();
        }

        container.addMailActionItem();
        container.addDownloadPdfActionItem();
        container.addDownloadExcelActionItem();
        container.addDownloadCsvActionItem();

        if (AppContext.canWrite(RolePermissionCollections.CRM_CAMPAIGN)) {
            container.addMassUpdateActionItem();
        }

        return container;
    }
}
