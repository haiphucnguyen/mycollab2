package com.esofthead.mycollab.module.crm.service;

import com.esofthead.mycollab.core.persistence.ICrudService;
import com.esofthead.mycollab.core.persistence.IPagableService;
import com.esofthead.mycollab.module.crm.domain.Note;
import com.esofthead.mycollab.module.crm.domain.criteria.NoteSearchCriteria;

public interface NoteService extends ICrudService<Note, Integer>,
		IPagableService<NoteSearchCriteria> {
	int insertNoteExt(Note note);
}
