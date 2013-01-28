/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.crm.view.activity;

import com.esofthead.mycollab.core.arguments.BitSearchField;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.module.crm.domain.SimpleCall;
import com.esofthead.mycollab.module.crm.domain.criteria.CallSearchCriteria;
import com.esofthead.mycollab.module.crm.service.CallService;
import com.esofthead.mycollab.vaadin.events.ApplicationEvent;
import com.esofthead.mycollab.vaadin.events.ApplicationEventListener;
import com.esofthead.mycollab.vaadin.ui.Depot;
import com.esofthead.mycollab.vaadin.ui.IPagedBeanTable;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author haiphucnguyen
 */
public class CallListDashlet extends Depot {

    private CallTableDisplay tableItem;

    public CallListDashlet() {
        super("My Calls", new VerticalLayout());

        tableItem = new CallTableDisplay(new String[]{"isClosed", "subject", "startdate", "status"}, new String[]{"", "Subject", "Start Date", "Status"});
        tableItem.addTableListener(new ApplicationEventListener<IPagedBeanTable.TableClickEvent>() {
            @Override
            public Class<? extends ApplicationEvent> getEventType() {
                return IPagedBeanTable.TableClickEvent.class;
            }

            @Override
            public void handle(IPagedBeanTable.TableClickEvent event) {
                SimpleCall call = (SimpleCall) event.getData();
                if ("isClosed".equals(event.getFieldName())) {
                    call.setIsclosed(true);
                    CallService callService = AppContext.getSpringBean(CallService.class);
                    callService.updateWithSession(call, AppContext.getUsername());
                    display();
                }
            }
        });
        this.bodyContent.addComponent(tableItem);
    }

    public void display() {
        CallSearchCriteria criteria = new CallSearchCriteria();
        criteria.setSaccountid(new NumberSearchField(AppContext.getAccountId()));
        criteria.setAssignUsers(new SetSearchField<String>(new String[]{AppContext.getUsername()}));
        criteria.setIsClosed(BitSearchField.FALSE);
        tableItem.setSearchCriteria(criteria);
    }
}
