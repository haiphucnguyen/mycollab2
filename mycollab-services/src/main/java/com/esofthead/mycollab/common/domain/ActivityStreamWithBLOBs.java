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
package com.esofthead.mycollab.common.domain;

import com.esofthead.mycollab.core.db.metadata.Column;
import com.esofthead.mycollab.core.db.metadata.Table;
import org.hibernate.validator.constraints.Length;

@SuppressWarnings("ucd")
public class ActivityStreamWithBLOBs extends ActivityStream {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_activitystream.typeId
     *
     * @mbggenerated Thu Jun 30 10:50:02 ICT 2016
     */
    @Length(max=65535, message="Field value is too long")
    @Column("typeId")
    private String typeid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_activitystream.nameField
     *
     * @mbggenerated Thu Jun 30 10:50:02 ICT 2016
     */
    @Length(max=65535, message="Field value is too long")
    @Column("nameField")
    private String namefield;

    private static final long serialVersionUID = 1;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_activitystream.typeId
     *
     * @return the value of s_activitystream.typeId
     *
     * @mbggenerated Thu Jun 30 10:50:02 ICT 2016
     */
    public String getTypeid() {
        return typeid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_activitystream.typeId
     *
     * @param typeid the value for s_activitystream.typeId
     *
     * @mbggenerated Thu Jun 30 10:50:02 ICT 2016
     */
    public void setTypeid(String typeid) {
        this.typeid = typeid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_activitystream.nameField
     *
     * @return the value of s_activitystream.nameField
     *
     * @mbggenerated Thu Jun 30 10:50:02 ICT 2016
     */
    public String getNamefield() {
        return namefield;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_activitystream.nameField
     *
     * @param namefield the value for s_activitystream.nameField
     *
     * @mbggenerated Thu Jun 30 10:50:02 ICT 2016
     */
    public void setNamefield(String namefield) {
        this.namefield = namefield;
    }

    public enum Field {
        id,
        saccountid,
        type,
        createdtime,
        action,
        createduser,
        module,
        extratypeid,
        typeid,
        namefield;

        public boolean equalTo(Object value) {
            return name().equals(value);
        }
    }
}