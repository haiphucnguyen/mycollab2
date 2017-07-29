package com.mycollab.module.crm.ui.format;

import com.hp.gagawa.java.elements.A;
import com.hp.gagawa.java.elements.Div;
import com.hp.gagawa.java.elements.Text;
import com.mycollab.common.i18n.GenericI18Enum;
import com.mycollab.html.DivLessFormatter;
import com.mycollab.module.crm.CrmLinkBuilder;
import com.mycollab.module.crm.CrmTypeConstants;
import com.mycollab.module.crm.domain.SimpleCampaign;
import com.mycollab.module.crm.service.CampaignService;
import com.mycollab.module.crm.ui.CrmAssetsManager;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.MyCollabUI;
import com.mycollab.vaadin.TooltipHelper;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.ui.formatter.HistoryFieldFormat;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.mycollab.vaadin.TooltipHelper.TOOLTIP_ID;

/**
 * @author MyCollab Ltd
 * @since 5.2.11
 */
public class CampaignHistoryFieldFormat implements HistoryFieldFormat {
    private static final Logger LOG = LoggerFactory.getLogger(CampaignHistoryFieldFormat.class);

    @Override
    public String toString(String value) {
        return toString(value, true, UserUIContext.getMessage(GenericI18Enum.FORM_EMPTY));
    }

    @Override
    public String toString(String value, Boolean displayAsHtml, String msgIfBlank) {
        if (StringUtils.isBlank(value)) {
            return msgIfBlank;
        }

        try {
            Integer campaignId = Integer.parseInt(value);
            CampaignService campaignService = AppContextUtil.getSpringBean(CampaignService.class);
            SimpleCampaign campaign = campaignService.findById(campaignId, MyCollabUI.getAccountId());

            if (campaign != null) {
                if (displayAsHtml) {
                    A link = new A().setId("tag" + TOOLTIP_ID);
                    link.setHref(CrmLinkBuilder.generateCampaignPreviewLinkFull(campaignId))
                            .appendChild(new Text(campaign.getCampaignname()));
                    link.setAttribute("onmouseover", TooltipHelper.projectHoverJsFunction(CrmTypeConstants.CAMPAIGN,
                            campaignId + ""));
                    link.setAttribute("onmouseleave", TooltipHelper.itemMouseLeaveJsFunction());
                    Div div = new DivLessFormatter().appendChild(new Text(CrmAssetsManager.getAsset(CrmTypeConstants.CAMPAIGN).getHtml()),
                            DivLessFormatter.EMPTY_SPACE(), link);
                    return div.write();
                } else {
                    return campaign.getCampaignname();
                }
            }
        } catch (Exception e) {
            LOG.error("Error", e);
        }

        return value;
    }
}
