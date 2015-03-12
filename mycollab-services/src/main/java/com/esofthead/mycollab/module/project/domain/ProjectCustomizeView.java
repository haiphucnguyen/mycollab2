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
/*Domain class of table m_prj_customize_view*/
package com.esofthead.mycollab.module.project.domain;

import com.esofthead.mycollab.core.arguments.ValuedBean;

@SuppressWarnings("ucd")
@com.esofthead.mycollab.core.db.metadata.Table("m_prj_customize_view")
public class ProjectCustomizeView extends ValuedBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_customize_view.id
     *
     * @mbggenerated Wed Mar 11 09:10:41 ICT 2015
     */
    @com.esofthead.mycollab.core.db.metadata.Column("id")
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_customize_view.projectId
     *
     * @mbggenerated Wed Mar 11 09:10:41 ICT 2015
     */
    @com.esofthead.mycollab.core.db.metadata.Column("projectId")
    private Integer projectid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_customize_view.displayMessage
     *
     * @mbggenerated Wed Mar 11 09:10:41 ICT 2015
     */
    @com.esofthead.mycollab.core.db.metadata.Column("displayMessage")
    private Boolean displaymessage;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_customize_view.displayMilestone
     *
     * @mbggenerated Wed Mar 11 09:10:41 ICT 2015
     */
    @com.esofthead.mycollab.core.db.metadata.Column("displayMilestone")
    private Boolean displaymilestone;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_customize_view.displayTask
     *
     * @mbggenerated Wed Mar 11 09:10:41 ICT 2015
     */
    @com.esofthead.mycollab.core.db.metadata.Column("displayTask")
    private Boolean displaytask;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_customize_view.displayBug
     *
     * @mbggenerated Wed Mar 11 09:10:41 ICT 2015
     */
    @com.esofthead.mycollab.core.db.metadata.Column("displayBug")
    private Boolean displaybug;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_customize_view.displayStandup
     *
     * @mbggenerated Wed Mar 11 09:10:41 ICT 2015
     */
    @com.esofthead.mycollab.core.db.metadata.Column("displayStandup")
    private Boolean displaystandup;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_customize_view.displayProblem
     *
     * @mbggenerated Wed Mar 11 09:10:41 ICT 2015
     */
    @com.esofthead.mycollab.core.db.metadata.Column("displayProblem")
    private Boolean displayproblem;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_customize_view.displayRisk
     *
     * @mbggenerated Wed Mar 11 09:10:41 ICT 2015
     */
    @com.esofthead.mycollab.core.db.metadata.Column("displayRisk")
    private Boolean displayrisk;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_customize_view.displayTimeLogging
     *
     * @mbggenerated Wed Mar 11 09:10:41 ICT 2015
     */
    @com.esofthead.mycollab.core.db.metadata.Column("displayTimeLogging")
    private Boolean displaytimelogging;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_customize_view.displayPage
     *
     * @mbggenerated Wed Mar 11 09:10:41 ICT 2015
     */
    @com.esofthead.mycollab.core.db.metadata.Column("displayPage")
    private Boolean displaypage;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_customize_view.displayFile
     *
     * @mbggenerated Wed Mar 11 09:10:41 ICT 2015
     */
    @com.esofthead.mycollab.core.db.metadata.Column("displayFile")
    private Boolean displayfile;

    private static final long serialVersionUID = 1;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_customize_view.id
     *
     * @return the value of m_prj_customize_view.id
     *
     * @mbggenerated Wed Mar 11 09:10:41 ICT 2015
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_customize_view.id
     *
     * @param id the value for m_prj_customize_view.id
     *
     * @mbggenerated Wed Mar 11 09:10:41 ICT 2015
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_customize_view.projectId
     *
     * @return the value of m_prj_customize_view.projectId
     *
     * @mbggenerated Wed Mar 11 09:10:41 ICT 2015
     */
    public Integer getProjectid() {
        return projectid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_customize_view.projectId
     *
     * @param projectid the value for m_prj_customize_view.projectId
     *
     * @mbggenerated Wed Mar 11 09:10:41 ICT 2015
     */
    public void setProjectid(Integer projectid) {
        this.projectid = projectid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_customize_view.displayMessage
     *
     * @return the value of m_prj_customize_view.displayMessage
     *
     * @mbggenerated Wed Mar 11 09:10:41 ICT 2015
     */
    public Boolean getDisplaymessage() {
        return displaymessage;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_customize_view.displayMessage
     *
     * @param displaymessage the value for m_prj_customize_view.displayMessage
     *
     * @mbggenerated Wed Mar 11 09:10:41 ICT 2015
     */
    public void setDisplaymessage(Boolean displaymessage) {
        this.displaymessage = displaymessage;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_customize_view.displayMilestone
     *
     * @return the value of m_prj_customize_view.displayMilestone
     *
     * @mbggenerated Wed Mar 11 09:10:41 ICT 2015
     */
    public Boolean getDisplaymilestone() {
        return displaymilestone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_customize_view.displayMilestone
     *
     * @param displaymilestone the value for m_prj_customize_view.displayMilestone
     *
     * @mbggenerated Wed Mar 11 09:10:41 ICT 2015
     */
    public void setDisplaymilestone(Boolean displaymilestone) {
        this.displaymilestone = displaymilestone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_customize_view.displayTask
     *
     * @return the value of m_prj_customize_view.displayTask
     *
     * @mbggenerated Wed Mar 11 09:10:41 ICT 2015
     */
    public Boolean getDisplaytask() {
        return displaytask;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_customize_view.displayTask
     *
     * @param displaytask the value for m_prj_customize_view.displayTask
     *
     * @mbggenerated Wed Mar 11 09:10:41 ICT 2015
     */
    public void setDisplaytask(Boolean displaytask) {
        this.displaytask = displaytask;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_customize_view.displayBug
     *
     * @return the value of m_prj_customize_view.displayBug
     *
     * @mbggenerated Wed Mar 11 09:10:41 ICT 2015
     */
    public Boolean getDisplaybug() {
        return displaybug;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_customize_view.displayBug
     *
     * @param displaybug the value for m_prj_customize_view.displayBug
     *
     * @mbggenerated Wed Mar 11 09:10:41 ICT 2015
     */
    public void setDisplaybug(Boolean displaybug) {
        this.displaybug = displaybug;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_customize_view.displayStandup
     *
     * @return the value of m_prj_customize_view.displayStandup
     *
     * @mbggenerated Wed Mar 11 09:10:41 ICT 2015
     */
    public Boolean getDisplaystandup() {
        return displaystandup;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_customize_view.displayStandup
     *
     * @param displaystandup the value for m_prj_customize_view.displayStandup
     *
     * @mbggenerated Wed Mar 11 09:10:41 ICT 2015
     */
    public void setDisplaystandup(Boolean displaystandup) {
        this.displaystandup = displaystandup;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_customize_view.displayProblem
     *
     * @return the value of m_prj_customize_view.displayProblem
     *
     * @mbggenerated Wed Mar 11 09:10:41 ICT 2015
     */
    public Boolean getDisplayproblem() {
        return displayproblem;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_customize_view.displayProblem
     *
     * @param displayproblem the value for m_prj_customize_view.displayProblem
     *
     * @mbggenerated Wed Mar 11 09:10:41 ICT 2015
     */
    public void setDisplayproblem(Boolean displayproblem) {
        this.displayproblem = displayproblem;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_customize_view.displayRisk
     *
     * @return the value of m_prj_customize_view.displayRisk
     *
     * @mbggenerated Wed Mar 11 09:10:41 ICT 2015
     */
    public Boolean getDisplayrisk() {
        return displayrisk;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_customize_view.displayRisk
     *
     * @param displayrisk the value for m_prj_customize_view.displayRisk
     *
     * @mbggenerated Wed Mar 11 09:10:41 ICT 2015
     */
    public void setDisplayrisk(Boolean displayrisk) {
        this.displayrisk = displayrisk;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_customize_view.displayTimeLogging
     *
     * @return the value of m_prj_customize_view.displayTimeLogging
     *
     * @mbggenerated Wed Mar 11 09:10:41 ICT 2015
     */
    public Boolean getDisplaytimelogging() {
        return displaytimelogging;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_customize_view.displayTimeLogging
     *
     * @param displaytimelogging the value for m_prj_customize_view.displayTimeLogging
     *
     * @mbggenerated Wed Mar 11 09:10:41 ICT 2015
     */
    public void setDisplaytimelogging(Boolean displaytimelogging) {
        this.displaytimelogging = displaytimelogging;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_customize_view.displayPage
     *
     * @return the value of m_prj_customize_view.displayPage
     *
     * @mbggenerated Wed Mar 11 09:10:41 ICT 2015
     */
    public Boolean getDisplaypage() {
        return displaypage;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_customize_view.displayPage
     *
     * @param displaypage the value for m_prj_customize_view.displayPage
     *
     * @mbggenerated Wed Mar 11 09:10:41 ICT 2015
     */
    public void setDisplaypage(Boolean displaypage) {
        this.displaypage = displaypage;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_customize_view.displayFile
     *
     * @return the value of m_prj_customize_view.displayFile
     *
     * @mbggenerated Wed Mar 11 09:10:41 ICT 2015
     */
    public Boolean getDisplayfile() {
        return displayfile;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_customize_view.displayFile
     *
     * @param displayfile the value for m_prj_customize_view.displayFile
     *
     * @mbggenerated Wed Mar 11 09:10:41 ICT 2015
     */
    public void setDisplayfile(Boolean displayfile) {
        this.displayfile = displayfile;
    }

    public static enum Field {
        id,
        projectid,
        displaymessage,
        displaymilestone,
        displaytask,
        displaybug,
        displaystandup,
        displayproblem,
        displayrisk,
        displaytimelogging,
        displaypage,
        displayfile;

        public boolean equalTo(Object value) {
            return name().equals(value);
        }
    }
}