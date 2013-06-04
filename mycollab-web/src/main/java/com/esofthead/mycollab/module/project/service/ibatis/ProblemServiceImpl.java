package com.esofthead.mycollab.module.project.service.ibatis;

import com.esofthead.mycollab.common.ModuleNameConstants;
import com.esofthead.mycollab.common.interceptor.service.Auditable;
import com.esofthead.mycollab.common.interceptor.service.Traceable;
import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import com.esofthead.mycollab.core.persistence.service.DefaultService;
import com.esofthead.mycollab.module.project.ProjectContants;
import com.esofthead.mycollab.module.project.dao.ProblemMapper;
import com.esofthead.mycollab.module.project.dao.ProblemMapperExt;
import com.esofthead.mycollab.module.project.domain.Problem;
import com.esofthead.mycollab.module.project.domain.SimpleProblem;
import com.esofthead.mycollab.module.project.domain.criteria.ProblemSearchCriteria;
import com.esofthead.mycollab.module.project.service.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Traceable(module = ModuleNameConstants.PRJ, nameField = "issuename", type = ProjectContants.PROBLEM, extraFieldName = "projectid")
@Auditable(module = ModuleNameConstants.PRJ, type = ProjectContants.PROBLEM)
public class ProblemServiceImpl extends DefaultService<Integer, Problem, ProblemSearchCriteria> implements
        ProblemService {

    @Autowired
    private ProblemMapper problemMapper;
    @Autowired
    private ProblemMapperExt problemMapperExt;

    @Override
    public ICrudGenericDAO<Integer, Problem> getCrudMapper() {
        return problemMapper;
    }

    @Override
    public ISearchableDAO<ProblemSearchCriteria> getSearchMapper() {
        return problemMapperExt;
    }

    @Override
    public SimpleProblem findProblemById(Integer problemId) {
        return problemMapperExt.findProblemById(problemId);
    }
}
