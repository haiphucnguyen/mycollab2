package com.mycollab.module.crm.domain;

import com.mycollab.db.metadata.Column;
import com.mycollab.db.metadata.Table;
import org.hibernate.validator.constraints.Length;

@SuppressWarnings("ucd")
public class CaseWithBLOBs extends Case {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_case.description
     *
     * @mbg.generated Mon Oct 03 14:51:42 ICT 2016
     */
    @Length(max=16777215, message="Field value is too long")
    @Column("description")
    private String description;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_case.resolution
     *
     * @mbg.generated Mon Oct 03 14:51:42 ICT 2016
     */
    @Length(max=16777215, message="Field value is too long")
    @Column("resolution")
    private String resolution;

    private static final long serialVersionUID = 1;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_case.description
     *
     * @return the value of m_crm_case.description
     *
     * @mbg.generated Mon Oct 03 14:51:42 ICT 2016
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_case.description
     *
     * @param description the value for m_crm_case.description
     *
     * @mbg.generated Mon Oct 03 14:51:42 ICT 2016
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_case.resolution
     *
     * @return the value of m_crm_case.resolution
     *
     * @mbg.generated Mon Oct 03 14:51:42 ICT 2016
     */
    public String getResolution() {
        return resolution;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_case.resolution
     *
     * @param resolution the value for m_crm_case.resolution
     *
     * @mbg.generated Mon Oct 03 14:51:42 ICT 2016
     */
    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public enum Field {
        id,
        priority,
        status,
        type,
        subject,
        accountid,
        createdtime,
        createduser,
        saccountid,
        assignuser,
        lastupdatedtime,
        reason,
        origin,
        email,
        phonenumber,
        description,
        resolution;

        public boolean equalTo(Object value) {
            return name().equals(value);
        }
    }
}