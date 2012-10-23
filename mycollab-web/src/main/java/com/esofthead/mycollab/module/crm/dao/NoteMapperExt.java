package com.esofthead.mycollab.module.crm.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.esofthead.mycollab.module.crm.domain.Note;
import com.esofthead.mycollab.module.crm.domain.criteria.NoteSearchCriteria;

public interface NoteMapperExt {
	public List findPagableList(NoteSearchCriteria criteria, RowBounds rowBounds);

	public int getTotalCount(NoteSearchCriteria criteria);
	
	int insertNoteExt(Note note);
}
