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
/*Domain class of table m_tracker_metadata*/
package com.esofthead.mycollab.module.tracker.domain;

import com.esofthead.mycollab.core.utils.ValuedBean;

public class MetaData extends ValuedBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_tracker_metadata.id
     *
     * @mbggenerated Sun Oct 13 15:52:38 ICT 2013
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_tracker_metadata.projectid
     *
     * @mbggenerated Sun Oct 13 15:52:38 ICT 2013
     */
    private Integer projectid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_tracker_metadata.xmlstring
     *
     * @mbggenerated Sun Oct 13 15:52:38 ICT 2013
     */
    @org.hibernate.validator.constraints.Length(max=2147483647, message="Field value is too long")
    private String xmlstring;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_tracker_metadata.id
     *
     * @return the value of m_tracker_metadata.id
     *
     * @mbggenerated Sun Oct 13 15:52:38 ICT 2013
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_tracker_metadata.id
     *
     * @param id the value for m_tracker_metadata.id
     *
     * @mbggenerated Sun Oct 13 15:52:38 ICT 2013
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_tracker_metadata.projectid
     *
     * @return the value of m_tracker_metadata.projectid
     *
     * @mbggenerated Sun Oct 13 15:52:38 ICT 2013
     */
    public Integer getProjectid() {
        return projectid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_tracker_metadata.projectid
     *
     * @param projectid the value for m_tracker_metadata.projectid
     *
     * @mbggenerated Sun Oct 13 15:52:38 ICT 2013
     */
    public void setProjectid(Integer projectid) {
        this.projectid = projectid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_tracker_metadata.xmlstring
     *
     * @return the value of m_tracker_metadata.xmlstring
     *
     * @mbggenerated Sun Oct 13 15:52:38 ICT 2013
     */
    public String getXmlstring() {
        return xmlstring;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_tracker_metadata.xmlstring
     *
     * @param xmlstring the value for m_tracker_metadata.xmlstring
     *
     * @mbggenerated Sun Oct 13 15:52:38 ICT 2013
     */
    public void setXmlstring(String xmlstring) {
        this.xmlstring = xmlstring;
    }
}