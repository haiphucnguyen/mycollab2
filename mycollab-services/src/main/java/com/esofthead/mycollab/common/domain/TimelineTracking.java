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
     * @mbggenerated Tue Oct 27 22:57:42 ICT 2015
     */
    @Column("id")
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_timeline_tracking.type
     *
     * @mbggenerated Tue Oct 27 22:57:42 ICT 2015
     */
    @Length(max=45, message="Field value is too long")
    @Column("type")
    private String type;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_timeline_tracking.typeid
     *
     * @mbggenerated Tue Oct 27 22:57:42 ICT 2015
     */
    @Column("typeid")
    private Integer typeid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_timeline_tracking.typeval
     *
     * @mbggenerated Tue Oct 27 22:57:42 ICT 2015
     */
    @Length(max=45, message="Field value is too long")
    @Column("typeval")
    private String typeval;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_timeline_tracking.extratypeid
     *
     * @mbggenerated Tue Oct 27 22:57:42 ICT 2015
     */
    @Column("extratypeid")
    private Integer extratypeid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_timeline_tracking.sAccountId
     *
     * @mbggenerated Tue Oct 27 22:57:42 ICT 2015
     */
    @Column("sAccountId")
    private Integer saccountid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_timeline_tracking.forDay
     *
     * @mbggenerated Tue Oct 27 22:57:42 ICT 2015
     */
    @Column("forDay")
    private Date forday;

    private static final long serialVersionUID = 1;

    public final boolean equals(Object obj) {
        if (obj == null) { return false;}
        if (obj == this) { return true;}
        if (obj.getClass() != getClass()) { return false;}
        TimelineTracking item = (TimelineTracking)obj;
        return new EqualsBuilder().append(id, item.id).build();
    }

    public final int hashCode() {
        return new HashCodeBuilder(1163, 563).append(id).build();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_timeline_tracking.id
     *
     * @return the value of s_timeline_tracking.id
     *
     * @mbggenerated Tue Oct 27 22:57:42 ICT 2015
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
     * @mbggenerated Tue Oct 27 22:57:42 ICT 2015
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
     * @mbggenerated Tue Oct 27 22:57:42 ICT 2015
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
     * @mbggenerated Tue Oct 27 22:57:42 ICT 2015
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_timeline_tracking.typeid
     *
     * @return the value of s_timeline_tracking.typeid
     *
     * @mbggenerated Tue Oct 27 22:57:42 ICT 2015
     */
    public Integer getTypeid() {
        return typeid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_timeline_tracking.typeid
     *
     * @param typeid the value for s_timeline_tracking.typeid
     *
     * @mbggenerated Tue Oct 27 22:57:42 ICT 2015
     */
    public void setTypeid(Integer typeid) {
        this.typeid = typeid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_timeline_tracking.typeval
     *
     * @return the value of s_timeline_tracking.typeval
     *
     * @mbggenerated Tue Oct 27 22:57:42 ICT 2015
     */
    public String getTypeval() {
        return typeval;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_timeline_tracking.typeval
     *
     * @param typeval the value for s_timeline_tracking.typeval
     *
     * @mbggenerated Tue Oct 27 22:57:42 ICT 2015
     */
    public void setTypeval(String typeval) {
        this.typeval = typeval;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_timeline_tracking.extratypeid
     *
     * @return the value of s_timeline_tracking.extratypeid
     *
     * @mbggenerated Tue Oct 27 22:57:42 ICT 2015
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
     * @mbggenerated Tue Oct 27 22:57:42 ICT 2015
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
     * @mbggenerated Tue Oct 27 22:57:42 ICT 2015
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
     * @mbggenerated Tue Oct 27 22:57:42 ICT 2015
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
     * @mbggenerated Tue Oct 27 22:57:42 ICT 2015
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
     * @mbggenerated Tue Oct 27 22:57:42 ICT 2015
     */
    public void setForday(Date forday) {
        this.forday = forday;
    }

    public enum Field {
        id,
        type,
        typeid,
        typeval,
        extratypeid,
        saccountid,
        forday;

        public boolean equalTo(Object value) {
            return name().equals(value);
        }
    }
}