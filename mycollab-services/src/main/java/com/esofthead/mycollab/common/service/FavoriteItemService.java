package com.esofthead.mycollab.common.service;

import com.esofthead.mycollab.common.domain.FavoriteItem;
import com.esofthead.mycollab.core.persistence.service.ICrudService;

/**
 * @author MyCollab Ltd.
 * @since 5.0.1
 */
public interface FavoriteItemService extends ICrudService<Integer, FavoriteItem> {
    void saveOrUpdate(FavoriteItem item);
}
