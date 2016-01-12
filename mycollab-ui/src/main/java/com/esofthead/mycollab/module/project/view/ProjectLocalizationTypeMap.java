package com.esofthead.mycollab.module.project.view;

import com.esofthead.mycollab.module.project.ProjectTypeConstants;
import com.esofthead.mycollab.module.project.i18n.ProjectTypeI18nEnum;

import java.util.HashMap;
import java.util.Map;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
public class ProjectLocalizationTypeMap {
    private static Map<String, ProjectTypeI18nEnum> typeMap;

    static {
        typeMap = new HashMap<>();
        typeMap.put(ProjectTypeConstants.PROJECT, ProjectTypeI18nEnum.PROJECT_ITEM);
        typeMap.put(ProjectTypeConstants.MESSAGE, ProjectTypeI18nEnum.MESSAGE_ITEM);
        typeMap.put(ProjectTypeConstants.MILESTONE, ProjectTypeI18nEnum.PHASE_ITEM);
        typeMap.put(ProjectTypeConstants.TASK, ProjectTypeI18nEnum.TASK_ITEM);
        typeMap.put(ProjectTypeConstants.BUG, ProjectTypeI18nEnum.BUG_ITEM);
        typeMap.put(ProjectTypeConstants.BUG_COMPONENT, ProjectTypeI18nEnum.COMPONENT_ITEM);
        typeMap.put(ProjectTypeConstants.BUG_VERSION, ProjectTypeI18nEnum.VERSION_ITEM);
        typeMap.put(ProjectTypeConstants.PAGE, ProjectTypeI18nEnum.PAGE_ITEM);
        typeMap.put(ProjectTypeConstants.STANDUP, ProjectTypeI18nEnum.STANDUP_ITEM);
        typeMap.put(ProjectTypeConstants.PROBLEM, ProjectTypeI18nEnum.PROBLEM_ITEM);
        typeMap.put(ProjectTypeConstants.RISK, ProjectTypeI18nEnum.RISK_ITEM);
    }

    public static ProjectTypeI18nEnum getType(String key) {
        ProjectTypeI18nEnum result = typeMap.get(key);
        if (result == null) {
            return null;
        }

        return result;
    }
}
