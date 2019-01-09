/*Domain class of table m_options*/
package com.mycollab.common.domain;

import com.mycollab.core.arguments.ValuedBean;
import com.mycollab.db.metadata.Column;
import com.mycollab.db.metadata.Table;
import java.time.LocalDateTime;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.ibatis.type.Alias;
import org.hibernate.validator.constraints.Length;

@SuppressWarnings("ucd")
@Table("m_options")
@Alias("OptionVal")
public class OptionVal extends ValuedBean {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_options.id
     *
     * @mbg.generated Tue Jan 08 20:44:16 CST 2019
     */
    @Column("id")
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_options.type
     *
     * @mbg.generated Tue Jan 08 20:44:16 CST 2019
     */
    @Length(max=45, message="Field value is too long")
    @Column("type")
    private String type;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_options.typeVal
     *
     * @mbg.generated Tue Jan 08 20:44:16 CST 2019
     */
    @Length(max=255, message="Field value is too long")
    @Column("typeVal")
    private String typeval;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_options.orderIndex
     *
     * @mbg.generated Tue Jan 08 20:44:16 CST 2019
     */
    @Column("orderIndex")
    private Integer orderindex;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_options.sAccountId
     *
     * @mbg.generated Tue Jan 08 20:44:16 CST 2019
     */
    @Column("sAccountId")
    private Integer saccountid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_options.createdTime
     *
     * @mbg.generated Tue Jan 08 20:44:16 CST 2019
     */
    @Column("createdTime")
    private LocalDateTime createdtime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_options.createdUser
     *
     * @mbg.generated Tue Jan 08 20:44:16 CST 2019
     */
    @Length(max=45, message="Field value is too long")
    @Column("createdUser")
    private String createduser;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_options.extraId
     *
     * @mbg.generated Tue Jan 08 20:44:16 CST 2019
     */
    @Column("extraId")
    private Integer extraid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_options.isDefault
     *
     * @mbg.generated Tue Jan 08 20:44:16 CST 2019
     */
    @Column("isDefault")
    private Boolean isdefault;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_options.refOption
     *
     * @mbg.generated Tue Jan 08 20:44:16 CST 2019
     */
    @Column("refOption")
    private Integer refoption;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_options.color
     *
     * @mbg.generated Tue Jan 08 20:44:16 CST 2019
     */
    @Length(max=6, message="Field value is too long")
    @Column("color")
    private String color;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_options.fieldgroup
     *
     * @mbg.generated Tue Jan 08 20:44:16 CST 2019
     */
    @Length(max=45, message="Field value is too long")
    @Column("fieldgroup")
    private String fieldgroup;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_options.isShow
     *
     * @mbg.generated Tue Jan 08 20:44:16 CST 2019
     */
    @Column("isShow")
    private Boolean isshow;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_options.description
     *
     * @mbg.generated Tue Jan 08 20:44:16 CST 2019
     */
    @Length(max=65535, message="Field value is too long")
    @Column("description")
    private String description;

    private static final long serialVersionUID = 1;

    public final boolean equals(Object obj) {
        if (obj == null) { return false;}
        if (obj == this) { return true;}
        if (!obj.getClass().isAssignableFrom(getClass())) { return false;}
        OptionVal item = (OptionVal)obj;
        return new EqualsBuilder().append(id, item.id).build();
    }

    public final int hashCode() {
        return new HashCodeBuilder(779, 981).append(id).build();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_options.id
     *
     * @return the value of m_options.id
     *
     * @mbg.generated Tue Jan 08 20:44:16 CST 2019
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_options.id
     *
     * @param id the value for m_options.id
     *
     * @mbg.generated Tue Jan 08 20:44:16 CST 2019
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_options.type
     *
     * @return the value of m_options.type
     *
     * @mbg.generated Tue Jan 08 20:44:16 CST 2019
     */
    public String getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_options.type
     *
     * @param type the value for m_options.type
     *
     * @mbg.generated Tue Jan 08 20:44:16 CST 2019
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_options.typeVal
     *
     * @return the value of m_options.typeVal
     *
     * @mbg.generated Tue Jan 08 20:44:16 CST 2019
     */
    public String getTypeval() {
        return typeval;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_options.typeVal
     *
     * @param typeval the value for m_options.typeVal
     *
     * @mbg.generated Tue Jan 08 20:44:16 CST 2019
     */
    public void setTypeval(String typeval) {
        this.typeval = typeval;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_options.orderIndex
     *
     * @return the value of m_options.orderIndex
     *
     * @mbg.generated Tue Jan 08 20:44:16 CST 2019
     */
    public Integer getOrderindex() {
        return orderindex;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_options.orderIndex
     *
     * @param orderindex the value for m_options.orderIndex
     *
     * @mbg.generated Tue Jan 08 20:44:16 CST 2019
     */
    public void setOrderindex(Integer orderindex) {
        this.orderindex = orderindex;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_options.sAccountId
     *
     * @return the value of m_options.sAccountId
     *
     * @mbg.generated Tue Jan 08 20:44:16 CST 2019
     */
    public Integer getSaccountid() {
        return saccountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_options.sAccountId
     *
     * @param saccountid the value for m_options.sAccountId
     *
     * @mbg.generated Tue Jan 08 20:44:16 CST 2019
     */
    public void setSaccountid(Integer saccountid) {
        this.saccountid = saccountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_options.createdTime
     *
     * @return the value of m_options.createdTime
     *
     * @mbg.generated Tue Jan 08 20:44:16 CST 2019
     */
    public LocalDateTime getCreatedtime() {
        return createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_options.createdTime
     *
     * @param createdtime the value for m_options.createdTime
     *
     * @mbg.generated Tue Jan 08 20:44:16 CST 2019
     */
    public void setCreatedtime(LocalDateTime createdtime) {
        this.createdtime = createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_options.createdUser
     *
     * @return the value of m_options.createdUser
     *
     * @mbg.generated Tue Jan 08 20:44:16 CST 2019
     */
    public String getCreateduser() {
        return createduser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_options.createdUser
     *
     * @param createduser the value for m_options.createdUser
     *
     * @mbg.generated Tue Jan 08 20:44:16 CST 2019
     */
    public void setCreateduser(String createduser) {
        this.createduser = createduser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_options.extraId
     *
     * @return the value of m_options.extraId
     *
     * @mbg.generated Tue Jan 08 20:44:16 CST 2019
     */
    public Integer getExtraid() {
        return extraid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_options.extraId
     *
     * @param extraid the value for m_options.extraId
     *
     * @mbg.generated Tue Jan 08 20:44:16 CST 2019
     */
    public void setExtraid(Integer extraid) {
        this.extraid = extraid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_options.isDefault
     *
     * @return the value of m_options.isDefault
     *
     * @mbg.generated Tue Jan 08 20:44:16 CST 2019
     */
    public Boolean getIsdefault() {
        return isdefault;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_options.isDefault
     *
     * @param isdefault the value for m_options.isDefault
     *
     * @mbg.generated Tue Jan 08 20:44:16 CST 2019
     */
    public void setIsdefault(Boolean isdefault) {
        this.isdefault = isdefault;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_options.refOption
     *
     * @return the value of m_options.refOption
     *
     * @mbg.generated Tue Jan 08 20:44:16 CST 2019
     */
    public Integer getRefoption() {
        return refoption;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_options.refOption
     *
     * @param refoption the value for m_options.refOption
     *
     * @mbg.generated Tue Jan 08 20:44:16 CST 2019
     */
    public void setRefoption(Integer refoption) {
        this.refoption = refoption;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_options.color
     *
     * @return the value of m_options.color
     *
     * @mbg.generated Tue Jan 08 20:44:16 CST 2019
     */
    public String getColor() {
        return color;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_options.color
     *
     * @param color the value for m_options.color
     *
     * @mbg.generated Tue Jan 08 20:44:16 CST 2019
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_options.fieldgroup
     *
     * @return the value of m_options.fieldgroup
     *
     * @mbg.generated Tue Jan 08 20:44:16 CST 2019
     */
    public String getFieldgroup() {
        return fieldgroup;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_options.fieldgroup
     *
     * @param fieldgroup the value for m_options.fieldgroup
     *
     * @mbg.generated Tue Jan 08 20:44:16 CST 2019
     */
    public void setFieldgroup(String fieldgroup) {
        this.fieldgroup = fieldgroup;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_options.isShow
     *
     * @return the value of m_options.isShow
     *
     * @mbg.generated Tue Jan 08 20:44:16 CST 2019
     */
    public Boolean getIsshow() {
        return isshow;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_options.isShow
     *
     * @param isshow the value for m_options.isShow
     *
     * @mbg.generated Tue Jan 08 20:44:16 CST 2019
     */
    public void setIsshow(Boolean isshow) {
        this.isshow = isshow;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_options.description
     *
     * @return the value of m_options.description
     *
     * @mbg.generated Tue Jan 08 20:44:16 CST 2019
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_options.description
     *
     * @param description the value for m_options.description
     *
     * @mbg.generated Tue Jan 08 20:44:16 CST 2019
     */
    public void setDescription(String description) {
        this.description = description;
    }

    public enum Field {
        id,
        type,
        typeval,
        orderindex,
        saccountid,
        createdtime,
        createduser,
        extraid,
        isdefault,
        refoption,
        color,
        fieldgroup,
        isshow,
        description;

        public boolean equalTo(Object value) {
            return name().equals(value);
        }
    }
}