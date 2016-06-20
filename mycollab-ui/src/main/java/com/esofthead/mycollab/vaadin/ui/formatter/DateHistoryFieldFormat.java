package com.esofthead.mycollab.vaadin.ui.formatter;

import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.core.utils.DateTimeUtils;
import com.esofthead.mycollab.core.utils.StringUtils;
import com.esofthead.mycollab.vaadin.AppContext;

import java.util.Date;

/**
 * @author MyCollab Ltd
 * @since 5.3.4
 */
public class DateHistoryFieldFormat implements HistoryFieldFormat {

    @Override
    public String toString(String value) {
        return toString(value, true, AppContext.getMessage(GenericI18Enum.FORM_EMPTY));
    }

    @Override
    public String toString(String value, Boolean displayAsHtml, String msgIfBlank) {
        String content;
        if (StringUtils.isNotBlank(value)) {
            Date formatDate = DateTimeUtils.parseDateByW3C(value);
            content = AppContext.formatDate(formatDate);
        } else {
            content = msgIfBlank;
        }
        return content;
    }
}
