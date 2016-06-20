package com.esofthead.mycollab.vaadin.ui.formatter;

import com.esofthead.mycollab.common.domain.AuditChangeItem;
import com.esofthead.mycollab.vaadin.AppContext;
import com.hp.gagawa.java.elements.Li;

/**
 * @author MyCollab Ltd
 * @since 5.3.4
 */
public class DefaultFieldDisplayHandler {
    private Enum displayName;
    private HistoryFieldFormat format;

    public DefaultFieldDisplayHandler(Enum displayName) {
        this(displayName, new DefaultHistoryFieldFormat());
    }

    public DefaultFieldDisplayHandler(Enum displayName, HistoryFieldFormat format) {
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
