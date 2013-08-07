package com.esofthead.mycollab.module.ecm;

import java.io.InputStream;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.esofthead.mycollab.module.ecm.dao.ContentJcrDao;
import com.esofthead.mycollab.module.ecm.domain.Content;
import com.esofthead.mycollab.module.ecm.domain.Folder;
import com.esofthead.mycollab.module.ecm.domain.Resource;
import com.esofthead.mycollab.module.file.service.RawContentService;

public class Demo {
	public static void main(final String[] args) throws Exception {
		final ApplicationContext context = new ClassPathXmlApplicationContext(
				"META-INF/spring/common-context.xml",
				"META-INF/spring/core-context.xml",
				"META-INF/spring/crm-context.xml",
				"META-INF/spring/datasource-context.xml",
				"META-INF/spring/ecm-context.xml",
				"META-INF/spring/project-context.xml",
				"META-INF/spring/rest-context.xml",
				"META-INF/spring/tracker-context.xml",
				"META-INF/spring/user-context.xml",
				"META-INF/spring/migration-context.xml");

//		final ContentJcrDao jcrDao = context.getBean(ContentJcrDao.class);
//		RawContentService rawContentService = context
//				.getBean(RawContentService.class);
//
//		String[] paths = new String[] {
//				"1/.attachments/crm-note/2/da1589d79676690909670cfe5be92b02.PDF",
//				"1/.attachments/crm-note/2/default_user_avatar_48_48.png",
//				"1/.attachments/crm-note/17/1-3.jpg"};
//		for (String path : paths) {
//			InputStream contentStream = rawContentService.getContent(path);
//			Content content = new Content();
//			content.setPath(path);
//			content.setSize(Double.parseDouble(contentStream.available() + ""));
//			content.setTitle("");
//			content.setDescription("");
//			jcrDao.saveContent(content, "");
//		}

	}
}
