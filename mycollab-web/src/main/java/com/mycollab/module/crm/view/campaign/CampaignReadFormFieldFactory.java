/**
 * Copyright © MyCollab
 * <p>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * <p>
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.mycollab.module.crm.view.campaign;

import com.mycollab.module.crm.domain.CampaignWithBLOBs;
import com.mycollab.module.crm.domain.SimpleCampaign;
import com.mycollab.module.crm.i18n.OptionI18nEnum.CampaignStatus;
import com.mycollab.module.crm.i18n.OptionI18nEnum.CampaignType;
import com.mycollab.vaadin.ui.AbstractBeanFieldGroupViewFieldFactory;
import com.mycollab.vaadin.ui.GenericBeanForm;
import com.mycollab.vaadin.ui.UIConstants;
import com.mycollab.vaadin.ui.field.CurrencyViewField;
import com.mycollab.vaadin.ui.field.DateViewField;
import com.mycollab.vaadin.ui.field.I18nFormViewField;
import com.mycollab.vaadin.ui.field.RichTextViewField;
import com.mycollab.vaadin.web.ui.field.UserLinkViewField;
import com.vaadin.data.HasValue;

/**
 * @author MyCollab Ltd.
 * @since 3.0
 */
class CampaignReadFormFieldFactory extends AbstractBeanFieldGroupViewFieldFactory<SimpleCampaign> {
    private static final long serialVersionUID = 1L;

    public CampaignReadFormFieldFactory(GenericBeanForm<SimpleCampaign> form) {
        super(form);
    }

    @Override
    protected HasValue<?> onCreateField(Object propertyId) {
        SimpleCampaign campaign = attachForm.getBean();

        if (propertyId.equals("assignuser")) {
            return new UserLinkViewField(campaign.getAssignuser(), campaign.getAssignUserAvatarId(),
                    campaign.getAssignUserFullName());
        } else if (propertyId.equals("startdate")) {
            return new DateViewField(campaign.getStartdate());
        } else if (propertyId.equals("enddate")) {
            return new DateViewField(campaign.getEnddate());
        } else if (propertyId.equals("currencyid")) {
            return new CurrencyViewField(campaign.getCurrencyid());
        } else if (propertyId.equals("description")) {
            return new RichTextViewField(campaign.getDescription());
        } else if (CampaignWithBLOBs.Field.type.equalTo(propertyId)) {
            return new I18nFormViewField(campaign.getType(), CampaignType.class);
        } else if (CampaignWithBLOBs.Field.status.equalTo(propertyId)) {
            return new I18nFormViewField(campaign.getStatus(), CampaignStatus.class).withStyleName(UIConstants.FIELD_NOTE);
        }

        return null;
    }

}
