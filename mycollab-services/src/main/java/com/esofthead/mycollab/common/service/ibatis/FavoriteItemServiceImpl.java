package com.esofthead.mycollab.common.service.ibatis;

import com.esofthead.mycollab.common.dao.FavoriteItemMapper;
import com.esofthead.mycollab.common.domain.FavoriteItem;
import com.esofthead.mycollab.common.service.FavoriteItemService;
import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.core.persistence.service.DefaultCrudService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author MyCollab Ltd.
 * @since 5.0.1
 */
public class FavoriteItemServiceImpl extends DefaultCrudService<Integer, FavoriteItem> implements FavoriteItemService {
    @Autowired
    private FavoriteItemMapper favoriteItemMapper;

    @Override
    public ICrudGenericDAO<Integer, FavoriteItem> getCrudMapper() {
        return favoriteItemMapper;
    }
}
