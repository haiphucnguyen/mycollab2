package com.esofthead.mycollab.vaadin.ui.formatter;

import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.core.utils.StringUtils;
import com.esofthead.mycollab.vaadin.AppContext;

/**
 * @author MyCollab Ltd
 * @since 5.3.4
 */
public final class TrimHtmlHistoryFieldFormat implements HistoryFieldFormat {
    @Override
    public String toString(String value) {
        return toString(value, true, AppContext.getMessage(GenericI18Enum.FORM_EMPTY));
    }

    @Override
    public String toString(String value, Boolean displayAsHtml, String msgIfBlank) {
        String content;
        if (StringUtils.isNotBlank(value)) {
            content = StringUtils.trimHtmlTags(value);
            content = (content.length() > 150) ? (content.substring(0, 150) + "...") : content;
        } else {
            content = msgIfBlank;
        }

        return content;
    }
}
