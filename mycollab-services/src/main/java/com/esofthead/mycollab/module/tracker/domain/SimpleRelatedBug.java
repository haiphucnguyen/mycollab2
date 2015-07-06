package com.esofthead.mycollab.module.tracker.domain;

/**
 * @author MyCollab Ltd
 * @since 5.1.0
 */
public class SimpleRelatedBug {
    private Integer bugId;
    private Integer bugKey;
    private String bugSummary;
    private String bugStatus;
    private String bugSeverity;
    private String relatedType;
    private String comment;

    public Integer getBugId() {
        return bugId;
    }

    public void setBugId(Integer bugId) {
        this.bugId = bugId;
    }

    public Integer getBugKey() {
        return bugKey;
    }

    public void setBugKey(Integer bugKey) {
        this.bugKey = bugKey;
    }

    public String getBugSummary() {
        return bugSummary;
    }

    public void setBugSummary(String bugSummary) {
        this.bugSummary = bugSummary;
    }

    public String getRelatedType() {
        return relatedType;
    }

    public void setRelatedType(String relatedType) {
        this.relatedType = relatedType;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getBugStatus() {
        return bugStatus;
    }

    public void setBugStatus(String bugStatus) {
        this.bugStatus = bugStatus;
    }

    public String getBugSeverity() {
        return bugSeverity;
    }

    public void setBugSeverity(String bugSeverity) {
        this.bugSeverity = bugSeverity;
    }
}
