package com.esofthead.mycollab.module.crm.view.cases;

import org.vaadin.addon.customfield.FieldWrapper;

import com.esofthead.mycollab.module.crm.domain.CaseWithBLOBs;
import com.esofthead.mycollab.module.crm.domain.SimpleCase;
import com.esofthead.mycollab.module.crm.service.CaseService;
import com.esofthead.mycollab.vaadin.ui.FieldSelection;
import com.esofthead.mycollab.vaadin.ui.UIHelper;
import com.esofthead.mycollab.web.AppContext;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.data.Property;
import com.vaadin.event.MouseEvents;
import com.vaadin.event.MouseEvents.ClickEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;

public class CaseSelectionField extends FieldWrapper<CaseWithBLOBs> implements
		FieldSelection {
	private static final long serialVersionUID = 1L;

	private HorizontalLayout layout;

	private SimpleCase cases;

	private TextField casesName;
	private Embedded browseBtn;
	private Embedded clearBtn;

	public CaseSelectionField() {
		super(new TextField(""), CaseWithBLOBs.class);

		layout = new HorizontalLayout();
		layout.setSpacing(true);

		casesName = new TextField();
		layout.addComponent(casesName);

		browseBtn = new Embedded(null,
				MyCollabResource.newResource("icons/16/browseItem.png"));
		layout.addComponent(browseBtn);
		layout.setComponentAlignment(browseBtn, Alignment.MIDDLE_LEFT);
		browseBtn.addListener(new MouseEvents.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void click(ClickEvent event) {
				CaseSelectionWindow casesWindow = new CaseSelectionWindow(
						CaseSelectionField.this);
				UIHelper.addWindowToRoot(CaseSelectionField.this, casesWindow);
				casesWindow.show();
			}
		});

		clearBtn = new Embedded(null,
				MyCollabResource.newResource("icons/16/clearItem.png"));
		clearBtn.addListener(new MouseEvents.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void click(ClickEvent event) {
				casesName.setValue("");
				CaseSelectionField.this.getWrappedField().setValue(null);
			}
		});
		layout.addComponent(clearBtn);
		layout.setComponentAlignment(clearBtn, Alignment.MIDDLE_LEFT);

		this.setCompositionRoot(layout);
		this.addListener(new Property.ValueChangeListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void valueChange(
					com.vaadin.data.Property.ValueChangeEvent event) {
				try {
					Integer casesId = Integer.parseInt((String) event
							.getProperty().getValue());
					CaseService casesService = AppContext
							.getSpringBean(CaseService.class);
					SimpleCase cases = casesService.findById(casesId,
							AppContext.getAccountId());
					if (cases != null) {
						casesName.setValue(cases.getSubject());
					}
				} catch (Exception e) {

				}

			}
		});
	}

	public void setCase(SimpleCase cases) {
		this.cases = cases;
		casesName.setValue(cases.getSubject());
	}

	@Override
	public void fireValueChange(Object data) {
		this.cases = (SimpleCase) data;
		casesName.setValue(cases.getSubject());
		this.getWrappedField().setValue(cases.getId());
	}

}
