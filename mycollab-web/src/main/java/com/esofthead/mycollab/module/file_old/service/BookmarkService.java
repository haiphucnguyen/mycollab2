package com.esofthead.mycollab.module.file_old.service;

public interface BookmarkService {

	/**
	 * 
	 * @param user
	 * @return
	 */
	String getBookmarkOfUser(int accountId, String user);

	/**
	 * 
	 * @param user
	 * @param bookmark
	 */
	void saveBookmark(int accountId, String user, String bookmark);
}
