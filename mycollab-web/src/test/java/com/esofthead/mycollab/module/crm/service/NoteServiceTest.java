package com.esofthead.mycollab.module.crm.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.esofthead.mycollab.common.domain.SimpleComment;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.module.crm.domain.Note;
import com.esofthead.mycollab.module.crm.domain.SimpleNote;
import com.esofthead.mycollab.module.crm.domain.criteria.NoteSearchCriteria;
import com.esofthead.mycollab.test.DataSet;
import com.esofthead.mycollab.test.EngroupClassRunner;
import com.esofthead.mycollab.test.ServiceTest;

@RunWith(EngroupClassRunner.class)
@ContextConfiguration(locations = {"classpath:META-INF/spring/service-context-test.xml"})
public class NoteServiceTest extends ServiceTest{

    @Autowired
    protected NoteService noteService;

    @DataSet
    @Test
    public void testGetTotalCount() {
        NoteSearchCriteria criteria = getCriteria();
        Assert.assertEquals(1, noteService.getTotalCount(criteria));
    }

    @SuppressWarnings("unchecked")
    @DataSet
    @Test
    public void testSearch() {
        NoteSearchCriteria criteria = getCriteria();

        List<SimpleNote> noteList = noteService
                .findPagableListByCriteria(new SearchRequest<NoteSearchCriteria>(
                criteria, 0, Integer.MAX_VALUE));

        Assert.assertEquals(1, noteList.size());

        SimpleNote note = noteList.get(0);
        Assert.assertEquals(2, note.getAttachments().size());
        Assert.assertEquals(2, note.getComments().size());
        
        SimpleComment comment = note.getComments().get(0);
        Assert.assertEquals(1, comment.getAttachments().size());
    }

    @DataSet
    @Test
    public void testSaveNote() {
        Note note = new Note();
        note.setSubject("subject");
        note.setSaccountid(1);
        noteService.saveWithSession(note, "admin");
        Assert.assertNotNull(note.getId());
    }

    private NoteSearchCriteria getCriteria() {
        NoteSearchCriteria criteria = new NoteSearchCriteria();
        criteria.setSaccountid(new NumberSearchField(SearchField.AND, 1));
        return criteria;
    }
}
