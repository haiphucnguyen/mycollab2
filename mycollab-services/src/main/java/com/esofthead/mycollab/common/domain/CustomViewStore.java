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
/*Domain class of table s_table_customize_view*/
package com.esofthead.mycollab.common.domain;

import com.esofthead.mycollab.core.arguments.ValuedBean;
import com.esofthead.mycollab.core.db.metadata.Column;
import com.esofthead.mycollab.core.db.metadata.Table;
import java.util.Date;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.Length;

@SuppressWarnings("ucd")
@Table("s_table_customize_view")
public class CustomViewStore extends ValuedBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_table_customize_view.id
     *
     * @mbggenerated Thu Jun 30 10:50:02 ICT 2016
     */
    @Column("id")
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_table_customize_view.createdUser
     *
     * @mbggenerated Thu Jun 30 10:50:02 ICT 2016
     */
    @Length(max=45, message="Field value is too long")
    @Column("createdUser")
    private String createduser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_table_customize_view.createdTime
     *
     * @mbggenerated Thu Jun 30 10:50:02 ICT 2016
     */
    @Column("createdTime")
    private Date createdtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_table_customize_view.viewId
     *
     * @mbggenerated Thu Jun 30 10:50:02 ICT 2016
     */
    @Length(max=45, message="Field value is too long")
    @Column("viewId")
    private String viewid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_table_customize_view.sAccountId
     *
     * @mbggenerated Thu Jun 30 10:50:02 ICT 2016
     */
    @Column("sAccountId")
    private Integer saccountid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_table_customize_view.viewInfo
     *
     * @mbggenerated Thu Jun 30 10:50:02 ICT 2016
     */
    @Length(max=65535, message="Field value is too long")
    @Column("viewInfo")
    private String viewinfo;

    private static final long serialVersionUID = 1;

    public final boolean equals(Object obj) {
        if (obj == null) { return false;}
        if (obj == this) { return true;}
        if (!obj.getClass().isAssignableFrom(getClass())) { return false;}
        CustomViewStore item = (CustomViewStore)obj;
        return new EqualsBuilder().append(id, item.id).build();
    }

    public final int hashCode() {
        return new HashCodeBuilder(337, 1025).append(id).build();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_table_customize_view.id
     *
     * @return the value of s_table_customize_view.id
     *
     * @mbggenerated Thu Jun 30 10:50:02 ICT 2016
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_table_customize_view.id
     *
     * @param id the value for s_table_customize_view.id
     *
     * @mbggenerated Thu Jun 30 10:50:02 ICT 2016
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_table_customize_view.createdUser
     *
     * @return the value of s_table_customize_view.createdUser
     *
     * @mbggenerated Thu Jun 30 10:50:02 ICT 2016
     */
    public String getCreateduser() {
        return createduser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_table_customize_view.createdUser
     *
     * @param createduser the value for s_table_customize_view.createdUser
     *
     * @mbggenerated Thu Jun 30 10:50:02 ICT 2016
     */
    public void setCreateduser(String createduser) {
        this.createduser = createduser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_table_customize_view.createdTime
     *
     * @return the value of s_table_customize_view.createdTime
     *
     * @mbggenerated Thu Jun 30 10:50:02 ICT 2016
     */
    public Date getCreatedtime() {
        return createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_table_customize_view.createdTime
     *
     * @param createdtime the value for s_table_customize_view.createdTime
     *
     * @mbggenerated Thu Jun 30 10:50:02 ICT 2016
     */
    public void setCreatedtime(Date createdtime) {
        this.createdtime = createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_table_customize_view.viewId
     *
     * @return the value of s_table_customize_view.viewId
     *
     * @mbggenerated Thu Jun 30 10:50:02 ICT 2016
     */
    public String getViewid() {
        return viewid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_table_customize_view.viewId
     *
     * @param viewid the value for s_table_customize_view.viewId
     *
     * @mbggenerated Thu Jun 30 10:50:02 ICT 2016
     */
    public void setViewid(String viewid) {
        this.viewid = viewid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_table_customize_view.sAccountId
     *
     * @return the value of s_table_customize_view.sAccountId
     *
     * @mbggenerated Thu Jun 30 10:50:02 ICT 2016
     */
    public Integer getSaccountid() {
        return saccountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_table_customize_view.sAccountId
     *
     * @param saccountid the value for s_table_customize_view.sAccountId
     *
     * @mbggenerated Thu Jun 30 10:50:02 ICT 2016
     */
    public void setSaccountid(Integer saccountid) {
        this.saccountid = saccountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_table_customize_view.viewInfo
     *
     * @return the value of s_table_customize_view.viewInfo
     *
     * @mbggenerated Thu Jun 30 10:50:02 ICT 2016
     */
    public String getViewinfo() {
        return viewinfo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_table_customize_view.viewInfo
     *
     * @param viewinfo the value for s_table_customize_view.viewInfo
     *
     * @mbggenerated Thu Jun 30 10:50:02 ICT 2016
     */
    public void setViewinfo(String viewinfo) {
        this.viewinfo = viewinfo;
    }

    public enum Field {
        id,
        createduser,
        createdtime,
        viewid,
        saccountid,
        viewinfo;

        public boolean equalTo(Object value) {
            return name().equals(value);
        }
    }
}