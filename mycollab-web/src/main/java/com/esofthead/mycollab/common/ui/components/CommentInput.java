/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.common.ui.components;

import com.esofthead.mycollab.common.domain.Comment;
import com.esofthead.mycollab.common.service.CommentService;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.VerticalLayout;
import java.util.GregorianCalendar;

/**
 *
 * @author haiphucnguyen
 */
public class CommentInput extends VerticalLayout {

    private TextArea commentArea;
    private ReloadableComponent component;
    private String type;
    private Integer typeid;

    public CommentInput(final ReloadableComponent component, final String type, final Integer typeid) {
        this(component, type, typeid, false);
    }

    public CommentInput(final ReloadableComponent component, final String type, final Integer typeid, boolean cancelButtonEnable) {
        this.setWidth("600px");
        this.setSpacing(true);
        this.setMargin(true);
        
        this.component = component;
        this.type = type;
        this.typeid = typeid;

        commentArea = new TextArea();
        commentArea.setWidth("560px");
        this.addComponent(commentArea);
        
        HorizontalLayout controlsLayout = new HorizontalLayout();
        this.addComponent(controlsLayout);
        this.setComponentAlignment(controlsLayout, Alignment.MIDDLE_RIGHT);

        if (cancelButtonEnable) {
            Button cancelBtn = new Button("Cancel", new Button.ClickListener() {
                @Override
                public void buttonClick(ClickEvent event) {
                    component.cancel();
                }
            });
            cancelBtn.setStyleName("link");
            controlsLayout.addComponent(cancelBtn);
            controlsLayout.setComponentAlignment(cancelBtn, Alignment.MIDDLE_RIGHT);
        }


        Button newCommentBtn = new Button("Post", new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                Comment comment = new Comment();
                comment.setComment((String) commentArea.getValue());
                comment.setCreatedtime(new GregorianCalendar().getTime());
                comment.setCreateduser(AppContext.getUsername());
                comment.setSaccountid(AppContext.getAccountId());
                comment.setType(type);
                comment.setTypeid(typeid);

                CommentService commentService = AppContext.getSpringBean(CommentService.class);
                commentService.saveWithSession(comment, AppContext.getUsername());

                //save success, clear comment area and load list comments again
                commentArea.setValue("");
                component.reload();
            }
        });
        controlsLayout.addComponent(newCommentBtn);
        controlsLayout.setComponentAlignment(newCommentBtn, Alignment.MIDDLE_RIGHT);

    }
}
