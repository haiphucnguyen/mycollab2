/**
 * mycollab-services - Parent pom providing dependency and plugin management for applications
		built with Maven
 * Copyright © 2017 MyCollab (support@mycollab.com)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.mycollab.common.domain;

import com.mycollab.db.metadata.Column;
import com.mycollab.db.metadata.Table;
import org.hibernate.validator.constraints.Length;

@SuppressWarnings("ucd")
public class CommentWithBLOBs extends Comment {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_comment.comment
     *
     * @mbg.generated Tue Aug 01 11:17:32 ICT 2017
     */
    @Length(max=65535, message="Field value is too long")
    @Column("comment")
    private String comment;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_comment.typeId
     *
     * @mbg.generated Tue Aug 01 11:17:32 ICT 2017
     */
    @Length(max=65535, message="Field value is too long")
    @Column("typeId")
    private String typeid;

    private static final long serialVersionUID = 1;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_comment.comment
     *
     * @return the value of m_comment.comment
     *
     * @mbg.generated Tue Aug 01 11:17:32 ICT 2017
     */
    public String getComment() {
        return comment;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_comment.comment
     *
     * @param comment the value for m_comment.comment
     *
     * @mbg.generated Tue Aug 01 11:17:32 ICT 2017
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_comment.typeId
     *
     * @return the value of m_comment.typeId
     *
     * @mbg.generated Tue Aug 01 11:17:32 ICT 2017
     */
    public String getTypeid() {
        return typeid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_comment.typeId
     *
     * @param typeid the value for m_comment.typeId
     *
     * @mbg.generated Tue Aug 01 11:17:32 ICT 2017
     */
    public void setTypeid(String typeid) {
        this.typeid = typeid;
    }

    public enum Field {
        id,
        createduser,
        createdtime,
        type,
        saccountid,
        extratypeid,
        comment,
        typeid;

        public boolean equalTo(Object value) {
            return name().equals(value);
        }
    }
}