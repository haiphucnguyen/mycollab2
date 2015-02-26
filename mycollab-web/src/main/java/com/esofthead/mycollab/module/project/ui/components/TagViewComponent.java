package com.esofthead.mycollab.module.project.ui.components;

import com.esofthead.mycollab.common.domain.Tag;
import com.esofthead.mycollab.common.service.TagService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import org.vaadin.maddon.layouts.MHorizontalLayout;
import org.vaadin.suggestfield.SuggestField;

import java.util.List;

/**
 * @author MyCollab Ltd.
 * @since 5.0.1
 */
public class TagViewComponent extends CssLayout {
    private String type;
    private int typeId;

    private TagService tagService;

    public TagViewComponent() {
        tagService = ApplicationContextUtil.getSpringBean(TagService.class);
        this.setStyleName("project-tag-comp");
    }

    public void display(String type, int typeId) {
        this.type = type;
        this.typeId = typeId;

        List<Tag> tags = tagService.findTags(type, typeId + "");
        for (Tag tag : tags) {
            this.addComponent(new TagBlock(tag));
        }

        this.addComponent(createAddTagBtn());
    }

    private Button createAddTagBtn() {
        final Button addTagBtn = new Button("Add tags", FontAwesome.PLUS_CIRCLE);
        addTagBtn.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                TagViewComponent.this.removeComponent(addTagBtn);
                TagViewComponent.this.addComponent(createSaveTagComp());
            }
        });
        addTagBtn.setStyleName("link");
        addTagBtn.addStyleName(UIConstants.THEME_GREEN_LINK);
        return addTagBtn;
    }

    private HorizontalLayout createSaveTagComp() {
        final MHorizontalLayout layout = new MHorizontalLayout();
        SuggestField field = new SuggestField();
        Button addBtn = new Button("Add");
        addBtn.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                TagViewComponent.this.removeComponent(layout);
                TagViewComponent.this.addComponent(createAddTagBtn());
            }
        });
        layout.with(field, addBtn);
        return layout;
    }

    private class TagBlock extends CssLayout {
        TagBlock(Tag tag) {
            this.addComponent(new Label(tag.getName()));
        }
    }
}
