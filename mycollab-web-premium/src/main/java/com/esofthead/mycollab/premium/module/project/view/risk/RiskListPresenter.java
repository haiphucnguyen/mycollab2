package com.esofthead.mycollab.premium.module.project.view.risk;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.esofthead.mycollab.core.persistence.service.ISearchableService;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.domain.Risk;
import com.esofthead.mycollab.module.project.domain.SimpleRisk;
import com.esofthead.mycollab.module.project.domain.criteria.RiskSearchCriteria;
import com.esofthead.mycollab.module.project.service.RiskService;
import com.esofthead.mycollab.module.project.view.ProjectBreadcrumb;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.events.PopupActionHandler;
import com.esofthead.mycollab.vaadin.mvp.ListCommand;
import com.esofthead.mycollab.vaadin.mvp.ListSelectionPresenter;
import com.esofthead.mycollab.vaadin.mvp.MassUpdateCommand;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.mvp.ViewManager;
import com.esofthead.mycollab.vaadin.ui.MailFormWindow;
import com.esofthead.mycollab.vaadin.ui.MessageBox;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.ComponentContainer;

public class RiskListPresenter extends
		ListSelectionPresenter<RiskListView, RiskSearchCriteria, SimpleRisk>
		implements ListCommand<RiskSearchCriteria>, MassUpdateCommand<Risk> {

	private static final long serialVersionUID = 1L;
	private RiskService riskService;

	public RiskListPresenter() {
		super(RiskListView.class);

		riskService = ApplicationContextUtil.getSpringBean(RiskService.class);

		view.getPopupActionHandlers().addPopupActionHandler(
				new DefaultPopupActionHandler(this) {

					@Override
					protected void onSelectExtra(String id, String caption) {
						if (PopupActionHandler.MAIL_ACTION.equals(id)) {
							view.getWidget().getWindow()
									.addWindow(new MailFormWindow());

						} else if (PopupActionHandler.MASS_UPDATE_ACTION
								.equals(id)) {
							MassUpdateRiskWindow massUpdateWindow = new MassUpdateRiskWindow(
									"Mass Update Risk", RiskListPresenter.this);
							view.getWindow().addWindow(massUpdateWindow);
						}

					}

					@Override
					protected String getReportTitle() {
						return "Risk List";
					}

					@Override
					protected Class getReportModelClassType() {
						return SimpleRisk.class;
					}
				});
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		if (CurrentProjectVariables
				.canRead(ProjectRolePermissionCollections.RISKS)) {
			RiskContainer riskContainer = (RiskContainer) container;
			riskContainer.removeAllComponents();
			riskContainer.addComponent(view.getWidget());
			doSearch((RiskSearchCriteria) data.getParams());

			ProjectBreadcrumb breadCrumb = ViewManager
					.getView(ProjectBreadcrumb.class);
			breadCrumb.gotoRiskList();
		} else {
			MessageBox.showMessagePermissionAlert();
		}
	}

	@Override
	protected void deleteSelectedItems() {
		if (!isSelectAll) {
			Collection<SimpleRisk> currentDataList = view.getPagedBeanTable()
					.getCurrentDataList();
			List<Integer> keyList = new ArrayList<Integer>();
			for (SimpleRisk item : currentDataList) {
				if (item.isSelected()) {
					keyList.add(item.getId());
				}
			}

			if (keyList.size() > 0) {
				riskService.massRemoveWithSession(keyList,
						AppContext.getUsername(), AppContext.getAccountId());
				doSearch(searchCriteria);
				checkWhetherEnableTableActionControl();
			}
		} else {
			riskService.removeByCriteria(searchCriteria,
					AppContext.getAccountId());
			doSearch(searchCriteria);
		}

	}

	@Override
	public void massUpdate(Risk value) {
		if (!isSelectAll) {
			Collection<SimpleRisk> currentDataList = view.getPagedBeanTable()
					.getCurrentDataList();
			List<Integer> keyList = new ArrayList<Integer>();
			for (SimpleRisk item : currentDataList) {
				if (item.isSelected()) {
					keyList.add(item.getId());
				}
			}

			if (keyList.size() > 0) {
				riskService.massUpdateWithSession(value, keyList,
						AppContext.getAccountId());
				doSearch(searchCriteria);
			}
		} else {
			riskService.updateBySearchCriteria(value, searchCriteria);
			doSearch(searchCriteria);
		}
	}

	@Override
	public ISearchableService<RiskSearchCriteria> getSearchService() {
		return ApplicationContextUtil.getSpringBean(RiskService.class);
	}
}
