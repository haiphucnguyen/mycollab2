/**
 * Copyright © MyCollab
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.mycollab.module.project.view.page;

import com.mycollab.core.MyCollabException;
import com.mycollab.module.project.ProjectTypeConstants;
import com.mycollab.module.project.view.ProjectLegacyView;
import com.mycollab.module.project.view.parameters.PageScreenData;
import com.mycollab.vaadin.mvp.PresenterResolver;
import com.mycollab.vaadin.mvp.ScreenData;
import com.mycollab.vaadin.web.ui.AbstractPresenter;
import com.vaadin.ui.HasComponents;

/**
 * @author MyCollab Ltd.
 * @since 4.4.0
 */
public class PagePresenter extends AbstractPresenter<PageContainer> {
    private static final long serialVersionUID = 1L;

    public PagePresenter() {
        super(PageContainer.class);
    }

    @Override
    protected void onGo(HasComponents container, ScreenData<?> data) {
        ProjectLegacyView projectViewContainer = (ProjectLegacyView) container;
        projectViewContainer.gotoSubView(ProjectTypeConstants.PAGE);

        AbstractPresenter presenter;
        if (data instanceof PageScreenData.Search) {
            presenter = PresenterResolver.getPresenter(PageListPresenter.class);
        } else if (data instanceof PageScreenData.Add || data instanceof PageScreenData.Edit) {
            presenter = PresenterResolver.getPresenter(PageAddPresenter.class);
        } else if (data instanceof PageScreenData.Read) {
            presenter = PresenterResolver.getPresenter(PageReadPresenter.class);
        } else {
            throw new MyCollabException("Do not support screen data " + data);
        }

        presenter.go(view, data);
    }

}
