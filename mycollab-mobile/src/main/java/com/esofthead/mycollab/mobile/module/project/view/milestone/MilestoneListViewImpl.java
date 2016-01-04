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
package com.esofthead.mycollab.mobile.module.project.view.milestone;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.eventmanager.EventBusFactory;
import com.esofthead.mycollab.mobile.module.project.events.MilestoneEvent;
import com.esofthead.mycollab.mobile.ui.AbstractMobileSwipeView;
import com.esofthead.mycollab.mobile.ui.AbstractMobileTabPageView;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.domain.criteria.MilestoneSearchCriteria;
import com.esofthead.mycollab.module.project.i18n.MilestoneI18nEnum;
import com.esofthead.mycollab.module.project.i18n.OptionI18nEnum.MilestoneStatus;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;

/**
 * @author MyCollab Ltd.
 * @since 4.5.2
 */
@ViewComponent
public class MilestoneListViewImpl extends AbstractMobileTabPageView implements MilestoneListView {
    private static final long serialVersionUID = 2799191640785637556L;

    private MilestoneListChildView closedMilestonesList;
    private MilestoneListChildView inProgressMilestonesList;
    private MilestoneListChildView futureMilestonesList;

    public MilestoneListViewImpl() {
        this.addStyleName("milestones-list-view");

        this.closedMilestonesList = new MilestoneListChildView();
        this.addTab(closedMilestonesList, FontAwesome.MINUS.getHtml() + " " + AppContext.getMessage(MilestoneI18nEnum.WIDGET_CLOSED_PHASE_TITLE));

        this.inProgressMilestonesList = new MilestoneListChildView();
        this.addTab(inProgressMilestonesList, FontAwesome.SPINNER.getHtml() + " " + AppContext.getMessage(MilestoneI18nEnum.WIDGET_INPROGRESS_PHASE_TITLE));

        this.futureMilestonesList = new MilestoneListChildView();
        this.addTab(futureMilestonesList, FontAwesome.CLOCK_O.getHtml() + " " + AppContext.getMessage(MilestoneI18nEnum.WIDGET_FUTURE_PHASE_TITLE));

        this.addListener(new SelectedTabChangeListener() {

            private static final long serialVersionUID = 1L;

            @Override
            public void selectedTabChange(SelectedTabChangeEvent event) {
                Component newTab = getSelelectedTab().getComponent();
                MilestoneSearchCriteria searchCriteria = new MilestoneSearchCriteria();
                searchCriteria.setProjectId(new NumberSearchField(SearchField.AND, CurrentProjectVariables.getProjectId()));
                if (newTab.equals(closedMilestonesList)) {
                    searchCriteria.setStatus(new StringSearchField(MilestoneStatus.Closed.name()));
                    closedMilestonesList.setSearchCriteria(searchCriteria);
                } else if (newTab.equals(futureMilestonesList)) {
                    searchCriteria.setStatus(new StringSearchField(MilestoneStatus.Future.name()));
                    futureMilestonesList.setSearchCriteria(searchCriteria);
                } else {
                    searchCriteria.setStatus(new StringSearchField(MilestoneStatus.InProgress.name()));
                    inProgressMilestonesList.setSearchCriteria(searchCriteria);
                }
            }
        });
    }

    @Override
    public void goToClosedMilestones() {
        this.setSelectedTab(closedMilestonesList);
    }

    @Override
    public void goToInProgressMilestones() {
        this.setSelectedTab(inProgressMilestonesList);
    }

    @Override
    public void goToFutureMilestones() {
        this.setSelectedTab(futureMilestonesList);
    }

    private static class MilestoneListChildView extends AbstractMobileSwipeView {
        private static final long serialVersionUID = 5398821908475706469L;
        private MilestoneListDisplay milestoneList;

        public MilestoneListChildView() {
            super();
            this.setCaption(AppContext.getMessage(MilestoneI18nEnum.VIEW_LIST_TITLE));
            this.milestoneList = new MilestoneListDisplay();
            this.setContent(milestoneList);
            this.setRightComponent(createRightComponent());
        }

        public void setSearchCriteria(MilestoneSearchCriteria searchCriteria) {
            this.milestoneList.setSearchCriteria(searchCriteria);
        }

        private Component createRightComponent() {
            Button addMilestone = new Button("", new Button.ClickListener() {
                @Override
                public void buttonClick(Button.ClickEvent clickEvent) {
                    EventBusFactory.getInstance().post(new MilestoneEvent.GotoAdd(this, null));
                }
            });
            addMilestone.setStyleName("add-btn");
            return addMilestone;
        }
    }

}
