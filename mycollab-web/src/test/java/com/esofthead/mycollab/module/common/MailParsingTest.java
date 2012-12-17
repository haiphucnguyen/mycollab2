package com.esofthead.mycollab.module.common;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.esofthead.mycollab.common.domain.MailRecipientField;
import com.esofthead.mycollab.utils.ParsingUtils;
import com.esofthead.mycollab.utils.ParsingUtils.InvalidEmailException;

public class MailParsingTest {
	@Test
	public void testValidEmail() throws InvalidEmailException {
		String validEmails = "Hai Nguyen <hainguyen@esofthead.com>, b@a.com";
		List<MailRecipientField> emailFields = ParsingUtils
				.parseEmailField(validEmails);
		Assert.assertEquals(2, emailFields.size());
	}

	@Test
	public void testValidEmail2() throws InvalidEmailException {
		String validEmails = "Hai Nguyen <hainguyen@esofthead.com>";
		List<MailRecipientField> emailFields = ParsingUtils
				.parseEmailField(validEmails);
		Assert.assertEquals(1, emailFields.size());
	}

	@Test
	public void testValidEmail3() throws InvalidEmailException {
		String validEmails = "a@a.com, b@b.com";
		List<MailRecipientField> emailFields = ParsingUtils
				.parseEmailField(validEmails);
		Assert.assertEquals(2, emailFields.size());
	}

	@Test(expected = InvalidEmailException.class)
	public void testInvalidEmail() throws InvalidEmailException {
		String emails = "a@a.com;b@a.com";
		ParsingUtils.parseEmailField(emails);
	}

	@Test(expected = InvalidEmailException.class)
	public void testInvalidEmail2() throws InvalidEmailException {
		String emails = " ";
		ParsingUtils.parseEmailField(emails);
	}

	@Test(expected = InvalidEmailException.class)
	public void testInvalidEmail3() throws InvalidEmailException {
		String emails = "a<a.com, hai@a.com";
		ParsingUtils.parseEmailField(emails);
	}

	@Test(expected = InvalidEmailException.class)
	public void testInvalidEmail4() throws InvalidEmailException {
		String emails = "a>a.com<, hai@a.com";
		ParsingUtils.parseEmailField(emails);
	}

	@Test(expected = InvalidEmailException.class)
	public void testInvalidEmail5() throws InvalidEmailException {
		String emails = "a<a. >, hai@a.com";
		ParsingUtils.parseEmailField(emails);
	}

	@Test(expected = InvalidEmailException.class)
	public void testInvalidEmail6() throws InvalidEmailException {
		String emails = "a<.com>, hai@a.com";
		ParsingUtils.parseEmailField(emails);
	}
}
