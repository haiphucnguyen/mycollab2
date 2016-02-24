package com.esofthead.mycollab.ondemand.module.support.service;

import com.esofthead.mycollab.cache.IgnoreCacheClass;
import com.esofthead.mycollab.core.persistence.service.ICrudService;
import com.esofthead.mycollab.ondemand.module.support.domain.Testimonial;

/**
 * @author MyCollab Ltd.
 * @since 5.0.6
 */
@IgnoreCacheClass
public interface TestimonialService extends ICrudService<Integer, Testimonial> {
}
