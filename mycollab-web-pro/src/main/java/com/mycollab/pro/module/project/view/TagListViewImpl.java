package com.mycollab.pro.module.project.view;

import com.mycollab.common.domain.AggregateTag;
import com.mycollab.common.domain.Tag;
import com.mycollab.common.i18n.TagI18nEnum;
import com.mycollab.common.service.TagService;
import com.mycollab.db.arguments.SetSearchField;
import com.mycollab.module.project.CurrentProjectVariables;
import com.mycollab.module.project.domain.ProjectGenericItem;
import com.mycollab.module.project.domain.criteria.ProjectGenericItemSearchCriteria;
import com.mycollab.module.project.i18n.ProjectCommonI18nEnum;
import com.mycollab.module.project.service.ProjectGenericItemService;
import com.mycollab.module.project.ui.components.GenericItemRowDisplayHandler;
import com.mycollab.module.project.view.ITagListView;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.AppContext;
import com.mycollab.vaadin.mvp.AbstractPageView;
import com.mycollab.vaadin.mvp.ViewComponent;
import com.mycollab.vaadin.ui.ELabel;
import com.mycollab.vaadin.web.ui.DefaultBeanPagedList;
import com.mycollab.vaadin.web.ui.WebUIConstants;
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

    private DefaultBeanPagedList<ProjectGenericItemService, ProjectGenericItemSearchCriteria, ProjectGenericItem> assignmentList;
    private List<String> selectedTags;

    public TagListViewImpl() {
        withMargin(new MarginInfo(true, false, true, true));
        tagService = AppContextUtil.getSpringBean(TagService.class);
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
                .withFullWidth();
        header.with(ELabel.h2(FontAwesome.TAGS.getHtml() + " " + AppContext.getMessage(ProjectCommonI18nEnum.VIEW_TAG))
                .withWidthUndefined());

        MHorizontalLayout contentWrapper = new MHorizontalLayout();
        assignmentList = new DefaultBeanPagedList<>(AppContextUtil.getSpringBean(ProjectGenericItemService.class),
                new GenericItemRowDisplayHandler());
        assignmentList.addStyleName("border-top");

        MVerticalLayout rightSideBar = new MVerticalLayout().withSpacing(false).withMargin(new MarginInfo(false,
                true, true, true)).withWidth("450px");
        MHorizontalLayout panelHeader = new MHorizontalLayout().withMargin(new MarginInfo(false, true,
                false, true)).withFullWidth();
        panelHeader.addStyleName(WebUIConstants.PANEL_HEADER);

        panelHeader.with(ELabel.h3(AppContext.getMessage(TagI18nEnum.OPT_TAG_CLOUD))).alignAll(Alignment.MIDDLE_LEFT);

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
            assignmentList.setCurrentDataList(new ArrayList<>());
        }
    }

    private class TagCloudComp extends CssLayout {
        TagCloudComp() {
            this.setStyleName("tagcloud");
        }

        void displayTagItems() {
            List<AggregateTag> tags = tagService.findTagsInProject(CurrentProjectVariables.getProjectId(), AppContext.getAccountId());
            if (CollectionUtils.isEmpty(tags)) {
                this.addComponent(new Label(AppContext.getMessage(TagI18nEnum.OPT_NO_TAG_EXISTED)));
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

        TagButton(final AggregateTag tag) {
            super(tag.getName() + " (" + tag.getCount() + ")");
            this.setStyleName("tagbutton");
            this.addClickListener(clickEvent -> {
                isSelected = !isSelected;
                if (isSelected) {
                    selectedTags.add(tag.getName());
                } else {
                    selectedTags.remove(tag.getName());
                }
                displaySelectedTags();
                setSelected(isSelected);
            });
        }

        public void setSelected(boolean isSelected) {
            if (isSelected) {
                removeStyleName(WebUIConstants.BUTTON_OPTION);
                addStyleName(WebUIConstants.BLOCK);
            } else {
                removeStyleName(WebUIConstants.BLOCK);
                addStyleName(WebUIConstants.BUTTON_OPTION);
            }
        }
    }
}
