package com.esofthead.mycollab;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.wicket.core.request.handler.PageProvider;
import org.apache.wicket.core.request.handler.RenderPageRequestHandler;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.protocol.http.servlet.ServletWebRequest;
import org.apache.wicket.protocol.http.servlet.ServletWebResponse;
import org.apache.wicket.request.IRequestHandler;
import org.apache.wicket.request.cycle.AbstractRequestCycleListener;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.http.WebRequest;
import org.apache.wicket.request.http.WebResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esofthead.mycollab.pages.BlogPage;
import com.esofthead.mycollab.pages.ContactUsPage;
import com.esofthead.mycollab.pages.Error404Page;
import com.esofthead.mycollab.pages.Error500Page;
import com.esofthead.mycollab.pages.HomePage;
import com.esofthead.mycollab.pages.PricingPage;
import com.esofthead.mycollab.pages.PrivacyPage;
import com.esofthead.mycollab.pages.SignInPage;
import com.esofthead.mycollab.pages.SignUpPage;
import com.esofthead.mycollab.pages.TermOfServicePage;
import com.esofthead.mycollab.pages.TourPage;
import com.esofthead.mycollab.pages.tour.CRMPage;
import com.esofthead.mycollab.pages.tour.DocumentManagementPage;
import com.esofthead.mycollab.pages.tour.ProjectManagementPage;
import com.esofthead.mycollab.pages.tour.WhoisitForPage;

/**
 * Application object for your web application. If you want to run this
 * application without deploying, run the Start class.
 * 
 * @see com.esofthead.mycollab.Start#main(String[])
 */
public class WicketApplication extends WebApplication {
	private final Logger log = LoggerFactory.getLogger(WicketApplication.class);

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
		this.getDebugSettings().setAjaxDebugModeEnabled(false);
		this.getMarkupSettings().setStripWicketTags(true);

		this.mountPage("/tour/whoisit", WhoisitForPage.class);
		this.mountPage("/tour", TourPage.class);
		this.mountPage("/tour/crm", CRMPage.class);
		this.mountPage("/tour/document_management",
				DocumentManagementPage.class);
		this.mountPage("/tour/project_management", ProjectManagementPage.class);
		this.mountPage("/pricing", PricingPage.class);
		this.mountPage("/privacy", PrivacyPage.class);
		this.mountPage("/signup", SignUpPage.class);
		this.mountPage("/contact-us", ContactUsPage.class);
		this.mountPage("/blog", BlogPage.class);
		this.mountPage("/terms", TermOfServicePage.class);
		this.mountPage("/signin/#{param1}", SignInPage.class);
		this.mountPage("/error404", Error404Page.class);
		this.mountPage("/error500", Error500Page.class);

		this.getApplicationSettings().setInternalErrorPage(Error500Page.class);
		this.getRequestCycleListeners().add(new AbstractRequestCycleListener() {
			@Override
			public IRequestHandler onException(final RequestCycle cycle,
					final Exception e) {
				WicketApplication.this.log.error("Error while process", e);
				return new RenderPageRequestHandler(new PageProvider(
						new Error500Page(e)));
			}
		});
	}

	@Override
	public WebRequest newWebRequest(final HttpServletRequest servletRequest,
			final String filterPath) {
		final WebRequest webRequest = super.newWebRequest(servletRequest,
				filterPath);
		return new ServletWebRequest(servletRequest, filterPath, webRequest
				.getUrl().canonical());
	}

	@Override
	protected WebResponse newWebResponse(final WebRequest webRequest,
			HttpServletResponse httpServletResponse) {
		return new ServletWebResponse((ServletWebRequest) webRequest,
				httpServletResponse) {

			@Override
			public String encodeURL(CharSequence url) {
				return isRobot(webRequest) ? url.toString() : super
						.encodeURL(url);
			}

			@Override
			public String encodeRedirectURL(CharSequence url) {
				return isRobot(webRequest) ? url.toString() : super
						.encodeRedirectURL(url);
			}

			private boolean isRobot(WebRequest request) {
				final String agent = webRequest.getHeader("User-Agent");
				return isAgent(agent);
			}
		};
	}

	private static final String[] botAgents = { "googlebot", "msnbot", "slurp",
			"jeeves", "appie", "architext", "jeeves", "bjaaland", "ferret",
			"gulliver", "harvest", "htdig", "linkwalker", "lycos_", "moget",
			"muscatferret", "myweb", "nomad", "scooter",
			"yahoo!\\sslurp\\schina", "slurp", "weblayers", "antibot",
			"bruinbot", "digout4u", "echo!", "ia_archiver", "jennybot",
			"mercator", "netcraft", "msnbot", "petersnews",
			"unlost_web_crawler", "voila", "webbase", "webcollage", "cfetch",
			"zyborg", "wisenutbot", "robot", "crawl", "spider" };

	public static boolean isAgent(final String agent) {
		if (agent != null) {
			final String lowerAgent = agent.toLowerCase();
			for (final String bot : botAgents) {
				if (lowerAgent.indexOf(bot) != -1) {
					return true;
				}
			}
		}
		return false;
	}

}
