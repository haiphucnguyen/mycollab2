package com.esofthead.mycollab.premium.module.project.view.risk;

import java.util.HashMap;
import java.util.Map;

import com.esofthead.mycollab.module.project.domain.Risk;
import com.esofthead.mycollab.vaadin.events.HasEditFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractPageView;
import com.esofthead.mycollab.vaadin.ui.AdvancedEditBeanForm;
import com.esofthead.mycollab.vaadin.ui.EditFormControlsGenerator;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Layout;

@ViewComponent
public class RiskAddViewImpl extends AbstractPageView implements RiskAddView {

	private static final long serialVersionUID = 1L;
	private final EditForm editForm;
	private Risk risk;

	private static Map<Integer, String> valueCaptions = new HashMap<Integer, String>(
			5);

	static {
		RiskAddViewImpl.getValueCaptions().put(1, "Epic Fail");
		RiskAddViewImpl.getValueCaptions().put(2, "Poor");
		RiskAddViewImpl.getValueCaptions().put(3, "OK");
		RiskAddViewImpl.getValueCaptions().put(4, "Good");
		RiskAddViewImpl.getValueCaptions().put(5, "Excellent");
	}

	public RiskAddViewImpl() {
		super();
		this.setMargin(true);
		this.editForm = new EditForm();
		this.addComponent(this.editForm);
	}

	@Override
	public void editItem(Risk risk) {
		this.risk = risk;
		this.editForm.setItemDataSource(new BeanItem<Risk>(risk));
	}

	public class EditForm extends AdvancedEditBeanForm<Risk> {

		private static final long serialVersionUID = 1L;

		@Override
		public void setItemDataSource(final Item newDataSource) {
			this.setFormLayoutFactory(new FormLayoutFactory());
			this.setFormFieldFactory(new RiskEditFormFieldFactory(risk));
			super.setItemDataSource(newDataSource);
		}

		class FormLayoutFactory extends RiskFormLayoutFactory {

			private static final long serialVersionUID = 1L;

			public FormLayoutFactory() {
				super("Create Risk");
			}

			private Layout createButtonControls() {
				final HorizontalLayout controlPanel = new HorizontalLayout();
				final Layout controlButtons = (new EditFormControlsGenerator<Risk>(
						EditForm.this)).createButtonControls();
				controlButtons.setSizeUndefined();
				controlPanel.addComponent(controlButtons);
				controlPanel.setWidth("100%");
				controlPanel.setComponentAlignment(controlButtons,
						Alignment.MIDDLE_CENTER);
				return controlPanel;
			}

			@Override
			protected Layout createTopPanel() {
				return this.createButtonControls();
			}

			@Override
			protected Layout createBottomPanel() {
				return this.createButtonControls();
			}
		}
	}

	@Override
	public HasEditFormHandlers<Risk> getEditFormHandlers() {
		return this.editForm;
	}

	public static Map<Integer, String> getValueCaptions() {
		return valueCaptions;
	}

	public static void setValueCaptions(Map<Integer, String> valueCaptions) {
		RiskAddViewImpl.valueCaptions = valueCaptions;
	}
}
