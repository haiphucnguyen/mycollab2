package com.esofthead.mycollab;

import org.apache.wicket.RuntimeConfigurationType;
import org.apache.wicket.core.request.handler.PageProvider;
import org.apache.wicket.core.request.handler.RenderPageRequestHandler;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.protocol.https.HttpsConfig;
import org.apache.wicket.protocol.https.HttpsMapper;
import org.apache.wicket.protocol.https.Scheme;
import org.apache.wicket.request.IRequestHandler;
import org.apache.wicket.request.cycle.AbstractRequestCycleListener;
import org.apache.wicket.request.cycle.RequestCycle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
	private Logger log = LoggerFactory.getLogger(WicketApplication.class);

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
		getDebugSettings().setAjaxDebugModeEnabled(false);
		this.getMarkupSettings().setStripWicketTags(true);

		this.mountPage("/tour", TourPage.class);
		this.mountPage("/pricing", PricingPage.class);
		this.mountPage("/privacy", PrivacyPage.class);
		this.mountPage("/signup", SignUpPage.class);
		this.mountPage("/terms", TermOfServicePage.class);
		this.mountPage("/signin", SignInPage.class);
		this.mountPage("/frontsite/signin", SignInPage.class);
		this.mountPage("/error404", Error404Page.class);
		this.mountPage("/error500", Error500Page.class);

		this.getApplicationSettings().setInternalErrorPage(Error500Page.class);
		this.getRequestCycleListeners().add(new AbstractRequestCycleListener() {
			@Override
			public IRequestHandler onException(final RequestCycle cycle,
					final Exception e) {
				log.error("Error while process", e);
				return new RenderPageRequestHandler(new PageProvider(
						new Error500Page(e)));
			}
		});
		
		// notice that in most cases this should be done as the
		// last mounting-related operation because it replaces the root mapper
//		setRootRequestMapper(new HttpsMapper(getRootRequestMapper(), new HttpsConfig()));

		setRootRequestMapper(new HttpsMapper(getRootRequestMapper(),
				new HttpsConfig()) {
			@Override
			protected Scheme getDesiredSchemeFor(Class pageClass) {
				if (getConfigurationType() == RuntimeConfigurationType.DEVELOPMENT) {
					log.debug("in development mode, returning HTTP");
					return Scheme.HTTP;
				} else {
					log.debug("not in development mode, letting the mapper decide");
					return super.getDesiredSchemeFor(pageClass);
				}
			}
		});
	}

}
