package com.esofthead.mycollab.module.project.service.ibatis;

import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import com.esofthead.mycollab.core.persistence.mybatis.DefaultService;
import com.esofthead.mycollab.module.project.dao.ChangeLogMapper;
import com.esofthead.mycollab.module.project.dao.ChangeLogMapperExt;
import com.esofthead.mycollab.module.project.domain.ChangeLog;
import com.esofthead.mycollab.module.project.domain.criteria.ChangeLogSearchCriteria;
import com.esofthead.mycollab.module.project.service.ChangeLogService;

@Service
public class ChangeLogServiceImpl extends
		DefaultService<Integer, ChangeLog, ChangeLogSearchCriteria> implements ChangeLogService {

	@Autowired
	private ChangeLogMapper changeLogMapper;
	
	@Autowired
	private ChangeLogMapperExt changeLogMapperExt;
	
	

	@Override
	public ICrudGenericDAO<Integer, ChangeLog> getCrudMapper() {
		return changeLogMapper;
	}

	@Override
	public ISearchableDAO<ChangeLogSearchCriteria> getSearchMapper() {
		return changeLogMapperExt;
	}

	@Override
	public void saveChangeLog(int projectid, String postedUser, String source,
			int sourceid, String action, String sourceDesc) {
		ChangeLog changeLog = new ChangeLog();
		changeLog.setProjectid(projectid);
		changeLog.setPosteduser(postedUser);
		changeLog.setSource(source);
		changeLog.setSourceid(sourceid);
		changeLog.setLogaction(action);
		changeLog.setSourcedesc(sourceDesc);
		changeLog.setPosteddate(new GregorianCalendar().getTime());
		this.internalSaveWithSession(changeLog, null);
	}

}
