package com.esofthead.mycollab.vaadin.ui.formatter;

import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.core.utils.DateTimeUtils;
import com.esofthead.mycollab.core.utils.StringUtils;
import com.esofthead.mycollab.vaadin.AppContext;
import com.hp.gagawa.java.elements.Span;

import java.util.Date;

/**
 * @author MyCollab Ltd
 * @since 5.3.4
 */
public class PrettyDateHistoryFieldFormat implements HistoryFieldFormat {

    @Override
    public String toString(String value) {
        return toString(value, true, AppContext.getMessage(GenericI18Enum.FORM_EMPTY));
    }

    @Override
    public String toString(String value, Boolean displayAsHtml, String msgIfBlank) {
        if (StringUtils.isNotBlank(value)) {
            Date formatDate = DateTimeUtils.parseDateByW3C(value);
            if (displayAsHtml) {
                Span lbl = new Span().appendText(AppContext.formatPrettyTime(formatDate));
                lbl.setTitle(AppContext.formatDate(formatDate));
                return lbl.write();
            } else {
                return AppContext.formatDate(formatDate);
            }
        } else {
            return msgIfBlank;
        }
    }
}
