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
package com.mycollab.module.crm.domain;

import com.mycollab.db.metadata.Column;
import com.mycollab.db.metadata.Table;
import org.hibernate.validator.constraints.Length;

@SuppressWarnings("ucd")
public class MeetingWithBLOBs extends Meeting {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_meeting.description
     *
     * @mbggenerated Tue Aug 16 15:25:11 ICT 2016
     */
    @Length(max=65535, message="Field value is too long")
    @Column("description")
    private String description;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_meeting.recurrenceInfo
     *
     * @mbggenerated Tue Aug 16 15:25:11 ICT 2016
     */
    @Length(max=65535, message="Field value is too long")
    @Column("recurrenceInfo")
    private String recurrenceinfo;

    private static final long serialVersionUID = 1;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_meeting.description
     *
     * @return the value of m_crm_meeting.description
     *
     * @mbggenerated Tue Aug 16 15:25:11 ICT 2016
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_meeting.description
     *
     * @param description the value for m_crm_meeting.description
     *
     * @mbggenerated Tue Aug 16 15:25:11 ICT 2016
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_meeting.recurrenceInfo
     *
     * @return the value of m_crm_meeting.recurrenceInfo
     *
     * @mbggenerated Tue Aug 16 15:25:11 ICT 2016
     */
    public String getRecurrenceinfo() {
        return recurrenceinfo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_meeting.recurrenceInfo
     *
     * @param recurrenceinfo the value for m_crm_meeting.recurrenceInfo
     *
     * @mbggenerated Tue Aug 16 15:25:11 ICT 2016
     */
    public void setRecurrenceinfo(String recurrenceinfo) {
        this.recurrenceinfo = recurrenceinfo;
    }

    public enum Field {
        id,
        subject,
        status,
        type,
        typeid,
        startdate,
        enddate,
        lastupdatedtime,
        createdtime,
        createduser,
        saccountid,
        location,
        isrecurrence,
        recurrencetype,
        recurrencestartdate,
        recurrenceenddate,
        contacttype,
        contacttypeid,
        isclosed,
        isnotified,
        isnotifiedprior,
        description,
        recurrenceinfo;

        public boolean equalTo(Object value) {
            return name().equals(value);
        }
    }
}