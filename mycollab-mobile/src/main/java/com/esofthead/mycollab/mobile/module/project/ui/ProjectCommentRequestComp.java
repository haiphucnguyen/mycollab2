package com.esofthead.mycollab.mobile.module.project.ui;


import com.esofthead.mycollab.mobile.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.ELabel;
import com.vaadin.addon.touchkit.ui.NavigationManager;
import com.vaadin.event.LayoutEvents;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.UI;
import org.vaadin.viritin.layouts.MHorizontalLayout;

/**
 * @author MyCollab Ltd
 * @since 5.2.5
 */
public class ProjectCommentRequestComp extends MHorizontalLayout {
    public ProjectCommentRequestComp(final String typeVal, final String typeIdVal, final Integer extraTypeIdVal) {
        withMargin(true);
        ELabel hintLbl = new ELabel(FontAwesome.COMMENT.getHtml() + " Add a comment", ContentMode.HTML).withStyleName
                (UIConstants.META_INFO);
        this.addComponent(hintLbl);
        this.addLayoutClickListener(new LayoutEvents.LayoutClickListener() {
            @Override
            public void layoutClick(LayoutEvents.LayoutClickEvent layoutClickEvent) {
                ((NavigationManager) UI.getCurrent().getContent()).navigateTo(new ProjectCommentInputView(typeVal,
                        typeIdVal, extraTypeIdVal));
            }
        });
    }
}
