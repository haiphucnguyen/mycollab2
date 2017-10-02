package com.mycollab.premium.mobile.module.crm.view.cases;

import com.mycollab.common.UrlEncodeDecoder;
import com.mycollab.common.i18n.GenericI18Enum;
import com.mycollab.vaadin.EventBusFactory;
import com.mycollab.mobile.module.crm.event.CaseEvent;
import com.mycollab.mobile.module.crm.view.AbstractCrmPresenter;
import com.mycollab.mobile.module.crm.view.cases.CaseAddView;
import com.mycollab.mobile.module.crm.view.cases.ICaseAddPresenter;
import com.mycollab.mobile.shell.event.ShellEvent;
import com.mycollab.module.crm.domain.CaseWithBLOBs;
import com.mycollab.module.crm.domain.SimpleCase;
import com.mycollab.module.crm.i18n.CaseI18nEnum;
import com.mycollab.module.crm.service.CaseService;
import com.mycollab.security.RolePermissionCollections;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.AppUI;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.event.DefaultEditFormHandler;
import com.mycollab.vaadin.mvp.ScreenData;
import com.mycollab.vaadin.ui.NotificationUtil;
import com.vaadin.ui.HasComponents;

/**
 * @author MyCollab Ltd.
 * @since 4.1
 */
public class CaseAddPresenter extends AbstractCrmPresenter<CaseAddView> implements ICaseAddPresenter {
    private static final long serialVersionUID = 8258508255937580306L;

    public CaseAddPresenter() {
        super(CaseAddView.class);
    }

    @Override
    protected void postInitView() {
        getView().getEditFormHandlers().addFormHandler(new DefaultEditFormHandler<SimpleCase>() {
            private static final long serialVersionUID = 1L;

            @Override
            public void onSave(final SimpleCase cases) {
                saveCase(cases);
                EventBusFactory.getInstance().post(new ShellEvent.NavigateBack(this, null));
            }

            @Override
            public void onSaveAndNew(final SimpleCase cases) {
                saveCase(cases);
                EventBusFactory.getInstance().post(new CaseEvent.GotoAdd(this, null));
            }
        });
    }

    @Override
    protected void onGo(HasComponents container, ScreenData<?> data) {
        if (UserUIContext.canWrite(RolePermissionCollections.CRM_CASE)) {

            SimpleCase cases = null;
            if (data.getParams() instanceof SimpleCase) {
                cases = (SimpleCase) data.getParams();
            } else if (data.getParams() instanceof Integer) {
                CaseService caseService = AppContextUtil.getSpringBean(CaseService.class);
                cases = caseService.findById((Integer) data.getParams(), AppUI.getAccountId());
            }
            if (cases == null) {
                NotificationUtil.showRecordNotExistNotification();
                return;
            }
            super.onGo(container, data);
            getView().editItem(cases);

            if (cases.getId() == null) {
                AppUI.addFragment("crm/cases/add", UserUIContext.getMessage(GenericI18Enum.BROWSER_ADD_ITEM_TITLE,
                        UserUIContext.getMessage(CaseI18nEnum.SINGLE)));
            } else {
                AppUI.addFragment("crm/cases/edit/" + UrlEncodeDecoder.encode(cases.getId()),
                        UserUIContext.getMessage(GenericI18Enum.BROWSER_EDIT_ITEM_TITLE,
                                UserUIContext.getMessage(CaseI18nEnum.SINGLE), cases.getSubject()));
            }
        } else {
            NotificationUtil.showMessagePermissionAlert();
        }
    }

    private void saveCase(CaseWithBLOBs cases) {
        CaseService caseService = AppContextUtil.getSpringBean(CaseService.class);
        cases.setSaccountid(AppUI.getAccountId());
        if (cases.getId() == null) {
            caseService.saveWithSession(cases, UserUIContext.getUsername());
        } else {
            caseService.updateWithSession(cases, UserUIContext.getUsername());
        }
    }
}
