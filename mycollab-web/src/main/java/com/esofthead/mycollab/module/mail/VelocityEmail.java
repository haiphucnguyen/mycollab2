package com.esofthead.mycollab.module.mail;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.Properties;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.runtime.RuntimeConstants;

/**
 * Extends the HtmlEmail class from Apache Commons. Allows the content of the
 * email to be easily created via the specified Velocity template and POJO.
 * 
 * <h4>Prerequisites</h4>
 * <p>
 * These instructions work well in a J2EE environment. Feel free to tweak
 * in order to fit your own needs.</p>
 * <ul>
 * <li>Include the Velocity jar file in your project and create a 
 * velocity.properties configuration file. For example, this configuration will 
 * cause Velocity to search for template files on the classpath:
 * <pre>
 * resource.loader=class
 * class.resource.loader.class=org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader
 * </pre>
 * <li>Include the commons-email jar file from Apache Commons in your project.
 * </ul>
 * 
 * <h4>Example Usage</h4>
 * <p>
 * <pre>
 * {@code
 * // Initialize the VelocityEmail object
 * VelocityEmail email = new VelocityEmail(templateName);
 * email.setHostName(smtphost);
 * email.setFrom(fromAddress);
 * email.setTo(toAddress);
 * 
 * // Merge a single javabean with the template. It will be referenced as in
 * // $bean.fieldname in your template. 
 * email.setHtmlMsg(javabean)
 * 
 * // Alternatively, you may create your own VelocityContext to merge multiple
 * // javabeans with your template.
 * // VelocityContext context = new VelocityContext();
 * // context.put("bean1", javabean1);
 * // context.put("bean2", javabean2);
 * // email.setHtmlMsg(context);
 * 
 * // Send the email
 * email.send();
 * }
 * </pre>
 * 
 * @author Matthew Toso
 * @version 1.0
 *
 */
public class VelocityEmail extends HtmlEmail {
	
	/**
	 * The VelocityEmail constructor.
	 * 
	 * @param templateFile the Velocity template file name
	 */
	public VelocityEmail(String templateFile) {
		setTemplateFile(templateFile);
	}
	
	/**
	 * The name of the Velocity template file
	 */
	private String templateFile;

	/**
	 * Set the HTML content of the email, merging the template file with the 
	 * provided javabean. The javabean will be called "bean" in the context.
	 * 
	 * @param bean The javabean to be merged
	 * @throws EmailException
	 */
	public void setHtmlMsg(Object bean) throws EmailException {
			VelocityContext context = new VelocityContext();
	        context.put("bean", bean);
	        setHtmlMsg(context);
	}
	
	/**
	 * Set the HTML content of the email, merging the template file with the
	 * provided context. Allows for multiple objects in the context.
	 * 
	 * @param context The Velocity context to be merged
	 * @throws EmailException
	 */
	public void setHtmlMsg(VelocityContext context) throws EmailException {
		try {
			Velocity.init(getPropertiesFromClasspath("velocity.properties"));
	        StringWriter writer = new StringWriter();
	        Velocity.mergeTemplate(templateFile, RuntimeConstants.ENCODING_DEFAULT, context, writer);
	        setHtmlMsg(writer.toString());
		} catch (Exception e) {
			throw new EmailException(e.getMessage());
		}
	}
	
	/**
	 * Set the text content of the email, merging the template file with the 
	 * provided javabean. The javabean will be called "bean" in the context.
	 * 
	 * @param bean The javabean to be merged
	 * @throws EmailException
	 */
	public void setTextMsg(Object bean) throws EmailException {
			VelocityContext context = new VelocityContext();
	        context.put("bean", bean);
	        setTextMsg(context);
	}
	
	/**
	 * Set the text content of the email, merging the template file with the
	 * provided context. Allows for multiple objects in the context.
	 * 
	 * @param context The Velocity context to be merged
	 * @throws EmailException
	 */
	public void setTextMsg(VelocityContext context) throws EmailException {
		try {
			Velocity.init(getPropertiesFromClasspath("velocity.properties"));
	        StringWriter writer = new StringWriter();
	        Velocity.mergeTemplate(templateFile, RuntimeConstants.ENCODING_DEFAULT, context, writer);
	        setTextMsg(writer.toString());
		} catch (Exception e) {
			throw new EmailException(e.getMessage());
		}
	}

	/**
	 * Return the name of the Velocity template file
	 * 
	 * @return the Velocity template file name
	 */
	public String getTemplateFile() {
		return templateFile;
	}

	/**
	 * Set the name of the Velocity template file
	 * 
	 * @param templateFile the Velocity template file name 
	 */
	public void setTemplateFile(String templateFile) {
		this.templateFile = templateFile;
	}
	
	private Properties getPropertiesFromClasspath(String propFileName) throws IOException {
        Properties props = new Properties();
        InputStream inputStream = getClass().getClassLoader()
            .getResourceAsStream(propFileName);

        if (inputStream == null) {
            throw new FileNotFoundException("property file '" + propFileName
                + "' not found in the classpath");
        }

        props.load(inputStream);

        return props;
    }
}
