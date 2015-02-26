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

@SuppressWarnings("ucd")
@com.esofthead.mycollab.core.db.metadata.Table("s_tag")
public class Tag extends ValuedBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_tag.id
     *
     * @mbggenerated Thu Feb 26 15:08:43 ICT 2015
     */
    @com.esofthead.mycollab.core.db.metadata.Column("id")
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_tag.name
     *
     * @mbggenerated Thu Feb 26 15:08:43 ICT 2015
     */
    @org.hibernate.validator.constraints.Length(max=100, message="Field value is too long")
    @com.esofthead.mycollab.core.db.metadata.Column("name")
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_tag.type
     *
     * @mbggenerated Thu Feb 26 15:08:43 ICT 2015
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    @com.esofthead.mycollab.core.db.metadata.Column("type")
    private String type;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_tag.typerid
     *
     * @mbggenerated Thu Feb 26 15:08:43 ICT 2015
     */
    @org.hibernate.validator.constraints.Length(max=100, message="Field value is too long")
    @com.esofthead.mycollab.core.db.metadata.Column("typerid")
    private String typerid;

    private static final long serialVersionUID = 1;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_tag.id
     *
     * @return the value of s_tag.id
     *
     * @mbggenerated Thu Feb 26 15:08:43 ICT 2015
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
     * @mbggenerated Thu Feb 26 15:08:43 ICT 2015
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
     * @mbggenerated Thu Feb 26 15:08:43 ICT 2015
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
     * @mbggenerated Thu Feb 26 15:08:43 ICT 2015
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_tag.type
     *
     * @return the value of s_tag.type
     *
     * @mbggenerated Thu Feb 26 15:08:43 ICT 2015
     */
    public String getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_tag.type
     *
     * @param type the value for s_tag.type
     *
     * @mbggenerated Thu Feb 26 15:08:43 ICT 2015
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_tag.typerid
     *
     * @return the value of s_tag.typerid
     *
     * @mbggenerated Thu Feb 26 15:08:43 ICT 2015
     */
    public String getTyperid() {
        return typerid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_tag.typerid
     *
     * @param typerid the value for s_tag.typerid
     *
     * @mbggenerated Thu Feb 26 15:08:43 ICT 2015
     */
    public void setTyperid(String typerid) {
        this.typerid = typerid;
    }

    public static enum Field {
        id,
        name,
        type,
        typerid;

        public boolean equalTo(Object value) {
            return name().equals(value);
        }
    }
}