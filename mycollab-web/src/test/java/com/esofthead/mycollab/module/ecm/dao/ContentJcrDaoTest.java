package com.esofthead.mycollab.module.ecm.dao;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.esofthead.mycollab.module.ecm.domain.Content;
import com.esofthead.mycollab.test.EngroupClassRunner;
import com.esofthead.mycollab.test.ServiceTest;

@RunWith(EngroupClassRunner.class)
@ContextConfiguration(locations = { "classpath:META-INF/spring/service-test-context.xml" })
public class ContentJcrDaoTest extends ServiceTest {

	@Autowired
	private ContentJcrDao contentJcrDao;

	@Before
	public void setup() {
		Content pageContent = new Content();
		pageContent.setCreatedBy("hainguyen");
		pageContent.setTitle("page example");
		pageContent.setDescription("aaa");
		pageContent.setPath("a/b/xyz.mycollabtext");
		contentJcrDao.saveContent(pageContent, "example/a/b");
	}

	@After
	public void teardown() {
		contentJcrDao.removeContent("/");
	}

	@Test
	public void testGetContent() {
		Content content = contentJcrDao.getContent("example/a/b");
		Assert.assertNotNull(content);
	}

	@Test
	public void testRemoveContent() {
		contentJcrDao.removeContent("example/a/b");
		Content content = contentJcrDao.getContent("example/a/b");
		Assert.assertNull(content);
	}

	public void testSaveOverride() {
		Content pageContent = new Content();
		pageContent.setCreatedBy("hainguyen");
		pageContent.setTitle("page example");
		pageContent.setDescription("aaa");
		pageContent.setPath("a/b/xyz.mycollabtext");
		contentJcrDao.saveContent(pageContent, "example/a/b");
	}
}
