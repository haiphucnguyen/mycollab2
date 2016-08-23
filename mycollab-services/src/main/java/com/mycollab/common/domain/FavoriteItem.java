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
package com.mycollab.common.domain;

import com.mycollab.core.arguments.ValuedBean;
import com.mycollab.db.metadata.Column;
import com.mycollab.db.metadata.Table;
import java.util.Date;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.Length;

@SuppressWarnings("ucd")
@Table("s_favorite")
public class FavoriteItem extends ValuedBean {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_favorite.id
     *
     * @mbggenerated Tue Aug 23 16:53:53 ICT 2016
     */
    @Column("id")
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_favorite.type
     *
     * @mbggenerated Tue Aug 23 16:53:53 ICT 2016
     */
    @Length(max=45, message="Field value is too long")
    @Column("type")
    private String type;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_favorite.typeid
     *
     * @mbggenerated Tue Aug 23 16:53:53 ICT 2016
     */
    @Length(max=45, message="Field value is too long")
    @Column("typeid")
    private String typeid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_favorite.lastUpdatedTime
     *
     * @mbggenerated Tue Aug 23 16:53:53 ICT 2016
     */
    @Column("lastUpdatedTime")
    private Date lastupdatedtime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_favorite.createdTime
     *
     * @mbggenerated Tue Aug 23 16:53:53 ICT 2016
     */
    @Column("createdTime")
    private Date createdtime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_favorite.extraTypeId
     *
     * @mbggenerated Tue Aug 23 16:53:53 ICT 2016
     */
    @Column("extraTypeId")
    private Integer extratypeid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_favorite.createdUser
     *
     * @mbggenerated Tue Aug 23 16:53:53 ICT 2016
     */
    @Length(max=45, message="Field value is too long")
    @Column("createdUser")
    private String createduser;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_favorite.sAccountId
     *
     * @mbggenerated Tue Aug 23 16:53:53 ICT 2016
     */
    @Column("sAccountId")
    private Integer saccountid;

    private static final long serialVersionUID = 1;

    public final boolean equals(Object obj) {
        if (obj == null) { return false;}
        if (obj == this) { return true;}
        if (!obj.getClass().isAssignableFrom(getClass())) { return false;}
        FavoriteItem item = (FavoriteItem)obj;
        return new EqualsBuilder().append(id, item.id).build();
    }

    public final int hashCode() {
        return new HashCodeBuilder(155, 155).append(id).build();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_favorite.id
     *
     * @return the value of s_favorite.id
     *
     * @mbggenerated Tue Aug 23 16:53:53 ICT 2016
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
     * @mbggenerated Tue Aug 23 16:53:53 ICT 2016
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
     * @mbggenerated Tue Aug 23 16:53:53 ICT 2016
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
     * @mbggenerated Tue Aug 23 16:53:53 ICT 2016
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
     * @mbggenerated Tue Aug 23 16:53:53 ICT 2016
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
     * @mbggenerated Tue Aug 23 16:53:53 ICT 2016
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
     * @mbggenerated Tue Aug 23 16:53:53 ICT 2016
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
     * @mbggenerated Tue Aug 23 16:53:53 ICT 2016
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
     * @mbggenerated Tue Aug 23 16:53:53 ICT 2016
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
     * @mbggenerated Tue Aug 23 16:53:53 ICT 2016
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
     * @mbggenerated Tue Aug 23 16:53:53 ICT 2016
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
     * @mbggenerated Tue Aug 23 16:53:53 ICT 2016
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
     * @mbggenerated Tue Aug 23 16:53:53 ICT 2016
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
     * @mbggenerated Tue Aug 23 16:53:53 ICT 2016
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
     * @mbggenerated Tue Aug 23 16:53:53 ICT 2016
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
     * @mbggenerated Tue Aug 23 16:53:53 ICT 2016
     */
    public void setSaccountid(Integer saccountid) {
        this.saccountid = saccountid;
    }

    public enum Field {
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