package com.mycollab.mobile.module.crm.view.account;

import com.mycollab.eventmanager.EventBusFactory;
import com.mycollab.mobile.module.crm.event.AccountEvent;
import com.mycollab.mobile.module.crm.ui.AbstractListPageView;
import com.mycollab.mobile.ui.AbstractPagedBeanList;
import com.mycollab.mobile.ui.SearchInputField;
import com.mycollab.mobile.ui.SearchInputView;
import com.mycollab.mobile.ui.SearchNavigationButton;
import com.mycollab.module.crm.domain.SimpleAccount;
import com.mycollab.module.crm.domain.criteria.AccountSearchCriteria;
import com.mycollab.module.crm.i18n.AccountI18nEnum;
import com.mycollab.security.RolePermissionCollections;
import com.mycollab.vaadin.AppUI;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.mvp.ViewComponent;
import com.mycollab.vaadin.ui.UIConstants;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.layouts.MHorizontalLayout;

/**
 * @author MyCollab Ltd.
 * @since 3.0
 */
@ViewComponent
public class AccountListViewImpl extends AbstractListPageView<AccountSearchCriteria, SimpleAccount> implements AccountListView {
    private static final long serialVersionUID = -500810154594390148L;

    public AccountListViewImpl() {
        setCaption(UserUIContext.getMessage(AccountI18nEnum.LIST));
    }

    @Override
    protected AbstractPagedBeanList<AccountSearchCriteria, SimpleAccount> createBeanList() {
        return new AccountListDisplay();
    }

    @Override
    protected Component buildRightComponent() {
        SearchNavigationButton searchBtn = new SearchNavigationButton() {
            @Override
            protected SearchInputView getSearchInputView() {
                return new AccountSearchInputView();
            }
        };
        MButton newAccountBtn = new MButton("", clickEvent -> EventBusFactory.getInstance().post(new AccountEvent.GotoAdd(this, null)))
                .withIcon(FontAwesome.PLUS).withStyleName(UIConstants.CIRCLE_BOX)
                .withVisible(UserUIContext.canWrite(RolePermissionCollections.CRM_ACCOUNT));
        return new MHorizontalLayout(searchBtn, newAccountBtn).alignAll(Alignment.TOP_RIGHT);
    }

    @Override
    public void onBecomingVisible() {
        super.onBecomingVisible();
        AppUI.addFragment("crm/account/list", UserUIContext.getMessage(AccountI18nEnum.LIST));
    }

    @Override
    protected SearchInputField<AccountSearchCriteria> createSearchField() {
        return null;
    }
}
