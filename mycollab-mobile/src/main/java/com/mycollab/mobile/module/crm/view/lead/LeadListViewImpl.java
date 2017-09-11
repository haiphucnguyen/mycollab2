package com.mycollab.mobile.module.crm.view.lead;

import com.mycollab.eventmanager.EventBusFactory;
import com.mycollab.mobile.module.crm.events.LeadEvent;
import com.mycollab.mobile.module.crm.ui.AbstractListPageView;
import com.mycollab.mobile.ui.AbstractPagedBeanList;
import com.mycollab.mobile.ui.SearchInputField;
import com.mycollab.mobile.ui.SearchInputView;
import com.mycollab.mobile.ui.SearchNavigationButton;
import com.mycollab.module.crm.domain.SimpleLead;
import com.mycollab.module.crm.domain.criteria.LeadSearchCriteria;
import com.mycollab.module.crm.i18n.LeadI18nEnum;
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
 * @since 4.1
 */
@ViewComponent
public class LeadListViewImpl extends AbstractListPageView<LeadSearchCriteria, SimpleLead> implements LeadListView {
    private static final long serialVersionUID = -5311139413938817084L;

    public LeadListViewImpl() {
        setCaption(UserUIContext.getMessage(LeadI18nEnum.LIST));
    }

    @Override
    protected AbstractPagedBeanList<LeadSearchCriteria, SimpleLead> createBeanList() {
        return new LeadListDisplay();
    }

    @Override
    protected SearchInputField<LeadSearchCriteria> createSearchField() {
        return null;
    }

    @Override
    public void onBecomingVisible() {
        super.onBecomingVisible();
        AppUI.addFragment("crm/lead/list", UserUIContext.getMessage(LeadI18nEnum.LIST));
    }

    @Override
    protected Component buildRightComponent() {
        SearchNavigationButton searchBtn = new SearchNavigationButton() {
            @Override
            protected SearchInputView getSearchInputView() {
                return new LeadSearchInputView();
            }
        };
        MButton newLeadBtn = new MButton("", clickEvent -> EventBusFactory.getInstance().post(new LeadEvent.GotoAdd(this, null)))
                .withIcon(FontAwesome.PLUS).withStyleName(UIConstants.CIRCLE_BOX)
                .withVisible(UserUIContext.canWrite(RolePermissionCollections.INSTANCE.getCRM_LEAD()));
        return new MHorizontalLayout(searchBtn, newLeadBtn).alignAll(Alignment.TOP_RIGHT);
    }
}