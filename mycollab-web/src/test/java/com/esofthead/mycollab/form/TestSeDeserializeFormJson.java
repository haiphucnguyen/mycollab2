package com.esofthead.mycollab.form;

import org.junit.Assert;
import org.junit.Test;

import com.esofthead.mycollab.core.utils.JsonDeSerializer;
import com.esofthead.mycollab.form.view.builder.AbstractDynaFieldBuilder;
import com.esofthead.mycollab.form.view.builder.DynaSectionBuilder;
import com.esofthead.mycollab.form.view.builder.TextDynaFieldBuilder;
import com.esofthead.mycollab.form.view.builder.type.AbstractDynaField;
import com.esofthead.mycollab.form.view.builder.type.TextDynaField;

public class TestSeDeserializeFormJson {

	@Test
	public void testFromJsonWithExcludeProps() {
		DynaSectionBuilder builder = new DynaSectionBuilder().header("example");

		AbstractDynaFieldBuilder val1Builder = new TextDynaFieldBuilder()
				.maxLength(22).fieldIndex(1).fieldName("field1")
				.displayName("Field 1");

		builder.fields(val1Builder);

		AbstractDynaField field = val1Builder.build();
		Assert.assertNotNull(field.getOwnSection());

		String expectedVal = "{\"maxLength\":22}";
		Assert.assertEquals(expectedVal, JsonDeSerializer.toJson(field));
	}

	@Test
	public void testToJsonWithExcludeProps() {
		DynaSectionBuilder builder = new DynaSectionBuilder().header("example");

		AbstractDynaFieldBuilder val1Builder = new TextDynaFieldBuilder()
				.maxLength(22).fieldIndex(1).fieldName("field1")
				.displayName("Field 1");

		builder.fields(val1Builder);

		AbstractDynaField field = val1Builder.build();
		String jsonTxt = JsonDeSerializer.toJson(field);

		TextDynaField stringField = JsonDeSerializer.fromJson(jsonTxt,
				TextDynaField.class);
		Assert.assertEquals(22, stringField.getMaxLength());
		Assert.assertNull(stringField.getOwnSection());
	}
}
