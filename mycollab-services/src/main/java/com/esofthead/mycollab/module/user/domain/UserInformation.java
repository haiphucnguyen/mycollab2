/*Domain class of table s_user_information*/
package com.esofthead.mycollab.module.user.domain;

import com.esofthead.mycollab.core.arguments.ValuedBean;

@SuppressWarnings("ucd")
@com.esofthead.mycollab.core.db.metadata.Table("s_user_information")
public class UserInformation extends ValuedBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user_information.id
     *
     * @mbggenerated Tue Jun 23 23:25:33 ICT 2015
     */
    @com.esofthead.mycollab.core.db.metadata.Column("id")
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user_information.username
     *
     * @mbggenerated Tue Jun 23 23:25:33 ICT 2015
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    @com.esofthead.mycollab.core.db.metadata.Column("username")
    private String username;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user_information.sAccountId
     *
     * @mbggenerated Tue Jun 23 23:25:33 ICT 2015
     */
    @com.esofthead.mycollab.core.db.metadata.Column("sAccountId")
    private Integer saccountid;

    private static final long serialVersionUID = 1;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_user_information.id
     *
     * @return the value of s_user_information.id
     *
     * @mbggenerated Tue Jun 23 23:25:33 ICT 2015
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_user_information.id
     *
     * @param id the value for s_user_information.id
     *
     * @mbggenerated Tue Jun 23 23:25:33 ICT 2015
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_user_information.username
     *
     * @return the value of s_user_information.username
     *
     * @mbggenerated Tue Jun 23 23:25:33 ICT 2015
     */
    public String getUsername() {
        return username;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_user_information.username
     *
     * @param username the value for s_user_information.username
     *
     * @mbggenerated Tue Jun 23 23:25:33 ICT 2015
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_user_information.sAccountId
     *
     * @return the value of s_user_information.sAccountId
     *
     * @mbggenerated Tue Jun 23 23:25:33 ICT 2015
     */
    public Integer getSaccountid() {
        return saccountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_user_information.sAccountId
     *
     * @param saccountid the value for s_user_information.sAccountId
     *
     * @mbggenerated Tue Jun 23 23:25:33 ICT 2015
     */
    public void setSaccountid(Integer saccountid) {
        this.saccountid = saccountid;
    }

    public static enum Field {
        id,
        username,
        saccountid;

        public boolean equalTo(Object value) {
            return name().equals(value);
        }
    }
}