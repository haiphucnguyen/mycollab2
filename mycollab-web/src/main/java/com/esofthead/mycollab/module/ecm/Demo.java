package com.esofthead.mycollab.module.ecm;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.esofthead.mycollab.module.ecm.dao.ContentJcrDao;
import com.esofthead.mycollab.module.ecm.domain.Content;
import com.esofthead.mycollab.module.ecm.domain.Folder;
import com.esofthead.mycollab.module.ecm.domain.Resource;

public class Demo {
	public static void main(final String[] args) throws Exception {
		final ApplicationContext context = new ClassPathXmlApplicationContext(
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

		final ContentJcrDao jcrDao = context.getBean(ContentJcrDao.class);

		// jcrDao.removeResource("/a/b/d");

		final Folder pageContent = new Folder();
		pageContent.setPath("a/b/d");

		jcrDao.createFolder(pageContent, "baohan");

		final Content content = new Content();
		content.setDescription("AAA");
		content.setPath("a/b/c/example.txt");
		content.setSize(11d);
		content.setTitle("aaa");

		jcrDao.saveContent(content, "nghitran");
		//
		// Resource content = jcrDao.getResource("a/b/d");
		// System.out.println(content.getCreatedUser());
		//
		// System.out.println("NULL: " + jcrDao.getResource("a/b/c"));
		//
		// jcrDao.rename("a/b/d", "a/b/c");
		// System.out.println("NOT NULL: " + jcrDao.getResource("a/b/c"));
		// System.out.println("D NULL: " + jcrDao.getResource("a/b/d"));

		final List<Resource> resources = jcrDao.searchResourcesByName("a");
		System.out.println("Resources: " + resources.size());
		// System.out.println("CONTENT " + content);
		//
		// jcrDao.removeContent("example/a/b");
		// Content content2 = jcrDao.getContent("example/a/b");
		// System.out.println("CONTENT 2" + content2);

	}
}
