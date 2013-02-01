package com.esofthead.mycollab.module.mail.service.gmail;

import java.util.LinkedList;
import java.util.List;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		List<String> recipients = new LinkedList<String>();
		recipients.add("manhtran@esofthead.com");
		recipients.add("nobugz@live.com");

		List<String> attachments = new LinkedList<String>();
		attachments.add("D:/sample.jpg");

		new GmailManager().sendMail(recipients, "Test send mail",
				"<html><body><h1>Hello world</h1></body></html>", attachments);
	}

}
