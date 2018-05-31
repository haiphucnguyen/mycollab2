package com.mycollab.common.dao;

import com.mycollab.common.domain.OptionVal;
import com.mycollab.common.domain.OptionValExample;
import com.mycollab.db.persistence.ICrudGenericDAO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@SuppressWarnings({ "ucd", "rawtypes" })
public interface OptionValMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_options
     *
     * @mbg.generated Thu May 31 21:45:18 ICT 2018
     */
    long countByExample(OptionValExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_options
     *
     * @mbg.generated Thu May 31 21:45:18 ICT 2018
     */
    int deleteByExample(OptionValExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_options
     *
     * @mbg.generated Thu May 31 21:45:18 ICT 2018
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_options
     *
     * @mbg.generated Thu May 31 21:45:18 ICT 2018
     */
    int insert(OptionVal record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_options
     *
     * @mbg.generated Thu May 31 21:45:18 ICT 2018
     */
    int insertSelective(OptionVal record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_options
     *
     * @mbg.generated Thu May 31 21:45:18 ICT 2018
     */
    List<OptionVal> selectByExampleWithBLOBs(OptionValExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_options
     *
     * @mbg.generated Thu May 31 21:45:18 ICT 2018
     */
    List<OptionVal> selectByExample(OptionValExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_options
     *
     * @mbg.generated Thu May 31 21:45:18 ICT 2018
     */
    OptionVal selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_options
     *
     * @mbg.generated Thu May 31 21:45:18 ICT 2018
     */
    int updateByExampleSelective(@Param("record") OptionVal record, @Param("example") OptionValExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_options
     *
     * @mbg.generated Thu May 31 21:45:18 ICT 2018
     */
    int updateByExampleWithBLOBs(@Param("record") OptionVal record, @Param("example") OptionValExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_options
     *
     * @mbg.generated Thu May 31 21:45:18 ICT 2018
     */
    int updateByExample(@Param("record") OptionVal record, @Param("example") OptionValExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_options
     *
     * @mbg.generated Thu May 31 21:45:18 ICT 2018
     */
    int updateByPrimaryKeySelective(OptionVal record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_options
     *
     * @mbg.generated Thu May 31 21:45:18 ICT 2018
     */
    int updateByPrimaryKeyWithBLOBs(OptionVal record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_options
     *
     * @mbg.generated Thu May 31 21:45:18 ICT 2018
     */
    int updateByPrimaryKey(OptionVal record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_options
     *
     * @mbg.generated Thu May 31 21:45:18 ICT 2018
     */
    Integer insertAndReturnKey(OptionVal value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_options
     *
     * @mbg.generated Thu May 31 21:45:18 ICT 2018
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_options
     *
     * @mbg.generated Thu May 31 21:45:18 ICT 2018
     */
    void massUpdateWithSession(@Param("record") OptionVal record, @Param("primaryKeys") List primaryKeys);
}