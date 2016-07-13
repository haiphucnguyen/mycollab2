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
/*Domain class of table m_ecm_driveinfo*/
package com.mycollab.module.ecm.domain;

import com.mycollab.core.arguments.ValuedBean;
import com.mycollab.db.metadata.Column;
import com.mycollab.db.metadata.Table;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@SuppressWarnings("ucd")
@Table("m_ecm_driveinfo")
public class DriveInfo extends ValuedBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_ecm_driveinfo.id
     *
     * @mbggenerated Thu Jul 14 01:03:48 ICT 2016
     */
    @Column("id")
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_ecm_driveinfo.sAccountId
     *
     * @mbggenerated Thu Jul 14 01:03:48 ICT 2016
     */
    @Column("sAccountId")
    private Integer saccountid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_ecm_driveinfo.usedVolume
     *
     * @mbggenerated Thu Jul 14 01:03:48 ICT 2016
     */
    @Column("usedVolume")
    private Long usedvolume;

    private static final long serialVersionUID = 1;

    public final boolean equals(Object obj) {
        if (obj == null) { return false;}
        if (obj == this) { return true;}
        if (!obj.getClass().isAssignableFrom(getClass())) { return false;}
        DriveInfo item = (DriveInfo)obj;
        return new EqualsBuilder().append(id, item.id).build();
    }

    public final int hashCode() {
        return new HashCodeBuilder(59, 565).append(id).build();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_ecm_driveinfo.id
     *
     * @return the value of m_ecm_driveinfo.id
     *
     * @mbggenerated Thu Jul 14 01:03:48 ICT 2016
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_ecm_driveinfo.id
     *
     * @param id the value for m_ecm_driveinfo.id
     *
     * @mbggenerated Thu Jul 14 01:03:48 ICT 2016
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_ecm_driveinfo.sAccountId
     *
     * @return the value of m_ecm_driveinfo.sAccountId
     *
     * @mbggenerated Thu Jul 14 01:03:48 ICT 2016
     */
    public Integer getSaccountid() {
        return saccountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_ecm_driveinfo.sAccountId
     *
     * @param saccountid the value for m_ecm_driveinfo.sAccountId
     *
     * @mbggenerated Thu Jul 14 01:03:48 ICT 2016
     */
    public void setSaccountid(Integer saccountid) {
        this.saccountid = saccountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_ecm_driveinfo.usedVolume
     *
     * @return the value of m_ecm_driveinfo.usedVolume
     *
     * @mbggenerated Thu Jul 14 01:03:48 ICT 2016
     */
    public Long getUsedvolume() {
        return usedvolume;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_ecm_driveinfo.usedVolume
     *
     * @param usedvolume the value for m_ecm_driveinfo.usedVolume
     *
     * @mbggenerated Thu Jul 14 01:03:48 ICT 2016
     */
    public void setUsedvolume(Long usedvolume) {
        this.usedvolume = usedvolume;
    }

    public enum Field {
        id,
        saccountid,
        usedvolume;

        public boolean equalTo(Object value) {
            return name().equals(value);
        }
    }
}