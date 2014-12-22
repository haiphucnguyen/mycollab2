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
/*Domain class of table s_tag_relationship*/
package com.esofthead.mycollab.common.domain;

import com.esofthead.mycollab.core.arguments.ValuedBean;

@SuppressWarnings("ucd")
@com.esofthead.mycollab.core.db.metadata.Table("s_tag_relationship")
public class TagRelationship extends ValuedBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_tag_relationship.id
     *
     * @mbggenerated Mon Dec 22 11:25:12 ICT 2014
     */
    @com.esofthead.mycollab.core.db.metadata.Column("id")
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_tag_relationship.tagId
     *
     * @mbggenerated Mon Dec 22 11:25:12 ICT 2014
     */
    @com.esofthead.mycollab.core.db.metadata.Column("tagId")
    private Integer tagid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_tag_relationship.type
     *
     * @mbggenerated Mon Dec 22 11:25:12 ICT 2014
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    @com.esofthead.mycollab.core.db.metadata.Column("type")
    private String type;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_tag_relationship.typerid
     *
     * @mbggenerated Mon Dec 22 11:25:12 ICT 2014
     */
    @com.esofthead.mycollab.core.db.metadata.Column("typerid")
    private Integer typerid;

    private static final long serialVersionUID = 1;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_tag_relationship.id
     *
     * @return the value of s_tag_relationship.id
     *
     * @mbggenerated Mon Dec 22 11:25:12 ICT 2014
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_tag_relationship.id
     *
     * @param id the value for s_tag_relationship.id
     *
     * @mbggenerated Mon Dec 22 11:25:12 ICT 2014
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_tag_relationship.tagId
     *
     * @return the value of s_tag_relationship.tagId
     *
     * @mbggenerated Mon Dec 22 11:25:12 ICT 2014
     */
    public Integer getTagid() {
        return tagid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_tag_relationship.tagId
     *
     * @param tagid the value for s_tag_relationship.tagId
     *
     * @mbggenerated Mon Dec 22 11:25:12 ICT 2014
     */
    public void setTagid(Integer tagid) {
        this.tagid = tagid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_tag_relationship.type
     *
     * @return the value of s_tag_relationship.type
     *
     * @mbggenerated Mon Dec 22 11:25:12 ICT 2014
     */
    public String getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_tag_relationship.type
     *
     * @param type the value for s_tag_relationship.type
     *
     * @mbggenerated Mon Dec 22 11:25:12 ICT 2014
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_tag_relationship.typerid
     *
     * @return the value of s_tag_relationship.typerid
     *
     * @mbggenerated Mon Dec 22 11:25:12 ICT 2014
     */
    public Integer getTyperid() {
        return typerid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_tag_relationship.typerid
     *
     * @param typerid the value for s_tag_relationship.typerid
     *
     * @mbggenerated Mon Dec 22 11:25:12 ICT 2014
     */
    public void setTyperid(Integer typerid) {
        this.typerid = typerid;
    }

    public static enum Field {
        id,
        tagid,
        type,
        typerid;

        public boolean equalTo(Object value) {
            return name().equals(value);
        }
    }
}