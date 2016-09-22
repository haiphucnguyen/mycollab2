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
package com.mycollab.module.project.view.assignments;

import com.mycollab.common.domain.GroupItem;
import com.mycollab.core.utils.BeanUtility;
import com.mycollab.core.utils.StringUtils;
import com.mycollab.db.arguments.StringSearchField;
import com.mycollab.module.project.domain.criteria.ProjectAssignmentSearchCriteria;
import com.mycollab.module.project.i18n.TaskI18nEnum;
import com.mycollab.module.project.service.ProjectAssignmentService;
import com.mycollab.module.project.view.task.ITaskAssigneeChartWidget;
import com.mycollab.module.user.CommonTooltipGenerator;
import com.mycollab.module.user.domain.SimpleUser;
import com.mycollab.module.user.service.UserService;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.MyCollabUI;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.mvp.ViewManager;
import com.mycollab.vaadin.ui.UIConstants;
import com.mycollab.vaadin.ui.UserAvatarControlFactory;
import com.mycollab.vaadin.web.ui.DepotWithChart;
import com.mycollab.vaadin.web.ui.ProgressBarIndicator;
import com.mycollab.vaadin.web.ui.WebUIConstants;
import com.vaadin.ui.Alignment;
import org.apache.commons.collections.CollectionUtils;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.layouts.MCssLayout;
import org.vaadin.viritin.layouts.MHorizontalLayout;

import java.util.List;

/**
 * @author MyCollab Ltd.
 * @since 4.0
 */
public class UnresolvedAssignmentByAssigneeWidget extends DepotWithChart {
    private static final long serialVersionUID = 1L;

    private ProjectAssignmentSearchCriteria searchCriteria;
    private int totalCountItems;
    private List<GroupItem> groupItems;

//    private ApplicationEventListener<TaskEvent.HasTaskChange> taskChangeHandler = new ApplicationEventListener<TaskEvent.HasTaskChange>() {
//        @Override
//        @Subscribe
//        public void handle(TaskEvent.HasTaskChange event) {
//            if (searchCriteria != null) {
//                UI.getCurrent().access(() -> setSearchCriteria(searchCriteria));
//            }
//        }
//    };

    @Override
    public void attach() {
//        EventBusFactory.getInstance().register(taskChangeHandler);
        super.attach();
    }

    @Override
    public void detach() {
//        EventBusFactory.getInstance().unregister(taskChangeHandler);
        super.detach();
    }

    public void setSearchCriteria(final ProjectAssignmentSearchCriteria searchCriteria) {
        this.searchCriteria = searchCriteria;

        ProjectAssignmentService projectAssignmentService = AppContextUtil.getSpringBean(ProjectAssignmentService.class);
        totalCountItems = projectAssignmentService.getTotalCount(searchCriteria);
        groupItems = projectAssignmentService.getAssigneeSummary(searchCriteria);

        this.setTitle(String.format("%s (%d)", UserUIContext.getMessage(TaskI18nEnum.WIDGET_UNRESOLVED_BY_ASSIGNEE_TITLE), totalCountItems));
        displayPlainMode();
    }

    @Override
    protected void displayPlainMode() {
        bodyContent.removeAllComponents();
        if (CollectionUtils.isNotEmpty(groupItems)) {
            for (GroupItem item : groupItems) {
                MHorizontalLayout assigneeLayout = new MHorizontalLayout().withFullWidth();
                assigneeLayout.setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);

                String assignUser = item.getGroupid();
                String assignUserFullName = item.getGroupid() == null ? "" : item.getGroupname();

                if (StringUtils.isBlank(assignUserFullName)) {
                    assignUserFullName = StringUtils.extractNameFromEmail(item.getGroupid());
                }

                TaskAssigneeLink taskAssigneeLink = new TaskAssigneeLink(assignUser, item.getExtraValue(), assignUserFullName);
                assigneeLayout.addComponent(new MCssLayout(taskAssigneeLink).withWidth("110px"));
                ProgressBarIndicator indicator = new ProgressBarIndicator(totalCountItems, totalCountItems - item.getValue().intValue(), false);
                indicator.setWidth("100%");
                assigneeLayout.with(indicator).expand(indicator);
                bodyContent.addComponent(assigneeLayout);
            }
        }
    }

    @Override
    protected void displayChartMode() {
        bodyContent.removeAllComponents();
        ITaskAssigneeChartWidget taskAssigneeChartWidget = ViewManager.getCacheComponent(ITaskAssigneeChartWidget.class);
//        taskAssigneeChartWidget.displayChart(searchCriteria);
//        bodyContent.addComponent(taskAssigneeChartWidget);
    }

    class TaskAssigneeLink extends MButton {
        private static final long serialVersionUID = 1L;

        TaskAssigneeLink(final String assignee, String assigneeAvatarId, final String assigneeFullName) {
            super(StringUtils.trim(assigneeFullName, 25, true));

            this.withListener(clickEvent -> {
                ProjectAssignmentSearchCriteria criteria = BeanUtility.deepClone(searchCriteria);
                criteria.setAssignUser(StringSearchField.and(assignee));
//                EventBusFactory.getInstance().post(new TaskEvent.SearchRequest(UnresolvedAssignmentByAssigneeWidget.this, criteria));
            }).withWidth("100%").withIcon(UserAvatarControlFactory.createAvatarResource(assigneeAvatarId, 16))
                    .withStyleName(WebUIConstants.BUTTON_LINK, UIConstants.TEXT_ELLIPSIS);
            UserService service = AppContextUtil.getSpringBean(UserService.class);
            SimpleUser user = service.findUserByUserNameInAccount(assignee, MyCollabUI.getAccountId());
            this.setDescription(CommonTooltipGenerator.generateTooltipUser(UserUIContext.getUserLocale(), user,
                    MyCollabUI.getSiteUrl(), UserUIContext.getUserTimeZone()));
        }
    }
}
