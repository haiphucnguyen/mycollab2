package com.esofthead.mycollab.module.wiki;

import info.bliki.api.Page;
import info.bliki.api.User;

import java.util.List;

public class WikiTest {
	public static void main(String[] args) {
		String[] listOfTitleStrings = { "Web service" };
		User user = new User("", "", "http://en.wikipedia.org/w/api.php");
		user.login();
		List<Page> listOfPages = user.queryContent(listOfTitleStrings);
		for (Page page : listOfPages) {
			MyWikiModel wikiModel = new MyWikiModel("${image}", "${title}");
			String currentContent = page.getCurrentContent();
			String html = wikiModel.render(new MyHtmlConverter(true, true),
					currentContent);
			System.out.println(html);
		}
	}
}
