/**
 * Copyright © MyCollab
 * <p>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * <p>
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.mycollab.module.project.view.ticket;

import com.mycollab.core.MyCollabException;
import com.mycollab.core.utils.ClassUtils;
import com.mycollab.module.project.view.ProjectView;
import com.mycollab.module.project.view.parameters.TaskScreenData;
import com.mycollab.module.project.view.parameters.TicketScreenData;
import com.mycollab.module.project.view.task.TaskAddPresenter;
import com.mycollab.module.project.view.task.TaskReadPresenter;
import com.mycollab.vaadin.mvp.IPresenter;
import com.mycollab.vaadin.mvp.PresenterResolver;
import com.mycollab.vaadin.mvp.ScreenData;
import com.mycollab.vaadin.web.ui.AbstractPresenter;
import com.vaadin.ui.HasComponents;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
public class TicketPresenter extends AbstractPresenter<TicketContainer> {
    private static final long serialVersionUID = 1L;

    public TicketPresenter() {
        super(TicketContainer.class);
    }

    @Override
    protected void onGo(HasComponents container, ScreenData<?> data) {
        ProjectView projectView = (ProjectView) container;
        projectView.clearRightbar();
        projectView.gotoSubView(ProjectView.TICKET_ENTRY, view);

        IPresenter<?> presenter;

        if (data instanceof TaskScreenData.Read) {
            presenter = PresenterResolver.getPresenter(TaskReadPresenter.class);
        } else if (ClassUtils.instanceOf(data, TaskScreenData.Edit.class, TaskScreenData.Add.class)) {
            presenter = PresenterResolver.getPresenter(TaskAddPresenter.class);
        } else if (data instanceof TicketScreenData.GotoKanbanView) {
            presenter = PresenterResolver.getPresenter(ITicketKanbanPresenter.class);
        } else if (data == null || data instanceof TicketScreenData.GotoDashboard) {
            presenter = PresenterResolver.getPresenter(TicketDashboardPresenter.class);
        } else {
            throw new MyCollabException("No support data: " + data);
        }

        presenter.go(view, data);
    }
}
