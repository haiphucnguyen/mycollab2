package com.esofthead.mycollab.module.project.dao;

import com.esofthead.mycollab.module.project.domain.ProjectFavoriteItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author MyCollab Ltd.
 * @since 5.0.1
 */
public interface ProjectFavoriteItemMapper {
    List<ProjectFavoriteItem> getFavoriteItems(@Param("username") String username);
}
