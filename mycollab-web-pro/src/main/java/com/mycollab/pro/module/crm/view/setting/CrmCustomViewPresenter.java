package com.mycollab.pro.module.crm.view.setting;

import com.mycollab.module.crm.CrmTypeConstants;
import com.mycollab.module.crm.view.CrmGenericPresenter;
import com.mycollab.module.crm.view.setting.CrmSettingContainer;
import com.mycollab.module.crm.view.setting.ICrmCustomView;
import com.mycollab.module.crm.view.setting.ICrmCustomViewPresenter;
import com.mycollab.vaadin.AppUI;
import com.mycollab.vaadin.mvp.ScreenData;
import com.vaadin.ui.HasComponents;

/**
 * @author MyCollab Ltd.
 * @since 3.0
 */
public class CrmCustomViewPresenter extends CrmGenericPresenter<ICrmCustomView> implements ICrmCustomViewPresenter {
    private static final long serialVersionUID = 1L;

    public CrmCustomViewPresenter() {
        super(ICrmCustomView.class);
    }

    @Override
    protected void onGo(HasComponents container, ScreenData<?> data) {
        CrmSettingContainer settingContainer = (CrmSettingContainer) container;
        settingContainer.gotoSubView("customlayout");

        AppUI.addFragment("crm/setting/customlayout", "Custom Layouts");

        view.display(CrmTypeConstants.ACCOUNT);
    }

}
