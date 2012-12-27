/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.common.ui.components;

import com.esofthead.mycollab.common.domain.SimpleComment;
import com.esofthead.mycollab.vaadin.ui.BeanList;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author haiphucnguyen
 */
public class CommentRowDisplayHandler implements BeanList.RowDisplayHandler<SimpleComment> {
    
    @Override
    public Component generateRow(SimpleComment comment, int rowIndex) {
        VerticalLayout layout = new VerticalLayout();
        Label header = new Label(comment.getOwnerFullName() + " said on " + AppContext.formatDateToHumanRead(comment.getCreatedtime()));
        layout.addComponent(header);
        
        Label content = new Label(comment.getComment());
        layout.addComponent(content);
        return layout;
    }
}
