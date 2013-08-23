package com.esofthead.mycollab.module.ecm.dao;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;

import com.esofthead.mycollab.test.MyCollabClassRunner;
import com.esofthead.mycollab.test.service.ServiceTest;

@RunWith(MyCollabClassRunner.class)
@ContextConfiguration(locations = { "classpath:META-INF/spring/service-context-test.xml" })
public class ContentJcrDaoTest extends ServiceTest {

//	@Autowired
//	private ContentJcrDao contentJcrDao;
//
//	@Before
//	public void setup() {
//		Content pageContent = new Content();
//		pageContent.setCreatedBy("hainguyen");
//		pageContent.setTitle("page example");
//		pageContent.setDescription("aaa");
//		pageContent.setPath("example/a");
//		contentJcrDao.saveContent(pageContent);
//	}
//
//	@After
//	public void teardown() {
//		contentJcrDao.removeResource("/");
//	}
//
//	@Test
//	public void testGetContent() {
//		Resource content = contentJcrDao.getResource("example/a");
//		Assert.assertNotNull(content);
//	}
//
//	@Test
//	public void testRemoveContent() {
//		contentJcrDao.removeResource("example/a/b");
//		Resource content = contentJcrDao.getResource("example/a/b");
//		Assert.assertNull(content);
//	}

	// public void testSaveOverride() {
	// Content pageContent = new Content();
	// pageContent.setCreatedBy("hainguyen");
	// pageContent.setTitle("page example");
	// pageContent.setDescription("aaa");
	// pageContent.setPath("a/b/xyz.mycollabtext");
	// contentJcrDao.saveContent(pageContent, "example/a/b");
	// }
}
