package com.esofthead.mycollab.module.tracker.domain;

import com.esofthead.mycollab.module.file.domain.Attachment;
import java.util.List;

public class SimpleBug extends Bug {

    private static final long serialVersionUID = 1L;
    private String loguserFullName;
    private String assignuserFullName;
    private String projectname;
    private List<Version> affectedVersions;
    private List<Version> fixedVersions;
    private List<Component> components;
    private List<Attachment> attachments;
    private String comment;

    public String getProjectname() {
        return projectname;
    }

    public void setProjectname(String projectname) {
        this.projectname = projectname;
    }

    public String getLoguserFullName() {
        return loguserFullName;
    }

    public void setLoguserFullName(String loguserFullName) {
        this.loguserFullName = loguserFullName;
    }

    public String getAssignuserFullName() {
        return assignuserFullName;
    }

    public void setAssignuserFullName(String assignuserFullName) {
        this.assignuserFullName = assignuserFullName;
    }

    public List<Version> getAffectedVersions() {
        return affectedVersions;
    }

    public void setAffectedVersions(List<Version> affectedVersions) {
        this.affectedVersions = affectedVersions;
    }

    public List<Version> getFixedVersions() {
        return fixedVersions;
    }

    public void setFixedVersions(List<Version> fixedVersions) {
        this.fixedVersions = fixedVersions;
    }

    public List<Component> getComponents() {
        return components;
    }

    public void setComponents(List<Component> components) {
        this.components = components;
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
