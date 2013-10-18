package com.esofthead.mycollab.form;

import org.junit.Assert;
import org.junit.Test;

import com.esofthead.mycollab.core.utils.JsonDeSerializer;
import com.esofthead.mycollab.form.view.builder.AbstractFieldBuilder;
import com.esofthead.mycollab.form.view.builder.SectionBuilder;
import com.esofthead.mycollab.form.view.builder.StringFieldBuilder;
import com.esofthead.mycollab.form.view.builder.type.AbstractDynaField;
import com.esofthead.mycollab.form.view.builder.type.StringDynaField;

public class TestSeDeserializeFormJson {

	@Test
	public void testFromJsonWithExcludeProps() {
		SectionBuilder builder = new SectionBuilder().header("example");

		AbstractFieldBuilder val1Builder = new StringFieldBuilder()
				.maxLength(22).fieldIndex(1).fieldName("field1")
				.displayName("Field 1");

		builder.fields(val1Builder);

		AbstractDynaField field = val1Builder.build();
		Assert.assertNotNull(field.getOwnSection());

		String expectedVal = "{\"maxLength\":22,\"fieldIndex\":1,\"fieldName\":\"field1\",\"displayName\":\"Field 1\",\"isRequired\":false}";
		Assert.assertEquals(expectedVal, JsonDeSerializer.toJson(field));
	}

	@Test
	public void testToJsonWithExcludeProps() {
		SectionBuilder builder = new SectionBuilder().header("example");

		AbstractFieldBuilder val1Builder = new StringFieldBuilder()
				.maxLength(22).fieldIndex(1).fieldName("field1")
				.displayName("Field 1");

		builder.fields(val1Builder);

		AbstractDynaField field = val1Builder.build();
		String jsonTxt = JsonDeSerializer.toJson(field);

		StringDynaField stringField = JsonDeSerializer.fromJson(jsonTxt,
				StringDynaField.class);
		Assert.assertEquals("Field 1", stringField.getDisplayName());
		Assert.assertNull(stringField.getOwnSection());
	}
}
