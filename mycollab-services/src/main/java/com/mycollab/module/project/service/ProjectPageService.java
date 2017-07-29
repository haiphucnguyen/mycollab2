package com.mycollab.module.project.service;

import com.mycollab.db.persistence.service.IService;
import com.mycollab.module.page.domain.Page;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.5.6
 *
 */
public interface ProjectPageService extends IService {
	/**
	 * 
	 * @param page
	 * @param createdUser
	 * @param projectId
	 * @param accountId
	 */
	void savePage(Page page, String createdUser, Integer projectId,
			Integer accountId);

	/**
	 * 
	 * @param path
	 * @param requestedUser
	 * @return
	 */
	Page getPage(String path, String requestedUser);
}
