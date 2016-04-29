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
/*Domain class of table s_timeline_tracking*/
package com.esofthead.mycollab.common.domain;

import com.esofthead.mycollab.core.arguments.ValuedBean;
import com.esofthead.mycollab.core.db.metadata.Column;
import com.esofthead.mycollab.core.db.metadata.Table;
import java.util.Date;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.Length;

@SuppressWarnings("ucd")
@Table("s_timeline_tracking")
public class TimelineTracking extends ValuedBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_timeline_tracking.id
     *
     * @mbggenerated Fri Apr 29 10:32:16 ICT 2016
     */
    @Column("id")
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_timeline_tracking.type
     *
     * @mbggenerated Fri Apr 29 10:32:16 ICT 2016
     */
    @Length(max=45, message="Field value is too long")
    @Column("type")
    private String type;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_timeline_tracking.typeId
     *
     * @mbggenerated Fri Apr 29 10:32:16 ICT 2016
     */
    @Column("typeId")
    private Integer typeid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_timeline_tracking.fieldval
     *
     * @mbggenerated Fri Apr 29 10:32:16 ICT 2016
     */
    @Length(max=45, message="Field value is too long")
    @Column("fieldval")
    private String fieldval;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_timeline_tracking.fieldgroup
     *
     * @mbggenerated Fri Apr 29 10:32:16 ICT 2016
     */
    @Length(max=45, message="Field value is too long")
    @Column("fieldgroup")
    private String fieldgroup;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_timeline_tracking.extratypeid
     *
     * @mbggenerated Fri Apr 29 10:32:16 ICT 2016
     */
    @Column("extratypeid")
    private Integer extratypeid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_timeline_tracking.sAccountId
     *
     * @mbggenerated Fri Apr 29 10:32:16 ICT 2016
     */
    @Column("sAccountId")
    private Integer saccountid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_timeline_tracking.forDay
     *
     * @mbggenerated Fri Apr 29 10:32:16 ICT 2016
     */
    @Column("forDay")
    private Date forday;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_timeline_tracking.flag
     *
     * @mbggenerated Fri Apr 29 10:32:16 ICT 2016
     */
    @Column("flag")
    private Byte flag;

    private static final long serialVersionUID = 1;

    public final boolean equals(Object obj) {
        if (obj == null) { return false;}
        if (obj == this) { return true;}
        if (!obj.getClass().isAssignableFrom(getClass())) { return false;}
        TimelineTracking item = (TimelineTracking)obj;
        return new EqualsBuilder().append(id, item.id).build();
    }

    public final int hashCode() {
        return new HashCodeBuilder(511, 1609).append(id).build();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_timeline_tracking.id
     *
     * @return the value of s_timeline_tracking.id
     *
     * @mbggenerated Fri Apr 29 10:32:16 ICT 2016
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_timeline_tracking.id
     *
     * @param id the value for s_timeline_tracking.id
     *
     * @mbggenerated Fri Apr 29 10:32:16 ICT 2016
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_timeline_tracking.type
     *
     * @return the value of s_timeline_tracking.type
     *
     * @mbggenerated Fri Apr 29 10:32:16 ICT 2016
     */
    public String getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_timeline_tracking.type
     *
     * @param type the value for s_timeline_tracking.type
     *
     * @mbggenerated Fri Apr 29 10:32:16 ICT 2016
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_timeline_tracking.typeId
     *
     * @return the value of s_timeline_tracking.typeId
     *
     * @mbggenerated Fri Apr 29 10:32:16 ICT 2016
     */
    public Integer getTypeid() {
        return typeid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_timeline_tracking.typeId
     *
     * @param typeid the value for s_timeline_tracking.typeId
     *
     * @mbggenerated Fri Apr 29 10:32:16 ICT 2016
     */
    public void setTypeid(Integer typeid) {
        this.typeid = typeid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_timeline_tracking.fieldval
     *
     * @return the value of s_timeline_tracking.fieldval
     *
     * @mbggenerated Fri Apr 29 10:32:16 ICT 2016
     */
    public String getFieldval() {
        return fieldval;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_timeline_tracking.fieldval
     *
     * @param fieldval the value for s_timeline_tracking.fieldval
     *
     * @mbggenerated Fri Apr 29 10:32:16 ICT 2016
     */
    public void setFieldval(String fieldval) {
        this.fieldval = fieldval;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_timeline_tracking.fieldgroup
     *
     * @return the value of s_timeline_tracking.fieldgroup
     *
     * @mbggenerated Fri Apr 29 10:32:16 ICT 2016
     */
    public String getFieldgroup() {
        return fieldgroup;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_timeline_tracking.fieldgroup
     *
     * @param fieldgroup the value for s_timeline_tracking.fieldgroup
     *
     * @mbggenerated Fri Apr 29 10:32:16 ICT 2016
     */
    public void setFieldgroup(String fieldgroup) {
        this.fieldgroup = fieldgroup;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_timeline_tracking.extratypeid
     *
     * @return the value of s_timeline_tracking.extratypeid
     *
     * @mbggenerated Fri Apr 29 10:32:16 ICT 2016
     */
    public Integer getExtratypeid() {
        return extratypeid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_timeline_tracking.extratypeid
     *
     * @param extratypeid the value for s_timeline_tracking.extratypeid
     *
     * @mbggenerated Fri Apr 29 10:32:16 ICT 2016
     */
    public void setExtratypeid(Integer extratypeid) {
        this.extratypeid = extratypeid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_timeline_tracking.sAccountId
     *
     * @return the value of s_timeline_tracking.sAccountId
     *
     * @mbggenerated Fri Apr 29 10:32:16 ICT 2016
     */
    public Integer getSaccountid() {
        return saccountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_timeline_tracking.sAccountId
     *
     * @param saccountid the value for s_timeline_tracking.sAccountId
     *
     * @mbggenerated Fri Apr 29 10:32:16 ICT 2016
     */
    public void setSaccountid(Integer saccountid) {
        this.saccountid = saccountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_timeline_tracking.forDay
     *
     * @return the value of s_timeline_tracking.forDay
     *
     * @mbggenerated Fri Apr 29 10:32:16 ICT 2016
     */
    public Date getForday() {
        return forday;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_timeline_tracking.forDay
     *
     * @param forday the value for s_timeline_tracking.forDay
     *
     * @mbggenerated Fri Apr 29 10:32:16 ICT 2016
     */
    public void setForday(Date forday) {
        this.forday = forday;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_timeline_tracking.flag
     *
     * @return the value of s_timeline_tracking.flag
     *
     * @mbggenerated Fri Apr 29 10:32:16 ICT 2016
     */
    public Byte getFlag() {
        return flag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_timeline_tracking.flag
     *
     * @param flag the value for s_timeline_tracking.flag
     *
     * @mbggenerated Fri Apr 29 10:32:16 ICT 2016
     */
    public void setFlag(Byte flag) {
        this.flag = flag;
    }

    public enum Field {
        id,
        type,
        typeid,
        fieldval,
        fieldgroup,
        extratypeid,
        saccountid,
        forday,
        flag;

        public boolean equalTo(Object value) {
            return name().equals(value);
        }
    }
}