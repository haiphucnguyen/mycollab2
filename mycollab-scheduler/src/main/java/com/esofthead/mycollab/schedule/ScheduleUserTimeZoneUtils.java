package com.esofthead.mycollab.schedule;

import java.util.Date;

import org.apache.commons.beanutils.PropertyUtils;

import com.esofthead.mycollab.common.domain.AuditChangeItem;
import com.esofthead.mycollab.common.domain.SimpleAuditLog;
import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.core.utils.DateTimeUtils;
import com.esofthead.mycollab.core.utils.ValuedBean;

public class ScheduleUserTimeZoneUtils {
	public static <B extends ValuedBean> B formatDateTimeZone(final B bean,
			String userTimeZone, String... fieldNames) {
		for (String fieldName : fieldNames) {
			try {
				Date date = (Date) PropertyUtils.getProperty(bean, fieldName);
				PropertyUtils.setProperty(bean, fieldName, DateTimeUtils
						.converToDateWithUserTimeZone(date, userTimeZone));
			} catch (Exception e) {
				throw new MyCollabException(e);
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
