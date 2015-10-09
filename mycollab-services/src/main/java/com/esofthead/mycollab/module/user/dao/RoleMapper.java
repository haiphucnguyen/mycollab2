/**
 * This file is part of mycollab-services.
 *
 * mycollab-services is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-services is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-services.  If not, see <http://www.gnu.org/licenses/>.
 */
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
     * @mbggenerated Fri Oct 09 11:22:02 ICT 2015
     */
    int countByExample(RoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_roles
     *
     * @mbggenerated Fri Oct 09 11:22:02 ICT 2015
     */
    int deleteByExample(RoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_roles
     *
     * @mbggenerated Fri Oct 09 11:22:02 ICT 2015
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_roles
     *
     * @mbggenerated Fri Oct 09 11:22:02 ICT 2015
     */
    int insert(Role record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_roles
     *
     * @mbggenerated Fri Oct 09 11:22:02 ICT 2015
     */
    int insertSelective(Role record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_roles
     *
     * @mbggenerated Fri Oct 09 11:22:02 ICT 2015
     */
    List<Role> selectByExample(RoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_roles
     *
     * @mbggenerated Fri Oct 09 11:22:02 ICT 2015
     */
    Role selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_roles
     *
     * @mbggenerated Fri Oct 09 11:22:02 ICT 2015
     */
    int updateByExampleSelective(@Param("record") Role record, @Param("example") RoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_roles
     *
     * @mbggenerated Fri Oct 09 11:22:02 ICT 2015
     */
    int updateByExample(@Param("record") Role record, @Param("example") RoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_roles
     *
     * @mbggenerated Fri Oct 09 11:22:02 ICT 2015
     */
    int updateByPrimaryKeySelective(Role record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_roles
     *
     * @mbggenerated Fri Oct 09 11:22:02 ICT 2015
     */
    int updateByPrimaryKey(Role record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_roles
     *
     * @mbggenerated Fri Oct 09 11:22:02 ICT 2015
     */
    Integer insertAndReturnKey(Role value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_roles
     *
     * @mbggenerated Fri Oct 09 11:22:02 ICT 2015
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_roles
     *
     * @mbggenerated Fri Oct 09 11:22:02 ICT 2015
     */
    void massUpdateWithSession(@Param("record") Role record, @Param("primaryKeys") List primaryKeys);
}