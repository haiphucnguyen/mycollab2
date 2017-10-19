/*Domain class of table m_crm_contacts_cases*/
package com.mycollab.module.crm.domain;

import com.mycollab.core.arguments.ValuedBean;
import com.mycollab.db.metadata.Column;
import com.mycollab.db.metadata.Table;
import java.util.Date;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.ibatis.type.Alias;

@SuppressWarnings("ucd")
@Table("m_crm_contacts_cases")
@Alias("ContactCase")
public class ContactCase extends ValuedBean {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_contacts_cases.id
     *
     * @mbg.generated Thu Oct 19 13:57:50 ICT 2017
     */
    @Column("id")
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_contacts_cases.contactId
     *
     * @mbg.generated Thu Oct 19 13:57:50 ICT 2017
     */
    @Column("contactId")
    private Integer contactid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_contacts_cases.caseId
     *
     * @mbg.generated Thu Oct 19 13:57:50 ICT 2017
     */
    @Column("caseId")
    private Integer caseid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_contacts_cases.createdTime
     *
     * @mbg.generated Thu Oct 19 13:57:50 ICT 2017
     */
    @Column("createdTime")
    private Date createdtime;

    private static final long serialVersionUID = 1;

    public final boolean equals(Object obj) {
        if (obj == null) { return false;}
        if (obj == this) { return true;}
        if (!obj.getClass().isAssignableFrom(getClass())) { return false;}
        ContactCase item = (ContactCase)obj;
        return new EqualsBuilder().append(id, item.id).build();
    }

    public final int hashCode() {
        return new HashCodeBuilder(1297, 1623).append(id).build();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_contacts_cases.id
     *
     * @return the value of m_crm_contacts_cases.id
     *
     * @mbg.generated Thu Oct 19 13:57:50 ICT 2017
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_contacts_cases.id
     *
     * @param id the value for m_crm_contacts_cases.id
     *
     * @mbg.generated Thu Oct 19 13:57:50 ICT 2017
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_contacts_cases.contactId
     *
     * @return the value of m_crm_contacts_cases.contactId
     *
     * @mbg.generated Thu Oct 19 13:57:50 ICT 2017
     */
    public Integer getContactid() {
        return contactid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_contacts_cases.contactId
     *
     * @param contactid the value for m_crm_contacts_cases.contactId
     *
     * @mbg.generated Thu Oct 19 13:57:50 ICT 2017
     */
    public void setContactid(Integer contactid) {
        this.contactid = contactid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_contacts_cases.caseId
     *
     * @return the value of m_crm_contacts_cases.caseId
     *
     * @mbg.generated Thu Oct 19 13:57:50 ICT 2017
     */
    public Integer getCaseid() {
        return caseid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_contacts_cases.caseId
     *
     * @param caseid the value for m_crm_contacts_cases.caseId
     *
     * @mbg.generated Thu Oct 19 13:57:50 ICT 2017
     */
    public void setCaseid(Integer caseid) {
        this.caseid = caseid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_contacts_cases.createdTime
     *
     * @return the value of m_crm_contacts_cases.createdTime
     *
     * @mbg.generated Thu Oct 19 13:57:50 ICT 2017
     */
    public Date getCreatedtime() {
        return createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_contacts_cases.createdTime
     *
     * @param createdtime the value for m_crm_contacts_cases.createdTime
     *
     * @mbg.generated Thu Oct 19 13:57:50 ICT 2017
     */
    public void setCreatedtime(Date createdtime) {
        this.createdtime = createdtime;
    }

    public enum Field {
        id,
        contactid,
        caseid,
        createdtime;

        public boolean equalTo(Object value) {
            return name().equals(value);
        }
    }
}