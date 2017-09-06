package com.mycollab.common.service;

import com.mycollab.common.domain.FavoriteItem;
import com.mycollab.db.persistence.service.ICrudService;

/**
 * @author MyCollab Ltd.
 * @since 5.0.1
 */
public interface FavoriteItemService extends ICrudService<Integer, FavoriteItem> {
    void saveOrDelete(FavoriteItem item);

    boolean isUserFavorite(String username, String type, String typeId);
}
