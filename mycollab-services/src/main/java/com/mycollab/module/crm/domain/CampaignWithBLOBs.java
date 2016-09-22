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
package com.mycollab.module.crm.domain;

import com.mycollab.db.metadata.Column;
import com.mycollab.db.metadata.Table;
import org.hibernate.validator.constraints.Length;

@SuppressWarnings("ucd")
public class CampaignWithBLOBs extends Campaign {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_campaign.impressionnote
     *
     * @mbg.generated Thu Sep 22 13:23:56 ICT 2016
     */
    @Length(max=16777215, message="Field value is too long")
    @Column("impressionnote")
    private String impressionnote;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_campaign.objective
     *
     * @mbg.generated Thu Sep 22 13:23:56 ICT 2016
     */
    @Length(max=16777215, message="Field value is too long")
    @Column("objective")
    private String objective;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_campaign.description
     *
     * @mbg.generated Thu Sep 22 13:23:56 ICT 2016
     */
    @Length(max=16777215, message="Field value is too long")
    @Column("description")
    private String description;

    private static final long serialVersionUID = 1;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_campaign.impressionnote
     *
     * @return the value of m_crm_campaign.impressionnote
     *
     * @mbg.generated Thu Sep 22 13:23:56 ICT 2016
     */
    public String getImpressionnote() {
        return impressionnote;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_campaign.impressionnote
     *
     * @param impressionnote the value for m_crm_campaign.impressionnote
     *
     * @mbg.generated Thu Sep 22 13:23:56 ICT 2016
     */
    public void setImpressionnote(String impressionnote) {
        this.impressionnote = impressionnote;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_campaign.objective
     *
     * @return the value of m_crm_campaign.objective
     *
     * @mbg.generated Thu Sep 22 13:23:56 ICT 2016
     */
    public String getObjective() {
        return objective;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_campaign.objective
     *
     * @param objective the value for m_crm_campaign.objective
     *
     * @mbg.generated Thu Sep 22 13:23:56 ICT 2016
     */
    public void setObjective(String objective) {
        this.objective = objective;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_campaign.description
     *
     * @return the value of m_crm_campaign.description
     *
     * @mbg.generated Thu Sep 22 13:23:56 ICT 2016
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_campaign.description
     *
     * @param description the value for m_crm_campaign.description
     *
     * @mbg.generated Thu Sep 22 13:23:56 ICT 2016
     */
    public void setDescription(String description) {
        this.description = description;
    }

    public enum Field {
        id,
        campaignname,
        startdate,
        enddate,
        currencyid,
        budget,
        actualcost,
        expectedrevenue,
        expectedcost,
        impression,
        createdtime,
        createduser,
        saccountid,
        status,
        type,
        assignuser,
        lastupdatedtime,
        avatarid,
        impressionnote,
        objective,
        description;

        public boolean equalTo(Object value) {
            return name().equals(value);
        }
    }
}