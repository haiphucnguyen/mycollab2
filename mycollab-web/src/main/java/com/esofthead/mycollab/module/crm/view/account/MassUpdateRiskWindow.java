package com.esofthead.mycollab.module.crm.view.account;

import com.esofthead.mycollab.module.project.domain.Risk;
import com.esofthead.mycollab.module.project.view.risk.RiskEditFormFieldFactory;
import com.esofthead.mycollab.vaadin.mvp.MassUpdatePresenter;
import com.esofthead.mycollab.vaadin.ui.AdvancedEditBeanForm;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.vaadin.ui.IFormLayoutFactory;
import com.esofthead.mycollab.vaadin.ui.MassUpdateWindow;
import com.esofthead.mycollab.vaadin.ui.ReadViewLayout;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Field;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;

public class MassUpdateRiskWindow extends MassUpdateWindow<Risk> {
	private static final long serialVersionUID = 1L;

	private final Risk risk;
	private final EditForm updateForm;
	private ReadViewLayout riskAddLayout;
	private VerticalLayout layout;

	public MassUpdateRiskWindow(String title,
			MassUpdatePresenter<Risk> massUpdatePresenter) {
		super(title, massUpdatePresenter);
		this.setWidth("1000px");

		this.setIcon(MyCollabResource
				.newResource("icons/22/project/menu_risk.png"));

		riskAddLayout = new ReadViewLayout(null, false);

		risk = new Risk();

		layout = getLayout();

		updateForm = new EditForm();

		updateForm.setItemDataSource(new BeanItem<Risk>(risk));

		riskAddLayout.addComponent(updateForm);

		this.addComponent(riskAddLayout);
	}

	private class EditForm extends AdvancedEditBeanForm<Risk> {
		private static final long serialVersionUID = 1L;

		@Override
		public void setItemDataSource(Item newDataSource) {
			setFormLayoutFactory(new MassUpdateRiskFormLayoutFactory());
			setFormFieldFactory(new RiskEditFormFieldFactory(risk));
			super.setItemDataSource(newDataSource);
		}

		private class MassUpdateRiskFormLayoutFactory implements
				IFormLayoutFactory {
			private static final long serialVersionUID = 1L;

			private GridFormLayoutHelper informationLayout;

			@Override
			public Layout getLayout() {
				VerticalLayout formLayout = new VerticalLayout();

				Label organizationHeader = new Label("Risk Information");
				organizationHeader.setStyleName("h2");
				formLayout.addComponent(organizationHeader);

				informationLayout = new GridFormLayoutHelper(2, 6, "100%",
						"167px", Alignment.MIDDLE_LEFT);

				informationLayout.getLayout().setWidth("100%");
				informationLayout.getLayout().setMargin(false);
				informationLayout.getLayout().setSpacing(false);
				informationLayout.getLayout()
						.addStyleName("colored-gridlayout");
				formLayout.addComponent(informationLayout.getLayout());

				formLayout.addComponent(layout);
				formLayout
						.addStyleName("v-csslayout v-csslayout-readview-layout-body readview-layout-body");

				return formLayout;
			}

			// Raised By, Assign To, Date Due, Status, Consequence, Probability
			@Override
			public void attachField(Object propertyId, Field field) {
				if (propertyId.equals("raisedbyuser")) {
					this.informationLayout.addComponent(field, "Raised by", 0,
							0);
				} else if (propertyId.equals("assigntouser")) {
					this.informationLayout.addComponent(field, "Assigned to",
							1, 0);
				} else if (propertyId.equals("consequence")) {
					this.informationLayout.addComponent(field, "Consequence",
							0, 1);
				} else if (propertyId.equals("datedue")) {
					this.informationLayout
							.addComponent(field, "Date due", 1, 1);
				} else if (propertyId.equals("probalitity")) {
					this.informationLayout.addComponent(field, "Probality", 0,
							2);
				} else if (propertyId.equals("status")) {
					this.informationLayout.addComponent(field, "Status", 1, 2);
				}
			}
		}
	}

	@Override
	protected Risk getItem() {
		return this.risk;
	}
}
