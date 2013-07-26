package com.esofthead.mycollab.vaadin.ui;

import java.util.Date;

import org.apache.commons.beanutils.BeanUtils;
import org.vaadin.addon.customfield.CustomField;
import org.vaadin.easyuploads.MultiFileUploadExt;

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

public class DefaultFormViewFieldFactory extends DefaultFieldFactory {

	public static interface AttachmentUploadField extends Field {

		void saveContentsToRepo(String attachmentPath);
	}

	public static class FormAttachmentDisplayField extends CustomField {
		private static final long serialVersionUID = 1L;

		public FormAttachmentDisplayField(final String type, final int typeid) {
			final Component comp = AttachmentDisplayComponent
					.getAttachmentDisplayComponent(type, typeid);
			if (comp == null || !(comp instanceof AttachmentDisplayComponent)) {
				final Label l = new Label("&nbsp;", Label.CONTENT_XHTML);
				setCompositionRoot(l);
			} else {
				setCompositionRoot(comp);
			}
		}

		@Override
		public Class<?> getType() {
			return Object.class;
		}
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
				field = new FormViewField("Error");
			}
		}

		return field;
	}

	protected Field onCreateField(final Item item, final Object propertyId,
			final com.vaadin.ui.Component uiContext) {
		return null;
	}
}
