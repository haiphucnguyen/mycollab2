package com.esofthead.mycollab.ondemand.module.support.service.ibatis;

import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.core.persistence.service.DefaultCrudService;
import com.esofthead.mycollab.ondemand.module.support.dao.TestimonialMapper;
import com.esofthead.mycollab.ondemand.module.support.domain.Testimonial;
import com.esofthead.mycollab.ondemand.module.support.service.TestimonialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author MyCollab Ltd.
 * @since 5.0.6
 */
@Service
public class TestimonialServiceImpl extends DefaultCrudService<Integer, Testimonial> implements TestimonialService {
    @Autowired
    private TestimonialMapper testimonialMapper;

    @Override
    public ICrudGenericDAO<Integer, Testimonial> getCrudMapper() {
        return testimonialMapper;
    }
}
