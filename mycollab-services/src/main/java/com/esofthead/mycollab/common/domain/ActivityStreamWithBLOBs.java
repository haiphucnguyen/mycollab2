package com.esofthead.mycollab.common.domain;

import com.esofthead.mycollab.core.db.metadata.Column;
import com.esofthead.mycollab.core.db.metadata.Table;
import org.hibernate.validator.constraints.Length;

@SuppressWarnings("ucd")
public class ActivityStreamWithBLOBs extends ActivityStream {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_activitystream.typeId
     *
     * @mbggenerated Mon Aug 03 15:23:14 ICT 2015
     */
    @Length(max=65535, message="Field value is too long")
    @Column("typeId")
    private String typeid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_activitystream.nameField
     *
     * @mbggenerated Mon Aug 03 15:23:14 ICT 2015
     */
    @Length(max=65535, message="Field value is too long")
    @Column("nameField")
    private String namefield;

    private static final long serialVersionUID = 1;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_activitystream.typeId
     *
     * @return the value of s_activitystream.typeId
     *
     * @mbggenerated Mon Aug 03 15:23:14 ICT 2015
     */
    public String getTypeid() {
        return typeid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_activitystream.typeId
     *
     * @param typeid the value for s_activitystream.typeId
     *
     * @mbggenerated Mon Aug 03 15:23:14 ICT 2015
     */
    public void setTypeid(String typeid) {
        this.typeid = typeid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_activitystream.nameField
     *
     * @return the value of s_activitystream.nameField
     *
     * @mbggenerated Mon Aug 03 15:23:14 ICT 2015
     */
    public String getNamefield() {
        return namefield;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_activitystream.nameField
     *
     * @param namefield the value for s_activitystream.nameField
     *
     * @mbggenerated Mon Aug 03 15:23:14 ICT 2015
     */
    public void setNamefield(String namefield) {
        this.namefield = namefield;
    }

    public enum Field {
        id,
        saccountid,
        type,
        createdtime,
        action,
        createduser,
        module,
        extratypeid,
        typeid,
        namefield;

        public boolean equalTo(Object value) {
            return name().equals(value);
        }
    }
}