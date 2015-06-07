/**
 * This file is part of mycollab-mobile.
 *
 * mycollab-mobile is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-mobile is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-mobile.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.mobile.module.project.view;

import com.esofthead.mycollab.common.ActivityStreamConstants;
import com.esofthead.mycollab.common.domain.criteria.ActivityStreamSearchCriteria;
import com.esofthead.mycollab.eventmanager.EventBusFactory;
import com.esofthead.mycollab.mobile.module.project.events.ProjectEvent;
import com.esofthead.mycollab.mobile.module.project.ui.AbstractListViewComp;
import com.esofthead.mycollab.mobile.module.project.view.parameters.ProjectMemberScreenData;
import com.esofthead.mycollab.mobile.module.project.view.parameters.ProjectScreenData;
import com.esofthead.mycollab.mobile.ui.AbstractPagedBeanList;
import com.esofthead.mycollab.mobile.ui.AbstractPagedBeanList.RowDisplayHandler;
import com.esofthead.mycollab.module.project.ProjectLinkBuilder;
import com.esofthead.mycollab.module.project.ProjectTypeConstants;
import com.esofthead.mycollab.module.project.domain.ProjectActivityStream;
import com.esofthead.mycollab.module.project.i18n.ProjectCommonI18nEnum;
import com.esofthead.mycollab.module.project.ui.ProjectAssetsManager;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.mvp.PageActionChain;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.hp.gagawa.java.elements.A;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import org.vaadin.maddon.layouts.MHorizontalLayout;

/**
 * @author MyCollab Ltd.
 *
 * @since 4.5.2
 */

@ViewComponent
public class AllActivityViewImpl extends
		AbstractListViewComp<ActivityStreamSearchCriteria, ProjectActivityStream> implements AllActivityView {
	private static final long serialVersionUID = -7722214412998470562L;

	public AllActivityViewImpl() {
		this.setCaption(AppContext.getMessage(ProjectCommonI18nEnum.M_VIEW_PROJECT_ACTIVITIES));
		this.addStyleName("project-activities-view");
	}

	@Override
	protected AbstractPagedBeanList<ActivityStreamSearchCriteria, ProjectActivityStream> createBeanTable() {
		ProjectActivityStreamListDisplay beanList = new ProjectActivityStreamListDisplay();
		beanList.setRowDisplayHandler(new ActivityStreamRowHandler());
		return beanList;
	}

	@Override
	protected Component createRightComponent() {
		return null;
	}

	private static class ActivityStreamRowHandler implements RowDisplayHandler<ProjectActivityStream> {

		@Override
		public Component generateRow(final ProjectActivityStream streamData, int rowIndex) {
			MHorizontalLayout layout = new MHorizontalLayout().withWidth("100%").withStyleName("list-item");
			layout.addStyleName("activity-row");

			Label typeIcon = new Label("<span aria-hidden=\"true\" data-icon=\""
							+ ProjectAssetsManager.toHexString(streamData.getType())
							+ "\"></span>");
			typeIcon.setWidthUndefined();
			typeIcon.setContentMode(ContentMode.HTML);
			typeIcon.setStyleName("activity-type");

			layout.addComponent(typeIcon);

			VerticalLayout rightCol = new VerticalLayout();
			rightCol.setWidth("100%");
			Label streamItem = new Label(generateItemLink(streamData));
			streamItem.setStyleName("activity-item");
			streamItem.setContentMode(ContentMode.HTML);
			rightCol.addComponent(streamItem);

			CssLayout detailRow1 = new CssLayout();
			detailRow1.setWidth("100%");
			detailRow1.setStyleName("activity-detail-row");

			Label streamDetail = new Label();
			streamDetail.setWidthUndefined();
			streamDetail.setStyleName("activity-detail");
			if (ActivityStreamConstants.ACTION_CREATE.equals(streamData.getAction())) {
				streamDetail.setValue(AppContext
								.getMessage(ProjectCommonI18nEnum.M_FEED_USER_ACTIVITY_CREATE_ACTION_TITLE));
			} else if (ActivityStreamConstants.ACTION_UPDATE.equals(streamData.getAction())) {
				streamDetail.setValue(AppContext
								.getMessage(ProjectCommonI18nEnum.M_FEED_USER_ACTIVITY_UPDATE_ACTION_TITLE));
			}
			detailRow1.addComponent(streamDetail);
			Button activityUser = new Button(streamData.getCreatedUserFullName(),
					new Button.ClickListener() {

						private static final long serialVersionUID = -8003871011601870233L;

						@Override
						public void buttonClick(Button.ClickEvent event) {
							PageActionChain chain = new PageActionChain(
									new ProjectScreenData.Goto(streamData.getProjectId()),
									new ProjectMemberScreenData.Read(streamData.getCreateduser()));
							EventBusFactory.getInstance()
									.post(new ProjectEvent.GotoMyProject(this,
											chain));
						}
					});
			activityUser.setStyleName("link");
			detailRow1.addComponent(activityUser);
			rightCol.addComponent(detailRow1);

			if (!ProjectTypeConstants.PROJECT.equals(streamData.getType())) {
				CssLayout detailRow2 = new CssLayout();
				detailRow2.setWidth("100%");
				detailRow2.setStyleName("activity-detail-row");
				Label prefixLbl = new Label(
						AppContext
								.getMessage(ProjectCommonI18nEnum.M_FEED_PROJECT_ACTIVITY_PREFIX));
				prefixLbl.setWidthUndefined();
				prefixLbl.setStyleName("activity-detail");
				detailRow2.addComponent(prefixLbl);
				Button activityProject = new Button(streamData.getProjectName(),
						new Button.ClickListener() {

							private static final long serialVersionUID = -3098780059559395224L;

							@Override
							public void buttonClick(Button.ClickEvent event) {
								PageActionChain chain = new PageActionChain(
										new ProjectScreenData.Goto(streamData.getProjectId()));
								EventBusFactory.getInstance().post(
										new ProjectEvent.GotoMyProject(this, chain));
							}
						});
				activityProject.setStyleName("link");
				detailRow2.addComponent(activityProject);
				rightCol.addComponent(detailRow2);
			}

			layout.with(rightCol).expand(rightCol);
			return layout;
		}

	}

	private static String generateItemLink(ProjectActivityStream stream) {
		A itemLink = new A();
		if (ProjectTypeConstants.TASK.equals(stream.getType())
				|| ProjectTypeConstants.BUG.equals(stream.getType())) {
			itemLink.setHref(ProjectLinkBuilder.generateProjectItemLink(
					stream.getProjectShortName(), stream.getExtratypeid(),
					stream.getType(), stream.getItemKey() + ""));
		} else {
			itemLink.setHref(ProjectLinkBuilder.generateProjectItemLink(
					stream.getProjectShortName(), stream.getExtratypeid(),
					stream.getType(), stream.getTypeid()));
		}
		itemLink.appendText(stream.getNamefield());
		return itemLink.write();
	}

}
