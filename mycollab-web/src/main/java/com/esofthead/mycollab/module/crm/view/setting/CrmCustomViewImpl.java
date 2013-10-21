package com.esofthead.mycollab.module.crm.view.setting;

import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.form.view.builder.type.DynaForm;
import com.esofthead.mycollab.module.crm.CrmTypeConstants;
import com.esofthead.mycollab.module.crm.view.account.AccountDefaultDynaFormFactory;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.ValueComboBox;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

@ViewComponent
public class CrmCustomViewImpl extends AbstractView implements CrmCustomView {
	private static final long serialVersionUID = 1L;

	private Label headerLbl;
	private ModuleSelectionComboBox moduleComboBox;
	private DynaForm form;;

	public CrmCustomViewImpl() {
		headerLbl = new Label();
		this.addComponent(headerLbl);

		Label descLbl = new Label(
				"Customize the page layout by changing the order of the columns and fields, marking fields as mandatory, adding or removing the fields and sections. You can drag and drop the section header to reorder the sections. You need to drag and drop the fields to move them to the List of Removed Fields");
		this.addComponent(descLbl);

		HorizontalLayout controlLayout = new HorizontalLayout();
		moduleComboBox = new ModuleSelectionComboBox();

		controlLayout.addComponent(new Label("Module"));
		controlLayout.addComponent(moduleComboBox);

		Button createCustomFieldBtn = new Button("New Custom Field",
				new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(ClickEvent event) {
						// TODO Auto-generated method stub

					}
				});
		controlLayout.addComponent(createCustomFieldBtn);

		Button createSectionBtn = new Button("Create Section",
				new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(ClickEvent event) {
						CreateSectionWindow createSectionWindow = new CreateSectionWindow(
								CrmCustomViewImpl.this);
						getWindow().addWindow(createSectionWindow);

					}
				});
		controlLayout.addComponent(createSectionBtn);
		this.addComponent(controlLayout);
	}

	@Override
	public void display(String moduleName) {
		moduleComboBox.select(moduleName);

		if (CrmTypeConstants.ACCOUNT.equals(moduleName)) {
			form = AccountDefaultDynaFormFactory.getForm(AppContext
					.getAccountId());
		} else {
			throw new MyCollabException(
					"Do not support custom layout of module " + moduleName);
		}
	}

	private class ModuleSelectionComboBox extends ValueComboBox {
		private static final long serialVersionUID = 1L;

		public ModuleSelectionComboBox() {
			super(false, CrmTypeConstants.ACCOUNT, CrmTypeConstants.CONTACT,
					CrmTypeConstants.CAMPAIGN, CrmTypeConstants.LEAD,
					CrmTypeConstants.OPPORTUNITY, CrmTypeConstants.CASE,
					CrmTypeConstants.TASK, CrmTypeConstants.CALL,
					CrmTypeConstants.MEETING);
		}
	}

}
