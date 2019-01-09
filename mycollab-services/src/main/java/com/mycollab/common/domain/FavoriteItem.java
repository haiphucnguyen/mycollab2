/*Domain class of table s_favorite*/
package com.mycollab.common.domain;

import com.mycollab.core.arguments.ValuedBean;
import com.mycollab.db.metadata.Column;
import com.mycollab.db.metadata.Table;
import java.time.LocalDateTime;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.ibatis.type.Alias;
import org.hibernate.validator.constraints.Length;

@SuppressWarnings("ucd")
@Table("s_favorite")
@Alias("FavoriteItem")
public class FavoriteItem extends ValuedBean {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_favorite.id
     *
     * @mbg.generated Wed Jan 09 14:21:41 CST 2019
     */
    @Column("id")
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_favorite.type
     *
     * @mbg.generated Wed Jan 09 14:21:41 CST 2019
     */
    @NotEmpty
    @Length(max=45, message="Field value is too long")
    @Column("type")
    private String type;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_favorite.typeid
     *
     * @mbg.generated Wed Jan 09 14:21:41 CST 2019
     */
    @NotEmpty
    @Length(max=45, message="Field value is too long")
    @Column("typeid")
    private String typeid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_favorite.lastUpdatedTime
     *
     * @mbg.generated Wed Jan 09 14:21:41 CST 2019
     */
    @NotNull
    @Column("lastUpdatedTime")
    private LocalDateTime lastupdatedtime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_favorite.createdTime
     *
     * @mbg.generated Wed Jan 09 14:21:41 CST 2019
     */
    @NotNull
    @Column("createdTime")
    private LocalDateTime createdtime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_favorite.extraTypeId
     *
     * @mbg.generated Wed Jan 09 14:21:41 CST 2019
     */
    @Column("extraTypeId")
    private Integer extratypeid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_favorite.createdUser
     *
     * @mbg.generated Wed Jan 09 14:21:41 CST 2019
     */
    @NotEmpty
    @Length(max=45, message="Field value is too long")
    @Column("createdUser")
    private String createduser;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_favorite.sAccountId
     *
     * @mbg.generated Wed Jan 09 14:21:41 CST 2019
     */
    @NotNull
    @Column("sAccountId")
    private Integer saccountid;

    private static final long serialVersionUID = 1;

    public final boolean equals(Object obj) {
        if (obj == null) { return false;}
        if (obj == this) { return true;}
        if (!obj.getClass().isAssignableFrom(getClass())) { return false;}
        FavoriteItem item = (FavoriteItem)obj;
        return new EqualsBuilder().append(id, item.id).build();
    }

    public final int hashCode() {
        return new HashCodeBuilder(729, 887).append(id).build();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_favorite.id
     *
     * @return the value of s_favorite.id
     *
     * @mbg.generated Wed Jan 09 14:21:41 CST 2019
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_favorite.id
     *
     * @param id the value for s_favorite.id
     *
     * @mbg.generated Wed Jan 09 14:21:41 CST 2019
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_favorite.type
     *
     * @return the value of s_favorite.type
     *
     * @mbg.generated Wed Jan 09 14:21:41 CST 2019
     */
    public String getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_favorite.type
     *
     * @param type the value for s_favorite.type
     *
     * @mbg.generated Wed Jan 09 14:21:41 CST 2019
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_favorite.typeid
     *
     * @return the value of s_favorite.typeid
     *
     * @mbg.generated Wed Jan 09 14:21:41 CST 2019
     */
    public String getTypeid() {
        return typeid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_favorite.typeid
     *
     * @param typeid the value for s_favorite.typeid
     *
     * @mbg.generated Wed Jan 09 14:21:41 CST 2019
     */
    public void setTypeid(String typeid) {
        this.typeid = typeid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_favorite.lastUpdatedTime
     *
     * @return the value of s_favorite.lastUpdatedTime
     *
     * @mbg.generated Wed Jan 09 14:21:41 CST 2019
     */
    public LocalDateTime getLastupdatedtime() {
        return lastupdatedtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_favorite.lastUpdatedTime
     *
     * @param lastupdatedtime the value for s_favorite.lastUpdatedTime
     *
     * @mbg.generated Wed Jan 09 14:21:41 CST 2019
     */
    public void setLastupdatedtime(LocalDateTime lastupdatedtime) {
        this.lastupdatedtime = lastupdatedtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_favorite.createdTime
     *
     * @return the value of s_favorite.createdTime
     *
     * @mbg.generated Wed Jan 09 14:21:41 CST 2019
     */
    public LocalDateTime getCreatedtime() {
        return createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_favorite.createdTime
     *
     * @param createdtime the value for s_favorite.createdTime
     *
     * @mbg.generated Wed Jan 09 14:21:41 CST 2019
     */
    public void setCreatedtime(LocalDateTime createdtime) {
        this.createdtime = createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_favorite.extraTypeId
     *
     * @return the value of s_favorite.extraTypeId
     *
     * @mbg.generated Wed Jan 09 14:21:41 CST 2019
     */
    public Integer getExtratypeid() {
        return extratypeid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_favorite.extraTypeId
     *
     * @param extratypeid the value for s_favorite.extraTypeId
     *
     * @mbg.generated Wed Jan 09 14:21:41 CST 2019
     */
    public void setExtratypeid(Integer extratypeid) {
        this.extratypeid = extratypeid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_favorite.createdUser
     *
     * @return the value of s_favorite.createdUser
     *
     * @mbg.generated Wed Jan 09 14:21:41 CST 2019
     */
    public String getCreateduser() {
        return createduser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_favorite.createdUser
     *
     * @param createduser the value for s_favorite.createdUser
     *
     * @mbg.generated Wed Jan 09 14:21:41 CST 2019
     */
    public void setCreateduser(String createduser) {
        this.createduser = createduser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_favorite.sAccountId
     *
     * @return the value of s_favorite.sAccountId
     *
     * @mbg.generated Wed Jan 09 14:21:41 CST 2019
     */
    public Integer getSaccountid() {
        return saccountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_favorite.sAccountId
     *
     * @param saccountid the value for s_favorite.sAccountId
     *
     * @mbg.generated Wed Jan 09 14:21:41 CST 2019
     */
    public void setSaccountid(Integer saccountid) {
        this.saccountid = saccountid;
    }

    public enum Field {
        id,
        type,
        typeid,
        lastupdatedtime,
        createdtime,
        extratypeid,
        createduser,
        saccountid;

        public boolean equalTo(Object value) {
            return name().equals(value);
        }
    }
}