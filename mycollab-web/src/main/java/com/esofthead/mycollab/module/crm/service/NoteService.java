package com.esofthead.mycollab.module.crm.service;

import com.esofthead.mycollab.core.persistence.service.IDefaultService;
import com.esofthead.mycollab.module.crm.domain.Note;
import com.esofthead.mycollab.module.crm.domain.criteria.NoteSearchCriteria;

public interface NoteService extends
		IDefaultService<Integer, Note, NoteSearchCriteria> {
	int insertNoteExt(Note note);
}
