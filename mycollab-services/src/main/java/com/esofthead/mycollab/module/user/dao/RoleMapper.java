package com.esofthead.mycollab.module.user.dao;

import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.module.user.domain.Role;
import com.esofthead.mycollab.module.user.domain.RoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@SuppressWarnings({ "ucd", "rawtypes" })
public interface RoleMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_roles
     *
     * @mbggenerated Wed Feb 03 14:56:41 ICT 2016
     */
    int countByExample(RoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_roles
     *
     * @mbggenerated Wed Feb 03 14:56:41 ICT 2016
     */
    int deleteByExample(RoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_roles
     *
     * @mbggenerated Wed Feb 03 14:56:41 ICT 2016
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_roles
     *
     * @mbggenerated Wed Feb 03 14:56:41 ICT 2016
     */
    int insert(Role record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_roles
     *
     * @mbggenerated Wed Feb 03 14:56:41 ICT 2016
     */
    int insertSelective(Role record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_roles
     *
     * @mbggenerated Wed Feb 03 14:56:41 ICT 2016
     */
    List<Role> selectByExample(RoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_roles
     *
     * @mbggenerated Wed Feb 03 14:56:41 ICT 2016
     */
    Role selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_roles
     *
     * @mbggenerated Wed Feb 03 14:56:41 ICT 2016
     */
    int updateByExampleSelective(@Param("record") Role record, @Param("example") RoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_roles
     *
     * @mbggenerated Wed Feb 03 14:56:41 ICT 2016
     */
    int updateByExample(@Param("record") Role record, @Param("example") RoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_roles
     *
     * @mbggenerated Wed Feb 03 14:56:41 ICT 2016
     */
    int updateByPrimaryKeySelective(Role record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_roles
     *
     * @mbggenerated Wed Feb 03 14:56:41 ICT 2016
     */
    int updateByPrimaryKey(Role record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_roles
     *
     * @mbggenerated Wed Feb 03 14:56:41 ICT 2016
     */
    Integer insertAndReturnKey(Role value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_roles
     *
     * @mbggenerated Wed Feb 03 14:56:41 ICT 2016
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_roles
     *
     * @mbggenerated Wed Feb 03 14:56:41 ICT 2016
     */
    void massUpdateWithSession(@Param("record") Role record, @Param("primaryKeys") List primaryKeys);
}