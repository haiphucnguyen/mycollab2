package com.esofthead.mycollab.module.file.domain;

public class Attachment {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_attachment.id
     *
     * @mbggenerated Tue Oct 23 10:17:23 GMT+07:00 2012
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_attachment.attachmentid
     *
     * @mbggenerated Tue Oct 23 10:17:23 GMT+07:00 2012
     */
    private String attachmentid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_attachment.documentpath
     *
     * @mbggenerated Tue Oct 23 10:17:23 GMT+07:00 2012
     */
    private String documentpath;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_attachment.id
     *
     * @return the value of m_attachment.id
     *
     * @mbggenerated Tue Oct 23 10:17:23 GMT+07:00 2012
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_attachment.id
     *
     * @param id the value for m_attachment.id
     *
     * @mbggenerated Tue Oct 23 10:17:23 GMT+07:00 2012
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_attachment.attachmentid
     *
     * @return the value of m_attachment.attachmentid
     *
     * @mbggenerated Tue Oct 23 10:17:23 GMT+07:00 2012
     */
    public String getAttachmentid() {
        return attachmentid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_attachment.attachmentid
     *
     * @param attachmentid the value for m_attachment.attachmentid
     *
     * @mbggenerated Tue Oct 23 10:17:23 GMT+07:00 2012
     */
    public void setAttachmentid(String attachmentid) {
        this.attachmentid = attachmentid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_attachment.documentpath
     *
     * @return the value of m_attachment.documentpath
     *
     * @mbggenerated Tue Oct 23 10:17:23 GMT+07:00 2012
     */
    public String getDocumentpath() {
        return documentpath;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_attachment.documentpath
     *
     * @param documentpath the value for m_attachment.documentpath
     *
     * @mbggenerated Tue Oct 23 10:17:23 GMT+07:00 2012
     */
    public void setDocumentpath(String documentpath) {
        this.documentpath = documentpath;
    }
}