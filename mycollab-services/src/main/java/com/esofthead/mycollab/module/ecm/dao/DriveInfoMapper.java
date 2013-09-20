package com.esofthead.mycollab.module.ecm.dao;

import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.module.ecm.domain.DriveInfo;
import com.esofthead.mycollab.module.ecm.domain.DriveInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DriveInfoMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_ecm_driveinfo
     *
     * @mbggenerated Fri Sep 20 15:38:46 ICT 2013
     */
    int countByExample(DriveInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_ecm_driveinfo
     *
     * @mbggenerated Fri Sep 20 15:38:46 ICT 2013
     */
    int deleteByExample(DriveInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_ecm_driveinfo
     *
     * @mbggenerated Fri Sep 20 15:38:46 ICT 2013
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_ecm_driveinfo
     *
     * @mbggenerated Fri Sep 20 15:38:46 ICT 2013
     */
    int insert(DriveInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_ecm_driveinfo
     *
     * @mbggenerated Fri Sep 20 15:38:46 ICT 2013
     */
    int insertSelective(DriveInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_ecm_driveinfo
     *
     * @mbggenerated Fri Sep 20 15:38:46 ICT 2013
     */
    List<DriveInfo> selectByExample(DriveInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_ecm_driveinfo
     *
     * @mbggenerated Fri Sep 20 15:38:46 ICT 2013
     */
    DriveInfo selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_ecm_driveinfo
     *
     * @mbggenerated Fri Sep 20 15:38:46 ICT 2013
     */
    int updateByExampleSelective(@Param("record") DriveInfo record, @Param("example") DriveInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_ecm_driveinfo
     *
     * @mbggenerated Fri Sep 20 15:38:46 ICT 2013
     */
    int updateByExample(@Param("record") DriveInfo record, @Param("example") DriveInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_ecm_driveinfo
     *
     * @mbggenerated Fri Sep 20 15:38:46 ICT 2013
     */
    int updateByPrimaryKeySelective(DriveInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_ecm_driveinfo
     *
     * @mbggenerated Fri Sep 20 15:38:46 ICT 2013
     */
    Integer insertAndReturnKey(DriveInfo value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_ecm_driveinfo
     *
     * @mbggenerated Fri Sep 20 15:38:46 ICT 2013
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_ecm_driveinfo
     *
     * @mbggenerated Fri Sep 20 15:38:46 ICT 2013
     */
    void massUpdateWithSession(@Param("record") DriveInfo record, @Param("primaryKeys") List primaryKeys);
}