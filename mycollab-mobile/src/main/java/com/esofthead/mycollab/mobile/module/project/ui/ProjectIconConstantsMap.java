package com.esofthead.mycollab.mobile.module.project.ui;

import java.util.HashMap;
import java.util.Map;

import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.mobile.ui.IconConstants;
import com.esofthead.mycollab.module.project.ProjectTypeConstants;

/**
 * @author MyCollab Ltd.
 *
 * @since 4.5.2
 */
public class ProjectIconConstantsMap {

	private static Map<String, String> constantsMap;

	static {
		constantsMap = new HashMap<String, String>();
		constantsMap.put(ProjectTypeConstants.BUG, IconConstants.PROJECT_BUG);
		constantsMap.put(ProjectTypeConstants.TASK, IconConstants.PROJECT_TASK);
		constantsMap.put(ProjectTypeConstants.TASK_LIST,
				IconConstants.PROJECT_TASK);
		constantsMap.put(ProjectTypeConstants.MILESTONE,
				IconConstants.PROJECT_MILESTONE);
		constantsMap.put(ProjectTypeConstants.MESSAGE,
				IconConstants.PROJECT_MESSAGE);
		constantsMap.put(ProjectTypeConstants.PROJECT,
				IconConstants.PROJECT_DASHBOARD);
	}

	public static String getIcon(String type) {
		String result = constantsMap.get(type);
		if (result == null) {
			throw new MyCollabException("Can not get key: " + type);
		}

		return result;
	}

}
