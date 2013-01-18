/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.crm.view.account;

import com.esofthead.mycollab.module.crm.domain.SimpleCase;
import com.esofthead.mycollab.module.crm.domain.criteria.CaseSearchCriteria;
import com.esofthead.mycollab.module.crm.events.CaseEvent;
import com.esofthead.mycollab.module.crm.ui.components.RelatedListComp;
import com.esofthead.mycollab.module.crm.view.cases.CaseTableDisplay;
import com.esofthead.mycollab.module.user.RolePermissionCollections;
import com.esofthead.mycollab.vaadin.events.ApplicationEvent;
import com.esofthead.mycollab.vaadin.events.ApplicationEventListener;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.ui.IPagedBeanTable;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author haiphucnguyen
 */
public class AccountCaseListComp extends RelatedListComp<CaseSearchCriteria> {

    private static final long serialVersionUID = 1L;

    public AccountCaseListComp() {
        super("Cases");

        initUI();
    }

    private void initUI() {
        VerticalLayout contentContainer = (VerticalLayout) bodyContent;
        contentContainer.setSpacing(true);

        Button newBtn = new Button("New Case", new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                fireNewRelatedItem("");
            }
        });
        newBtn.setIcon(new ThemeResource("icons/16/addRecord.png"));
        newBtn.setEnabled(AppContext.canWrite(RolePermissionCollections.CRM_CASE));

        contentContainer.addComponent(newBtn);

        tableItem = new CaseTableDisplay(new String[]{"subject",
                    "priority", "status", "assignUserFullName",
                    "createdtime"}, new String[]{"Subject", "Priority", "Status", "Assigned To",
                    "Date Created"});

        tableItem.addTableListener(new ApplicationEventListener<IPagedBeanTable.TableClickEvent>() {
            @Override
            public Class<? extends ApplicationEvent> getEventType() {
                return IPagedBeanTable.TableClickEvent.class;
            }

            @Override
            public void handle(IPagedBeanTable.TableClickEvent event) {
                SimpleCase cases = (SimpleCase) event.getData();
                if ("subject".equals(event.getFieldName())) {
                    EventBus.getInstance().fireEvent(
                            new CaseEvent.GotoRead(this,
                            cases.getId()));
                }
            }
        });

        contentContainer.addComponent(tableItem);
    }
}
