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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author haiphucnguyen
 */
public class HistoryLogWindow extends Window {

    protected BeanList<AuditLogService, AuditLogSearchCriteria, SimpleAuditLog> logTable;
    protected Map<String, FieldDisplayHandler> fieldsFormat = new HashMap<String, FieldDisplayHandler>();

    private String module;
    private String type;
    private int typeid;
            
    public HistoryLogWindow(String module, String type, int typeid) {
        super("Change Log");
        this.setWidth("700px");

        this.module = module;
        this.type = type;
        this.typeid = typeid;
    }

    @Override
    public void attach() {
        super.attach();
        logTable = new BeanList<AuditLogService, AuditLogSearchCriteria, SimpleAuditLog>(this, AppContext.getSpringBean(AuditLogService.class), HistoryLogRowDisplay.class);
        AuditLogSearchCriteria criteria = new AuditLogSearchCriteria();
        criteria.setSaccountid(new NumberSearchField(AppContext.getAccountId()));
        criteria.setModule(new StringSearchField(module));
        criteria.setType(new StringSearchField(type));
        criteria.setTypeid(new NumberSearchField(typeid));
        logTable.setSearchCriteria(criteria);
        this.addComponent(logTable);
    }
    
    

    public void generateFieldDisplayHandler(String fieldname, String displayName) {
        fieldsFormat.put(fieldname, new FieldDisplayHandler(displayName));
    }

    public void generateFieldDisplayHandler(String fieldname, String displayName, HistoryFieldFormat format) {
        fieldsFormat.put(fieldname, new FieldDisplayHandler(displayName, format));
    }

    public class HistoryLogRowDisplay implements BeanList.RowDisplayHandler<SimpleAuditLog> {

        @Override
        public Component generateRow(SimpleAuditLog log, int rowIndex) {

            List<AuditChangeItem> changeItems = log.getChangeItems();
            if (changeItems != null && changeItems.size() > 0) {
                GridLayout gridLayout = new GridLayout(3, changeItems.size() + 2);
                gridLayout.setWidth("100%");

                int visibleRows = 0;

                for (int i = 0; i < changeItems.size(); i++) {
                    AuditChangeItem item = changeItems.get(i);
                    String fieldName = item.getField();
                    
                    System.out.print("Field name: " + fieldName + " " + fieldsFormat.size());

                    FieldDisplayHandler fieldDisplayHandler = fieldsFormat.get(fieldName);
                    if (fieldDisplayHandler != null) {
                        gridLayout.addComponent(new Label(fieldDisplayHandler.getDisplayName()), 0, visibleRows + 2);
                        gridLayout.addComponent(fieldDisplayHandler.getFormat().formatField(item.getOldvalue()), 1, visibleRows + 2);
                        gridLayout.addComponent(fieldDisplayHandler.getFormat().formatField(item.getNewvalue()), 2, visibleRows + 2);
                        visibleRows++;
                    }
                }

                if (visibleRows == 0) {
                    return null;
                } else {
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

                    gridLayout.setRows(visibleRows + 2);
                    return gridLayout;
                }

            } else {
                return null;
            }

        }
    }

    /**
     *
     */
    private static class FieldDisplayHandler {

        private String displayName;
        private HistoryFieldFormat format;

        public FieldDisplayHandler(String displayName) {
            this(displayName, new DefaultHistoryFieldFormat());
        }

        public FieldDisplayHandler(String displayName, HistoryFieldFormat format) {
            this.displayName = displayName;
            this.format = format;
        }

        public String getDisplayName() {
            return displayName;
        }

        public void setDisplayName(String displayName) {
            this.displayName = displayName;
        }

        public HistoryFieldFormat getFormat() {
            return format;
        }

        public void setFormat(HistoryFieldFormat format) {
            this.format = format;
        }
    }

    /**
     *
     */
    public static interface HistoryFieldFormat {

        Component formatField(String value);
    }

    /**
     *
     */
    public static class DefaultHistoryFieldFormat implements HistoryFieldFormat {

        @Override
        public Component formatField(String value) {
            return new Label(value);
        }
    }
}
