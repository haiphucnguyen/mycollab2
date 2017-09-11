package com.mycollab.module.project.service.impl;

import com.mycollab.common.ActivityStreamConstants;
import com.mycollab.common.ModuleNameConstants;
import com.mycollab.common.domain.ActivityStreamWithBLOBs;
import com.mycollab.common.service.ActivityStreamService;
import com.mycollab.module.page.domain.Page;
import com.mycollab.module.page.service.PageService;
import com.mycollab.module.project.ProjectTypeConstants;
import com.mycollab.module.project.service.ProjectPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.GregorianCalendar;

@Service
public class ProjectPageServiceImpl implements ProjectPageService {

	@Autowired
	private PageService pageService;

	@Autowired
	private ActivityStreamService activityStreamService;

	@Override
	public void savePage(Page page, String createdUser, Integer projectId, Integer accountId) {
		pageService.savePage(page, createdUser);

		ActivityStreamWithBLOBs activityStream = new ActivityStreamWithBLOBs();
		activityStream.setAction(ActivityStreamConstants.INSTANCE.getACTION_CREATE());
		activityStream.setCreateduser(createdUser);
		activityStream.setCreatedtime(new GregorianCalendar().getTime());
		activityStream.setModule(ModuleNameConstants.INSTANCE.getPRJ());
		activityStream.setNamefield(page.getSubject());
		activityStream.setSaccountid(accountId);
		activityStream.setType(ProjectTypeConstants.INSTANCE.getPAGE());
		activityStream.setTypeid(page.getPath());
		activityStream.setExtratypeid(projectId);
		activityStreamService.save(activityStream);
	}

	@Override
	public Page getPage(String path, String requestedUser) {
		return pageService.getPage(path, requestedUser);
	}
}