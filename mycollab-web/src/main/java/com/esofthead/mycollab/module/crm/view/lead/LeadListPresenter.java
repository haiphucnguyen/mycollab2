package com.esofthead.mycollab.module.crm.view.lead;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.common.localization.WebExceptionI18nEnum;
import com.esofthead.mycollab.core.persistence.service.ISearchableService;
import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.module.crm.domain.Lead;
import com.esofthead.mycollab.module.crm.domain.SimpleLead;
import com.esofthead.mycollab.module.crm.domain.criteria.LeadSearchCriteria;
import com.esofthead.mycollab.module.crm.localization.CrmCommonI18nEnum;
import com.esofthead.mycollab.module.crm.service.LeadService;
import com.esofthead.mycollab.module.crm.view.CrmGenericListPresenter;
import com.esofthead.mycollab.module.crm.view.CrmToolbar;
import com.esofthead.mycollab.security.RolePermissionCollections;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.events.PopupActionHandler;
import com.esofthead.mycollab.vaadin.mvp.MassUpdateCommand;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.mvp.ViewManager;
import com.esofthead.mycollab.vaadin.ui.MailFormWindow;
import com.esofthead.mycollab.vaadin.ui.MessageConstants;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.ComponentContainer;

public class LeadListPresenter extends
		CrmGenericListPresenter<LeadListView, LeadSearchCriteria, SimpleLead>
		implements MassUpdateCommand<Lead> {

	private static final long serialVersionUID = 1L;
	private LeadService leadService;

	public LeadListPresenter() {
		super(LeadListView.class);
		leadService = ApplicationContextUtil.getSpringBean(LeadService.class);

		view.getPopupActionHandlers().addPopupActionHandler(
				new DefaultPopupActionHandler(this) {

					@Override
					protected void onSelectExtra(String id, String caption) {
						if (PopupActionHandler.MAIL_ACTION.equals(id)) {
							if (isSelectAll) {
								AppContext
										.getApplication()
										.getMainWindow()
										.showNotification(
												LocalizationHelper
														.getMessage(WebExceptionI18nEnum.NOT_SUPPORT_SENDING_EMAIL_TO_ALL_USERS));
							} else {
								List<String> lstMail = new ArrayList<String>();

								List<SimpleLead> tableData = view
										.getPagedBeanTable()
										.getCurrentDataList();
								for (SimpleLead item : tableData) {
									if (item.isSelected()) {
										lstMail.add(item.getLeadName() + " <"
												+ item.getEmail() + ">");
									}
								}

								view.getWidget().getWindow()
										.addWindow(new MailFormWindow(lstMail));
							}

						} else if (PopupActionHandler.MASS_UPDATE_ACTION
								.equals(id)) {
							MassUpdateLeadWindow massUpdateWindow = new MassUpdateLeadWindow(
									LocalizationHelper
											.getMessage(
													GenericI18Enum.MASS_UPDATE_WINDOW_TITLE,
													"Lead"),
									LeadListPresenter.this);
							view.getWindow().addWindow(massUpdateWindow);
						}

					}

					@Override
					protected String getReportTitle() {
						return "Lead List";
					}

					@Override
					protected Class getReportModelClassType() {
						return SimpleLead.class;
					}
				});
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		if (AppContext.canRead(RolePermissionCollections.CRM_LEAD)) {
			CrmToolbar crmToolbar = ViewManager.getView(CrmToolbar.class);
			crmToolbar.gotoItem(LocalizationHelper
					.getMessage(CrmCommonI18nEnum.TOOLBAR_LEADS_HEADER));

			super.onGo(container, data);
			doSearch((LeadSearchCriteria) data.getParams());

			AppContext.addFragment("crm/lead/list",
					LocalizationHelper.getMessage(
							GenericI18Enum.BROWSER_LIST_ITEMS_TITLE, "Lead"));
		} else {
			MessageConstants.showMessagePermissionAlert();
		}
	}

	@Override
	protected void deleteSelectedItems() {
		if (!isSelectAll) {
			Collection<SimpleLead> currentDataList = view.getPagedBeanTable()
					.getCurrentDataList();
			List<Integer> keyList = new ArrayList<Integer>();
			for (SimpleLead item : currentDataList) {
				if (item.isSelected()) {
					keyList.add(item.getId());
				}
			}

			if (keyList.size() > 0) {
				leadService.massRemoveWithSession(keyList,
						AppContext.getUsername(), AppContext.getAccountId());
				doSearch(searchCriteria);
				checkWhetherEnableTableActionControl();
			}
		} else {
			leadService.removeByCriteria(searchCriteria,
					AppContext.getAccountId());
			doSearch(searchCriteria);
		}
	}

	@Override
	public void massUpdate(Lead value) {
		if (!isSelectAll) {
			Collection<SimpleLead> currentDataList = view.getPagedBeanTable()
					.getCurrentDataList();
			List<Integer> keyList = new ArrayList<Integer>();
			for (SimpleLead item : currentDataList) {
				if (item.isSelected()) {
					keyList.add(item.getId());
				}
			}

			if (keyList.size() > 0) {
				leadService.massUpdateWithSession(value, keyList,
						AppContext.getAccountId());
				doSearch(searchCriteria);
			}
		} else {
			leadService.updateBySearchCriteria(value, searchCriteria);
			doSearch(searchCriteria);
		}
	}

	@Override
	public ISearchableService<LeadSearchCriteria> getSearchService() {
		return ApplicationContextUtil.getSpringBean(LeadService.class);
	}
}
