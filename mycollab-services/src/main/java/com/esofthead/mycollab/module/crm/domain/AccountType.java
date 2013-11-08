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
package com.esofthead.mycollab.module.crm.domain;

import com.esofthead.mycollab.core.utils.ValuedBean;

public class AccountType extends ValuedBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_accounttype.id
     *
     * @mbggenerated Fri Nov 16 15:23:00 GMT+07:00 2012
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_accounttype.name
     *
     * @mbggenerated Fri Nov 16 15:23:00 GMT+07:00 2012
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_accounttype.sAccountId
     *
     * @mbggenerated Fri Nov 16 15:23:00 GMT+07:00 2012
     */
    private Integer saccountid;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_accounttype.id
     *
     * @return the value of m_crm_accounttype.id
     *
     * @mbggenerated Fri Nov 16 15:23:00 GMT+07:00 2012
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_accounttype.id
     *
     * @param id the value for m_crm_accounttype.id
     *
     * @mbggenerated Fri Nov 16 15:23:00 GMT+07:00 2012
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_accounttype.name
     *
     * @return the value of m_crm_accounttype.name
     *
     * @mbggenerated Fri Nov 16 15:23:00 GMT+07:00 2012
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_accounttype.name
     *
     * @param name the value for m_crm_accounttype.name
     *
     * @mbggenerated Fri Nov 16 15:23:00 GMT+07:00 2012
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_accounttype.sAccountId
     *
     * @return the value of m_crm_accounttype.sAccountId
     *
     * @mbggenerated Fri Nov 16 15:23:00 GMT+07:00 2012
     */
    public Integer getSaccountid() {
        return saccountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_accounttype.sAccountId
     *
     * @param saccountid the value for m_crm_accounttype.sAccountId
     *
     * @mbggenerated Fri Nov 16 15:23:00 GMT+07:00 2012
     */
    public void setSaccountid(Integer saccountid) {
        this.saccountid = saccountid;
    }
}