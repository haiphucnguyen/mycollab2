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
package com.esofthead.mycollab.module.file_old.service;

import java.util.Collection;

import com.esofthead.mycollab.module.file_old.domain.Content;
import com.esofthead.mycollab.module.file_old.domain.ContentSearchResult;
import com.esofthead.mycollab.module.file_old.domain.IdentityPermission;
import com.esofthead.mycollab.module.file_old.domain.SimpleFile;
import com.esofthead.mycollab.module.file_old.domain.criteria.ContentSearchCriteria;

/**
 * 
 */
public interface FileSystemService {
	/**
	 * 
	 * @param username
	 * @param filePath
	 * @return
	 */
	Content getContentByPath(String username, String filePath);

	/**
	 * 
	 * @param username
	 * @param contentPath
	 * @return
	 */
	Content findByPath(String username, String contentPath);

	/**
	 * 
	 * @param username
	 * @param content
	 * @param isTrack
	 */
	void save(String username, Content content, boolean isTrack);

	/**
	 * 
	 * @param username
	 * @param criteria
	 * @return
	 */
	Collection<ContentSearchResult> findByCriteria(String username,
			ContentSearchCriteria criteria);

	/**
	 * 
	 * @param username
	 * @param content
	 * @param isTrack
	 */
	void remove(String username, Content content, boolean isTrack);

	/**
	 * 
	 * @param username
	 * @param contentPath
	 * @param isTrack
	 */
	void removeByPath(String username, String contentPath, boolean isTrack);

	/**
	 * 
	 * @param username
	 * @param filePath
	 * @return
	 */
	Collection<Content> listContents(String username, String filePath);

	/**
	 * 
	 * @param username
	 * @param owner
	 * @param contextBase
	 * @param folderName
	 */
	void addFolder(String username, String owner, String contextBase,
			String folderName);

	/**
	 * 
	 * @param username
	 * @param file
	 */
	void updateFileMetaData(String username, SimpleFile file);

	/**
	 * 
	 * @param tag
	 * @return
	 */
	Collection<Content> getContentsByTag(String username, String tag);

	/**
	 * 
	 * @param path
	 * @param permissions
	 */
	void savePermissions(String username, String path,
			Collection<IdentityPermission> permissions);

	/**
	 * 
	 * @param path
	 * @return
	 */
	Collection<IdentityPermission> getContentPermissions(String username,
			String path);
}
