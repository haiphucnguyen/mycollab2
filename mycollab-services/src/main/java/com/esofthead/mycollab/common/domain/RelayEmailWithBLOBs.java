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
public class RelayEmailWithBLOBs extends RelayEmail {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_relay_mail.bodyContent
     *
     * @mbggenerated Mon Nov 09 12:33:48 ICT 2015
     */
    @Length(max=65535, message="Field value is too long")
    @Column("bodyContent")
    private String bodycontent;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_relay_mail.recipients
     *
     * @mbggenerated Mon Nov 09 12:33:48 ICT 2015
     */
    @Length(max=65535, message="Field value is too long")
    @Column("recipients")
    private String recipients;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_relay_mail.subject
     *
     * @mbggenerated Mon Nov 09 12:33:48 ICT 2015
     */
    @Length(max=65535, message="Field value is too long")
    @Column("subject")
    private String subject;

    private static final long serialVersionUID = 1;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_relay_mail.bodyContent
     *
     * @return the value of s_relay_mail.bodyContent
     *
     * @mbggenerated Mon Nov 09 12:33:48 ICT 2015
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
     * @mbggenerated Mon Nov 09 12:33:48 ICT 2015
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
     * @mbggenerated Mon Nov 09 12:33:48 ICT 2015
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
     * @mbggenerated Mon Nov 09 12:33:48 ICT 2015
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
     * @mbggenerated Mon Nov 09 12:33:48 ICT 2015
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
     * @mbggenerated Mon Nov 09 12:33:48 ICT 2015
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    public enum Field {
        id,
        saccountid,
        fromname,
        fromemail,
        emailhandlerbean,
        bodycontent,
        recipients,
        subject;

        public boolean equalTo(Object value) {
            return name().equals(value);
        }
    }
}