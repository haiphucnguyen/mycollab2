/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.crm.view;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;

import com.esofthead.mycollab.common.ActivityStreamConstants;
import com.esofthead.mycollab.common.ModuleNameConstants;
import com.esofthead.mycollab.common.domain.SimpleActivityStream;
import com.esofthead.mycollab.common.domain.criteria.ActivityStreamSearchCriteria;
import com.esofthead.mycollab.common.service.ActivityStreamService;
import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.module.crm.localization.CrmCommonI18nEnum;
import com.esofthead.mycollab.module.crm.localization.CrmLocalizationTypeMap;
import com.esofthead.mycollab.vaadin.ui.AbstractBeanPagedList;
import com.esofthead.mycollab.vaadin.ui.Depot;
import com.esofthead.mycollab.vaadin.ui.UserAvatarControlFactory;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.lazyloadwrapper.LazyLoadWrapper;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * @author haiphucnguyen
 */
public class ActivityStreamPanel extends Depot {
	private static final long serialVersionUID = 1L;

	private final CrmActivityStreamPagedList activityStreamList;

	public ActivityStreamPanel() {
		super("Activity Channels", new VerticalLayout(), "100%");
		this.activityStreamList = new CrmActivityStreamPagedList();

		this.activityStreamList.addStyleName("stream-list");
		this.bodyContent.addComponent(new LazyLoadWrapper(
				this.activityStreamList));
		this.addStyleName("activity-panel");
		((VerticalLayout) this.bodyContent).setMargin(false);
	}

	public void display() {
		final ActivityStreamSearchCriteria searchCriteria = new ActivityStreamSearchCriteria();
		searchCriteria.setModuleSet(new SetSearchField<String>(SearchField.AND,
				new String[] { ModuleNameConstants.CRM }));
		this.activityStreamList.setSearchCriteria(searchCriteria);
	}

	static class CrmActivityStreamPagedList
			extends
			AbstractBeanPagedList<ActivityStreamSearchCriteria, SimpleActivityStream> {
		private static final long serialVersionUID = 1L;

		private final ActivityStreamService activityStreamService;

		public CrmActivityStreamPagedList() {
			super(null, 20);
			this.activityStreamService = AppContext
					.getSpringBean(ActivityStreamService.class);

		}

		@Override
		protected void doSearch() {
			this.totalCount = this.activityStreamService
					.getTotalCount(this.searchRequest.getSearchCriteria());
			this.totalPage = (this.totalCount - 1)
					/ this.searchRequest.getNumberOfItems() + 1;
			if (this.searchRequest.getCurrentPage() > this.totalPage) {
				this.searchRequest.setCurrentPage(this.totalPage);
			}

			if (this.totalPage > 1) {
				// Define button layout
				if (this.controlBarWrapper != null) {
					this.removeComponent(this.controlBarWrapper);
				}
				this.addComponent(this.createPageControls());
			} else {
				if (this.getComponentCount() == 2) {
					this.removeComponent(this.getComponent(1));
				}
			}

			this.setCurrentPage(this.currentPage);
			this.setTotalPage(this.totalPage);

			final List<SimpleActivityStream> currentListData = this.activityStreamService
					.findPagableListByCriteria(this.searchRequest);
			this.listContainer.removeAllComponents();

			Date currentDate = new GregorianCalendar(2100, 1, 1).getTime();

			try {
				for (final SimpleActivityStream activityStream : currentListData) {
					final Date itemCreatedDate = activityStream
							.getCreatedtime();

					if (!DateUtils.isSameDay(currentDate, itemCreatedDate)) {
						final CssLayout dateWrapper = new CssLayout();
						dateWrapper.setWidth("100%");
						dateWrapper.addStyleName("date-wrapper");
						dateWrapper.addComponent(new Label(AppContext
								.formatDate(itemCreatedDate)));
						this.listContainer.addComponent(dateWrapper);
						currentDate = itemCreatedDate;
					}

					CrmCommonI18nEnum action = null;

					if (ActivityStreamConstants.ACTION_CREATE
							.equals(activityStream.getAction())) {
						action = CrmCommonI18nEnum.WIDGET_ACTIVITY_CREATE_ACTION;
					} else if (ActivityStreamConstants.ACTION_UPDATE
							.equals(activityStream.getAction())) {
						action = CrmCommonI18nEnum.WIDGET_ACTIVITY_UPDATE_ACTION;
					}

					String content = LocalizationHelper
							.getMessage(action, UserAvatarControlFactory
									.getAvatarLink(activityStream
											.getCreatedUserAvatarId(), 16),
									activityStream.getCreatedUserFullName(),
									LocalizationHelper
											.getMessage(CrmLocalizationTypeMap
													.getType(activityStream
															.getType())),
									CrmResources.getResourceLink(activityStream
											.getType()),
									CrmLinkGenerator.generateCrmItemLink(
											activityStream.getType(),
											activityStream.getTypeid()),
									activityStream.getNamefield());
					if (activityStream.getAssoAuditLog() != null) {
						content += CrmActivityStreamGenerator
								.generatorDetailChangeOfActivity(activityStream);
					}

					final Label activityLink = new Label(content,
							Label.CONTENT_XHTML);
					final CssLayout streamWrapper = new CssLayout();
					streamWrapper.setWidth("100%");
					streamWrapper.addStyleName("stream-wrapper");
					streamWrapper.addComponent(activityLink);
					this.listContainer.addComponent(streamWrapper);
				}
			} catch (final Exception e) {
				throw new MyCollabException(e);
			}
		}

		@Override
		protected int queryTotalCount() {
			return 0;
		}

		@Override
		protected List<SimpleActivityStream> queryCurrentData() {
			return null;
		}
	}
}
