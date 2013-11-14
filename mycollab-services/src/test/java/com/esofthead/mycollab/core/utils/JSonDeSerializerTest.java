package com.esofthead.mycollab.core.utils;

import org.junit.Assert;
import org.junit.Test;

import com.esofthead.mycollab.security.PermissionMap;

public class JSonDeSerializerTest {
	@Test
	public void testSerializeArray() {
		String[][] twoArr = { { "Nguyen", "Hai" }, { "eSoftHead", "MyCollab" } };
		String json = JsonDeSerializer.toJson(twoArr);

		System.out.println("Json: " + json);

		String[][] newVal = JsonDeSerializer.fromJson(json, String[][].class);
		Assert.assertEquals(2, newVal.length);
		Assert.assertEquals("Nguyen", newVal[0][0]);

	}

	@Test
	public void testSerializePermissionMap() {
		PermissionMap map = new PermissionMap();
		map.addPath("a", 1);
		map.addPath("b", 2);

		String json = JsonDeSerializer.toJson(map);
		System.out.println("Json: " + json + "--"
				+ json.replaceAll("\"", "\\\\\""));

		PermissionMap permissionMap = JsonDeSerializer.fromJson(json,
				PermissionMap.class);
		Assert.assertEquals(new Integer(1), permissionMap.get("a"));
	}
}
