package com.esofthead.mycollab.pages;

import org.apache.wicket.markup.html.basic.Label;

import com.esofthead.mycollab.base.BasePage;

public class TermOfServicePage extends BasePage {
	private static final long serialVersionUID = 1L;
	
	//private static Logger log = LoggerFactory.getLogger(SignInPage.class);
	
	public TermOfServicePage(){
		
		add(new Label("pagetitle", "Term of Service"));
	}
}
