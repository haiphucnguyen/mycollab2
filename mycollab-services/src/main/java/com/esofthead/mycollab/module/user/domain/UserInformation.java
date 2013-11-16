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
/*Domain class of table s_user_information*/
package com.esofthead.mycollab.module.user.domain;

import com.esofthead.mycollab.core.arguments.ValuedBean;

public class UserInformation extends ValuedBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user_information.id
     *
     * @mbggenerated Thu Oct 31 11:24:43 ICT 2013
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user_information.username
     *
     * @mbggenerated Thu Oct 31 11:24:43 ICT 2013
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    private String username;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user_information.sAccountId
     *
     * @mbggenerated Thu Oct 31 11:24:43 ICT 2013
     */
    private Integer saccountid;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_user_information.id
     *
     * @return the value of s_user_information.id
     *
     * @mbggenerated Thu Oct 31 11:24:43 ICT 2013
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_user_information.id
     *
     * @param id the value for s_user_information.id
     *
     * @mbggenerated Thu Oct 31 11:24:43 ICT 2013
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_user_information.username
     *
     * @return the value of s_user_information.username
     *
     * @mbggenerated Thu Oct 31 11:24:43 ICT 2013
     */
    public String getUsername() {
        return username;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_user_information.username
     *
     * @param username the value for s_user_information.username
     *
     * @mbggenerated Thu Oct 31 11:24:43 ICT 2013
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_user_information.sAccountId
     *
     * @return the value of s_user_information.sAccountId
     *
     * @mbggenerated Thu Oct 31 11:24:43 ICT 2013
     */
    public Integer getSaccountid() {
        return saccountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_user_information.sAccountId
     *
     * @param saccountid the value for s_user_information.sAccountId
     *
     * @mbggenerated Thu Oct 31 11:24:43 ICT 2013
     */
    public void setSaccountid(Integer saccountid) {
        this.saccountid = saccountid;
    }
}