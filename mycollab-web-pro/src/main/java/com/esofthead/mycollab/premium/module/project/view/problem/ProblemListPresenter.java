package com.esofthead.mycollab.premium.module.project.view.problem;

import com.esofthead.mycollab.core.persistence.service.ISearchableService;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.domain.Problem;
import com.esofthead.mycollab.module.project.domain.SimpleProblem;
import com.esofthead.mycollab.module.project.domain.criteria.ProblemSearchCriteria;
import com.esofthead.mycollab.module.project.service.ProblemService;
import com.esofthead.mycollab.module.project.view.ProjectBreadcrumb;
import com.esofthead.mycollab.module.project.view.ProjectGenericListPresenter;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.desktop.ui.DefaultMassEditActionHandler;
import com.esofthead.mycollab.vaadin.events.ViewItemAction;
import com.esofthead.mycollab.vaadin.mvp.*;
import com.esofthead.mycollab.vaadin.ui.MailFormWindow;
import com.esofthead.mycollab.vaadin.ui.NotificationUtil;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.UI;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
@LoadPolicy(scope = ViewScope.PROTOTYPE)
public class ProblemListPresenter extends ProjectGenericListPresenter<ProblemListView, ProblemSearchCriteria, SimpleProblem>
        implements MassUpdateCommand<Problem> {
    private static final long serialVersionUID = 1L;

    private ProblemService problemService;

    public ProblemListPresenter() {
        super(ProblemListView.class, ProblemListNoItemView.class);

        problemService = ApplicationContextUtil.getSpringBean(ProblemService.class);
    }

    @Override
    protected void postInitView() {
        super.postInitView();

        view.getPopupActionHandlers().setMassActionHandler(new DefaultMassEditActionHandler(this) {

            @Override
            protected void onSelectExtra(String id) {
                if (ViewItemAction.MAIL_ACTION().equals(id)) {
                    UI.getCurrent().addWindow(new MailFormWindow());
                } else if (ViewItemAction.MASS_UPDATE_ACTION().equals(id)) {
                    MassUpdateProblemWindow massUpdateWindow = new MassUpdateProblemWindow(
                            "Mass Update Problems", ProblemListPresenter.this);
                    UI.getCurrent().addWindow(massUpdateWindow);
                }

            }

            @Override
            protected String getReportTitle() {
                return "Problems";
            }

            @Override
            protected Class<?> getReportModelClassType() {
                return SimpleProblem.class;
            }
        });
    }

    @Override
    protected void onGo(ComponentContainer container, ScreenData<?> data) {
        if (CurrentProjectVariables.canRead(ProjectRolePermissionCollections.PROBLEMS)) {
            ProblemContainer problemContainer = (ProblemContainer) container;
            problemContainer.removeAllComponents();
            problemContainer.addComponent(view.getWidget());

            searchCriteria = (ProblemSearchCriteria) data.getParams();
            int totalCount = problemService.getTotalCount(searchCriteria);

            if (totalCount > 0) {
                doSearch(searchCriteria);
                displayListView(container, data);
            } else {
                displayNoExistItems(container, data);
            }

            ProjectBreadcrumb breadcrumb = ViewManager.getCacheComponent(ProjectBreadcrumb.class);
            breadcrumb.gotoProblemList();
        } else {
            NotificationUtil.showMessagePermissionAlert();
        }
    }

    @Override
    protected void deleteSelectedItems() {
        if (!isSelectAll) {
            Collection<SimpleProblem> currentDataList = view.getPagedBeanTable().getCurrentDataList();
            List<Problem> keyList = new ArrayList<>();
            for (SimpleProblem item : currentDataList) {
                if (item.isSelected()) {
                    keyList.add(item);
                }
            }

            if (keyList.size() > 0) {
                problemService.massRemoveWithSession(keyList, AppContext.getUsername(), AppContext.getAccountId());
            }
        } else {
            problemService.removeByCriteria(searchCriteria, AppContext.getAccountId());
        }

        int totalCount = problemService.getTotalCount(searchCriteria);
        if (totalCount > 0) {
            doSearch(searchCriteria);
            displayListView((ComponentContainer) view.getParent(), null);
        } else {
            displayNoExistItems((ComponentContainer) view.getParent(), null);
        }

    }

    @Override
    public void massUpdate(Problem value) {
        if (!isSelectAll) {
            Collection<SimpleProblem> currentDataList = view.getPagedBeanTable().getCurrentDataList();
            List<Integer> keyList = new ArrayList<>();
            for (SimpleProblem item : currentDataList) {
                if (item.isSelected()) {
                    keyList.add(item.getId());
                }
            }

            if (keyList.size() > 0) {
                problemService.massUpdateWithSession(value, keyList, AppContext.getAccountId());
                doSearch(searchCriteria);
            }
        } else {
            problemService.updateBySearchCriteria(value, searchCriteria);
            doSearch(searchCriteria);
        }
    }

    @Override
    public ISearchableService<ProblemSearchCriteria> getSearchService() {
        return problemService;
    }
}
