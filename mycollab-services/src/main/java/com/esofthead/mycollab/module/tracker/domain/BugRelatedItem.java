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
/*Domain class of table m_tracker_bug_related_item*/
package com.esofthead.mycollab.module.tracker.domain;

import com.esofthead.mycollab.core.arguments.ValuedBean;

@SuppressWarnings("ucd")
@com.esofthead.mycollab.core.db.metadata.Table("m_tracker_bug_related_item")
public class BugRelatedItem extends ValuedBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_tracker_bug_related_item.id
     *
     * @mbggenerated Mon May 04 10:52:58 ICT 2015
     */
    @com.esofthead.mycollab.core.db.metadata.Column("id")
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_tracker_bug_related_item.bugid
     *
     * @mbggenerated Mon May 04 10:52:58 ICT 2015
     */
    @com.esofthead.mycollab.core.db.metadata.Column("bugid")
    private Integer bugid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_tracker_bug_related_item.type
     *
     * @mbggenerated Mon May 04 10:52:58 ICT 2015
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    @com.esofthead.mycollab.core.db.metadata.Column("type")
    private String type;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_tracker_bug_related_item.typeid
     *
     * @mbggenerated Mon May 04 10:52:58 ICT 2015
     */
    @com.esofthead.mycollab.core.db.metadata.Column("typeid")
    private Integer typeid;

    private static final long serialVersionUID = 1;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_tracker_bug_related_item.id
     *
     * @return the value of m_tracker_bug_related_item.id
     *
     * @mbggenerated Mon May 04 10:52:58 ICT 2015
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_tracker_bug_related_item.id
     *
     * @param id the value for m_tracker_bug_related_item.id
     *
     * @mbggenerated Mon May 04 10:52:58 ICT 2015
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_tracker_bug_related_item.bugid
     *
     * @return the value of m_tracker_bug_related_item.bugid
     *
     * @mbggenerated Mon May 04 10:52:58 ICT 2015
     */
    public Integer getBugid() {
        return bugid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_tracker_bug_related_item.bugid
     *
     * @param bugid the value for m_tracker_bug_related_item.bugid
     *
     * @mbggenerated Mon May 04 10:52:58 ICT 2015
     */
    public void setBugid(Integer bugid) {
        this.bugid = bugid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_tracker_bug_related_item.type
     *
     * @return the value of m_tracker_bug_related_item.type
     *
     * @mbggenerated Mon May 04 10:52:58 ICT 2015
     */
    public String getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_tracker_bug_related_item.type
     *
     * @param type the value for m_tracker_bug_related_item.type
     *
     * @mbggenerated Mon May 04 10:52:58 ICT 2015
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_tracker_bug_related_item.typeid
     *
     * @return the value of m_tracker_bug_related_item.typeid
     *
     * @mbggenerated Mon May 04 10:52:58 ICT 2015
     */
    public Integer getTypeid() {
        return typeid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_tracker_bug_related_item.typeid
     *
     * @param typeid the value for m_tracker_bug_related_item.typeid
     *
     * @mbggenerated Mon May 04 10:52:58 ICT 2015
     */
    public void setTypeid(Integer typeid) {
        this.typeid = typeid;
    }

    public static enum Field {
        id,
        bugid,
        type,
        typeid;

        public boolean equalTo(Object value) {
            return name().equals(value);
        }
    }
}