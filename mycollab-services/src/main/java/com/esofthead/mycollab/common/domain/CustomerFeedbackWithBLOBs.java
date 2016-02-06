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
public class CustomerFeedbackWithBLOBs extends CustomerFeedback {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_customer_feedback.reasonToLeave
     *
     * @mbggenerated Sat Feb 06 17:34:26 ICT 2016
     */
    @Length(max=65535, message="Field value is too long")
    @Column("reasonToLeave")
    private String reasontoleave;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_customer_feedback.reasonToBack
     *
     * @mbggenerated Sat Feb 06 17:34:26 ICT 2016
     */
    @Length(max=65535, message="Field value is too long")
    @Column("reasonToBack")
    private String reasontoback;

    private static final long serialVersionUID = 1;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_customer_feedback.reasonToLeave
     *
     * @return the value of s_customer_feedback.reasonToLeave
     *
     * @mbggenerated Sat Feb 06 17:34:26 ICT 2016
     */
    public String getReasontoleave() {
        return reasontoleave;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_customer_feedback.reasonToLeave
     *
     * @param reasontoleave the value for s_customer_feedback.reasonToLeave
     *
     * @mbggenerated Sat Feb 06 17:34:26 ICT 2016
     */
    public void setReasontoleave(String reasontoleave) {
        this.reasontoleave = reasontoleave;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_customer_feedback.reasonToBack
     *
     * @return the value of s_customer_feedback.reasonToBack
     *
     * @mbggenerated Sat Feb 06 17:34:26 ICT 2016
     */
    public String getReasontoback() {
        return reasontoback;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_customer_feedback.reasonToBack
     *
     * @param reasontoback the value for s_customer_feedback.reasonToBack
     *
     * @mbggenerated Sat Feb 06 17:34:26 ICT 2016
     */
    public void setReasontoback(String reasontoback) {
        this.reasontoback = reasontoback;
    }

    public enum Field {
        id,
        saccountid,
        username,
        leavetype,
        othertool,
        reasontoleave,
        reasontoback;

        public boolean equalTo(Object value) {
            return name().equals(value);
        }
    }
}