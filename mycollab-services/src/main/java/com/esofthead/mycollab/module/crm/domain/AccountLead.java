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
/*Domain class of table m_crm_accounts_leads*/
package com.esofthead.mycollab.module.crm.domain;

import com.esofthead.mycollab.core.arguments.ValuedBean;
import com.esofthead.mycollab.core.db.metadata.Column;
import com.esofthead.mycollab.core.db.metadata.Table;
import java.util.Date;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@SuppressWarnings("ucd")
@Table("m_crm_accounts_leads")
public class AccountLead extends ValuedBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_accounts_leads.id
     *
     * @mbggenerated Thu Mar 10 00:40:01 ICT 2016
     */
    @Column("id")
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_accounts_leads.accountId
     *
     * @mbggenerated Thu Mar 10 00:40:01 ICT 2016
     */
    @Column("accountId")
    private Integer accountid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_accounts_leads.leadId
     *
     * @mbggenerated Thu Mar 10 00:40:01 ICT 2016
     */
    @Column("leadId")
    private Integer leadid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_accounts_leads.createTime
     *
     * @mbggenerated Thu Mar 10 00:40:01 ICT 2016
     */
    @Column("createTime")
    private Date createtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_accounts_leads.isConvertRel
     *
     * @mbggenerated Thu Mar 10 00:40:01 ICT 2016
     */
    @Column("isConvertRel")
    private Boolean isconvertrel;

    private static final long serialVersionUID = 1;

    public final boolean equals(Object obj) {
        if (obj == null) { return false;}
        if (obj == this) { return true;}
        if (obj.getClass() != getClass()) { return false;}
        AccountLead item = (AccountLead)obj;
        return new EqualsBuilder().append(id, item.id).build();
    }

    public final int hashCode() {
        return new HashCodeBuilder(1451, 713).append(id).build();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_accounts_leads.id
     *
     * @return the value of m_crm_accounts_leads.id
     *
     * @mbggenerated Thu Mar 10 00:40:01 ICT 2016
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_accounts_leads.id
     *
     * @param id the value for m_crm_accounts_leads.id
     *
     * @mbggenerated Thu Mar 10 00:40:01 ICT 2016
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_accounts_leads.accountId
     *
     * @return the value of m_crm_accounts_leads.accountId
     *
     * @mbggenerated Thu Mar 10 00:40:01 ICT 2016
     */
    public Integer getAccountid() {
        return accountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_accounts_leads.accountId
     *
     * @param accountid the value for m_crm_accounts_leads.accountId
     *
     * @mbggenerated Thu Mar 10 00:40:01 ICT 2016
     */
    public void setAccountid(Integer accountid) {
        this.accountid = accountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_accounts_leads.leadId
     *
     * @return the value of m_crm_accounts_leads.leadId
     *
     * @mbggenerated Thu Mar 10 00:40:01 ICT 2016
     */
    public Integer getLeadid() {
        return leadid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_accounts_leads.leadId
     *
     * @param leadid the value for m_crm_accounts_leads.leadId
     *
     * @mbggenerated Thu Mar 10 00:40:01 ICT 2016
     */
    public void setLeadid(Integer leadid) {
        this.leadid = leadid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_accounts_leads.createTime
     *
     * @return the value of m_crm_accounts_leads.createTime
     *
     * @mbggenerated Thu Mar 10 00:40:01 ICT 2016
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_accounts_leads.createTime
     *
     * @param createtime the value for m_crm_accounts_leads.createTime
     *
     * @mbggenerated Thu Mar 10 00:40:01 ICT 2016
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_accounts_leads.isConvertRel
     *
     * @return the value of m_crm_accounts_leads.isConvertRel
     *
     * @mbggenerated Thu Mar 10 00:40:01 ICT 2016
     */
    public Boolean getIsconvertrel() {
        return isconvertrel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_accounts_leads.isConvertRel
     *
     * @param isconvertrel the value for m_crm_accounts_leads.isConvertRel
     *
     * @mbggenerated Thu Mar 10 00:40:01 ICT 2016
     */
    public void setIsconvertrel(Boolean isconvertrel) {
        this.isconvertrel = isconvertrel;
    }

    public enum Field {
        id,
        accountid,
        leadid,
        createtime,
        isconvertrel;

        public boolean equalTo(Object value) {
            return name().equals(value);
        }
    }
}