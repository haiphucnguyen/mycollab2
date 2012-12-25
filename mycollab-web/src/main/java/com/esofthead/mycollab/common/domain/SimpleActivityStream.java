package com.esofthead.mycollab.common.domain;

import java.util.List;

public class SimpleActivityStream extends ActivityStream {

    private static final long serialVersionUID = 1L;
    private String createdUserFullName;
    private List<SimpleComment> comments;

    public List<SimpleComment> getComments() {
        return comments;
    }

    public void setComments(List<SimpleComment> comments) {
        this.comments = comments;
    }

    public String getCreatedUserFullName() {
        return createdUserFullName;
    }

    public void setCreatedUserFullName(String createdUserFullName) {
        this.createdUserFullName = createdUserFullName;
    }
}
