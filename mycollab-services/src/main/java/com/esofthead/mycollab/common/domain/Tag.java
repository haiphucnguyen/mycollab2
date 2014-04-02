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
/*Domain class of table s_tag*/
package com.esofthead.mycollab.common.domain;

import com.esofthead.mycollab.core.arguments.ValuedBean;

@com.esofthead.mycollab.core.db.metadata.Table("s_tag")
public class Tag extends ValuedBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_tag.id
     *
     * @mbggenerated Sat Mar 08 17:38:10 ICT 2014
     */
    @com.esofthead.mycollab.core.db.metadata.Column("id")
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_tag.name
     *
     * @mbggenerated Sat Mar 08 17:38:10 ICT 2014
     */
    @org.hibernate.validator.constraints.Length(max=255, message="Field value is too long")
    @com.esofthead.mycollab.core.db.metadata.Column("name")
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_tag.sAccountId
     *
     * @mbggenerated Sat Mar 08 17:38:10 ICT 2014
     */
    @com.esofthead.mycollab.core.db.metadata.Column("sAccountId")
    private Integer saccountid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_tag.lowercasename
     *
     * @mbggenerated Sat Mar 08 17:38:10 ICT 2014
     */
    @org.hibernate.validator.constraints.Length(max=255, message="Field value is too long")
    @com.esofthead.mycollab.core.db.metadata.Column("lowercasename")
    private String lowercasename;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_tag.id
     *
     * @return the value of s_tag.id
     *
     * @mbggenerated Sat Mar 08 17:38:10 ICT 2014
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_tag.id
     *
     * @param id the value for s_tag.id
     *
     * @mbggenerated Sat Mar 08 17:38:10 ICT 2014
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_tag.name
     *
     * @return the value of s_tag.name
     *
     * @mbggenerated Sat Mar 08 17:38:10 ICT 2014
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_tag.name
     *
     * @param name the value for s_tag.name
     *
     * @mbggenerated Sat Mar 08 17:38:10 ICT 2014
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_tag.sAccountId
     *
     * @return the value of s_tag.sAccountId
     *
     * @mbggenerated Sat Mar 08 17:38:10 ICT 2014
     */
    public Integer getSaccountid() {
        return saccountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_tag.sAccountId
     *
     * @param saccountid the value for s_tag.sAccountId
     *
     * @mbggenerated Sat Mar 08 17:38:10 ICT 2014
     */
    public void setSaccountid(Integer saccountid) {
        this.saccountid = saccountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_tag.lowercasename
     *
     * @return the value of s_tag.lowercasename
     *
     * @mbggenerated Sat Mar 08 17:38:10 ICT 2014
     */
    public String getLowercasename() {
        return lowercasename;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_tag.lowercasename
     *
     * @param lowercasename the value for s_tag.lowercasename
     *
     * @mbggenerated Sat Mar 08 17:38:10 ICT 2014
     */
    public void setLowercasename(String lowercasename) {
        this.lowercasename = lowercasename;
    }
}