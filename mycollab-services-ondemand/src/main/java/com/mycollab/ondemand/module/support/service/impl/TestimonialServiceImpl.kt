package com.mycollab.ondemand.module.support.service.impl

import com.mycollab.db.persistence.ICrudGenericDAO
import com.mycollab.db.persistence.service.DefaultCrudService
import com.mycollab.ondemand.module.support.dao.TestimonialMapper
import com.mycollab.ondemand.module.support.domain.Testimonial
import com.mycollab.ondemand.module.support.service.TestimonialService
import org.springframework.stereotype.Service

/**
 * @author MyCollab Ltd.
 * @since 5.0.6
 */
@Service
class TestimonialServiceImpl(private val testimonialMapper: TestimonialMapper) : DefaultCrudService<Int, Testimonial>(), TestimonialService {

    override val crudMapper: ICrudGenericDAO<Int, Testimonial>
        get() = testimonialMapper as ICrudGenericDAO<Int, Testimonial>
}
