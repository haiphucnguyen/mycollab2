package com.esofthead.mycollab.pro.common.service.ibatis;

import com.esofthead.mycollab.cache.CleanCacheEvent;
import com.esofthead.mycollab.common.domain.FavoriteItem;
import com.esofthead.mycollab.common.domain.FavoriteItemExample;
import com.esofthead.mycollab.common.service.FavoriteItemService;
import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.core.persistence.service.DefaultCrudService;
import com.esofthead.mycollab.pro.common.dao.FavoriteItemMapper;
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
