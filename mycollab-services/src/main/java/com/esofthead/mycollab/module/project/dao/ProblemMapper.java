package com.esofthead.mycollab.module.project.dao;

import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.module.project.domain.Problem;
import com.esofthead.mycollab.module.project.domain.ProblemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@SuppressWarnings({ "ucd", "rawtypes" })
public interface ProblemMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_problem
     *
     * @mbggenerated Fri Feb 27 09:55:30 ICT 2015
     */
    int countByExample(ProblemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_problem
     *
     * @mbggenerated Fri Feb 27 09:55:30 ICT 2015
     */
    int deleteByExample(ProblemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_problem
     *
     * @mbggenerated Fri Feb 27 09:55:30 ICT 2015
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_problem
     *
     * @mbggenerated Fri Feb 27 09:55:30 ICT 2015
     */
    int insert(Problem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_problem
     *
     * @mbggenerated Fri Feb 27 09:55:30 ICT 2015
     */
    int insertSelective(Problem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_problem
     *
     * @mbggenerated Fri Feb 27 09:55:30 ICT 2015
     */
    List<Problem> selectByExampleWithBLOBs(ProblemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_problem
     *
     * @mbggenerated Fri Feb 27 09:55:30 ICT 2015
     */
    List<Problem> selectByExample(ProblemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_problem
     *
     * @mbggenerated Fri Feb 27 09:55:30 ICT 2015
     */
    Problem selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_problem
     *
     * @mbggenerated Fri Feb 27 09:55:30 ICT 2015
     */
    int updateByExampleSelective(@Param("record") Problem record, @Param("example") ProblemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_problem
     *
     * @mbggenerated Fri Feb 27 09:55:30 ICT 2015
     */
    int updateByExampleWithBLOBs(@Param("record") Problem record, @Param("example") ProblemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_problem
     *
     * @mbggenerated Fri Feb 27 09:55:30 ICT 2015
     */
    int updateByExample(@Param("record") Problem record, @Param("example") ProblemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_problem
     *
     * @mbggenerated Fri Feb 27 09:55:30 ICT 2015
     */
    int updateByPrimaryKeySelective(Problem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_problem
     *
     * @mbggenerated Fri Feb 27 09:55:30 ICT 2015
     */
    int updateByPrimaryKeyWithBLOBs(Problem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_problem
     *
     * @mbggenerated Fri Feb 27 09:55:30 ICT 2015
     */
    int updateByPrimaryKey(Problem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_problem
     *
     * @mbggenerated Fri Feb 27 09:55:30 ICT 2015
     */
    Integer insertAndReturnKey(Problem value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_problem
     *
     * @mbggenerated Fri Feb 27 09:55:30 ICT 2015
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_problem
     *
     * @mbggenerated Fri Feb 27 09:55:30 ICT 2015
     */
    void massUpdateWithSession(@Param("record") Problem record, @Param("primaryKeys") List primaryKeys);
}