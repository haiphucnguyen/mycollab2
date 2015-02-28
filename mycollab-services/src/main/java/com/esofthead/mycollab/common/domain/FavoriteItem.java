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
/*Domain class of table s_favorite*/
package com.esofthead.mycollab.common.domain;

import com.esofthead.mycollab.core.arguments.ValuedBean;
import java.util.Date;

@SuppressWarnings("ucd")
@com.esofthead.mycollab.core.db.metadata.Table("s_favorite")
public class FavoriteItem extends ValuedBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_favorite.id
     *
     * @mbggenerated Sat Feb 28 16:29:57 ICT 2015
     */
    @com.esofthead.mycollab.core.db.metadata.Column("id")
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_favorite.type
     *
     * @mbggenerated Sat Feb 28 16:29:57 ICT 2015
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    @com.esofthead.mycollab.core.db.metadata.Column("type")
    private String type;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_favorite.typeid
     *
     * @mbggenerated Sat Feb 28 16:29:57 ICT 2015
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    @com.esofthead.mycollab.core.db.metadata.Column("typeid")
    private String typeid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_favorite.lastUpdatedTime
     *
     * @mbggenerated Sat Feb 28 16:29:57 ICT 2015
     */
    @com.esofthead.mycollab.core.db.metadata.Column("lastUpdatedTime")
    private Date lastupdatedtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_favorite.createdTime
     *
     * @mbggenerated Sat Feb 28 16:29:57 ICT 2015
     */
    @com.esofthead.mycollab.core.db.metadata.Column("createdTime")
    private Date createdtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_favorite.extraTypeId
     *
     * @mbggenerated Sat Feb 28 16:29:57 ICT 2015
     */
    @com.esofthead.mycollab.core.db.metadata.Column("extraTypeId")
    private Integer extratypeid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_favorite.createdUser
     *
     * @mbggenerated Sat Feb 28 16:29:57 ICT 2015
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    @com.esofthead.mycollab.core.db.metadata.Column("createdUser")
    private String createduser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_favorite.sAccountId
     *
     * @mbggenerated Sat Feb 28 16:29:57 ICT 2015
     */
    @com.esofthead.mycollab.core.db.metadata.Column("sAccountId")
    private Integer saccountid;

    private static final long serialVersionUID = 1;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_favorite.id
     *
     * @return the value of s_favorite.id
     *
     * @mbggenerated Sat Feb 28 16:29:57 ICT 2015
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_favorite.id
     *
     * @param id the value for s_favorite.id
     *
     * @mbggenerated Sat Feb 28 16:29:57 ICT 2015
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_favorite.type
     *
     * @return the value of s_favorite.type
     *
     * @mbggenerated Sat Feb 28 16:29:57 ICT 2015
     */
    public String getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_favorite.type
     *
     * @param type the value for s_favorite.type
     *
     * @mbggenerated Sat Feb 28 16:29:57 ICT 2015
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_favorite.typeid
     *
     * @return the value of s_favorite.typeid
     *
     * @mbggenerated Sat Feb 28 16:29:57 ICT 2015
     */
    public String getTypeid() {
        return typeid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_favorite.typeid
     *
     * @param typeid the value for s_favorite.typeid
     *
     * @mbggenerated Sat Feb 28 16:29:57 ICT 2015
     */
    public void setTypeid(String typeid) {
        this.typeid = typeid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_favorite.lastUpdatedTime
     *
     * @return the value of s_favorite.lastUpdatedTime
     *
     * @mbggenerated Sat Feb 28 16:29:57 ICT 2015
     */
    public Date getLastupdatedtime() {
        return lastupdatedtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_favorite.lastUpdatedTime
     *
     * @param lastupdatedtime the value for s_favorite.lastUpdatedTime
     *
     * @mbggenerated Sat Feb 28 16:29:57 ICT 2015
     */
    public void setLastupdatedtime(Date lastupdatedtime) {
        this.lastupdatedtime = lastupdatedtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_favorite.createdTime
     *
     * @return the value of s_favorite.createdTime
     *
     * @mbggenerated Sat Feb 28 16:29:57 ICT 2015
     */
    public Date getCreatedtime() {
        return createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_favorite.createdTime
     *
     * @param createdtime the value for s_favorite.createdTime
     *
     * @mbggenerated Sat Feb 28 16:29:57 ICT 2015
     */
    public void setCreatedtime(Date createdtime) {
        this.createdtime = createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_favorite.extraTypeId
     *
     * @return the value of s_favorite.extraTypeId
     *
     * @mbggenerated Sat Feb 28 16:29:57 ICT 2015
     */
    public Integer getExtratypeid() {
        return extratypeid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_favorite.extraTypeId
     *
     * @param extratypeid the value for s_favorite.extraTypeId
     *
     * @mbggenerated Sat Feb 28 16:29:57 ICT 2015
     */
    public void setExtratypeid(Integer extratypeid) {
        this.extratypeid = extratypeid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_favorite.createdUser
     *
     * @return the value of s_favorite.createdUser
     *
     * @mbggenerated Sat Feb 28 16:29:57 ICT 2015
     */
    public String getCreateduser() {
        return createduser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_favorite.createdUser
     *
     * @param createduser the value for s_favorite.createdUser
     *
     * @mbggenerated Sat Feb 28 16:29:57 ICT 2015
     */
    public void setCreateduser(String createduser) {
        this.createduser = createduser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_favorite.sAccountId
     *
     * @return the value of s_favorite.sAccountId
     *
     * @mbggenerated Sat Feb 28 16:29:57 ICT 2015
     */
    public Integer getSaccountid() {
        return saccountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_favorite.sAccountId
     *
     * @param saccountid the value for s_favorite.sAccountId
     *
     * @mbggenerated Sat Feb 28 16:29:57 ICT 2015
     */
    public void setSaccountid(Integer saccountid) {
        this.saccountid = saccountid;
    }

    public static enum Field {
        id,
        type,
        typeid,
        lastupdatedtime,
        createdtime,
        extratypeid,
        createduser,
        saccountid;

        public boolean equalTo(Object value) {
            return name().equals(value);
        }
    }
}