package com.esofthead.mycollab.premium.module.project.view.problem;

import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.module.project.domain.Problem;
import com.esofthead.mycollab.module.project.i18n.ProblemI18nEnum;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.ui.AbstractBeanFieldGroupEditFieldFactory;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.vaadin.ui.IFormLayoutFactory;
import com.esofthead.mycollab.vaadin.ui.MassUpdateWindow;
import com.esofthead.mycollab.vaadin.ui.MyCollabResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Field;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * @author MyCollab Ltd.
 * @since 2.0
 * 
 */
public class MassUpdateProblemWindow extends MassUpdateWindow<Problem> {
	private static final long serialVersionUID = 1L;

	public MassUpdateProblemWindow(final String title,
			final ProblemListPresenter presenter) {
		super(title, MyCollabResource
				.newResource("icons/24/project/problem.png"), new Problem(),
				presenter);
	}

	@Override
	protected IFormLayoutFactory buildFormLayoutFactory() {
		return new MassUpdateProblemFormLayoutFactory();
	}

	@Override
	protected AbstractBeanFieldGroupEditFieldFactory<Problem> buildBeanFormFieldFactory() {
		return new ProblemEditFormFieldFactory(updateForm, false);
	}

	private class MassUpdateProblemFormLayoutFactory implements
			IFormLayoutFactory {
		private static final long serialVersionUID = 1L;

		private GridFormLayoutHelper informationLayout;

		@Override
		public Layout getLayout() {
			final VerticalLayout formLayout = new VerticalLayout();

			final Label organizationHeader = new Label("Problem Information");
			organizationHeader.setStyleName("h2");
			formLayout.addComponent(organizationHeader);

			this.informationLayout = new GridFormLayoutHelper(2, 6, "100%",
					"167px", Alignment.TOP_LEFT);

			this.informationLayout.getLayout().setWidth("100%");
			this.informationLayout.getLayout().setMargin(false);
			this.informationLayout.getLayout().setSpacing(false);
			this.informationLayout.getLayout().addStyleName(
					"colored-gridlayout");
			formLayout.addComponent(this.informationLayout.getLayout());
			formLayout.addComponent(buildButtonControls());

			formLayout
					.addStyleName("v-csslayout v-csslayout-readview-layout-body readview-layout-body");
			return formLayout;
		}

		// Raised By, Assign To, Date Due, Status, Probability
		@Override
		public void attachField(final Object propertyId, final Field<?> field) {
			if (propertyId.equals("raisedbyuser")) {
				this.informationLayout.addComponent(field,
						AppContext.getMessage(ProblemI18nEnum.FORM_RAISED_BY),
						0, 0);
			} else if (propertyId.equals("assigntouser")) {
				this.informationLayout.addComponent(field, AppContext
						.getMessage(GenericI18Enum.FORM_ASSIGNEE_FIELD), 1, 0);
			} else if (propertyId.equals("datedue")) {
				this.informationLayout.addComponent(field,
						AppContext.getMessage(ProblemI18nEnum.FORM_DATE_DUE),
						0, 1);
			} else if (propertyId.equals("priority")) {
				this.informationLayout.addComponent(field,
						AppContext.getMessage(ProblemI18nEnum.FORM_PRIORITY),
						1, 1);
			} else if (propertyId.equals("status")) {
				this.informationLayout.addComponent(field,
						AppContext.getMessage(ProblemI18nEnum.FORM_STATUS), 0,
						2);
			}
		}
	}
}
