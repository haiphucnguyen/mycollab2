/**
 * Engroup - Enterprise Groupware Platform
 * Copyright (C) 2007-2009 eSoftHead Company <engroup@esofthead.com>
 * http://www.esofthead.com
 *
 *  Licensed under the GPL, Version 3.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.gnu.org/licenses/gpl.html
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.esofthead.mycollab.module.file.service;

import java.util.Collection;

import javax.persistence.Version;

import com.esofthead.mycollab.module.file.domain.Content;
import com.esofthead.mycollab.module.file.domain.ContentSearchResult;
import com.esofthead.mycollab.module.file.domain.FileItem;
import com.esofthead.mycollab.module.file.domain.IdentityPermission;
import com.esofthead.mycollab.module.file.domain.SimpleFile;
import com.esofthead.mycollab.module.file.domain.criteria.ContentSearchCriteria;

/**
 * 
 */
public interface FileSystemService {
	/**
	 * 
	 * @param userSessionId
	 * @param filePath
	 * @return
	 */
	Content getContentByPath(String userSessionId, String filePath);

	/**
	 * 
	 * @param userSessionId
	 * @param contentPath
	 * @return
	 */
	Content findByPath(String userSessionId, String contentPath);

	/**
	 * 
	 * @param userSessionId
	 * @param content
	 * @param isTrack
	 */
	void save(String userSessionId, Content content, boolean isTrack);

	/**
	 * 
	 * @param userSessionId
	 * @param criteria
	 * @return
	 */
	Collection<ContentSearchResult> findByCriteria(String userSessionId,
			ContentSearchCriteria criteria);

	/**
	 * 
	 * @param userSessionId
	 * @param content
	 * @param isTrack
	 */
	void remove(String userSessionId, Content content, boolean isTrack);

	/**
	 * 
	 * @param userSessionId
	 * @param contentPath
	 * @param isTrack
	 */
	void removeByPath(String userSessionId, String contentPath, boolean isTrack);

	/**
	 * 
	 * @param userSessionId
	 * @param filePath
	 * @return
	 */
	Collection<Version> getVersionHistory(String userSessionId, String filePath);

	/**
	 * 
	 * @param userSessionId
	 * @param filePath
	 * @param version
	 * @return
	 */
	SimpleFile getSimpleFileByVersion(String userSessionId, String filePath,
			String version);

	/**
	 * 
	 * @param userSessionId
	 * @param filePath
	 * @param version
	 * @return
	 */
	FileItem getFileByVersion(String userSessionId, String filePath,
			String version);

	/**
	 * 
	 * @param userSessionId
	 * @param filePath
	 * @return
	 */
	Collection<Content> listContents(String userSessionId, String filePath);

	/**
	 * 
	 * @param userSessionId
	 * @param owner
	 * @param contextBase
	 * @param folderName
	 */
	void addFolder(String userSessionId, String owner, String contextBase,
			String folderName);

	/**
	 * 
	 * @param userSessionId
	 * @param file
	 */
	void updateFileMetaData(String userSessionId, SimpleFile file);

	/**
	 * 
	 * @param tag
	 * @return
	 */
	Collection<Content> getContentsByTag(String userSessionId, String tag);

	/**
	 * 
	 * @param path
	 * @param permissions
	 */
	void savePermissions(String userSessionId, String path,
			Collection<IdentityPermission> permissions);

	/**
	 * 
	 * @param path
	 * @return
	 */
	Collection<IdentityPermission> getContentPermissions(String userSessionId,
			String path);
}
