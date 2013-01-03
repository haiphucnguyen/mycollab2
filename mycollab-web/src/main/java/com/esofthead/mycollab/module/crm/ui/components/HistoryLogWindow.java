/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.crm.ui.components;

import com.esofthead.mycollab.common.domain.AuditChangeItem;
import com.esofthead.mycollab.common.domain.SimpleAuditLog;
import com.esofthead.mycollab.common.domain.criteria.AuditLogSearchCriteria;
import com.esofthead.mycollab.common.service.AuditLogService;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.vaadin.ui.BeanList;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Window;
import java.util.List;

/**
 *
 * @author haiphucnguyen
 */
public class HistoryLogWindow extends Window {

    private BeanList<AuditLogService, AuditLogSearchCriteria, SimpleAuditLog> logTable;

    public HistoryLogWindow(String module, String type, int typeid) {
        super("Change Log");
        this.setWidth("700px");

        logTable = new BeanList<AuditLogService, AuditLogSearchCriteria, SimpleAuditLog>(AppContext.getSpringBean(AuditLogService.class), HistoryLogRowDisplay.class);
        AuditLogSearchCriteria criteria = new AuditLogSearchCriteria();
        criteria.setSaccountid(new NumberSearchField(AppContext.getAccountId()));
        criteria.setModule(new StringSearchField(module));
        criteria.setType(new StringSearchField(type));
        criteria.setTypeid(new NumberSearchField(typeid));
        logTable.setSearchCriteria(criteria);
        this.addComponent(logTable);
    }

    public static class HistoryLogRowDisplay implements BeanList.RowDisplayHandler<SimpleAuditLog> {

        @Override
        public Component generateRow(SimpleAuditLog log, int rowIndex) {
            List<AuditChangeItem> changeItems = log.getChangeItems();
            if (changeItems != null && changeItems.size() > 0) {
                GridLayout gridLayout = new GridLayout(3, changeItems.size() + 2);
                gridLayout.setWidth("100%");

                HorizontalLayout header = new HorizontalLayout();
                header.setWidth("100%");
                Button userLink = new Button(log.getPostedUserFullName(), new Button.ClickListener() {
                    @Override
                    public void buttonClick(ClickEvent event) {
                        throw new UnsupportedOperationException("Not supported yet.");
                    }
                });

                userLink.setStyleName("link");
                header.addComponent(userLink);
                header.addComponent(new Label("made changes - date here"));
                gridLayout.addComponent(header, 0, 0, 2, 0);

                gridLayout.addComponent(new Label("Field"), 0, 1);
                gridLayout.addComponent(new Label("Old Value"), 1, 1);
                gridLayout.addComponent(new Label("New Value"), 2, 1);

                for (int i = 0; i < changeItems.size(); i++) {
                    AuditChangeItem item = changeItems.get(i);
                    gridLayout.addComponent(new Label(item.getField()), 0, i + 2);
                    gridLayout.addComponent(new Label(item.getOldvalue()), 1, i + 2);
                    gridLayout.addComponent(new Label(item.getNewvalue()), 2, i + 2);
                }
                return gridLayout;
            } else {
                return new HorizontalLayout();
            }

        }
    }
}
