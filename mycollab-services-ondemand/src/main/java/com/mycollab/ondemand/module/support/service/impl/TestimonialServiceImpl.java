package com.mycollab.ondemand.module.support.service.impl;

import com.mycollab.db.persistence.ICrudGenericDAO;
import com.mycollab.db.persistence.service.DefaultCrudService;
import com.mycollab.ondemand.module.support.dao.TestimonialMapper;
import com.mycollab.ondemand.module.support.domain.Testimonial;
import com.mycollab.ondemand.module.support.service.TestimonialService;
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
