package com.esofthead.mycollab.module.crm.ui.format;

import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.html.DivLessFormatter;
import com.esofthead.mycollab.module.crm.CrmLinkGenerator;
import com.esofthead.mycollab.module.crm.CrmTypeConstants;
import com.esofthead.mycollab.module.crm.domain.SimpleCampaign;
import com.esofthead.mycollab.module.crm.service.CampaignService;
import com.esofthead.mycollab.module.crm.ui.CrmAssetsManager;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.utils.HistoryFieldFormat;
import com.esofthead.mycollab.utils.TooltipHelper;
import com.esofthead.mycollab.vaadin.AppContext;
import com.hp.gagawa.java.elements.A;
import com.hp.gagawa.java.elements.Div;
import com.hp.gagawa.java.elements.Text;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.esofthead.mycollab.utils.TooltipHelper.TOOLTIP_ID;

/**
 * @author MyCollab Ltd
 * @since 5.2.11
 */
public class CampaignHistoryFieldFormat implements HistoryFieldFormat {
    private static final Logger LOG = LoggerFactory.getLogger(CampaignHistoryFieldFormat.class);

    @Override
    public String toString(String value) {
        return toString(value, true, AppContext.getMessage(GenericI18Enum.FORM_EMPTY));
    }

    @Override
    public String toString(String value, Boolean displayAsHtml, String msgIfBlank) {
        if (StringUtils.isBlank(value)) {
            return msgIfBlank;
        }

        try {
            Integer campaignId = Integer.parseInt(value);
            CampaignService campaignService = ApplicationContextUtil.getSpringBean(CampaignService.class);
            SimpleCampaign campaign = campaignService.findById(campaignId, AppContext.getAccountId());

            if (campaign != null) {
                if (displayAsHtml) {
                    A link = new A().setId("tag" + TOOLTIP_ID);
                    link.setHref(CrmLinkGenerator.generateCampaignPreviewFullLink(AppContext.getSiteUrl(), campaignId))
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
