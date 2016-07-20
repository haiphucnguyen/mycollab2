package com.mycollab.pro.common.service.impl;

import com.mycollab.cache.CleanCacheEvent;
import com.mycollab.common.domain.FavoriteItem;
import com.mycollab.common.domain.FavoriteItemExample;
import com.mycollab.common.service.FavoriteItemService;
import com.mycollab.db.persistence.ICrudGenericDAO;
import com.mycollab.db.persistence.service.DefaultCrudService;
import com.mycollab.pro.common.dao.FavoriteItemMapper;
import com.google.common.eventbus.AsyncEventBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author MyCollab Ltd.
 * @since 5.0.1
 */
@Service
public class FavoriteItemServiceImpl extends DefaultCrudService<Integer, FavoriteItem> implements FavoriteItemService {
    @Autowired
    private FavoriteItemMapper favoriteItemMapper;

    @Autowired
    private AsyncEventBus asyncEventBus;

    @Override
    public ICrudGenericDAO<Integer, FavoriteItem> getCrudMapper() {
        return favoriteItemMapper;
    }

    @Override
    public void saveOrDelete(FavoriteItem item) {
        FavoriteItemExample ex = new FavoriteItemExample();
        ex.createCriteria().andTypeEqualTo(item.getType()).andTypeidEqualTo(item.getTypeid()).
                andCreateduserEqualTo(item.getCreateduser());
        int count = favoriteItemMapper.countByExample(ex);
        if (count > 0) {
            favoriteItemMapper.deleteByExample(ex);
        } else {
            Date now = new GregorianCalendar().getTime();
            item.setLastupdatedtime(now);
            item.setCreatedtime(now);
            favoriteItemMapper.insert(item);
        }
        asyncEventBus.post(new CleanCacheEvent(item.getSaccountid(), new Class[]{FavoriteItemService.class}));
    }

    @Override
    public boolean isUserFavorite(String username, String type, String typeId) {
        FavoriteItemExample ex = new FavoriteItemExample();
        ex.createCriteria().andTypeEqualTo(type).andTypeidEqualTo(typeId).andCreateduserEqualTo(username);
        return (favoriteItemMapper.countByExample(ex) > 0);
    }
}
