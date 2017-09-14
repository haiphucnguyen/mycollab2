package com.mycollab.pro.module.project.view.time;

import com.mycollab.db.arguments.SetSearchField;
import com.mycollab.db.query.DateParam;
import com.mycollab.db.query.VariableInjector;
import com.mycollab.module.project.CurrentProjectVariables;
import com.mycollab.module.project.ProjectTypeConstants;
import com.mycollab.module.project.domain.criteria.ItemTimeLoggingSearchCriteria;
import com.mycollab.module.project.i18n.InvoiceI18nEnum;
import com.mycollab.module.project.i18n.ProjectCommonI18nEnum;
import com.mycollab.module.project.ui.ProjectAssetsManager;
import com.mycollab.module.project.view.parameters.TimeTrackingScreenData;
import com.mycollab.module.project.view.time.IFinanceContainer;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.mvp.AbstractVerticalPageView;
import com.mycollab.vaadin.mvp.PresenterResolver;
import com.mycollab.vaadin.mvp.ViewComponent;
import com.mycollab.vaadin.web.ui.TabSheetDecorator;
import com.vaadin.ui.Component;
import com.vaadin.ui.TabSheet;

/**
 * @author MyCollab Ltd
 * @since 2.0
 */
@ViewComponent
public class FinanceContainer extends AbstractVerticalPageView implements IFinanceContainer {
    private static final long serialVersionUID = 1L;

    private TabSheetDecorator myProjectTab;

    @Override
    public void initContent() {
        removeAllComponents();
        this.myProjectTab = new TabSheetDecorator();
        this.addComponent(myProjectTab);
        this.buildComponents();
    }

    private void buildComponents() {
        if (CurrentProjectVariables.hasTimeFeature()) {
            myProjectTab.addWrappedTab(UserUIContext.getMessage(ProjectCommonI18nEnum.VIEW_TIME),
                    ProjectAssetsManager.getAsset(ProjectTypeConstants.TIME));
        }

        if (CurrentProjectVariables.hasInvoiceFeature()) {
            myProjectTab.addWrappedTab(UserUIContext.getMessage(InvoiceI18nEnum.LIST),
                    ProjectAssetsManager.getAsset(ProjectTypeConstants.INVOICE));
        }

        myProjectTab.addSelectedTabChangeListener(new TabSheet.SelectedTabChangeListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void selectedTabChange(TabSheet.SelectedTabChangeEvent event) {
                TabSheet.Tab tab = ((TabSheetDecorator) event.getTabSheet()).getSelectedTabInfo();
                String caption = tab.getCaption();
                if (UserUIContext.getMessage(ProjectCommonI18nEnum.VIEW_TIME).equals(caption)) {
                    showTimeView();
                } else {
                    showInvoiceView();
                }
            }
        });
    }

    @Override
    public Component gotoSubView(String name) {
        return myProjectTab.selectTab(name).getComponent();
    }

    @Override
    public void showTimeView() {
        TimeTrackingListPresenter timeTrackingListPresenter = PresenterResolver.getPresenter(TimeTrackingListPresenter.class);
        ItemTimeLoggingSearchCriteria searchCriteria = new ItemTimeLoggingSearchCriteria();
        searchCriteria.setProjectIds(new SetSearchField<>(CurrentProjectVariables.getProjectId()));
        searchCriteria.addExtraField(DateParam.inRangeDate(ItemTimeLoggingSearchCriteria.p_logDates,
                VariableInjector.THIS_WEEK));
        timeTrackingListPresenter.go(FinanceContainer.this, new TimeTrackingScreenData.Search(searchCriteria));
    }

    @Override
    public void showInvoiceView() {
        InvoicePresenter invoicePresenter = PresenterResolver.getPresenter(InvoicePresenter.class);
        invoicePresenter.go(FinanceContainer.this, null);
    }
}
