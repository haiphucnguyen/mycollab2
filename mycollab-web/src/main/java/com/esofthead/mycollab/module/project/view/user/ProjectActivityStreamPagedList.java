/**
 * This file is part of mycollab-web.
 *
 * mycollab-web is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-web is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-web.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.module.project.view.user;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esofthead.mycollab.common.ActivityStreamConstants;
import com.esofthead.mycollab.common.domain.SimpleActivityStream;
import com.esofthead.mycollab.common.domain.criteria.ActivityStreamSearchCriteria;
import com.esofthead.mycollab.common.service.ActivityStreamService;
import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.module.project.ProjectResources;
import com.esofthead.mycollab.module.project.domain.ProjectActivityStream;
import com.esofthead.mycollab.module.project.localization.ProjectCommonI18nEnum;
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

	private static Logger log = LoggerFactory
			.getLogger(ProjectActivityStreamPagedList.class);

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

				String dateTimeTypeIdStr = AppContext
						.formatDateTime(itemCreatedDate).replace("/", "")
						.trim().replace(" ", "").replace(":", "")
						+ activityStream.getTypeid();
				String idDivSeverData = "serverdata" + dateTimeTypeIdStr + "";
				String idToopTipDiv = "tooltip" + dateTimeTypeIdStr + "";
				String idStickyToolTipDiv = "mystickyTooltip"
						+ dateTimeTypeIdStr;
				String idtagA = "tagA" + dateTimeTypeIdStr;

				if (ActivityStreamConstants.ACTION_CREATE.equals(activityStream
						.getAction())) {
					String arg0 = UserAvatarControlFactory.getAvatarLink(
							activityStream.getCreatedUserAvatarId(), 16);
					String arg1 = ProjectLinkBuilder
							.generateProjectMemberFullLink(
									activityStream.getExtratypeid(),
									activityStream.getCreateduser());
					String arg2 = activityStream.getCreatedUserFullName();

					String arg3 = ProjectResources
							.getResourceLink(activityStream.getType());
					String arg4 = idtagA;
					String arg5 = ProjectLinkBuilder.generateProjectItemLink(
							activityStream.getExtratypeid(),
							activityStream.getType(),
							activityStream.getTypeid());
					String arg6 = "'" + dateTimeTypeIdStr + "'";
					String arg7 = "'" + activityStream.getType() + "'";
					String arg8 = "'" + activityStream.getTypeid() + "'";
					String arg9 = "'" + AppContext.getSiteUrl() + "tooltip/'";
					String arg10 = "'" + activityStream.getSaccountid() + "'";
					String arg11 = "'" + AppContext.getSiteUrl() + "'";
					String arg12 = activityStream.getNamefield();
					String arg13 = idStickyToolTipDiv;
					String arg14 = idToopTipDiv;
					String arg15 = idDivSeverData;
					content = LocalizationHelper
							.getMessage(
									ProjectCommonI18nEnum.FEED_USER_ACTIVITY_CREATE_ACTION_TITLE,
									arg0, arg1, arg2, arg3, arg4, arg5, arg6,
									arg7, arg8, arg9, arg10, arg11, arg12,
									arg13, arg14, arg15);
				} else if (ActivityStreamConstants.ACTION_UPDATE
						.equals(activityStream.getAction())) {
					// tooltip id is = tooltip + dateTime + typeId
					// serverData id is = serverdata + dateTime + typeId

					String arg0 = UserAvatarControlFactory.getAvatarLink(
							activityStream.getCreatedUserAvatarId(), 16);
					String arg1 = ProjectLinkBuilder
							.generateProjectMemberFullLink(
									activityStream.getExtratypeid(),
									activityStream.getCreateduser());
					String arg2 = activityStream.getCreatedUserFullName();

					String arg3 = ProjectResources
							.getResourceLink(activityStream.getType());
					String arg4 = idtagA;
					String arg5 = ProjectLinkBuilder.generateProjectItemLink(
							activityStream.getExtratypeid(),
							activityStream.getType(),
							activityStream.getTypeid());
					String arg6 = "'" + dateTimeTypeIdStr + "'";
					String arg7 = "'" + activityStream.getType() + "'";
					String arg8 = "'" + activityStream.getTypeid() + "'";
					String arg9 = "'" + AppContext.getSiteUrl() + "tooltip/'";
					String arg10 = "'" + activityStream.getSaccountid() + "'";
					String arg11 = "'" + AppContext.getSiteUrl() + "'";
					String arg12 = activityStream.getNamefield();
					String arg13 = idStickyToolTipDiv;
					String arg14 = idToopTipDiv;
					String arg15 = idDivSeverData;
					content = LocalizationHelper
							.getMessage(
									ProjectCommonI18nEnum.FEED_USER_ACTIVITY_UPDATE_ACTION_TITLE,
									arg0, arg1, arg2, arg3, arg4, arg5, arg6,
									arg7, arg8, arg9, arg10, arg11, arg12,
									arg13, arg14, arg15);
					log.debug("CONTENT: " + content);
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
