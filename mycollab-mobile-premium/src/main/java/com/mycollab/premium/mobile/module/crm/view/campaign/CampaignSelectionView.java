package com.mycollab.premium.mobile.module.crm.view.campaign;

import com.mycollab.db.arguments.NumberSearchField;
import com.mycollab.mobile.module.crm.view.campaign.CampaignListDisplay;
import com.mycollab.mobile.ui.AbstractSelectionView;
import com.mycollab.module.crm.domain.CampaignWithBLOBs;
import com.mycollab.module.crm.domain.SimpleCampaign;
import com.mycollab.module.crm.domain.criteria.CampaignSearchCriteria;
import com.mycollab.module.crm.i18n.CampaignI18nEnum;
import com.mycollab.vaadin.AppUI;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.ui.IBeanList;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;

/**
 * @author MyCollab Ltd.
 * @since 4.1
 */
public class CampaignSelectionView extends AbstractSelectionView<CampaignWithBLOBs> {
    private static final long serialVersionUID = 1L;

    private CampaignListDisplay itemList;

    private CampaignRowDisplayHandler rowHandler = new CampaignRowDisplayHandler();

    public CampaignSelectionView() {
        createUI();
        this.setCaption(UserUIContext.getMessage(CampaignI18nEnum.M_VIEW_CAMPAIGN_NAME_LOOKUP));
    }

    private void createUI() {
        itemList = new CampaignListDisplay();
        itemList.setWidth("100%");
        itemList.setRowDisplayHandler(rowHandler);
        this.setContent(itemList);
    }

    @Override
    public void load() {
        CampaignSearchCriteria searchCriteria = new CampaignSearchCriteria();
        searchCriteria.setSaccountid(new NumberSearchField(AppUI.getAccountId()));
        itemList.search(searchCriteria);
        SimpleCampaign clearCampaign = new SimpleCampaign();
        itemList.addComponentAtTop(rowHandler.generateRow(itemList, clearCampaign, 0));
    }

    private class CampaignRowDisplayHandler implements IBeanList.RowDisplayHandler<SimpleCampaign> {

        @Override
        public Component generateRow(IBeanList<SimpleCampaign> host, final SimpleCampaign campaign, int rowIndex) {
            return new Button(campaign.getCampaignname(), clickEvent -> {
                selectionField.fireValueChange(campaign);
                CampaignSelectionView.this.getNavigationManager().navigateBack();
            });
        }
    }
}
