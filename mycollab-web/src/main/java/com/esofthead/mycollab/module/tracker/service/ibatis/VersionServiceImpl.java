package com.esofthead.mycollab.module.tracker.service.ibatis;

import com.esofthead.mycollab.common.interceptor.service.Traceable;
import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.core.persistence.service.DefaultCrudService;
import com.esofthead.mycollab.module.tracker.RelatedItemConstants;
import com.esofthead.mycollab.module.tracker.dao.RelatedItemMapper;
import com.esofthead.mycollab.module.tracker.dao.VersionMapper;
import com.esofthead.mycollab.module.tracker.domain.RelatedItemExample;
import com.esofthead.mycollab.module.tracker.domain.Version;
import com.esofthead.mycollab.module.tracker.domain.VersionExample;
import com.esofthead.mycollab.module.tracker.service.VersionService;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Traceable(module = "Project", nameField = "versionname", type = "Version")
public class VersionServiceImpl extends DefaultCrudService<Integer, Version>
        implements VersionService {

    @Autowired
    private VersionMapper versionMapper;
    @Autowired
    private RelatedItemMapper relatedItemMapper;

    @Override
    public ICrudGenericDAO<Integer, Version> getCrudMapper() {
        return versionMapper;
    }

    @Override
    public List<Version> getVersionsOfProject(int projectid) {
        VersionExample ex = new VersionExample();
        ex.createCriteria().andProjectidEqualTo(projectid);
        return versionMapper.selectByExample(ex);
    }

    @Override
    public int remove(Integer primaryKey) {
        RelatedItemExample ex = new RelatedItemExample();
        ex.createCriteria()
                .andTypeidIn(
                Arrays.asList(RelatedItemConstants.AFFECTED_VERSION,
                RelatedItemConstants.FIXED_VERSION))
                .andTypeidEqualTo(primaryKey);
        relatedItemMapper.deleteByExample(ex);

        return super.remove(primaryKey);
    }
}
