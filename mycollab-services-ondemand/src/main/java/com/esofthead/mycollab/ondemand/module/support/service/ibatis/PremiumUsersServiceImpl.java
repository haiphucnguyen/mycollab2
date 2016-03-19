package com.esofthead.mycollab.ondemand.module.support.service.ibatis;

import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.core.persistence.service.DefaultCrudService;
import com.esofthead.mycollab.ondemand.module.support.domain.PremiumUser;
import com.esofthead.mycollab.ondemand.module.support.dao.PremiumUserMapper;
import com.esofthead.mycollab.ondemand.module.support.service.PremiumUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author MyCollab Ltd
 * @since 5.2.6
 */
@Service
public class PremiumUsersServiceImpl extends DefaultCrudService<Integer, PremiumUser> implements PremiumUsersService {
    @Autowired
    private PremiumUserMapper premiumUserMapper;

    @Override
    public ICrudGenericDAO<Integer, PremiumUser> getCrudMapper() {
        return premiumUserMapper;
    }
}
