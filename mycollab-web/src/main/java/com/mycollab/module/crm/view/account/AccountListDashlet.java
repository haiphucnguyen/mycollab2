package com.mycollab.module.crm.view.account;

import com.mycollab.common.i18n.GenericI18Enum;
import com.mycollab.db.arguments.NumberSearchField;
import com.mycollab.db.arguments.StringSearchField;
import com.mycollab.module.crm.domain.criteria.AccountSearchCriteria;
import com.mycollab.module.crm.fielddef.AccountTableFieldDef;
import com.mycollab.module.crm.i18n.AccountI18nEnum;
import com.mycollab.vaadin.MyCollabUI;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.web.ui.Depot;
import com.mycollab.vaadin.web.ui.WebThemes;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import org.vaadin.viritin.button.MButton;

import java.util.Arrays;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
public class AccountListDashlet extends Depot {
    private static final long serialVersionUID = 1L;
    private AccountTableDisplay tableItem;

    public AccountListDashlet() {
        super(UserUIContext.getMessage(AccountI18nEnum.MY_ITEMS), new VerticalLayout());
        this.setMargin(new MarginInfo(true, false, false, false));
        tableItem = new AccountTableDisplay(Arrays.asList(AccountTableFieldDef.accountname(),
                AccountTableFieldDef.phoneoffice(), AccountTableFieldDef.email()));
        bodyContent.addComponent(tableItem);

        MButton customizeViewBtn = new MButton("", clickEvent -> UI.getCurrent().addWindow(new AccountListCustomizeWindow(tableItem)))
                .withIcon(FontAwesome.ADJUST).withStyleName(WebThemes.BUTTON_SMALL_PADDING);
        customizeViewBtn.setDescription(UserUIContext.getMessage(GenericI18Enum.OPT_LAYOUT_OPTIONS));
        this.addHeaderElement(customizeViewBtn);
    }

    public void display() {
        final AccountSearchCriteria criteria = new AccountSearchCriteria();
        criteria.setSaccountid(new NumberSearchField(MyCollabUI.getAccountId()));
        criteria.setAssignUser(StringSearchField.and(UserUIContext.getUsername()));
        tableItem.setSearchCriteria(criteria);
    }
}
