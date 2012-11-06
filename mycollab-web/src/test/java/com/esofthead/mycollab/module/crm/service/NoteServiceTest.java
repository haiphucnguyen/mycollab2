package com.esofthead.mycollab.module.crm.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.module.crm.domain.Note;
import com.esofthead.mycollab.module.crm.domain.criteria.NoteSearchCriteria;
import com.esofthead.mycollab.test.DataSet;
import com.esofthead.mycollab.test.EngroupClassRunner;

@RunWith(EngroupClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:META-INF/spring/audit-context.xml",
		"classpath:META-INF/spring/file-context.xml",
		"classpath:META-INF/spring/crm-context.xml",
		"classpath:META-INF/spring/datasource-test-context.xml" })
public class NoteServiceTest {

	@Autowired
	protected NoteService noteService;

	@DataSet
	@Test
	public void testGetTotalCount() {
		NoteSearchCriteria criteria = getCriteria();
		Assert.assertEquals(1, noteService.getTotalCount(criteria));
	}

	@DataSet
	@Test
	public void testSearch() {
		NoteSearchCriteria criteria = getCriteria();

		Assert.assertEquals(
				1,
				noteService.findPagableListByCriteria(criteria, 0,
						Integer.MAX_VALUE).size());
	}
	
	@DataSet
	@Test
	public void testSaveNote() {
		Note note = new Note();
		note.setSubject("subject");
		note.setSaccountid(1);
		int noteid = noteService.insertNoteExt(note);
		System.out.println("Noteid: " + note.getId() + "--" + noteid);
		Assert.assertNotNull(note.getId());
	}

	private NoteSearchCriteria getCriteria() {
		NoteSearchCriteria criteria = new NoteSearchCriteria();
		criteria.setSaccountid(new NumberSearchField(SearchField.AND, 1));
		return criteria;
	}
}
