/**
 * This file is part of mycollab-web.
 *
 * mycollab-web is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-web is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-web.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.vaadin.ui;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.addon.customfield.CustomField;
import org.vaadin.easyuploads.MultiFileUploadExt;

import com.esofthead.mycollab.core.utils.StringUtils;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * @author haiphucnguyen
 *
 */
public class DefaultFormViewFieldFactory extends DefaultFieldFactory {
	private static Logger log = LoggerFactory
			.getLogger(DefaultFormViewFieldFactory.class);

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	@Override
	public Field createField(final Item item, final Object propertyId,
			final com.vaadin.ui.Component uiContext) {

		Field field = onCreateField(item, propertyId, uiContext);
		if (field == null) {
			final Object bean = ((BeanItem<Object>) item).getBean();

			try {
				final String propertyValue = BeanUtils.getProperty(bean,
						(String) propertyId);
				field = new FormViewField(propertyValue);
			} catch (final Exception e) {
				log.error("Error while get field value", e);
				field = new FormViewField("Error");
			}
		}

		return field;
	}

	protected Field onCreateField(final Item item, final Object propertyId,
			final com.vaadin.ui.Component uiContext) {
		return null;
	}

	public static interface AttachmentUploadField extends Field {

		void saveContentsToRepo(String attachmentPath);
	}

	public static class FormAttachmentUploadField extends CustomField implements
			AttachmentUploadField {
		private static final long serialVersionUID = 1L;
		private final MultiFileUploadExt uploadExt;
		private final AttachmentPanel attachmentPanel;

		public FormAttachmentUploadField() {
			final VerticalLayout layout = new VerticalLayout();
			attachmentPanel = new AttachmentPanel();
			uploadExt = new MultiFileUploadExt(attachmentPanel);
			layout.addComponent(attachmentPanel);
			layout.addComponent(uploadExt);
			setCompositionRoot(layout);
		}

		public void getAttachments(String attachmentPath) {
			attachmentPanel.getAttachments(attachmentPath);
		}

		@Override
		public Class<?> getType() {
			return Object.class;
		}

		@Override
		public void saveContentsToRepo(String attachmentPath) {
			attachmentPanel.saveContentsToRepo(attachmentPath);
		}
	}

	public static class FormContainerField extends CustomField {

		private static final long serialVersionUID = 1L;

		public FormContainerField(final ComponentContainer container) {
			setCompositionRoot(container);
		}

		@Override
		public Class<?> getType() {
			return Object.class;
		}
	}

	public static class FormContainerHorizontalViewField extends CustomField {
		private static final long serialVersionUID = 1L;
		private final HorizontalLayout layout;

		public FormContainerHorizontalViewField() {
			this(true);
		}

		public FormContainerHorizontalViewField(boolean spacing) {
			layout = new HorizontalLayout();
			layout.setWidth("100%");
			layout.setSpacing(spacing);
			setCompositionRoot(layout);
		}

		public void addComponentField(final Component component) {
			layout.addComponent(component);
		}

		public HorizontalLayout getLayout() {
			return layout;
		}

		@Override
		public Class<?> getType() {
			return Object.class;
		}
	}

	public static class DateFieldWithUserTimeZone extends CustomField {
		private static final long serialVersionUID = 1L;
		private static String DATE_FORMAT = "MM/dd/yyyy";
		private static String DATETIME_FORMAT = "MM/dd/yyyy HH:mm";
		private SimpleDateFormat simpleDateTimeFormat = new SimpleDateFormat(
				DATE_FORMAT);
		private Calendar calendar = Calendar.getInstance();

		public DateFieldWithUserTimeZone(final Date date, String dateformat) {
			if (date == null) {
				this.setCompositionRoot(new Label());
			} else {
				if (dateformat.equals("DATETIME_FIELD")) {
					simpleDateTimeFormat = new SimpleDateFormat(DATETIME_FORMAT);
				}
				calendar.setTime(date);
				int timeFormat = calendar.get(Calendar.AM_PM);
				if (timeFormat == 1) {
					calendar.add(Calendar.HOUR_OF_DAY, -12);
				}
				String timeStr = simpleDateTimeFormat
						.format(calendar.getTime())
						+ " "
						+ ((timeFormat == 0) ? "AM" : "PM");
				Label label = new Label();
				label.setValue(timeStr);
				HorizontalLayout layout = new HorizontalLayout();
				layout.addComponent(label);
				this.setCompositionRoot(layout);
			}
		}

		@Override
		public Class<String> getType() {
			return String.class;
		}
	}

	public static class FormContainerViewField extends CustomField {
		private static final long serialVersionUID = 1L;
		private final CssLayout layout;

		public FormContainerViewField() {
			layout = new CssLayout();
			layout.setWidth("100%");
			setCompositionRoot(layout);
			setStyleName(UIConstants.FORM_CONTAINER_VIEW);
		}

		public void addComponentField(final Component component) {
			layout.addComponent(component);
		}

		@Override
		public Class<?> getType() {
			return Object.class;
		}
	}

	public static class FormDateViewField extends CustomField {
		private static final long serialVersionUID = 1L;

		public FormDateViewField(final Date date) {
			final Label l = new Label();
			l.setWidth("100%");
			if (date == null) {
				l.setValue("&nbsp;");
				l.setContentMode(Label.CONTENT_XHTML);
			} else {
				l.setValue(AppContext.formatDate(date));
			}
			setCompositionRoot(l);
		}

		@Override
		public Class<?> getType() {
			return Object.class;
		}
	}

	public static class FormEmailLinkViewField extends CustomField {

		private static final long serialVersionUID = 1L;

		public FormEmailLinkViewField(final String email) {
			final EmailLink l = new EmailLink(email);
			l.setWidth(UIConstants.DEFAULT_CONTROL_WIDTH);
			setCompositionRoot(l);
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}
	}

	public static class FormLinkViewField extends CustomField {

		private static final long serialVersionUID = 1L;

		public FormLinkViewField(final String value,
				final Button.ClickListener listener) {
			if (value != null && (!value.equals(""))) {
				final ButtonLink l = new ButtonLink(value, listener);
				l.setWidth("100%");
				setCompositionRoot(l);
			} else {
				final Label l = new Label("&nbsp;", Label.CONTENT_XHTML);
				l.setWidth("100%");
				setCompositionRoot(l);
			}
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}
	}

	public static class FormDetectAndDisplayUrlViewField extends CustomField {

		private static final long serialVersionUID = 1L;

		public FormDetectAndDisplayUrlViewField(String url) {
			if (url == null || url.trim().equals("")) {
				Label lbl = new Label("&nbsp;");
				lbl.setContentMode(Label.CONTENT_XHTML);
				lbl.setWidth("100%");
				setCompositionRoot(lbl);
			} else {
				final Label link = new Label(StringUtils.formatExtraLink(url),
						Label.CONTENT_XHTML);
				setCompositionRoot(link);
			}
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}
	}

	public static class FormUrlLinkViewField extends CustomField {

		private static final long serialVersionUID = 1L;

		public FormUrlLinkViewField(String url) {
			if (url == null || url.trim().equals("")) {
				Label lbl = new Label("&nbsp;");
				lbl.setContentMode(Label.CONTENT_XHTML);
				lbl.setWidth("100%");
				setCompositionRoot(lbl);
			} else {
				final Link link = new UrlLink(url);
				link.setWidth(UIConstants.DEFAULT_CONTROL_WIDTH);
				setCompositionRoot(link);
			}
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}
	}

	public static class FormUrlSocialNetworkLinkViewField extends CustomField {

		private static final long serialVersionUID = 1L;

		public FormUrlSocialNetworkLinkViewField(String caption,
				String linkAccount) {
			if (caption == null || caption.trim().equals("")) {
				Label lbl = new Label("&nbsp;");
				lbl.setContentMode(Label.CONTENT_XHTML);
				lbl.setWidth("100%");
				setCompositionRoot(lbl);
			} else {
				linkAccount = (linkAccount == null) ? "" : linkAccount;
				final Link link = new SocialNetworkLink(caption, linkAccount);
				link.setWidth(UIConstants.DEFAULT_CONTROL_WIDTH);
				setCompositionRoot(link);
			}
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}
	}

	public static class FormViewField extends CustomField {

		private static final long serialVersionUID = 1L;

		public FormViewField(final String value) {
			this(value, Label.CONTENT_DEFAULT);
		}

		public FormViewField(final String value, final int contentMode) {
			final Label l = new Label();
			l.setWidth("100%");
			l.setContentMode(contentMode);
			setCompositionRoot(l);
			if (value != null && (!value.equals(""))) {
				l.setValue(value);
			} else {
				l.setValue("&nbsp;");
				l.setContentMode(Label.CONTENT_XHTML);
			}
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}
	}

	public static class UserLinkViewField extends CustomField {
		private static final long serialVersionUID = 1L;

		public UserLinkViewField(final String username, String userAvatarId,
				final String fullName) {
			final UserLink l = new UserLink(username, userAvatarId, fullName);
			l.setWidth(UIConstants.DEFAULT_CONTROL_WIDTH);
			setCompositionRoot(l);
		}

		@Override
		public Class<?> getType() {
			return Object.class;
		}
	}
}
