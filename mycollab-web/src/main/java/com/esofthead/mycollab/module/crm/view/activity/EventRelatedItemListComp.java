/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.crm.view.activity;

import com.esofthead.mycollab.module.crm.domain.criteria.EventSearchCriteria;
import com.esofthead.mycollab.module.crm.ui.components.RelatedListComp;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author haiphucnguyen
 */
public class EventRelatedItemListComp extends RelatedListComp<EventSearchCriteria> {
    public EventRelatedItemListComp() {
        super("Activities");

        initUI();
    }
    
    private void initUI() {
        VerticalLayout contentContainer = (VerticalLayout) content;
        contentContainer.setSpacing(true);
        
        Button newBtn = new Button("New Task", new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                fireRelatedListHandler("task");
            }
        });
        newBtn.setIcon(new ThemeResource("icons/16/addRecord.png"));
    }
}
