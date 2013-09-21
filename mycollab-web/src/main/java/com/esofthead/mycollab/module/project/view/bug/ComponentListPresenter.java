/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.bug;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.esofthead.mycollab.core.persistence.service.ISearchableService;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.view.ProjectBreadcrumb;
import com.esofthead.mycollab.module.tracker.domain.SimpleComponent;
import com.esofthead.mycollab.module.tracker.domain.criteria.ComponentSearchCriteria;
import com.esofthead.mycollab.module.tracker.service.ComponentService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.events.PopupActionHandler;
import com.esofthead.mycollab.vaadin.mvp.ListSelectionPresenter;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.mvp.ViewManager;
import com.esofthead.mycollab.vaadin.ui.MailFormWindow;
import com.esofthead.mycollab.vaadin.ui.MessageConstants;
import com.esofthead.mycollab.web.AppContext;

/**
 * 
 * @author haiphucnguyen
 */
public class ComponentListPresenter
		extends
		ListSelectionPresenter<ComponentListView, ComponentSearchCriteria, SimpleComponent> {

	private static final long serialVersionUID = 1L;
	private ComponentService componentService;

	public ComponentListPresenter() {
		super(ComponentListView.class);

		componentService = ApplicationContextUtil.getSpringBean(ComponentService.class);

		view.getPopupActionHandlers().addPopupActionHandler(
				new DefaultPopupActionHandler(this) {

					@Override
					protected void onSelectExtra(String id, String caption) {
						if (PopupActionHandler.MAIL_ACTION.equals(id)) {
							view.getWidget().getWindow()
									.addWindow(new MailFormWindow());
						}

					}

					@Override
					protected String getReportTitle() {
						return "Component List";
					}

					@Override
					protected Class getReportModelClassType() {
						return SimpleComponent.class;
					}
				});
	}

	@Override
	protected void onGo(com.vaadin.ui.ComponentContainer container,
			ScreenData<?> data) {
		if (CurrentProjectVariables
				.canRead(ProjectRolePermissionCollections.COMPONENTS)) {
			ComponentContainer trackerContainer = (ComponentContainer) container;
			trackerContainer.removeAllComponents();
			trackerContainer.addComponent(view.getWidget());

			doSearch((ComponentSearchCriteria) data.getParams());

			ProjectBreadcrumb breadcrumb = ViewManager
					.getView(ProjectBreadcrumb.class);
			breadcrumb.gotoComponentList();
		} else {
			MessageConstants.showMessagePermissionAlert();
		}
	}

	@Override
	protected void deleteSelectedItems() {
		if (!isSelectAll) {
			Collection<SimpleComponent> currentDataList = view
					.getPagedBeanTable().getCurrentDataList();
			List<Integer> keyList = new ArrayList<Integer>();
			for (SimpleComponent item : currentDataList) {
				if (item.isSelected()) {
					keyList.add(item.getId());
				}
			}

			if (keyList.size() > 0) {
				componentService.massRemoveWithSession(keyList,
						AppContext.getUsername(), AppContext.getAccountId());
				doSearch(searchCriteria);
				checkWhetherEnableTableActionControl();
			}
		} else {
			componentService.removeByCriteria(searchCriteria,
					AppContext.getAccountId());
			doSearch(searchCriteria);
		}

	}

	@Override
	public ISearchableService<ComponentSearchCriteria> getSearchService() {
		return ApplicationContextUtil.getSpringBean(ComponentService.class);
	}
}
