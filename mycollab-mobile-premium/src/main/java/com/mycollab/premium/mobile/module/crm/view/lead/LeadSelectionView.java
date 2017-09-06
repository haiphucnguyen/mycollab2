package com.mycollab.premium.mobile.module.crm.view.lead;

import com.mycollab.db.arguments.NumberSearchField;
import com.mycollab.mobile.module.crm.view.lead.LeadListDisplay;
import com.mycollab.mobile.ui.AbstractSelectionView;
import com.mycollab.module.crm.domain.SimpleLead;
import com.mycollab.module.crm.domain.criteria.LeadSearchCriteria;
import com.mycollab.module.crm.i18n.LeadI18nEnum;
import com.mycollab.vaadin.AppUI;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.ui.IBeanList;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;

/**
 * @author MyCollab Ltd.
 * @since 4.1
 */
public class LeadSelectionView extends AbstractSelectionView<SimpleLead> {
    private static final long serialVersionUID = 8715554837844950390L;

    private LeadListDisplay itemList;

    private LeadRowDisplayHandler rowHandler = new LeadRowDisplayHandler();

    public LeadSelectionView() {
        createUI();
        this.setCaption(UserUIContext.getMessage(LeadI18nEnum.M_VIEW_LEAD_NAME_LOOKUP));
    }

    private void createUI() {
        itemList = new LeadListDisplay();
        itemList.setWidth("100%");
        itemList.setRowDisplayHandler(rowHandler);
        this.setContent(itemList);
    }

    @Override
    public void load() {
        LeadSearchCriteria searchCriteria = new LeadSearchCriteria();
        searchCriteria.setSaccountid(new NumberSearchField(AppUI.getAccountId()));
        itemList.search(searchCriteria);
        SimpleLead clearLead = new SimpleLead();
        itemList.addComponentAtTop(rowHandler.generateRow(itemList, clearLead, 0));
    }

    private class LeadRowDisplayHandler implements IBeanList.RowDisplayHandler<SimpleLead> {

        @Override
        public Component generateRow(IBeanList<SimpleLead> host, final SimpleLead lead, int rowIndex) {
            return new Button(lead.getLeadName(), clickEvent -> {
                selectionField.fireValueChange(lead);
                LeadSelectionView.this.getNavigationManager().navigateBack();
            });
        }
    }
}
