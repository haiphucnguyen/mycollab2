package com.esofthead.mycollab.common.interceptor.aspect.cache;

import java.util.Arrays;
import java.util.List;

import com.esofthead.mycollab.common.service.MonitorItemService;
import com.esofthead.mycollab.common.service.RelayEmailNotificationService;
import com.esofthead.mycollab.module.ecm.service.ContentActivityLogService;
import com.esofthead.mycollab.module.ecm.service.ExternalDriveService;
import com.esofthead.mycollab.module.ecm.service.ExternalResourceService;
import com.esofthead.mycollab.module.ecm.service.ResourceMover;
import com.esofthead.mycollab.module.ecm.service.ResourceService;
import com.esofthead.mycollab.module.file.service.RawContentServiceFactoryBean;
import com.esofthead.mycollab.module.project.esb.DeleteProjectCommand;
import com.esofthead.mycollab.module.project.esb.DeleteProjectMemberCommand;
import com.esofthead.mycollab.module.tracker.service.BugRelatedItemService;
import com.esofthead.mycollab.module.tracker.service.RelatedBugService;
import com.esofthead.mycollab.module.user.service.UserService;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 * 
 */
public class CacheServiceIgnoreList {
	static List<Class> blacklistCls = Arrays.asList(new Class[] {
			UserService.class, RelayEmailNotificationService.class,
			RelatedBugService.class, MonitorItemService.class,
			BugRelatedItemService.class, RawContentServiceFactoryBean.class,
			ResourceService.class, DeleteProjectCommand.class,
			DeleteProjectMemberCommand.class, ExternalResourceService.class,
			ContentActivityLogService.class, ExternalDriveService.class,
			ResourceMover.class });

	static boolean isInBlackList(Class<?> cls) {
		return blacklistCls.contains(cls);
	}
}
