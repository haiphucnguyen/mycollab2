package com.esofthead.mycollab.module.crm.service.ibatis;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.esofthead.mycollab.core.persistence.mybatis.DefaultCrudService;
import com.esofthead.mycollab.module.crm.dao.NoteMapperExt;
import com.esofthead.mycollab.module.crm.domain.Note;
import com.esofthead.mycollab.module.crm.domain.criteria.NoteSearchCriteria;
import com.esofthead.mycollab.module.crm.service.NoteService;
import com.esofthead.mycollab.module.file.service.AttachmentService;

public class NoteServiceImpl extends DefaultCrudService<Note, Integer>
		implements NoteService {

	private NoteMapperExt noteExtDAO;

	private AttachmentService attachmentService;

	public void setNoteExtDAO(NoteMapperExt noteExtDAO) {
		this.noteExtDAO = noteExtDAO;
	}

	public void setAttachmentService(AttachmentService attachmentService) {
		this.attachmentService = attachmentService;
	}

	@Override
	public int remove(Integer primaryKey) {
		String attachmentid = "crm-note-" + primaryKey;
		attachmentService.removeById(attachmentid);
		return super.remove(primaryKey);
	}

	@Override
	public List findPagableListByCriteria(NoteSearchCriteria criteria,
			int skipNum, int maxResult) {
		return noteExtDAO.findPagableList(criteria, new RowBounds(skipNum,
				maxResult));
	}

	@Override
	public int getTotalCount(NoteSearchCriteria criteria) {
		return noteExtDAO.getTotalCount(criteria);
	}

	@Override
	public int insertNoteExt(Note note) {
		noteExtDAO.insertNoteExt(note);
		return note.getId();
	}

}
