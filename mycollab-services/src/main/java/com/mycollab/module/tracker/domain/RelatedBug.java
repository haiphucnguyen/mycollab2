/*Domain class of table m_tracker_related_bug*/
package com.mycollab.module.tracker.domain;

import com.mycollab.core.arguments.ValuedBean;
import com.mycollab.db.metadata.Column;
import com.mycollab.db.metadata.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.ibatis.type.Alias;
import org.hibernate.validator.constraints.Length;

@SuppressWarnings("ucd")
@Table("m_tracker_related_bug")
@Alias("RelatedBug")
public class RelatedBug extends ValuedBean {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_tracker_related_bug.id
     *
     * @mbg.generated Thu Jan 24 08:25:34 CST 2019
     */
    @Column("id")
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_tracker_related_bug.bugid
     *
     * @mbg.generated Thu Jan 24 08:25:34 CST 2019
     */
    @NotNull
    @Column("bugid")
    private Integer bugid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_tracker_related_bug.relatedid
     *
     * @mbg.generated Thu Jan 24 08:25:34 CST 2019
     */
    @NotNull
    @Column("relatedid")
    private Integer relatedid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_tracker_related_bug.relatetype
     *
     * @mbg.generated Thu Jan 24 08:25:34 CST 2019
     */
    @NotEmpty
    @Length(max=45, message="Field value is too long")
    @Column("relatetype")
    private String relatetype;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_tracker_related_bug.comment
     *
     * @mbg.generated Thu Jan 24 08:25:34 CST 2019
     */
    @Length(max=4000, message="Field value is too long")
    @Column("comment")
    private String comment;

    private static final long serialVersionUID = 1;

    public final boolean equals(Object obj) {
        if (obj == null) { return false;}
        if (obj == this) { return true;}
        if (!obj.getClass().isAssignableFrom(getClass())) { return false;}
        RelatedBug item = (RelatedBug)obj;
        return new EqualsBuilder().append(id, item.id).build();
    }

    public final int hashCode() {
        return new HashCodeBuilder(925, 345).append(id).build();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_tracker_related_bug.id
     *
     * @return the value of m_tracker_related_bug.id
     *
     * @mbg.generated Thu Jan 24 08:25:34 CST 2019
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_tracker_related_bug.id
     *
     * @param id the value for m_tracker_related_bug.id
     *
     * @mbg.generated Thu Jan 24 08:25:34 CST 2019
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_tracker_related_bug.bugid
     *
     * @return the value of m_tracker_related_bug.bugid
     *
     * @mbg.generated Thu Jan 24 08:25:34 CST 2019
     */
    public Integer getBugid() {
        return bugid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_tracker_related_bug.bugid
     *
     * @param bugid the value for m_tracker_related_bug.bugid
     *
     * @mbg.generated Thu Jan 24 08:25:34 CST 2019
     */
    public void setBugid(Integer bugid) {
        this.bugid = bugid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_tracker_related_bug.relatedid
     *
     * @return the value of m_tracker_related_bug.relatedid
     *
     * @mbg.generated Thu Jan 24 08:25:34 CST 2019
     */
    public Integer getRelatedid() {
        return relatedid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_tracker_related_bug.relatedid
     *
     * @param relatedid the value for m_tracker_related_bug.relatedid
     *
     * @mbg.generated Thu Jan 24 08:25:34 CST 2019
     */
    public void setRelatedid(Integer relatedid) {
        this.relatedid = relatedid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_tracker_related_bug.relatetype
     *
     * @return the value of m_tracker_related_bug.relatetype
     *
     * @mbg.generated Thu Jan 24 08:25:34 CST 2019
     */
    public String getRelatetype() {
        return relatetype;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_tracker_related_bug.relatetype
     *
     * @param relatetype the value for m_tracker_related_bug.relatetype
     *
     * @mbg.generated Thu Jan 24 08:25:34 CST 2019
     */
    public void setRelatetype(String relatetype) {
        this.relatetype = relatetype;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_tracker_related_bug.comment
     *
     * @return the value of m_tracker_related_bug.comment
     *
     * @mbg.generated Thu Jan 24 08:25:34 CST 2019
     */
    public String getComment() {
        return comment;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_tracker_related_bug.comment
     *
     * @param comment the value for m_tracker_related_bug.comment
     *
     * @mbg.generated Thu Jan 24 08:25:34 CST 2019
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    public enum Field {
        id,
        bugid,
        relatedid,
        relatetype,
        comment;

        public boolean equalTo(Object value) {
            return name().equals(value);
        }
    }
}