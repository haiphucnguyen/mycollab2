package com.mycollab.db.persistence;

import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.List;

/**
 * @param <K>
 * @param <T>
 * @author MyCollab Ltd.
 * @since 1.0
 */
public interface ICrudGenericDAO<K extends Serializable, T> {

    /**
     * @param record
     */
    void insert(T record);

    /**
     * @param record
     * @return
     */
    int updateByPrimaryKey(T record);

    /**
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(T record);

    /**
     * @param record
     * @param primaryKeys
     */
    void massUpdateWithSession(@Param("record") T record, @Param("primaryKeys") List<K> primaryKeys);

    /**
     * @param primaryKey
     * @return
     */
    T selectByPrimaryKey(K primaryKey);

    /**
     * @param primaryKey
     * @return
     */
    int deleteByPrimaryKey(K primaryKey);

    /**
     * @param value
     * @return
     */
    int insertAndReturnKey(T value);

    /**
     * @param keys
     */
    void removeKeysWithSession(List keys);

}
