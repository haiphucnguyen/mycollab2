package com.esofthead.mycollab.pro.module.project.view;

import com.esofthead.mycollab.common.domain.AggregateTag;
import com.esofthead.mycollab.common.domain.Tag;
import com.esofthead.mycollab.common.service.TagService;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.domain.ProjectGenericItem;
import com.esofthead.mycollab.module.project.domain.criteria.ProjectGenericItemSearchCriteria;
import com.esofthead.mycollab.module.project.service.ProjectGenericItemService;
import com.esofthead.mycollab.module.project.ui.components.GenericItemRowDisplayHandler;
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
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;
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

    private DefaultBeanPagedList<ProjectGenericItemService, ProjectGenericItemSearchCriteria, ProjectGenericItem>
            assignmentList;
    private List<String> selectedTags;

    public TagListViewImpl() {
        withMargin(new MarginInfo(true, false, true, true));
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
        header.with(ELabel.h2(FontAwesome.TAGS.getHtml() + " Tags").withWidthUndefined());

        MHorizontalLayout contentWrapper = new MHorizontalLayout();
        assignmentList = new DefaultBeanPagedList<>(ApplicationContextUtil.getSpringBean(ProjectGenericItemService.class),
                new GenericItemRowDisplayHandler());
        assignmentList.addStyleName("border-top");

        MVerticalLayout rightSideBar = new MVerticalLayout().withSpacing(false).withMargin(new MarginInfo(false,
                true, true, true)).withWidth("450px");
        MHorizontalLayout panelHeader = new MHorizontalLayout().withMargin(new MarginInfo(false, true,
                false, true)).withWidth("100%");
        panelHeader.addStyleName("panel-header");

        panelHeader.with(ELabel.h3("Tag Cloud")).alignAll(Alignment.MIDDLE_LEFT);

        TagCloudComp cloudComp = new TagCloudComp();
        cloudComp.displayTagItems();
        rightSideBar.with(panelHeader, cloudComp);

        contentWrapper.with(assignmentList, rightSideBar).expand(assignmentList);
        with(header, contentWrapper);
    }

    private void displaySelectedTags() {
        if (CollectionUtils.isNotEmpty(selectedTags)) {
            ProjectGenericItemSearchCriteria searchCriteria = new ProjectGenericItemSearchCriteria();
            searchCriteria.setTagNames(new SetSearchField<>(selectedTags));
            assignmentList.setSearchCriteria(searchCriteria);
        } else {
            assignmentList.setCurrentDataList(new ArrayList<ProjectGenericItem>());
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
