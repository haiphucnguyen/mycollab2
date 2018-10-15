package com.mycollab.pro.module.project.ui.components;

import com.google.common.base.MoreObjects;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Label;
import org.vaadin.viritin.layouts.MHorizontalLayout;

/**
 * @author MyCollab Ltd
 * @since 5.3.5
 */
public class FeatureSelectionBox extends MHorizontalLayout {
    private static final long serialVersionUID = 1L;

    private CheckBox checkbox;

    public FeatureSelectionBox(VaadinIcons iconResource, String caption, Boolean selected) {
        this.withMargin(true).withFullWidth().withStyleName("feature-select-box");
        Label captionLbl = new Label(iconResource.getHtml() + " " + caption, ContentMode.HTML);
        checkbox = new CheckBox("", MoreObjects.firstNonNull(selected, Boolean.FALSE));
        this.with(captionLbl, checkbox).expand(captionLbl);
    }

    public Boolean getSelected() {
        return checkbox.getValue();
    }
}
