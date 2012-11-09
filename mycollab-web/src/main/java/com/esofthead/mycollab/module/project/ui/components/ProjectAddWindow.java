package com.esofthead.mycollab.module.project.ui.components;

import org.vaadin.teemu.wizards.Wizard;
import org.vaadin.teemu.wizards.WizardStep;
import org.vaadin.teemu.wizards.event.WizardCancelledEvent;
import org.vaadin.teemu.wizards.event.WizardCompletedEvent;
import org.vaadin.teemu.wizards.event.WizardProgressListener;
import org.vaadin.teemu.wizards.event.WizardStepActivationEvent;
import org.vaadin.teemu.wizards.event.WizardStepSetChangedEvent;

import com.esofthead.mycollab.module.project.domain.Project;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.vaadin.ui.WizardExt;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.Form;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

@SuppressWarnings("serial")
public class ProjectAddWindow extends Window {
	private Project project;

	public ProjectAddWindow() {
		super();
		project = new Project();
		initComponents();
		center();
		this.setWidth("900px");
		this.setHeight("200px");
	}

	private void initComponents() {
		Wizard wizard = new WizardExt();
		wizard.addListener(new WizardProgressListener() {

			@Override
			public void wizardCompleted(WizardCompletedEvent event) {
				System.out.println("Wizard complete");
				// Save project information
				project.setSaccountid(AppContext.getAccountId());

				ProjectAddWindow.this.getParent().removeWindow(
						ProjectAddWindow.this);
			}

			@Override
			public void wizardCancelled(WizardCancelledEvent event) {

			}

			@Override
			public void stepSetChanged(WizardStepSetChangedEvent event) {
				// TODO Auto-generated method stub

			}

			@Override
			public void activeStepChanged(WizardStepActivationEvent event) {
				WizardStep activeStep = event.getActivatedStep();
				if (activeStep instanceof ProjectAttributesStep
						|| activeStep instanceof ProjectBudgetStep) {
					event.getWizard().getFinishButton().setEnabled(true);
				}
			}
		});

		wizard.addStep(new ProjectBasicInformation());
		wizard.addStep(new ProjectAttributesStep());
		wizard.addStep(new ProjectBudgetStep());
		this.addComponent(wizard);
	}

	private class ProjectBasicInformation extends Form implements WizardStep {
		private GridFormLayoutHelper layoutHelper;

		public ProjectBasicInformation() {
			super();
			layoutHelper = new GridFormLayoutHelper(2, 3);
			this.setLayout(layoutHelper.getLayout());
			this.setItemDataSource(new BeanItem<Project>(project));
		}

		@Override
		protected void attachField(Object propertyId, Field field) {
			layoutHelper.addComponent(propertyId.equals("name"), field,
					"Project Name", 0, 0);
			layoutHelper.addComponent(propertyId.equals("homepage"), field,
					"Home Page", 1, 0);
			layoutHelper.addComponent(propertyId.equals("shortname"), field,
					"Short Name", 0, 1);
			layoutHelper.addComponent(propertyId.equals("projecttype"), field, "Type",
					1, 1);
			if (propertyId.equals("description")) {
				layoutHelper.addComponent(field, "Description", 0, 2, 1, 2);
			}
			
		}

		@Override
		public String getCaption() {
			return "Project Information";
		}

		@Override
		public Component getContent() {
			return this;
		}

		@Override
		public boolean onAdvance() {
			return true;
		}

		@Override
		public boolean onBack() {
			return true;
		}
	}

	private class ProjectAttributesStep implements WizardStep {

		@Override
		public String getCaption() {
			return "Project Attributes";
		}

		@Override
		public Component getContent() {
			return new VerticalLayout();
		}

		@Override
		public boolean onAdvance() {
			return true;
		}

		@Override
		public boolean onBack() {
			return true;
		}
	}

	private class ProjectBudgetStep implements WizardStep {

		@Override
		public String getCaption() {
			return "Project Budget";
		}

		@Override
		public Component getContent() {
			return new VerticalLayout();
		}

		@Override
		public boolean onAdvance() {
			return true;
		}

		@Override
		public boolean onBack() {
			return true;
		}

	}
}
