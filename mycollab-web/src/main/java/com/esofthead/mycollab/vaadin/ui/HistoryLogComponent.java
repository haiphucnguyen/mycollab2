/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.vaadin.ui;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.esofthead.mycollab.common.domain.AuditChangeItem;
import com.esofthead.mycollab.common.domain.SimpleAuditLog;
import com.esofthead.mycollab.common.domain.criteria.AuditLogSearchCriteria;
import com.esofthead.mycollab.common.service.AuditLogService;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.core.utils.DateTimeUtils;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author haiphucnguyen
 */
@SuppressWarnings("serial")
public class HistoryLogComponent extends VerticalLayout {

    public static final String DEFAULT_FIELD = "default";
    public static final String DATE_FIELD = "date";
    public static final String DATETIME_FIELD = "datetime";
    private static Map<String, HistoryFieldFormat> defaultFieldHandlers;

    static {
        defaultFieldHandlers = new HashMap<String, HistoryFieldFormat>();
        defaultFieldHandlers.put(DEFAULT_FIELD, new DefaultHistoryFieldFormat());
        defaultFieldHandlers.put(DATE_FIELD, new DateHistoryFieldFormat());
    }
    protected BeanList<AuditLogService, AuditLogSearchCriteria, SimpleAuditLog> logTable;
    protected Map<String, FieldDisplayHandler> fieldsFormat = new HashMap<String, FieldDisplayHandler>();
    private String module;
    private String type;
    private int typeid;

    public HistoryLogComponent(String module, String type, int typeid) {
        this.module = module;
        this.type = type;
        this.typeid = typeid;

        logTable = new BeanList<AuditLogService, AuditLogSearchCriteria, SimpleAuditLog>(this, AppContext.getSpringBean(AuditLogService.class), HistoryLogRowDisplay.class);
        this.addComponent(logTable);
    }

    @Override
    public void attach() {
        AuditLogSearchCriteria criteria = new AuditLogSearchCriteria();
        criteria.setSaccountid(new NumberSearchField(AppContext.getAccountId()));
        criteria.setModule(new StringSearchField(module));
        criteria.setType(new StringSearchField(type));
        criteria.setTypeid(new NumberSearchField(typeid));
        logTable.setSearchCriteria(criteria);
    }

    public void generateFieldDisplayHandler(String fieldname, String displayName) {
        fieldsFormat.put(fieldname, new FieldDisplayHandler(displayName));
    }

    public void generateFieldDisplayHandler(String fieldname, String displayName, HistoryFieldFormat format) {
        fieldsFormat.put(fieldname, new FieldDisplayHandler(displayName, format));
    }

    public void generateFieldDisplayHandler(String fieldname, String displayName, String formatName) {
        fieldsFormat.put(fieldname, new FieldDisplayHandler(displayName, defaultFieldHandlers.get(formatName)));
    }

    public class HistoryLogRowDisplay implements BeanList.RowDisplayHandler<SimpleAuditLog> {

        @Override
        public Component generateRow(SimpleAuditLog log, int rowIndex) {

            List<AuditChangeItem> changeItems = log.getChangeItems();
            if (changeItems != null && changeItems.size() > 0) {
                CssLayout layout = new CssLayout();
                layout.setWidth("100%");
                layout.setStyleName("activity-stream");

                GridLayout gridLayout = new GridLayout(3, changeItems.size() + 2);
                gridLayout.setWidth("100%");

                int visibleRows = 0;

                String strDate = "";

                for (int i = 0; i < changeItems.size(); i++) {
                    AuditChangeItem item = changeItems.get(i);
                    String fieldName = item.getField();

                    FieldDisplayHandler fieldDisplayHandler = fieldsFormat.get(fieldName);
                    if (fieldDisplayHandler != null) {
                        gridLayout.addComponent(new Label(fieldDisplayHandler.getDisplayName()), 0, visibleRows + 2);
                        gridLayout.addComponent(fieldDisplayHandler.getFormat().formatField(item.getOldvalue()), 1, visibleRows + 2);
                        gridLayout.addComponent(fieldDisplayHandler.getFormat().formatField(item.getNewvalue()), 2, visibleRows + 2);
                        visibleRows++;
                    }

                    if (fieldName.equals("lastupdatedtime")) {
                        strDate = item.getNewvalue();
                    }
                }

                if (visibleRows == 0) {
                    return null;
                } else {
                    HorizontalLayout header = new HorizontalLayout();
                    header.setWidth("100%");
                    header.setSpacing(true);
                    UserLink userLink = new UserLink(log.getPosteduser(), log.getPostedUserFullName(), false);

                    header.addComponent(userLink);
                    header.setComponentAlignment(userLink, Alignment.MIDDLE_LEFT);
                    String formatW3C = "yyyy-MM-dd'T'HH:mm:ss";
                    Label lbDate = new Label("changed " + DateTimeUtils.getStringDateFromNow(DateTimeUtils.getDateByStringWithFormat(strDate, formatW3C)));
                    header.addComponent(lbDate);
                    header.setComponentAlignment(lbDate, Alignment.MIDDLE_LEFT);
                    header.setExpandRatio(lbDate, 1.0f);
                    gridLayout.addComponent(header, 0, 0, 2, 0);

                    gridLayout.addComponent(new Label("<div style=\"font-weight: bold;\">Field</div>", Label.CONTENT_XHTML), 0, 1);
                    gridLayout.addComponent(new Label("<div style=\"font-weight: bold;\">Old Value</div>", Label.CONTENT_XHTML), 1, 1);
                    gridLayout.addComponent(new Label("<div style=\"font-weight: bold;\">New Value</div>", Label.CONTENT_XHTML), 2, 1);

                    gridLayout.setRows(visibleRows + 2);
                    layout.addComponent(gridLayout);
                    return layout;
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

        @SuppressWarnings("unused")
        public void setDisplayName(String displayName) {
            this.displayName = displayName;
        }

        public HistoryFieldFormat getFormat() {
            return format;
        }

        @SuppressWarnings("unused")
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
        	LabelHTMLDisplayWidget lbHtml = new LabelHTMLDisplayWidget(value);
        	lbHtml.setWidth("90%");
            return lbHtml;
        }
    }

    public static class DateHistoryFieldFormat implements HistoryFieldFormat {

        @Override
        public Component formatField(String value) {
        	String formatW3C = "yyyy-MM-dd'T'HH:mm:ss";
            Date formatDate = DateTimeUtils.getDateByStringWithFormat(value, formatW3C);
            return new Label(AppContext.formatDate(formatDate));
        }
    }
}
