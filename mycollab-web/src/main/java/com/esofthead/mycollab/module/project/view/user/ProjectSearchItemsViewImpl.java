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

import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectLinkBuilder;
import com.esofthead.mycollab.module.project.domain.ProjectGenericItem;
import com.esofthead.mycollab.module.project.domain.criteria.ProjectGenericItemSearchCriteria;
import com.esofthead.mycollab.module.project.service.ProjectGenericItemService;
import com.esofthead.mycollab.module.project.ui.ProjectAssetsManager;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.mvp.AbstractPageView;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.esofthead.mycollab.vaadin.ui.*;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;
import org.vaadin.maddon.layouts.MHorizontalLayout;
import org.vaadin.maddon.layouts.MVerticalLayout;

/**
 * @author MyCollab Ltd.
 * @since 5.0.3
 */
@ViewComponent
public class ProjectSearchItemsViewImpl extends AbstractPageView implements ProjectSearchItemsView {

    private DefaultBeanPagedList<ProjectGenericItemService, ProjectGenericItemSearchCriteria, ProjectGenericItem>
            searchItemsTable;

    @Override
    public void displayResults(String value) {
        this.removeAllComponents();

        Label headerLbl = new Label(FontAwesome.SEARCH.getHtml() + " Search for " + value, ContentMode.HTML);
        headerLbl.addStyleName("headerName");

        searchItemsTable = new DefaultBeanPagedList<>(ApplicationContextUtil.getSpringBean(ProjectGenericItemService.class), new
                ItemRowDisplayHandler());

        this.with(headerLbl, searchItemsTable);
        ProjectGenericItemSearchCriteria criteria = new ProjectGenericItemSearchCriteria();
        criteria.setPrjKeys(new SetSearchField<>(CurrentProjectVariables.getProjectId()));
        criteria.setTxtValue(new StringSearchField(value));
        searchItemsTable.setSearchCriteria(criteria);
    }

    private static class ItemRowDisplayHandler implements AbstractBeanPagedList.RowDisplayHandler<ProjectGenericItem> {
        @Override
        public Component generateRow(ProjectGenericItem obj, int rowIndex) {
            MVerticalLayout layout = new MVerticalLayout();
            MHorizontalLayout header = new MHorizontalLayout().withWidth("100%");
            FontIconLabel icon = new FontIconLabel(ProjectAssetsManager.getAsset(obj.getType()));
            icon.addStyleName("icon-18px");
            CssLayout iconWrapper = new CssLayout();
            iconWrapper.addComponent(icon);
            Label link = new Label(ProjectLinkBuilder.generateProjectItemHtmlLink(obj.getProjectShortName(), obj
                    .getProjectId(), obj.getSummary(), obj.getType(), obj.getTypeId()), ContentMode.HTML);
            link.setStyleName("link");
            header.with(iconWrapper, link).expand(link);

            SafeHtmlLabel desc = new SafeHtmlLabel(obj.getDescription());

            MHorizontalLayout footer = new MHorizontalLayout().withWidth("100%");
            footer.setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);
            UserLink createUserLink = new UserLink(obj.getCreatedUser(), obj.getCreatedUserAvatarId(), obj.getCreatedUserDisplayName());
            Label lastUpdateLbl = new Label("Last updated on: " + AppContext.formatPrettyTime(obj.getLastUpdatedTime()));
            footer.with(new Label("Created by: "), createUserLink, lastUpdateLbl);

            layout.with(header, desc, footer);
            return layout;
        }
    }
}
