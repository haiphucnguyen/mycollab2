package com.esofthead.mycollab.premium.module.project.view.problem;

import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.module.crm.domain.Account;
import com.esofthead.mycollab.module.project.domain.Problem;
import com.esofthead.mycollab.vaadin.ui.AdvancedEditBeanForm;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.vaadin.ui.IFormLayoutFactory;
import com.esofthead.mycollab.vaadin.ui.MassUpdateLayout;
import com.esofthead.mycollab.vaadin.ui.MassUpdateWindow;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Field;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * @author haiphucnguyen
 * 
 */
public class MassUpdateProblemWindow extends MassUpdateWindow<Problem> {
	private static final long serialVersionUID = 1L;

	private final Problem problem;
	private final EditForm updateForm;
	private final MassUpdateLayout accountAddLayout;
	private final VerticalLayout layout;

	public MassUpdateProblemWindow(final String title,
			final ProblemListPresenter presenter) {
		super(title, presenter);
		this.setWidth("1000px");

		this.setIcon(MyCollabResource
				.newResource("icons/24/project/problem.png"));

		this.accountAddLayout = new MassUpdateLayout();

		this.problem = new Problem();

		this.layout = this.getLayout();

		this.updateForm = new EditForm();

		this.updateForm.setItemDataSource(new BeanItem<Problem>(this.problem));

		this.accountAddLayout.addBody(this.updateForm);

		this.setContent(this.accountAddLayout);
	}

	private class EditForm extends AdvancedEditBeanForm<Account> {
		private static final long serialVersionUID = 1L;

		@Override
		public void setItemDataSource(final Item newDataSource) {
			this.setFormLayoutFactory(new MassUpdateAccountFormLayoutFactory());
			this.setFormFieldFactory(new ProblemEditFormFieldFactory(
					MassUpdateProblemWindow.this.problem));
			super.setItemDataSource(newDataSource);
		}

		private class MassUpdateAccountFormLayoutFactory implements
				IFormLayoutFactory {
			private static final long serialVersionUID = 1L;

			private GridFormLayoutHelper informationLayout;

			@Override
			public Layout getLayout() {
				final VerticalLayout formLayout = new VerticalLayout();

				final Label organizationHeader = new Label(
						"Problem Information");
				organizationHeader.setStyleName("h2");
				formLayout.addComponent(organizationHeader);

				this.informationLayout = new GridFormLayoutHelper(2, 6, "100%",
						"167px", Alignment.MIDDLE_LEFT);

				this.informationLayout.getLayout().setWidth("100%");
				this.informationLayout.getLayout().setMargin(false);
				this.informationLayout.getLayout().setSpacing(false);
				this.informationLayout.getLayout().addStyleName(
						"colored-gridlayout");
				formLayout.addComponent(this.informationLayout.getLayout());
				formLayout.addComponent(MassUpdateProblemWindow.this.layout);

				return formLayout;
			}

			// Raised By, Assign To, Date Due, Status, Probability
			@Override
			public void attachField(final Object propertyId, final Field field) {
				if (propertyId.equals("raisedbyuser")) {
					this.informationLayout.addComponent(field, "Raised by", 0,
							0);
				} else if (propertyId.equals("assigntouser")) {
					this.informationLayout
							.addComponent(
									field,
									LocalizationHelper
											.getMessage(GenericI18Enum.FORM_ASSIGNEE_FIELD),
									1, 0);
				} else if (propertyId.equals("datedue")) {
					this.informationLayout
							.addComponent(field, "Date due", 0, 1);
				} else if (propertyId.equals("priority")) {
					this.informationLayout
							.addComponent(field, "Priority", 1, 1);
				} else if (propertyId.equals("status")) {
					this.informationLayout.addComponent(field, "Status", 0, 2);
				}
			}
		}
	}

	@Override
	protected Problem getItem() {
		return this.problem;
	}
}
