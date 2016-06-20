/**
 * This file is part of mycollab-ui.
 *
 * mycollab-ui is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-ui is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-ui.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.vaadin.ui.formatter;

import com.esofthead.mycollab.common.domain.AuditChangeItem;
import com.esofthead.mycollab.vaadin.AppContext;
import com.hp.gagawa.java.elements.Li;

import java.util.HashMap;
import java.util.Map;

/**
 * @author MyCollab Ltd.
 * @since 4.3.3
 */
public class FieldGroupFormatter {

    private static Map<String, HistoryFieldFormat> defaultFieldHandlers;

    public static final String DEFAULT_FIELD = "default";
    public static final String DATE_FIELD = "date";
    public static final String DATETIME_FIELD = "datetime";
    public static final String PRETTY_DATE_FIELD = "prettydate";
    public static final String PRETTY_DATE_TIME_FIELD = "prettydatetime";
    public static final String CURRENCY_FIELD = "currency";
    public static final String TRIM_HTMLS = "trim_htmls";

    protected Map<String, FieldDisplayHandler> fieldsFormat = new HashMap<>();

    static {
        defaultFieldHandlers = new HashMap<>();
        defaultFieldHandlers.put(DEFAULT_FIELD, new DefaultHistoryFieldFormat());
        defaultFieldHandlers.put(DATE_FIELD, new DateHistoryFieldFormat());
        defaultFieldHandlers.put(DATETIME_FIELD, new DateTimeHistoryFieldFormat());
        defaultFieldHandlers.put(PRETTY_DATE_FIELD, new PrettyDateHistoryFieldFormat());
        defaultFieldHandlers.put(PRETTY_DATE_TIME_FIELD, new PrettyDateTimeHistoryFieldFormat());
        defaultFieldHandlers.put(CURRENCY_FIELD, new CurrencyHistoryFieldFormat());
        defaultFieldHandlers.put(TRIM_HTMLS, new TrimHtmlHistoryFieldFormat());
    }

    public void generateFieldDisplayHandler(String fieldName, Enum displayName) {
        fieldsFormat.put(fieldName, new FieldDisplayHandler(displayName));
    }

    public void generateFieldDisplayHandler(String fieldName, Enum displayName, HistoryFieldFormat format) {
        fieldsFormat.put(fieldName, new FieldDisplayHandler(displayName, format));
    }

    public void generateFieldDisplayHandler(String fieldName, Enum displayName, String formatName) {
        fieldsFormat.put(fieldName, new FieldDisplayHandler(displayName, defaultFieldHandlers.get(formatName)));
    }

    public FieldDisplayHandler getFieldDisplayHandler(String fieldName) {
        return fieldsFormat.get(fieldName);
    }

    public static class FieldDisplayHandler {
        private Enum displayName;
        private HistoryFieldFormat format;

        public FieldDisplayHandler(Enum displayName) {
            this(displayName, new DefaultHistoryFieldFormat());
        }

        public FieldDisplayHandler(Enum displayName, HistoryFieldFormat format) {
            this.displayName = displayName;
            this.format = format;
        }

        public Enum getDisplayName() {
            return displayName;
        }

        public HistoryFieldFormat getFormat() {
            return format;
        }

        public String generateLogItem(AuditChangeItem item) {
            Li li = new Li().appendText(AppContext.getMessage(displayName) + ": ")
                    .appendText(format.toString(item.getOldvalue()))
                    .appendText("&nbsp; &rarr; &nbsp; ")
                    .appendText(format.toString(item.getNewvalue()));
            return li.write();
        }
    }

}