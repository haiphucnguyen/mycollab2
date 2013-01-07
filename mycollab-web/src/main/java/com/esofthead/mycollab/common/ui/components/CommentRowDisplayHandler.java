/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.common.ui.components;

import com.esofthead.mycollab.common.domain.SimpleComment;
import com.esofthead.mycollab.core.utils.DateTimeUtils;
import com.esofthead.mycollab.vaadin.ui.BeanList;
import com.esofthead.mycollab.vaadin.ui.UserAvatar;
import com.esofthead.mycollab.vaadin.ui.UserLink;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author haiphucnguyen
 */
public class CommentRowDisplayHandler implements BeanList.RowDisplayHandler<SimpleComment> {
    
    @Override
    public Component generateRow(SimpleComment comment, int rowIndex) {
        HorizontalLayout layout = new HorizontalLayout();
        layout.addComponent(new UserAvatar(comment.getCreateduser(), comment.getOwnerFullName()));
        
        VerticalLayout contentLayout = new VerticalLayout();
        layout.addComponent(contentLayout);
        
        HorizontalLayout commentHeader = new HorizontalLayout();
        UserLink userLink = new UserLink(comment.getCreateduser(), comment.getOwnerFullName());
        commentHeader.addComponent(userLink);
        commentHeader.setComponentAlignment(userLink, Alignment.MIDDLE_LEFT);
        
        Label dateLbl = new Label("commented on " + DateTimeUtils.getStringDateFromNow(comment.getCreatedtime()));
        commentHeader.addComponent(dateLbl);
        commentHeader.setComponentAlignment(dateLbl, Alignment.MIDDLE_LEFT);
        
        contentLayout.addComponent(commentHeader);
        
        Label content = new Label(comment.getComment());
        contentLayout.addComponent(content);
        return layout;
    }
}
