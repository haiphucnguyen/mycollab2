package com.esofthead.mycollab.schedule;

import java.util.Date;

import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esofthead.mycollab.common.domain.AuditChangeItem;
import com.esofthead.mycollab.common.domain.SimpleAuditLog;
import com.esofthead.mycollab.core.utils.DateTimeUtils;
import com.esofthead.mycollab.core.utils.ValuedBean;

public class ScheduleUserTimeZoneUtils {
	private static Logger log = LoggerFactory
			.getLogger(ScheduleUserTimeZoneUtils.class);

	public static <B extends ValuedBean> B formatDateTimeZone(final B bean,
			String userTimeZone, String... fieldNames) {
		for (String fieldName : fieldNames) {
			Date date = null;
			try {
				date = (Date) PropertyUtils.getProperty(bean, fieldName);
				Date dateAssociateTz = DateTimeUtils
						.converToDateWithUserTimeZone(date, userTimeZone);
				if (dateAssociateTz != null) {
					PropertyUtils.setProperty(bean, fieldName, dateAssociateTz);
				}

			} catch (Exception e) {
				log.error(
						"Error while convert time from date {} with timezone {}",
						date, userTimeZone);
			}
		}
		return bean;
	}

	public static SimpleAuditLog formatDate(final SimpleAuditLog auditLog,
			String userTimeZone, String... fieldDateNames) {
		for (AuditChangeItem item : auditLog.getChangeItems()) {
			for (String fieldDateName : fieldDateNames) {
				if (item.getField().equals(fieldDateName)) {
					Date dateNewValue = DateTimeUtils
							.convertDateByFormatW3C(item.getNewvalue());
					Date dateOldValue = DateTimeUtils
							.convertDateByFormatW3C(item.getOldvalue());
					if (dateNewValue == null) {
						item.setNewvalue("");
					} else {
						item.setNewvalue(DateTimeUtils
								.converToStringWithUserTimeZone(dateNewValue,
										userTimeZone));
					}
					if (dateOldValue == null) {
						item.setOldvalue("");
					} else {
						item.setOldvalue(DateTimeUtils
								.converToStringWithUserTimeZone(dateOldValue,
										userTimeZone));
					}
				}
			}
		}
		return auditLog;
	}
}
