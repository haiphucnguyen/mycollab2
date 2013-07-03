package com.esofthead.mycollab.pages;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.StatelessForm;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esofthead.mycollab.base.BasePage;

public class SignInPage extends BasePage {
	
	private static final long serialVersionUID = 1L;
	
	private static Logger log = LoggerFactory.getLogger(SignInPage.class);
	
	public SignInPage(final PageParameters parameters){
		super(parameters);
		final TextField<String> email = new TextField<String>(
				"emailfield", new Model<String>());
		
		StatelessForm<Void> form = new StatelessForm<Void>("signinform") {
			
			private static final long serialVersionUID = 1L;
			
			@Override
			protected void onSubmit() {
				// TODO Auto-generated method stub				
				DefaultHttpClient httpClient = new DefaultHttpClient();
				HttpPost postRequest = new HttpPost(
						"http://localhost:8080/mycollab-web/api/signup");
				List<NameValuePair> nvps = new ArrayList<NameValuePair>();
				nvps.add(new BasicNameValuePair("email", email.getModelObject()));
				
				try {
					postRequest.setEntity(new UrlEncodedFormEntity(nvps));

					HttpResponse response = httpClient.execute(postRequest);
					StatusLine status = response.getStatusLine();
					if (status.getStatusCode() == 200) {
						// redirect to signup success page and ask user verify
						// his signup by email
						log.debug("Signup successfully. Status is "
								+ status.getReasonPhrase());
					} else {
						// inform error with user
					}
				} catch (Exception e) {
					log.error("Send post request fail");
				}
			}
		};
		
		add(form);
		form.add(email);
		
		add(new Label("pagetitle", "Sign In"));
	}
}
