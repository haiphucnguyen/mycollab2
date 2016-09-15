package com.mycollab.pro.module.project.view.risk;

import com.mycollab.common.i18n.GenericI18Enum;
import com.mycollab.core.arguments.ValuedBean;
import com.mycollab.db.persistence.service.ISearchableService;
import com.mycollab.module.project.CurrentProjectVariables;
import com.mycollab.module.project.ProjectRolePermissionCollections;
import com.mycollab.module.project.domain.Risk;
import com.mycollab.module.project.domain.SimpleRisk;
import com.mycollab.module.project.domain.criteria.RiskSearchCriteria;
import com.mycollab.module.project.i18n.RiskI18nEnum;
import com.mycollab.module.project.service.RiskService;
import com.mycollab.module.project.view.ProjectBreadcrumb;
import com.mycollab.module.project.view.ProjectGenericListPresenter;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.MyCollabUI;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.events.ViewItemAction;
import com.mycollab.vaadin.mvp.*;
import com.mycollab.vaadin.ui.NotificationUtil;
import com.mycollab.vaadin.web.ui.DefaultMassEditActionHandler;
import com.mycollab.vaadin.web.ui.MailFormWindow;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.UI;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
@LoadPolicy(scope = ViewScope.PROTOTYPE)
public class RiskListPresenter extends ProjectGenericListPresenter<RiskListView, RiskSearchCriteria, SimpleRisk>
        implements ListCommand<RiskSearchCriteria>, MassUpdateCommand<SimpleRisk> {
    private static final long serialVersionUID = 1L;

    private RiskService riskService;

    public RiskListPresenter() {
        super(RiskListView.class);
        riskService = AppContextUtil.getSpringBean(RiskService.class);
    }

    @Override
    protected void viewAttached() {
        super.viewAttached();

        view.getPopupActionHandlers().setMassActionHandler(new DefaultMassEditActionHandler(this) {
            @Override
            protected void onSelectExtra(String id) {
                if (ViewItemAction.MAIL_ACTION().equals(id)) {
                    UI.getCurrent().addWindow(new MailFormWindow());

                } else if (ViewItemAction.MASS_UPDATE_ACTION().equals(id)) {
                    MassUpdateRiskWindow massUpdateWindow = new MassUpdateRiskWindow(UserUIContext.getMessage(GenericI18Enum.WINDOW_MASS_UPDATE_TITLE,
                            UserUIContext.getMessage(RiskI18nEnum.LIST)), RiskListPresenter.this);
                    UI.getCurrent().addWindow(massUpdateWindow);
                }
            }

            @Override
            protected String getReportTitle() {
                return UserUIContext.getMessage(RiskI18nEnum.LIST);
            }

            @Override
            protected Class<?> getReportModelClassType() {
                return SimpleRisk.class;
            }
        });
    }

    @Override
    protected void onGo(ComponentContainer container, ScreenData<?> data) {
        if (CurrentProjectVariables.canRead(ProjectRolePermissionCollections.RISKS)) {
            RiskContainer riskContainer = (RiskContainer) container;
            riskContainer.removeAllComponents();
            riskContainer.addComponent(view);

            searchCriteria = (RiskSearchCriteria) data.getParams();
            int totalCount = riskService.getTotalCount(searchCriteria);

            if (totalCount > 0) {
                doSearch(searchCriteria);
            } else {
                view.showNoItemView();
            }

            ProjectBreadcrumb breadCrumb = ViewManager.getCacheComponent(ProjectBreadcrumb.class);
            breadCrumb.gotoRiskList();
        } else {
            NotificationUtil.showMessagePermissionAlert();
        }
    }

    @Override
    protected void deleteSelectedItems() {
        if (!isSelectAll) {
            Collection<SimpleRisk> risks = view.getPagedBeanTable().getCurrentDataList();
            List<Risk> keyList = new ArrayList<>();
            for (SimpleRisk risk : risks) {
                if (risk.isSelected()) {
                    keyList.add(risk);
                }
            }

            if (keyList.size() > 0) {
                riskService.massRemoveWithSession(keyList, UserUIContext.getUsername(), MyCollabUI.getAccountId());
            }
        } else {
            riskService.removeByCriteria(searchCriteria, MyCollabUI.getAccountId());
        }

        int totalCount = riskService.getTotalCount(searchCriteria);

        if (totalCount > 0) {
            doSearch(searchCriteria);
        } else {
            view.showNoItemView();
        }
    }

    @Override
    public void massUpdate(SimpleRisk value) {
        if (!isSelectAll) {
            Collection<SimpleRisk> risks = view.getPagedBeanTable().getCurrentDataList();
            List<Integer> riskKeys = risks.stream().filter(ValuedBean::isSelected).map(Risk::getId).collect(Collectors.toList());

            if (riskKeys.size() > 0) {
                riskService.massUpdateWithSession(value, riskKeys, MyCollabUI.getAccountId());
                doSearch(searchCriteria);
            }
        } else {
            riskService.updateBySearchCriteria(value, searchCriteria);
            doSearch(searchCriteria);
        }
    }

    @Override
    public ISearchableService<RiskSearchCriteria> getSearchService() {
        return AppContextUtil.getSpringBean(RiskService.class);
    }
}
