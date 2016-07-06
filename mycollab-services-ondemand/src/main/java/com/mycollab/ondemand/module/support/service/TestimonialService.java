package com.mycollab.ondemand.module.support.service;

import com.mycollab.cache.IgnoreCacheClass;
import com.mycollab.db.persistence.service.ICrudService;
import com.mycollab.ondemand.module.support.domain.Testimonial;

/**
 * @author MyCollab Ltd.
 * @since 5.0.6
 */
@IgnoreCacheClass
public interface TestimonialService extends ICrudService<Integer, Testimonial> {
}
