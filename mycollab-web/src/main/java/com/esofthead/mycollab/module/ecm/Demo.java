package com.esofthead.mycollab.module.ecm;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.esofthead.mycollab.module.ecm.dao.ContentJcrDao;
import com.esofthead.mycollab.module.ecm.domain.Content;
import com.esofthead.mycollab.module.ecm.domain.Folder;
import com.esofthead.mycollab.module.ecm.domain.Resource;

public class Demo {
	public static void main(String[] args) throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"META-INF/spring/common-context.xml",
				"META-INF/spring/core-context.xml",
				"META-INF/spring/crm-context.xml",
				"META-INF/spring/datasource-context.xml",
				"META-INF/spring/ecm-context.xml",
				"META-INF/spring/file-context.xml",
				"META-INF/spring/project-context.xml",
				"META-INF/spring/rest-context.xml",
				"META-INF/spring/tracker-context.xml",
				"META-INF/spring/user-context.xml");

		ContentJcrDao jcrDao = context.getBean(ContentJcrDao.class);
		jcrDao.removeResource("a/b/c");
		jcrDao.removeResource("a");
		jcrDao.removeResource("b");
		jcrDao.removeResource("c");
		
		Folder pageContent = new Folder();
		pageContent.setPath("a/b/c");
//		pageContent.setTitle("AAA");
//		pageContent.setDescription("ccc");

		jcrDao.createFolder(pageContent);

		Resource content = jcrDao.getResource("a/b");
		System.out.println(content);
		// System.out.println("CONTENT " + content);
		//
		// jcrDao.removeContent("example/a/b");
		// Content content2 = jcrDao.getContent("example/a/b");
		// System.out.println("CONTENT 2" + content2);

	}
}
