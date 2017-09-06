package com.mycollab.module.crm.view.campaign;

import com.mycollab.common.TableViewField;
import com.mycollab.module.crm.CrmTooltipGenerator;
import com.mycollab.module.crm.CrmLinkBuilder;
import com.mycollab.module.crm.domain.SimpleCampaign;
import com.mycollab.module.crm.domain.criteria.CampaignSearchCriteria;
import com.mycollab.module.crm.i18n.OptionI18nEnum.CampaignStatus;
import com.mycollab.module.crm.i18n.OptionI18nEnum.CampaignType;
import com.mycollab.module.crm.service.CampaignService;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.AppUI;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.ui.ELabel;
import com.mycollab.vaadin.web.ui.*;
import com.mycollab.vaadin.web.ui.table.DefaultPagedBeanTable;
import com.vaadin.ui.Label;

import java.util.GregorianCalendar;
import java.util.List;

import static com.mycollab.module.crm.i18n.OptionI18nEnum.CampaignStatus.Completed;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
public class CampaignTableDisplay extends DefaultPagedBeanTable<CampaignService, CampaignSearchCriteria, SimpleCampaign> {

    public CampaignTableDisplay(List<TableViewField> displayColumns) {
        this(null, displayColumns);
    }

    public CampaignTableDisplay(TableViewField requiredColumn, List<TableViewField> displayColumns) {
        this(null, requiredColumn, displayColumns);
    }

    public CampaignTableDisplay(String viewId, TableViewField requiredColumn, List<TableViewField> displayColumns) {
        super(AppContextUtil.getSpringBean(CampaignService.class), SimpleCampaign.class, viewId, requiredColumn, displayColumns);
        this.addGeneratedColumn("selected", (source, itemId, columnId) -> {
            final SimpleCampaign campaign = getBeanByIndex(itemId);
            final CheckBoxDecor cb = new CheckBoxDecor("", campaign.isSelected());
            cb.addValueChangeListener(valueChangeEvent -> {
                fireSelectItemEvent(campaign);
                fireTableEvent(new TableClickEvent(CampaignTableDisplay.this, campaign, "selected"));
            });
            campaign.setExtraData(cb);
            return cb;
        });

        this.addGeneratedColumn("campaignname", (source, itemId, columnId) -> {
            final SimpleCampaign campaign = getBeanByIndex(itemId);

            LabelLink b = new LabelLink(campaign.getCampaignname(), CrmLinkBuilder.generateCampaignPreviewLinkFull(campaign.getId()));
            b.setDescription(CrmTooltipGenerator.generateTooltipCampaign(UserUIContext.getUserLocale(), AppUI.getDateFormat(),
                    campaign, AppUI.getSiteUrl(), UserUIContext.getUserTimeZone()));
            b.setStyleName(WebThemes.BUTTON_LINK);

            if (Completed.name().equals(campaign.getStatus())) {
                b.addStyleName(WebThemes.LINK_COMPLETED);
            } else {
                if (campaign.getEnddate() != null && (campaign.getEnddate().before(new GregorianCalendar().getTime()))) {
                    b.addStyleName(WebThemes.LINK_OVERDUE);
                }
            }
            return b;
        });

        this.addGeneratedColumn("assignUserFullName", (source, itemId, columnId) -> {
            final SimpleCampaign campaign = getBeanByIndex(itemId);
            return new UserLink(campaign.getAssignuser(), campaign.getAssignUserAvatarId(), campaign.getAssignUserFullName());
        });

        this.addGeneratedColumn("expectedrevenue", (source, itemId, columnId) -> {
            final SimpleCampaign campaign = getBeanByIndex(itemId);
            if (campaign.getExpectedrevenue() != null) {
                String expectedRevenueText = campaign.getExpectedrevenue() + "";
                if (campaign.getCurrencyid() != null) {
                    expectedRevenueText += " " + campaign.getCurrencyid();
                }

                return new Label(expectedRevenueText);
            } else {
                return new Label("");
            }
        });

        this.addGeneratedColumn("expectedcost", (source, itemId, columnId) -> {
            final SimpleCampaign campaign = getBeanByIndex(itemId);
            if (campaign.getExpectedrevenue() != null) {
                String expectedCostText = campaign.getExpectedcost() + "";
                if (campaign.getCurrencyid() != null) {
                    expectedCostText += " " + campaign.getCurrencyid();
                }

                return new Label(expectedCostText);
            } else {
                return new Label("");
            }
        });

        this.addGeneratedColumn("startdate", (source, itemId, columnId) -> {
            final SimpleCampaign campaign = getBeanByIndex(itemId);
            return new ELabel().prettyDate(campaign.getStartdate());
        });

        this.addGeneratedColumn("enddate", (source, itemId, columnId) -> {
            final SimpleCampaign campaign = getBeanByIndex(itemId);
            return new ELabel().prettyDate(campaign.getEnddate());
        });

        this.addGeneratedColumn("type", (source, itemId, columnId) -> {
            final SimpleCampaign campaign = getBeanByIndex(itemId);
            return ELabel.i18n(campaign.getType(), CampaignType.class);
        });

        this.addGeneratedColumn("status", (source, itemId, columnId) -> {
            final SimpleCampaign campaign = getBeanByIndex(itemId);
            return ELabel.i18n(campaign.getStatus(), CampaignStatus.class);
        });

        this.setWidth("100%");
    }
}
