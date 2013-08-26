package com.esofthead.mycollab.common.interceptor.aspect.cache;

import java.util.Arrays;
import java.util.List;

import com.esofthead.mycollab.common.service.MonitorItemService;
import com.esofthead.mycollab.common.service.RelayEmailNotificationService;
import com.esofthead.mycollab.module.ecm.service.ContentActivityLogService;
import com.esofthead.mycollab.module.ecm.service.ExternalResourceService;
import com.esofthead.mycollab.module.ecm.service.ResourceService;
import com.esofthead.mycollab.module.file.service.RawContentServiceFactoryBean;
import com.esofthead.mycollab.module.project.service.esb.ProjectDeleteListener;
import com.esofthead.mycollab.module.project.service.esb.ProjectMemberDeleteListener;
import com.esofthead.mycollab.module.tracker.service.BugRelatedItemService;

public class CacheServiceIgnoreList {
	static List<Class> blacklistCls = Arrays.asList(new Class[] {
			RelayEmailNotificationService.class, MonitorItemService.class,
			BugRelatedItemService.class, RawContentServiceFactoryBean.class,
			ResourceService.class, ProjectDeleteListener.class,
			ProjectMemberDeleteListener.class, ExternalResourceService.class,
			ContentActivityLogService.class });

	static boolean isInBlackList(Class cls) {
		return blacklistCls.contains(cls);
	}
}
