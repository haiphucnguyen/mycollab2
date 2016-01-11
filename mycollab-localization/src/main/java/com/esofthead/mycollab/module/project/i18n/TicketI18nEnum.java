package com.esofthead.mycollab.module.project.i18n;

import ch.qos.cal10n.BaseName;
import ch.qos.cal10n.Locale;
import ch.qos.cal10n.LocaleData;

/**
 * @author MyCollab Ltd
 * @since 5.2.5
 */
@BaseName("localization/project-ticket")
@LocaleData(value = { @Locale("en-US"), @Locale("ja-JP") }, defaultCharset = "UTF-8")
public enum TicketI18nEnum {
    M_TICKET_NUM
}
