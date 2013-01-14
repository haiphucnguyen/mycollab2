package com.esofthead.mycollab.module.project.dao;

import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.module.project.domain.Problem;
import com.esofthead.mycollab.module.project.domain.ProblemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProblemMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_problem
     *
     * @mbggenerated Mon Jan 14 15:29:26 GMT+07:00 2013
     */
    int countByExample(ProblemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_problem
     *
     * @mbggenerated Mon Jan 14 15:29:26 GMT+07:00 2013
     */
    int deleteByExample(ProblemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_problem
     *
     * @mbggenerated Mon Jan 14 15:29:26 GMT+07:00 2013
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_problem
     *
     * @mbggenerated Mon Jan 14 15:29:26 GMT+07:00 2013
     */
    int insert(Problem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_problem
     *
     * @mbggenerated Mon Jan 14 15:29:26 GMT+07:00 2013
     */
    int insertSelective(Problem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_problem
     *
     * @mbggenerated Mon Jan 14 15:29:26 GMT+07:00 2013
     */
    List<Problem> selectByExample(ProblemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_problem
     *
     * @mbggenerated Mon Jan 14 15:29:26 GMT+07:00 2013
     */
    Problem selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_problem
     *
     * @mbggenerated Mon Jan 14 15:29:26 GMT+07:00 2013
     */
    int updateByExampleSelective(@Param("record") Problem record, @Param("example") ProblemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_problem
     *
     * @mbggenerated Mon Jan 14 15:29:26 GMT+07:00 2013
     */
    int updateByExample(@Param("record") Problem record, @Param("example") ProblemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_problem
     *
     * @mbggenerated Mon Jan 14 15:29:26 GMT+07:00 2013
     */
    int updateByPrimaryKeySelective(Problem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_problem
     *
     * @mbggenerated Mon Jan 14 15:29:26 GMT+07:00 2013
     */
    int updateByPrimaryKey(Problem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_problem
     *
     * @mbggenerated Mon Jan 14 15:29:26 GMT+07:00 2013
     */
    Integer insertAndReturnKey(Problem value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_problem
     *
     * @mbggenerated Mon Jan 14 15:29:26 GMT+07:00 2013
     */
    void removeKeysWithSession(List primaryKeys);
}