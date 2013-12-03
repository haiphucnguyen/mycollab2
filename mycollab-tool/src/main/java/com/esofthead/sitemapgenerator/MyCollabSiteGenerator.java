package com.esofthead.sitemapgenerator;

import java.io.File;
import java.net.MalformedURLException;

import com.redfin.sitemapgenerator.SitemapIndexGenerator;
import com.redfin.sitemapgenerator.WebSitemapGenerator;

public class MyCollabSiteGenerator {
	public static void main(String[] args) throws MalformedURLException {
		WebSitemapGenerator wsg = new WebSitemapGenerator(
				"http://www.mycollab.com", new File(
						System.getProperty("user.dir")));
		wsg.addUrl("http://www.mycollab.com/");
		wsg.addUrl("http://www.mycollab.com/tour");
		wsg.addUrl("http://www.mycollab.com/tour/whoisit");
		wsg.addUrl("http://www.mycollab.com/tour/crm");
		wsg.addUrl("http://www.mycollab.com/tour/document_management");
		wsg.addUrl("http://www.mycollab.com/tour/project_management");
		wsg.addUrl("http://www.mycollab.com/pricing");
		wsg.addUrl("http://www.mycollab.com/privacy");
		wsg.addUrl("http://www.mycollab.com/signup");
		wsg.addUrl("http://www.mycollab.com/contact-us");
		wsg.addUrl("http://www.mycollab.com/blog");
		wsg.addUrl("http://www.mycollab.com/terms");
		wsg.write();

		SitemapIndexGenerator sig = new SitemapIndexGenerator(
				"http://www.mycollab.com", new File(
						System.getProperty("user.dir"), "test.xml"));
		sig.addUrl("http://wiki.mycollab.com/sitemap.xml");
		sig.addUrl("http://forum.mycollab.com/sitemapindex.xml");
		sig.write();
	}
}
