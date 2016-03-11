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
package com.esofthead.mycollab.pro.module.project.view;

import com.esofthead.mycollab.common.domain.AggregateTag;
import com.esofthead.mycollab.common.domain.Tag;
import com.esofthead.mycollab.common.service.TagService;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.domain.ProjectGenericTask;
import com.esofthead.mycollab.module.project.domain.criteria.ProjectGenericTaskSearchCriteria;
import com.esofthead.mycollab.module.project.service.ProjectGenericTaskService;
import com.esofthead.mycollab.module.project.ui.components.ProjectTaskRowDisplayHandler;
import com.esofthead.mycollab.module.project.view.ITagListView;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.mvp.AbstractPageView;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.esofthead.mycollab.vaadin.ui.ELabel;
import com.esofthead.mycollab.vaadin.web.ui.DefaultBeanPagedList;
import com.esofthead.mycollab.vaadin.web.ui.UIConstants;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.themes.ValoTheme;
import org.apache.commons.collections.CollectionUtils;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * @author MyCollab Ltd.
 * @since 5.0.1
 */
@ViewComponent
public class TagListViewImpl extends AbstractPageView implements ITagListView {
    private TagService tagService;
    private ProjectGenericTaskService projectGenericTaskService;

    private DefaultBeanPagedList<ProjectGenericTaskService, ProjectGenericTaskSearchCriteria, ProjectGenericTask> assignmentList;
    private List<String> selectedTags;

    public TagListViewImpl() {
        withMargin(new MarginInfo(true, false, true, true));
        projectGenericTaskService = ApplicationContextUtil.getSpringBean(ProjectGenericTaskService.class);
        tagService = ApplicationContextUtil.getSpringBean(TagService.class);
        selectedTags = new ArrayList<>();
    }

    @Override
    public void displayTags(Tag tag) {
        removeAllComponents();
        selectedTags.clear();

        if (tag != null) {
            selectedTags.add(tag.getName());
        }
        MHorizontalLayout header = new MHorizontalLayout().withMargin(new MarginInfo(false, false, true, false))
                .withWidth("100%");
        Label headerLbl = new Label(FontAwesome.TAGS.getHtml() + " Tags", ContentMode.HTML);
        headerLbl.setSizeUndefined();
        headerLbl.setStyleName(ValoTheme.LABEL_H2);
        headerLbl.addStyleName(ValoTheme.LABEL_NO_MARGIN);
        header.with(headerLbl);

        MHorizontalLayout contentWrapper = new MHorizontalLayout();
        assignmentList = new DefaultBeanPagedList<>(projectGenericTaskService, new ProjectTaskRowDisplayHandler());
        assignmentList.addStyleName("border-top");

        MVerticalLayout rightSideBar = new MVerticalLayout().withSpacing(false).withMargin(new MarginInfo(false,
                true, true, true)).withWidth("450px");
        MHorizontalLayout panelHeader = new MHorizontalLayout().withMargin(new MarginInfo(false, true,
                false, true)).withWidth("100%");
        panelHeader.addStyleName("panel-header");
        ELabel lbl = new ELabel("Tag Cloud").withStyleName(ValoTheme.LABEL_H3, ValoTheme.LABEL_NO_MARGIN);
        panelHeader.with(lbl).alignAll(Alignment.MIDDLE_LEFT);

        TagCloudComp cloudComp = new TagCloudComp();
        cloudComp.displayTagItems();
        rightSideBar.with(panelHeader, cloudComp);

        contentWrapper.with(assignmentList, rightSideBar).expand(assignmentList);
        with(header, contentWrapper);
    }

    private void displaySelectedTags() {
        if (CollectionUtils.isNotEmpty(selectedTags)) {
            ProjectGenericTaskSearchCriteria searchCriteria = new ProjectGenericTaskSearchCriteria();
            searchCriteria.setTagNames(new SetSearchField<>(selectedTags));
            assignmentList.setSearchCriteria(searchCriteria);
        } else {
            assignmentList.setCurrentDataList(new ArrayList<ProjectGenericTask>());
        }
    }

    private class TagCloudComp extends CssLayout {
        TagCloudComp() {
            this.setStyleName("tagcloud");
        }

        void displayTagItems() {
            List<AggregateTag> tags = tagService.findTagsInProject(CurrentProjectVariables.getProjectId(), AppContext
                    .getAccountId());
            if (CollectionUtils.isEmpty(tags)) {
                this.addComponent(new Label("No tag is existed"));
            } else {
                for (AggregateTag tag : tags) {
                    TagButton btn = new TagButton(tag);
                    this.addComponent(btn);
                    boolean isSelected = selectedTags.contains(tag.getName());
                    btn.setSelected(isSelected);
                }
            }
            displaySelectedTags();
        }
    }

    private class TagButton extends Button {
        private boolean isSelected = false;

        public TagButton(final AggregateTag tag) {
            super(tag.getName() + " (" + tag.getCount() + ")");
            this.setStyleName("tagbutton");
            this.addClickListener(new ClickListener() {
                @Override
                public void buttonClick(ClickEvent clickEvent) {
                    isSelected = !isSelected;
                    if (isSelected) {
                        selectedTags.add(tag.getName());
                    } else {
                        selectedTags.remove(tag.getName());
                    }
                    displaySelectedTags();
                    setSelected(isSelected);
                }
            });
        }

        public void setSelected(boolean isSelected) {
            if (isSelected) {
                removeStyleName(UIConstants.BUTTON_OPTION);
                addStyleName(UIConstants.BUTTON_BLOCK);
            } else {
                removeStyleName(UIConstants.BUTTON_BLOCK);
                addStyleName(UIConstants.BUTTON_OPTION);
            }
        }
    }
}
