package com.esofthead.mycollab.module.crm.view.campaign;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.core.persistence.service.ISearchableService;
import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.module.crm.domain.CampaignWithBLOBs;
import com.esofthead.mycollab.module.crm.domain.SimpleCampaign;
import com.esofthead.mycollab.module.crm.domain.criteria.CampaignSearchCriteria;
import com.esofthead.mycollab.module.crm.localization.CrmCommonI18nEnum;
import com.esofthead.mycollab.module.crm.service.CampaignService;
import com.esofthead.mycollab.module.crm.view.CrmGenericListPresenter;
import com.esofthead.mycollab.module.crm.view.CrmToolbar;
import com.esofthead.mycollab.module.user.RolePermissionCollections;
import com.esofthead.mycollab.vaadin.mvp.MassUpdatePresenter;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.mvp.ViewManager;
import com.esofthead.mycollab.vaadin.ui.MailFormWindow;
import com.esofthead.mycollab.vaadin.ui.MessageConstants;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.ComponentContainer;

public class CampaignListPresenter
		extends
		CrmGenericListPresenter<CampaignListView, CampaignSearchCriteria, SimpleCampaign>
		implements MassUpdatePresenter<CampaignWithBLOBs> {

	private static final long serialVersionUID = 1L;
	private CampaignService campaignService;

	public CampaignListPresenter() {
		super(CampaignListView.class);
		campaignService = AppContext.getSpringBean(CampaignService.class);

		view.getPopupActionHandlers().addPopupActionHandler(
				new DefaultPopupActionHandler(this) {

					@Override
					protected void onSelectExtra(String id, String caption) {
						if ("mail".equals(id)) {
							view.getWidget().getWindow()
									.addWindow(new MailFormWindow());
						} else if ("massUpdate".equals(id)) {
							MassUpdateCampaignWindow massUpdateWindow = new MassUpdateCampaignWindow(
									LocalizationHelper
											.getMessage(
													GenericI18Enum.MASS_UPDATE_WINDOW_TITLE,
													"Campaign"),
									CampaignListPresenter.this);
							view.getWindow().addWindow(massUpdateWindow);
						}

					}

					@Override
					protected String getReportTitle() {
						return "Campaign List";
					}

					@Override
					protected Class getReportModelClassType() {
						return SimpleCampaign.class;
					}
				});
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		if (AppContext.canRead(RolePermissionCollections.CRM_CAMPAIGN)) {
			CrmToolbar crmToolbar = ViewManager.getView(CrmToolbar.class);
			crmToolbar.gotoItem(LocalizationHelper
					.getMessage(CrmCommonI18nEnum.TOOLBAR_CAMPAIGNS_HEADER));

			super.onGo(container, data);
			doSearch((CampaignSearchCriteria) data.getParams());

			AppContext.addFragment("crm/campaign/list", LocalizationHelper
					.getMessage(GenericI18Enum.BROWSER_LIST_ITEMS_TITLE,
							"Campaign"));
		} else {
			MessageConstants.showMessagePermissionAlert();
		}
	}

	@Override
	protected void deleteSelectedItems() {
		if (!isSelectAll) {
			Collection<SimpleCampaign> currentDataList = view
					.getPagedBeanTable().getCurrentDataList();
			List<Integer> keyList = new ArrayList<Integer>();
			for (SimpleCampaign item : currentDataList) {
				if (item.isSelected()) {
					keyList.add(item.getId());
				}
			}

			if (keyList.size() > 0) {
				campaignService.massRemoveWithSession(keyList,
						AppContext.getUsername(), AppContext.getAccountId());
				doSearch(searchCriteria);
				checkWhetherEnableTableActionControl();
			}
		} else {
			campaignService.removeByCriteria(searchCriteria,
					AppContext.getAccountId());
			doSearch(searchCriteria);
		}
	}

	@Override
	public void massUpdate(CampaignWithBLOBs value) {
		if (!isSelectAll) {
			Collection<SimpleCampaign> currentDataList = view
					.getPagedBeanTable().getCurrentDataList();
			List<Integer> keyList = new ArrayList<Integer>();
			for (SimpleCampaign item : currentDataList) {
				if (item.isSelected()) {
					keyList.add(item.getId());
				}
			}
			if (keyList.size() > 0) {
				campaignService.massUpdateWithSession(value, keyList,
						AppContext.getAccountId());
				doSearch(searchCriteria);
			}
		} else {
			campaignService.updateBySearchCriteria(value, searchCriteria);
			doSearch(searchCriteria);
		}
	}

	@Override
	public ISearchableService<CampaignSearchCriteria> getSearchService() {
		return AppContext.getSpringBean(CampaignService.class);
	}
}
