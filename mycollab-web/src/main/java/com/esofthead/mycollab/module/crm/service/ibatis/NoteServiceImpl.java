package com.esofthead.mycollab.module.crm.service.ibatis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import com.esofthead.mycollab.core.persistence.mybatis.DefaultService;
import com.esofthead.mycollab.module.crm.dao.NoteMapper;
import com.esofthead.mycollab.module.crm.dao.NoteMapperExt;
import com.esofthead.mycollab.module.crm.domain.Note;
import com.esofthead.mycollab.module.crm.domain.criteria.NoteSearchCriteria;
import com.esofthead.mycollab.module.crm.service.NoteService;
import com.esofthead.mycollab.module.file.service.AttachmentService;

@Service
public class NoteServiceImpl extends DefaultService<Integer, Note, NoteSearchCriteria>
		implements NoteService {

	@Autowired
	private NoteMapper noteMapper;
	
	@Autowired
	private NoteMapperExt noteMapperExt;

	@Autowired
	private AttachmentService attachmentService;
	
	@Override
	public ICrudGenericDAO<Integer, Note> getCrudMapper() {
		return noteMapper;
	}

	@Override
	public ISearchableDAO<NoteSearchCriteria> getSearchMapper() {
		return noteMapperExt;
	}

	@Override
	public int remove(Integer primaryKey) {
		return super.remove(primaryKey);
	}

	@Override
	public int insertNoteExt(Note note) {
		noteMapperExt.insertNoteExt(note);
		return note.getId();
	}

}
