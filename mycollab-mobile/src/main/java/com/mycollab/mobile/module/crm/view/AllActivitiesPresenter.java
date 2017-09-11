package com.mycollab.mobile.module.crm.view;

import com.mycollab.common.domain.criteria.ActivityStreamSearchCriteria;
import com.mycollab.db.arguments.NumberSearchField;
import com.mycollab.db.arguments.SearchCriteria;
import com.mycollab.db.arguments.SetSearchField;
import com.mycollab.module.crm.CrmTypeConstants;
import com.mycollab.security.RolePermissionCollections;
import com.mycollab.vaadin.AppUI;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.mvp.ScreenData;
import com.vaadin.addon.touchkit.ui.NavigationManager;
import com.vaadin.ui.HasComponents;

/**
 * @author MyCollab Ltd.
 * @since 4.4.0
 */
public class AllActivitiesPresenter extends AbstractCrmPresenter<AllActivitiesView> {
    private static final long serialVersionUID = -2422488836026839744L;

    public AllActivitiesPresenter() {
        super(AllActivitiesView.class);
    }

    @Override
    protected void onGo(HasComponents navigator, ScreenData<?> data) {
        NavigationManager currentNav = (NavigationManager) navigator;
        ActivityStreamSearchCriteria searchCriteria = new ActivityStreamSearchCriteria();
        searchCriteria.setModuleSet(new SetSearchField(CrmModule.TYPE));
        searchCriteria.setSaccountid(new NumberSearchField(AppUI.getAccountId()));
        searchCriteria.setTypes(getRestrictedItemTypes());
        searchCriteria.addOrderField(new SearchCriteria.OrderField("createdTime", SearchCriteria.Companion.getDESC()));
        view.getPagedBeanTable().setSearchCriteria(searchCriteria);
        currentNav.navigateTo(view);
    }

    private SetSearchField<String> getRestrictedItemTypes() {
        SetSearchField<String> types = new SetSearchField<>();
        if (UserUIContext.canRead(RolePermissionCollections.INSTANCE.getCRM_ACCOUNT())) {
            types.addValue(CrmTypeConstants.INSTANCE.getACCOUNT());
        }
        if (UserUIContext.canRead(RolePermissionCollections.INSTANCE.getCRM_CONTACT())) {
            types.addValue(CrmTypeConstants.INSTANCE.getCONTACT());
        }
        if (UserUIContext.canRead(RolePermissionCollections.INSTANCE.getCRM_LEAD())) {
            types.addValue(CrmTypeConstants.INSTANCE.getLEAD());
        }
        if (UserUIContext.canRead(RolePermissionCollections.INSTANCE.getCRM_CAMPAIGN())) {
            types.addValue(CrmTypeConstants.INSTANCE.getCAMPAIGN());
        }
        if (UserUIContext.canRead(RolePermissionCollections.INSTANCE.getCRM_OPPORTUNITY())) {
            types.addValue(CrmTypeConstants.INSTANCE.getOPPORTUNITY());
        }
        if (UserUIContext.canRead(RolePermissionCollections.INSTANCE.getCRM_CASE())) {
            types.addValue(CrmTypeConstants.INSTANCE.getCASE());
        }
        if (UserUIContext.canRead(RolePermissionCollections.INSTANCE.getCRM_TASK())) {
            types.addValue(CrmTypeConstants.INSTANCE.getTASK());
        }
        if (UserUIContext.canRead(RolePermissionCollections.INSTANCE.getCRM_MEETING())) {
            types.addValue(CrmTypeConstants.INSTANCE.getMEETING());
        }
        if (UserUIContext.canRead(RolePermissionCollections.INSTANCE.getCRM_CALL())) {
            types.addValue(CrmTypeConstants.INSTANCE.getCALL());
        }
        return types;
    }
}
