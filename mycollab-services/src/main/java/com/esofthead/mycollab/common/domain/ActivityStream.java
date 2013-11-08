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
/*Domain class of table s_activitystream*/
package com.esofthead.mycollab.common.domain;

import com.esofthead.mycollab.core.utils.ValuedBean;
import java.util.Date;

public class ActivityStream extends ValuedBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_activitystream.id
     *
     * @mbggenerated Sun Oct 13 15:52:38 ICT 2013
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_activitystream.sAccountId
     *
     * @mbggenerated Sun Oct 13 15:52:38 ICT 2013
     */
    private Integer saccountid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_activitystream.type
     *
     * @mbggenerated Sun Oct 13 15:52:38 ICT 2013
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    private String type;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_activitystream.typeId
     *
     * @mbggenerated Sun Oct 13 15:52:38 ICT 2013
     */
    private Integer typeid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_activitystream.createdTime
     *
     * @mbggenerated Sun Oct 13 15:52:38 ICT 2013
     */
    private Date createdtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_activitystream.action
     *
     * @mbggenerated Sun Oct 13 15:52:38 ICT 2013
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    private String action;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_activitystream.createdUser
     *
     * @mbggenerated Sun Oct 13 15:52:38 ICT 2013
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    private String createduser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_activitystream.module
     *
     * @mbggenerated Sun Oct 13 15:52:38 ICT 2013
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    private String module;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_activitystream.extraTypeId
     *
     * @mbggenerated Sun Oct 13 15:52:38 ICT 2013
     */
    private Integer extratypeid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_activitystream.createdUserDisplayName
     *
     * @mbggenerated Sun Oct 13 15:52:38 ICT 2013
     */
    @org.hibernate.validator.constraints.Length(max=100, message="Field value is too long")
    private String createduserdisplayname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_activitystream.nameField
     *
     * @mbggenerated Sun Oct 13 15:52:38 ICT 2013
     */
    @org.hibernate.validator.constraints.Length(max=65535, message="Field value is too long")
    private String namefield;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_activitystream.id
     *
     * @return the value of s_activitystream.id
     *
     * @mbggenerated Sun Oct 13 15:52:38 ICT 2013
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_activitystream.id
     *
     * @param id the value for s_activitystream.id
     *
     * @mbggenerated Sun Oct 13 15:52:38 ICT 2013
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_activitystream.sAccountId
     *
     * @return the value of s_activitystream.sAccountId
     *
     * @mbggenerated Sun Oct 13 15:52:38 ICT 2013
     */
    public Integer getSaccountid() {
        return saccountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_activitystream.sAccountId
     *
     * @param saccountid the value for s_activitystream.sAccountId
     *
     * @mbggenerated Sun Oct 13 15:52:38 ICT 2013
     */
    public void setSaccountid(Integer saccountid) {
        this.saccountid = saccountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_activitystream.type
     *
     * @return the value of s_activitystream.type
     *
     * @mbggenerated Sun Oct 13 15:52:38 ICT 2013
     */
    public String getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_activitystream.type
     *
     * @param type the value for s_activitystream.type
     *
     * @mbggenerated Sun Oct 13 15:52:38 ICT 2013
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_activitystream.typeId
     *
     * @return the value of s_activitystream.typeId
     *
     * @mbggenerated Sun Oct 13 15:52:38 ICT 2013
     */
    public Integer getTypeid() {
        return typeid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_activitystream.typeId
     *
     * @param typeid the value for s_activitystream.typeId
     *
     * @mbggenerated Sun Oct 13 15:52:38 ICT 2013
     */
    public void setTypeid(Integer typeid) {
        this.typeid = typeid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_activitystream.createdTime
     *
     * @return the value of s_activitystream.createdTime
     *
     * @mbggenerated Sun Oct 13 15:52:38 ICT 2013
     */
    public Date getCreatedtime() {
        return createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_activitystream.createdTime
     *
     * @param createdtime the value for s_activitystream.createdTime
     *
     * @mbggenerated Sun Oct 13 15:52:38 ICT 2013
     */
    public void setCreatedtime(Date createdtime) {
        this.createdtime = createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_activitystream.action
     *
     * @return the value of s_activitystream.action
     *
     * @mbggenerated Sun Oct 13 15:52:38 ICT 2013
     */
    public String getAction() {
        return action;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_activitystream.action
     *
     * @param action the value for s_activitystream.action
     *
     * @mbggenerated Sun Oct 13 15:52:38 ICT 2013
     */
    public void setAction(String action) {
        this.action = action;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_activitystream.createdUser
     *
     * @return the value of s_activitystream.createdUser
     *
     * @mbggenerated Sun Oct 13 15:52:38 ICT 2013
     */
    public String getCreateduser() {
        return createduser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_activitystream.createdUser
     *
     * @param createduser the value for s_activitystream.createdUser
     *
     * @mbggenerated Sun Oct 13 15:52:38 ICT 2013
     */
    public void setCreateduser(String createduser) {
        this.createduser = createduser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_activitystream.module
     *
     * @return the value of s_activitystream.module
     *
     * @mbggenerated Sun Oct 13 15:52:38 ICT 2013
     */
    public String getModule() {
        return module;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_activitystream.module
     *
     * @param module the value for s_activitystream.module
     *
     * @mbggenerated Sun Oct 13 15:52:38 ICT 2013
     */
    public void setModule(String module) {
        this.module = module;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_activitystream.extraTypeId
     *
     * @return the value of s_activitystream.extraTypeId
     *
     * @mbggenerated Sun Oct 13 15:52:38 ICT 2013
     */
    public Integer getExtratypeid() {
        return extratypeid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_activitystream.extraTypeId
     *
     * @param extratypeid the value for s_activitystream.extraTypeId
     *
     * @mbggenerated Sun Oct 13 15:52:38 ICT 2013
     */
    public void setExtratypeid(Integer extratypeid) {
        this.extratypeid = extratypeid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_activitystream.createdUserDisplayName
     *
     * @return the value of s_activitystream.createdUserDisplayName
     *
     * @mbggenerated Sun Oct 13 15:52:38 ICT 2013
     */
    public String getCreateduserdisplayname() {
        return createduserdisplayname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_activitystream.createdUserDisplayName
     *
     * @param createduserdisplayname the value for s_activitystream.createdUserDisplayName
     *
     * @mbggenerated Sun Oct 13 15:52:38 ICT 2013
     */
    public void setCreateduserdisplayname(String createduserdisplayname) {
        this.createduserdisplayname = createduserdisplayname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_activitystream.nameField
     *
     * @return the value of s_activitystream.nameField
     *
     * @mbggenerated Sun Oct 13 15:52:38 ICT 2013
     */
    public String getNamefield() {
        return namefield;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_activitystream.nameField
     *
     * @param namefield the value for s_activitystream.nameField
     *
     * @mbggenerated Sun Oct 13 15:52:38 ICT 2013
     */
    public void setNamefield(String namefield) {
        this.namefield = namefield;
    }
}