package com.esofthead.mycollab.module.project.view.user;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;

import com.esofthead.mycollab.common.ActivityStreamConstants;
import com.esofthead.mycollab.common.domain.SimpleActivityStream;
import com.esofthead.mycollab.common.domain.criteria.ActivityStreamSearchCriteria;
import com.esofthead.mycollab.common.service.ActivityStreamService;
import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.module.project.ProjectResources;
import com.esofthead.mycollab.module.project.domain.ProjectActivityStream;
import com.esofthead.mycollab.module.project.localization.ProjectCommonI18nEnum;
import com.esofthead.mycollab.module.project.localization.ProjectLocalizationTypeMap;
import com.esofthead.mycollab.module.project.ui.components.ProjectActivityStreamGenerator;
import com.esofthead.mycollab.module.project.view.ProjectLinkBuilder;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.ui.AbstractBeanPagedList;
import com.esofthead.mycollab.vaadin.ui.UserAvatarControlFactory;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;

public class ProjectActivityStreamPagedList
		extends
		AbstractBeanPagedList<ActivityStreamSearchCriteria, ProjectActivityStream> {
	private static final long serialVersionUID = 1L;

	private final ActivityStreamService activityStreamService;

	public ProjectActivityStreamPagedList() {
		super(null, 20);
		this.activityStreamService = ApplicationContextUtil
				.getSpringBean(ActivityStreamService.class);

	}

	@Override
	public void doSearch() {
		this.totalCount = this.activityStreamService
				.getTotalCount(this.searchRequest.getSearchCriteria());
		this.totalPage = (this.totalCount - 1)
				/ this.searchRequest.getNumberOfItems() + 1;
		if (this.searchRequest.getCurrentPage() > this.totalPage) {
			this.searchRequest.setCurrentPage(this.totalPage);
		}

		if (totalPage > 1) {
			if (this.controlBarWrapper != null) {
				this.removeComponent(this.controlBarWrapper);
			}
			this.addComponent(this.createPageControls());
		} else {
			if (getComponentCount() == 2) {
				removeComponent(getComponent(1));
			}
		}

		final List<SimpleActivityStream> currentListData = this.activityStreamService
				.findPagableListByCriteria(this.searchRequest);
		this.listContainer.removeAllComponents();

		Date currentDate = new GregorianCalendar(2100, 1, 1).getTime();

		try {
			for (final SimpleActivityStream activityStream : currentListData) {
				final Date itemCreatedDate = activityStream.getCreatedtime();

				if (!DateUtils.isSameDay(currentDate, itemCreatedDate)) {
					final CssLayout dateWrapper = new CssLayout();
					dateWrapper.setWidth("100%");
					dateWrapper.addStyleName("date-wrapper");
					dateWrapper.addComponent(new Label(AppContext
							.formatDate(itemCreatedDate)));
					this.listContainer.addComponent(dateWrapper);
					currentDate = itemCreatedDate;
				}
				String content = "";

				if (ActivityStreamConstants.ACTION_CREATE.equals(activityStream
						.getAction())) {
					content = LocalizationHelper
							.getMessage(
									ProjectCommonI18nEnum.FEED_USER_ACTIVITY_CREATE_ACTION_TITLE,
									UserAvatarControlFactory.getAvatarLink(
											activityStream
													.getCreatedUserAvatarId(),
											16),
									ProjectLinkBuilder
											.generateProjectMemberFullLink(
													activityStream
															.getExtratypeid(),
													activityStream
															.getCreateduser()),
									activityStream.getCreatedUserFullName(),
									LocalizationHelper
											.getMessage(ProjectLocalizationTypeMap
													.getType(activityStream
															.getType())),
									ProjectResources
											.getResourceLink(activityStream
													.getType()),
									ProjectLinkBuilder
											.generateProjectItemLink(
													activityStream
															.getExtratypeid(),
													activityStream.getType(),
													activityStream.getTypeid()),
									activityStream.getNamefield());
				} else if (ActivityStreamConstants.ACTION_UPDATE
						.equals(activityStream.getAction())) {
					content = LocalizationHelper
							.getMessage(
									ProjectCommonI18nEnum.FEED_USER_ACTIVITY_UPDATE_ACTION_TITLE,
									UserAvatarControlFactory.getAvatarLink(
											activityStream
													.getCreatedUserAvatarId(),
											16),
									activityStream.getCreatedUserFullName(),
									LocalizationHelper
											.getMessage(ProjectLocalizationTypeMap
													.getType(activityStream
															.getType())),
									ProjectResources
											.getResourceLink(activityStream
													.getType()),
									ProjectLinkBuilder
											.generateProjectItemLink(
													activityStream
															.getExtratypeid(),
													activityStream.getType(),
													activityStream.getTypeid()),
									activityStream.getNamefield());
					if (activityStream.getAssoAuditLog() != null) {
						content += ProjectActivityStreamGenerator
								.generatorDetailChangeOfActivity(activityStream);
					}
				}

				final Label actionLbl = new Label(content, Label.CONTENT_XHTML);
				final CssLayout streamWrapper = new CssLayout();
				streamWrapper.setWidth("100%");
				streamWrapper.addStyleName("stream-wrapper");
				streamWrapper.addComponent(actionLbl);
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
	protected List<ProjectActivityStream> queryCurrentData() {
		return null;
	}

}
