/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.crm.view.activity;

import com.esofthead.mycollab.module.crm.domain.SimpleEvent;
import com.esofthead.mycollab.module.crm.domain.criteria.EventSearchCriteria;
import com.esofthead.mycollab.module.crm.events.ActivityEvent;
import com.esofthead.mycollab.module.crm.ui.components.RelatedListComp;
import com.esofthead.mycollab.vaadin.events.ApplicationEvent;
import com.esofthead.mycollab.vaadin.events.ApplicationEventListener;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.ui.IPagedBeanTable;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author haiphucnguyen
 */
public class EventRelatedItemListComp extends RelatedListComp<EventSearchCriteria> {

    private boolean allowCreateNew;

    public EventRelatedItemListComp(String title, boolean allowCreateNew) {
        super(title);

        this.allowCreateNew = allowCreateNew;

        initUI();
    }

    private void initUI() {
        VerticalLayout contentContainer = (VerticalLayout) content;
        contentContainer.setSpacing(true);

        if (allowCreateNew) {
            HorizontalLayout buttonControls = new HorizontalLayout();
            Button newTaskBtn = new Button("New Task", new Button.ClickListener() {
                @Override
                public void buttonClick(Button.ClickEvent event) {
                    fireRelatedListHandler("task");
                }
            });
            newTaskBtn.setIcon(new ThemeResource("icons/16/addRecord.png"));
            buttonControls.addComponent(newTaskBtn);

            Button newCallBtn = new Button("New Call", new Button.ClickListener() {
                @Override
                public void buttonClick(Button.ClickEvent event) {
                    fireRelatedListHandler("call");
                }
            });
            newCallBtn.setIcon(new ThemeResource("icons/16/addRecord.png"));
            buttonControls.addComponent(newCallBtn);

            Button newMeetingBtn = new Button("New Meeting", new Button.ClickListener() {
                @Override
                public void buttonClick(Button.ClickEvent event) {
                    fireRelatedListHandler("call");
                }
            });
            newMeetingBtn.setIcon(new ThemeResource("icons/16/addRecord.png"));
            buttonControls.addComponent(newMeetingBtn);

            contentContainer.addComponent(buttonControls);
        }

        tableItem = new EventTableDisplay(new String[]{"status",
                    "eventType", "subject", "startDate", "endDate"},
                new String[]{"Status", "Type", "Subject", "Start Date",
                    "End Date"});

        tableItem.addTableListener(new ApplicationEventListener<IPagedBeanTable.TableClickEvent>() {
            @Override
            public Class<? extends ApplicationEvent> getEventType() {
                return IPagedBeanTable.TableClickEvent.class;
            }

            @Override
            public void handle(IPagedBeanTable.TableClickEvent event) {
                SimpleEvent simpleEvent = (SimpleEvent) event.getData();
                if ("Task".equals(simpleEvent.getEventType())) {
                    EventBus.getInstance().fireEvent(
                            new ActivityEvent.TaskRead(this,
                            simpleEvent.getId()));
                } else if ("Meeting".equals(simpleEvent
                        .getEventType())) {
                    EventBus.getInstance().fireEvent(
                            new ActivityEvent.MeetingRead(this,
                            simpleEvent.getId()));
                } else if ("Call".equals(simpleEvent
                        .getEventType())) {
                    EventBus.getInstance().fireEvent(
                            new ActivityEvent.CallRead(this,
                            simpleEvent.getId()));
                }
            }
        });
        
        contentContainer.addComponent(tableItem);
   }
}
