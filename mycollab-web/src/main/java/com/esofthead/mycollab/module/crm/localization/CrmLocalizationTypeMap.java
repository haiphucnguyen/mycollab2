package com.esofthead.mycollab.module.crm.localization;

import java.util.HashMap;
import java.util.Map;

import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.module.crm.CrmTypeConstants;

public class CrmLocalizationTypeMap {
	private static Map<String, CrmTypeI18nEnum> typeMap;

	static {
		typeMap = new HashMap<String, CrmTypeI18nEnum>();
		typeMap.put(CrmTypeConstants.ACCOUNT, CrmTypeI18nEnum.ACCOUNT);
		typeMap.put(CrmTypeConstants.CALL, CrmTypeI18nEnum.CALL);
		typeMap.put(CrmTypeConstants.CAMPAIGN, CrmTypeI18nEnum.CAMPAIGN);
		typeMap.put(CrmTypeConstants.CASE, CrmTypeI18nEnum.CASES);
		typeMap.put(CrmTypeConstants.CONTACT, CrmTypeI18nEnum.CONTACT);
		typeMap.put(CrmTypeConstants.LEAD, CrmTypeI18nEnum.LEAD);
		typeMap.put(CrmTypeConstants.MEETING, CrmTypeI18nEnum.MEETING);
		typeMap.put(CrmTypeConstants.OPPORTUNITY, CrmTypeI18nEnum.OPPORTUNITY);
		typeMap.put(CrmTypeConstants.TASK, CrmTypeI18nEnum.TASK);
	}

	public static CrmTypeI18nEnum getType(String key) {
		CrmTypeI18nEnum result = typeMap.get(key);
		if (result == null) {
			throw new MyCollabException("CAN NOT GET VALUE FOR KEY: " + key);
		}

		return result;
	}
}
