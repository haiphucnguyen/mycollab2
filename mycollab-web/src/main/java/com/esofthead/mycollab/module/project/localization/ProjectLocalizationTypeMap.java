package com.esofthead.mycollab.module.project.localization;

import java.util.HashMap;
import java.util.Map;

import com.esofthead.mycollab.core.MyCollabException;

public class ProjectLocalizationTypeMap {
	private static Map<String, ProjectTypeI18nEnum> typeMap;

	static {
		typeMap = new HashMap<String, ProjectTypeI18nEnum>();
		typeMap.put("Message", ProjectTypeI18nEnum.MESSAGE_ITEM);
		typeMap.put("Milestone", ProjectTypeI18nEnum.PHASE_ITEM);
		typeMap.put("Task", ProjectTypeI18nEnum.TASK_ITEM);
		typeMap.put("TaskList", ProjectTypeI18nEnum.TASKGROUP_ITEM);
		typeMap.put("Bug", ProjectTypeI18nEnum.BUG_ITEM);
		typeMap.put("Component", ProjectTypeI18nEnum.COMPONENT_ITEM);
		typeMap.put("Version", ProjectTypeI18nEnum.VERSION_ITEM);
		typeMap.put("StandUp", ProjectTypeI18nEnum.STANDUP_ITEM);
		typeMap.put("Problem", ProjectTypeI18nEnum.PROBLEM_ITEM);
		typeMap.put("Risk", ProjectTypeI18nEnum.RISK_ITEM);
	}

	public static ProjectTypeI18nEnum getType(String key) {
		ProjectTypeI18nEnum result = typeMap.get(key);
		if (result == null) {
			throw new MyCollabException("Can not get key: " + key);
		}

		return result;
	}
}
