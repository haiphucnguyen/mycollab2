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
package com.mycollab.module.crm.view.campaign;

import com.mycollab.module.crm.domain.CampaignWithBLOBs;
import com.mycollab.module.crm.domain.SimpleCampaign;
import com.mycollab.module.crm.service.CampaignService;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.ui.FieldSelection;
import com.mycollab.vaadin.web.ui.WebUIConstants;
import com.vaadin.data.Property;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.*;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.layouts.MHorizontalLayout;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
public class CampaignSelectionField extends CustomField<Integer> implements FieldSelection<CampaignWithBLOBs> {
    private CampaignWithBLOBs internalValue = new CampaignWithBLOBs();

    private TextField campaignName = new TextField();

    @Override
    public void setPropertyDataSource(Property newDataSource) {
        Object value = newDataSource.getValue();
        if (value instanceof Integer) {
            setCampaignByVal((Integer) value);
            super.setPropertyDataSource(newDataSource);
        } else {
            super.setPropertyDataSource(newDataSource);
        }
    }

    @Override
    public void setValue(Integer value) {
        this.setCampaignByVal(value);
        super.setValue(value);
    }

    private void setCampaignByVal(Integer campaignId) {
        CampaignService campaignService = AppContextUtil.getSpringBean(CampaignService.class);
        SimpleCampaign campaign = campaignService.findById(campaignId, UserUIContext.getAccountId());
        if (campaign != null) {
            setInternalCampaign(campaign);
        }
    }

    private void setInternalCampaign(SimpleCampaign campaign) {
        this.internalValue = campaign;
        campaignName.setValue(internalValue.getCampaignname());
    }

    @Override
    protected Component initContent() {
        MHorizontalLayout layout = new MHorizontalLayout().withFullWidth();
        layout.setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);

        MButton browseBtn = new MButton("", clickEvent -> {
            CampaignSelectionWindow campaignWindow = new CampaignSelectionWindow(CampaignSelectionField.this);
            UI.getCurrent().addWindow(campaignWindow);
            campaignWindow.show();
        }).withIcon(FontAwesome.ELLIPSIS_H).withStyleName(WebUIConstants.BUTTON_OPTION, WebUIConstants.BUTTON_SMALL_PADDING);

        MButton clearBtn = new MButton("", clickEvent -> {
            campaignName.setValue("");
            internalValue = null;
        }).withIcon(FontAwesome.TRASH_O).withStyleName(WebUIConstants.BUTTON_OPTION, WebUIConstants.BUTTON_SMALL_PADDING);

        layout.with(campaignName, browseBtn, clearBtn).expand(campaignName);
        return layout;
    }

    @Override
    public Class<Integer> getType() {
        return Integer.class;
    }

    @Override
    public void fireValueChange(CampaignWithBLOBs data) {
        this.internalValue = data;
        if (internalValue != null) {
            campaignName.setValue(internalValue.getCampaignname());
            setInternalValue(data.getId());
        }
    }
}
