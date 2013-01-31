package com.esofthead.mycollab.module.mail.service;

import java.util.LinkedList;
import java.util.List;

import com.esofthead.mycollab.utils.EncryptText;

public class Message {
	private String html;
	private String text;
	private String subject;
	private String from_email;
	private String from_name;
	private List<Recipient> to = new LinkedList<Recipient>();
	private boolean track_opens;
	private boolean track_clicks;
	private boolean auto_text;
	private boolean url_strip_qs;
	private boolean preserve_recipients;
	private String bcc_address;
	private List<Attachment> attachments;// = new LinkedList<Attachment>();
	//private MessageHeader headers;
	/**
	 * @return the html
	 */
	public String getHtml() {
		return html;
	}
	/**
	 * @param html the html to set
	 */
	public void setHtml(String html) {
		this.html = html;
	}
	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}
	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}
	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}
	/**
	 * @param subject the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}
	/**
	 * @return the from_email
	 */
	public String getFrom_email() {
		return from_email;
	}
	/**
	 * @param from_email the from_email to set
	 */
	public void setFrom_email(String from_email) {
		this.from_email = from_email;
	}
	/**
	 * @return the from_name
	 */
	public String getFrom_name() {
		return from_name;
	}
	/**
	 * @param from_name the from_name to set
	 */
	public void setFrom_name(String from_name) {
		this.from_name = from_name;
	}
	/**
	 * @return the track_opens
	 */
	public boolean isTrack_opens() {
		return track_opens;
	}
	/**
	 * @param track_opens the track_opens to set
	 */
	public void setTrack_opens(boolean track_opens) {
		this.track_opens = track_opens;
	}
	/**
	 * @return the track_clicks
	 */
	public boolean isTrack_clicks() {
		return track_clicks;
	}
	/**
	 * @param track_clicks the track_clicks to set
	 */
	public void setTrack_clicks(boolean track_clicks) {
		this.track_clicks = track_clicks;
	}
	/**
	 * @return the auto_text
	 */
	public boolean isAuto_text() {
		return auto_text;
	}
	/**
	 * @param auto_text the auto_text to set
	 */
	public void setAuto_text(boolean auto_text) {
		this.auto_text = auto_text;
	}
	/**
	 * @return the url_strip_qs
	 */
	public boolean isUrl_strip_qs() {
		return url_strip_qs;
	}
	/**
	 * @param url_strip_qs the url_strip_qs to set
	 */
	public void setUrl_strip_qs(boolean url_strip_qs) {
		this.url_strip_qs = url_strip_qs;
	}
	/**
	 * @return the preserve_recipients
	 */
	public boolean isPreserve_recipients() {
		return preserve_recipients;
	}
	/**
	 * @param preserve_recipients the preserve_recipients to set
	 */
	public void setPreserve_recipients(boolean preserve_recipients) {
		this.preserve_recipients = preserve_recipients;
	}
	/**
	 * @return the bcc_address
	 */
	public String getBcc_address() {
		return bcc_address;
	}
	/**
	 * @param bcc_address the bcc_address to set
	 */
	public void setBcc_address(String bcc_address) {
		this.bcc_address = bcc_address;
	}
	
	
	public void addRecipient(Recipient recipient) {
		if (to.indexOf(recipient) == -1) {
			to.add(recipient);
		}
	}
	
	public void removeRecipient(Recipient recipient) {
		to.remove(recipient);
	}
	
	public Message() {
		CampaignConfig config = CampaignConfig.loadConfig();
		if (null != config) {
			String key = EncryptText.encryptText(String.valueOf(System.currentTimeMillis()));
			from_email = String.format(config.getSenderMail(), key);
		}
		
		setTrack_opens(true);
		setTrack_clicks(true);
		setAuto_text(false);
		setUrl_strip_qs(true);
		setPreserve_recipients(true);
	}
	
	public boolean addAttachMent(String fileName) {
		Attachment attachFile = Attachment.load(fileName);
		if (null == attachFile)
			return false;
		
		if (null == attachments)
			attachments = new LinkedList<Attachment>();
		attachments.add(attachFile);
		return true;
	}
	
	public void removeAttachment(Attachment attachFile) {
		if (null != attachments) {
			attachments.remove(attachFile);
			if (attachments.size() == 0) {
				attachments = null;
			}
		}
	}
}
