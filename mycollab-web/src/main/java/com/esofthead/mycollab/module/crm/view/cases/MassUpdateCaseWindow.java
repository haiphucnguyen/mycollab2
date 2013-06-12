package com.esofthead.mycollab.module.crm.view.cases;

import com.esofthead.mycollab.module.crm.domain.CaseWithBLOBs;
import com.esofthead.mycollab.module.crm.domain.Contact;
import com.esofthead.mycollab.vaadin.ui.AdvancedEditBeanForm;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.vaadin.ui.IFormLayoutFactory;
import com.esofthead.mycollab.vaadin.ui.MassUpdateWindow;
import com.esofthead.mycollab.vaadin.ui.ReadViewLayout;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Field;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;

public class MassUpdateCaseWindow extends MassUpdateWindow<CaseWithBLOBs>{
	private static final long serialVersionUID = 1L;
	private CaseWithBLOBs cases;
	private final EditForm updateForm;
	private ReadViewLayout caseAddLayout;
	private VerticalLayout layout;

	public MassUpdateCaseWindow(String title,CaseListPresenter presenter) {
		super(title, presenter);
		
		this.setWidth("1000px");
		
		this.setIcon(new ThemeResource("icons/18/crm/case.png"));
		
		caseAddLayout = new ReadViewLayout(null);

		cases = new CaseWithBLOBs();

		layout = getLayout();

		updateForm = new EditForm();

		updateForm.setItemDataSource(new BeanItem<CaseWithBLOBs>(cases));

		caseAddLayout.addComponent(updateForm);

		this.addComponent(caseAddLayout);
	}

	private class EditForm extends AdvancedEditBeanForm<Contact> {
		private static final long serialVersionUID = 1L;

		@Override
		public void setItemDataSource(Item newDataSource) {
			setFormLayoutFactory(new MassUpdateContactFormLayoutFactory());
			setFormFieldFactory(new CaseEditFormFieldFactory(cases));
			super.setItemDataSource(newDataSource);
		}

		private class MassUpdateContactFormLayoutFactory implements
				IFormLayoutFactory {
			private static final long serialVersionUID = 1L;

			private GridFormLayoutHelper informationLayout;

			@Override
			public Layout getLayout() {
				VerticalLayout formLayout = new VerticalLayout();

				Label organizationHeader = new Label("Case Information");
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
				formLayout.addStyleName("v-csslayout v-csslayout-readview-layout-body readview-layout-body");

				return formLayout;
			}
//			 priority, status, account name, origin, type,  reason, assignuser
			@Override
			public void attachField(final Object propertyId, final Field field) {
				 if (propertyId.equals("priority")) {
		                informationLayout.addComponent(field, "Priority", 0, 0);
		            } else if (propertyId.equals("status")) {
		                informationLayout.addComponent(field, "Status", 1, 0);
		            } else if (propertyId.equals("accountid")) {
		                informationLayout.addComponent(field, "Account Name", 0, 1);
		            }else if (propertyId.equals("origin")) {
		                informationLayout.addComponent(field, "Origin", 1, 1);
		            } else if (propertyId.equals("type")) {
		                informationLayout.addComponent(field, "Type", 0, 2);
		            } else if (propertyId.equals("reason")) {
		                informationLayout.addComponent(field, "Reason", 1, 2);
		            }else if (propertyId.equals("assignuser")) {
		                informationLayout.addComponent(field, "Assigned User", 0, 3);
		            }
			}
		}
	}

	@Override
	protected CaseWithBLOBs getItem() {
		return cases;
	}
}
