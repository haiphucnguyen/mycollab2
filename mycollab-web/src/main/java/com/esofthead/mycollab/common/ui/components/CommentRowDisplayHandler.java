/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.common.ui.components;

import com.esofthead.mycollab.common.domain.SimpleComment;
import com.esofthead.mycollab.vaadin.ui.BeanList;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;

/**
 *
 * @author haiphucnguyen
 */
public class CommentRowDisplayHandler implements BeanList.RowDisplayHandler<SimpleComment> {

    @Override
    public Component generateRow(SimpleComment obj, int rowIndex) {
        return new Label("a");
    }
    
}
