package com.esofthead.mycollab.rest;

import org.codehaus.jackson.map.ObjectMapper;
import org.restlet.resource.ClientResource;

import com.esofthead.mycollab.common.domain.GroupItem;
import com.esofthead.mycollab.core.utils.BeanUtility;

public class Test {
	public static void main(String[] args) throws Exception {
		ClientResource mailRoot = new ClientResource(
				"http://localhost:8080/mycollab-web/api/signin");

		// GroupItem item = new GroupItem();
		// item.setGroupname("AAA");
		// item.setGroupid("1");
		// item.setValue(4);
		//
		// mailRoot.addQueryParameter("format", "json");
		String value = mailRoot.get().getText();
		System.out.println(value);
		ObjectMapper mapper = new ObjectMapper();
		GroupItem readValue = mapper.readValue(value, GroupItem.class);
		System.out.println("GROUP: " + BeanUtility.printBeanObj(readValue));
		// System.out.println("VAL: " + BeanUtility.printBeanObj(readValue) +
		// " " + mailRoot.get().getMediaType());
		// System.out.println();
		// Representation post = (Representation) mailRoot.post(item,
		// MediaType.APPLICATION_JAVA_OBJECT);
		// System.out.println(post.getText() + "  " + post);
	}
}
