package com.mycollab.premium.mobile.module.crm.view.cases;

import com.mycollab.db.arguments.NumberSearchField;
import com.mycollab.mobile.module.crm.view.cases.CaseListDisplay;
import com.mycollab.mobile.ui.AbstractSelectionView;
import com.mycollab.module.crm.domain.SimpleCase;
import com.mycollab.module.crm.domain.criteria.CaseSearchCriteria;
import com.mycollab.module.crm.i18n.CaseI18nEnum;
import com.mycollab.vaadin.MyCollabUI;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.ui.IBeanList;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;

/**
 * @author MyCollab Ltd.
 * @since 4.1
 */
public class CaseSelectionView extends AbstractSelectionView<SimpleCase> {
    private static final long serialVersionUID = 2092608350938161913L;

    private CaseListDisplay itemList;

    private CaseRowDisplayHandler rowHandler = new CaseRowDisplayHandler();

    public CaseSelectionView() {
        createUI();
        this.setCaption(UserUIContext.getMessage(CaseI18nEnum.M_VIEW_CASE_NAME_LOOKUP));
    }

    private void createUI() {
        itemList = new CaseListDisplay();
        itemList.setWidth("100%");
        itemList.setRowDisplayHandler(rowHandler);
        this.setContent(itemList);
    }

    @Override
    public void load() {
        CaseSearchCriteria searchCriteria = new CaseSearchCriteria();
        searchCriteria.setSaccountid(new NumberSearchField(MyCollabUI.getAccountId()));
        itemList.search(searchCriteria);
        SimpleCase clearCase = new SimpleCase();
        itemList.addComponentAtTop(rowHandler.generateRow(itemList, clearCase, 0));
    }

    private class CaseRowDisplayHandler implements IBeanList.RowDisplayHandler<SimpleCase> {

        @Override
        public Component generateRow(IBeanList<SimpleCase> host, final SimpleCase cases, int rowIndex) {
            return new Button(cases.getSubject(), clickEvent -> {
                selectionField.fireValueChange(cases);
                CaseSelectionView.this.getNavigationManager().navigateBack();
            });
        }
    }
}
