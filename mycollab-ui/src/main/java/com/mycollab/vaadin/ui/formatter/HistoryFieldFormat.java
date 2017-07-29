package com.mycollab.vaadin.ui.formatter;

/**
 * @author MyCollab Ltd.
 * @since 4.0
 */
public interface HistoryFieldFormat {

    /**
     * @param value
     * @return
     */
    String toString(String value);

    /**
     *
     * @param value
     * @param msgIfBlank
     * @return
     */
    String toString(String value, Boolean displayAsHtml, String msgIfBlank);
}
