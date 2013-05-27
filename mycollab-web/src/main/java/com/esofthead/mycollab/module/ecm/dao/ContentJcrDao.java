package com.esofthead.mycollab.module.ecm.dao;

import com.esofthead.mycollab.module.ecm.domain.Content;

public interface ContentJcrDao {
	void saveContent(Content content, String path);

	Content getContent(String path);

	void removeContent(String path);
}
