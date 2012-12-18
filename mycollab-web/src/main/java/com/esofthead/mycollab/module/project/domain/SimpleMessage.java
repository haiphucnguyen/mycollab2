package com.esofthead.mycollab.module.project.domain;

public class SimpleMessage extends Message {
	private static final long serialVersionUID = 1L;

	private int commentsCount;
    
    private String messageCategoryName;
    
    private String fullPostedUserName;

    public int getCommentsCount() {
        return commentsCount;
    }

    public void setCommentsCount(int commentsCount) {
        this.commentsCount = commentsCount;
    }

	public String getMessageCategoryName() {
		return messageCategoryName;
	}

	public void setMessageCategoryName(String messageCategoryName) {
		this.messageCategoryName = messageCategoryName;
	}

	public String getFullPostedUserName() {
		return fullPostedUserName;
	}

	public void setFullPostedUserName(String fullPostedUserName) {
		this.fullPostedUserName = fullPostedUserName;
	}
}
