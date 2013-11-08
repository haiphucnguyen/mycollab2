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

public class RelayEmailWithBLOBs extends RelayEmail {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_relay_mail.bodyContent
     *
     * @mbggenerated Sun Oct 13 15:52:38 ICT 2013
     */
    @org.hibernate.validator.constraints.Length(max=65535, message="Field value is too long")
    private String bodycontent;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_relay_mail.recipients
     *
     * @mbggenerated Sun Oct 13 15:52:38 ICT 2013
     */
    @org.hibernate.validator.constraints.Length(max=65535, message="Field value is too long")
    private String recipients;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_relay_mail.subject
     *
     * @mbggenerated Sun Oct 13 15:52:38 ICT 2013
     */
    @org.hibernate.validator.constraints.Length(max=65535, message="Field value is too long")
    private String subject;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_relay_mail.bodyContent
     *
     * @return the value of s_relay_mail.bodyContent
     *
     * @mbggenerated Sun Oct 13 15:52:38 ICT 2013
     */
    public String getBodycontent() {
        return bodycontent;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_relay_mail.bodyContent
     *
     * @param bodycontent the value for s_relay_mail.bodyContent
     *
     * @mbggenerated Sun Oct 13 15:52:38 ICT 2013
     */
    public void setBodycontent(String bodycontent) {
        this.bodycontent = bodycontent;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_relay_mail.recipients
     *
     * @return the value of s_relay_mail.recipients
     *
     * @mbggenerated Sun Oct 13 15:52:38 ICT 2013
     */
    public String getRecipients() {
        return recipients;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_relay_mail.recipients
     *
     * @param recipients the value for s_relay_mail.recipients
     *
     * @mbggenerated Sun Oct 13 15:52:38 ICT 2013
     */
    public void setRecipients(String recipients) {
        this.recipients = recipients;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_relay_mail.subject
     *
     * @return the value of s_relay_mail.subject
     *
     * @mbggenerated Sun Oct 13 15:52:38 ICT 2013
     */
    public String getSubject() {
        return subject;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_relay_mail.subject
     *
     * @param subject the value for s_relay_mail.subject
     *
     * @mbggenerated Sun Oct 13 15:52:38 ICT 2013
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }
}