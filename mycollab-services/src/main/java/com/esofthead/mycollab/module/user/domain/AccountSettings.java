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
/*Domain class of table s_account_settings*/
package com.esofthead.mycollab.module.user.domain;

import com.esofthead.mycollab.core.arguments.ValuedBean;

@SuppressWarnings("ucd")
@com.esofthead.mycollab.core.db.metadata.Table("s_account_settings")
public class AccountSettings extends ValuedBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_account_settings.id
     *
     * @mbggenerated Mon May 18 10:06:57 ICT 2015
     */
    @com.esofthead.mycollab.core.db.metadata.Column("id")
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_account_settings.sAccountId
     *
     * @mbggenerated Mon May 18 10:06:57 ICT 2015
     */
    @com.esofthead.mycollab.core.db.metadata.Column("sAccountId")
    private Integer saccountid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_account_settings.logoPath
     *
     * @mbggenerated Mon May 18 10:06:57 ICT 2015
     */
    @org.hibernate.validator.constraints.Length(max=255, message="Field value is too long")
    @com.esofthead.mycollab.core.db.metadata.Column("logoPath")
    private String logopath;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_account_settings.defaultTimezone
     *
     * @mbggenerated Mon May 18 10:06:57 ICT 2015
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    @com.esofthead.mycollab.core.db.metadata.Column("defaultTimezone")
    private String defaulttimezone;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_account_settings.defaultThemeId
     *
     * @mbggenerated Mon May 18 10:06:57 ICT 2015
     */
    @com.esofthead.mycollab.core.db.metadata.Column("defaultThemeId")
    private Integer defaultthemeid;

    private static final long serialVersionUID = 1;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_account_settings.id
     *
     * @return the value of s_account_settings.id
     *
     * @mbggenerated Mon May 18 10:06:57 ICT 2015
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_account_settings.id
     *
     * @param id the value for s_account_settings.id
     *
     * @mbggenerated Mon May 18 10:06:57 ICT 2015
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_account_settings.sAccountId
     *
     * @return the value of s_account_settings.sAccountId
     *
     * @mbggenerated Mon May 18 10:06:57 ICT 2015
     */
    public Integer getSaccountid() {
        return saccountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_account_settings.sAccountId
     *
     * @param saccountid the value for s_account_settings.sAccountId
     *
     * @mbggenerated Mon May 18 10:06:57 ICT 2015
     */
    public void setSaccountid(Integer saccountid) {
        this.saccountid = saccountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_account_settings.logoPath
     *
     * @return the value of s_account_settings.logoPath
     *
     * @mbggenerated Mon May 18 10:06:57 ICT 2015
     */
    public String getLogopath() {
        return logopath;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_account_settings.logoPath
     *
     * @param logopath the value for s_account_settings.logoPath
     *
     * @mbggenerated Mon May 18 10:06:57 ICT 2015
     */
    public void setLogopath(String logopath) {
        this.logopath = logopath;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_account_settings.defaultTimezone
     *
     * @return the value of s_account_settings.defaultTimezone
     *
     * @mbggenerated Mon May 18 10:06:57 ICT 2015
     */
    public String getDefaulttimezone() {
        return defaulttimezone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_account_settings.defaultTimezone
     *
     * @param defaulttimezone the value for s_account_settings.defaultTimezone
     *
     * @mbggenerated Mon May 18 10:06:57 ICT 2015
     */
    public void setDefaulttimezone(String defaulttimezone) {
        this.defaulttimezone = defaulttimezone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_account_settings.defaultThemeId
     *
     * @return the value of s_account_settings.defaultThemeId
     *
     * @mbggenerated Mon May 18 10:06:57 ICT 2015
     */
    public Integer getDefaultthemeid() {
        return defaultthemeid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_account_settings.defaultThemeId
     *
     * @param defaultthemeid the value for s_account_settings.defaultThemeId
     *
     * @mbggenerated Mon May 18 10:06:57 ICT 2015
     */
    public void setDefaultthemeid(Integer defaultthemeid) {
        this.defaultthemeid = defaultthemeid;
    }

    public static enum Field {
        id,
        saccountid,
        logopath,
        defaulttimezone,
        defaultthemeid;

        public boolean equalTo(Object value) {
            return name().equals(value);
        }
    }
}