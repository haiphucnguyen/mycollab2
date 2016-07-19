package com.mycollab.ondemand.module.support.service.ibatis;

import com.mycollab.ondemand.module.support.dao.EmailReferenceMapper;
import com.mycollab.ondemand.module.support.domain.EmailReference;
import com.mycollab.ondemand.module.support.domain.EmailReferenceExample;
import com.mycollab.ondemand.module.support.service.EmailReferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.GregorianCalendar;

/**
 * @author MyCollab Ltd
 * @since 5.3.5
 */
@Service
public class EmailReferenceServiceImpl implements EmailReferenceService {
    @Autowired
    private EmailReferenceMapper emailReferenceMapper;

    @Override
    public void save(String email) {
        EmailReferenceExample ex = new EmailReferenceExample();
        ex.createCriteria().andEmailEqualTo(email);
        if (emailReferenceMapper.countByExample(ex) == 0) {
            EmailReference emailReference = new EmailReference();
            emailReference.setCreatedtime(new GregorianCalendar().getTime());
            emailReference.setEmail(email);
            emailReference.setSubscribe(Boolean.TRUE);
            emailReferenceMapper.insert(emailReference);
        }
    }

    @Override
    public void remove(String email) {
        EmailReferenceExample ex = new EmailReferenceExample();
        ex.createCriteria().andEmailEqualTo(email);
        emailReferenceMapper.deleteByExample(ex);
    }
}
