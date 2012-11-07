package com.esofthead.mycollab.module.project.service.ibatis;

import java.util.GregorianCalendar;
import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.esofthead.mycollab.core.persistence.mybatis.DefaultCrudService;
import com.esofthead.mycollab.module.project.dao.ChangeLogMapperExt;
import com.esofthead.mycollab.module.project.domain.ChangeLog;
import com.esofthead.mycollab.module.project.domain.criteria.ChangeLogSearchCriteria;
import com.esofthead.mycollab.module.project.service.ChangeLogService;

public class ChangeLogServiceImpl extends
		DefaultCrudService<Integer, ChangeLog> implements ChangeLogService {

	private ChangeLogMapperExt changeLogExtDAO;

	public void setChangeLogExtDAO(ChangeLogMapperExt changeLogExtDAO) {
		this.changeLogExtDAO = changeLogExtDAO;
	}

	@Override
	public List findPagableListByCriteria(ChangeLogSearchCriteria criteria,
			int skipNum, int maxResult) {
		return changeLogExtDAO.findPagableList(criteria, new RowBounds(skipNum,
				maxResult));
	}

	@Override
	public int getTotalCount(ChangeLogSearchCriteria criteria) {
		return changeLogExtDAO.getTotalCount(criteria);
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
