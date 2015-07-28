package com.esofthead.mycollab.common.service.ibatis;

import com.esofthead.mycollab.common.dao.OptionValMapper;
import com.esofthead.mycollab.common.domain.OptionVal;
import com.esofthead.mycollab.common.service.OptionValService;
import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.core.persistence.service.DefaultCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author MyCollab Ltd
 * @since 5.1.1
 */
@Service
public class OptionValServiceImpl extends DefaultCrudService<Integer, OptionVal> implements OptionValService {
    @Autowired
    private OptionValMapper optionValMapper;

    @Override
    public ICrudGenericDAO<Integer, OptionVal> getCrudMapper() {
        return optionValMapper;
    }
}
