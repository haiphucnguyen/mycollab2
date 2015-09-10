package com.esofthead.mycollab.core.utils;

import org.joda.time.LocalDate;

import java.util.Date;

/**
 * @author MyCollab Ltd
 * @since 5.1.3
 */
public class JodaTimeUtils {

    public static Date convertJodaToJavaDate(LocalDate date) {
        return (date != null) ? date.toDate() : null;
    }
}
