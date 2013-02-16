package com.esofthead.mycollab.wiki;

import info.bliki.wiki.model.WikiModel;

public class MyCollabWikiModel extends WikiModel {

	public MyCollabWikiModel(String imageBaseURL, String linkBaseURL) {
		super(imageBaseURL, linkBaseURL);
	}

	public static void main(String[] args) {
		MyCollabWikiModel wikiModel = new MyCollabWikiModel(
				"http://www.mywiki.com/wiki/${image}",
				"http://www.mywiki.com/wiki/${title}");
		String wikiText = "some wiki text we would like [[http://www.vnexpress.net]] to convert to HTML";
		String htmlStr = wikiModel.render(wikiText);
		System.out.print(htmlStr);
	}

}
