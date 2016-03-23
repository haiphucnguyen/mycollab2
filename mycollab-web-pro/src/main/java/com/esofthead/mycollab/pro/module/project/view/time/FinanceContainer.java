package com.esofthead.mycollab.pro.module.project.view.time;

import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.core.db.query.DateParam;
import com.esofthead.mycollab.core.db.query.VariableInjecter;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectTypeConstants;
import com.esofthead.mycollab.module.project.domain.criteria.ItemTimeLoggingSearchCriteria;
import com.esofthead.mycollab.module.project.i18n.ProjectCommonI18nEnum;
import com.esofthead.mycollab.module.project.ui.ProjectAssetsManager;
import com.esofthead.mycollab.module.project.view.parameters.TimeTrackingScreenData;
import com.esofthead.mycollab.module.project.view.time.IFinanceContainer;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.mvp.AbstractPageView;
import com.esofthead.mycollab.vaadin.mvp.PresenterResolver;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.esofthead.mycollab.vaadin.web.ui.TabSheetDecorator;
import com.vaadin.ui.Component;
import com.vaadin.ui.TabSheet;

/**
 * @author MyCollab Ltd
 * @since 2.0
 */
@ViewComponent
public class FinanceContainer extends AbstractPageView implements IFinanceContainer {
    private static final long serialVersionUID = 1L;

    private TimeTrackingListPresenter timeTrackingListPresenter;
    private InvoicePresenter invoicePresenter;

    private TabSheetDecorator myProjectTab;

    public FinanceContainer() {
        this.setWidth("100%");
        removeAllComponents();
        this.myProjectTab = new TabSheetDecorator();
        this.addComponent(myProjectTab);
        this.buildComponents();
    }

    private void buildComponents() {
        if (CurrentProjectVariables.hasTimeFeature()) {
            timeTrackingListPresenter = PresenterResolver.getPresenter(TimeTrackingListPresenter.class);
            myProjectTab.addTab(timeTrackingListPresenter.getView(), AppContext.getMessage(ProjectCommonI18nEnum
                    .VIEW_TIME), ProjectAssetsManager.getAsset(ProjectTypeConstants.TIME));
        }

        if (CurrentProjectVariables.hasInvoiceFeature()) {
            invoicePresenter = PresenterResolver.getPresenter(InvoicePresenter.class);
            myProjectTab.addTab(invoicePresenter.getView(), AppContext.getMessage(ProjectCommonI18nEnum
                    .VIEW_INVOICE), ProjectAssetsManager.getAsset(ProjectTypeConstants.INVOICE));
        }

        myProjectTab.addSelectedTabChangeListener(new TabSheet.SelectedTabChangeListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void selectedTabChange(TabSheet.SelectedTabChangeEvent event) {
                TabSheet.Tab tab = ((TabSheetDecorator) event.getTabSheet()).getSelectedTabInfo();
                String caption = tab.getCaption();
                if (AppContext.getMessage(ProjectCommonI18nEnum.VIEW_TIME).equals(caption)) {
                    ItemTimeLoggingSearchCriteria searchCriteria = new ItemTimeLoggingSearchCriteria();
                    searchCriteria.setProjectIds(new SetSearchField<>(CurrentProjectVariables.getProjectId()));
                    searchCriteria.addExtraField(DateParam.inRangeDate(ItemTimeLoggingSearchCriteria.p_logDates,
                            VariableInjecter.THIS_WEEK));
                    timeTrackingListPresenter.onGo(FinanceContainer.this, new TimeTrackingScreenData.Search(searchCriteria));
                } else {
                    invoicePresenter.onGo(FinanceContainer.this, null);
                }
            }
        });
    }

    @Override
    public Component gotoSubView(String name) {
        return myProjectTab.selectTab(name).getComponent();
    }
}
