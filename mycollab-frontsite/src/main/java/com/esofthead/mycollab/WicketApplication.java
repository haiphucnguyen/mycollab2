package com.esofthead.mycollab;

import org.apache.wicket.core.request.handler.PageProvider;
import org.apache.wicket.core.request.handler.RenderPageRequestHandler;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.IRequestHandler;
import org.apache.wicket.request.cycle.AbstractRequestCycleListener;
import org.apache.wicket.request.cycle.RequestCycle;

import com.esofthead.mycollab.pages.Error404Page;
import com.esofthead.mycollab.pages.Error500Page;
import com.esofthead.mycollab.pages.HomePage;
import com.esofthead.mycollab.pages.PricingPage;
import com.esofthead.mycollab.pages.PrivacyPage;
import com.esofthead.mycollab.pages.SignInPage;
import com.esofthead.mycollab.pages.SignUpPage;
import com.esofthead.mycollab.pages.TermOfServicePage;
import com.esofthead.mycollab.pages.TourPage;

/**
 * Application object for your web application. If you want to run this
 * application without deploying, run the Start class.
 * 
 * @see com.esofthead.mycollab.Start#main(String[])
 */
public class WicketApplication extends WebApplication {
	/**
	 * @see org.apache.wicket.Application#getHomePage()
	 */
	@Override
	public Class<? extends WebPage> getHomePage() {
		return HomePage.class;
	}

	/**
	 * @see org.apache.wicket.Application#init()
	 */
	@Override
	public void init() {
		super.init();

		// add your configuration here

		this.getMarkupSettings().setStripWicketTags(true);

		this.mountPage("/tour", TourPage.class);
		this.mountPage("/pricing", PricingPage.class);
		this.mountPage("/privacy", PrivacyPage.class);
		this.mountPage("/signup", SignUpPage.class);
		this.mountPage("/terms", TermOfServicePage.class);
		this.mountPage("/signin", SignInPage.class);
		this.mountPage("/error404", Error404Page.class);
		this.mountPage("/error500", Error500Page.class);

		this.getApplicationSettings().setInternalErrorPage(Error500Page.class);
		this.getRequestCycleListeners().add(new AbstractRequestCycleListener() {
			@Override
			public IRequestHandler onException(final RequestCycle cycle,
					final Exception e) {
				return new RenderPageRequestHandler(new PageProvider(
						new Error500Page(e)));
			}
		});
	}
}
