package com.mycollab.ondemand.module.billing.dao;

import com.mycollab.db.persistence.ICrudGenericDAO;
import com.mycollab.ondemand.module.billing.domain.ProEditionInfo;
import com.mycollab.ondemand.module.billing.domain.ProEditionInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@SuppressWarnings({ "ucd", "rawtypes" })
public interface ProEditionInfoMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_pro_edition_info
     *
     * @mbggenerated Mon Jul 18 20:40:09 ICT 2016
     */
    int countByExample(ProEditionInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_pro_edition_info
     *
     * @mbggenerated Mon Jul 18 20:40:09 ICT 2016
     */
    int deleteByExample(ProEditionInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_pro_edition_info
     *
     * @mbggenerated Mon Jul 18 20:40:09 ICT 2016
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_pro_edition_info
     *
     * @mbggenerated Mon Jul 18 20:40:09 ICT 2016
     */
    int insert(ProEditionInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_pro_edition_info
     *
     * @mbggenerated Mon Jul 18 20:40:09 ICT 2016
     */
    int insertSelective(ProEditionInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_pro_edition_info
     *
     * @mbggenerated Mon Jul 18 20:40:09 ICT 2016
     */
    List<ProEditionInfo> selectByExample(ProEditionInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_pro_edition_info
     *
     * @mbggenerated Mon Jul 18 20:40:09 ICT 2016
     */
    ProEditionInfo selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_pro_edition_info
     *
     * @mbggenerated Mon Jul 18 20:40:09 ICT 2016
     */
    int updateByExampleSelective(@Param("record") ProEditionInfo record, @Param("example") ProEditionInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_pro_edition_info
     *
     * @mbggenerated Mon Jul 18 20:40:09 ICT 2016
     */
    int updateByExample(@Param("record") ProEditionInfo record, @Param("example") ProEditionInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_pro_edition_info
     *
     * @mbggenerated Mon Jul 18 20:40:09 ICT 2016
     */
    int updateByPrimaryKeySelective(ProEditionInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_pro_edition_info
     *
     * @mbggenerated Mon Jul 18 20:40:09 ICT 2016
     */
    int updateByPrimaryKey(ProEditionInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_pro_edition_info
     *
     * @mbggenerated Mon Jul 18 20:40:09 ICT 2016
     */
    Integer insertAndReturnKey(ProEditionInfo value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_pro_edition_info
     *
     * @mbggenerated Mon Jul 18 20:40:09 ICT 2016
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_pro_edition_info
     *
     * @mbggenerated Mon Jul 18 20:40:09 ICT 2016
     */
    void massUpdateWithSession(@Param("record") ProEditionInfo record, @Param("primaryKeys") List primaryKeys);
}