package com.mycollab.module.project.domain;

import com.mycollab.db.metadata.Column;
import com.mycollab.db.metadata.Table;
import org.hibernate.validator.constraints.Length;

@SuppressWarnings("ucd")
public class StandupReportWithBLOBs extends StandupReport {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_standup.whatlastday
     *
     * @mbg.generated Sat Feb 09 11:42:24 CST 2019
     */
    @Length(max=65535, message="Field value is too long")
    @Column("whatlastday")
    private String whatlastday;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_standup.whattoday
     *
     * @mbg.generated Sat Feb 09 11:42:24 CST 2019
     */
    @Length(max=65535, message="Field value is too long")
    @Column("whattoday")
    private String whattoday;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_standup.whatproblem
     *
     * @mbg.generated Sat Feb 09 11:42:24 CST 2019
     */
    @Length(max=65535, message="Field value is too long")
    @Column("whatproblem")
    private String whatproblem;

    private static final long serialVersionUID = 1;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_standup.whatlastday
     *
     * @return the value of m_prj_standup.whatlastday
     *
     * @mbg.generated Sat Feb 09 11:42:24 CST 2019
     */
    public String getWhatlastday() {
        return whatlastday;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_standup
     *
     * @mbg.generated Sat Feb 09 11:42:24 CST 2019
     */
    public StandupReportWithBLOBs withWhatlastday(String whatlastday) {
        this.setWhatlastday(whatlastday);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_standup.whatlastday
     *
     * @param whatlastday the value for m_prj_standup.whatlastday
     *
     * @mbg.generated Sat Feb 09 11:42:24 CST 2019
     */
    public void setWhatlastday(String whatlastday) {
        this.whatlastday = whatlastday;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_standup.whattoday
     *
     * @return the value of m_prj_standup.whattoday
     *
     * @mbg.generated Sat Feb 09 11:42:24 CST 2019
     */
    public String getWhattoday() {
        return whattoday;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_standup
     *
     * @mbg.generated Sat Feb 09 11:42:24 CST 2019
     */
    public StandupReportWithBLOBs withWhattoday(String whattoday) {
        this.setWhattoday(whattoday);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_standup.whattoday
     *
     * @param whattoday the value for m_prj_standup.whattoday
     *
     * @mbg.generated Sat Feb 09 11:42:24 CST 2019
     */
    public void setWhattoday(String whattoday) {
        this.whattoday = whattoday;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_standup.whatproblem
     *
     * @return the value of m_prj_standup.whatproblem
     *
     * @mbg.generated Sat Feb 09 11:42:24 CST 2019
     */
    public String getWhatproblem() {
        return whatproblem;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_standup
     *
     * @mbg.generated Sat Feb 09 11:42:24 CST 2019
     */
    public StandupReportWithBLOBs withWhatproblem(String whatproblem) {
        this.setWhatproblem(whatproblem);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_standup.whatproblem
     *
     * @param whatproblem the value for m_prj_standup.whatproblem
     *
     * @mbg.generated Sat Feb 09 11:42:24 CST 2019
     */
    public void setWhatproblem(String whatproblem) {
        this.whatproblem = whatproblem;
    }

    public enum Field {
        id,
        saccountid,
        projectid,
        forday,
        logby,
        createdtime,
        lastupdatedtime,
        whatlastday,
        whattoday,
        whatproblem;

        public boolean equalTo(Object value) {
            return name().equals(value);
        }
    }
}