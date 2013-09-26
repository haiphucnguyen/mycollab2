/*Domain class of table s_save_search_result*/
package com.esofthead.mycollab.common.domain;

import com.esofthead.mycollab.core.utils.ValuedBean;
import java.util.Date;

class SaveSearchResult extends ValuedBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_save_search_result.id
     *
     * @mbggenerated Thu Sep 26 09:54:43 ICT 2013
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_save_search_result.saveUser
     *
     * @mbggenerated Thu Sep 26 09:54:43 ICT 2013
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    private String saveuser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_save_search_result.sAccountId
     *
     * @mbggenerated Thu Sep 26 09:54:43 ICT 2013
     */
    private Integer saccountid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_save_search_result.type
     *
     * @mbggenerated Thu Sep 26 09:54:43 ICT 2013
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    private String type;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_save_search_result.createdTime
     *
     * @mbggenerated Thu Sep 26 09:54:43 ICT 2013
     */
    private Date createdtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_save_search_result.lastUpdatedTime
     *
     * @mbggenerated Thu Sep 26 09:54:43 ICT 2013
     */
    private Date lastupdatedtime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_save_search_result.id
     *
     * @return the value of s_save_search_result.id
     *
     * @mbggenerated Thu Sep 26 09:54:43 ICT 2013
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_save_search_result.id
     *
     * @param id the value for s_save_search_result.id
     *
     * @mbggenerated Thu Sep 26 09:54:43 ICT 2013
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_save_search_result.saveUser
     *
     * @return the value of s_save_search_result.saveUser
     *
     * @mbggenerated Thu Sep 26 09:54:43 ICT 2013
     */
    public String getSaveuser() {
        return saveuser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_save_search_result.saveUser
     *
     * @param saveuser the value for s_save_search_result.saveUser
     *
     * @mbggenerated Thu Sep 26 09:54:43 ICT 2013
     */
    public void setSaveuser(String saveuser) {
        this.saveuser = saveuser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_save_search_result.sAccountId
     *
     * @return the value of s_save_search_result.sAccountId
     *
     * @mbggenerated Thu Sep 26 09:54:43 ICT 2013
     */
    public Integer getSaccountid() {
        return saccountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_save_search_result.sAccountId
     *
     * @param saccountid the value for s_save_search_result.sAccountId
     *
     * @mbggenerated Thu Sep 26 09:54:43 ICT 2013
     */
    public void setSaccountid(Integer saccountid) {
        this.saccountid = saccountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_save_search_result.type
     *
     * @return the value of s_save_search_result.type
     *
     * @mbggenerated Thu Sep 26 09:54:43 ICT 2013
     */
    public String getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_save_search_result.type
     *
     * @param type the value for s_save_search_result.type
     *
     * @mbggenerated Thu Sep 26 09:54:43 ICT 2013
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_save_search_result.createdTime
     *
     * @return the value of s_save_search_result.createdTime
     *
     * @mbggenerated Thu Sep 26 09:54:43 ICT 2013
     */
    public Date getCreatedtime() {
        return createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_save_search_result.createdTime
     *
     * @param createdtime the value for s_save_search_result.createdTime
     *
     * @mbggenerated Thu Sep 26 09:54:43 ICT 2013
     */
    public void setCreatedtime(Date createdtime) {
        this.createdtime = createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_save_search_result.lastUpdatedTime
     *
     * @return the value of s_save_search_result.lastUpdatedTime
     *
     * @mbggenerated Thu Sep 26 09:54:43 ICT 2013
     */
    public Date getLastupdatedtime() {
        return lastupdatedtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_save_search_result.lastUpdatedTime
     *
     * @param lastupdatedtime the value for s_save_search_result.lastUpdatedTime
     *
     * @mbggenerated Thu Sep 26 09:54:43 ICT 2013
     */
    public void setLastupdatedtime(Date lastupdatedtime) {
        this.lastupdatedtime = lastupdatedtime;
    }
}