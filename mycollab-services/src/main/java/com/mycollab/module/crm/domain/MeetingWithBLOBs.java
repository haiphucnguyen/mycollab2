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
     * @mbg.generated Fri Nov 30 08:06:38 CST 2018
     */
    @Length(max=16777215, message="Field value is too long")
    @Column("description")
    private String description;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_meeting.recurrenceInfo
     *
     * @mbg.generated Fri Nov 30 08:06:38 CST 2018
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
     * @mbg.generated Fri Nov 30 08:06:38 CST 2018
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
     * @mbg.generated Fri Nov 30 08:06:38 CST 2018
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
     * @mbg.generated Fri Nov 30 08:06:38 CST 2018
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
     * @mbg.generated Fri Nov 30 08:06:38 CST 2018
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