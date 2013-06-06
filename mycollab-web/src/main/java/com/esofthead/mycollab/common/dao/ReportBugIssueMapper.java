package com.esofthead.mycollab.common.dao;

import com.esofthead.mycollab.common.domain.ReportBugIssueExample;
import com.esofthead.mycollab.common.domain.ReportBugIssueWithBLOBs;
import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ReportBugIssueMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_report_bug_issue
     *
     * @mbggenerated Thu Jun 06 11:18:20 GMT+07:00 2013
     */
    int countByExample(ReportBugIssueExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_report_bug_issue
     *
     * @mbggenerated Thu Jun 06 11:18:20 GMT+07:00 2013
     */
    int deleteByExample(ReportBugIssueExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_report_bug_issue
     *
     * @mbggenerated Thu Jun 06 11:18:20 GMT+07:00 2013
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_report_bug_issue
     *
     * @mbggenerated Thu Jun 06 11:18:20 GMT+07:00 2013
     */
    int insert(ReportBugIssueWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_report_bug_issue
     *
     * @mbggenerated Thu Jun 06 11:18:20 GMT+07:00 2013
     */
    int insertSelective(ReportBugIssueWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_report_bug_issue
     *
     * @mbggenerated Thu Jun 06 11:18:20 GMT+07:00 2013
     */
    List<ReportBugIssueWithBLOBs> selectByExampleWithBLOBs(ReportBugIssueExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_report_bug_issue
     *
     * @mbggenerated Thu Jun 06 11:18:20 GMT+07:00 2013
     */
    ReportBugIssueWithBLOBs selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_report_bug_issue
     *
     * @mbggenerated Thu Jun 06 11:18:20 GMT+07:00 2013
     */
    int updateByExampleSelective(@Param("record") ReportBugIssueWithBLOBs record, @Param("example") ReportBugIssueExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_report_bug_issue
     *
     * @mbggenerated Thu Jun 06 11:18:20 GMT+07:00 2013
     */
    int updateByExampleWithBLOBs(@Param("record") ReportBugIssueWithBLOBs record, @Param("example") ReportBugIssueExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_report_bug_issue
     *
     * @mbggenerated Thu Jun 06 11:18:20 GMT+07:00 2013
     */
    int updateByPrimaryKeySelective(ReportBugIssueWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_report_bug_issue
     *
     * @mbggenerated Thu Jun 06 11:18:20 GMT+07:00 2013
     */
    Integer insertAndReturnKey(ReportBugIssueWithBLOBs value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_report_bug_issue
     *
     * @mbggenerated Thu Jun 06 11:18:20 GMT+07:00 2013
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_report_bug_issue
     *
     * @mbggenerated Thu Jun 06 11:18:20 GMT+07:00 2013
     */
    void massUpdateWithSession(@Param("record") ReportBugIssueWithBLOBs record, @Param("primaryKeys") List primaryKeys);
}