package com.esofthead.mycollab.module.project.view.problem;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.esofthead.mycollab.core.persistence.service.ISearchableService;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.domain.Problem;
import com.esofthead.mycollab.module.project.domain.SimpleProblem;
import com.esofthead.mycollab.module.project.domain.criteria.ProblemSearchCriteria;
import com.esofthead.mycollab.module.project.service.ProblemService;
import com.esofthead.mycollab.module.project.view.ProjectBreadcrumb;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.events.PopupActionHandler;
import com.esofthead.mycollab.vaadin.mvp.ListSelectionPresenter;
import com.esofthead.mycollab.vaadin.mvp.MassUpdatePresenter;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.mvp.ViewManager;
import com.esofthead.mycollab.vaadin.ui.MailFormWindow;
import com.esofthead.mycollab.vaadin.ui.MessageConstants;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.ComponentContainer;

public class ProblemListPresenter
		extends
		ListSelectionPresenter<ProblemListView, ProblemSearchCriteria, SimpleProblem>
		implements MassUpdatePresenter<Problem> {

	private static final long serialVersionUID = 1L;
	private ProblemService problemService;

	public ProblemListPresenter() {
		super(ProblemListView.class);

		problemService = ApplicationContextUtil.getSpringBean(ProblemService.class);

		view.getPopupActionHandlers().addPopupActionHandler(
				new DefaultPopupActionHandler(this) {

					@Override
					protected void onSelectExtra(String id, String caption) {
						if (PopupActionHandler.MAIL_ACTION.equals(id)) {
							view.getWidget().getWindow()
									.addWindow(new MailFormWindow());
						} else if (PopupActionHandler.MASS_UPDATE_ACTION
								.equals(id)) {
							MassUpdateProblemWindow massUpdateWindow = new MassUpdateProblemWindow(
									"Mass Update Problems",
									ProblemListPresenter.this);
							view.getWindow().addWindow(massUpdateWindow);
						}

					}

					@Override
					protected String getReportTitle() {
						return "Problem List";
					}

					@Override
					protected Class getReportModelClassType() {
						return SimpleProblem.class;
					}
				});
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		if (CurrentProjectVariables
				.canRead(ProjectRolePermissionCollections.PROBLEMS)) {
			ProblemContainer problemContainer = (ProblemContainer) container;
			problemContainer.removeAllComponents();
			problemContainer.addComponent(view.getWidget());

			doSearch((ProblemSearchCriteria) data.getParams());

			ProjectBreadcrumb breadcrumb = ViewManager
					.getView(ProjectBreadcrumb.class);
			breadcrumb.gotoProblemList();
		} else {
			MessageConstants.showMessagePermissionAlert();
		}
	}

	@Override
	protected void deleteSelectedItems() {
		if (!isSelectAll) {
			Collection<SimpleProblem> currentDataList = view
					.getPagedBeanTable().getCurrentDataList();
			List<Integer> keyList = new ArrayList<Integer>();
			for (SimpleProblem item : currentDataList) {
				if (item.isSelected()) {
					keyList.add(item.getId());
				}
			}

			if (keyList.size() > 0) {
				problemService.massRemoveWithSession(keyList,
						AppContext.getUsername(), AppContext.getAccountId());
				doSearch(searchCriteria);
				checkWhetherEnableTableActionControl();
			}
		} else {
			problemService.removeByCriteria(searchCriteria,
					AppContext.getAccountId());
			doSearch(searchCriteria);
		}

	}

	@Override
	public void massUpdate(Problem value) {
		if (!isSelectAll) {
			Collection<SimpleProblem> currentDataList = view
					.getPagedBeanTable().getCurrentDataList();
			List<Integer> keyList = new ArrayList<Integer>();
			for (SimpleProblem item : currentDataList) {
				if (item.isSelected()) {
					keyList.add(item.getId());
				}
			}

			if (keyList.size() > 0) {
				problemService.massUpdateWithSession(value, keyList,
						AppContext.getAccountId());
				doSearch(searchCriteria);
			}
		} else {
			problemService.updateBySearchCriteria(value, searchCriteria);
			doSearch(searchCriteria);
		}
	}

	@Override
	public ISearchableService<ProblemSearchCriteria> getSearchService() {
		return ApplicationContextUtil.getSpringBean(ProblemService.class);
	}
}
