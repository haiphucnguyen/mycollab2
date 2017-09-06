package com.mycollab.premium.mobile.module.crm.view.opportunity;

import com.mycollab.db.arguments.NumberSearchField;
import com.mycollab.mobile.module.crm.view.opportunity.OpportunityListDisplay;
import com.mycollab.mobile.ui.AbstractSelectionView;
import com.mycollab.module.crm.domain.SimpleOpportunity;
import com.mycollab.module.crm.domain.criteria.OpportunitySearchCriteria;
import com.mycollab.module.crm.i18n.OpportunityI18nEnum;
import com.mycollab.vaadin.AppUI;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.ui.IBeanList;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;

/**
 * @author MyCollab Ltd.
 * @since 4.1
 */
public class OpportunitySelectionView extends AbstractSelectionView<SimpleOpportunity> {
    private static final long serialVersionUID = -4651110982471036490L;

    private OpportunityListDisplay itemList;
    private OpportunityRowDisplayHandler rowHandler = new OpportunityRowDisplayHandler();

    public OpportunitySelectionView() {
        createUI();
        this.setCaption(UserUIContext.getMessage(OpportunityI18nEnum.M_VIEW_OPPORTUNITY_NAME_LOOKUP));
    }

    private void createUI() {
        itemList = new OpportunityListDisplay();
        itemList.setWidth("100%");
        itemList.setRowDisplayHandler(rowHandler);
        this.setContent(itemList);
    }

    @Override
    public void load() {
        OpportunitySearchCriteria searchCriteria = new OpportunitySearchCriteria();
        searchCriteria.setSaccountid(new NumberSearchField(AppUI.getAccountId()));
        itemList.search(searchCriteria);
        SimpleOpportunity clearOpportunity = new SimpleOpportunity();
        itemList.addComponentAtTop(rowHandler.generateRow(itemList, clearOpportunity, 0));
    }

    private class OpportunityRowDisplayHandler implements IBeanList.RowDisplayHandler<SimpleOpportunity> {

        @Override
        public Component generateRow(IBeanList<SimpleOpportunity> host, final SimpleOpportunity opportunity, int rowIndex) {
            return new Button(opportunity.getOpportunityname(), clickEvent -> {
                selectionField.fireValueChange(opportunity);
                OpportunitySelectionView.this.getNavigationManager().navigateBack();
            });
        }
    }
}
