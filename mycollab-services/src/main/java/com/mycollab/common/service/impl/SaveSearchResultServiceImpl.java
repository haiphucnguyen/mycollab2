package com.mycollab.common.service.impl;

import com.mycollab.common.dao.SaveSearchResultMapper;
import com.mycollab.common.dao.SaveSearchResultMapperExt;
import com.mycollab.common.domain.SaveSearchResult;
import com.mycollab.common.domain.SaveSearchResultExample;
import com.mycollab.common.domain.criteria.SaveSearchResultCriteria;
import com.mycollab.common.service.SaveSearchResultService;
import com.mycollab.core.UserInvalidInputException;
import com.mycollab.db.persistence.ICrudGenericDAO;
import com.mycollab.db.persistence.ISearchableDAO;
import com.mycollab.db.persistence.service.DefaultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
@Service
public class SaveSearchResultServiceImpl extends DefaultService<Integer, SaveSearchResult, SaveSearchResultCriteria>
        implements SaveSearchResultService {

    @Autowired
    private SaveSearchResultMapper saveSearchResultMapper;

    @Autowired
    private SaveSearchResultMapperExt saveSearchResultMapperExt;

    @Override
    public ICrudGenericDAO<Integer, SaveSearchResult> getCrudMapper() {
        return saveSearchResultMapper;
    }

    @Override
    public ISearchableDAO<SaveSearchResultCriteria> getSearchMapper() {
        return saveSearchResultMapperExt;
    }

    @Override
    public Integer saveWithSession(SaveSearchResult record, String username) {
        checkDuplicateEntryName(record);
        return super.saveWithSession(record, username);
    }

    @Override
    public Integer updateWithSession(SaveSearchResult record, String username) {
        return super.updateWithSession(record, username);
    }

    private void checkDuplicateEntryName(SaveSearchResult record) {
        SaveSearchResultExample ex = new SaveSearchResultExample();
        ex.createCriteria().andSaccountidEqualTo(record.getSaccountid()).andTypeEqualTo(record.getType())
                .andQuerynameEqualTo(record.getQueryname());
        if (saveSearchResultMapper.countByExample(ex) > 0) {
            throw new UserInvalidInputException("There is the query name existed");
        }
    }
}
