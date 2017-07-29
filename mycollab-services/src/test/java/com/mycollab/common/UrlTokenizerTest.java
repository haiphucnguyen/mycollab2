package com.mycollab.common;

import com.mycollab.common.GenericLinkUtils;
import com.mycollab.common.InvalidTokenException;
import com.mycollab.common.UrlTokenizer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UrlTokenizerTest {
	private String testData;

	@Before
	public void setUp() {
		testData = GenericLinkUtils.encodeParam(1, "a", "b", "1/2/3");
	}

	@Test
	public void testGetProperValue() throws InvalidTokenException {
		UrlTokenizer tokenizer = UrlTokenizer.apply(testData);
		Assert.assertEquals(new Integer(1), tokenizer.getInt());
		Assert.assertEquals("a", tokenizer.getString());
		Assert.assertEquals("b", tokenizer.getString());
		Assert.assertEquals("1/2/3", tokenizer.getRemainValue());
	}
}
